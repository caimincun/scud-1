package com.horadrim.talrasha.admin.controller.web;

import com.horadrim.talrasha.admin.model.CanteenAuthority;
import com.horadrim.talrasha.admin.model.CanteenDept;
import com.horadrim.talrasha.admin.model.CanteenUser;
import com.horadrim.talrasha.admin.model.DeptAndAuthority;
import com.horadrim.talrasha.admin.service.CanteenDeptService;
import com.horadrim.talrasha.admin.service.CanteenUserService;
import com.horadrim.talrasha.admin.service.DeptAndAuthorityService;
import com.horadrim.talrasha.common.CommonParamDefined;
import com.horadrim.talrasha.common.model.Canteen;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

/**
 * Created by Administrator on 2015/7/23.
 */
@Controller
@RequestMapping("/dept")
public class CanteenDeptController {

    @Resource
    private CanteenDeptService canteenDeptService;

    @Resource
    private DeptAndAuthorityService deptAndAuthorityService;

    @Resource
    private CanteenUserService canteenUserService;

    @RequestMapping("/saveDept")
    public ModelAndView saveDept(HttpSession session,CanteenDept canteenDept,@RequestParam(required = false) int[] authority){
        CanteenUser canteenUser = (CanteenUser)session.getAttribute(CommonParamDefined.USER);
        canteenDept.setCanteenId(canteenUser.getCanteenId());
        canteenDeptService.save(canteenDept);
        canteenDeptService.saveDetpAndAuthority(canteenDept.getId(),authority);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/canteenUser/choicepage?id=21");
        return  modelAndView;
    }

    @RequestMapping("/updateDept")
    public ModelAndView updateDept(CanteenDept canteenDept,@RequestParam(required = false) int[] authority){
//
        canteenDeptService.update(canteenDept);
        canteenDeptService.deptDeptAndAuthority(canteenDept.getId());
        canteenDeptService.saveDetpAndAuthority(canteenDept.getId(),authority);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/canteenUser/choicepage?id=21");
        return  modelAndView;
    }

    @RequestMapping("/getDeptAndAuthority")
    public ModelAndView getDeptAndAuthority(HttpSession session,int id){
        CanteenDept canteenDept = canteenDeptService.get(id);
        List<DeptAndAuthority> deptAndAuthorityList = deptAndAuthorityService.getDeptAndAuthorityBydeptId(id);
        for(DeptAndAuthority deptAndAuthority:deptAndAuthorityList){
            System.out.println(deptAndAuthority);
            System.out.println("部门对应的authorityId:"+deptAndAuthority.getAuthortyId());
        }
        CanteenUser canteenUser = (CanteenUser)session.getAttribute(CommonParamDefined.USER);
        List<CanteenAuthority> canteenAuthoritys =  canteenUserService.queryAuthByCantUser(canteenUser);
        TreeMap<CanteenAuthority, List<CanteenAuthority>> canteenAuthoritieMap =new TreeMap<>();
        List<CanteenAuthority> canteenAuthorityList ;
        for(int i=0;i<canteenAuthoritys.size();i++){
            if(canteenAuthoritys.get(i).getCanteenAuthority() == null) { //判断是否为一级节点
                int canteenAuthority_id = canteenAuthoritys.get(i).getId();
                canteenAuthorityList = new ArrayList<>();
                for (int j = 0; j < canteenAuthoritys.size(); j++) {
                    if(i == j){
                        continue;
                    }
                    if (null != canteenAuthoritys.get(j).getCanteenAuthority() && canteenAuthority_id == canteenAuthoritys.get(j).getCanteenAuthority().getId())
                    {
                        canteenAuthorityList.add(canteenAuthoritys.get(j));
                    }
                }
                canteenAuthoritieMap.put(canteenAuthoritys.get(i),canteenAuthorityList);
            }
        }

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("canteenDept",canteenDept);
        modelAndView.addObject("deptAndAuthorityList",deptAndAuthorityList);
        modelAndView.addObject("canteenAuthoritieMap",canteenAuthoritieMap);
        modelAndView.setViewName("updateDeptAndAuthority");
        return modelAndView;
    }

}
