package cn.scud.main.order.service.impl;

import cn.scud.main.order.dao.OrderDao;
import cn.scud.main.order.model.Order;
import cn.scud.main.order.service.OrderService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by Administrator on 2015/6/25.
 */
@Service("orderService")
public class OrderServiceImpl implements OrderService {

    @Resource
    private OrderDao orderDao;
    @Override
    public void saveOrder(Order order) {
//        orderDao.saveOrder(order);
    }
}
