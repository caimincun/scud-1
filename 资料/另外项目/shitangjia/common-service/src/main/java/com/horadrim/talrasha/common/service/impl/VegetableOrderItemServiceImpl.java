package com.horadrim.talrasha.common.service.impl;

import com.horadrim.talrasha.common.dao.GenericDao;
import com.horadrim.talrasha.common.dao.OrderItemDao;
import com.horadrim.talrasha.common.dao.VegetableOrderItemDao;
import com.horadrim.talrasha.common.model.OrderItem;
import com.horadrim.talrasha.common.model.VegetableOrderItem;
import com.horadrim.talrasha.common.service.OrderItemService;
import com.horadrim.talrasha.common.service.VegetableOrderItemService;
import com.horadrim.talrasha.common.service.dto.OrderItemPojo;
import com.horadrim.talrasha.common.util.MapToPojoUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2015/6/1.
 */
@Service("vegetableOrderItemService")
public class VegetableOrderItemServiceImpl extends GenericServiceImpl<VegetableOrderItem,Integer> implements VegetableOrderItemService {
    @Resource(name="vegetableOrderItemDao")
    private VegetableOrderItemDao orderItemDao;
    @Override
    protected GenericDao<VegetableOrderItem, Integer> getGenericDao() {
        return orderItemDao;
    }
    @Override
    public List<OrderItemPojo> getOrderItemsByOrderId(Integer orderId) {
        String sql="select v.vegetable_name as productName,v.unit_price as price,i.count as count from qc_vegetable_order o,qc_vegetable_order_item i,qc_vegetable v " +
                "where v.id=i.vegetable_id " +
                "and i.vegetable_order_id=o.id " +
                "and o.id=?";
        List<Map<String,Object>> list=orderItemDao.listFieldBySQL(sql,new Object[]{orderId});
        List<OrderItemPojo>  items=new ArrayList<>();

        for(Map<String,Object> map:list){
            OrderItemPojo orderItemPojo=new OrderItemPojo();
            MapToPojoUtil.mapToPojo(map,orderItemPojo);
            items.add(orderItemPojo);
        }
        return items;
    }

    @Override
    public List<VegetableOrderItem> queryPage(int currentPage, int size,int orderId) {
        String sql = "select * from qc_order_item  where order_id = ?";
        Object[] params = {orderId};
        List<VegetableOrderItem> orderItems = orderItemDao.listBySQL(sql,currentPage,size,params);
        return orderItems;
    }

    @Override
    public List<VegetableOrderItem> queryVegPage(int currentPage, int size, int vegeId) {
//        String sql = "select * from qc_vegetable where id in (select vegetable_id from qc_vegetable_order_item where vegetable_order_id = ?)";
        String sql = "select * from qc_vegetable_order_item where vegetable_order_id = ?";
        Object[] params = {vegeId};
        List<VegetableOrderItem> vegetableOrderItems = orderItemDao.listBySQL(sql,currentPage,size,params);
        return vegetableOrderItems;
    }

}
