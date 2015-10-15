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
