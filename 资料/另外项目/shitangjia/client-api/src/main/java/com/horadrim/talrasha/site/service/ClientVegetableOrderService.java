package com.horadrim.talrasha.site.service;



import com.horadrim.talrasha.common.model.ConsumeHistory;
import com.horadrim.talrasha.common.model.User;
import com.horadrim.talrasha.common.model.VegetableOrder;

import com.horadrim.talrasha.common.service.VegetableOrderService;
import com.horadrim.talrasha.site.controller.request.CartItem;
import com.horadrim.talrasha.site.service.dto.SaveOrderDto;


import java.util.Map;

/**
 * Created by Administrator on 2015/6/3.
 */
public interface ClientVegetableOrderService extends VegetableOrderService {
   //添加订单，创建订单使用
    SaveOrderDto addVegetableOrder(Map<Integer, CartItem> param, User user,String note);

    void changeOrderStatus(Integer orderId,Integer status,Integer payStatus);

    int payOrder(int userId,ConsumeHistory.AccountType accountType, int[] orderArr,String payPwd) throws Exception;

    /**
     *  用户确认订单
     *  解除冻结金额
     *  执行扣费
     *  记录
     */
    int confirmOrder(int userId,int orderId,String payPwd);
}
