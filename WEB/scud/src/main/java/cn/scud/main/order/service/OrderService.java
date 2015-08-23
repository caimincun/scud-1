package cn.scud.main.order.service;

import cn.scud.main.order.model.OrderAndUser;
import cn.scud.main.order.model.UserOrder;
import cn.scud.main.user.model.UserInfo;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by Administrator on 2015/6/25.
 */
public interface OrderService {

    /**
     * 添加订单
     * @param order
     */
    void saveOrder(UserOrder order);

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
    List<UserOrder> nearByOrders(HttpSession session,String lng,String lat,int radius,int page_index,int page_size,int userLbsId);

    /**
     * 根据 userToken 查询 所有相关的order (发布和接受的)
     * @param userToken
     * @return
     */
    List<UserOrder> listReltOrderByUsken(String userToken);

    /**
     * 根据 orderToken 查询相关的 意向接单人的信息 ，并加上距离
     */
    List<UserInfo> OrderAcptUserByOrken(int lbsId,String lat,String lng,String orderToken);

    /**
     * 保存 用户 和订单 之间的关联关系
     * @param orderAndUser
     */
    void  saveOrderAndUser(OrderAndUser orderAndUser);
}
