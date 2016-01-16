package com.horadrim.talrasha.site.controller.result;

import com.horadrim.talrasha.common.model.Order;
import com.horadrim.talrasha.common.response.json.SuccessJsonRes;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by Administrator on 2015/6/4.
 * 订单查询返回结果
 */
public class QueryOrdersRes extends SuccessJsonRes {
    private List<Order> orders;

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }
}
