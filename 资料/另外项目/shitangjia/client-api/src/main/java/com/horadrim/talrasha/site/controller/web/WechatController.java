package com.horadrim.talrasha.site.controller.web;


import com.alibaba.fastjson.JSONObject;
import com.horadrim.talrasha.common.CommonParamDefined;
import com.horadrim.talrasha.common.model.ConsumeHistory;
import com.horadrim.talrasha.common.model.User;
import com.horadrim.talrasha.common.model.WechatRechargeLog;
import com.horadrim.talrasha.common.service.ConsumeHistoryService;
import com.horadrim.talrasha.common.service.UserService;
import com.horadrim.talrasha.common.service.WechatRechargeLogService;
import com.horadrim.talrasha.common.util.SplitDateUtil;
import com.horadrim.talrasha.common.util.StringUtils;
import com.horadrim.talrasha.site.wechat.WeChat;
import com.horadrim.talrasha.site.wechat.bean.WeChatBuyPost;
import com.horadrim.talrasha.site.wechat.oauth.Oauth;
import com.horadrim.talrasha.site.wechat.util.Tools;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;
import java.util.Map;

/**
 * Created by Administrator on 2015/5/28.
 */
@Controller
@RequestMapping("/wechat")
public class WechatController {
    private static final Logger logger = LoggerFactory.getLogger(WechatController.class);
    // 微信返回  fail 失败，success 成功
    private static final String STATUC_SUCCESS = "SUCCESS";
    private static final String STATUC_FAIL    = "FAIL";
    @Resource
    private WechatRechargeLogService wechatRechargeLogService;
    @Resource
    private ConsumeHistoryService consumeHistoryService;
    @Resource
    private UserService userService;
    /**
     * 微信接入验证
     */
    @RequestMapping(value = "/auth",method = RequestMethod.GET)
    @ResponseBody
    public String checkAuth(String signature,String timestamp ,String nonce ,String echostr){
        System.out.println("in get ");
        if (StringUtils.isBlank(signature)||StringUtils.isBlank(timestamp)||StringUtils.isBlank(nonce)||
                StringUtils.isBlank(echostr))
            return null;
        if (WeChat.checkSignature("qingcaikeji", signature, timestamp, nonce)){
            System.out.println("教研成功");
            return echostr;
        }
        System.out.println("教研失败");
        return null;
    }

    @RequestMapping(value = "/auth",method = RequestMethod.POST)
    @ResponseBody
    public String checkAuth(HttpServletRequest request) throws IOException {
        InputStream stream = request.getInputStream();

        String str = Tools.inputStream2String(stream);
      //  System.out.println("in post ---"+str);
//        System.out.println(str);
        String res = WeChat.processing(str);
     //   System.out.println("in post res "+res);
        return res;
    }

    /**
     * 取得openid放入session
     * @param session
     * @param code
     * @throws Exception
     */
    @RequestMapping("/getOpenId")
    public String getOpenId(HttpSession session,String code,String localRedirectUrl) throws Exception {
        System.out.println("in getOpenId  "+code+"  "+localRedirectUrl);
        if (StringUtils.isBlank(code))
            return "redirect:500.html";
        //code 换取access token
        String str = new Oauth().getToken(code);
        if (StringUtils.isBlank(str))
            return "redirect:500.html";
        Map<String,Object> map = JSONObject.parseObject(str);
        Object openId = map.get("openid");
        if(openId!=null){
            session.setAttribute(CommonParamDefined.OPENID,openId);
            if (!StringUtils.isBlank(localRedirectUrl))
                return "redirect:"+localRedirectUrl;
            return "index";
        }else {
            return "redirect:500.html";
        }

    }

    /**
     * 封装回调地址 同时页面指向认证页面
     * @throws java.io.UnsupportedEncodingException
     */
    @RequestMapping("/toAuthPage")
    public ModelAndView toAuthPage(String uri) throws UnsupportedEncodingException {
        //封装回调地址
        ModelAndView mav = new ModelAndView("/wechatAuth");
//        String redirect_uri = CommonParamDefined.CALLBACKURL+"?redirect="+uri;
//        String url = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=" + ConfKit.get("AppId")
//                + "&redirect_uri=" + URLEncoder.encode(redirect_uri,"UTF-8") + "&response_type=code&" +
//                "scope=snsapi_base&state=123#wechat_redirect";
//        System.out.println("toAuthPage "+url);
        String url=new Oauth().getCode(uri);
        mav.addObject("url",url );
        System.out.println("toAuthPage*****"+url);
        return mav;
    }
    @RequestMapping("/pay/notify")
    @ResponseBody
    public String payNotify(HttpServletRequest request,HttpServletResponse response){
    // post 过来的xml
        WeChatBuyPost postData ;
//        String           openid   = null;
//        String       appsignature = null;
        try {
            ServletInputStream in = request.getInputStream();
            // 转换微信post过来的xml内容
            XStream xs = new XStream(new DomDriver());
            xs.alias("xml", WeChatBuyPost.class);
            String xmlMsg = Tools.inputStream2String(in);
            System.out.println("pay notify ----\n"+xmlMsg);
            postData = (WeChatBuyPost) xs.fromXML(xmlMsg);
//            logger.info("postData =====  "+postData.toString());

            if ("SUCCESS".equalsIgnoreCase(postData.getResult_code())){
                WechatRechargeLog log = wechatRechargeLogService.getLog(postData.getOut_trade_no());
                if (log!=null&&log.getIsConfirm()!=1) {
                    //更新总价
                    BigDecimal totalFee = new BigDecimal(postData.getTotal_fee());
                    System.out.println("pay  totalfee "+ totalFee);
                    totalFee = totalFee.divide(new BigDecimal(100),2, RoundingMode.HALF_UP);
                    log.setIsConfirm(1);
                    log.setTransactionId(postData.getTransaction_id());

                    wechatRechargeLogService.update(log);
                    //更新用户的平台账号余额
                    User user = userService.get(log.getUserId());
                    user.setQingcaiBalance(user.getQingcaiBalance().add(log.getRealFee()));
                    userService.update(user);
                    ConsumeHistory consumeHistory = new ConsumeHistory();
                    consumeHistory.setUserId(log.getUserId());
                    consumeHistory.setBalance(totalFee);
                    consumeHistory.setConsumeType(ConsumeHistory.ConsumeType.CHONGZHI);
                    consumeHistory.setAccountType(ConsumeHistory.AccountType.PINGTAI);
                    Date date = new Date();
                    consumeHistory.setConsumeTime(date);
                    SplitDateUtil.setYMD(consumeHistory,date);
                    consumeHistoryService.save(consumeHistory);

                }
                String sucXml = setXML(STATUC_SUCCESS,"");
                logger.info("pay notify return success"+setXML(STATUC_SUCCESS,""));
                return sucXml;
            }

        } catch (IOException e) {
            e.printStackTrace();
            return setXML(STATUC_FAIL,"fail");
        }
        return setXML(STATUC_FAIL,"fail");
    }
    private String setXML(String return_code, String return_msg) {
        return "<xml><return_code><![CDATA[" + return_code + "]]></return_code><return_msg><![CDATA[" + return_msg + "]]></return_msg></xml>";
    }

    @RequestMapping("/pay/warn")
    public void payWarn(){
        logger.info("pay warn");
    }



   /* @RequestMapping("/pay/test")
    public ModelAndView toPayPage(HttpServletRequest req) throws UnsupportedEncodingException {
        boolean isweixin = WeChat.isWeiXin(req);
        ModelAndView mav = new ModelAndView();
        if (isweixin){
            String nonceStr = RandomStringUtils.random(8, "123456789"); // 8位随机数
            Map<String,String> params = new HashMap<>();
            params.put("appid","");
            params.put("mch_id","");

            params.put();
            String productName = "测试商品001";
            String total_fee   = "100";
            String orderid     = "123456";

            // package 参数------------------------------  //
            Map<String, String> params = new HashMap<>();
            // 对商品名截取, 去除空格
            productName = productName.replaceAll(" ", "");
            productName = productName.length() > 17 ? productName.substring(0, 17) + "..." : productName;

            params.put("body", productName);	//商品描述。
            params.put("total_fee", total_fee);	//订单总金额
            params.put("fee_type", "1");	//现金支付币种,取值： 1 （人民币）
            params.put("out_trade_no", orderid); //商户系统内部的订单号
            params.put("spbill_create_ip", getIp(req)); // ip
            // 参数
            String timeStamp = System.currentTimeMillis() + "";

            String nonceStr = RandomStringUtils.random(8, "123456789"); // 8位随机数
            String packagestring = Pay.getPackage(params); //对参数打包
            String paySign = Pay.paySign(timeStamp, nonceStr, packagestring); // 构造签名

            // appId
            mav.addObject("appid", ConfKit.get("AppId"));
            // timeStamp
            mav.addObject("timeStamp", timeStamp);
            // nonceStr
            mav.addObject("nonceStr", nonceStr);
            // package
            mav.addObject("package", packagestring);
            // paySign
            mav.addObject("paySign", paySign);


            mav.setViewName("pay");
        }else{
            mav.setViewName("404.html");
        }
        return mav;
    }*/


}
