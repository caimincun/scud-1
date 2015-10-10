package com.horadrim.talrasha.site.utils;

import com.horadrim.talrasha.common.model.CanteenMessage;
import com.horadrim.talrasha.common.service.CanteenMessageService;
import com.horadrim.talrasha.site.wechat.util.HttpKit;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2015/7/10.
 */
@Component("orderNotifyCanteenUtil")
public class OrderNotifyCanteenUtil {

//    private static final String notifyUrl="http://192.168.1.130:8080/message/notify";
    private static final String notifyUrl="http://rest.yunba.io:8080";
    @Resource
    private CanteenMessageService canteenMessageService;

    public  void notify(int canteenId,int targetId,CanteenMessage.MessageType messageType){

        CanteenMessage message = new CanteenMessage();
        message.setCanteenId(canteenId);
        message.setMessageType(messageType.ordinal());
        message.setTargetId(targetId);
        message.setCreateDate(new Date());
        canteenMessageService.save(message);
        Map<String,String> map = new HashMap<>();
        map.put("method","publish_to_alias");
        map.put("appkey","55c198e1504438f24763bcc8");
        map.put("seckey","sec-qDtydCzOwef7EtMUgm8xWQTEHpU3QrH0nLKUDClpbm0HWJjV");
        map.put("alias",""+canteenId);
        map.put("msg",""+messageType.ordinal());
//        map.put("canteenId",canteenId+"");
//        map.put("msgType",messageType.ordinal()+"");
        try {
            HttpKit.get(notifyUrl,map);
        }catch (Exception e){
            e.printStackTrace();
        }


    }

    public  void asynNotify(final int canteenId,final int targetId,final CanteenMessage.MessageType messageType){
         Thread t = new Thread(new Runnable(){
            public void run(){
                CanteenMessage message = new CanteenMessage();
                message.setCanteenId(canteenId);
                message.setMessageType(messageType.ordinal());
                message.setTargetId(targetId);
                message.setCreateDate(new Date());
                canteenMessageService.save(message);
                Map<String,String> map = new HashMap<>();
                map.put("method","publish_to_alias");
                map.put("appkey","55c198e1504438f24763bcc8");
                map.put("seckey","sec-qDtydCzOwef7EtMUgm8xWQTEHpU3QrH0nLKUDClpbm0HWJjV");
                map.put("alias",""+canteenId);
                map.put("msg",""+messageType.ordinal());
//        map.put("canteenId",canteenId+"");
//        map.put("msgType",messageType.ordinal()+"");
                try {
                    HttpKit.get(notifyUrl,map);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }

         });
        t.start();
    }
}
