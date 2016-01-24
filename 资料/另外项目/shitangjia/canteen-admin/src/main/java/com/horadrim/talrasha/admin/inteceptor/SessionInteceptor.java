package com.horadrim.talrasha.admin.inteceptor;

import com.horadrim.talrasha.admin.model.CanteenUser;
import com.horadrim.talrasha.common.CommonParamDefined;
import org.hibernate.Session;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.Writer;

/**
 * Created by Administrator on 2015/7/30.
 */
public class SessionInteceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        String[] noFilters = new String[] { "/canteenUser/toCheckUser", "/canteenUser/showAuthority"};
        String url = httpServletRequest.getRequestURI();
        System.out.println("url:"+url);
        for(String nofilter:noFilters){
            if(nofilter.equals(url)){
                return true;
            }
        }
        CanteenUser user = (CanteenUser)httpServletRequest.getSession().getAttribute(CommonParamDefined.USER);
        if(user != null){
            return true;
        }
        Writer writer = httpServletResponse.getWriter();
        writer.write("<script>window.parent.location.href='/canteenUser/toCheckUser';</script>");
        writer.flush();
        return false;


    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
