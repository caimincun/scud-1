package com.horadrim.talrasha.site.service.impl;

import com.horadrim.talrasha.common.CodeDefined;
import com.horadrim.talrasha.common.dao.ConsumeHistoryDao;
import com.horadrim.talrasha.common.dao.OrderItemDao;
import com.horadrim.talrasha.common.dao.ProductDao;
import com.horadrim.talrasha.common.dao.UserDao;
import com.horadrim.talrasha.common.model.*;
import com.horadrim.talrasha.common.service.OrderItemService;
import com.horadrim.talrasha.common.service.OrderService;
import com.horadrim.talrasha.common.service.ProductService;
import com.horadrim.talrasha.common.service.dto.QueryOrdersParam;
import com.horadrim.talrasha.common.service.impl.OrderServiceImpl;
import com.horadrim.talrasha.common.util.CheckCodeUtil;
import com.horadrim.talrasha.common.util.SplitDateUtil;
import com.horadrim.talrasha.common.util.StringUtils;
import com.horadrim.talrasha.site.controller.request.CartItem;
import com.horadrim.talrasha.site.service.ClientOrderService;
import com.horadrim.talrasha.site.utils.OrderNotifyCanteenUtil;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.*;

/**
 * Created by Administrator on 2015/6/3.
 */
@Service("clientOrderService")
public class ClientOrderServiceImpl extends OrderServiceImpl implements ClientOrderService {

    @Resource(name = "productDao")
    private ProductDao productDao;
    @Resource(name="orderItemDao")
    private OrderItemDao orderItemDao;
    @Resource(name = "userDao")
    private UserDao userDao;
    @Resource(name = "consumeHistoryDao")
    private ConsumeHistoryDao consumeHistoryDao;
    @Resource
    private OrderNotifyCanteenUtil orderNotifyCanteenUtil;
//    @Resource(name="orderService")
//    private OrderService orderService;
    @Override
    public Order addOrder(Map<Integer,CartItem> param,User user) {
        Product product;
        Order order=new Order();
        order.setDeleted(0);
        order.setIsPayed(0);
        order.setOrderDate(new Date());

        //TODO ordernum 需要重新生成策略
        Long l=new Date().getTime();
        String oNum=l.toString()+new CheckCodeUtil().generateRandomNumberCode();
        order.setOrderNum(oNum);
        order.setStatus(0);
        order.setTotalPrice(new BigDecimal(0));
        order.setUser(user);
        order.setCanteenId(user.getCanteenId());
        save(order);
        OrderItem orderItem;
        BigDecimal totalPrice=null;
        CartItem c;
        for(Integer key:param.keySet()){
            c=param.get(key);
            product=productDao.get(c.getId());
            if(totalPrice==null){
                totalPrice=product.getPrice().multiply(new BigDecimal(c.getCount()));
            }else{
                totalPrice=totalPrice.add((product.getPrice()).multiply(new BigDecimal(c.getCount())));
            }
            orderItem=new OrderItem();
//            orderItem.setOrder(order);
            orderItem.setProduct(product);
            orderItem.setCount(c.getCount());
            orderItemDao.save(orderItem);
        }
        order.setTotalPrice(totalPrice);
        this.update(order);
        return order;
    }

    @Override
    public void changeOrderStatus(Integer orderId, Integer status,Integer payStatus) {
        Order order=orderDao.get(orderId);
        if(status!=null){
            order.setStatus(status);
        }
        if(payStatus!=null){
            order.setIsPayed(payStatus);
        }
        orderDao.saveOrUpdate(order);
    }

    @Override
    public int payOrder(int userId,ConsumeHistory.AccountType accountType, int[] orderArr,String payPwd) throws Exception{
        User user = userDao.get(userId);
        if (user == null)
            return CodeDefined.COMMON_NOT_AUTH;
        BigDecimal totalPay = new BigDecimal(0);
        List<ConsumeHistory> histories = new ArrayList<>();
        List<Order> orders = new ArrayList<>();
        for(int orderId : orderArr){
            Order order = orderDao.get(orderId);
            if (order==null)
                return CodeDefined.ORDER_INFO_NULL;
            //计算总费用
            totalPay=totalPay.add(order.getTotalPrice());
            //临时保存记录
            ConsumeHistory history = new ConsumeHistory();
            history.setUserId(user.getId());
            history.setBalance(order.getTotalPrice());
            history.setTargetId(orderId);
            history.setAccountType(accountType);
            history.setConsumeType(ConsumeHistory.ConsumeType.DIANCAN);
            Date date = new Date();
            history.setConsumeTime(date);
            SplitDateUtil.setYMD(history, date);
            histories.add(history);
            orders.add(order);
        }

        if (accountType!= ConsumeHistory.AccountType.XIANXIA&&totalPay.compareTo(new BigDecimal(user.getSafeAmount()))==1){
            //大于50 需要支付密码
            String payPwdDb = user.getPayPassword();
            if(StringUtils.isBlank(payPwdDb))
                return CodeDefined.ACCOUNT_USER_PAYPWD_ISNOTSET;
            if(StringUtils.isBlank(payPwd))
                return CodeDefined.ACCOUNT_USER_PAYPWD_ISNULL;
            if (!payPwdDb.equals(DigestUtils.md5Hex(payPwd)))
                return CodeDefined.ACCOUNT_USER_PAYPWD_ERROR;
        }
        //修改费用
        if (accountType== ConsumeHistory.AccountType.PINGTAI){
            if(user.getQingcaiBalance().compareTo(totalPay)==-1)
                 return CodeDefined.ACCOUNT_USER_INSUFFICIENT_BALANCE; //余额不足
            user.setQingcaiBalance(user.getQingcaiBalance().subtract(totalPay));
            //付款
            userDao.update(user);

        }else if(accountType== ConsumeHistory.AccountType.SHITANG){
            if(user.getQingcaiBalance().compareTo(totalPay)==-1)
                return CodeDefined.ACCOUNT_USER_INSUFFICIENT_BALANCE; //余额不足
            user.setCanteenBalance(user.getCanteenBalance().subtract(totalPay));
            //付款
            userDao.update(user);

        }else if (accountType==ConsumeHistory.AccountType.XIANXIA){
            //线下付款，不做操作
        }else {
            return CodeDefined.ACCOUNT_USER_PAYTYPE_WRONG;
        }

        //保存记录
        Order order ;
        for (int i = 0 ;i<histories.size();i++) {
            consumeHistoryDao.save(histories.get(i));
            order = orders.get(i);
            order.setIsPayed(1);
            order.setPayType(accountType.ordinal());
            orderDao.update(order);

            //通知食堂
            orderNotifyCanteenUtil.asynNotify(user.getCanteenId(),order.getId(),CanteenMessage.MessageType.DIANCAN_ORDER);
        }


        return 0;
    }

    @Override
    public List<Order> saveOrder(Map<Integer, CartItem> param, User user) {
        //创建临时订单item，保存产品和数量
        List<OrderItem> tempItmes = new ArrayList<>();
        for (Map.Entry<Integer, CartItem> entry : param.entrySet()){
            int productId = entry.getKey();
            CartItem item = entry.getValue();
            Product product = productDao.get(productId);
            OrderItem orderItem = new OrderItem();
            orderItem.setCount(item.getCount());
            orderItem.setProduct(product);
            tempItmes.add(orderItem);
        }
        //遍历临时item并创建order
        List<Order> orders = new LinkedList<>();
        for (int i=0 ; i<tempItmes.size();i++){
            OrderItem orderItem = tempItmes.get(i);
            //设置order
            Order order = new Order();
            Date date = new Date();
            order.setOrderNum(date.getTime()+""+new CheckCodeUtil().generateRandomNumberCode());
            order.setOrderDate(date);
            order.setUser(user);
            order.setCanteenId(user.getCanteenId()
            );
//            orderItem.setOrder(order);
            order.getOrderItems().add(orderItem);
            BigDecimal totalPay = orderItem.getProduct().getPrice().multiply(new BigDecimal(orderItem.getCount()));
            long time = orderItem.getProduct().getCreatedTime().getTime();
            //找出临时item中时间节点相同的，放在一起
            for (int j=i+1;j<tempItmes.size();j++){
                OrderItem orderItemLast = tempItmes.get(j);
                if (time==orderItemLast.getProduct().getCreatedTime().getTime()
                        &&orderItem.getProduct().getTimeNode()==orderItemLast.getProduct().getTimeNode()){
                    order.getOrderItems().add(orderItemLast);
                    totalPay=totalPay.add(orderItemLast.getProduct().getPrice().multiply(new BigDecimal(orderItem.getCount())));
//                    orderItemLast.setOrder(order);
                    order.setTakeDate(orderItemLast.getProduct().getCreatedTime());
                    order.setTakeNode(orderItemLast.getProduct().getTimeNode());
                    tempItmes.remove(orderItemLast);
                    j--;
                }
            }
            order.setTotalPrice(totalPay);
            order.setPayType(-1);
            orderDao.save(order);
            orders.add(order);
        }
        return orders;
    }
//    @Override
//    public List<Order> getOrdersByUserId(Integer userId) {
//        return orderService.getOrdersByUserId(userId);
//    }
//
//    @Override
//    public List<Order> queryOrders(Integer userId, Integer orderStatus) {
//        return orderService.queryOrders(userId,orderStatus);
//    }
//    @Override
//    public List<Order> queryOrders(QueryOrdersParam param) {
//        return orderService.queryOrders(param);
//    }
}
