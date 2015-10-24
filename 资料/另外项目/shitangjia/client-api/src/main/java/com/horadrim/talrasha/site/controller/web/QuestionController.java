package com.horadrim.talrasha.site.controller.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.horadrim.talrasha.common.CommonParamDefined;
import com.horadrim.talrasha.common.model.Other;
import com.horadrim.talrasha.common.model.QuestionCategory;
import com.horadrim.talrasha.common.model.QuestionResult;
import com.horadrim.talrasha.common.model.QuestionUser;
import com.horadrim.talrasha.common.response.json.AbstractJsonRes;
import com.horadrim.talrasha.common.response.json.SuccessJsonRes;
import com.horadrim.talrasha.common.service.OtherService;
import com.horadrim.talrasha.common.service.QuestionCategoryService;
import com.horadrim.talrasha.common.service.QuestionResultService;
import com.horadrim.talrasha.common.service.QuestionService;
import com.horadrim.talrasha.common.service.dto.QuestionPojo;
import com.horadrim.talrasha.common.util.QuestResultsPojo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

/**
 * Created by Administrator on 2015/7/27.
 * 前台用户问卷controller
 */
@Controller
@RequestMapping("/questionary")
public class QuestionController {
    private static final String[] optionChar={"A","B","C","D","E"};
    @Resource
    private QuestionCategoryService questionCategoryService;
    @Resource
    private QuestionService questionService;
    @Resource
    private QuestionResultService questionResultService;
    @Resource
    private OtherService otherService;

    @RequestMapping("/toQuestionaryPage")
    public String toQuestionaryPage(int categoryId,Model model,HttpSession session){
        QuestionCategory category = questionCategoryService.get(categoryId);
        if (category==null||category.getStatus()==1)
            return "redirect:404.html";
        List<QuestionPojo> pojos = questionService.listByCanteenId((Integer) session.getAttribute(CommonParamDefined.CANTEEN_ID), categoryId);
        model.addAttribute("questions",pojos);
        model.addAttribute("optionChar", optionChar);
        model.addAttribute("count",pojos.size());
        model.addAttribute("categoryName",category.getDescription());
        return "questions";
    }
    @RequestMapping("/toQuestionCategoryPage")
    public String toQuestionCategoryPage(Model model,HttpSession session){
        model.addAttribute("categories",questionCategoryService.list("FROM QuestionCategory WHERE canteenId=? AND status=0",
                new Object[]{(Integer) session.getAttribute(CommonParamDefined.CANTEEN_ID)}));
        return "wenjuan";
    }
    @RequestMapping("/addQuestionAnswers")
    @ResponseBody
    public AbstractJsonRes addQuestionAnswers(String result_json,String other_info,HttpSession session) throws IOException {
//        Integer questionUserId = (Integer)session.getAttribute(CanteenCodeDefined.QUESTION_USER_ID);
//        if (questionUserId==null)
//            return "questionIndex";
//        QuestionUser questionUser = questionUserService.get(questionUserId);
//        if (questionUser==null){
//            return "questionIndex";
//        }
//        if (questionUser.getIsCanjia()==1){
//            //已经参加过
//            return "iperror";
//        }
        int userId = (int) session.getAttribute(CommonParamDefined.USERID);
        ObjectMapper mapper = new ObjectMapper();
        List<QuestResultsPojo> lists= mapper.readValue(result_json, mapper.getTypeFactory().constructParametricType(List.class, QuestResultsPojo.class));
        questionResultService.addQuestResultsByList(lists, userId);

        if(null!=other_info&&!"".equals(other_info)){
            Other other=new Other();
            other.setInfo(other_info);
            other.setUserId(userId);
            otherService.addOther(other);
        }
//        session.setAttribute(CanteenCodeDefined.IS_ACCEPT_QUESTION,"accept");
        return new SuccessJsonRes();
    }
}
