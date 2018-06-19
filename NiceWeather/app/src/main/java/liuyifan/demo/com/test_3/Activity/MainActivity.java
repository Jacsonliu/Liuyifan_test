package liuyifan.demo.com.test_3.Activity;
import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.util.List;

import liuyifan.demo.com.test_3.R;
import liuyifan.demo.com.test_3.database.MyDatabaseHelper;
import liuyifan.demo.com.test_3.pagerAdapter.MyFragmentPagerAdapter;
import liuyifan.demo.com.test_3.pagerAdapter.ZoomOutPageTransformer;

public class MainActivity extends AppCompatActivity {
    private DrawerLayout mDrawerLayout;
    private ViewPager viewPager_test;
    private List<View> views;
    private MyFragmentPagerAdapter mAdapter;
    private MyDatabaseHelper helper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initPageViewer();//初始化pagerViewer
        initToolBar();//初始化标题栏
        initMenu();//初始化滑动菜单
    }

    //初始化viewpager
    private void initPageViewer(){
        viewPager_test=(ViewPager)findViewById(R.id.viewpager_test);
        mAdapter = new MyFragmentPagerAdapter(getSupportFragmentManager(),this);
        viewPager_test.setAdapter(mAdapter);
        viewPager_test.setPageTransformer(true, new ZoomOutPageTransformer());



    }
    // 初始化标题栏
    private void initToolBar(){
        Toolbar toolbar=(Toolbar) findViewById(R.id.toolbar3);//设置toolbar为标题栏
        setSupportActionBar(toolbar);//
        ActionBar actionBar=getSupportActionBar();
        if(actionBar!=null){
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.drawable.leftbtn);
        }
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()){
                    case R.id.edit:
//                        Toast.makeText(MainActivity.this,"你点击了编辑按钮",Toast.LENGTH_LONG).show();
                        //以下为跳转逻辑
                        Intent edit=new Intent(MainActivity.this,EditCityActivity.class);
                        startActivity(edit);
                        break;
                    default:
                        break;
                }
                return true;
            }
        });
    }

    //实现左滑动菜单
    private void initMenu(){
        mDrawerLayout=(DrawerLayout) findViewById(R.id.drawer_layout);
        NavigationView navigationView=(NavigationView)findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                mDrawerLayout.closeDrawers();
                return true;
            }
        });
    }

    //   创建右侧菜单栏触发
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_item,menu);
        return true;
    }

    //   左侧菜单栏被选中触发
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);
                break;
            default:
        }
        return true;
    }



}






