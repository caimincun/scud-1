package com.horadrim.talrasha.common.service;


import com.horadrim.talrasha.common.model.Order;
import com.horadrim.talrasha.common.model.User;
import com.horadrim.talrasha.common.service.dto.OrderPojo;
import com.horadrim.talrasha.common.service.dto.QueryOrdersParam;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2015/6/1.
 */
public interface OrderService extends GenericService<Order,Integer> {
    //根据用户id获取用户所有订单
    public List<Order> getOrdersByUserId(Integer userId);

    //根据用户ID,订单状态查询订单列表
    public List<Order> queryOrders(Integer userId,Integer orderStatus);

    //根据封装类查询订单列表
    public List<Order> queryOrders(QueryOrdersParam param);

    public List<OrderPojo> listOrders(QueryOrdersParam param);

    /**
     * 通过订单号取得订单
     * @param orderNum 订单号
     */
    Order getOrderByOrderNum(String orderNum);

    /**
     * 订单的分页查询
     * @param currentPage
     * @param size
     * @return
     */
    List<Order> queryOrderPage(int id,Integer sta,Integer pay,int currentPage , int size,String order_date);

    /**
     * size  显示行数
     * currentPage 当前页
     * sort 排序方式
     */

    List<Order> queryOrders(int userId,int currentPage,int size,int sort);//根据id查询所有的订单且分页
}
