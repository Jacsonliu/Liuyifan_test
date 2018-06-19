package liuyifan.demo.com.test_3.recycle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import liuyifan.demo.com.test_3.Entities.City;
import liuyifan.demo.com.test_3.R;

import android.content.Context;

public class TestAdapter extends BaseSwipListAdapter {

    public  ArrayList<String> mcity;
    public Context mcontext;

    @Override
    public int getCount() {
        return mcity.size();
    }

    @Override
    public String getItem(int position) {
        return mcity.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view=LayoutInflater.from(mcontext).inflate(R.layout.recycleitem_3,parent,false);
        TextView city = (TextView) view.findViewById(R.id.item3_text);
        city.setText(City.cities.get(position));
        return view;
    }

    public TestAdapter(Context context,ArrayList<String> city){
        mcity=city;
        mcontext=context;
    }
}
