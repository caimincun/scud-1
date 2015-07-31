package cn.scud.main.order.dao;

import cn.scud.main.order.model.UserOrder;

import java.util.List;

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
     * 根据token ，获取 orders
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
}
