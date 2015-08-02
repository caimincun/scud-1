package cn.scud.main.order.service;

import cn.scud.main.order.model.UserOrder;

import java.util.List;

/**
 * Created by Administrator on 2015/6/25.
 */
public interface OrderService {

    /**
     * 添加订单
     * @param order
     */
    List<UserOrder> saveOrder(UserOrder order,String userToken);

    /**
     * 根据用户token获取他发布的订单
     * @param userToken
     * @return
     */
    List<UserOrder> listOrdersByToken(String userToken);

    /**
     * 根据orderToken 获取订单信息
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
     * 搜索附近的订单
     * @param lng
     * @param lat
     * @param radius
     * @param page_index
     * @param page_size
     * @param userLbsId
     * @return
     */
    List<UserOrder> nearByOrders(String lng,String lat,int radius,int page_index,int page_size,int userLbsId);

    /**
     * 根据 userToken 查询 所有相关的order (发布和接受的)
     * @param userToken
     * @return
     */
    List<UserOrder> listReltOrderByUsken(String userToken);
}
