package cn.scud.main.order.service.impl;

import cn.scud.commoms.jsonModel.JsonPioContent;
import cn.scud.commoms.jsonModel.JsonPioDetail;
import cn.scud.commoms.jsonModel.JsonPioSearch;
import cn.scud.main.order.dao.OrderDao;
import cn.scud.main.order.model.UserOrder;
import cn.scud.main.order.service.OrderService;
import cn.scud.main.user.dao.UserDao;
import cn.scud.main.user.model.UserInfo;
import cn.scud.utils.LbsHelper;
import cn.scud.utils.WebUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collections;
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

    @Override
    public List<UserOrder> saveOrder(UserOrder order, String userToken) {
        order.setOrderUserToken(userToken);
        order.setOrderToken(WebUtil.getOrderToken());
        order.setOrderComplteFlag(0); // 设置订单状态为0，发布中
        orderDao.saveOrder(order);
//        return orderDao.listOrdersByToken(userToken);  // 连续执行两个dao ，第二个会自动关闭
        List<UserOrder> userOrders = listOrdersByToken(userToken);
        return userOrders;
    }


    @Override
    public List<UserOrder> listOrdersByToken(String userToken) {
        List<UserOrder> orders = orderDao.listOrdersByToken(userToken);
        return orders;
    }

    @Override
    public UserOrder getOrderByToken(String orderToken) {
        UserOrder order = orderDao.getOrderByToken(orderToken);
        return order;
    }

    @Override
    public void setOrderComplete(String orderToken) {
        orderDao.setOrderComplete(orderToken);
    }

    /**
     * 按距离查查询附近的订单
     * @param lng
     * @param lat
     * @param radius
     * @param page_index
     * @param page_size
     * @param userLbsId
     * @return
     */
    @Override
    @Transactional
    public List<UserOrder> nearByOrders(String lng, String lat, int radius, int page_index, int page_size, int userLbsId) {

//        //1.跟新当前用户lbs 经纬度
//        LbsHelper.updatePio(lng, lat, userLbsId);
//        //2. 搜索附近范围内 的对象
//        JsonPioSearch jsonPioSearch = LbsHelper.pioSearch(lng, lat, radius, page_index, page_size);
//        List<JsonPioContent> jsonPioContents = jsonPioSearch.getContents();
//        List userLbsIds = new ArrayList();
//        for(JsonPioContent jsonPioContent:jsonPioContents){
//            userLbsIds.add(jsonPioContent.getUid());
//        }
//        List<UserInfo> userInfos = userDao.searchNearbyPoi(userLbsIds); // 取得附近人的信息，但是还需要把 jsonPioSearch 记录里面的 距离添加进去,此时是无序的
//        List<UserInfo> userInfoList = new ArrayList<UserInfo>();                // 保存由近到远的 userInfo
//        for(JsonPioContent jsonPioContent:jsonPioContents){
//            for(UserInfo userInfo:userInfos){
//                if(jsonPioContent.getUid() == userInfo.getLbsId()){
//                    userInfo.setDistance(jsonPioContent.getDistance());
//                    userInfoList.add(userInfo); //将有序由近到远的添加进去
//                    break;
//                }
//            }
//        }
//        //3. 根据附近的对象，再来查询 相关的订单
//        List<String> userTokens = new ArrayList<String>();
//        for(UserInfo userInfo:userInfos){
//            userTokens.add(userInfo.getUserToken());
//        }
//        List<UserOrder> orderList = orderDao.getOrdersByUsTokens(userTokens);       // 此时，查询的附近的订单有可能为空，还需要扩大范围
//        // 这儿还需要进一步优化，如果orderList为空的话，还需要扩大搜索范围
//        return orderList;


        // 方法二、返回数据加上距离

        //1.跟新当前用户lbs 经纬度
        LbsHelper.updatePio(lng, lat, userLbsId);
        //2. 搜索附近范围内 的对象
        JsonPioSearch jsonPioSearch = LbsHelper.pioSearch(lng, lat, radius, page_index, page_size);
        List<JsonPioContent> jsonPioContents = jsonPioSearch.getContents();
        List userLbsIds = new ArrayList();
        for(JsonPioContent jsonPioContent:jsonPioContents){
            userLbsIds.add(jsonPioContent.getUid());        // 取得附近地图上人的 lbsid
        }
        List<UserInfo> userInfos = userDao.searchNearbyPoi(userLbsIds); // 取得lbsid 对应数据库附近人的信息

        List<UserOrder> userOrderList = new ArrayList<UserOrder>();                             // 这个是保存所有的订单

        for(JsonPioContent jsonPioContent:jsonPioContents){             // 由近到远遍历对象
            for(UserInfo userInfo:userInfos){
                if(jsonPioContent.getUid() == userInfo.getLbsId()){
                    List<UserOrder> userOrders = orderDao.listOrdersByToken(userInfo.getUserToken());           // 通过附近的人 取出 数据库对应的订单
                    for(UserOrder userOrder:userOrders){
                        userOrder.setDistance(jsonPioContent.getDistance());
                        userOrder.setUserPicture(userInfo.getUserInfoPicture());
                        userOrder.setUserSex(userInfo.getUserInfoSex());
                        userOrderList.add(userOrder);                           // 将封装好的 order 放入 orderList
                    }
                    break;
                }
            }
        }
        return userOrderList;
    }

    @Override
    public List<UserOrder> listReltOrderByUsken(String userToken) {

        return orderDao.listReltOrderByUsken(userToken);
    }



    /**
     * 根据 orderToken 查询相关的 意向接单人的信息 ，并加上距离
     */
    @Override
    public List<UserInfo> OrderAcptUserByOrken(int lbsId,String lat,String lng,String orderToken) {
        int page_size = 1;
        int userLbsId = 0;
        //1. 根据orderToken 查询出相关的人
        List<UserInfo> userInfos = userDao.loadOrderAcptUserByUsken(orderToken);
        //2. 根据 userInfos 查询出 和 当前用户 lbsid 之间的距离  ，通过每个用户 lbsid 取出每个用户的 经纬度， 然后计算两点经纬度之间的距离
        for(UserInfo userInfo:userInfos){
            JsonPioDetail jsonPioDetail = LbsHelper.pioDetail(userInfo.getLbsId());
            double lng2 = Double.parseDouble(jsonPioDetail.getPoi().getLocation()[0]);
            double lat2 = Double.parseDouble(jsonPioDetail.getPoi().getLocation()[1]);
            Double distance = LbsHelper.getDistance(Double.parseDouble(lng), Double.parseDouble(lat), lng2, lat2);
            System.out.println("两点 经纬度之间的距离为："+distance);
            userInfo.setDistance(Integer.valueOf(distance.intValue()));
        }
        Collections.sort(userInfos);
        return userInfos;
    }
}
