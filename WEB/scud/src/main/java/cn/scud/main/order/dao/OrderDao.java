package cn.scud.main.order.dao;

import cn.scud.main.order.model.Order;

import java.util.List;

/**
 * Created by Administrator on 2015/6/25.
 */
public interface OrderDao {

    /**
     * 添加订单
     * @return
     */
     void saveOrder(Order order);

    /**
     * 根据token ，获取 orders
     */
    List<Order> listOrdersByToken(String userToken);

    /**
     * 根据orderToken 获取 Order
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
