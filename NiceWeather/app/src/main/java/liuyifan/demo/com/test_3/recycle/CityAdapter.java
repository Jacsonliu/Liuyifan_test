package liuyifan.demo.com.test_3.recycle;

import android.support.design.widget.Snackbar;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.ArrayList;
import liuyifan.demo.com.test_3.R;

public class CityAdapter extends RecyclerView.Adapter<CityAdapter.CityHolder>{
    public ArrayList<String> mCity;
    @Override
    public CityHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.recycleitem,parent,false);
        CityHolder cityHolder=new CityHolder(view);
        return cityHolder;
    }

    @Override
    public void onBindViewHolder(CityHolder holder, final int position) {
        String name=mCity.get(position);
        holder.textView.setText(name);
        holder.imageView.setImageResource(R.drawable.minus);
        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mCity.size()==1){
                    Snackbar.make(v, "此条目不能删除", Snackbar.LENGTH_SHORT).show();
                }
                else {
                    deleteItem(position);
                }
            }
        })

        ;
    }
    public void addData(int position,String cityName) {
//      在list中添加数据，并通知条目加入一条
        mCity.add(position,cityName);
        //添加动画
        notifyItemInserted(position);
    }

    public void deleteItem(int position){
        mCity.remove(position);
        notifyItemRemoved(position);
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return mCity.size();
    }


    static class CityHolder  extends RecyclerView.ViewHolder{
        TextView textView;
        ImageView imageView;
        public CityHolder(View view){
            super(view);
            textView=(TextView)view.findViewById(R.id.item_text);
            imageView=(ImageView)view.findViewById(R.id.item_img);
        }
    }

    public CityAdapter(ArrayList<String> city) {
        mCity=city;
    }
}
