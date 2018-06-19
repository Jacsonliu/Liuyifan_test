package liuyifan.demo.com.test_3.Activity;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import liuyifan.demo.com.test_3.R;

public class setting extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        initToolBar();
    }

    private void initToolBar() {
//        Toolbar mToolbarTb = (Toolbar) findViewById(R.id.toolbar_setting);
//        setSupportActionBar(mToolbarTb);
//        ActionBar actionBar=getSupportActionBar();
//        if(actionBar!=null){
//            actionBar.setDisplayHomeAsUpEnabled(true);
//            actionBar.setHomeAsUpIndicator(R.drawable.no);
//        }
//        getSupportActionBar().setDisplayShowTitleEnabled(false);
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
}
