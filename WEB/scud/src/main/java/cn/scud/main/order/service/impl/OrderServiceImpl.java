package cn.scud.main.order.service.impl;

import cn.easemob.server.example.SendMegHelper;
import cn.scud.commoms.jsonModel.JsonPioContent;
import cn.scud.commoms.jsonModel.JsonPioDetail;
import cn.scud.commoms.jsonModel.JsonPioSearch;
import cn.scud.main.order.dao.OrderDao;
import cn.scud.main.order.model.OrderAndUser;
import cn.scud.main.order.model.UserOrder;
import cn.scud.main.order.service.OrderService;
import cn.scud.main.user.dao.UserDao;
import cn.scud.main.user.model.User;
import cn.scud.main.user.model.UserInfo;
import cn.scud.utils.LbsHelper;
import cn.scud.utils.WebUtil;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.sql.Timestamp;
import java.util.*;

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
    public void saveOrder(UserOrder order) {
        order.setOrderToken(WebUtil.getOrderToken());
        order.setOrderComplteFlag(0); // 设置订单状态为0，发布中
        orderDao.saveOrder(order);
        // 消息推送
        SendMegHelper.sendMsg("18381090832",SendMegHelper.MSG_TYPE_SKILL_ORDER,"测试消息发送");
//        return orderDao.listOrdersByToken(userToken);  // 连续执行两个dao ，第二个会自动关闭
//        List<UserOrder> userOrders = listOrdersByToken(userToken);
//        return userOrders;
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
    public List<UserOrder> nearByOrders(HttpSession session,String lng, String lat, int radius, int page_index, int page_size, int userLbsId) {
        //1.跟新当前用户lbs 经纬度
        LbsHelper.updatePio(lng, lat, userLbsId);
        // 为循环查询便利
        if(page_index == 0){
            session.setAttribute("order_differ_num",-1);  // 当前页和实际查询页之间的差均
        }
        Boolean ifLoop = true;
        List<UserOrder> userOrderList = new ArrayList<UserOrder>();         // 返回的数据列表
        int loopTime = 0;           // 设置遍历循环次数记录对象
        int numTemp = 0;
        System.out.println("page_index:"+page_index);
        while (ifLoop) {
            loopTime++;
            //2. 搜索附近范围内 的对象
            int searchNum = Integer.parseInt(session.getAttribute("order_differ_num").toString());
            numTemp = searchNum+1;
            session.setAttribute("order_differ_num", numTemp);
            JsonPioSearch jsonPioSearch = LbsHelper.pioSearch(lng, lat, radius, numTemp, page_size);
            List<JsonPioContent> jsonPioContents = jsonPioSearch.getContents();
            List userLbsIds = new ArrayList();

            for (JsonPioContent jsonPioContent : jsonPioContents) {
                userLbsIds.add(jsonPioContent.getUid());        // 取得附近地图上人的 lbsid
            }
            List<UserInfo> userInfos = null;
            if(userLbsIds.size()>0){
                 userInfos = userDao.searchNearbyPoi(userLbsIds); // 取得lbsid 对应数据库附近人的信息
            }else {
                if(loopTime<3){
                    ifLoop = false;
                    continue;
                }else{
                    break;
                }
            }
            // 这个是保存所有的订单
            for (JsonPioContent jsonPioContent : jsonPioContents) {             // 由近到远遍历对象
                for (UserInfo userInfo : userInfos) {
                    if (jsonPioContent.getUid() == userInfo.getLbsId()) {
                        List<UserOrder> userOrders = orderDao.listOrdersByToken(userInfo.getUserToken());           // 通过附近的人 取出 数据库对应的订单
                        for (UserOrder userOrder : userOrders) {                                                           // 对订单进行分装，添加上距离等信息
                            userOrder.setDistance(jsonPioContent.getDistance());
                            userOrder.setUserPicture(userInfo.getUserInfoPicture());
                            userOrder.setUserSex(userInfo.getUserInfoSex());
                            userOrderList.add(userOrder);                           // 将封装好的 order 放入 orderList
                        }
                    }
                }
            }
            if(userOrderList.size() == 0 ){                    // 如果这次查询没有结果，则扩大查询范围，这样保持每次分页查询都有查询结果
                ifLoop = true;
            }else {
                ifLoop = false;
            }
            if(loopTime>= 3){
                ifLoop = false;
            }
        }

        return userOrderList;
    }

    @Override
    public List<UserOrder> listReltOrderByUsken(String userToken) {

        return orderDao.listReltOrderByUsken(userToken);
    }

    @Override
    public List<UserOrder> listRelateComplateOrders(String userToken) {
        return orderDao.listRelateComplateOrders(userToken);
    }


    /**
     * 根据 orderToken 查询相关的 意向接单人的信息 ，并加上距离
     */
    @Override
    public List<UserInfo> OrderAcptUserByOrken(int lbsId,String lat,String lng,String orderToken) {
        int page_size = 1;
        int userLbsId = 0;
        //1. 根据orderToken 查询中间表里面的人
        System.out.println("orderToken:"+orderToken);
        List<UserInfo> userInfos = userDao.loadOrderAcptUserByUsken(orderToken);   // 这个方法需要修改，不应该从这调用这个 dao 方法查询相关对象
        //2. 根据 userInfos 查询出 和 当前用户 lbsid 之间的距离  ，通过每个用户 lbsid 取出每个用户的 经纬度， 然后计算两点经纬度之间的距离
        for(UserInfo userInfo:userInfos){
            JsonPioDetail jsonPioDetail = LbsHelper.pioDetail(userInfo.getLbsId());
            double lng2 = Double.parseDouble(jsonPioDetail.getPoi().getLocation()[0]);
            double lat2 = Double.parseDouble(jsonPioDetail.getPoi().getLocation()[1]);
            Double distance = LbsHelper.getDistance(Double.parseDouble(lng), Double.parseDouble(lat), lng2, lat2);
            userInfo.setDistance(Integer.valueOf(distance.intValue()));
            userInfo.setIsAccess(0);
        }
        Collections.sort(userInfos);
        // 然后查询接单人的信息
        UserOrder userOrder = orderDao.getOrderByToken(orderToken);
        UserInfo userInfo = null;
        if(null != userOrder.getOrderAcptUsken() && !"".equals(userOrder.getOrderAcptUsken())){
            userInfo = userDao.getUserInfoByToken(userOrder.getOrderAcptUsken());
            JsonPioDetail jsonPioDetail = LbsHelper.pioDetail(userInfo.getLbsId());
            double lng2 = Double.parseDouble(jsonPioDetail.getPoi().getLocation()[0]);
            double lat2 = Double.parseDouble(jsonPioDetail.getPoi().getLocation()[1]);
            Double distance = LbsHelper.getDistance(Double.parseDouble(lng), Double.parseDouble(lat), lng2, lat2);
            userInfo.setDistance(Integer.valueOf(distance.intValue()));
            userInfo.setIsAccess(1);
            userInfo.setPhoneNumber(userDao.loadUserByToken(userInfo.getUserToken()).getPhoneNumber());
            userInfos.add(0,userInfo);  // 将确认的接单人放到第一条数据
        }
        return userInfos;
    }

    @Transactional
    @Override
    public void saveOrderAndUser(OrderAndUser orderAndUser) {
        // 保存中间表
        orderDao.saveOrderAndUser(orderAndUser);
        // 修改订单中的接单人数+1
        orderDao.aptNumAddOne(orderAndUser.getOrderToken());

    }

    /**
     *  1.确定接单人
     *  2.将中间表中关于 接单人 的 中间记录删除
     * @param userToken
     * @param orderToken
     */
    @Transactional
    @Override
    public void setOrderAcptToken(String userToken, String orderToken) {
        Map map = new HashMap();
        map.put("userToken",userToken);
        map.put("orderToken",orderToken);
        orderDao.setOrderConfirm(map);
        // 然后删除中间表中间这条记录
        orderDao.delByUskenAndOrken(map);

    }

    @Override
    public void delOrderByOrken(String orderToken) {
        orderDao.delOrderByOrken(orderToken);
        // 然后删除需求中间表
        orderDao.delOrdAndUserByOrken(orderToken);
    }

    @Override
    public Boolean isSaveOrderAndUser(OrderAndUser orderAndUser) {
        int num = orderDao.countOrderAndUser(orderAndUser);
        if(num>0){
            return true;
        }
        return false;
    }

    @Override
    public List<UserOrder> listRelatedOrders(String userToken) {
        return orderDao.listOrdersByToken(userToken);
    }
}
