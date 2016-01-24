package com.horadrim.talrasha.common.service.impl;

import com.horadrim.talrasha.common.dao.GenericDao;
import com.horadrim.talrasha.common.dao.OrderItemDao;
import com.horadrim.talrasha.common.model.OrderItem;
import com.horadrim.talrasha.common.service.OrderItemService;
import com.horadrim.talrasha.common.service.dto.OrderItemPojo;
import com.horadrim.talrasha.common.util.MapToPojoUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipEntry;

/**
 * Created by Administrator on 2015/6/1.
 */
@Service("orderItemService")
public class OrderItemServiceImpl extends GenericServiceImpl<OrderItem,Integer> implements OrderItemService {
    @Resource(name="orderItemDao")
    private OrderItemDao orderItemDao;
    @Override
    protected GenericDao<OrderItem, Integer> getGenericDao() {
        return orderItemDao;
    }

    @Override
    public List<OrderItemPojo> getOrderItemsByOrderId(Integer orderId) {

        String sql="select p.name as productName,p.price as price,i.count as count from qc_order o,qc_order_item i,qc_product p " +
                "where p.id=i.product_id " +
                "and i.order_id=o.id " +
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
    public List<OrderItem> queryPage(int currentPage, int size,int orderId) {
        String sql = "select * from qc_order_item  where order_id = ?";
        Object[] params = {orderId};
        List<OrderItem> orderItems = orderItemDao.listBySQL(sql,currentPage,size,params);
        return orderItems;
    }
}
