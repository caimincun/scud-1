package cn.scud.main.order.service;

import cn.scud.main.order.model.Order;

import java.util.List;

/**
 * Created by Administrator on 2015/6/25.
 */
public interface OrderService {

    /**
     * 添加订单
     * @param order
     */
    void saveOrder(Order order);

    /**
     * 根据用户token获取他发布的订单
     * @param userToken
     * @return
     */
    List<Order> listOrdersByToken(String userToken);

    /**
     * 根据orderToken 获取订单信息
     * @param orderToken
     * @return
     */
    Order getOrderByToken(String orderToken);

    /**
     * 设置 order 标记 完成
     * @param orderToken
     */
    void setOrderComplete(String orderToken);
}
