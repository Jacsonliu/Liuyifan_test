package liuyifan.demo.com.test_3.Threads;

import android.content.Context;
import android.util.Log;

import org.json.JSONObject;

import java.util.concurrent.Callable;

import liuyifan.demo.com.test_3.Entities.GeneralTemp;
import liuyifan.demo.com.test_3.Entities.SpecificTemp;
import liuyifan.demo.com.test_3.Request.RequestTempData;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class SpecificThread implements Callable<SpecificTemp> {

    private String cityChoosed;
    public  SpecificThread(String city){
        cityChoosed=city;
    }
    @Override
    public SpecificTemp call() throws Exception {
        return  RequestTempData.requestCurrentTemp(cityChoosed);

    }
}
