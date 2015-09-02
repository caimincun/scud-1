package cn.scud.main.skill.controller;

import cn.scud.commoms.CommonParamDefined;
import cn.scud.commoms.response.ListSucRes;
import cn.scud.commoms.response.OperatorResponse;
import cn.scud.commoms.response.SuccessJsonRes;
import cn.scud.main.skill.model.Skill;
import cn.scud.main.skill.service.SkillService;
import cn.scud.utils.JsonSerializer;
import cn.scud.utils.StreamSerializer;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

/**
 * Created by Administrator on 2015/8/13.
 */
@Controller
@RequestMapping("/skill")
public class SkillController {

    @Resource
    private SkillService skillService;

    /**
     * 保存技能             暂时不处理上传图片
     * @return
     */
    @RequestMapping("/saveSkill")
    @ResponseBody
    public OperatorResponse saveSkill(String json,HttpServletRequest request) throws Exception {
//        Skill skill = StreamSerializer.streamSerializer(request.getInputStream(), Skill.class);
//        skill.setUserToken((String)request.getSession().getAttribute(CommonParamDefined.USER_TOKEN));
//        System.out.println("skill:"+skill);
////        skillService.saveSkill(skill);
//
//        MultipartHttpServletRequest multipartRequest  =  (MultipartHttpServletRequest) request;
//        MultipartFile img  =  multipartRequest.getFile("skillImage");
//        System.out.printf("img:"+img.getSize());


        Skill skill = JsonSerializer.deSerialize(json,Skill.class);
        System.out.printf("" + skill);
        MultipartHttpServletRequest multipartRequest  =  (MultipartHttpServletRequest) request;
        MultipartFile img  =  multipartRequest.getFile("userImage");
        System.out.printf("img:"+img.getSize());

        return new SuccessJsonRes();
    }

    /**
     * 获取用户技能列表
     * @param session
     * @return
     */
    @RequestMapping("/listUserSkills")
    @ResponseBody
    public OperatorResponse listUserSkills(HttpSession session){
        List<Skill> skills = skillService.listUserSkills((String)session.getAttribute(CommonParamDefined.USER_TOKEN));
        ListSucRes listSucRes = new ListSucRes();
        listSucRes.setData(skills);
        return listSucRes;
    }

    /**
     * 修改技能
     * @return
     */
    @RequestMapping("/updateSkill")
    @ResponseBody
    public OperatorResponse updateSkill(HttpServletRequest request) throws Exception {
        Skill skill = StreamSerializer.streamSerializer(request.getInputStream(), Skill.class);
        skill.setUserToken((String)request.getSession().getAttribute(CommonParamDefined.USER_TOKEN));
        System.out.println("skill:"+skill);
        skillService.updateSkill(skill);
        return new SuccessJsonRes();
    }



}
