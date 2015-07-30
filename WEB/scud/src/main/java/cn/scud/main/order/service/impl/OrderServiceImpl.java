package cn.scud.main.order.service.impl;

import cn.scud.commoms.jsonModel.JsonPioContent;
import cn.scud.commoms.jsonModel.JsonPioSearch;
import cn.scud.main.order.dao.OrderDao;
import cn.scud.main.order.model.Order;
import cn.scud.main.order.service.OrderService;
import cn.scud.main.user.dao.UserDao;
import cn.scud.main.user.model.UserInfo;
import cn.scud.utils.LbsHelper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2015/6/25.
 */
@Service("orderService")
public class OrderServiceImpl implements OrderService {

    @Resource
    private OrderDao orderDao;

    @Resource
    private UserDao userDao;

    @Transactional
    @Override
    public List<Order> saveOrder(Order order, String userToken) {
        orderDao.saveOrder(order);
        return orderDao.listOrdersByToken(userToken);
    }


    @Override
    public List<Order> listOrdersByToken(String userToken) {
        List<Order> orders = orderDao.listOrdersByToken(userToken);
        return orders;
    }

    @Override
    public Order getOrderByToken(String orderToken) {
        Order order = orderDao.getOrderByToken(orderToken);
        return order;
    }

    @Override
    public void setOrderComplete(String orderToken) {
        orderDao.setOrderComplete(orderToken);
    }

    @Override
    @Transactional
    public List<Order> nearByOrders(String lng, String lat, int radius, int page_index, int page_size, int userLbsId) {

        //1.跟新当前用户lbs 经纬度
        LbsHelper.updatePio(lng, lat, userLbsId);
        //2. 搜索附近范围内 的对象
        JsonPioSearch jsonPioSearch = LbsHelper.pioSearch(lng, lat, radius, page_index, page_size);
        List<JsonPioContent> jsonPioContents = jsonPioSearch.getContents();
        List userLbsIds = new ArrayList();
        for(JsonPioContent jsonPioContent:jsonPioContents){
            userLbsIds.add(jsonPioContent.getUid());
        }
        List<UserInfo> userInfos = userDao.searchNearbyPoi(userLbsIds); // 取得附近人的信息，但是还需要把 jsonPioSearch 记录里面的 距离添加进去,此时是无序的
        List<UserInfo> userInfoList = new ArrayList<UserInfo>();
        for(JsonPioContent jsonPioContent:jsonPioContents){
            for(UserInfo userInfo:userInfos){
                if(jsonPioContent.getUid() == userInfo.getLbsId()){
                    userInfo.setDistance(jsonPioContent.getDistance());
                    userInfoList.add(userInfo); //将有序由近到远的添加进去
                    break;
                }
            }
        }
        //3. 根据附近的对象，再来查询 相关的订单
        List<String> userTokens = new ArrayList<String>();
        for(UserInfo userInfo:userInfos){
            userTokens.add(userInfo.getUserToken());
        }
        List<Order> orderList = orderDao.getOrdersByUsTokens(userTokens);
        // 这儿还需要进一步优化，如果orderList为空的话，还需要扩大搜索范围
        return orderList;
    }
}
