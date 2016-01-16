package com.horadrim.talrasha.site.controller.web;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.horadrim.talrasha.common.CodeDefined;
import com.horadrim.talrasha.common.CommonParamDefined;
import com.horadrim.talrasha.common.model.ConsumeHistory;
import com.horadrim.talrasha.common.model.User;
import com.horadrim.talrasha.common.model.Vegetable;
import com.horadrim.talrasha.common.model.VegetableCategory;
import com.horadrim.talrasha.common.response.json.AbstractJsonRes;
import com.horadrim.talrasha.common.response.json.ErrorJsonRes;
import com.horadrim.talrasha.common.response.json.SuccessJsonRes;
import com.horadrim.talrasha.common.service.UserService;
import com.horadrim.talrasha.common.service.VegetableCategoryService;
import com.horadrim.talrasha.common.service.VegetableService;
import com.horadrim.talrasha.common.util.StringUtils;
import com.horadrim.talrasha.site.controller.request.CartItem;
import com.horadrim.talrasha.site.service.ClientVegetableOrderService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2015/7/8.
 */
@Controller
@RequestMapping("/vegetable")
public class VegetableController {
    @Resource(name = "vegetableService")
    private VegetableService vegetableService;
    @Resource(name = "vegetableCategoryService")
    private VegetableCategoryService categoryService;
    @Resource
    private ClientVegetableOrderService clientVegetableOrderService;
    @RequestMapping("/list")
    public String list(HttpSession session ,Model model){
        List<Vegetable> vegetables = vegetableService.listByCanteen(
                (int) session.getAttribute(CommonParamDefined.CANTEEN_ID),0);
        List<VegetableCategory> categories = categoryService.getVCategoryes();
        Map<Integer,CartItem> cart=(Map<Integer,CartItem>)session.getAttribute(CommonParamDefined.VEGETABLE_CART);
        Integer productCount=0;
        if(null!=cart){
            for(Map.Entry<Integer,CartItem> entry:cart.entrySet()){
                productCount+=entry.getValue().getCount();
            }
        }
        model.addAttribute("productCount",productCount);
        model.addAttribute("vegetables", JSON.toJSON(vegetables));
        model.addAttribute("categories", JSON.toJSON(categories));
        return "vegetable_list";
    }

    /**
     * 支付订单
     * @param orderIds  订单号集合 用逗号分隔开
     * @param payType   账号类型 食堂，平台
     * @param payPwd   支付密码
     */
    @RequestMapping("/payOrder")
    @ResponseBody
    public AbstractJsonRes payOrder(String orderIds,int payType,String payPwd,HttpSession session) {
        ConsumeHistory.AccountType accountType = ConsumeHistory.AccountType.getAccount(payType);
        if (accountType == null)
            return new ErrorJsonRes(CodeDefined.ACCOUNT_USER_PAYTYPE_WRONG);
        try {
            int code = clientVegetableOrderService.payOrder((int)session.getAttribute(CommonParamDefined.USERID),
                    accountType, StringUtils.parseToIntArrayBycomma(orderIds),payPwd);
            if (code==0)
                return new SuccessJsonRes();
            return new ErrorJsonRes(code);
        } catch (Exception e) {
            return new ErrorJsonRes(CodeDefined.COMMON_SERVICE_ERROR);
        }

    }
}
