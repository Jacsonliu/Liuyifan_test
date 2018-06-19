package liuyifan.demo.com.test_3.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.TypedValue;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import com.baoyz.swipemenulistview.SwipeMenu;
import com.baoyz.swipemenulistview.SwipeMenuCreator;
import com.baoyz.swipemenulistview.SwipeMenuItem;
import com.baoyz.swipemenulistview.SwipeMenuListView;
import liuyifan.demo.com.test_3.Entities.City;
import liuyifan.demo.com.test_3.R;
import liuyifan.demo.com.test_3.database.MyDatabaseHelper;
import liuyifan.demo.com.test_3.recycle.TestAdapter;

public class EditCityActivity extends AppCompatActivity {
    private TestAdapter testAdapter;
    private MyDatabaseHelper helper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_city);
        initToolbar();
        initRecycle();
        initBtn();

    }
//    保存修改后的城市数据
    public void saveData(){
        helper=new MyDatabaseHelper(this,"Broadcast.db",null,1);
        SQLiteDatabase db=helper.getWritableDatabase();
        db.execSQL("delete from City");
        ContentValues values=new ContentValues();
        for (int i=0;i<City.cities.size();i++) {
            values.put("CityName", City.cities.get(i));
            System.out.print(City.cities.get(i));
        }
        db.insert("City",null,values);
    }

    private void initBtn() {
        Button btn= (Button)findViewById(R.id.add_city_btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(EditCityActivity.this,AddCityActivity.class ));
            }
        });
    }

    private void initToolbar() {
        Toolbar mToolbarTb = (Toolbar) findViewById(R.id.toolbar4);
        setSupportActionBar(mToolbarTb);
        ActionBar actionBar=getSupportActionBar();
        if(actionBar!=null){
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.drawable.no);
        }
        getSupportActionBar().setDisplayShowTitleEnabled(false);
//   确认修改
        mToolbarTb.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            //设定确认修改的跳转
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()){
                    case R.id.confirm:
                        City.cities=testAdapter.mcity;
                        saveData();
                        startActivity(new Intent(EditCityActivity.this,MainActivity.class));
                        finish();
                        break;
                }
                return true;
            }
        });

    }

    //取消修改
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                break;
            default:
        }
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_item2,menu);
        return true;
    }
    private int dp2px(int dp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp,
                getResources().getDisplayMetrics());
    }


    public void initRecycle(){
        SwipeMenuListView swipeMenuListView=(SwipeMenuListView)findViewById(R.id.recycle);
        testAdapter=new TestAdapter(this,City.cities);
        swipeMenuListView.setAdapter(testAdapter);
        SwipeMenuCreator creator = new SwipeMenuCreator() {

            @Override
            public void create(SwipeMenu menu) {

                // create "delete" item
                SwipeMenuItem deleteItem = new SwipeMenuItem(
                        getApplicationContext());
                // set item background
                // set item width
                deleteItem.setWidth(dp2px(90));
                // set a icon
                deleteItem.setTitle("删除");
                deleteItem.setTitleSize(18);
                deleteItem.setTitleColor(R.color.red);
                // add to menu
                menu.addMenuItem(deleteItem);
            }
        };
        swipeMenuListView.setMenuCreator(creator);
        swipeMenuListView.setOnMenuItemClickListener(new SwipeMenuListView.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(int position, SwipeMenu menu, int index) {
                switch (index){
                    case 0:
                        if (testAdapter.mcity.size()==1){
                        }
                        else {
                            testAdapter.mcity.remove(position);
                            testAdapter.notifyDataSetChanged();
                        }
                    break;
                }
                return false;
            }
        });
    }
}
