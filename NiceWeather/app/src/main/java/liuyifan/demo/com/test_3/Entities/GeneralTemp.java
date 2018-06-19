package liuyifan.demo.com.test_3.Entities;

import android.support.annotation.VisibleForTesting;

public class GeneralTemp {
    private int temperature;
    private String city;
    private String weather;
    public GeneralTemp(int t,String c,String w){
        this.temperature=t;
        this.city=c;
        this.weather=w;
    }
    public int getTemperature() {
        return temperature;
    }

    public void setTemperature(int temperature) {
        this.temperature = temperature;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getWeather() {
        return weather;
    }

    public void setWeather(String weather) {
        this.weather = weather;
    }
}
