package cn.scud.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.util.Map;

/**
 * map转换为实体类
 * Created by Administrator on 2014/12/9.
 */
public class MapToPojoUtil {

    private static Logger logger = LoggerFactory.getLogger(MapToPojoUtil.class);
    /**
     *  map转换为实体类不包含忽略字段
     * @param map  键值对
     * @param obj  被转换的对象
     */
    public static void mapToPojo(Map<String , Object> map, Object obj) {
        Class clazz = obj.getClass();
        Field[] fields = clazz.getDeclaredFields();
        for (Field field :fields){
            String fieldName = field.getName();
            for (Map.Entry<String,Object> entry : map.entrySet()){
                if (entry.getValue()!=null&&fieldName.toLowerCase().equals(entry.getKey().toLowerCase())){
                    field.setAccessible(true);
                    try {
                        field.set(obj, entry.getValue());
                    } catch (IllegalAccessException e) {
                       logger.error("map转换为pojo失败"+e);
                    }
                    break;
                }
            }
        }
    }
    /**
     *  map转换为实体类包含忽略字段
     * @param map  键值对
     * @param obj  被转换的对象
     */
    public static void mapToPojo(Map<String , Object> map, Object obj,String... ignoreField)  {
        Class clazz = obj.getClass();
        Field[] fields = clazz.getDeclaredFields();
        for (Field field :fields){
            String fieldName = field.getName();
            boolean flag = false;
            for(String str : ignoreField){
                if (fieldName.equals(str)) {
                    flag = true;
                    break;
                }
            }
            if (flag)
                continue;
            for (Map.Entry<String,Object> entry : map.entrySet()){
                if (entry.getValue()!=null&&fieldName.toLowerCase().equals(entry.getKey().toLowerCase())){
                    field.setAccessible(true);
                    try {
                        field.set(obj, entry.getValue());
                    } catch (IllegalAccessException e) {
                        logger.error("map转换为pojo失败"+e);
                    }
                    break;
                }
            }
        }
    }

    /**
     *  map转换为实体类包含忽略字段
     * @param obj  被转换的对象
     */
    public static void pojoToMap(Map<String,Object> map, Object obj,String... ignoreField) {
        Class clazz = obj.getClass();
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            String fieldName = field.getName();
            boolean flag = false;
            for (String str : ignoreField) {
                if (fieldName.equals(str)) {
                    flag = true;
                    break;
                }
            }
            if (flag)
                continue;
            field.setAccessible(true);

            try {
                map.put(fieldName,field.get(obj));
            } catch (IllegalAccessException e) {
                logger.error("pojo转换为map失败" + e);
            }
        }
    }

}
