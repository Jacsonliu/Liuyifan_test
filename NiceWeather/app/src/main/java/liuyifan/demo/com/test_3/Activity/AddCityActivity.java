package liuyifan.demo.com.test_3.Activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import liuyifan.demo.com.test_3.Entities.City;
import liuyifan.demo.com.test_3.R;

public class AddCityActivity extends AppCompatActivity implements View.OnClickListener{
    private EditText editText;
    private Button btn;
    private Button btn1;
    private Button btn2;
    private Button btn3;
    private Button btn4;
    private Button btn5;
    private Button btn6;
    private Button btn7;
    private Button btn8;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_city);
        initToolbar();
        initBtn();
    }

    private void initBtn() {
        editText=(EditText)findViewById(R.id.add_city);
        btn= (Button) findViewById(R.id.confirm_add);
        btn1=(Button) findViewById(R.id.hot_city_1);
        btn2=(Button) findViewById(R.id.hot_city_2);
        btn3=(Button) findViewById(R.id.hot_city_3);
        btn4=(Button) findViewById(R.id.hot_city_4);
        btn5=(Button) findViewById(R.id.hot_city_5);
        btn6=(Button) findViewById(R.id.hot_city_6);
        btn7=(Button) findViewById(R.id.hot_city_7);
        btn8=(Button) findViewById(R.id.hot_city_8);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!editText.getText().toString().equals("")){
                    City.cities.add(editText.getText().toString());
                    startActivity(new Intent(AddCityActivity.this,EditCityActivity.class));
                    AddCityActivity.this.finish();
                }
                else {
                    AlertDialog.Builder dialog=new AlertDialog.Builder(AddCityActivity.this);
                    dialog.setTitle("警告");
                    dialog.setMessage("输入为空");
                    dialog.setCancelable(false);
                    dialog.setPositiveButton("ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    });
                    dialog.show();
                }

            }
        });
        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);
        btn4.setOnClickListener(this);
        btn5.setOnClickListener(this);
        btn6.setOnClickListener(this);
        btn7.setOnClickListener(this);
        btn8.setOnClickListener(this);
    }

    private void initToolbar() {
        Toolbar mToolbarTb = (Toolbar) findViewById(R.id.add_city_toolbar);
        setSupportActionBar(mToolbarTb);
        ActionBar actionBar=getSupportActionBar();
        if(actionBar!=null){
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.drawable.no);
        }
        getSupportActionBar().setDisplayShowTitleEnabled(false);


    }

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


    public void jump(){
        Intent intent=new Intent(this,EditCityActivity.class);
        startActivity(intent);
        finish();
    }


    public void onClick(View v) {
        Button btn;
        switch (v.getId()){
            case R.id.hot_city_1:
                btn=(Button) findViewById(R.id.hot_city_1);
                City.cities.add(btn.getText().toString());
                jump();
                break;
            case R.id.hot_city_2:
                btn=(Button)findViewById(R.id.hot_city_2);
                City.cities.add(btn.getText().toString());
                jump();
                break;
            case R.id.hot_city_3:
                btn=(Button)findViewById(R.id.hot_city_3);
                City.cities.add(btn.getText().toString());
                jump();
                break;
            case R.id.hot_city_4:
                btn=(Button)findViewById(R.id.hot_city_4);
                City.cities.add(btn.getText().toString());

                jump();
                break;
            case R.id.hot_city_5:
                btn=(Button)findViewById(R.id.hot_city_5);
                City.cities.add(btn.getText().toString());
                jump();
                break;
            case R.id.hot_city_6:
                btn=(Button)findViewById(R.id.hot_city_6);
                City.cities.add(btn.getText().toString());
                jump();
                break;
            case R.id.hot_city_7:
                btn=(Button)findViewById(R.id.hot_city_7);
                City.cities.add(btn.getText().toString());
                jump();
                break;
            case R.id.hot_city_8:
                btn=(Button)findViewById(R.id.hot_city_8);
                City.cities.add(btn.getText().toString());
                jump();
                break;
        }
    }
}
