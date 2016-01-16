package com.horadrim.talrasha.admin.controller.web;

import com.horadrim.talrasha.admin.CanteenCodeDefined;
import com.horadrim.talrasha.admin.controller.result.AwardRes;
import com.horadrim.talrasha.admin.model.Awards;
import com.horadrim.talrasha.common.model.QuestionUser;
import com.horadrim.talrasha.admin.service.AwardsService;
import com.horadrim.talrasha.common.service.QuestionUserService;
import com.horadrim.talrasha.common.CodeDefined;
import com.horadrim.talrasha.common.response.json.AbstractJsonRes;
import com.horadrim.talrasha.common.response.json.ErrorJsonRes;
import com.horadrim.talrasha.common.response.json.SuccessJsonRes;
import com.horadrim.talrasha.common.util.BaeSmsUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.*;

/**
 * Created by Administrator on 2015/6/11.
 */
@RequestMapping("/awards")
@Controller
public class AwardsController {
    @Resource
    private QuestionUserService questionUserService;
    @Resource(name = "awardsService")
    private AwardsService awardsService;
//    private static final List<Integer> awards; //油 1 米2 纸3
//    private static final Map<Integer,Integer> newAwards;
//    static {
//        newAwards = new LinkedHashMap<>();
//        for (int i=1 ; i<=20 ; i++){
//            if (i <= 5) {
//                newAwards.put(1000+i,1);
//            } else if (i <= 10) {
//                newAwards.put(1000 + i, 2);
//            } else {
//                newAwards.put(1000+i,1);
//            }
//        }
//
//        //放入奖品
//        awards = new ArrayList<>();
//        for (int i = 1; i <= 20; i++) {
//            if (i <= 5) {
//                awards.add(1);
//            } else if (i <= 10) {
//                awards.add(2);
//            } else {
//                awards.add(3);
//            }
//        }
//    }
    @RequestMapping("/toAwardPage")
    public String toAwardPage(){
        return "new_dazhuanpan";
    }
//    /**
//     * 查询剩余
//     *
//     * @return
//     */
//    @RequestMapping("/surplus")
//    @ResponseBody
//    public String surplusAwards() {
//        int you = 0, mi = 0, zhi = 0;
//        for (int index : awards) {
//            if (index == 1) {
//                you += 1;
//            } else if (index == 2) {
//                mi += 1;
//            } else {
//                zhi += 1;
//            }
//        }
//        return "剩余油： " + you + " 瓶 米： " + mi + " 袋 纸： " + zhi + "袋";
//    }

    @RequestMapping("/choujiang")
    @ResponseBody
    public AbstractJsonRes chouJiang(HttpSession session) {
        Integer userId = (Integer) session.getAttribute(CanteenCodeDefined.QUESTION_USER_ID);
        if (userId==null)
            return new ErrorJsonRes(CodeDefined.COMMON_NOT_AUTH);
        QuestionUser questionUser = questionUserService.get(userId);
        if (questionUser==null){
            return new ErrorJsonRes(CodeDefined.COMMON_NOT_AUTH);
        }
        if (questionUser.getIsChoujiang()==1){
            //已经参加过
            return new ErrorJsonRes(4,"谢谢!您已经参加过抽奖");
        }
        synchronized (this) {

            long count = questionUserService.count();//从数据库
            long size = awardsService.countNotUse();
            Awards award = null;
            if (size == 0) {
                System.out.println("抽完了");
            } else {
                Random random = new Random();
                if (count <= 30) {
                    //前30人概率为1/3
                    if (0 == random.nextInt(3)) {
                        award = getAwards();
                    }

                } else if (count <= 50) {
                    //30 到 50 人中奖概率为1/4
                    if (0 == random.nextInt(4)) {
                        award = getAwards();
                    }
                } else {
                    if (random.nextInt(12) < 5) {
                        award = getAwards();
                    }
                }
            }

            questionUser.setIsChoujiang(1);
            questionUser.setAward(award==null?0:award.getAward());
            questionUser.setIsConfirm(0);
            questionUserService.update(questionUser);
            AwardRes awardRes = new AwardRes();
            awardRes.setAward(award==null?0:award.getAward());
            awardRes.setAwardCode(award==null?0:award.getAwardCode());
            if(award!=null){
                award.setIsUse(1);
                awardsService.update(award);
            }

            return awardRes;
        }

    }
    @RequestMapping("/confirm")
    @ResponseBody
    public AbstractJsonRes confirmAward(HttpSession session,int award,int awardCode){
        if (award<=0||awardCode<=0){
            return new ErrorJsonRes(CodeDefined.ACCOUNT_USER_PHONE_IS_WRONG);
        }
        Integer questionUserId = (Integer) session.getAttribute(CanteenCodeDefined.QUESTION_USER_ID);
        if (questionUserId==null){
            return new ErrorJsonRes(CodeDefined.COMMON_NOT_AUTH);
        }
        QuestionUser questionUser = questionUserService.get(questionUserId);
        if (questionUser==null){
            return new ErrorJsonRes(CodeDefined.COMMON_NOT_AUTH);
        }
        if (questionUser.getAward()!=award||questionUser.getIsConfirm()==1){
            return new ErrorJsonRes(4,"已经确认过奖品");
        }
        //保存
        questionUser.setIsConfirm(1);
        questionUserService.update(questionUser);
        //发送短信
        Map<String,String> map = new HashMap<>();
        map.put("code",""+awardCode);
        map.put("hour",""+24);
        if(!BaeSmsUtil.send(BaeSmsUtil.CHECK_CODE_03,map,questionUser.getPhoneNumber())){
         return new ErrorJsonRes(4,"短信发送失败!请联系工作人员");
        }
        return new SuccessJsonRes();
    }
    private Awards getAwards() {
        return awardsService.randAward();
    }
}
