package cn.scud.main.skill.controller;

import cn.scud.commoms.CommonParamDefined;
import cn.scud.commoms.response.ListSucRes;
import cn.scud.commoms.response.ObjSucRes;
import cn.scud.commoms.response.OperatorResponse;
import cn.scud.commoms.response.SuccessJsonRes;
import cn.scud.main.skill.model.Skill;
import cn.scud.main.skill.service.SkillService;
import cn.scud.main.user.model.UserInfo;
import cn.scud.utils.BosHelper;
import cn.scud.utils.JsonSerializer;
import cn.scud.utils.StreamSerializer;
import cn.scud.utils.WebUtil;
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
import java.io.UnsupportedEncodingException;
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
        Skill skill = JsonSerializer.deSerialize(json,Skill.class);
        MultipartHttpServletRequest multipartRequest  =  (MultipartHttpServletRequest) request;
        MultipartFile img0  =  multipartRequest.getFile("userImage0");
        MultipartFile img1  =  multipartRequest.getFile("userImage1");
        MultipartFile img2  =  multipartRequest.getFile("userImage2");
        String prictureTemp = "";
        if(null != img0 && img0.getSize()>0){
            prictureTemp += BosHelper.putSkillImage(img0.getInputStream(), WebUtil.getBosOjectKey(), img0.getSize(), img0.getContentType());
        }
        if(null != img1 && img1.getSize()>0){
            prictureTemp += ";";
            prictureTemp += BosHelper.putSkillImage(img1.getInputStream(), WebUtil.getBosOjectKey(), img1.getSize(), img1.getContentType());
        }
        if(null != img2 && img2.getSize()>0){
            prictureTemp += ";";
            prictureTemp += BosHelper.putSkillImage(img2.getInputStream(), WebUtil.getBosOjectKey(), img2.getSize(), img2.getContentType());
        }
        skill.setSkillPicture(prictureTemp);
        skill.setUserToken((String)request.getSession().getAttribute(CommonParamDefined.USER_TOKEN));
        skillService.saveSkill(skill);
        return new SuccessJsonRes();
    }

    /**
     * 获取用户技能列表
     * @param session
     * @return
     */
    @RequestMapping("/listUserSkills")
    @ResponseBody
    public OperatorResponse listUserSkills(HttpSession session,String userToken){

//        String userToken =(String)session.getAttribute(CommonParamDefined.USER_TOKEN);
        List<Skill> skills = skillService.listUserSkills(userToken);
        ListSucRes listSucRes = new ListSucRes();
        listSucRes.setData(skills);
        return listSucRes;
    }


    /**
     *  获取技能详情
     * @param skillToken
     * @return
     */
    public OperatorResponse detailSkill(String skillToken){
        Skill skill = skillService.loadSkillBysktoken(skillToken);
        ObjSucRes objSucRes = new ObjSucRes();
        objSucRes.setData(skill);
        return  objSucRes;
    }


    /**
     * 查询附近的对象，添加条件查询
     * @param session
     * @return
     */
    @RequestMapping("/getNearSkill")
    @ResponseBody
    public OperatorResponse getNearSkill(HttpSession session,String lat,String lng,int page_index,String skillName) throws UnsupportedEncodingException { //page_index，当前页数，起始页数为1
        System.out.println("lat:"+lat+"lng:"+lng+"page_index:"+page_index);
        int userLbsId = (Integer)session.getAttribute(CommonParamDefined.USER_LBS_ID);
        int radius = 100000; //默认查询50公里距离内的
        int page_size = 2;// 设置每一页返回的条数，这儿默认两条
        List<Skill> skills = skillService.LbsNearSkill(session, lng, lat, radius, page_index, page_size, userLbsId, skillName);
        System.out.println("查询附近的技能对象，添加条件查询+skills.size:"+skills.size());
        ListSucRes listSucRes = new ListSucRes();
        listSucRes.setData(skills);
        return  listSucRes;
    }


    /**
     * 修改技能
     * @return
     */
    @RequestMapping("/updateSkill")
    @ResponseBody
    public OperatorResponse updateSkill(String json,HttpServletRequest request) throws Exception {
        Skill skill = JsonSerializer.deSerialize(json,Skill.class);
        MultipartHttpServletRequest multipartRequest  =  (MultipartHttpServletRequest) request;
        MultipartFile img0  =  multipartRequest.getFile("userImage0");
        MultipartFile img1  =  multipartRequest.getFile("userImage1");
        MultipartFile img2  =  multipartRequest.getFile("userImage2");
        String prictureTemp = "";
        if(img0.getSize()>0){
            prictureTemp += BosHelper.putSkillImage(img0.getInputStream(), WebUtil.getBosOjectKey(), img0.getSize(), img0.getContentType());
        }
        if(img1.getSize()>0){
            prictureTemp += ";";
            prictureTemp += BosHelper.putSkillImage(img1.getInputStream(), WebUtil.getBosOjectKey(), img1.getSize(), img1.getContentType());
        }
        if(img2.getSize()>0){
            prictureTemp += ";";
            prictureTemp += BosHelper.putSkillImage(img2.getInputStream(), WebUtil.getBosOjectKey(), img2.getSize(), img2.getContentType());
        }
        skill.setSkillPicture(prictureTemp);
        skill.setUserToken((String)request.getSession().getAttribute(CommonParamDefined.USER_TOKEN));
        // 然后删除用户在远端原来的技能图片
        Skill skill2 = skillService.loadSkillBysktoken(skill.getSkillToken());
        if(skill2.getSkillPicture().length()>0){
            String[] path = skill2.getSkillPicture().split(";");
            for(int i =0;i<path.length;i++){
                BosHelper.deleteSkillObject(path[i]);
            }
        }
        skillService.updateSkill(skill);
        return new SuccessJsonRes();
    }



}
