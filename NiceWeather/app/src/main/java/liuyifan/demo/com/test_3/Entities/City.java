package liuyifan.demo.com.test_3.Entities;

import java.util.ArrayList;

public class City {
    public static ArrayList<String> cities=new ArrayList();
//    public static ArrayList<String> cities=new ArrayList(){{add("南京");add("衡阳");add("广州");}};
    public static void fresh(ArrayList<String> list){
        cities=list;
    }
    public static synchronized ArrayList  getCity(){
        return cities;
    }
}
