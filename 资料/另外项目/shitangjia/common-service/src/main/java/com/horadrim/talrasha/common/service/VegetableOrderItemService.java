package com.horadrim.talrasha.common.service;


import com.horadrim.talrasha.common.model.OrderItem;
import com.horadrim.talrasha.common.model.Vegetable;
import com.horadrim.talrasha.common.model.VegetableOrderItem;
import com.horadrim.talrasha.common.service.dto.OrderItemPojo;

import java.util.List;

/**
 * Created by Administrator on 2015/6/1.
 */
public interface VegetableOrderItemService extends GenericService<VegetableOrderItem,Integer> {
    public List<OrderItemPojo> getOrderItemsByOrderId(Integer orderId);
    List<VegetableOrderItem> queryPage(int currentPage, int size, int orderId);
    List<VegetableOrderItem> queryVegPage(int currentPage,int size ,int vegeId);
}
