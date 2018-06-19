package liuyifan.demo.com.test_3.Fragments;
import liuyifan.demo.com.test_3.Entities.*;
import liuyifan.demo.com.test_3.Threads.AirThread;
import liuyifan.demo.com.test_3.Threads.HourlyTemp;
import liuyifan.demo.com.test_3.Threads.LivingThread;
import liuyifan.demo.com.test_3.Threads.SpecificThread;
import liuyifan.demo.com.test_3.Threads.TestThread;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.github.mikephil.charting.charts.LineChart;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

import liuyifan.demo.com.test_3.LineChart.LineChartManager;
import liuyifan.demo.com.test_3.R;
import liuyifan.demo.com.test_3.Threads.ThreeDayThread;
import liuyifan.demo.com.test_3.recycle.DividerItemDecoration;
import liuyifan.demo.com.test_3.recycle.ThreeDayForcastAdapter;

public class frag1 extends Fragment {
    private String city;
    private SwipeRefreshLayout swipeRefreshLayout;
    public void setCity(String cityname){
        city=cityname;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.pager_1, container, false);
        setFreshView(view);
        setLineView(view);
        freshWeather(view);
        initRecycle(view);
        return view;
    }

    private void initRecycle(View view) {
        FutureTask<FutureTemp> future = new FutureTask<FutureTemp>(new ThreeDayThread(city));
        new Thread(future).start();

        RecyclerView recyclerView=(RecyclerView)view.findViewById(R.id.recycle_1);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(linearLayoutManager);

        try{
            ThreeDayForcastAdapter threeDayForcastAdapter=new ThreeDayForcastAdapter();
            threeDayForcastAdapter.futureTemp=future.get();
            recyclerView.setAdapter(threeDayForcastAdapter);
            recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(),DividerItemDecoration.VERTICAL_LIST));
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

    }

    private void setLineView(View view) {
        FutureTask<ArrayList<String>> future = new FutureTask<ArrayList<String>>(new HourlyTemp(city));
        new Thread(future).start();
        LineChart lineChart1 = (LineChart) view.findViewById(R.id.line_chart1);
        LineChartManager lineChartManager1 = new LineChartManager(lineChart1);
        //设置x轴的数据

        ArrayList<Float> xValues = new ArrayList<>();
        for (int i = 0; i < 12; i++) {
            xValues.add((float)(i));
    }

        //设置y轴的数据()
        List<Float> yValues = new ArrayList<>();
        for (int j = 0; j < 12; j++) {
            try {
                yValues.add(Float.parseFloat(future.get().get(j)));
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }

        lineChartManager1.showLineChart(xValues, yValues,"折线", Color.GREEN);
        lineChartManager1.setDescription("未来温度变化预测");
        lineChartManager1.setYAxis(45, 0, 11);
        lineChartManager1.setHightLimitLine(38,"高温报警",Color.RED);

    }
    private void setFreshView(View view){
        swipeRefreshLayout=(SwipeRefreshLayout)view.findViewById(R.id.swipe_fresh);
        swipeRefreshLayout.setColorSchemeResources(R.color.pink);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refreshAll();
            }
        });
    }

    private void refreshAll() {
        FutureTask<SpecificTemp> future = new FutureTask<SpecificTemp>(new SpecificThread(city));
        new Thread(future).start();

        FutureTask<AirConditon> future2=new FutureTask<AirConditon>(new AirThread(city));
        new Thread(future2).start();

        FutureTask<LivingLevel> future3=new FutureTask<LivingLevel>(new LivingThread(city));
        new Thread(future3).start();

        try {
            TextView textView1=(TextView) getActivity().findViewById(R.id.weather1);
            TextView textView2=(TextView) getActivity().findViewById(R.id.wind1);
            TextView textView3=(TextView) getActivity().findViewById(R.id.temp1);
            TextView textView4=(TextView) getActivity().findViewById(R.id.feel_temp1);
            TextView textView5=(TextView) getActivity().findViewById(R.id.city1);
            TextView textView6=(TextView) getActivity().findViewById(R.id.wet1);
            textView1.setText(future.get().getWeather());
            textView2.setText("风力指数："+future.get().getWind_scale());
            textView3.setText(future.get().getTemp()+"°C");
            textView4.setText("体感温度："+future.get().getFeel_temp()+"°C");
            textView5.setText(future.get().getCityname());
            textView6.setText("相对湿度："+future.get().getHumidity());

            TextView textView7=(TextView) getActivity().findViewById(R.id.pm25_1);
            TextView textView8=(TextView) getActivity().findViewById(R.id.aqi_1);
            TextView textView9=(TextView) getActivity().findViewById(R.id.polution_1);
            TextView textView10=(TextView) getActivity().findViewById(R.id.air_quality_1);
            textView7.setText(future2.get().getPm25());
            textView8.setText(future2.get().getAqi());
            textView9.setText("主要污染物："+future2.get().getPrimary_pollutant());
            textView10.setText("空气质量："+future2.get().getQuality());

            TextView textView11=(TextView) getActivity().findViewById(R.id.confort_1);
            TextView textView12=(TextView) getActivity().findViewById(R.id.wash_1);
            TextView textView13=(TextView) getActivity().findViewById(R.id.sport_1);
            TextView textView14=(TextView) getActivity().findViewById(R.id.road_1);
            TextView textView15=(TextView) getActivity().findViewById(R.id.dress_1);
            textView11.setText("舒适度："+future3.get().getComfort());
            textView12.setText("洗车指数："+future3.get().getCar_washing());
            textView13.setText("运动指数："+future3.get().getSport());
            textView14.setText("路况指数："+future3.get().getRoad_condition());
            textView15.setText("穿衣指数："+future3.get().getDressing());

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        swipeRefreshLayout.setRefreshing(false);

    }
    //用于在初始化的时候获得相关的天气内容
    private void freshWeather(View view) {
        if (view != null) {

            FutureTask<SpecificTemp> future = new FutureTask<SpecificTemp>(new SpecificThread(city));
            new Thread(future).start();

            Callable<AirConditon> test2 = new AirThread(city);
            FutureTask<AirConditon> future2=new FutureTask<AirConditon>(test2);
            new Thread(future2).start();

            Callable<LivingLevel> test3 = new LivingThread(city);
            FutureTask<LivingLevel> future3=new FutureTask<LivingLevel>(test3);
            new Thread(future3).start();

            try {
                TextView textView1=(TextView) view.findViewById(R.id.weather1);
                TextView textView2=(TextView) view.findViewById(R.id.wind1);
                TextView textView3=(TextView) view.findViewById(R.id.temp1);
                TextView textView4=(TextView) view.findViewById(R.id.feel_temp1);
                TextView textView5=(TextView) view.findViewById(R.id.city1);
                TextView textView6=(TextView) view.findViewById(R.id.wet1);
                textView1.setText(future.get().getWeather());
                textView2.setText("风力指数："+future.get().getWind_scale());
                textView3.setText(future.get().getTemp()+"°C");
                textView4.setText("体感温度："+future.get().getFeel_temp()+"°C");
                textView5.setText(future.get().getCityname());
                textView6.setText("相对湿度："+future.get().getHumidity());


                TextView textView7=(TextView) view.findViewById(R.id.pm25_1);
                TextView textView8=(TextView) view.findViewById(R.id.aqi_1);
                TextView textView9=(TextView) view.findViewById(R.id.polution_1);
                TextView textView10=(TextView) view.findViewById(R.id.air_quality_1);
                textView7.setText(future2.get().getPm25());
                textView8.setText(future2.get().getAqi());
                textView9.setText("主要污染物："+future2.get().getPrimary_pollutant());
                textView10.setText("空气质量："+future2.get().getQuality());


                TextView textView11=(TextView) view.findViewById(R.id.confort_1);
                TextView textView12=(TextView) view.findViewById(R.id.wash_1);
                TextView textView13=(TextView) view.findViewById(R.id.sport_1);
                TextView textView14=(TextView) view.findViewById(R.id.road_1);
                TextView textView15=(TextView) view.findViewById(R.id.dress_1);
                textView11.setText("舒适度："+future3.get().getComfort());
                textView12.setText("洗车指数："+future3.get().getCar_washing());
                textView13.setText("运动指数："+future3.get().getSport());
                textView14.setText("路况指数："+future3.get().getRoad_condition());
                textView15.setText("穿衣指数："+future3.get().getDressing());



            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }
    }


}
