package liuyifan.demo.com.test_3.Request;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import liuyifan.demo.com.test_3.Entities.AirConditon;
import liuyifan.demo.com.test_3.Entities.FutureTemp;
import liuyifan.demo.com.test_3.Entities.LivingLevel;
import liuyifan.demo.com.test_3.Entities.SpecificTemp;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class RequestTempData {

    public static  ArrayList<String> requestTempPerHour(String city)  {
        ArrayList<String> temp=new ArrayList();
        try {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("https://api.seniverse.com/v3/weather/hourly.json?key=0dravfqrktqbnvyh&location="+city+"&language=zh-Hans&unit=c&start=0&hours=12")
                .build();
        Response response = client.newCall(request).execute();
        String json = response.body().string();
        JSONObject json_test= null;
        json_test = new JSONObject(json);
        for (int i=0;i<12;i++) {
            temp.add(json_test.getJSONArray("results").getJSONObject(0).getJSONArray("hourly").getJSONObject(i).get("temperature").toString());
        }

        } catch (JSONException e) {
            e.printStackTrace();
        }
          catch (IOException e){
            e.printStackTrace();
        }
        return temp;
    }


    public static SpecificTemp requestCurrentTemp(String city){
        SpecificTemp specificTemp=new SpecificTemp();
        try {
            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder()
                    .url("https://api.seniverse.com/v3/weather/now.json?key=0dravfqrktqbnvyh&location="+city+"&language=zh-Hans&unit=c")
                    .build();
            Response response = client.newCall(request).execute();
            String json = response.body().string();
            JSONObject json_test= null;
            json_test = new JSONObject(json);
            specificTemp.setCityname(city);
            specificTemp.setFeel_temp(json_test.getJSONArray("results").getJSONObject(0).getJSONObject("now").get("feels_like").toString());
            specificTemp.setHumidity(json_test.getJSONArray("results").getJSONObject(0).getJSONObject("now").get("humidity").toString());
            specificTemp.setWeather(json_test.getJSONArray("results").getJSONObject(0).getJSONObject("now").get("text").toString());
            specificTemp.setTemp(json_test.getJSONArray("results").getJSONObject(0).getJSONObject("now").get("temperature").toString());
            specificTemp.setWind_scale(json_test.getJSONArray("results").getJSONObject(0).getJSONObject("now").get("wind_scale").toString());

        } catch (JSONException e) {
            e.printStackTrace();
        }
        catch (IOException e){
            e.printStackTrace();
        }

        return specificTemp;
    }

    public static AirConditon requestAir(String city){
        AirConditon airConditon=new AirConditon();
        try {
            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder()
                    .url("https://api.seniverse.com/v3/air/now.json?key=0dravfqrktqbnvyh&location="+city+"&language=zh-Hans&scope=city")

                    .build();
            Response response = client.newCall(request).execute();
            String json = response.body().string();
            JSONObject json_test= new JSONObject(json);
            airConditon.setCity(city);
            airConditon.setAqi(json_test.getJSONArray("results").getJSONObject(0).getJSONObject("air").getJSONObject("city").get("aqi").toString());
            airConditon.setPm25(json_test.getJSONArray("results").getJSONObject(0).getJSONObject("air").getJSONObject("city").get("pm25").toString());
            airConditon.setPrimary_pollutant(json_test.getJSONArray("results").getJSONObject(0).getJSONObject("air").getJSONObject("city").get("primary_pollutant").toString());
            airConditon.setQuality(json_test.getJSONArray("results").getJSONObject(0).getJSONObject("air").getJSONObject("city").get("quality").toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        catch (IOException e){
            e.printStackTrace();
        }
        return airConditon;

    }

    public static LivingLevel requestLivingLevel(String city){
        LivingLevel livingLevel=new LivingLevel();
        try {
            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder()
                    .url("https://api.seniverse.com/v3/life/suggestion.json?key=0dravfqrktqbnvyh&location="+city+"&language=zh-Hans")
                    .build();
            Response response = client.newCall(request).execute();
            String json = response.body().string();
            JSONObject json_test= new JSONObject(json);
            livingLevel.setCity(city);
            livingLevel.setCar_washing(json_test.getJSONArray("results").getJSONObject(0).getJSONObject("suggestion").getJSONObject("car_washing").get("details").toString());
            livingLevel.setDressing(json_test.getJSONArray("results").getJSONObject(0).getJSONObject("suggestion").getJSONObject("dressing").get("details").toString());
            livingLevel.setSport(json_test.getJSONArray("results").getJSONObject(0).getJSONObject("suggestion").getJSONObject("sport").get("details").toString());
            livingLevel.setRoad_condition(json_test.getJSONArray("results").getJSONObject(0).getJSONObject("suggestion").getJSONObject("road_condition").get("details").toString());
            livingLevel.setComfort(json_test.getJSONArray("results").getJSONObject(0).getJSONObject("suggestion").getJSONObject("comfort").get("details").toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        catch (IOException e){
            e.printStackTrace();
        }
        return livingLevel;

    }


    public static FutureTemp requestFuture(String city){
        FutureTemp futureTemp=new FutureTemp();
        try {
            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder()
                    .url("https://api.seniverse.com/v3/weather/daily.json?key=0dravfqrktqbnvyh&location="+city+"&language=zh-Hans&unit=c&start=0&days=4")
                    .build();
            Response response = client.newCall(request).execute();
            String json = response.body().string();
            JSONObject json_test= new JSONObject(json);

            for (int i=0;i<3;i++) {
                futureTemp.low[i]=json_test.getJSONArray("results").getJSONObject(0).getJSONArray("daily").getJSONObject(i).getString("low");
                futureTemp.high[i]=json_test.getJSONArray("results").getJSONObject(0).getJSONArray("daily").getJSONObject(i).getString("high");
                futureTemp.weather[i]=json_test.getJSONArray("results").getJSONObject(0).getJSONArray("daily").getJSONObject(i).getString("text_day");
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        catch (IOException e){
            e.printStackTrace();
        }

        return futureTemp;



    }





}
