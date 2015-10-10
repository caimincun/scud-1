package com.horadrim.talrasha.admin.controller.web;

import com.horadrim.talrasha.admin.CanteenCodeDefined;

import com.horadrim.talrasha.admin.model.CanteenUser;
import com.horadrim.talrasha.common.CommonParamDefined;
import com.horadrim.talrasha.common.model.Canteen;
import com.horadrim.talrasha.common.model.Question;
import com.horadrim.talrasha.common.model.QuestionCategory;
import com.horadrim.talrasha.common.service.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by Administrator on 2015/6/11.
 */
@Controller
@RequestMapping("/question")
public class QuestionController {

    private static final String[] optionChar={"A","B","C","D","E"};
    @Resource
    private QuestionService questionService;
    private static List<Question> questions ;
    @Resource(name="otherService")
    private OtherService otherService;
    @Resource
    private QuestionUserService questionUserService;

    @Resource
    private QuestionCategoryService questionCategoryService;

    @Resource(name = "questionAnswersService")
    private QuestionAnswersService questionAnswersService;
    /**
     * 跳转到添加问题的界面
     * @return
     */
    @RequestMapping("/add")
    public String toAddQuestionPage(){
        return "addQuestion";
    }

    /**
     * 添加问卷调查问题
     * @param A
     * @param B
     * @param C
     * @param D
     * @return
     */
    @RequestMapping("/addQuestion")
    public ModelAndView addQuestion(HttpSession session,int questionCategoryId,String title,String questionType,String A,String B,String C,String D){
        CanteenUser canteenUser =(CanteenUser)session.getAttribute(CommonParamDefined.USER);
        questionService.addQuestionAndAnswers(canteenUser.getId(),questionCategoryId,title,questionType,A,B,C,D);

        ModelAndView modelAndView = new ModelAndView();
        CanteenUser canteenUser1 = (CanteenUser)session.getAttribute(CommonParamDefined.USER);
        String hql = "from QuestionCategory where status = 0 and canteenId ="+canteenUser1.getCanteenId();
        List<QuestionCategory> questionCategoryList = questionCategoryService.list(hql,null);
        modelAndView.addObject("questionCategoryList",questionCategoryList);
        modelAndView.setViewName("addQuestion");
        return modelAndView;
    }


    @RequestMapping("/getQuestionCate")
    public ModelAndView  getQuestionCate(int id){
        QuestionCategory questionCategory = questionCategoryService.get(id);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("questionCategory",questionCategory);
        modelAndView.setViewName("updateCagtegory");
        return modelAndView;
    }

    @RequestMapping("/upadateQuestionCate")
    public ModelAndView upadateQuestionCate(HttpSession session,QuestionCategory questionCategory){
        CanteenUser canteenUser = (CanteenUser) session.getAttribute(CommonParamDefined.USER);
        questionCategory.setCanteenId(canteenUser.getCanteenId());
        questionCategoryService.update(questionCategory);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/canteenUser/choicepage?id=25");
        return modelAndView;
    }

    @RequestMapping("/addQuestionCate")
    public ModelAndView addQuestionCate(HttpSession session,QuestionCategory questionCategory){
        CanteenUser canteenUser = (CanteenUser)session.getAttribute(CommonParamDefined.USER);
        questionCategory.setCanteenId(canteenUser.getCanteenId());
        questionCategoryService.save(questionCategory);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/canteenUser/choicepage?id=25");
        return modelAndView;
    }


    /**
     * 添加问卷调查答案
     * @return
     * @throws java.io.IOException
     */

    @RequestMapping("/list")
    public String listQuestion(HttpSession session,Model model){
       if (session.getAttribute(CanteenCodeDefined.QUESTION_USER_ID)==null)
            return "questionIndex";
//        List<Question> questions = questionService.findAll();
//        List<QuestionWithAnswerPojo> pojos = new ArrayList<>();
//        final String hql = "FROM ";
//        List<Map<String,Object>> results = new ArrayList<>();
//        int flag = 0;
//        for (Map<String,Object> map : results){
//            int questionId = (int) map.get("questionId");
//            if (questionId!=flag){
//                   //todo 二级缓存
//            }
//        }
        model.addAttribute("questions", getQuestions());
        model.addAttribute("optionChar", optionChar);
        model.addAttribute("count",questions.size());
        return "questions";
    }

    @RequestMapping("/yijian")
    public String yijian(Model model){
        final String sql =" SELECT o.info,u.phonenumber FROM qc_other o LEFT JOIN qc_questionuser u ON o.user_id = u.id ";
        model.addAttribute("info",questionService.listFieldBySQL(sql,null));
        return "questions_tongji_2";
    }

    private List<Question> getQuestions(){
        if (questions==null){
            questions =  questionService.findAll();
        }
        return questions;
    }



}
