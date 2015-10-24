package com.horadrim.talrasha.common.service.impl;

import com.horadrim.talrasha.common.dao.GenericDao;
import com.horadrim.talrasha.common.dao.OrderDao;
import com.horadrim.talrasha.common.dao.VegetableOrderDao;
import com.horadrim.talrasha.common.model.Order;
import com.horadrim.talrasha.common.model.OrderItem;
import com.horadrim.talrasha.common.model.Product;
import com.horadrim.talrasha.common.model.VegetableOrder;
import com.horadrim.talrasha.common.service.OrderService;
import com.horadrim.talrasha.common.service.VegetableOrderService;
import com.horadrim.talrasha.common.service.dto.OrderPojo;
import com.horadrim.talrasha.common.service.dto.QueryOrdersParam;
import com.horadrim.talrasha.common.util.MapToPojoUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.*;

/**
 * Created by Administrator on 2015/6/1.
 */
@Service("vegetableOrderService")
public class VegetableOrderServiceImpl extends  GenericServiceImpl<VegetableOrder,Integer> implements VegetableOrderService {

    @Resource(name="vegetableOrderDao")
    protected VegetableOrderDao vegetableOrderDao;

    @Override
    protected GenericDao<VegetableOrder, Integer> getGenericDao() {
        return vegetableOrderDao;
    }

    @Override
    public List<VegetableOrder> getOrdersByUserId(Integer userId) {
//        String hql="select o from Order o,User u where o.user.id=u.id and u.id=?";
        String sql="select o.* from qc_order o,qc_user u where o.user_id=u.id and u.id=?";
       // List<Order> orders=orderDao.list(hql,new Object[]{userId});
        return vegetableOrderDao.listBySQL(sql,new Object[]{userId});
    }

    @Override
    public List<VegetableOrder> queryOrders(Integer userId, Integer orderStatus) {
       String sql="select o.* from qc_order o,qc_user u where o.user_id=u.id and o.status=? and u.id=?";
       return vegetableOrderDao.listBySQL(sql,new Object[]{orderStatus,userId});
    }

    @Override
    public List<VegetableOrder> queryOrders(QueryOrdersParam param) {
        String sql="select o.* from qc_vegetable_order o,qc_user u where o.user_id=u.id ";
        StringBuilder sb=new StringBuilder();
        List<Object> list=new ArrayList<>();
        if(null!=param.getUserId()){
            sb.append("and u.id=? ");
            list.add(param.getUserId());
        }
        if(null!=param.getStatus()){
            sb.append("and o.status=? ");
            list.add(param.getStatus());
        }
        if(null!=param.getIsPayed()){
            sb.append("and o.is_payed=? ");
            list.add(param.getIsPayed());
        }
        Object objects[] =new Object[list.size()];
        for(int i=0;i<list.size();i++){
            objects[i]=list.get(i);
        }
        sql=sql+sb.toString();
        sql=sql+"order by o.id desc";
        return vegetableOrderDao.listBySQL(sql,objects);
    }

    @Override
    public List<OrderPojo> listOrders(QueryOrdersParam param) {
        String sql="select o.id as orderId," +
                " o.order_num as orderNum," +
                "o.total_price as totalPrice," +
                "o.is_payed as isPayed," +
                "o.status as status," +
                "o.order_date as orderDate," +
                "o.deleted as deleted" +
                " from qc_vegetable_order o,qc_user u where o.user_id=u.id ";
        StringBuilder sb=new StringBuilder();
        List<Object> list=new ArrayList<Object>();
        if(null!=param.getUserId()){
            sb.append("and u.id=? ");
            list.add(param.getUserId());
        }
        if(null!=param.getStatus()){
            sb.append("and o.status=? ");
            list.add(param.getStatus());
        }
        if(null!=param.getIsPayed()){
            sb.append("and o.is_payed=? ");
            list.add(param.getIsPayed());
        }
        Object objects[] =new Object[list.size()];

        for(int i=0;i<list.size();i++){
            objects[i]=list.get(i);
        }
        sql=sql+sb.toString();
        sql=sql+"order by o.id desc";

        List<Map<String,Object>> l=vegetableOrderDao.listFieldBySQL(sql,objects);

        List<OrderPojo> results=new ArrayList<>();
        for(int i=0;i<l.size();i++){
            OrderPojo oPojo=new OrderPojo();
            MapToPojoUtil.mapToPojo(l.get(i),oPojo);
            results.add(oPojo);
        }
        return results;
    }

    @Override
    public VegetableOrder getByOrderNum(String orderNum) {
        final String hql ="FROM VegetableOrder WHERE orderNum=?";
        return vegetableOrderDao.query(hql,new Object[]{orderNum});
    }


    /**
     * 订单的分页查询
     * @param currentPage
     * @param size
     * @return
     */
    @Override
    public List<VegetableOrder> queryOrderPage(int id,Integer sta,Integer pay,int currentPage, int size,String order_date) {
        StringBuffer hql = new StringBuffer("from VegetableOrder WHERE canteenId=");
        hql.append(id);
        boolean flag = false;
        if(null != sta && sta != 5){
            hql.append(" and status ="+sta);
        }
        if( null != pay && pay!= 5){
            hql.append(" and isPayed ="+pay);
        }

        List<VegetableOrder> orders = null;
        if(null != order_date && !"".equals(order_date)){
            hql.append(" and orderDate like concat(?,'%')");
            flag = true;
        } else{
            flag = false;
        }
        hql.append(" order by orderDate desc");
        if(flag){
            orders = vegetableOrderDao.list(hql.toString(),currentPage*size,size,new Object[]{order_date});
        }else{
            orders = vegetableOrderDao.list(hql.toString(),currentPage*size,size,null);
        }

        return orders;
    }

//    /**
//     * 查询订单列表
//     * @param param
//     * @return
//     */
//    @Override
//    public List<Order> queryOrders(QueryOrdersParam param) {
//
//    }

    public void createOrder(Order order,Map<String,Object> map,Integer orderId){
        orderId=(Integer)map.get("order_id");
        order.setId(orderId);
        order.setOrderDate((Date)map.get("order_date"));
        Set<OrderItem> orderItems=new HashSet<OrderItem>();
        //addOrderItem(orderItems,map);
        OrderItem orderItem=new OrderItem();
        Product product=new Product();
        product.setName((String)map.get("product_name"));
        product.setPrice((BigDecimal)map.get("product_price"));
        orderItem.setProduct(product);
        orderItem.setCount((Integer)map.get("product_count"));
        orderItems.add(orderItem);
        order.setOrderItems(orderItems);
    }

    public void addOrderItem(Set<OrderItem> orderItems,Map<String,Object> map){
        OrderItem orderItem=new OrderItem();
        Product product=new Product();
        product.setName((String)map.get("product_name"));
        product.setPrice((BigDecimal)map.get("product_price"));
        orderItem.setProduct(product);
        orderItem.setCount((Integer)map.get("product_count"));
        orderItems.add(orderItem);
    }

}
