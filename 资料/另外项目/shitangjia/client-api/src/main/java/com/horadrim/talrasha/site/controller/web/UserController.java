package com.horadrim.talrasha.site.controller.web;


import com.horadrim.talrasha.common.CodeDefined;
import com.horadrim.talrasha.common.CommonParamDefined;
import com.horadrim.talrasha.common.model.*;
import com.horadrim.talrasha.common.model.Collection;
import com.horadrim.talrasha.common.response.json.AbstractJsonRes;
import com.horadrim.talrasha.common.response.json.ErrorJsonRes;
import com.horadrim.talrasha.common.response.json.GetObjSucRes;
import com.horadrim.talrasha.common.response.json.SuccessJsonRes;
import com.horadrim.talrasha.common.service.CanteenModuleService;
import com.horadrim.talrasha.common.service.CanteenService;
import com.horadrim.talrasha.common.service.TixianService;
import com.horadrim.talrasha.common.service.WechatRechargeLogService;
import com.horadrim.talrasha.common.util.BaeSmsUtil;
import com.horadrim.talrasha.common.util.CheckCodeUtil;
import com.horadrim.talrasha.common.util.StringUtils;
import com.horadrim.talrasha.site.exception.DefaultExceptionHandler;
import com.horadrim.talrasha.site.service.ClientCollectionService;
import com.horadrim.talrasha.site.service.ClientProductService;
import com.horadrim.talrasha.site.service.ClientUserService;
import com.horadrim.talrasha.site.utils.OrderUtil;
import com.horadrim.talrasha.site.wechat.WeChat;
import com.horadrim.talrasha.site.wechat.bean.UserInfo;
import com.horadrim.talrasha.site.wechat.oauth.Pay;
import com.horadrim.talrasha.site.wechat.util.ConfKit;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.RandomStringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.util.*;

/**
 * Created by Administrator
 * 用户控制
 */
@Controller
@RequestMapping("/user")
public class UserController extends DefaultExceptionHandler {

   /* @Autowired
    private UserService userService;*/
    @Resource(name = "clientUserService")
    private ClientUserService userService;

    @Resource(name = "canteenService")
    private CanteenService canteenService;
    @Resource(name="clientProductService")
    private ClientProductService clientProductService;
    @Resource(name="clientCollectionService")
    private ClientCollectionService clientCollectionService;
    @Resource
    private WechatRechargeLogService wechatRechargeLogService;
    @Resource
    private CanteenModuleService canteenModuleService;
    @Resource
    private TixianService tixianService;
    /**
     *注册获取验证码
     * @param phone 手机号
     */
    @RequestMapping("/getSMSCode")
    @ResponseBody
    public AbstractJsonRes getSMSCode(String phone,HttpSession session) {
        if(StringUtils.isBlank(phone)){
            return new ErrorJsonRes(CodeDefined.ACCOUNT_USER_INPUT_NULL);
        }
        if(!StringUtils.validatePhone(phone)){
            return new ErrorJsonRes(CodeDefined.ACCOUNT_USER_PHONE_IS_WRONG);
        }
        String code=new CheckCodeUtil().generateRandomNumberCode();
        session.setAttribute(CommonParamDefined.SMS_CHECKCODE,code);
//        session.setAttribute(CommonParamDefined.CART,new Cart());
        //发送短信
        Map<String,String> map = new HashMap<>();
        map.put("code", code);
        BaeSmsUtil.send(BaeSmsUtil.CHECK_CODE_02, map, phone);
        return new SuccessJsonRes();
    }

    /**
     * 注册用户
     * @param canteenId 餐厅id
     * @param phone    电话
     * @param smsCode  短信验证码
     */
    @RequestMapping("/register")
    @ResponseBody
    public AbstractJsonRes register(String canteenId,String phone,String smsCode,HttpSession session) {
        String sessionSmsCode=(String)session.getAttribute(CommonParamDefined.SMS_CHECKCODE);
        if(StringUtils.isBlank(smsCode)){
            return new ErrorJsonRes(CodeDefined.ACCOUNT_USER_CHECKCODE_BLANK);
        }
        if(!smsCode.equals(sessionSmsCode)){
            return new ErrorJsonRes(CodeDefined.ACCOUNT_USER_CHECKCODE_ERROR);
        }
        if (StringUtils.isBlank(canteenId)||StringUtils.isBlank(phone))
            return new ErrorJsonRes(CodeDefined.COMMON_PARAMS_ERROR);
        //释放验证码
        session.removeAttribute(CommonParamDefined.SMS_CHECKCODE);
        if(userService.countUserByPhone(phone)){
            return new ErrorJsonRes(CodeDefined.ACCOUNT_USER_HAVE_BEEN_REGISTED);
        }
        UserInfo wechatUser = null ;
        try {
            wechatUser = new com.horadrim.talrasha.site.wechat.oauth.User().getUserInfo(WeChat.ACCESS_TOKEN, (String) session.getAttribute(CommonParamDefined.OPENID));
        } catch (Exception e) {
            e.printStackTrace();
        }
        

        User user=new User();
        user.setCanteenId(Integer.parseInt(canteenId));
        user.setPhone(phone);
        user.setOpenId((String) session.getAttribute(CommonParamDefined.OPENID));
//      user.setOpenId("12345");
        if (wechatUser!=null){
            user.setNickName(wechatUser.getNickname());
            user.setSex(wechatUser.getSex());
            if(!StringUtils.isBlank(wechatUser.getHeadimgurl()))
                user.setHeadImg(wechatUser.getHeadimgurl());
            else
                user.setHeadImg(getDefaultHeadImg(wechatUser.getSex()));
        }else {
            user.setSex(0);
            user.setHeadImg(getDefaultHeadImg(0));
            user.setNickName("未知");
        }
        user.setQingcaiBalance(new BigDecimal(0));
        user.setCanteenBalance(new BigDecimal(0));
        user.setIsWithoutPwd(0);
        userService.save(user);
        session.setAttribute(CommonParamDefined.USERID, user.getId());
        session.setAttribute(CommonParamDefined.CANTEEN_ID,user.getCanteenId());
        return new SuccessJsonRes();
    }
    //根据性别设置默认头像
    private String getDefaultHeadImg(int sex){
        Random random = new Random();
        String str = "http://qingcai-images.bj.bcebos.com/default/";
        switch (sex){
            case 1:
                str+="m0";
                break;
            case 2:
                str+="n0";
                break;
            default:
                str += (random.nextInt(2) == 0 ? "m0" : "n0");
                break;
        }
        str += (random.nextInt(7))+".gif";
        return str;
    }

    /**
     * 修改支付密码
     * @param payPwd  支付密码
     * @param smsCode 短信验证码
     * @return 修改成功或失败
     */
    @RequestMapping("/updatePayPwd")
    @ResponseBody
    public AbstractJsonRes updatePayPwd (String payPwd,String smsCode,HttpSession session){
        if (StringUtils.isBlank(smsCode)||StringUtils.isBlank(payPwd)||payPwd.length()>20||payPwd.length()<4)
            return new ErrorJsonRes(CodeDefined.COMMON_PARAMS_ERROR);
        if (!smsCode.equals(session.getAttribute(CommonParamDefined.SMS_CHECKCODE)))
            return new ErrorJsonRes(CodeDefined.ACCOUNT_USER_CHECKCODE_ERROR);
        User user = userService.get((int)session.getAttribute(CommonParamDefined.USERID));
        if (user==null)
            return new ErrorJsonRes(CodeDefined.USER_INFO_NULL);
        user.setPayPassword(DigestUtils.md5Hex(payPwd));
        userService.update(user);
        return new SuccessJsonRes();
    }

    /**
     * 判断是否存在密码
     * @param session
     * @return
     */
    @RequestMapping("/isHavingPwd")
    @ResponseBody
    public AbstractJsonRes isHavingPwd (HttpSession session){
        Integer userId=(Integer)session.getAttribute(CommonParamDefined.USERID);
        User user=userService.get(userId);
        String pwd=user.getPayPassword();
        if(null==pwd||"".equals(pwd)){
//            CheckIsHavIngPwdRes checkIsHavIngPwdRes=new CheckIsHavIngPwdRes();
            /*checkIsHavIngPwdRes.setPhone(user.getPhone());
            return checkIsHavIngPwdRes;*/
            return new ErrorJsonRes(CodeDefined.ACCOUNT_USER_PAYPWD_ISNOTSET,"未设置支付密码");
        }
        return new SuccessJsonRes();

    }

//    /**
//     * 订单支付
//     * @param orderId  订单id
//     * @param payType  支付方式
//     * @param payPwd   支付密码
//     */
//    @RequestMapping("/payOrder")
//    @ResponseBody
//    public AbstractJsonRes payOrder(String orderIds,int payType,String payPwd,HttpSession session){
////        Order order = clientOderService.get(orderId);
////        if (order==null){
////            return new ErrorJsonRes(CodeDefined.ORDER_INFO_NULL);
////        }
//        ClientUserServiceImpl.PayType type = null;
//        switch (payType){
//            case 0:
//                type = ClientUserServiceImpl.PayType.QINGCAIBALANCE;
//                break;
//            case 1:
//                type = ClientUserServiceImpl.PayType.CANTEENBALANCE;
//        }
//        if (type == null)
//            return new ErrorJsonRes(CodeDefined.ACCOUNT_USER_PAYTYPE_WRONG);
//        int[] orderIdArray=StringUtils.parseToIntArrayBycomma(orderIds);
//        for (int orderId : orderIdArray){
//            Order order = clientOderService.get(orderId);
//            if (order==null){
//                return new ErrorJsonRes(CodeDefined.ORDER_INFO_NULL);
//            }
//        }
//        return new ErrorJsonRes(userService.payment((int)session.getAttribute(CommonParamDefined.USERID),
//                order.getTotalPrice(),type,payPwd,orderId));
//
//    }
    @RequestMapping("/recharge")
    @ResponseBody
    public AbstractJsonRes wechatRecharge(Double totalFee,Double realFee,HttpSession session,HttpServletRequest request,Model model) throws Exception {
        System.out.println("totalFee   "+totalFee);
//        if (totalFee==null)
//            return "redirect:500.html";
//        return "";
        if (WeChat.isWeiXin(request)) {
            String orderNum = OrderUtil.GetOrderNumber("");
            Map<String, String> params = new HashMap<>();
            String paternerKey = ConfKit.get("partnerKey");
            params.put("nonce_str", RandomStringUtils.random(8, "123456789"));
            params.put("body", "微信充值");
            params.put("out_trade_no",orderNum);
            params.put("total_fee", (int)(totalFee*100)+"");
            params.put("spbill_create_ip", request.getRemoteAddr());
            params.put("trade_type", "JSAPI");
            params.put("openid", (String) session.getAttribute(CommonParamDefined.OPENID));
            //参数打包
            String sign = Pay.getPackage(params, paternerKey);
            params.put("sign", sign);
            //获取prePayId
            String prePayId = Pay.getPrePayId(params);
            if (prePayId != null) {
                Map<String, String> jsParam = Pay.paySign(RandomStringUtils.random(8, "123456789"), "prepay_id=" + prePayId, paternerKey);
              //  model.addAttribute("jsParam", jsParam);
//           accesstoken换取ticket
//            String ticket = WeChat.getTicket(WeChat.getAccessToken());
//                System.out.println("ticket==========="+ticket);
//            Map<String,String> mxConfig = WeChat.jsApiSign(ticket, "http://qingcaitest.duapp.com/user/recharge");
//            model.addAttribute("mxConfig",mxConfig);
//                System.out.println("nonceStr"+mxConfig.get("nonceStr")+"  "+"timestamp"+mxConfig.get("timestamp")+"  signature"+mxConfig.get("signature"));
                //保存充值日志
                WechatRechargeLog log = new WechatRechargeLog();
                log.setIsConfirm(0);
                log.setOpenId((String) session.getAttribute(CommonParamDefined.OPENID));
                log.setOrderNum(orderNum);
                log.setTransactionId("");
                log.setTotalFee(new BigDecimal(totalFee));
                log.setSaveTime(new Date());
                log.setUserId((Integer) session.getAttribute(CommonParamDefined.USERID));
                log.setRealFee(new BigDecimal(realFee));
                wechatRechargeLogService.save(log);
                GetObjSucRes res = new GetObjSucRes();
                res.setData(jsParam);
                return res;
            }
//            return "redirect:500.html";
            return null;
        }else {
            //TODO 不是来自微信5.0后的版本，应该进入升级提示
//            return "index";
            return null;
        }

    }
    @RequestMapping("/recharge/test2")
    public String wechatRechargetest(HttpSession session,Model model) throws Exception {
        User user = userService.get((Integer) session.getAttribute(CommonParamDefined.USERID));
        if(user==null)
            return "register";
        model.addAttribute("account",user.getQingcaiBalance());
        return "recharge";

    }
    /**
     * 跳转到充值页面
     * 这个目录用不起....
     * 只能用/recharge/test2
     */
    @RequestMapping("/recharge/toRechargePage")
    public String toRechargePage(HttpSession session,Model model) throws Exception {
        User user = userService.get((Integer) session.getAttribute(CommonParamDefined.USERID));
        if(user==null)
            return "register";
        //           accesstoken换取ticket
//        String ticket = WeChat.getTicket(WeChat.getAccessToken());
//        System.out.println("ticket==========="+ticket);
//        String url = request.getScheme()+"://"+request.getServerName()+request.getRequestURI();
//        Map<String,String> mxConfig = WeChat.jsApiSign(ticket, url);
//        model.addAttribute("mxConfig",mxConfig);
//        System.out.println("nonceStr"+mxConfig.get("nonceStr")+"  "+"timestamp"+mxConfig.get("timestamp")+"  signature"+mxConfig.get("signature")+" url "+url);

        model.addAttribute("account",user.getQingcaiBalance());
        return "recharge";
    }

    public User getUserFromSession(HttpSession session){
        Integer userId = (Integer)session.getAttribute(CommonParamDefined.USERID);
        if (userId==null)
            return null;
        return userService.get(getUserIdFromSession(session));
    }
    public Integer getUserIdFromSession(HttpSession session){
        return (Integer)session.getAttribute(CommonParamDefined.USERID);
    }

    @RequestMapping("/addToCollection")
    public AbstractJsonRes addToCollection(Integer productId,HttpSession session){
        User user=getUserFromSession(session);
        Product product=clientProductService.get(productId);
        Collection collection=new Collection();
        collection.setUser(user);
        collection.setProduct(product);
        clientCollectionService.save(collection);
        return new SuccessJsonRes();
    }

    /**
     * 跳转注册页面
     */
    @RequestMapping("/toRegPage")
    public ModelAndView toRegPage(){
        ModelAndView modelAndView=new ModelAndView("register");
        List<Canteen> list=canteenService.findAll();
        modelAndView.addObject("canteenList",list);
        return modelAndView;
    }
    @RequestMapping("/toPersonCenterPage")
    public ModelAndView toPersonCenterPage(HttpSession session){
        ModelAndView modelAndView=new ModelAndView("person_center");
        Integer userId=(Integer)session.getAttribute(CommonParamDefined.USERID);
        User user=userService.get(userId);
        modelAndView.addObject("user", user);
        return modelAndView;
    }

    /**
     * 跳到首页
     */
    @RequestMapping("/toIndexPage")
    public ModelAndView toIndexPage(HttpSession session ,HttpServletRequest req){
        //session.setAttribute(CommonParamDefined.CANTEEN_ID,1);
        //session.setAttribute(CommonParamDefined.USERID,2);
        //测试用
        Integer userId=(Integer)session.getAttribute(CommonParamDefined.USERID);
//        if(null==userId){
//            session.setAttribute(CommonParamDefined.CANTEEN_ID,1);
//            session.setAttribute(CommonParamDefined.USERID,3);
//        }
        User user = userService.get(userId);
        session.setAttribute(CommonParamDefined.CANTEEN_MODULE,
                canteenModuleService.getByCanteenId(user.getCanteenId()));
        session.setAttribute("canteenName", canteenService.get(user.getCanteenId()).getCanteenName());
        return  new ModelAndView("index");
    }

    /**
     * 跳到首页
     */
    @RequestMapping("/toTestIndexPage")
    public ModelAndView toTestIndexPage(HttpSession session ,HttpServletRequest req){
        //session.setAttribute(CommonParamDefined.CANTEEN_ID,1);
        //session.setAttribute(CommonParamDefined.USERID,2);
        //测试用
        Integer userId=(Integer)session.getAttribute(CommonParamDefined.USERID);
        if(null==userId){
            userId=3;
            session.setAttribute(CommonParamDefined.CANTEEN_ID,1);
            session.setAttribute(CommonParamDefined.USERID,userId);
            session.setAttribute(CommonParamDefined.TEST,"1");
        }
        User user = userService.get(userId);
        session.setAttribute(CommonParamDefined.CANTEEN_MODULE,
                canteenModuleService.getByCanteenId(user.getCanteenId()));
        session.setAttribute("canteenName", canteenService.get(user.getCanteenId()).getCanteenName());
        return  new ModelAndView("index");
    }

    /**
     * 跳转到查看余额页面
     */
    @RequestMapping("/toBalancePage")
    public String toShowBalancePage(HttpSession session,Model model){
        User user = getUserFromSession(session);
//        User user = userService.get(1);
        if (user==null)
            return "404.html";
        model.addAttribute("qingcaiBalance", user.getQingcaiBalance());
        model.addAttribute("shitangBalance", user.getCanteenBalance());
        return "balance";
    }


    /**
     * 跳转到提现页面
     * @param session
     * @param model
     * @return
     */
    @RequestMapping("/toTixianPage")
    public String toTixianPage(HttpSession session,Model model){
        User user = userService.get((int)session.getAttribute(CommonParamDefined.USERID));
        if (user==null) {
            return "register";
        }
        model.addAttribute("phone",user.getPhone()==null?"":user.getPhone());
        return "tixian";
    }

    @RequestMapping("/tixian")
    @ResponseBody
    public AbstractJsonRes tixian(@Validated Tixian tixian, HttpSession session,BindingResult result){
        if (result.hasErrors())
            return new ErrorJsonRes(CodeDefined.COMMON_PARAMS_ERROR);
       tixian.setTixianTime(new Date());
        tixian.setUserId((int) session.getAttribute(CommonParamDefined.USERID));
        tixianService.save(tixian);
        return new SuccessJsonRes();
    }
    /**
     * 跳转到个人信息页面
     */
    @RequestMapping("/toPersonInfoPage")
    public ModelAndView toPersonInfoPage(HttpSession session){
        ModelAndView model =new ModelAndView("person_info");
        User user=getUserFromSession(session);
        Canteen canteen=canteenService.get(user.getCanteenId());
        model.addObject("user",user);
        model.addObject("canteen",canteen);
        return  model;
    }
  @RequestMapping("/toZhangHuPage")
   public ModelAndView toZhangHuPage(){
      ModelAndView model=new ModelAndView("zhanghu");
     // model.addObject("safeAmount", user.getSafeAmount());
      return model;
   }
    @RequestMapping("/toPaySetPage")
    public ModelAndView toPaySetPage(HttpSession session){
        User user=getUserFromSession(session);
        ModelAndView model=new ModelAndView("pay_set");
        float safeAmount =user.getSafeAmount();
        String pwd=user.getPayPassword();
        String  isHavPwd="0";
        if(pwd==null||"".equals(pwd)){
            isHavPwd="1";
        }
        model.addObject("safeAmount",safeAmount);
        model.addObject("isHavPwd",isHavPwd);
        model.addObject("phone",user.getPhone());
        return model;
    }
    @RequestMapping("/changeSafeAmount")
    @ResponseBody
    public AbstractJsonRes changeSafeAmount(HttpSession session,Float safeAmount){
        User user=getUserFromSession(session);
        user.setSafeAmount(safeAmount);
        userService.saveOrUpdate(user);
        return new SuccessJsonRes();
    }

    @RequestMapping("/updateUserInfo")
    @ResponseBody
    public AbstractJsonRes updateUserInfo(HttpSession session,String infoName,String infoValue){
        User user=getUserFromSession(session);
        if(infoName.equals("name")){
        user.setNickName(infoValue);
        }
        if(infoName.equals("phone"))
        {
            user.setPhone(infoValue);
        }
        if(infoName.equals("email"))
        {
            user.setEmail(infoValue);
        }
        if(infoName.equals("qq"))
        {
            user.setQq(infoValue);
        }
        if(infoName.equals("sex")){
            user.setSex(Integer.parseInt(infoValue));
        }
        userService.saveOrUpdate(user);
        return  new SuccessJsonRes();
    }

    @RequestMapping("/toWenJuanPage")
    public ModelAndView toWenJuanPage(){
        ModelAndView model=new ModelAndView("wenjuan");
        return model;
    }

}
