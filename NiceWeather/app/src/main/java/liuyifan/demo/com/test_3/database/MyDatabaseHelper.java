package liuyifan.demo.com.test_3.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MyDatabaseHelper extends SQLiteOpenHelper {
    private Context mContext;
    private String CREATE_TABLE_GENWEATHER="create table GeneralWeather("
            +"id integer primary key autoincrement  ,"
            +"city text,"
            +"temperature integer,"
            +"weather text)";

    private String CREATE_TABLE_CITY="create table City("
            +"CityName text)";



    private String CREATE_TABLE_SPECIFICWEATHER="";

    public MyDatabaseHelper(Context context,String name,SQLiteDatabase.CursorFactory factory, int version){
        super(context, name, factory, version);
        mContext=context;
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
                       db.execSQL(CREATE_TABLE_CITY);

    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
                db.execSQL("drop table if exists GeneralWeather");
                onCreate(db);
    }
}
