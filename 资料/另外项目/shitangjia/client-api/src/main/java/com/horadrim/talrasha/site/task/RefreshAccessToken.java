package com.horadrim.talrasha.site.task;

import com.horadrim.talrasha.site.wechat.WeChat;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Administrator on 2015/7/22.
 */
public class RefreshAccessToken {

    public void refressh(){
        System.out.println("任务调度");
        try {
            //String token = WeChat.getAccessToken();
            //System.out.println("执行了刷新token ===  "+token+"  时间 "+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
