package com.horadrim.talrasha.site.service.impl;


import com.horadrim.talrasha.common.CodeDefined;
import com.horadrim.talrasha.common.dao.ConsumeHistoryDao;
import com.horadrim.talrasha.common.dao.ProductDao;
import com.horadrim.talrasha.common.dao.UserDao;
import com.horadrim.talrasha.common.dao.VegetableDao;
import com.horadrim.talrasha.common.model.*;
import com.horadrim.talrasha.common.service.OrderItemService;
import com.horadrim.talrasha.common.service.VegetableOrderItemService;
import com.horadrim.talrasha.common.service.VegetableService;
import com.horadrim.talrasha.common.service.impl.VegetableOrderServiceImpl;
import com.horadrim.talrasha.common.util.CheckCodeUtil;
import com.horadrim.talrasha.common.util.SplitDateUtil;
import com.horadrim.talrasha.common.util.StringUtils;
import com.horadrim.talrasha.site.controller.request.CartItem;
import com.horadrim.talrasha.site.service.ClientVegetableOrderService;
import com.horadrim.talrasha.site.service.dto.SaveOrderDto;
import com.horadrim.talrasha.site.utils.OrderNotifyCanteenUtil;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.*;

/**
 * Created by Administrator on 2015/6/3.
 */
@Service("clientVegetableOrderService")
public class ClientVegetableOrderServiceImpl extends VegetableOrderServiceImpl implements ClientVegetableOrderService {
    @Resource(name="vegetableDao")
    private VegetableDao vegetableDao;
    @Resource(name = "userDao")
    private UserDao userDao;
    @Resource(name = "consumeHistoryDao")
    private ConsumeHistoryDao consumeHistoryDao;
    @Resource
    private OrderNotifyCanteenUtil orderNotifyCanteenUtil;
//    @Resource(name="orderService")
//    private OrderService orderService;
    @Override
    public SaveOrderDto addVegetableOrder(Map<Integer,CartItem> param,User user,String note) {

        VegetableOrder vegetableOrder=new VegetableOrder();
        SaveOrderDto dto = new SaveOrderDto();

        //保存订单的item
        BigDecimal totalPrice=new BigDecimal(0);
        CartItem c;
        Vegetable vegetable;
        for(Integer key:param.keySet()){
            c=param.get(key);
            vegetable=vegetableDao.get(c.getId());
            totalPrice=totalPrice.add((vegetable.getUnitPrice()).multiply(new BigDecimal(c.getCount())));
            VegetableOrderItem vegetableOrderItem=new VegetableOrderItem();
//            orderItem.setOrder(order);
            vegetableOrderItem.setVegetable(vegetable);
            vegetableOrderItem.setCount(c.getCount());
            vegetableOrder.getOrderItems().add(vegetableOrderItem);
            //vegetable.setSurplusNum();
        }
        //判断冻结金额是否小于等于当前金额
        if (user.getQingcaiBalance().compareTo(totalPrice)<0){
            dto.setRet(1);
            return dto;
        }

        vegetableOrder.setDeleted(0);
        vegetableOrder.setIsPayed(0);
        if(null!=note&&!"".equals(note))
            vegetableOrder.setNote(note);
        vegetableOrder.setOrderDate(new Date());
        //vegetableOrder.setTakeDate(vegetable.getSaleTime());
                //TODO ordernum 需要重新生成策略
        Long l=new Date().getTime();
        String oNum=l.toString()+new CheckCodeUtil().generateRandomNumberCode();
        vegetableOrder.setOrderNum(oNum);
        vegetableOrder.setStatus(0);
        vegetableOrder.setUser(user);
        vegetableOrder.setCanteenId(user.getCanteenId());
        //save(vegetableOrder);

        vegetableOrder.setTotalPrice(totalPrice);
        vegetableOrder.setPayType(-1);
        vegetableOrder = save(vegetableOrder);

        //冻结金额
        userDao.executeUpdate("UPDATE User SET qingcaiBalance = ? WHERE id=?",
                new Object[]{user.getQingcaiBalance().subtract(totalPrice),user.getId()});
        ConsumeHistory history = new ConsumeHistory();
        history.setAccountType(ConsumeHistory.AccountType.PINGTAI);
        history.setConsumeType(ConsumeHistory.ConsumeType.MAICAI_DONGJIE);
        history.setBalance(totalPrice);
        history.setTargetId(vegetableOrder.getId());
        history.setCanteenId(user.getCanteenId());
        history.setUserId(user.getId());
        Date date = new Date();
        history.setConsumeTime(date);
        SplitDateUtil.setYMD(history,date);
        consumeHistoryDao.save(history);
        dto.setOrder(vegetableOrder);

         //通知食堂
        orderNotifyCanteenUtil.asynNotify(user.getCanteenId(), vegetableOrder.getId(), CanteenMessage.MessageType.SHUCAI_ORDER);
        return dto;
    }

    @Override
    public void changeOrderStatus(Integer orderId, Integer status, Integer payStatus) {
        VegetableOrder order=vegetableOrderDao.get(orderId);
        User user=order.getUser();
        if(status!=null){
            if(status==3){
                //查询冻结记录
                /*ConsumeHistory dongjie = consumeHistoryDao.query("FROM ConsumeHistory WHERE userId=? AND consumeType=? AND targetId=?"
                        ,new Object[]{user.getId(),ConsumeHistory.ConsumeType.MAICAI_DONGJIE,orderId});
                //退费
                user.setQingcaiBalance(user.getQingcaiBalance().add(dongjie.getBalance()));
                //记录解冻
                ConsumeHistory jiedong = new ConsumeHistory();
                jiedong.setUserId(user.getId());
                jiedong.setBalance(dongjie.getBalance());
                jiedong.setTargetId(orderId);
                jiedong.setAccountType(ConsumeHistory.AccountType.PINGTAI);
                jiedong.setConsumeType(ConsumeHistory.ConsumeType.MAICAI_JIEDONG);
                Date date = new Date();
                jiedong.setConsumeTime(date);
                SplitDateUtil.setYMD(jiedong, date);*/
                order.setStatus(status);
            }else{
                order.setStatus(status);
            }
        }
        if(payStatus!=null){
            order.setIsPayed(payStatus);
        }
        vegetableOrderDao.saveOrUpdate(order);
    }

    @Override
    public int payOrder(int userId, ConsumeHistory.AccountType accountType, int[] orderArr, String payPwd) throws Exception {
        User user = userDao.get(userId);
        if (user == null)
            return CodeDefined.COMMON_NOT_AUTH;
        BigDecimal totalPay = new BigDecimal(0);
        List<ConsumeHistory> histories = new ArrayList<>();
        List<VegetableOrder> orders = new ArrayList<>();
        for(int orderId : orderArr){
            VegetableOrder order = vegetableOrderDao.get(orderId);
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
            history.setConsumeType(ConsumeHistory.ConsumeType.MAICAI);
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
        VegetableOrder order ;
        for (int i = 0 ;i<histories.size();i++) {
            consumeHistoryDao.save(histories.get(i));
            order = orders.get(i);
            order.setIsPayed(1);
            order.setPayType(accountType.ordinal());
            vegetableOrderDao.update(order);
            //通知食堂
            orderNotifyCanteenUtil.asynNotify(user.getCanteenId(),order.getId(),CanteenMessage.MessageType.SHUCAI_ORDER);
        }
        return 0;
    }

    @Override
    public int confirmOrder(int userId, int orderId, String payPwd) {
        User user = userDao.get(userId);
        if (user == null)
            return CodeDefined.COMMON_NOT_AUTH;
        VegetableOrder order = vegetableOrderDao.get(orderId);
        if (order==null)
            return CodeDefined.ORDER_INFO_NULL;

        if (order.getTotalPrice().compareTo(new BigDecimal(user.getSafeAmount()))==1){
            //大于50 需要支付密码
            String payPwdDb = user.getPayPassword();
            if(StringUtils.isBlank(payPwdDb))
                return CodeDefined.ACCOUNT_USER_PAYPWD_ISNOTSET;
            if(StringUtils.isBlank(payPwd))
                return CodeDefined.ACCOUNT_USER_PAYPWD_ISNULL;
            if (!payPwdDb.equals(DigestUtils.md5Hex(payPwd)))
                return CodeDefined.ACCOUNT_USER_PAYPWD_ERROR;
        }
        //查询冻结记录
        ConsumeHistory dongjie = consumeHistoryDao.query("FROM ConsumeHistory WHERE userId=? AND consumeType=? AND targetId=?"
                    ,new Object[]{userId,ConsumeHistory.ConsumeType.MAICAI_DONGJIE,orderId});
        //退费
        user.setQingcaiBalance(user.getQingcaiBalance().add(dongjie.getBalance()));
        //记录解冻
        ConsumeHistory jiedong = new ConsumeHistory();
        jiedong.setUserId(user.getId());
        jiedong.setBalance(dongjie.getBalance());
        jiedong.setTargetId(orderId);
        jiedong.setAccountType(ConsumeHistory.AccountType.PINGTAI);
        jiedong.setConsumeType(ConsumeHistory.ConsumeType.MAICAI_JIEDONG);
        Date date = new Date();
        jiedong.setConsumeTime(date);
        SplitDateUtil.setYMD(jiedong, date);

        //扣费
        user.setQingcaiBalance(user.getQingcaiBalance().subtract(order.getTotalPrice()));
        userDao.update(user);
        //将当前订单修改为已经确认，已经支付
        order.setIsPayed(1);
        order.setPayType(ConsumeHistory.AccountType.PINGTAI.ordinal());
        order.setStatus(2);
        update(order);

        //记录扣费记录
        ConsumeHistory history = new ConsumeHistory();
        history.setUserId(user.getId());
        history.setBalance(order.getTotalPrice());
        history.setTargetId(orderId);
        history.setAccountType(ConsumeHistory.AccountType.PINGTAI);
        history.setConsumeType(ConsumeHistory.ConsumeType.MAICAI);
        history.setConsumeTime(date);
        SplitDateUtil.setYMD(history, date);
        return 0;
    }


}
