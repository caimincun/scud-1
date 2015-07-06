import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2015/7/6.
 */
public class JinWeiTest {



    private static final double PI = 3.14159265;
    private static final double EARTH_RADIUS = 63781.37;
    private static final double RAD = Math.PI / 180.0;

    public static double getDistance(double lng1, double lat1, double lng2, double lat2) {
        double radLat1 = lat1 * RAD;
        double radLat2 = lat2 * RAD;
        double a = radLat1 - radLat2;
        double b = (lng1 - lng2) * RAD;
        double s = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(a / 2), 2) +
                Math.cos(radLat1) * Math.cos(radLat2) * Math.pow(Math.sin(b / 2), 2)));
        s = s * EARTH_RADIUS;
        s = Math.round(s * 10000) / 10000;
        return s;
    }
    public static void main(String[] args) {
        //济南国际会展中心经纬度：117.11811  36.68484
        //趵突泉：117.00999000000002  36.66123
        // 天府广场经纬度
        double lat1 = 104.06326999999999; //精度
        double lng1 = 30.66074; // 维度

        //环球中心
        double lat2 = 108.91219747151378;
        double lng2 = 33.06609988100316;
//
        System.out.println(getDistance(lat1,lng1,lat2,lng2));


    }
}
