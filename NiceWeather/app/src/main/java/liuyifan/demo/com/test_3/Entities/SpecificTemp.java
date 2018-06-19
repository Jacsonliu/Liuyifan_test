package liuyifan.demo.com.test_3.Entities;

public class SpecificTemp {
   private String temp;
   private String cityname;
   private String feel_temp;
   private String humidity;
   private String wind_scale;
   private String weather;



    public void setCityname(String cityname) {
        this.cityname = cityname;
    }

    public String getCityname() {
        return cityname;
    }

    public String getFeel_temp() {
        return feel_temp;
    }

    public void setFeel_temp(String feel_temp) {
        this.feel_temp = feel_temp;
    }

    public String getHumidity() {
        return humidity;
    }

    public void setHumidity(String humidity) {
        this.humidity = humidity;
    }

    public String getWind_scale() {
        return wind_scale;
    }

    public void setWind_scale(String wind_scale) {
        this.wind_scale = wind_scale;
    }

    public String getTemp() {
        return temp;
    }

    public void setTemp(String temp) {
        this.temp = temp;
    }

    public String getWeather() {
        return weather;
    }

    public void setWeather(String weather) {
        this.weather = weather;
    }
}
