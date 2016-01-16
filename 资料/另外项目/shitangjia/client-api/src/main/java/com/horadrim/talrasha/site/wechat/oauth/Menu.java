/**
 * 微信公众平台开发模式(JAVA) SDK
 * (c) 2012-2014 ____′↘夏悸 <wmails@126.cn>, MIT Licensed
 * http://www.jeasyuicn.com/wechat
 */
package com.horadrim.talrasha.site.wechat.oauth;

import com.alibaba.fastjson.JSON;
import com.horadrim.talrasha.site.wechat.WeChat;
import com.horadrim.talrasha.site.wechat.util.HttpKit;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.util.Map;
import java.util.concurrent.ExecutionException;

/**
 * 菜单,可以将accessToken
 * 存储在session或者memcache中
 * @author L.cm
 * @date 2013-11-5 下午3:17:33
 */
@SuppressWarnings("unchecked")
public class Menu {

    /**
     * 创建菜单
     * @throws java.io.IOException
     * @throws java.security.NoSuchProviderException
     * @throws java.security.NoSuchAlgorithmException
     * @throws java.security.KeyManagementException
     */
	public boolean createMenu(String accessToken,String params) throws InterruptedException, ExecutionException, IOException {
        String jsonStr = HttpKit.post("https://api.weixin.qq.com/cgi-bin/menu/create?access_token=" + accessToken, params);
        Map<String, Object> map = JSON.parseObject(jsonStr,Map.class);
        return "0".equals(map.get("errcode").toString());
    }

    /**
     * 查询菜单
     * @throws java.io.IOException
     * @throws java.security.NoSuchProviderException
     * @throws java.security.NoSuchAlgorithmException
     * @throws java.security.KeyManagementException
     */
    public Map<String, Object> getMenuInfo(String accessToken) throws InterruptedException, ExecutionException, NoSuchAlgorithmException, KeyManagementException, IOException, NoSuchProviderException {
        String jsonStr = HttpKit.get("https://api.weixin.qq.com/cgi-bin/menu/get?access_token=" + accessToken);
        Map<String, Object> map = JSON.parseObject(jsonStr, Map.class);
        return map;
    }

    /**
     * 删除自定义菜单
     * @throws java.io.IOException
     * @throws java.security.NoSuchProviderException
     * @throws java.security.NoSuchAlgorithmException
     * @throws java.security.KeyManagementException
     */
    public boolean deleteMenu(String accessToken) throws InterruptedException, ExecutionException, NoSuchAlgorithmException, KeyManagementException, IOException, NoSuchProviderException {
        String jsonStr = HttpKit.get("https://api.weixin.qq.com/cgi-bin/menu/delete?access_token=" + accessToken);
        Map<String, Object> map = JSON.parseObject(jsonStr, Map.class);
        return "0".equals(map.get("errcode").toString());
    }

    public static void main(String[] args) {
        try {
            String accessToken = WeChat.getAccessToken();
            String content = "{\n" +
                    "     \"button\":[\n" +
                    "     {\t\n" +
                    "          \"type\":\"view\",\n" +
                    "          \"name\":\"点餐\",\n" +
                    "          \"url\":\"http://qingcaitest.duapp.com/product/toDianCanPage\"\n" +
                    "      },\n" +
                    "      { \n" +
                    "          \"type\":\"view\",\n" +
                    "          \"name\":\"首页\",\n" +
                    "          \"url\":\"http://qingcaitest.duapp.com/user/toIndexPage\"\n" +
                    "      },\n" +
            "               {\n" +
            "               \"type\":\"view\",\n" +
            "               \"name\":\"买菜\",\n" +
            "               \"url\":\"http://qingcaitest.duapp.com/vegetable/list\"\n" +
            "            }\n" +
                    "       ]\n" +
                    " }";
            System.out.println(new Menu().createMenu(accessToken,content));
//        System.out.println(new Menu().getMenuInfo(WeChat.getAccessToken()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
