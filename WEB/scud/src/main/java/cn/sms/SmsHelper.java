<<<<<<< HEAD
package cn.sms;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Administrator on 2015/9/25.
 */
public class SmsHelper {

    public static void main(String[] arg) throws IOException {

        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        String content = df.format(new Date());

        // 创建StringBuffer对象用来操作字符串
        StringBuffer sb = new StringBuffer("http://api.cnsms.cn/?");

        // 向StringBuffer追加用户名
        sb.append("ac=send&uid=118600");

        // 向StringBuffer追加密码（密码采用MD5 32位 小写）
        sb.append("&pwd=89f9501380ade65df57a433dcdacb551");

        // 向StringBuffer追加手机号码
        sb.append("&mobile=18381090832,18728120022,15984755735");

        sb.append("&encode=utf8");

        // 向StringBuffer追加消息内容转URL标准码
        sb.append("&content="+ URLEncoder.encode(content));

        // 创建url对象
        URL url = new URL(sb.toString());

        // 打开url连接
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        // 设置url请求方式 ‘get’ 或者 ‘post’
        connection.setRequestMethod("POST");

        // 发送
        BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));

        // 返回发送结果
        String inputline = in.readLine();

        // 返回结果为‘100’ 发送成功
        System.out.println(inputline);
    }

}
=======
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
>>>>>>> branch 'master' of git@github.com:caimincun/scud-1.git
