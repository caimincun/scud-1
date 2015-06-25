package cn.scud.utils;

import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2015/2/5.
 */
public class SplitDateUtil {
    /**
     * 根据传入的日期,取得年月日小时的键值对
     * @param date 年月日
     * @return 键值对 year:2015 month:12
     */
    public static Map<String,Integer> getYMDH(Date date){
        SimpleDateFormat year = new SimpleDateFormat("yyyy");
        SimpleDateFormat month = new SimpleDateFormat("MM");
        SimpleDateFormat day = new SimpleDateFormat("dd");
        SimpleDateFormat hour = new SimpleDateFormat("HH");
        Map<String,Integer> result = new HashMap<String,Integer>();
        result.put("hour",Integer.parseInt(hour.format(date)));
        result.put("month",Integer.parseInt(month.format(date)));
        result.put("day",Integer.parseInt(day.format(date)));
        result.put("year",Integer.parseInt(year.format(date)));
        return result;
    }

    @SuppressWarnings("unchecked")
    public static void setYMDH(Object obj,Date date){
        Class clazz = obj.getClass();
        try {
            Method setCYear = clazz.getDeclaredMethod("setC_year", int.class);
            Method setCMonth = clazz.getDeclaredMethod("setC_month", int.class);
            Method setCDay = clazz.getDeclaredMethod("setC_day", int.class);
            Method setCHour = clazz.getDeclaredMethod("setC_hour", int.class);

            Map<String,Integer> result = getYMDH(date);
            if (setCYear!=null)
                setCYear.invoke(obj, result.get("year"));
            if (setCMonth!=null)
                setCMonth.invoke(obj,result.get("month"));
            if (setCDay!=null)
                 setCDay.invoke(obj,result.get("day"));
            if (setCHour!=null)
                setCHour.invoke(obj,result.get("hour"));

        }catch (Exception e){
            e.printStackTrace();
        }
    }
    @SuppressWarnings("unchecked")
    public static void setYMD(Object obj,Date date){
        Class clazz = obj.getClass();
        try {
            Method setCYear = clazz.getDeclaredMethod("setC_year", int.class);
            Method setCMonth = clazz.getDeclaredMethod("setC_month", int.class);
            Method setCDay = clazz.getDeclaredMethod("setC_day", int.class);

            Map<String,Integer> result = getYMDH(date);
            if (setCYear!=null)
                setCYear.invoke(obj, result.get("year"));
            if (setCMonth!=null)
                setCMonth.invoke(obj,result.get("month"));
            if (setCDay!=null)
                 setCDay.invoke(obj,result.get("day"));

        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
