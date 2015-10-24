package com.horadrim.talrasha.common.service;


import com.horadrim.talrasha.common.model.OrderItem;
import com.horadrim.talrasha.common.service.dto.OrderItemPojo;

import java.util.List;

/**
 * Created by Administrator on 2015/6/1.
 */
public interface OrderItemService extends GenericService<OrderItem,Integer> {
    public List<OrderItemPojo> getOrderItemsByOrderId(Integer orderId);

    List<OrderItem> queryPage(int currentPage,int size,int orderId);
}
