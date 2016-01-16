package com.horadrim.talrasha.site.controller.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.horadrim.talrasha.common.CommonParamDefined;
import com.horadrim.talrasha.common.model.InformationCategory;
import com.horadrim.talrasha.common.model.Other;
import com.horadrim.talrasha.common.model.QuestionCategory;
import com.horadrim.talrasha.common.response.json.AbstractJsonRes;
import com.horadrim.talrasha.common.response.json.SuccessJsonRes;
import com.horadrim.talrasha.common.service.*;
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
@RequestMapping("/information")
public class InformationController {

    @Resource
    private InformationCategoryService informationCategoryService;

    @RequestMapping("/toInfoCategoryPage")
    public String toInfoCategoryPage(Model model,HttpSession session){
        model.addAttribute("categories",informationCategoryService.list("FROM InformationCategory WHERE canteenId=? AND status=0",
                new Object[]{(Integer) session.getAttribute(CommonParamDefined.CANTEEN_ID)}));
        return "xinxi";
    }

    @RequestMapping("/toInformationPage")
    public String toInformationPage(int categoryId,Model model,HttpSession session){
        InformationCategory category = informationCategoryService.get(categoryId);
        if (category==null||category.getStatus()==1)
            return "redirect:404.html";
        model.addAttribute("categoryName",category.getDescription());
        String url="";
        if(categoryId==1){
            url="fgw";//发改委信息
        }
        if(categoryId==2){
            url="canteen_team";//食堂团队
        }
        if(categoryId==3){
            url="cgxinxi";//采购信息
        }
        if(categoryId==4){
            url="guangbo";//食堂广播
        }
        return url;
    }
}
