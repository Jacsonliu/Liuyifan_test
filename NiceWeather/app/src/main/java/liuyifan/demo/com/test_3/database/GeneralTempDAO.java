package liuyifan.demo.com.test_3.database;

import android.content.Context;

public class GeneralTempDAO {
    private Context context;
    private MyDatabaseHelper myDatabaseHelper;
    private static GeneralTempDAO DAO;
    private GeneralTempDAO(Context cnt){
        this.context=cnt;
        myDatabaseHelper=new MyDatabaseHelper(context,"WeatherTest",null,1);
    }
    public static synchronized GeneralTempDAO getInstance(Context cnt){
        if (DAO==null){
             DAO=new GeneralTempDAO(cnt);
        }
        return DAO;
    }
    public MyDatabaseHelper getMyDatabaseHelper(){
        return myDatabaseHelper;
    }
}
