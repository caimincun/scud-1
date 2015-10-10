package com.horadrim.talrasha.common.util;

import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by Administrator on 2015/2/5.
 */
public class SplitDateUtil {
    private static final int FIRST_DAY = Calendar.MONDAY;
    /**
     * 根据传入的日期,取得年月日小时的键值对
     * @param date 年月日
     * @return 键值对 year:2015 month:12
     */
    public static Map<String,Integer> getYMDH(Date date){
        Map<String,Integer> result = new HashMap<>();
        result.put("year",Integer.parseInt(new SimpleDateFormat("yyyy").format(date)));
        result.put("month",Integer.parseInt(new SimpleDateFormat("MM").format(date)));
        result.put("day",Integer.parseInt(new SimpleDateFormat("dd").format(date)));
        result.put("hour",Integer.parseInt( new SimpleDateFormat("HH").format(date)));
        return result;
    }
    public static Map<String,Integer> getYMD(Date date){
        Map<String,Integer> result = new HashMap<>();
        result.put("year",Integer.parseInt(new SimpleDateFormat("yyyy").format(date)));
        result.put("month",Integer.parseInt(new SimpleDateFormat("MM").format(date)));
        result.put("day",Integer.parseInt(new SimpleDateFormat("dd").format(date)));
        return result;
    }

    /**
     * 设置一个对象年月日小时
     * @param obj
     * @param date
     */
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

    /**
     * 设置一个对象年月日
     * @param obj
     * @param date
     */
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

    public static Map<String,String> getCurrentWeekDate(){
        Calendar calendar = Calendar.getInstance();
        //设置当前时间为第一天
        while (calendar.get(Calendar.DAY_OF_WEEK) != FIRST_DAY) {
            calendar.add(Calendar.DATE, -1);
        }
        SimpleDateFormat ymd = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat week = new SimpleDateFormat("EE");
        Map<String,String> date = new LinkedHashMap<>();
        for (int i = 0; i < 5; i++) {
            date.put(intWeekToString(i),ymd.format(calendar.getTime()));
            calendar.add(Calendar.DATE, 1);
        }
        return date;
    }
    public static String intWeekToString(int i){
        String str = "";
        switch (i){
            case 0:
                str ="星期一";
                break;
            case 1:
                str ="星期二";
                break;
            case 2:
                str ="星期三";
                break;
            case 3:
                str ="星期四";
                break;
            case 4:
                str ="星期五";
                break;
        }
        return str;
    }

}

