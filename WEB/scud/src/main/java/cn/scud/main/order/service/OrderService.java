package cn.scud.main.order.service;

import cn.scud.main.order.model.Order;

/**
 * Created by Administrator on 2015/6/25.
 */
public interface OrderService {

    /**
     * 添加订单
     * @param order
     */
    void saveOrder(Order order);
}
