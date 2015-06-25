package cn.scud.utils;


import cn.scud.main.user.model.User;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * Created by Administrator on 2014/7/25.
 */
public class WebUtil {

    //拿到后端登录用户
    public static User getlogonAccountBack(HttpServletRequest request) {
        return (User) request.getSession().getAttribute("loginAccountBack");
    }

    //拿到前端登录用户
    public static User getlogonAccountBefore(HttpServletRequest request) {
        return (User) request.getSession().getAttribute("loginAccountBefore");
    }


    //生成ID
    public static String getGeratorID() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        Date date = new Date();
        String dateStr = sdf.format(date);
        String id = dateStr + getRandomString(7);
        return id;
    }

    public static String getRandomString(int length) {
        String base = "abcdefghijklmnopqrstuvwxyz0123456789";
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; i++) {
            int number = random.nextInt(base.length());
            sb.append(base.charAt(number));
        }
        return sb.toString();
    }

    //得到指定名称的cookie
    public static Cookie getCookie(HttpServletRequest request, String name) {
        Cookie cookies[] = request.getCookies();
        if (cookies == null || name == null || name.length() == 0) {
            return null;
        }
//        System.out.println(request.getServerName().equals(cookies[i].getDomain()));
        for (int i = 0; i < cookies.length; i++) {
            if (name.equals(cookies[i].getName())) {
                return cookies[i];
            }
        }
        return null;
    }

    //删除cookie
    public static void deleteCookie(HttpServletRequest request,
                                    HttpServletResponse response, Cookie cookie) {
        if (cookie != null) {
            cookie.setPath(getPath(request));
            cookie.setValue("");
            cookie.setMaxAge(0);
            response.addCookie(cookie);
        }
    }

    //保存cookie
    public static void setCookie(HttpServletRequest request,
                                 HttpServletResponse response, String name, String value) {
        setCookie(request, response, name, value, 0x278d00);
    }

    //保存cookie并设置保存时间
    public static void setCookie(HttpServletRequest request,
                                 HttpServletResponse response, String name, String value, int maxAge) {
        Cookie cookie = new Cookie(name, value == null ? "" : value);
        cookie.setMaxAge(maxAge);
        cookie.setPath(getPath(request));
        response.addCookie(cookie);
    }

    //得到根路径
    public static String getPath(HttpServletRequest request) {
        String path = request.getSession().getServletContext().getRealPath("/");
        path = path.replaceAll("\\\\", "/");
        return (path == null || path.length()==0) ? "/" : path;
    }

    //获取客户端真实IP
    public static String getRemoteHost(HttpServletRequest request){
        String ip = request.getHeader("x-forwarded-for");
        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)){
            ip = request.getHeader("Proxy-Client-IP");
        }
        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)){
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)){
            ip = request.getRemoteAddr();
        }
        return ip;
    }

    // 返回当前时间，格式：2015-06-12 03:02:24
    public static String getCurrentTime(){
        SimpleDateFormat   sDateFormat   =   new   SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String  date   =   sDateFormat.format(new   java.util.Date());
        return date;
    }
}
