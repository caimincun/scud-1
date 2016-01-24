package com.horadrim.talrasha.admin.controller.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.horadrim.talrasha.admin.controller.result.DataTableRes;
import com.horadrim.talrasha.admin.model.CanteenUser;
import com.horadrim.talrasha.common.CommonParamDefined;
import com.horadrim.talrasha.common.model.ConsumeHistory;
import com.horadrim.talrasha.common.service.ConsumeHistoryService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2015/7/22.
 */
@Controller
@RequestMapping("/consume")
public class ConsumeController {

    @Resource
    private ConsumeHistoryService consumeHistoryService;

    @RequestMapping("/listConsume")
    @ResponseBody
    public DataTableRes listConsume(HttpSession session,@RequestParam String aoData,@RequestParam(required=false)Integer sta,@RequestParam(required=false)Integer pay){
        System.out.println("后台传递参数："+sta+"  "+pay);
        Integer accountTpye = sta;
        Integer consumeType = pay;

        String sEcho = null;
        int iDisplayStart = 0; // 起始索引
        int iDisplayLength = 0; // 每页显示的行数
        ObjectMapper mapper = new ObjectMapper();
        List<Map<String,Object>> mapList =null;
        try {
            mapList = mapper.readValue(aoData,mapper.getTypeFactory().constructParametricType(List.class, Map.class));
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (mapList==null){
            //错误处理。。。
            return null;
        }
        for (Map obj : mapList){
            if (obj.get("name").equals("sEcho"))
                sEcho = obj.get("value").toString();

            if (obj.get("name").equals("iDisplayStart"))
                iDisplayStart = (int) obj.get("value");

            if (obj.get("name").equals("iDisplayLength"))
                iDisplayLength = (int) obj.get("value");
        }
        String contSql = "";
        CanteenUser user = (CanteenUser)session.getAttribute(CommonParamDefined.USER);
        if(accountTpye == null && consumeType ==null){
            accountTpye = 5;
            consumeType = 5;  //参数调用传递不能为空，如果传递的参数为null,则赋一个不能是前台传递的值
            contSql = "select count(id) from ConsumeHistory where canteenId ="+user.getCanteenId();
        } else{
            contSql = "select count(id) from ConsumeHistory where canteenId ="+user.getCanteenId()+" and accountType ="+accountTpye+" and consumeType ="+consumeType;
        }
        int size = consumeHistoryService.count(contSql,null).intValue();
        List<Map<String,Object>> consumeHistoryList = consumeHistoryService.queryConsumePage(user.getId(),accountTpye,consumeType,iDisplayStart / iDisplayLength, iDisplayLength);
        List<String[]> lst = new ArrayList<String[]>();
        for(Map<String,Object> consumeHistory:consumeHistoryList){
            String accountType = "";
            System.out.println(consumeHistory.get("account_type")+"  "+ConsumeHistory.AccountType.SHITANG);
            if(consumeHistory.get("account_type") == 1){
                accountType = "食堂账户";
            }
            else if(consumeHistory.get("account_type") == 2){
                accountType = "平台账户";
            }
            else if(consumeHistory.get("account_type") == 3){
                accountType = "线下转账";
            }
            String consumeTpye = "";
            if(consumeHistory.get("consume_type") == 1){
                consumeTpye = "充值";
            }else if(consumeHistory.get("consume_type") == 2){
                consumeTpye = "撤销订单扣款";
            }else if(consumeHistory.get("consume_type") == 3){
                consumeTpye = "点餐扣费";
            }else if(consumeHistory.get("consume_type") == 4){
                consumeTpye = "买菜扣费";
            }else if(consumeHistory.get("consume_type") == 5) {
                consumeTpye = "买菜时冻结金额";
            }
            else if(consumeHistory.get("consume_type") == 6) {
                consumeTpye = "买菜支付解冻";
            }


            String[] dts = {consumeHistory.get("nickname").toString(),accountType,consumeTpye,consumeHistory.get("balance").toString(),consumeHistory.get("consume_time").toString().substring(0,19)};
            lst.add(dts);
        }
        DataTableRes res = new DataTableRes();
        res.setsEcho(sEcho);
        res.setiTotalRecords(size);
        res.setiTotalDisplayRecords(size);
        res.setAaData(lst);
        return res;
    }
}
