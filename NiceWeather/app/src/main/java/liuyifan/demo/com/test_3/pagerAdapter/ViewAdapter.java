package liuyifan.demo.com.test_3.pagerAdapter;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

public class ViewAdapter extends PagerAdapter {
    private List<View> datas;

    public ViewAdapter(List<View> list) {
        datas=list;
    }

    @Override
    public int getCount() {
        return datas.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view==object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View view=datas.get(position);
        if(position==0){

        }
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView(datas.get(position));
    }


}
