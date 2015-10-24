package com.horadrim.talrasha.site.task;

import com.horadrim.talrasha.site.service.ClientVegetableOrderService;
import com.horadrim.talrasha.site.wechat.WeChat;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Administrator on 2015/7/22.
 */
public class CheckOrderStatus {

    @Resource(name="clientVegetableOrderService")
    private ClientVegetableOrderService clientVegetableOrderService;
    public void check(){
        Date dNow = new Date();   //当前时间
        Calendar calendar = Calendar.getInstance(); //得到日历
        calendar.setTime(dNow);//把当前时间赋给日历
        calendar.add(Calendar.DAY_OF_MONTH, -1);  //设置为前一天
        Date dBefore = calendar.getTime();   //得到前一天的时间
        clientVegetableOrderService.executeUpdate("UPDATE VegetableOrder SET status =3 WHERE " +
                " status =0 AND orderDate <?",new Object[]{dBefore});
        System.out.println("执行扫描秒"+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(dBefore));
    }

}
