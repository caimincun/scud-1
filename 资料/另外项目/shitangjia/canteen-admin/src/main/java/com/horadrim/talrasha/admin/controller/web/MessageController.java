package com.horadrim.talrasha.admin.controller.web;

import com.horadrim.talrasha.admin.controller.result.DataTableRes;
import com.horadrim.talrasha.admin.model.CanteenUser;
import com.horadrim.talrasha.admin.util.DatatableParamPojo;
import com.horadrim.talrasha.admin.util.ParseDatatableParamUtil;
import com.horadrim.talrasha.common.CommonParamDefined;
import com.horadrim.talrasha.common.model.CanteenMessage;
import com.horadrim.talrasha.common.response.json.AbstractJsonRes;
import com.horadrim.talrasha.common.response.json.ErrorJsonRes;
import com.horadrim.talrasha.common.response.json.SuccessJsonRes;
import com.horadrim.talrasha.common.service.CanteenMessageService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by Administrator on 2015/7/9.
 */
@RequestMapping("/message")
@Controller
public class MessageController {
    @Resource
    private CanteenMessageService canteenMessageService;

    /*@RequestMapping("/notify")
    @ResponseBody
    public AbstractJsonRes sendMsgToCanteen(int canteenId,int msgType){
        new TextMessageHandler().sendMessageToCanteen(canteenId,new TextMessage(msgType+""));
        return new SuccessJsonRes();
    }*/

    @RequestMapping("/toHistoryMsgPage")
    public String toHistoryMsgPage(int messageType,Model model){
        model.addAttribute("messageType",messageType);
        return "messageHistory";
//        return "testData";
    }
    @RequestMapping("/list")
    @ResponseBody
    public DataTableRes listMessage(int messageType,int isRead,String aoData,HttpSession session){
        final String hql ="FROM CanteenMessage WHERE canteenId=? AND messageType=? AND isRead=?";
        DatatableParamPojo paramPojo = ParseDatatableParamUtil.parse(aoData);
        if (paramPojo==null)
            return new DataTableRes();

//       //加载测试数据
//        List<Map<String,Object>> maps = new ArrayList<>();
//        for (int i=0;i<paramPojo.getiDisplayLength();i++){
//            Map<String,Object> map = new HashMap<>();
//            map.put("planName",paramPojo.getiDisplayStart());
//            map.put("planId","1111");
//            maps.add(map);
//        }
//        DataTableRes res = new DataTableRes();
//        res.setsEcho(paramPojo.getsEcho());
//        res.setiTotalRecords(40);
//        res.setiTotalDisplayRecords(40);
//        res.setAaData(maps);
        Object params[] = new Object[]{((CanteenUser)session.getAttribute(CommonParamDefined.USER)).getCanteenId(),messageType,isRead};
        List<CanteenMessage> messages =canteenMessageService.list(hql+" order by id desc",
                paramPojo.getiDisplayStart()/paramPojo.getiDisplayLength(),paramPojo.getiDisplayLength(),
               params);
        int size = canteenMessageService.count("SELECT COUNT(id) "+hql,
                params).intValue();
        DataTableRes res = new DataTableRes();
        res.setsEcho(paramPojo.getsEcho());
        res.setiTotalRecords(size);
        res.setiTotalDisplayRecords(size);
        res.setAaData(messages);
        return res;
    }

    @RequestMapping("/updateMsg")
    @ResponseBody
    public AbstractJsonRes updateMsg(int msgId){
        CanteenMessage message = canteenMessageService.get(msgId);
        if (message==null){
            return new ErrorJsonRes(4,"没有相关消息");
        }
        message.setIsRead(1);
        canteenMessageService.update(message);
        return new SuccessJsonRes();
    }


}
