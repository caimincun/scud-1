package com.horadrim.talrasha.common.util;

import com.baidubce.auth.DefaultBceCredentials;
import com.baidubce.services.sms.SmsClient;
import com.baidubce.services.sms.SmsClientConfiguration;
import com.baidubce.services.sms.model.SendMessageRequest;
import com.baidubce.services.sms.model.SendMessageResponse;
import com.google.gson.Gson;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2015/6/12.
 */
public class BaeSmsUtil {
    private static final String ACCESS_KEY_ID="24437049526242ff90a4ed5ceca240bc";
    private static final String SECRET_ACCESS_KEY="1daf7ac994824ae194fb622e43c77e9f";
    private static final String ENDPOINT ="http://sms.bj.baidubce.com";
    private static final SmsClient client;

    //短信模板
    public static final String CHECK_CODE_01 = "smsTpl:e7476122a1c24e37b3b0de19d04ae900";
    public static final String CHECK_CODE_02 = "smsTpl:e7476122a1c24e37b3b0de19d04ae901";
    public static final String CHECK_CODE_03 = "smsTpl:e7476122a1c24e37b3b0de19d04ae902";
    public static final String CHECK_CODE_04 = "smsTpl:e7476122a1c24e37b3b0de19d04ae903";
    public static final String YOUHUI_QUAN_01 = "smsTpl:e7476122a1c24e37b3b0de19d04ae906";


    static {
        SmsClientConfiguration config=new SmsClientConfiguration();
        config.setCredentials(new DefaultBceCredentials(ACCESS_KEY_ID,SECRET_ACCESS_KEY));
        config.setEndpoint(ENDPOINT);
        client=new SmsClient(config);
    }

    public static boolean send(String templateId,Map<String,String> content,String... phone){
        SendMessageRequest request =new SendMessageRequest();// 构建SendMessageRequest对象

       request.setTemplateId(templateId);// 设置短信模板ID
//        request.setContentVar("{\"code\":\"1234\"}");// 设置短信模板内容变量的替换值
        request.setContentVar(new Gson().toJson(content));// 设置短信模板内容变量的替换值
        request.setReceiver(Arrays.asList(phone));// 设置接收人列表
        try {
            client.sendMessage(request);// 请求Server
//            SendMessageResponse sendResponse = client.sendMessage(request);// 请求Server
        }catch (Exception e){
            System.out.println("短信发送失败"+e);
            return false;
        }
        return true;
    }

    public static void main(String[] args) {
        Map<String,String> map = new HashMap<>();
        map.put("code","1234");
        send(BaeSmsUtil.YOUHUI_QUAN_01,map,"18328399307");
    }
}
