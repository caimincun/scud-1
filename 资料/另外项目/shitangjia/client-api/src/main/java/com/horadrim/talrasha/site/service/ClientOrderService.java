package com.horadrim.talrasha.site.service;

import com.horadrim.talrasha.common.model.ConsumeHistory;
import com.horadrim.talrasha.common.model.Order;
import com.horadrim.talrasha.common.model.User;
import com.horadrim.talrasha.common.service.OrderService;
import com.horadrim.talrasha.common.service.dto.QueryOrdersParam;
import com.horadrim.talrasha.site.controller.request.CartItem;


import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by Administrator on 2015/6/3.
 */
public interface ClientOrderService extends OrderService {
   //添加订单，创建订单使用
    public Order addOrder(Map<Integer,CartItem> param,User user);

    List<Order> saveOrder(Map<Integer,CartItem> param,User user);

//   //根据用户id查询用户所有订单列表
//    public List<Order> getOrdersByUserId(Integer userId);
//    //根据用户id ，订单状态查询订单列表
//    public List<Order> queryOrders(Integer userId,Integer orderStatus);
//    //根据param参数查询订单列表
//    public List<Order> queryOrders(QueryOrdersParam param);

     void changeOrderStatus(Integer orderId,Integer status,Integer payStatus);



    int payOrder(int userId,ConsumeHistory.AccountType accountType, int[] orderArr,String payPwd) throws Exception;
}
