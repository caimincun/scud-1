package cn.scud.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2015/7/14.
 *  关于LBS 的相关操作
 */
public class LbsHelper {

    public static final String AK = "YANNPWadDPvvzTOZGWzXl0Rt"; //  访问 LBS 数据的权限
    public static final String GEOTABLE_ID = "113562";          //  LBS 数据库的标志

    /**
     * 向指定URL发送GET方法的请求
     *
     * @param url
     *            发送请求的URL
     * @param param
     *            请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
     * @return URL 所代表远程资源的响应结果
     */
    public static String sendGet(String url, String param) {
        String result = "";
        BufferedReader in = null;
        try {
            String urlNameString = url + "?" + param;
            URL realUrl = new URL(urlNameString);
            // 打开和URL之间的连接
            URLConnection connection = realUrl.openConnection();
            // 设置通用的请求属性
            connection.setRequestProperty("accept", "*/*");
            connection.setRequestProperty("connection", "Keep-Alive");
            connection.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            // 建立实际的连接
            connection.connect();
            // 获取所有响应头字段
            Map<String, List<String>> map = connection.getHeaderFields();
            // 遍历所有的响应头字段
            for (String key : map.keySet()) {
                System.out.println(key + "--->" + map.get(key));
            }
            // 定义 BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(
                    connection.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            System.out.println("发送GET请求出现异常！" + e);
            e.printStackTrace();
        }
        // 使用finally块来关闭输入流
        finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return result;
    }


    /**
     * 发送post 请求
     * @param url
     * @param param
     * @return
     */
    public static String sendPost(String url, String param) {
        PrintWriter out = null;
        BufferedReader in = null;
        String result = "";
        try {
            URL realUrl = new URL(url);
            // 打开和URL之间的连接
            URLConnection conn = realUrl.openConnection();
            // 设置通用的请求属性
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            // 发送POST请求必须设置如下两行
            conn.setDoOutput(true);
            conn.setDoInput(true);
            // 获取URLConnection对象对应的输出流
            out = new PrintWriter(conn.getOutputStream());
            // 发送请求参数
            out.print(param);
            // flush输出流的缓冲
            out.flush();
            // 定义BufferedReader输入流来读取URL的响应
            in = new BufferedReader(
                    new InputStreamReader(conn.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            System.out.println("发送 POST 请求出现异常！"+e);
            e.printStackTrace();
        }
        //使用finally块来关闭输出流、输入流
        finally{
            try{
                if(out!=null){
                    out.close();
                }
                if(in!=null){
                    in.close();
                }
            }
            catch(IOException ex){
                ex.printStackTrace();
            }
        }
        return result;
    }


    public static void main(String[] args) {
//        //发送 GET 请求
//        String s=LbsHelper.sendGet("http://localhost:6144/Home/RequestString", "key=123&v=456");
//        System.out.println(s);

        //发送 POST 请求， ok 删除 pio 数据成功
        // String sr= LbsHelper.sendPost("http://api.map.baidu.com/geodata/v3/poi/delete", "geotable_id=113321&ak=YANNPWadDPvvzTOZGWzXl0Rt&id=1046936672");

        //修改 pio 数据 ,ok 成功
        // String sr= LbsHelper.sendPost("http://api.map.baidu.com/geodata/v3/poi/update", "geotable_id=113321&ak=YANNPWadDPvvzTOZGWzXl0Rt&id=1044253868&latitude=30.65555&longitude=104.086347&coord_type=3&title=333");

        // 保存 pio 数据
        String sr= LbsHelper.sendPost("http://api.map.baidu.com/geodata/v3/poi/create", "geotable_id=113321&ak=YANNPWadDPvvzTOZGWzXl0Rt&id=104422544445668&latitude=38.65575&longitude=104.016347&coord_type=3&title=添加数据&tags=女");

        // 周边检索 pio .查看附近 ， 发送 get 请求  春熙路数据：  104.086399,30.659378
//        String parma ="geotable_id=113321&ak=YANNPWadDPvvzTOZGWzXl0Rt&id=1044225445668&location=104.094664,30.654407&radius=100000&sortby=distance:1&tags=男";
//        String sr= LbsHelper.sendGet("http://api.map.baidu.com/geosearch/v3/nearby",parma);


        System.out.println(sr);
    }

}