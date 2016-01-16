package com.horadrim.talrasha.common.service;


import com.horadrim.talrasha.common.model.VegetableOrder;
import com.horadrim.talrasha.common.service.dto.OrderPojo;
import com.horadrim.talrasha.common.service.dto.QueryOrdersParam;

import java.util.List;

/**
 * Created by Administrator on 2015/6/1.
 */
public interface VegetableOrderService extends GenericService<VegetableOrder,Integer> {
    //根据用户id获取用户所有订单
    public List<VegetableOrder> getOrdersByUserId(Integer userId);

    //根据用户ID,订单状态查询订单列表
    public List<VegetableOrder> queryOrders(Integer userId, Integer orderStatus);

    //根据封装类查询订单列表
    public List<VegetableOrder> queryOrders(QueryOrdersParam param);

    public List<OrderPojo> listOrders(QueryOrdersParam param);

    /**
     * 通过订单号取得订单
     * @param orderNum 订单号
     */
    VegetableOrder getByOrderNum(String orderNum);

    /**
     * 订单的分页查询
     * @param currentPage
     * @param size
     * @return
     */
    List<VegetableOrder> queryOrderPage(int id,Integer sta,Integer pay,int currentPage, int size,String queryOrderPage);
}
