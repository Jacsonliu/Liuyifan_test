package liuyifan.demo.com.test_3.Threads;

import java.util.ArrayList;
import java.util.concurrent.Callable;

import liuyifan.demo.com.test_3.Request.RequestTempData;

public class HourlyTemp implements Callable<ArrayList<String>> {
    private  String city;
    public HourlyTemp(String c){
        city=c;
    }
    @Override
    public ArrayList<String> call() throws Exception {
        return RequestTempData.requestTempPerHour(city);
    }
}
