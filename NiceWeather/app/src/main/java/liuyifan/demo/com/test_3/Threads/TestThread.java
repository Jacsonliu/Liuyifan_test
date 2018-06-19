package liuyifan.demo.com.test_3.Threads;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import org.json.JSONObject;
import java.util.concurrent.Callable;
import liuyifan.demo.com.test_3.Entities.GeneralTemp;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import liuyifan.demo.com.test_3.database.*;
public class TestThread implements Callable <GeneralTemp>{
    private GeneralTemp generalTemp;
    private Context cnt;
    private String citychoosed;//便于以后从数据库中读取城市后动态改变
    public TestThread(String string , Context context){
        citychoosed=string;
        cnt=context;
    }
    @Override
    public GeneralTemp call() throws Exception {
        int temp;
        String city;
        String weather;
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("https://api.seniverse.com/v3/weather/now.json?key=0dravfqrktqbnvyh&location="+citychoosed+"&language=zh-Hans")
                .build();
        Response response = client.newCall(request).execute();
        String json = response.body().string();
        JSONObject json_test=new JSONObject(json);
        weather=json_test.getJSONArray("results").getJSONObject(0).getJSONObject("now").get("text").toString();
        city=json_test.getJSONArray("results").getJSONObject(0).getJSONObject("location").get("name").toString();
        temp=Integer.parseInt(json_test.getJSONArray("results").getJSONObject(0).getJSONObject("now").get("temperature").toString());
        Log.e("请求成功", city);
        Log.e("请求成功", ""+temp);
        generalTemp=new GeneralTemp(temp,city,weather);
        saveData(generalTemp);
        return generalTemp;
    }
    public void saveData(GeneralTemp temp){
        SQLiteDatabase db=GeneralTempDAO.getInstance(cnt).getMyDatabaseHelper().getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put("city",temp.getCity());
        values.put("temperature",temp.getTemperature());
        values.put("weather",temp.getWeather());
        db.insert("GeneralWeather",null,values);
        Log.e("数据库", "储存成功");
    }
}
