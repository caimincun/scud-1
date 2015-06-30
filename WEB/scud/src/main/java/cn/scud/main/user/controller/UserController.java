package cn.scud.main.user.controller;

import cn.scud.commoms.CodeDefined;
import cn.scud.commoms.CommonParamDefined;
import cn.scud.commoms.response.*;
import cn.scud.main.user.model.User;
import cn.scud.main.user.model.UserInfo;
import cn.scud.main.user.service.UserService;
import cn.scud.utils.WebUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Created by cmc on 14-12-9.
 */
@Controller
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserService userService;

    /**
     * 用户注册,只返回token
     * @param user
     * @return
     */
    @RequestMapping(value="/add")
    @ResponseBody
    public OperatorResponse saveUser(HttpServletRequest request,User user) throws Exception {
//        User user =  StreamSerializer.streamSerializer(request.getInputStream(), User.class);
        String ip = WebUtil.getRemoteHost(request);// 获取注册ip
        user.setLastLoginIp(ip);
        userService.saveUser(user);
        ObjSucRes objSucRes = new ObjSucRes();
//        objSucRes.setData(user);
        objSucRes.setData(user.getUserToken()); // 只返回 token 标志 ,{"respStatus":{"result":0,"msg":"ok"},"data":"201506291301187955qs9mxe"}
        return objSucRes;
    }

    /**
     * 用户登录,将token保存进入session,将用户完整信息返回
     * @param request
     * @param user
     * @return
     * @throws Exception
     */
    @RequestMapping("/userLogin")
    @ResponseBody
    public OperatorResponse loginUser(HttpServletRequest request,User user)throws Exception{
//        User user =  StreamSerializer.streamSerializer(request.getInputStream(), User.class); // 这个是为andorid端json数据解析准备
        User fulUser= userService.loginUser(user);
        if(fulUser==null){
            return new ErrorJsonRes(CodeDefined.ACCOUNT_USER_LOGIN,CodeDefined.getMessage(CodeDefined.ACCOUNT_USER_LOGIN));
        }
        request.getSession().setAttribute(CommonParamDefined.TOKEN,fulUser.getUserToken());
        ObjSucRes objSucRes = new ObjSucRes();
        objSucRes.setData(fulUser);
        return objSucRes;
    }


    /**
     * 根据用户唯一编号token,获取用户完整信息userInfo
     *
     * @return
     */
    @RequestMapping("/getUserByToken")
    @ResponseBody
    public OperatorResponse getUserByToken(HttpSession session){
        UserInfo userInfo = userService.getUserInfoByToken((String)session.getAttribute(CommonParamDefined.TOKEN));
        ObjSucRes objSucRes = new ObjSucRes();
        objSucRes.setData(userInfo);
        return  objSucRes;
    }




    @RequestMapping("/select")
    @ResponseBody
    public OperatorResponse selectAll(){
        System.out.println(userService.findAll());
        return new SuccessJsonRes();
    }


}
