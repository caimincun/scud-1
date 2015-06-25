package cn.scud.main.user.controller;

import cn.scud.commoms.CodeDefined;
import cn.scud.commoms.response.*;
import cn.scud.main.user.model.User;
import cn.scud.main.user.service.UserService;
import cn.scud.utils.JsonSerializer;
import cn.scud.utils.StreamSerializer;
import cn.scud.utils.WebUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by cmc on 14-12-9.
 */
//@RestController
@Controller
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserService userService;

    /**
     * 用户注册
     * @param user
     * @return
     */
    @RequestMapping(value="/add")
    @ResponseBody
    public OperatorResponse addUser(HttpServletRequest request,User user) throws Exception {
//        User user =  StreamSerializer.streamSerializer(request.getInputStream(), User.class);
        String ip = WebUtil.getRemoteHost(request);// 获取注册ip
        user.setLastLoginIp(ip);
        userService.addUser(user);
        GetObjSucRes objSucRes = new GetObjSucRes();
        objSucRes.setData(user);
        return objSucRes;
    }

    /**
     * 用户登录
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
        GetObjSucRes objSucRes = new GetObjSucRes();
        objSucRes.setData(fulUser);
        return objSucRes;
    }



    @RequestMapping("/select")
    @ResponseBody
    public OperatorResponse selectAll(){
        System.out.println(userService.findAll());
        return new SuccessJsonRes();
    }


}
