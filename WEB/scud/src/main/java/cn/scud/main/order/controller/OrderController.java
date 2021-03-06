package cn.scud.main.order.controller;

import cn.easemob.server.example.SendMegHelper;
import cn.scud.commoms.CodeDefined;
import cn.scud.commoms.CommonParamDefined;
import cn.scud.commoms.response.*;
import cn.scud.main.order.model.OrderAndUser;
import cn.scud.main.order.model.UserOrder;
import cn.scud.main.order.service.OrderService;
import cn.scud.main.user.model.User;
import cn.scud.main.user.model.UserInfo;
import cn.scud.main.user.service.UserService;
import cn.scud.utils.StreamSerializer;
import cn.scud.utils.WebUtil;
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
@Controller
@RequestMapping("/order")
public class OrderController {

    @Resource
    private OrderService orderService;

    @Resource
    private UserService userService;

    /**
     * 添加订单信息
     * @return
     */
    @RequestMapping("/saveOrder")
    @ResponseBody
    public OperatorResponse saveOrder(HttpServletRequest request) throws Exception{
        UserOrder order = StreamSerializer.streamSerializer(request.getInputStream(),UserOrder.class);
        String userToken = (String)request.getSession().getAttribute(CommonParamDefined.USER_TOKEN);
        order.setOrderUserToken(userToken);
        order.setAptUserNum(0);
        order.setOrderComplteFlag(0);
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
        List<UserOrder> userOrderList = orderService.listReltOrderByUsken((String) session.getAttribute(CommonParamDefined.USER_TOKEN));
        ListSucRes listSucRes = new ListSucRes();
        listSucRes.setData(userOrderList);
        return listSucRes;
    }

    /**
     * 查询用户 与自己相关订单 （已经完成），不分页
     * @param session
     * @return
     */
    @RequestMapping("/lisetRelateComplateOrders")
    @ResponseBody
    public OperatorResponse lisetRelateComplateOrders(HttpSession session){
        List<UserOrder> userOrderList = orderService.listRelateComplateOrders((String) session.getAttribute(CommonParamDefined.USER_TOKEN));
        ListSucRes listSucRes = new ListSucRes();
        listSucRes.setData(userOrderList);
        return listSucRes;
    }


    /**
     * 根据用户 userToken 查询自己发布的订单
     * @param session
     * @return
     */
    @RequestMapping("/getOrdersbyUsTokey")
    @ResponseBody
    public OperatorResponse getOrdersbyUsTokey(HttpSession session){
        List<UserOrder> orderList = orderService.listOrdersByToken((String) session.getAttribute(CommonParamDefined.USER_TOKEN));
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
     *  修改订单状态，可以将其标记为完成
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
        return new SuccessJsonRes();
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
        System.out.println("userLbsId:" + userLbsId);
        int radius = 50; //默认查询5公里距离内的
        int page_size = 100;// 设置每一页返回的条数，这儿默认两条
        List<UserOrder> orderLists = orderService.nearByOrders(session,lng,lat,radius,page_index,page_size,userLbsId);
        System.out.println("方法执行结束nearByOrders_roderLists.size():"+orderLists.size());
        ListSucRes listSucRes = new ListSucRes();
        listSucRes.setData(orderLists);
        return listSucRes;
    }


    /**
     * 表达接单意向，将 userToken 和 orderToken 保存在 中间表中  ，表达接单意向
     * @param orderToken
     * @param session
     * @return
     */
    @RequestMapping("/saveOrderAndUser")
    @ResponseBody
    public OperatorResponse saveOrderAndUser(String orderToken,String skillUserToken,HttpSession session){
        String userToken = (String)session.getAttribute(CommonParamDefined.USER_TOKEN);
        OrderAndUser orderAndUser = new OrderAndUser();
        orderAndUser.setOrderToken(orderToken);
        orderAndUser.setUserToken(userToken);
        //先判断是否已经表达了接单意向
        if(orderService.isSaveOrderAndUser(orderAndUser)){
            return new ErrorJsonRes(CodeDefined.ORDER_TOKEN_JIEDAN,"你已经对此需求表达接单意向，无需重复操作！");
        }
        // 如果没有，则表达接单意向
        orderService.saveOrderAndUser(orderAndUser);
        //获取用户信息
        User user = userService.loadUserByToken(skillUserToken);
        // 消息推送
        SendMegHelper.sendMsg(user.getPhoneNumber(),SendMegHelper.MSG_TYPE_SKILL_ORDER,"有人愿意接收您的订单!!");
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
        listSucRes.setData(userInfoList);
        return  listSucRes;
    }

    /**
     * 用户直接对某个对象的技能发布订单                 // 前台，应该把对方的 的 userToken 隐藏保存给我
     * userToken   向 userToken  发起订单
     * @return
     */
    @RequestMapping("/userStartOrder")
    @ResponseBody
    public OperatorResponse userStartOrder(HttpServletRequest request){  // 这儿应该传递一个订单的整体对象实体对象过来，
        UserOrder order = null;
        try {
             order = StreamSerializer.streamSerializer(request.getInputStream(),UserOrder.class);
        } catch (Exception e) {
            return  new ErrorJsonRes(CodeDefined.EXCEPTION_CODE_DATA_ERROR,CodeDefined.getMessage(CodeDefined.EXCEPTION_CODE_DATA_ERROR));
        }

        order.setOrderComplteFlag(1);
        order.setOrderToken(WebUtil.getOrderToken());
        order.setAptUserNum(1); // 意向接单人数量为1
        order.setOrderUserToken((String)request.getSession().getAttribute(CommonParamDefined.USER_TOKEN));
        orderService.saveOrder(order);
        return new SuccessJsonRes();
    }

    /**
     *  确认某人接单 ,（暂时不删除 已将接单表中的临时数据） ,然后修改 order 状态为确认接单1 ，然后给 order 记录 的 orderacpToken 添加上数据
     * @param userToken
     * @param orderToken
     * @return
     */
    @RequestMapping("/setOrderAcptToken")
    @ResponseBody
    public OperatorResponse setOrderAcptToken(String userToken,String orderToken){
        orderService.setOrderAcptToken(userToken, orderToken);
        return new SuccessJsonRes();
    }


    /**
     * 删除自己发布的需求订单 by OrderToken
     * @return
     */
    @RequestMapping("/delOrderByOrken")
    @ResponseBody
    public OperatorResponse delOrderByOrken(String orderToken,String userOrderToken,HttpServletRequest request){
        // 需要判断订单删除的操作者是订单发布人还是接单人，比较当前用户的usertoken和订单发布者的userOrderToken
        String userToken = (String)request.getSession().getAttribute(CommonParamDefined.USER_TOKEN);
        // 如果相等，则代表是订单发布者删除订单，则删除所有信息，接单相关信息也删除
        if(userToken.equals(userOrderToken)){
            orderService.delOrderByOrken(orderToken);
        }
        // 不相等，则只需要删除该接单者的接单中间表信息
        else{
            orderService.delOrdAndUserByUsken(userToken,orderToken);
        }
        return new SuccessJsonRes();
    }



}
