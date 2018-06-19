package liuyifan.demo.com.test_3.Threads;

import java.util.concurrent.Callable;
import liuyifan.demo.com.test_3.Entities.LivingLevel;
import liuyifan.demo.com.test_3.Request.RequestTempData;

public class LivingThread implements Callable<LivingLevel> {
    private String city;

    public LivingThread(String c){
        city=c;
    }

    @Override
    public LivingLevel call() throws Exception {
        return RequestTempData.requestLivingLevel(city);
    }
}
