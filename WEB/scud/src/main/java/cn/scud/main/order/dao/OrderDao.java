package cn.scud.main.order.dao;

import cn.scud.main.order.model.OrderAndUser;
import cn.scud.main.order.model.UserOrder;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2015/6/25.
 */
public interface OrderDao {

    /**
     * 添加订单
     * @return
     */
     void saveOrder(UserOrder order);

    /**
     * 根据token ，获取 用户发布的订单 orders
     */
    List<UserOrder> listOrdersByToken(String userToken);

    /**
     * 根据orderToken 获取 Order
     * @param orderToken
     * @return
     */
    UserOrder getOrderByToken(String orderToken);

    /**
     * 设置 order 标记 完成
     * @param orderToken
     */
    void setOrderComplete(String orderToken);

    /**
     * 设置订单状态为确认
     * @param orderToken
     */
    void setOrderConfirm(String orderToken);

    /**
     * 根据 userTokens 查询相关订单
     * @param userTokens
     * @return
     */
    List<UserOrder> getOrdersByUsTokens(List userTokens);

    /**
     * 查询所有相关订单 （发布和接受）
     * @param userToken
     * @return
     */
    List<UserOrder> listReltOrderByUsken(String userToken);


    /**
     * 保存 用户 和订单 之间的关联关系
     * @param orderAndUser
     */
    void  saveOrderAndUser(OrderAndUser orderAndUser);

    /**
     * 设置订单的接单人
     */
    void setOrderAcptToken(Map map);

    /**
     * 根据 orderToken 删除中间表所有相关数据
     * @param orderToken
     */
    void delOrdAndUserByOrken(String orderToken);

    /**
     * 删除自己发布的需求订单
     * @param orderToken
     */
    void delOrderByOrken(String orderToken);

    /**
     * 根据 orderToken 和 userToken 判断是否表达接单意向
     * @param orderAndUser
     * @return
     */
    int countOrderAndUser(OrderAndUser orderAndUser);

    /**
     * 将order 的接单人数 +1
     * @param orderToken
     */
    void aptNumAddOne(String orderToken);
}
