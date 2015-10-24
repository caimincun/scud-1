package com.horadrim.talrasha.admin.controller.web;

import com.horadrim.talrasha.admin.model.CanteenUser;
import com.horadrim.talrasha.common.CommonParamDefined;
import com.horadrim.talrasha.common.model.Canteen;
import com.horadrim.talrasha.common.model.Canteen_Module;
import com.horadrim.talrasha.common.model.InformationCategory;
import com.horadrim.talrasha.common.model.Module;
import com.horadrim.talrasha.common.service.CanteenModuleService;
import com.horadrim.talrasha.common.service.InformationCategoryService;
import com.horadrim.talrasha.common.service.ModuleService;
import com.horadrim.talrasha.common.util.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.TreeMap;

/**
 * Created by Administrator on 2015/7/1.
 */
@Controller
@RequestMapping("/module")
public class ModuleController {

    @Resource
    private ModuleService moduleService;

    @Resource
    private CanteenModuleService canteenModuleService;

    @Resource
    private InformationCategoryService informationCategoryService;

    /**
     * 展示模块
     * @param session
     * @return
     */
    @RequestMapping("/listModule")
    public ModelAndView listModule(HttpSession session){
        ModelAndView modelAndView = new ModelAndView();
        CanteenUser canteenUser = (CanteenUser)session.getAttribute(CommonParamDefined.USER);
        if(canteenUser == null){
            modelAndView.setViewName("redirect:/canteenUser/toCheckUser");
            return modelAndView;
        }

        List<Module> modules = moduleService.findAll();
        List<Canteen_Module> canteenModules = canteenModuleService.getByCanteenId(canteenUser.getCanteenId());
        TreeMap<Module,String> moduleMap = new TreeMap<>();
        for(Module module:modules){
            boolean flag = false;
            for(Canteen_Module canteen_module:canteenModules){
                if(module.getModuleCode().equals(canteen_module.getModuleCode())){
                    flag = true;
                    break;
                }
            }
            if(flag){
                moduleMap.put(module,"checked");
            }else{
                moduleMap.put(module,"");
            }
        }
        // 读取
        List<InformationCategory> informationCategories = informationCategoryService.listIncaByCanteenId(canteenUser.getCanteenId());
        modelAndView.setViewName("module");
        modelAndView.addObject("moduleMap",moduleMap);
        modelAndView.addObject("informationCategories",informationCategories);
        return  modelAndView;
    }

    /**
     *  设置餐厅功能子模块是否显示
     * @param session
     * @param moduleCode
     * @return
     */
    @RequestMapping("/setCanteenModule")
    public ModelAndView setCanteenModule(HttpSession session,String moduleCode,String informationCategory,String usercenter){
        System.out.println("informationCategory:"+informationCategory);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/module/listModule");
        CanteenUser canteenUser = (CanteenUser)session.getAttribute(CommonParamDefined.USER);
        if(null == moduleCode || moduleCode.equals("")){
            canteenModuleService.delCanModuleByCanteenId(canteenUser.getCanteenId());
            return modelAndView;
        }
        String[] modulecode =new String[8];
        if(null != moduleCode && moduleCode.length()>0){
             modulecode = moduleCode.split(",");
        }
        String[] infroCode =new String[4];
        if(null != informationCategory && informationCategory.length()>0){
           infroCode = informationCategory.split(",");
        }
        boolean flag = false;
        for(int i=0;i<modulecode.length;i++){
            if(modulecode[i].equals("m_xinxi")){
                flag = true;
                break;
            }
        }
        if(flag){
            informationCategoryService.saveInformationCa(canteenUser.getCanteenId(),infroCode);
        }else{
            informationCategoryService.updateInfoStatuFalse(canteenUser.getCanteenId());
        }
        if(null != usercenter){ //个人中心的子模块如果被选中
            informationCategoryService.saveUserCenter(usercenter,canteenUser.getCanteenId(),true);
        }else{
            informationCategoryService.saveUserCenter(usercenter,canteenUser.getCanteenId(),false);
        }

        canteenModuleService.saveCanModule(modulecode,canteenUser.getCanteenId());
        return modelAndView;
    }
}
