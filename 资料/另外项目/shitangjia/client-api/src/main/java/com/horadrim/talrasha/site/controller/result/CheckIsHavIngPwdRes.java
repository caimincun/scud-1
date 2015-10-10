package com.horadrim.talrasha.site.controller.result;

import com.horadrim.talrasha.common.response.json.SuccessJsonRes;

/**
 * Created by Administrator on 2015/6/4.
 * 检查用户是否有支付密码返回结果
 */
public class CheckIsHavIngPwdRes extends SuccessJsonRes {
     private String phone;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
