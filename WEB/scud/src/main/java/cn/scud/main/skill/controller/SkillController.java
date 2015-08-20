package cn.scud.main.skill.controller;

import cn.scud.commoms.CommonParamDefined;
import cn.scud.commoms.response.ListSucRes;
import cn.scud.commoms.response.OperatorResponse;
import cn.scud.commoms.response.SuccessJsonRes;
import cn.scud.main.skill.model.Skill;
import cn.scud.main.skill.service.SkillService;
import cn.scud.utils.StreamSerializer;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
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
    public OperatorResponse saveSkill(HttpServletRequest request) throws Exception {
        Skill skill = StreamSerializer.streamSerializer(request.getInputStream(), Skill.class);
        skill.setUserToken((String)request.getSession().getAttribute(CommonParamDefined.USER_TOKEN));
        return new SuccessJsonRes();
    }

    /**
     * 获取用户技能
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
}
