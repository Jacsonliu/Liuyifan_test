package liuyifan.demo.com.test_3.Threads;

import java.util.concurrent.Callable;

import liuyifan.demo.com.test_3.Entities.AirConditon;
import liuyifan.demo.com.test_3.Entities.SpecificTemp;
import liuyifan.demo.com.test_3.Request.RequestTempData;

public class AirThread implements Callable<AirConditon> {
    private String city;

    public AirThread(String c){
        city=c;
    }

    @Override
    public AirConditon call() throws Exception {
        return RequestTempData.requestAir(city);
    }
}
