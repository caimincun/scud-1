package com.horadrim.talrasha.admin.controller.web;

import com.horadrim.talrasha.admin.model.CanteenAuthority;
import com.horadrim.talrasha.admin.model.CanteenDept;
import com.horadrim.talrasha.admin.model.CanteenUser;
import com.horadrim.talrasha.admin.service.CanteenDeptService;
import com.horadrim.talrasha.admin.service.CanteenUserService;
import com.horadrim.talrasha.common.CodeDefined;
import com.horadrim.talrasha.common.CommonParamDefined;
import com.horadrim.talrasha.common.model.*;
import com.horadrim.talrasha.common.response.json.AbstractJsonRes;
import com.horadrim.talrasha.common.response.json.ErrorJsonRes;
import com.horadrim.talrasha.common.service.*;
import com.horadrim.talrasha.common.util.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.bind.SchemaOutputResolver;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by Administrator on 2015/6/10.
 */
@Controller
@RequestMapping("/canteenUser")
public class CanteenUsercontroller {
    @Resource
    private CanteenUserService canteenUserService;

    @Resource
    private ProductCategoryService productCategoryService;

    @Resource
    private VegetableCategoryService vegetableCategoryService;

    @Resource
    private CanteenMessageService canteenMessageService;

    @Resource
    private CanteenDeptService canteenDeptService;

    @Resource
    private QuestionCategoryService questionCategoryService;
    @Resource
    private UserService userService;

    /**
     * 注销登录
     * @param session
     * @return
     */
    @RequestMapping("/logout")
    public ModelAndView logout(HttpSession session){
        session.removeAttribute(CommonParamDefined.USER);
        ModelAndView modelAndView =new ModelAndView();
        modelAndView.setViewName("redirect:/canteenUser/toCheckUser");
        return modelAndView;
    }

    /**
     * 跳转到输入用户名，检查权限页面
     * @return
     */
    @RequestMapping("/toCheckUser")
    public ModelAndView toCheckUser(HttpServletRequest request){
        return new ModelAndView("checkCanteenUser");
    }


    @RequestMapping("/addUser")
    public ModelAndView addUser(HttpSession session,CanteenUser canteenUser){
        canteenUser.setCanteenId(((CanteenUser) session.getAttribute(CommonParamDefined.USER)).getCanteenId());
        canteenUserService.save(canteenUser);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/canteenUser/choicepage?id=30");
        return modelAndView;
    }

    /**
     * 根据用户信息展示不同的权限信息
     * @param canteenUser
     * @return
     */
    @RequestMapping("/showAuthority")
    public ModelAndView showAuthority(CanteenUser canteenUser,String rember,HttpSession session,HttpServletResponse response){
        ModelAndView modelAndView = new ModelAndView();
        CanteenUser cuser = canteenUserService.checkCanteenUser(canteenUser);
        if(cuser == null){
            modelAndView.addObject("error","对不起，用户名或密码错误,请重新输入！");
            modelAndView.setViewName("checkCanteenUser");
            return modelAndView;
        }
       session.setAttribute(CommonParamDefined.USER, cuser);
        if (!StringUtils.isBlank(rember)&&"1".equals(rember)) {
            //保存cookie
        Cookie ncookie = new Cookie("name", canteenUser.getName());
        ncookie.setMaxAge(604800);
        response.addCookie(ncookie);
        Cookie pcookie = new Cookie("passWord", canteenUser.getPassword());
        pcookie.setMaxAge(604800);
        response.addCookie(pcookie);
    }
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
        //查询未读消息
        for (Map<String,Object> map :canteenMessageService.countNotReadMsg(cuser.getCanteenId())){
            int messageType = (int)map.get("messageType");
            if (messageType==CanteenMessage.MessageType.DIANCAN_ORDER.ordinal()){
                modelAndView.addObject("diancanNum",map.get("number"));
            }else if (messageType==CanteenMessage.MessageType.SHUCAI_ORDER.ordinal()){
                modelAndView.addObject("shucaiNum",map.get("number"));
            }
        }
        modelAndView.addObject("canteenAuthoritieMap",canteenAuthoritieMap);
        modelAndView.addObject("canteenId",cuser.getCanteenId());
        modelAndView.setViewName("index");
//        modelAndView.setViewName("showAuthority");
//        session.setAttribute("canteenAuthoritieMap",canteenAuthoritieMap);
        return modelAndView;
    }

    /**
     *  根据前台传递过来的不同参数跳转到不同的页面
     * @param id
     * @return
     */
    @RequestMapping("/choicepage")
    public ModelAndView hrefdifPage(int id,HttpSession session){
        ModelAndView modelAndView = new ModelAndView();
        CanteenUser canteenUser = (CanteenUser)session.getAttribute(CommonParamDefined.USER);
        switch (id){
            case 3:
                modelAndView.setViewName("qucan");
                break;
            case 8:
                List<ProductCategory> productCategorys = productCategoryService.listProduct(canteenUser.getCanteenId());
                modelAndView.addObject("productCategorys", productCategorys);
                modelAndView.setViewName("canteenProductCategory");
                break;
            case 9:
                modelAndView.setViewName("product");
                break;
            case 10:
                List<ProductCategory> productCategoryList = productCategoryService.listProduct(canteenUser.getCanteenId());
                modelAndView.addObject("productCategoryList", productCategoryList);
                modelAndView.setViewName("addProduct");
                break;
            case 11:
                modelAndView.setViewName("redirect:/module/listModule");
                break;
            case 13:
                modelAndView.setViewName("vegetableList");
                break;
            case 14:
                String hqlcate = "select * from qc_vegetable_category where canteen_id ="+canteenUser.getCanteenId();
                List<VegetableCategory> vegetableCategories = vegetableCategoryService.listBySQL(hqlcate,null);
                modelAndView.addObject("vegetableCategories",vegetableCategories);
                modelAndView.setViewName("store");
                break;
            case 15:
                modelAndView.setViewName("orderList");
                break;
            case 16:
                modelAndView.setViewName("vegetableOrderList");
                break;
            case 17:
                modelAndView.setViewName("qucai");
                break;
            case 18:
                modelAndView.setViewName("redirect:/comment/toCommentPage");
                break;
            case 19:
                modelAndView.setViewName("consumehistory");
                break;
            case 21:
                CanteenUser user = (CanteenUser)session.getAttribute(CommonParamDefined.USER);
                List<CanteenDept> canteenDeptList = canteenDeptService.listDept(user.getCanteenId());
                modelAndView.addObject("canteenDeptList",canteenDeptList);
                modelAndView.setViewName("deptManager");
                break;
            case 22:
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
                modelAndView.addObject("canteenAuthoritieMap",canteenAuthoritieMap);
                modelAndView.setViewName("addDept");
                break;
            case 24:
                CanteenUser canteenUser1 = (CanteenUser)session.getAttribute(CommonParamDefined.USER);
                String hql = "from QuestionCategory where status = 0 and canteenId ="+canteenUser1.getCanteenId();
                List<QuestionCategory> questionCategoryList = questionCategoryService.list(hql,null);
                modelAndView.addObject("questionCategoryList",questionCategoryList);
                modelAndView.setViewName("addQuestion");
                break;
            case 25:
                CanteenUser canteenUser2 = (CanteenUser)session.getAttribute(CommonParamDefined.USER);
                String hql2 = "from QuestionCategory where canteen_id = "+canteenUser2.getCanteenId();
                List<QuestionCategory> questionCategoryList2 = questionCategoryService.list(hql2,null);
                modelAndView.addObject("questionCategoryList",questionCategoryList2);
                modelAndView.setViewName("questionCateManager");
                break;
            case 26:
                modelAndView.setViewName("/addQuestionCategory");
                break;
            case 27:
                modelAndView.setViewName("vegeAllOrder");
                break;
            case 30:
                String sql3 = "select u.name,u.phone_number,d.dept_name FROM qc_canteenuser u LEFT JOIN qc_canteendept d on u.dept_id = d.id WHERE  u.canteen_id ="+((CanteenUser) session.getAttribute(CommonParamDefined.USER)).getCanteenId();
                List<Map<Object,Object>> canteenUserList = canteenUserService.listFieldBySQL(sql3,null);
                modelAndView.addObject("canteenUserList",canteenUserList);
                String hql3 = "from CanteenDept where canteenId ="+((CanteenUser) session.getAttribute(CommonParamDefined.USER)).getCanteenId();
                List<CanteenDept> deptList = canteenDeptService.list(hql3,null);
                modelAndView.addObject("deptList",deptList);
                modelAndView.setViewName("administratorManage");
                break;

            case 29:
                modelAndView.setViewName("user");

                break;
            default:
                modelAndView.setViewName("testData");
        }
        return modelAndView;
    }



}
