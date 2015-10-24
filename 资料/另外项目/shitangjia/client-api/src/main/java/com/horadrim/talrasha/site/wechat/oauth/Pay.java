package com.horadrim.talrasha.site.wechat.oauth;

import com.alibaba.fastjson.JSONObject;
import com.horadrim.talrasha.common.util.XStreamFactory;
import com.horadrim.talrasha.site.utils.OrderUtil;
import com.horadrim.talrasha.site.wechat.bean.PrePayFeedBack;
import com.horadrim.talrasha.site.wechat.util.ConfKit;
import com.horadrim.talrasha.site.wechat.util.HttpKit;
import com.thoughtworks.xstream.XStream;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.StringUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ExecutionException;

/**
 * 支付相关方法
 * @author L.cm
 * email: 596392912@qq.com
 * site:  http://www.dreamlu.net
 *
 */
public class Pay {

    // 发货通知接口
    private static final String DELIVERNOTIFY_URL = "https://api.weixin.qq.com/pay/delivernotify?access_token=";
    private static String UNIFIEDORDER = "https://api.mch.weixin.qq.com/pay/unifiedorder";

    /**
     * 参与 paySign 签名的字段包括：appid、timestamp、noncestr、package 以及 appkey。
     * 这里 signType 并不参与签名微信的Package参数
     * @param params
     * @return
     * @throws java.io.UnsupportedEncodingException
     */
    public static String getPackage(Map<String, String> params,String partnerKey) throws UnsupportedEncodingException {
//        String partnerKey = ConfKit.get("partnerKey");
//        String partnerId = ConfKit.get("partnerId");
        String mchId = ConfKit.get("mch_id");
        String notifyUrl = ConfKit.get("notify_url");
        // 公共参数
        params.put("appid",ConfKit.get("AppId"));
//        params.put("bank_type", "WX");
        params.put("attach", "yongle");
        params.put("mch_id", mchId);
        params.put("notify_url", notifyUrl);
//        params.put("input_charset", "UTF-8");
        return packageSign(params, partnerKey);
    }

    /**
     * 构造签名
     * @param params
     * @param encode
     * @return
     * @throws java.io.UnsupportedEncodingException
     */
    public static String createSign(Map<String, String> params, boolean encode) throws UnsupportedEncodingException {
        Set<String> keysSet = params.keySet();
        Object[] keys = keysSet.toArray();
        Arrays.sort(keys);
        StringBuilder temp = new StringBuilder();
        boolean first = true;
        for (Object key : keys) {
            if (first) {
                first = false;
            } else {
                temp.append("&");
            }
            temp.append(key).append("=");
            Object value = params.get(key);
            String valueString = "";
            if (null != value) {
                valueString = value.toString();
            }
            if (encode) {
                temp.append(URLEncoder.encode(valueString, "UTF-8"));
            } else {
                temp.append(valueString);
            }
        }
        return temp.toString();
    }

    /**
     * 构造package
     * @param params
     * @param paternerKey
     * @return
     * @throws java.io.UnsupportedEncodingException
     */
    private static String packageSign(Map<String, String> params, String paternerKey) throws UnsupportedEncodingException {
        String string1 = createSign(params, false);
        String stringSignTemp = string1 + "&key=" + paternerKey;
        return DigestUtils.md5Hex(stringSignTemp).toUpperCase();
    }

    /**
     * 支付签名
     * @param noncestr
     * @param packages
     * @return
     * @throws java.io.UnsupportedEncodingException
     */
    public static Map<String, String> paySign( String noncestr,String packages,String paternerKey) throws UnsupportedEncodingException {
        Map<String, String> paras = new HashMap();
        paras.put("appId", ConfKit.get("AppId"));
        paras.put("timeStamp", OrderUtil.GetTimestamp());
        paras.put("nonceStr", noncestr);
        paras.put("package", packages);
        paras.put("signType","MD5");
        paras.put("paySign",packageSign(paras, paternerKey));
//        return JSONObject.toJSONString(paras);
        return paras;
    }

    /**
     * 取得prepayid
     */
    public static String getPrePayId(Map<String,String> params){

        StringBuilder xml = new StringBuilder();
        xml.append("<xml>\n");
        for (Map.Entry<String, String> entry : params.entrySet()) {
            if ("body".equals(entry.getKey()) || "sign".equals(entry.getKey())) {
                xml.append("<").append(entry.getKey()).append("><![CDATA[").
                        append(entry.getValue()).append("]]></").append(entry.getKey()).append(">\n");
            } else {
                xml.append("<").append(entry.getKey()).append(">").append(entry.getValue()).append("</").append(entry.getKey()).append(">\n");
            }
        }
        xml.append("</xml>");

        try {
            String result =  HttpKit.post(UNIFIEDORDER,xml.toString());
            XStream xStream = XStreamFactory.init(false);
            xStream.alias("xml",PrePayFeedBack.class);
            PrePayFeedBack back = (PrePayFeedBack) xStream.fromXML(result);
            return back.getPrepay_id();
        } catch (IOException | ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * 支付回调校验签名
     * @param timestamp
     * @param noncestr
     * @param openid
     * @param issubscribe
     * @param appsignature
     * @return
     * @throws java.io.UnsupportedEncodingException
     */
    public static boolean verifySign(long timestamp,
            String noncestr, String openid, int issubscribe, String appsignature) throws UnsupportedEncodingException {
        Map<String, String> paras = new HashMap<String, String>();
        paras.put("appid", ConfKit.get("AppId"));
        paras.put("appkey", ConfKit.get("paySignKey"));
        paras.put("timestamp", String.valueOf(timestamp));
        paras.put("noncestr", noncestr);
        paras.put("openid", openid);
        paras.put("issubscribe", String.valueOf(issubscribe));
        // appid、appkey、productid、timestamp、noncestr、openid、issubscribe
        String string1 = createSign(paras, false);
        String paySign = DigestUtils.sha1Hex(string1);
        return paySign.equalsIgnoreCase(appsignature);
    }

    /**
     * 发货通知签名
     * @param paras
     * @return
     * @throws java.io.UnsupportedEncodingException
     *
     * @参数 appid、appkey、openid、transid、out_trade_no、deliver_timestamp、deliver_status、deliver_msg；
     */
    private static String deliverSign(Map<String, String> paras) throws UnsupportedEncodingException {
        paras.put("appkey", ConfKit.get("paySignKey"));
        String string1 = createSign(paras, false);
        return DigestUtils.sha1Hex(string1);
    }


    /**
     * 发货通知
     * @param access_token
     * @param openid
     * @param transid
     * @param out_trade_no
     * @return
     * @throws java.io.IOException
     * @throws java.security.NoSuchProviderException
     * @throws java.security.NoSuchAlgorithmException
     * @throws java.security.KeyManagementException
     * @throws InterruptedException
     * @throws java.util.concurrent.ExecutionException
     */

    public static boolean delivernotify(String access_token, String openid, String transid, String out_trade_no) throws IOException, ExecutionException, InterruptedException {
        Map<String, String> paras = new HashMap<String, String>();
        paras.put("appid", ConfKit.get("AppId"));
        paras.put("openid", openid);
        paras.put("transid", transid);
        paras.put("out_trade_no", out_trade_no);
        paras.put("deliver_timestamp", (System.currentTimeMillis() / 1000) + "");
        paras.put("deliver_status", "1");
        paras.put("deliver_msg", "ok");
        // 签名
        String app_signature = deliverSign(paras);
        paras.put("app_signature", app_signature);
        paras.put("sign_method", "sha1");
        String json = HttpKit.post(DELIVERNOTIFY_URL.concat(access_token), JSONObject.toJSONString(paras));
        if (StringUtils.isNotBlank(json)) {
            JSONObject object = JSONObject.parseObject(json);
            if (object.containsKey("errcode")) {
                int errcode = object.getIntValue("errcode");
                return errcode == 0;
            }
        }
        return false;
    }


}
