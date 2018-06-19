package liuyifan.demo.com.test_3.Threads;

import java.util.concurrent.Callable;

import liuyifan.demo.com.test_3.Entities.FutureTemp;
import liuyifan.demo.com.test_3.Entities.GeneralTemp;
import liuyifan.demo.com.test_3.Request.RequestTempData;

public class ThreeDayThread implements Callable<FutureTemp> {
    private String city;
    public  ThreeDayThread(String c){
        city=c;
    }
    @Override
    public FutureTemp call() throws Exception {
        return RequestTempData.requestFuture(city);
    }
}
