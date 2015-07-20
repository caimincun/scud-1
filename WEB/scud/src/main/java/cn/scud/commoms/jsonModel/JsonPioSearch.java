package cn.scud.commoms.jsonModel;

import java.util.List;

/**
 * Created by Administrator on 2015/7/20.
 */
public class JsonPioSearch {
    public int status;
    public int total;
    public int size;
    public List<Content> contents;
//    {"status":0, "total":6, "size":6, "contents":[{"tags":"男 女", "uid":1044253868, "province":"四川省", "geotable_id":113321, "modify_time":1436860984, "district":"锦江区", "create_time":1436752880, "city":"成都市", "location":[104.086347, 30.655550000000002],
//        "address":"四川省成都市锦江区红星路73号", "title":"红星路", "coord_type":3, "type":0, "distance":806, "weight":0},
        public static class Content{
        public String tags;
        public int uid;
        public String province;
        public int geotable_id;
        public int modify_time;
        public String district;
        public int create_time;
        public  String city;
        public String location;
        public String address;
        public String title;
        public int coord_type;
        public int type;
        public int distance;
        public int weight;

    }
}
