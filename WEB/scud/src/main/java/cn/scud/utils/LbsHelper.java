package cn.scud.utils;

import java.io.*;
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


    public static void main(String[] args) throws UnsupportedEncodingException {
//        //发送 GET 请求
//        String s=LbsHelper.sendGet("http://localhost:6144/Home/RequestString", "key=123&v=456");
//        System.out.println(s);

        //发送 POST 请求， ok 删除 pio 数据成功
        String sr= LbsHelper.sendPost("http://api.map.baidu.com/geodata/v3/poi/delete", "geotable_id=113562&ak=YANNPWadDPvvzTOZGWzXl0Rt&usertoken=1046936672&title=aa");

        //修改 pio 数据 ,ok 成功
//        String sr= LbsHelper.sendPost("http://api.map.baidu.com/geodata/v3/poi/update", "geotable_id=113321&ak=YANNPWadDPvvzTOZGWzXl0Rt&id=1044253868&latitude=30.65555&longitude=104.086347&coord_type=3&title=333");

        // 保存 pio 数据
//        String param ="geotable_id=113321&ak=YANNPWadDPvvzTOZGWzXl0Rt&id=104422544445668&latitude=30.659769&longitude=104.080335" +
//                "&coord_type=3&title=添加数据&tags=女 爱吃&aaa=5&user=abc";




//        String  param= "geotable_id=113562&ak=YANNPWadDPvvzTOZGWzXl0Rt&latitude=30.659769&longitude=104.080335" +
//                "&coord_type=3&usertoken=20150718141901445";
//
//        String sr= LbsHelper.sendPost("http://api.map.baidu.com/geodata/v3/poi/create",param);





        // 周边检索 pio .查看附近 ， 发送 get 请求  春熙路数据：  104.086399,30.659378
//        String parma ="geotable_id=113321&ak=YANNPWadDPvvzTOZGWzXl0Rt&id=1044225445668&location=104.094664,30.654407&radius=100000&sortby=distance:1&tags="+ URLEncoder.encode("女 爱吃", "utf-8");
        //filter 条件过滤,一个条件可以
//        String parma ="geotable_id=113321&ak=YANNPWadDPvvzTOZGWzXl0Rt" +
//                "&id=1044 String parma ="geotable_id=113321&ak=YANNPWadDPvvzTOZGWzXl0Rt" +


        //测试 一个tags 加一个 filter的过滤成功
//        String parma ="geotable_id=113321&ak=YANNPWadDPvvzTOZGWzXl0Rt" +
//                "&id=1044225445668&location=104.094664,30.654407&radius=100000&sortby=distance:1&tags=女&filter=aaa:5";

        // 测试两个条件,似乎 只能对 自定义的int 字段检索 filter

        String parma ="geotable_id=113321&ak=YANNPWadDPvvzTOZGWzXl0Rt" +
                "&id=1044225445668&location=104.094664,30.654407&radius=100000&sortby=distance:1&tags=女";
        String sr= LbsHelper.sendGet("http://api.map.baidu.com/geosearch/v3/nearby",parma);


//        String json = sr.substring(sr.indexOf("[{"), sr.lastIndexOf("}]") + 1);


        System.out.println(decodeUnicode(sr));
    }

    /**
     * 将 unicode 转换成为 中文
     * @param theString
     * @return
     */
    public static String decodeUnicode(String theString) {
        char aChar;
        int len = theString.length();
        StringBuffer outBuffer = new StringBuffer(len);
        for (int x = 0; x < len;) {
            aChar = theString.charAt(x++);
            if (aChar == '\\') {
                aChar = theString.charAt(x++);
                if (aChar == 'u') {
                    // Read the xxxx
                    int value = 0;
                    for (int i = 0; i < 4; i++) {
                        aChar = theString.charAt(x++);
                        switch (aChar) {
                            case '0':
                            case '1':
                            case '2':
                            case '3':
                            case '4':
                            case '5':
                            case '6':
                            case '7':
                            case '8':
                            case '9':
                                value = (value << 4) + aChar - '0';
                                break;
                            case 'a':
                            case 'b':
                            case 'c':
                            case 'd':
                            case 'e':
                            case 'f':
                                value = (value << 4) + 10 + aChar - 'a';
                                break;
                            case 'A':
                            case 'B':
                            case 'C':
                            case 'D':
                            case 'E':
                            case 'F':
                                value = (value << 4) + 10 + aChar - 'A';
                                break;
                            default:
                                throw new IllegalArgumentException(
                                        "Malformed   \\uxxxx   encoding.");
                        }
                    }
                    outBuffer.append((char) value);
                } else {
                    if (aChar == 't')
                        aChar = '\t';
                    else if (aChar == 'r')
                        aChar = '\r';
                    else if (aChar == 'n')
                        aChar = '\n';
                    else if (aChar == 'f')
                        aChar = '\f';
                    outBuffer.append(aChar);
                }
            } else
                outBuffer.append(aChar);
        }
        return outBuffer.toString();

    }

}
