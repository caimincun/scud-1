package cn.scud.main.order.controller;

import cn.scud.commoms.CodeDefined;
import cn.scud.commoms.CommonParamDefined;
import cn.scud.commoms.response.*;
import cn.scud.main.order.model.OrderAndUser;
import cn.scud.main.order.model.UserOrder;
import cn.scud.main.order.service.OrderService;
import cn.scud.main.user.model.UserInfo;
import cn.scud.main.user.service.UserService;
import cn.scud.utils.StreamSerializer;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by Administrator on 2015/6/25.
 */
@Controller()
@RequestMapping("/order")
public class OrderController {

    @Resource
    private OrderService orderService;

    @Resource
    private UserService userService;

    /**
     * 添加订单信息,这儿应该返回所有的用户相关的订单列表
     * @return
     */
    @RequestMapping("/saveOrder")
    @ResponseBody
    public OperatorResponse saveOrder(HttpServletRequest request) throws Exception{
        UserOrder order = StreamSerializer.streamSerializer(request.getInputStream(),UserOrder.class);
        System.out.println("order:"+order);
        String userToken = (String)request.getSession().getAttribute(CommonParamDefined.USER_TOKEN);
        order.setOrderUserToken(userToken);
        orderService.saveOrder(order);
        return new SuccessJsonRes();
    }


    /**
     * 根据用户userToken获取用户发布订单
     * @return
     */
    @RequestMapping("/listOrdersByToken")
    @ResponseBody
    public OperatorResponse listOrdersByToken(HttpSession session){
       String userToken = (String)session.getAttribute(CommonParamDefined.USER_TOKEN);
        List<UserOrder> orders = orderService.listOrdersByToken(userToken);
        ListSucRes listSucRes = new ListSucRes();
        listSucRes.setData(orders);
        return listSucRes;
    }

    /**
     * 根据 userToken 获取相关的订单，未完成的 （自己发布和自己接受的单子）
     * @return
     */
    @RequestMapping("/listReltOrderByUsken")
    @ResponseBody
    public OperatorResponse listReltOrderByUsken(HttpSession session){
        List<UserOrder> userOrderList = orderService.listReltOrderByUsken((String)session.getAttribute(CommonParamDefined.USER_TOKEN));
        ListSucRes listSucRes = new ListSucRes();
        listSucRes.setData(userOrderList);
        return listSucRes;
    }

    /**
     * 根据用户 userToken 查询自己发布的相关的订单
     * @param session
     * @return
     */
    @RequestMapping("/getOrdersbyUsTokey")
    @ResponseBody
    public OperatorResponse getOrdersbyUsTokey(HttpSession session){
        List<UserOrder> orderList = orderService.listOrdersByToken((String)session.getAttribute(CommonParamDefined.USER_TOKEN));
        ListSucRes listSucRes = new ListSucRes();
        listSucRes.setData(orderList);
        return listSucRes;
    }

    /**
     * 根据orderToken 获取订单信息
     * @param orderToken
     * @return
     */
    @RequestMapping("/getOrderByToken")
    @ResponseBody
    public OperatorResponse getOrderByToken(String orderToken){
        if(null == orderToken || "".equals(orderToken)){
            return new ErrorJsonRes(CodeDefined.ORDER_TOKEN_NULL,CodeDefined.getMessage(CodeDefined.ORDER_TOKEN_NULL));
            //30002，订单token 为空
        }
        UserOrder order = orderService.getOrderByToken(orderToken);
        ObjSucRes objSucRes = new ObjSucRes();
        objSucRes.setData(order);
        return objSucRes;
    }



    /**
     *  修改订单状态，可以将其标记白为完成、未完成、撤销之类的 ,, 这个接口需要讨论
     * @param orderToken
     * @return
     */
    @RequestMapping("/setOrderComplete")
    @ResponseBody
    public  OperatorResponse setOrderComplete(String orderToken){
        if(null == orderToken || "".equals(orderToken)){
            return new ErrorJsonRes(CodeDefined.ORDER_TOKEN_NULL,CodeDefined.getMessage(CodeDefined.ORDER_TOKEN_NULL));
            //30002，订单token 为空
        }
        orderService.setOrderComplete(orderToken);
        SuccessJsonRes successJsonRes = new SuccessJsonRes();
        return successJsonRes;
    }


    /**
     * 查看附近的订单
     * @return
     */
    @RequestMapping("/nearByOrders")
    @ResponseBody
    public OperatorResponse nearByOrders(HttpSession session,String lat,String lng,int page_index){
        System.out.println("lat:"+lat +" lng:"+lng);
        int userLbsId = (Integer)session.getAttribute(CommonParamDefined.USER_LBS_ID);
        System.out.println("userLbsId:"+userLbsId);
        int radius = 100000; //默认查询50公里距离内的
        int page_size = 5;// 设置每一页返回的条数，这儿默认两条
        List<UserOrder> orderLists = orderService.nearByOrders(lng,lat,radius,page_index,page_size,userLbsId);
        System.out.println("nearByOrders_roderLists.size():"+orderLists.size());
        for(UserOrder userOrder:orderLists){
            System.out.println(userOrder);
        }
        ListSucRes listSucRes = new ListSucRes();
        listSucRes.setData(orderLists);
        return listSucRes;
    }



    /**
     * 表达接单意向，将 userToken 和 orderToken 保存在 中间表中
     * @param orderToken
     * @param session
     * @return
     */
    @RequestMapping("/saveOrderAndUser")
    @ResponseBody
    public OperatorResponse saveOrderAndUser(String orderToken,HttpSession session){
        String userToken = (String)session.getAttribute(CommonParamDefined.USER_TOKEN);
        OrderAndUser orderAndUser = new OrderAndUser();
        orderAndUser.setOrderToken(orderToken);
        orderAndUser.setUserToken(userToken);
        orderService.saveOrderAndUser(orderAndUser);
        return new SuccessJsonRes();
    }


    /**
     * 根据 orderToken 查询相关的 意向接单人的信息 ，并加上距离,这次更新本用户的经纬度
     * @return
     */
    @RequestMapping("/orderAcptUserByOrken")
    @ResponseBody
    public OperatorResponse OrderAcptUserByOrken(HttpSession session,String lat,String lng,String orderToken){
        int userLbsId = (Integer)session.getAttribute(CommonParamDefined.USER_LBS_ID);      // 获取当前用户的 lbs 关联 id
        List<UserInfo> userInfoList = orderService.OrderAcptUserByOrken(userLbsId,lat,lng,orderToken);
        System.out.println("userInfoList:"+userInfoList.size());
        ListSucRes listSucRes = new ListSucRes();
        return  listSucRes;
    }







}
