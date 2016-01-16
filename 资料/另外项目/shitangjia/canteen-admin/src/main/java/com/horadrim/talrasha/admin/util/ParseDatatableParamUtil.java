package com.horadrim.talrasha.admin.util;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2015/7/29.
 */
public class ParseDatatableParamUtil {

    public static DatatableParamPojo parse(String aoData){
        ObjectMapper mapper = new ObjectMapper();
        List<Map<String,Object>> mapList =null;
        try {
            mapList = mapper.readValue(aoData,mapper.getTypeFactory().constructParametricType(List.class, Map.class));
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (mapList==null){
            return null;
        }
        DatatableParamPojo paramPojo = new DatatableParamPojo();
        for (Map<String,Object> obj : mapList){
            if (obj.get("name").equals("sEcho"))
                paramPojo.setsEcho(obj.get("value").toString());
            if (obj.get("name").equals("iDisplayStart"))
                paramPojo.setiDisplayStart((int) obj.get("value"));
            if (obj.get("name").equals("iDisplayLength"))
                paramPojo.setiDisplayLength((int) obj.get("value"));
        }
        return paramPojo;
    }
}
