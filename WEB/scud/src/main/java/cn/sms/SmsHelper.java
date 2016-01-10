package cn.sms;


import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2015/9/25.
 */
public class SmsHelper {

    private static String Url = "http://106.ihuyi.cn/webservice/sms.php?method=Submit";

    public static Map sendMeg(String phoneNumber,int mobile_code){
        {

            HttpClient client = new HttpClient();
            PostMethod method = new PostMethod(Url);

            client.getParams().setContentCharset("UTF-8");
            method.setRequestHeader("ContentType","application/x-www-form-urlencoded;charset=UTF-8");

            String content = new String("您的验证码是：" + mobile_code + "。请不要把验证码泄露给其他人。");

            NameValuePair[] data = {//提交短信
                    new NameValuePair("account", "cf_1019557621"),
                    new NameValuePair("password", "cai199202045819"), //密码可以使用明文密码或使用32位MD5加密
                    new NameValuePair("mobile", phoneNumber),
                    new NameValuePair("content", content),
            };

            method.setRequestBody(data);

            Map<String,String> paramMap = new HashMap<String,String>();
            paramMap.put("content",content);
            try {
                client.executeMethod(method);

                String SubmitResult =method.getResponseBodyAsString();
                Document doc = DocumentHelper.parseText(SubmitResult);
                Element root = doc.getRootElement();

                String code = root.elementText("code");
                String msg = root.elementText("msg");
                String smsid = root.elementText("smsid");

                paramMap.put("code",code);

                System.out.println(code);
                System.out.println(msg);
                System.out.println(smsid);

                if("2".equals(code)){
                    System.out.println("短信提交成功");
                }

            } catch (HttpException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }catch (DocumentException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            return  paramMap;

        }
    }

}
