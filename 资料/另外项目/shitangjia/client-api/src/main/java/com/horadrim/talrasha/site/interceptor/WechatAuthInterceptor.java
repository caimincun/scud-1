package com.horadrim.talrasha.site.interceptor;


import com.horadrim.talrasha.common.CommonParamDefined;
import com.horadrim.talrasha.common.model.User;
import com.horadrim.talrasha.common.service.CanteenModuleService;
import com.horadrim.talrasha.common.service.UserService;

import com.horadrim.talrasha.common.util.StringUtils;
import com.horadrim.talrasha.site.service.ClientUserService;
import com.horadrim.talrasha.site.wechat.WeChat;
import com.horadrim.talrasha.site.wechat.bean.UserInfo;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 微信验证拦截器,用于检验微信公众号内的请求 用户是否绑定了openid
 */
public class WechatAuthInterceptor implements HandlerInterceptor{

   // private UserService userService = (UserService) SpringContenxtUtil.getBean("userService");
    @Resource(name = "clientUserService")
    private ClientUserService clientUserService;
    @Resource(name = "canteenModuleService")
    private CanteenModuleService canteenModuleService;
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) throws Exception {
        HttpSession session = request.getSession();

//        session.setAttribute(CommonParamDefined.OPENID,"oBpoJuEOXqA03jvCpTvyKxCYsee4");

        Integer userId = (Integer) session.getAttribute(CommonParamDefined.USERID);
        String openId = (String) session.getAttribute(CommonParamDefined.OPENID);
        String isTestCanteen=(String)session.getAttribute(CommonParamDefined.TEST);

        String url=request.getRequestURI();

        if(null!=isTestCanteen&&"1".equals(isTestCanteen)){
            return true;
        }


        if (userId != null && userId > 0 && !StringUtils.isBlank(openId))
            return true;
        else {
            //判断openid是否为空
            if (!StringUtils.isBlank(openId)) {
                //取得User 判断是否绑定
                User user = clientUserService.getByOpenId(openId);
                if (user != null) {
                    if (userId==null)
                        session.setAttribute(CommonParamDefined.USERID, user.getId());
                        session.setAttribute(CommonParamDefined.CANTEEN_ID,user.getCanteenId());
                    //查询用户权限
                    if (session.getAttribute(CommonParamDefined.CANTEEN_MODULE)==null){
                        session.setAttribute(CommonParamDefined.CANTEEN_MODULE,
                                canteenModuleService.getByCanteenId(user.getCanteenId()));
                    }
                     //通过openid获取用户信息，并判断与数据库保存是否一致，不一致则修改
                    UserInfo wechatUser = new com.horadrim.talrasha.site.wechat.oauth.User().getUserInfo(WeChat.ACCESS_TOKEN,openId);
                    if (wechatUser!=null&&(!wechatUser.getNickname().equals(user.getNickName())
                            ||(!StringUtils.isBlank(wechatUser.getHeadimgurl())&&!wechatUser.getHeadimgurl().equals(user.getHeadImg()))
                            ||!(wechatUser.getSex()==user.getSex()))){
                        clientUserService.executeUpdate("UPDATE User SET nickName=? ,headImg=?,sex=? WHERE id=?",
                                new Object[]{wechatUser.getNickname(),StringUtils.isBlank(wechatUser.getHeadimgurl())?
                                        user.getHeadImg():wechatUser.getHeadimgurl(),wechatUser.getSex(),user.getId()});
                    }
                    return true;
                } else {
                    //跳转到绑定页面
                    response.sendRedirect(request.getContextPath()+"/user/toRegPage");
                }

            } else {
                response.sendRedirect(request.getContextPath()+"/wechat/toAuthPage?uri="+request.getRequestURI());
            }

        }
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
