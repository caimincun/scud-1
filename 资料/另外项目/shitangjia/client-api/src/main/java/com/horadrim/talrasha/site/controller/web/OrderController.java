package com.horadrim.talrasha.site.controller.web;


import com.horadrim.talrasha.common.CodeDefined;
import com.horadrim.talrasha.common.CommonParamDefined;
import com.horadrim.talrasha.common.model.*;
import com.horadrim.talrasha.common.response.json.AbstractJsonRes;
import com.horadrim.talrasha.common.response.json.ErrorJsonRes;
import com.horadrim.talrasha.common.response.json.ListSucRes;
import com.horadrim.talrasha.common.response.json.SuccessJsonRes;
import com.horadrim.talrasha.common.service.*;
import com.horadrim.talrasha.common.service.dto.OrderItemPojo;
import com.horadrim.talrasha.common.service.dto.OrderPojo;
import com.horadrim.talrasha.common.service.dto.QueryOrdersParam;
import com.horadrim.talrasha.common.util.StringUtils;
import com.horadrim.talrasha.site.controller.request.AddToCartReq;
import com.horadrim.talrasha.site.controller.request.CartItem;
import com.horadrim.talrasha.site.controller.result.ChangeCartRes;
import com.horadrim.talrasha.site.controller.result.VegCheckConfirmRes;
import com.horadrim.talrasha.site.exception.DefaultExceptionHandler;
import com.horadrim.talrasha.site.service.ClientOrderService;
import com.horadrim.talrasha.site.service.ClientUserService;
import com.horadrim.talrasha.site.service.ClientVegetableOrderService;
import com.horadrim.talrasha.site.service.dto.SaveOrderDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2014/10/28.
 */
@Controller
@RequestMapping("/order")
public class OrderController extends DefaultExceptionHandler {

    @Resource(name = "userService")
    private UserService userService;
    @Resource(name="productService")
    private ProductService productService;
    @Resource(name="clientOrderService")
    private ClientOrderService clientOrderService;
    @Resource(name="clientUserService")
    private ClientUserService clientUserService;
    @Resource(name="orderItemService")
    private OrderItemService orderItemService;
    @Resource(name="clientVegetableOrderService")
    private ClientVegetableOrderService clientVegetableOrderService;
    @Resource(name="vegetableService")
    private VegetableService vegetableService;
    @Resource(name="vegetableOrderItemService")
    private VegetableOrderItemService vegetableOrderItemService;


    /**
     * 跳转到支付页main
     * @return
     */
    @RequestMapping("/toPayPage")
    public ModelAndView toPayPage(){
        ModelAndView model=new ModelAndView("onfirm_pay");
        /*List<Product> list=productService.findAll();
        model.addObject("productList",list);*/
        return model;
    }

    /**
     * 跳转到取餐页面
     * @return
     */
    @RequestMapping("/toAcceptDinnerPage")
    public ModelAndView toAcceptDinnerPage(HttpSession session){
        Integer userId=getUserIdFromSession(session);
        QueryOrdersParam param=new QueryOrdersParam();
        param.setUserId(userId);
        param.setStatus(0);
        param.setIsPayed(1);
        List<Order> orders=clientOrderService.queryOrders(param);
        ModelAndView model=new ModelAndView("qucan");
        model.addObject("orders",orders);
        model.addObject("count",orders.size());
        return  model;
    }
    @RequestMapping("/toShowVOrderCodePage")
    public ModelAndView toShowVOrderCodePage(String orderId){
       ModelAndView model =new ModelAndView("qucai");
       VegetableOrder order=clientVegetableOrderService.get(Integer.parseInt(orderId));
       model.addObject("order",order);
       return model;
    }
    /**
     * 跳转到取菜页面
     * @return
     */
    @RequestMapping("/toAcceptVegetablePage")
    public ModelAndView toAcceptVegetablePage(HttpSession session){
        Integer userId=getUserIdFromSession(session);
        QueryOrdersParam param=new QueryOrdersParam();
        param.setUserId(userId);
        param.setStatus(0);
        param.setIsPayed(1);
        List<VegetableOrder> orders=clientVegetableOrderService.queryOrders(param);
        ModelAndView model=new ModelAndView("qucai");
        model.addObject("orders",orders);
        model.addObject("count",orders.size());
        return  model;
    }
    /**
     * 跳转到订单确认页面
     * @param session
     * @return
     */
    @RequestMapping("/toOrderConfirmPage")
    public ModelAndView toOrderConfirmPage(HttpSession session){
        ModelAndView model=new ModelAndView("order_confirm");
        Map<Integer,CartItem> cart=(Map<Integer,CartItem>)session.getAttribute(CommonParamDefined.CART);
        Integer totalCount=0;
        BigDecimal totalPrice=new BigDecimal(0);

        if(null!=cart&&cart.size()>0){
            model.addObject("isCartNull","1");
            for(Integer key:cart.keySet()){
                totalCount=totalCount+cart.get(key).getCount();
                totalPrice=totalPrice.add(new BigDecimal(cart.get(key).getCount()).multiply(cart.get(key).getPrice()));
            }
            model.addObject("cart",cart);
        }else{
            model.addObject("isCartNull","0");
        }
        model.addObject("totalCount",totalCount);
        model.addObject("totalPrice",totalPrice.doubleValue());

        return model;
    }

    /**
     * 跳转到蔬菜订单确认页面
     * @param session
     * @return
     */
    @RequestMapping("/toVOrderConfirmPage")
    public ModelAndView toVOrderConfirmPage(HttpSession session){
        ModelAndView model=new ModelAndView("vegetable_order_confirm");
        Map<Integer,CartItem> cart=(Map<Integer,CartItem>)session.getAttribute(CommonParamDefined.VEGETABLE_CART);
        Integer totalCount=0;
        BigDecimal totalPrice=new BigDecimal(0);

        if(null!=cart&&cart.size()>0) {
            model.addObject("isCartNull", "1");
            for (Integer key : cart.keySet()) {
                totalCount = totalCount + cart.get(key).getCount();
                totalPrice = totalPrice.add(new BigDecimal(cart.get(key).getCount()).multiply(cart.get(key).getPrice()));
            }
            model.addObject("vegetable_cart",cart);
        }else{
            model.addObject("isCartNull","0");
        }
        model.addObject("totalCount",totalCount);
        model.addObject("totalPrice",totalPrice.doubleValue());
        return model;
    }

    /**
     * 支付订单
     * @param session
     * @param orderId
     * @param orderType 订单类型 0为点餐订单 1为买菜订单
     * @return
     */
    @RequestMapping("/toPayOrderPage")
    public ModelAndView toPayOrderPage(HttpSession session,Integer orderId,Integer orderType){
        ModelAndView model=new ModelAndView("redirect:/user/toIndexPage");
        User user=getUserFromSession(session);
        if (user==null)
            return model;
        if(orderType==0){
            model.setViewName("confirm_pay");
            List<Order> orders=new ArrayList<>();
            Order order=clientOrderService.get(orderId);
            orders.add(order);
            model.addObject("orders", orders);
        }
        if(orderType==1){
            model.setViewName("vegetable_confirm_pay");
            List<VegetableOrder> orders=new ArrayList<>();
            VegetableOrder vegetableOrder=clientVegetableOrderService.get(orderId);
            orders.add(vegetableOrder);
            model.addObject("orders", orders);

        }
        model.addObject("safeAmount", user.getSafeAmount());
        model.addObject("user", user);
        return model;
    }

    /**
     * 提交订单
     * @return 成功失败
     */
    @RequestMapping(value = "/addOrder")
    public String addOrder(HttpSession session,Model model) {
        Map<Integer,CartItem> cart=(Map<Integer,CartItem>)session.getAttribute(CommonParamDefined.CART);
        if (cart==null)
            return "redirect:/product/toDianCanPage";
        Integer userId=(Integer)session.getAttribute(CommonParamDefined.USERID);
        User user=userService.get(userId);
            List<Order> orders = clientOrderService.saveOrder(cart,user);
        session.removeAttribute(CommonParamDefined.CART);
        model.addAttribute("orders", orders);
        model.addAttribute("safeAmount",user.getSafeAmount());
        model.addAttribute("user",user);
        return "confirm_pay";
    }

    /**
     * 创建买菜时的订单
     *             金额冻结
     *             /    \
     *            /      \
     *     金额不足        金额满足
     *     不清除session   清除session
     *     不创建订单       创建订单
     *     跳转到充值页     提示成功
     *
     */
    @SuppressWarnings("unchecked")
    @RequestMapping(value="/addVegetableOrder")
    public String addVegetableOrder(HttpSession session,Model model,String note){
        Map<Integer,CartItem> cart=(Map<Integer,CartItem>)session.getAttribute(CommonParamDefined.VEGETABLE_CART);
        if (cart==null)
            return "redirect:/vegetable/list";
        Integer userId=(Integer)session.getAttribute(CommonParamDefined.USERID);
        User user=userService.get(userId);

        SaveOrderDto dto=clientVegetableOrderService.addVegetableOrder(cart,user,note);
//        if (dto.getRet()==1){
//            //....
//        }else {
//            //清除session
//        }

        session.removeAttribute(CommonParamDefined.VEGETABLE_CART);
//        List<VegetableOrder> orders=new ArrayList<>();
//        orders.add(dto.getOrder());
       // model.addAttribute("orders", orders);
        //model.addAttribute("safeAmount",user.getSafeAmount());
       // model.addAttribute("user",user);
        //return "vegetable_confirm_pay";
        //model.addAttribute("totalPrice",dto.getOrder().getTotalPrice().toString());
        //model.addAttribute("takeDate",dto.getOrder().getTakeDate());
        model.addAttribute("saveOrderDto",dto);
        return "vegetable_order_success";
    }


    /**
     * 选好了，添加到购物车
     * @param param
     * @return
     */
    @RequestMapping(value = "/addToCart", method = RequestMethod.POST, produces = "application/json;charset=UTF-8", consumes = "application/json")
    public
    @ResponseBody
    AbstractJsonRes addToCart(@RequestBody List<AddToCartReq> param ,HttpSession session) {
        Map<Integer,CartItem> cart=(Map<Integer,CartItem>)session.getAttribute(CommonParamDefined.CART);

        if(cart==null||cart.size()==0){
            cart=new HashMap<Integer,CartItem>();
            for(AddToCartReq req:param){
                addItemToChart(req,cart);
            }
        }else {
            for (AddToCartReq aParam : param) {
                CartItem cartItem =cart.get(aParam.getId());
                if (cartItem==null){
                    addItemToChart(aParam, cart);
                }else {
                    cartItem.setCount(aParam.getCount()+cartItem.getCount());
                }
            }
        }
        //更新session里面购物车
        session.setAttribute(CommonParamDefined.CART, cart);
        return new SuccessJsonRes();
    }

    /**
     * 选好了，添加到蔬菜购物车
     * @param param
     * @return
     */
    @RequestMapping(value = "/addToVegetableCart", method = RequestMethod.POST, produces = "application/json;charset=UTF-8", consumes = "application/json")
    public
    @ResponseBody
    AbstractJsonRes addToVegetableCart(@RequestBody List<AddToCartReq> param ,HttpSession session) {
        Map<Integer,CartItem> cart=(Map<Integer,CartItem>)session.getAttribute(CommonParamDefined.VEGETABLE_CART);

        if(cart==null||cart.size()==0){
            cart=new HashMap<Integer,CartItem>();
            for(AddToCartReq req:param){
                addItemToVegetableChart(req, cart);
            }
        }else {
            for (AddToCartReq aParam : param) {
                CartItem cartItem =cart.get(aParam.getId());
                if (cartItem==null){
                    addItemToVegetableChart(aParam, cart);
                }else {
                    cartItem.setCount(aParam.getCount()+cartItem.getCount());
                }
            }
        }
        //更新session里面购物车
        session.setAttribute(CommonParamDefined.VEGETABLE_CART, cart);
        return new SuccessJsonRes();
    }

   /**
     * //向购物车里面添加商品
     * @param req 请求项
     * @param cart 购物车
     */
    public void addItemToChart(AddToCartReq req,Map<Integer,CartItem> cart){
        CartItem cartItem=new CartItem();
        Product product=productService.get(req.getId());
        cartItem.setId(req.getId());
        cartItem.setCount(req.getCount());
        cartItem.setName(product.getName());
        cartItem.setPrice(req.getPrice());
        cartItem.setImg(product.getImg());
        cartItem.setProductDetail(product.getDescription());
        cartItem.setAcceptTime(product.getCreatedTime());
        cartItem.setTimeNode(product.getTimeNode());
        cart.put(req.getId(), cartItem);
    }

    /**
     * //向购物车里面添加商品
     * @param req 请求项
     * @param cart 购物车
     */
    public void addItemToVegetableChart(AddToCartReq req,Map<Integer,CartItem> cart){
        CartItem cartItem=new CartItem();
        Vegetable product=vegetableService.get(req.getId());
        cartItem.setId(req.getId());
        cartItem.setCount(req.getCount());
        cartItem.setName(product.getVegetableName());
        cartItem.setPrice(req.getPrice());
        cartItem.setUnitName(product.getUnitName());
        cartItem.setImg(product.getImg());
        cart.put(req.getId(), cartItem);
    }

    /**
     * 修改购物车商品数量
     * @param param
     * @return
     */
    @RequestMapping(value = "/changeCart", method = RequestMethod.POST, produces = "application/json;charset=UTF-8", consumes = "application/json")
    public
    @ResponseBody
    AbstractJsonRes changeCart(@RequestBody AddToCartReq param ,HttpSession session) {
        Map<Integer,CartItem> cart=(Map<Integer,CartItem>)session.getAttribute(CommonParamDefined.CART);
        CartItem item=cart.get(param.getId());
        if(param.getCount()>0){
            item.setCount(param.getCount());
        }else{
            cart.remove(param.getId());
        }
        session.setAttribute(CommonParamDefined.CART,cart);
        ChangeCartRes result=new ChangeCartRes();
        if(item!=null){
            result.setCount(item.getCount());
        }else {
            result.setCount(0);
        }
        return result;
    }

    /**
     * 修改蔬菜购物车商品数量
     * @param param
     * @return
     */
    @RequestMapping(value = "/changeVegetableCart", method = RequestMethod.POST, produces = "application/json;charset=UTF-8", consumes = "application/json")
    public
    @ResponseBody
    AbstractJsonRes changeVegetableCart(@RequestBody AddToCartReq param ,HttpSession session) {
        Map<Integer,CartItem> cart=(Map<Integer,CartItem>)session.getAttribute(CommonParamDefined.VEGETABLE_CART);
        CartItem item=cart.get(param.getId());
        if(param.getCount()>0){
            item.setCount(param.getCount());
        }else{
            cart.remove(param.getId());
        }
        session.setAttribute(CommonParamDefined.VEGETABLE_CART,cart);
        ChangeCartRes result=new ChangeCartRes();
        if(item!=null){
            result.setCount(item.getCount());
        }else {
            result.setCount(0);
        }
        return result;
    }

    /**
     * 查看订单列表
     * @param orderStatus 订单状态
     * @param session
     * @return
     */
    @RequestMapping("/queryOrders")
    public ModelAndView queryOrders(Integer orderStatus,HttpSession session,String orderType){
        ModelAndView model=null;
        if(null==orderStatus||"".equals(orderStatus)){
            orderStatus=0;
        }
        Integer userId=(Integer)session.getAttribute(CommonParamDefined.USERID);
        QueryOrdersParam param=new QueryOrdersParam();
        param.setUserId(userId);
        param.setStatus(orderStatus);
        if("0".equals(orderType)) {
            model=new ModelAndView("orders");
            //List<Order> orders=clientOrderService.queryOrders(param);
            //model.addObject("orders", orders);
        }
        if("1".equals(orderType)){
            model=new ModelAndView("vegetable_orders");
           // List<VegetableOrder> orders=clientVegetableOrderService.queryOrders(param);
           // model.addObject("orders", orders);
        }
        return model;
    }

    /**
     * 撤销订单
     * @param orderId
     * @param session
     * @return
     */
    @RequestMapping("/deleteOrder")
    @ResponseBody
    public AbstractJsonRes deleteOrder(Integer orderId,HttpSession session,String orderType){
        User user=clientUserService.get((Integer)(session.getAttribute(CommonParamDefined.USERID)));
        if("0".equals(orderType)){
            Order order=clientOrderService.get(orderId);
            if(order.getStatus()==0){
                if(order.getIsPayed()==0){
                    clientOrderService.changeOrderStatus(orderId,3,null);
                    return new SuccessJsonRes();
                }else {
                    //todo
                    BigDecimal totalPrice=order.getTotalPrice();
                    BigDecimal qBalance=user.getQingcaiBalance();
                    qBalance=qBalance.add(totalPrice);
                    user.setQingcaiBalance(qBalance);
                    clientUserService.saveOrUpdate(user);
                    clientOrderService.changeOrderStatus(orderId,3,3);
                    return new SuccessJsonRes();
                }
            }
        }
        if("1".equals(orderType)){
            VegetableOrder vegetableOrder=clientVegetableOrderService.get(orderId);
            if(vegetableOrder.getStatus()==0){
                clientVegetableOrderService.changeOrderStatus(orderId,3,null);
                return new SuccessJsonRes();

            }
        }
        return new ErrorJsonRes(CodeDefined.ORDER_CAN_NOT_CANCELED,"订单不可撤销");
    }

    /**
     * 支付订单
     * @param orderIds  订单号集合 用逗号分隔开
     * @param payType   账号类型 食堂，平台
     * @param payPwd   支付密码
     */
    @RequestMapping("/payOrder")
    @ResponseBody
    public AbstractJsonRes payOrder(String orderIds,int payType,String payPwd,HttpSession session) {
//        User user = getUserFromSession(session);
//        if (user==null)
//            return new ErrorJsonRes(CodeDefined.USER_INFO_NULL);
        ConsumeHistory.AccountType accountType = ConsumeHistory.AccountType.getAccount(payType);
        if (accountType == null)
            return new ErrorJsonRes(CodeDefined.ACCOUNT_USER_PAYTYPE_WRONG);
        try {
            int code = clientOrderService.payOrder((int)session.getAttribute(CommonParamDefined.USERID),accountType, StringUtils.parseToIntArrayBycomma(orderIds),payPwd);
            if (code==0)
                return new SuccessJsonRes();
            return new ErrorJsonRes(code);
        } catch (Exception e) {
            return new ErrorJsonRes(CodeDefined.COMMON_SERVICE_ERROR);
        }

    }

    @RequestMapping("/getOrderPojos")
    @ResponseBody
    public AbstractJsonRes getOrderPojos(Integer isPayed,Integer status,HttpSession session,String orderType){
        QueryOrdersParam param=new QueryOrdersParam();
        param.setUserId((Integer)session.getAttribute(CommonParamDefined.USERID));
        param.setStatus(status);

        List<OrderPojo> list=null;
        if("0".equals(orderType)){
            param.setIsPayed(isPayed);
            list=clientOrderService.listOrders(param);
        }
        if("1".equals(orderType)){
            param.setIsPayed(null);
            list=clientVegetableOrderService.listOrders(param);
        }
        ListSucRes res=new ListSucRes();
        res.setData(list);
        return  res;
    }
    @RequestMapping("/getOrderItems")
    @ResponseBody
    public AbstractJsonRes getOrderItems(Integer orderId,String orderType){
        List<OrderItemPojo> items=null;
        if("0".equals(orderType)){
            items=orderItemService.getOrderItemsByOrderId(orderId);
        }
       if("1".equals(orderType)){
           items=vegetableOrderItemService.getOrderItemsByOrderId(orderId);
       }
        ListSucRes res=new ListSucRes();
        res.setData(items);
        return res;
    }

    /**
     * 检查订单是否确认
     * @param orderId
     * @param session
     * @return
     */
    @RequestMapping("/vegetable/checkConfirm")
    @ResponseBody
    public AbstractJsonRes checkConfirm(int orderId,HttpSession session){

        VegetableOrder order = clientVegetableOrderService.query("FROM VegetableOrder o WHERE o.id=? AND o.user.id=? AND o.status=1",
                        new Object[]{orderId,session.getAttribute(CommonParamDefined.USERID)});
        if (order==null){
            return new ErrorJsonRes(4,"查询不到相应的订单");
        }else {
            VegCheckConfirmRes res = new VegCheckConfirmRes();
            res.setSafeAmount(order.getUser().getSafeAmount());
            res.setTotalPrice(order.getTotalPrice());
            return res;
        }
    }

    /**
     * 确认订单
     * @param orderId
     * @param session
     * @return
     */
    @RequestMapping("/vegetable/confirm")
    @ResponseBody
    public AbstractJsonRes vegetableConfirm(int orderId,String pwd,HttpSession session){
        int code = clientVegetableOrderService.confirmOrder((int)session.getAttribute(CommonParamDefined.USERID),orderId,pwd);
        if (code==0)
            return new SuccessJsonRes();
        else
            return new ErrorJsonRes(code);
    }

    private User getUserFromSession(HttpSession session){
        return userService.get(getUserIdFromSession(session));
    }
    private Integer getUserIdFromSession(HttpSession session){
        return (Integer)session.getAttribute(CommonParamDefined.USERID);
    }
}
