package cn.scud.main.order.dao;

import cn.scud.main.order.service.OrderService;
import org.springframework.stereotype.Controller;

/**
 * Created by Administrator on 2015/6/25.
 */
@Controller("/order")
public interface OrderDao {

    /**
     * 添加订单
     * @return
     */
     OrderService addOrder();
}
