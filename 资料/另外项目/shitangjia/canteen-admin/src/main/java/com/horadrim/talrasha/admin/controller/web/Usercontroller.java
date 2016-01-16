package com.horadrim.talrasha.admin.controller.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.horadrim.talrasha.admin.controller.result.DataTableRes;
import com.horadrim.talrasha.admin.model.CanteenUser;
import com.horadrim.talrasha.common.CommonParamDefined;
import com.horadrim.talrasha.common.model.Order;
import com.horadrim.talrasha.common.model.User;
import com.horadrim.talrasha.common.service.OrderService;
import com.horadrim.talrasha.common.service.UserService;
import com.horadrim.talrasha.common.service.dto.QueryOrdersParam;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2015/6/10.
 * 此controller 目前暂时无用
 */
@Controller
@RequestMapping("/user")
public class Usercontroller {
    @Resource
    private UserService userService;
    @Resource
    private OrderService orderService;

    @RequestMapping("/UserlistPage")
    @ResponseBody
    public DataTableRes tableDemoAjax(@RequestParam String aoData, HttpSession session) {
        String sEcho = null;
        int iDisplayStart = 0; // 起始索引
        int iDisplayLength = 0; // 每页显示的行数
        ObjectMapper mapper = new ObjectMapper();
        List<Map<String, Object>> mapList = null;
        try {
            mapList = mapper.readValue(aoData, mapper.getTypeFactory().constructParametricType(List.class, Map.class));
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (mapList == null) {
            //错误处理。。。
            return null;
        }
        for (Map obj : mapList) {
            if (obj.get("name").equals("sEcho"))
                sEcho = obj.get("value").toString();

            if (obj.get("name").equals("iDisplayStart"))
                iDisplayStart = (int) obj.get("value");

            if (obj.get("name").equals("iDisplayLength"))
                iDisplayLength = (int) obj.get("value");
        }

        List<String[]> lst = new ArrayList<String[]>();
        List<User> usertList = new ArrayList<User>();
        CanteenUser canteenUser = (CanteenUser) session.getAttribute(CommonParamDefined.USER);
        usertList = this.userService.queryUsers(canteenUser.getCanteenId(),iDisplayStart / iDisplayLength, iDisplayLength,0);
String sql="SELECT COUNT(id)FROM qc_user ;";
        int size=this.userService.countBySQL(sql,null).intValue();
        if (usertList != null) {
            for (int i = 0; i < usertList.size(); i++) {

                String sex = "";
                if (usertList.get(i).getSex() == 1) {
                    sex = "男";
                }
                if (usertList.get(i).getSex() == 2) {
                    sex = "女";
                } else if (usertList.get(i).getSex() == 0) {
                    sex = "未知";
                }
                String[] dts = {"<img width=50 height=40 src=" + usertList.get(i).getHeadImg() + ">", usertList.get(i).getNickName(), sex,usertList.get(i).getPhone(), "<input type='button' value='详情' onclick='userDetail(" + usertList.get(i).getId() + ")'>"};
                lst.add(dts);
            }
        }
        DataTableRes res = new DataTableRes();
        res.setsEcho(sEcho);
        res.setiTotalRecords(size);
        res.setiTotalDisplayRecords(size);
        res.setAaData(lst);
        return res;
    }
    @RequestMapping("/toTest")
    public ModelAndView toTest(int id){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("oDetail");
        modelAndView.addObject("id",id);
        return modelAndView;
    }
    @RequestMapping("/orderDetail")//到用户详情界面
    @ResponseBody
    public DataTableRes getOrderDetail(@RequestParam String aoData, int id) {
        String sEcho = null;
        int iDisplayStart = 0; // 起始索引
        int iDisplayLength = 0; // 每页显示的行数
        ObjectMapper mapper = new ObjectMapper();
        List<Map<String, Object>> mapList = null;
        try {
            mapList = mapper.readValue(aoData, mapper.getTypeFactory().constructParametricType(List.class, Map.class));
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (mapList == null) {
            //错误处理。。。
            return null;
        }
        for (Map obj : mapList) {
            if (obj.get("name").equals("sEcho"))
                sEcho = obj.get("value").toString();

            if (obj.get("name").equals("iDisplayStart"))
                iDisplayStart = (int) obj.get("value");

            if (obj.get("name").equals("iDisplayLength"))
                iDisplayLength = (int) obj.get("value");
        }

        List<String[]> lst = new ArrayList<String[]>();
        List<Order> orderList = new ArrayList<Order>();

        orderList = this.orderService.queryOrders(id, iDisplayStart / iDisplayLength, iDisplayLength,0);
        String sql="SELECT COUNT(o.order_num)from qc_order o where o.user_id=?";
        int    size=orderService.countBySQL(sql,new Object[]{id}).intValue();
        if (orderList != null) {
            for (int i = 0; i < orderList.size(); i++) {

                String[] dts = {orderList.get(i).getOrderNum(), orderList.get(i).getOrderDate().toString(),orderList.get(i).getTotalPrice().toString(),"<input type='button' value='详情' onclick='userOrderDetail(" + orderList.get(i).getId() + ")'>"};
                lst.add(dts);
            }
        }
        DataTableRes res = new DataTableRes();
        res.setsEcho(sEcho);
        res.setiTotalRecords(size);
        res.setiTotalDisplayRecords(size);
        res.setAaData(lst);
        return res;
    }

}
