package cn.scud.main.order.service.impl;

import cn.scud.main.order.dao.OrderDao;
import cn.scud.main.order.model.Order;
import cn.scud.main.order.service.OrderService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Administrator on 2015/6/25.
 */
@Service("orderService")
public class OrderServiceImpl implements OrderService {

    @Resource
    private OrderDao orderDao;

    @Override
    public void saveOrder(Order order) {
        orderDao.saveOrder(order);
    }

    @Override
    public List<Order> listOrdersByToken(String userToken) {
        List<Order> orders = orderDao.listOrdersByToken(userToken);
        return orders;
    }

    @Override
    public Order getOrderByToken(String orderToken) {
        Order order = orderDao.getOrderByToken(orderToken);
        return order;
    }

    @Override
    public void setOrderComplete(String orderToken) {
        orderDao.setOrderComplete(orderToken);
    }
}
