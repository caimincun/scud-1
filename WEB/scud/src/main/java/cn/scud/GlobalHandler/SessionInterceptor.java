package cn.scud.GlobalHandler;

import cn.scud.commoms.CodeDefined;
import cn.scud.commoms.CommonParamDefined;
import cn.scud.commoms.response.ErrorJsonRes;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * Created by Administrator on 2015/7/28.
 * 对 session 是否过期进行判断
 */
public class SessionInterceptor implements HandlerInterceptor {

    public boolean preHandle(HttpServletRequest request,HttpServletResponse response, Object handler) throws Exception {

        System.out.println("进入拦截器判断session是否存在！path:"+request.getRequestURI());
//        if(null == request.getSession().getAttribute(CommonParamDefined.TOKEN)){ // 当session 不存在时候
////            return new ErrorJsonRes(CodeDefined.EXCEPTION_CODE_SEESION_ERROR,CodeDefined.getMessage(CodeDefined.EXCEPTION_CODE_SEESION_ERROR));
////            JSONObject responseJSONObject = JSONObject.fromObject(responseObject);
//            System.out.println("session已经过期，需要从新登录！！");
//            ErrorJsonRes errorJsonRes = new ErrorJsonRes(CodeDefined.EXCEPTION_CODE_SEESION_ERROR,CodeDefined.getMessage(CodeDefined.EXCEPTION_CODE_SEESION_ERROR));
//            response.setCharacterEncoding("UTF-8");
//            response.setContentType("application/json; charset=utf-8");
//            response.getWriter().write(errorJsonRes.toString());
//            return false;
//        }

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }

}
