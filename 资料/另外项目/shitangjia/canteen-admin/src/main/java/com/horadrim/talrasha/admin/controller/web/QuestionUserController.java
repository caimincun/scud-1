package com.horadrim.talrasha.admin.controller.web;

import com.horadrim.talrasha.admin.CanteenCodeDefined;
import com.horadrim.talrasha.common.model.QuestionUser;
import com.horadrim.talrasha.common.service.QuestionUserService;
import com.horadrim.talrasha.common.util.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by Administrator on 2015/6/11.
 */
@Controller
@RequestMapping("/questionuser")
public class QuestionUserController {

    @Resource
    private QuestionUserService questionUserService;

    /**
     * 到首页
     * @return
     */
    @RequestMapping("/toQuestionIndexPage")
    public String toAddQuestionUser() {
        return "questionIndex";
    }

    /**
     * 添加问卷调查的用户信息
     * @param questionUser
     * @param httpSession
     * @return
     */
    @RequestMapping("/addQuestionUsers")
    public String addQuestionUser(QuestionUser questionUser,HttpSession httpSession,Model model){
//        String ip = getIp(request);
//        System.out.println("ip"+ip);
//        if(questionUserService.existIp(ip)){
//            return "iperror";
//        }
        if (!StringUtils.validatePhone(questionUser.getPhoneNumber())){
            model.addAttribute("phone","err");
            return "questionIndex";
        }
        if (questionUserService.existPhone(questionUser.getPhoneNumber())){
            return "iperror";
        }
        questionUser.setIpAddr("");
        questionUserService.save(questionUser);
        httpSession.setAttribute(CanteenCodeDefined.QUESTION_USER_ID,questionUser.getId());
        return "redirect:/question/list";
    }
    @RequestMapping("/list")
    @ResponseBody
    public List<QuestionUser> list(){
        System.out.println("ddd");
        return questionUserService.findAll();
    }
    @RequestMapping("/test")
    @ResponseBody
    public String test(HttpServletRequest request){
      return  getIp(request);

    }

    /**
     * 获取ip
     * @param request
     * @return
     */
    public static String getIp(HttpServletRequest request) {
        if (request == null)
            return "";
        String ip = request.getHeader("X-Requested-For");
        if (org.apache.commons.lang.StringUtils.isEmpty(ip) || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("X-Forwarded-For");
        }
        if (org.apache.commons.lang.StringUtils.isEmpty(ip) || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (org.apache.commons.lang.StringUtils.isEmpty(ip) || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (org.apache.commons.lang.StringUtils.isEmpty(ip) || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (org.apache.commons.lang.StringUtils.isEmpty(ip) || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (org.apache.commons.lang.StringUtils.isEmpty(ip) || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
//        if (!StringUtils.isBlank(ip))
//            return ip.split(",")[0];
        return ip;
    }
}
