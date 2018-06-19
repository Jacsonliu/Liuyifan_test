package liuyifan.demo.com.test_3.pagerAdapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.ViewGroup;
import java.util.ArrayList;
import liuyifan.demo.com.test_3.Entities.City;
import liuyifan.demo.com.test_3.Fragments.frag1;
import liuyifan.demo.com.test_3.Fragments.frag2;
import liuyifan.demo.com.test_3.Fragments.frag3;
import liuyifan.demo.com.test_3.Fragments.frag4;


public class MyFragmentPagerAdapter extends FragmentPagerAdapter {
    private final int PAGER_COUNT =City.cities.size();
    public ArrayList<Fragment> list=new ArrayList<>();
    public Context context;

    public MyFragmentPagerAdapter(FragmentManager fm,Context cnt) {
            super(fm);
            context=cnt;
            initFragments();
    }

    private void initFragments() {
        for (int i=0;i<City.cities.size();i++){
            switch (i){
                case 0:
                    frag1 fgt1=new frag1();
                    fgt1.setCity(City.cities.get(i));
                    list.add(fgt1);
                    break;
                case 1:
                    frag2 fgt2=new frag2();
                    fgt2.setCity(City.cities.get(i));
                    list.add(fgt2);
                    break;
                case 2:
                    frag3 fgt3=new frag3();
                    fgt3.setCity(City.cities.get(i));
                    list.add(fgt3);
                    break;
                case 3:
                    frag4 fgt4=new frag4();
                    fgt4.setCity(City.cities.get(i));
                    list.add(fgt4);
                    break;
            }
        }
    }

    @Override
    public int getCount() {
        return PAGER_COUNT;
    }

    @Override
    public Object instantiateItem(ViewGroup vg, int position) {
        return super.instantiateItem(vg, position);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
//        super.destroyItem(container, position, object);
    }

    @Override
    public Fragment getItem(int position) {
        return list.get(position);
    }

//    @Override
//    public int getItemPosition(@NonNull Object object) {
//        return POSITION_NONE;
//    }

}
