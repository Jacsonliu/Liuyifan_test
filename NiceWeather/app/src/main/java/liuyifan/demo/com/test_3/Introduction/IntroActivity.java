
package liuyifan.demo.com.test_3.Introduction;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.chyrta.onboarder.OnboarderActivity;
import com.chyrta.onboarder.OnboarderPage;

import java.util.ArrayList;
import java.util.List;

import liuyifan.demo.com.test_3.Entities.City;
import liuyifan.demo.com.test_3.Activity.MainActivity;
import liuyifan.demo.com.test_3.R;
import liuyifan.demo.com.test_3.database.MyDatabaseHelper;

public class IntroActivity extends OnboarderActivity {

    List<OnboarderPage> onboarderPages;
    private MyDatabaseHelper helper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initCity();
        onboarderPages = new ArrayList<OnboarderPage>();
        // Create your first page
        OnboarderPage onboarderPage1 = new OnboarderPage("晴天", "晴日暖风生麦气，绿阴幽草胜花时", R.drawable.sunny);
        OnboarderPage onboarderPage2 = new OnboarderPage("阴雨", "春风桃李花开日，秋雨梧桐叶落时", R.drawable.cloudy);
        OnboarderPage onboarderPage3 = new OnboarderPage("雷电", "莫道无心畏雷电, 海龙王处也横行", R.drawable.flash);
        // You can define title and description colors (by default white)
        // Don't forget to set background color for your page
       onboarderPage1.setBackgroundColor(R.color.yellow_light);
       onboarderPage2.setBackgroundColor(R.color.blue_light);
       onboarderPage3.setBackgroundColor(R.color.pink);
        setActiveIndicatorColor(android.R.color.white);
        setInactiveIndicatorColor(android.R.color.darker_gray);
        setDividerColor(Color.WHITE);
        setDividerVisibility(View.GONE);
        onboarderPage1.setTitleTextSize(25);
        onboarderPage1.setDescriptionTextSize(20);
        onboarderPage2.setTitleTextSize(25);
        onboarderPage2.setDescriptionTextSize(20);
        onboarderPage3.setTitleTextSize(25);
        onboarderPage3.setDescriptionTextSize(20);
        setSkipButtonHidden();
        // Add your pages to the list
        onboarderPages.add(onboarderPage1);
        onboarderPages.add(onboarderPage2);
        onboarderPages.add(onboarderPage3);
        // And pass your pages to 'setOnboardPagesReady' method
        setOnboardPagesReady(onboarderPages);

    }

    @Override
    public void onSkipButtonPressed() {
        // Optional: by default it skips onboarder to the end
        super.onSkipButtonPressed();
        // Define your actions when the user press 'Skip' button
    }

    @Override
    public void onFinishButtonPressed() {
        // Define your actions when the user press 'Finish' button
        Intent intent=new Intent(IntroActivity.this,MainActivity.class);
        startActivity(intent);
        this.finish();
    }
//    初始化城市，如果数据库中没有相关数据则设定初始值。
    private void initCity() {
        helper=new MyDatabaseHelper(this,"Broadcast.db",null,1);
        SQLiteDatabase db=helper.getWritableDatabase();
        Cursor cursor=db.query("City",null,null,null,null,null,null);
        if (cursor.moveToFirst()){
            do {
                Log.d("城市",cursor.getString(cursor.getColumnIndex("CityName")));
                City.cities.add(cursor.getString(0));
            }while(cursor.moveToNext());
        }
        else {
            City.cities.add("南京");
            City.cities.add("广州");
        }
        cursor.close();
    }

}
