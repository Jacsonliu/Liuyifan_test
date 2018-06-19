package liuyifan.demo.com.test_3.recycle;
import java.util.List;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import liuyifan.demo.com.test_3.Entities.FutureTemp;
import liuyifan.demo.com.test_3.R;

public class ThreeDayForcastAdapter extends RecyclerView.Adapter<ThreeDayForcastAdapter.ForcastHolder> {
    public FutureTemp futureTemp;
    @Override
    public ForcastHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.recycleitem_2,parent,false);
        ForcastHolder forcastHolder=new ForcastHolder(view);
        return  forcastHolder;
    }

    @Override
    public void onBindViewHolder(ForcastHolder holder, int position) {
            holder.textView.setText(futureTemp.day[position]);
            holder.textView2.setText(futureTemp.weather[position]);
            holder.textView3.setText(futureTemp.low[position]+"°C~"+futureTemp.high[position]+"°C");
            if (futureTemp.weather[position].contains("云")) {
                holder.imageView.setImageResource(R.drawable.cloudy);
            }else if (futureTemp.weather[position].contains("晴")){
                holder.imageView.setImageResource(R.drawable.sunny);
            }else if (futureTemp.weather[position].contains("雷")){
                holder.imageView.setImageResource(R.drawable.flash);
            }else if (futureTemp.weather[position].contains("雨")){
                holder.imageView.setImageResource(R.drawable.rain);
            }
    }

    @Override
    public int getItemCount() {
        return 3;
    }
    //
    static class ForcastHolder  extends RecyclerView.ViewHolder{
        TextView  textView;
        ImageView imageView;
        TextView  textView2;
        TextView  textView3;
        public ForcastHolder(View itemView) {
            super(itemView);
            textView=itemView.findViewById(R.id.recycle_item_2_1);
            textView2=itemView.findViewById(R.id.recycle_item_2_3);
            textView3=itemView.findViewById(R.id.recycle_item_2_4);
            imageView=itemView.findViewById(R.id.recycle_item_2_2);
        }
    }
}
