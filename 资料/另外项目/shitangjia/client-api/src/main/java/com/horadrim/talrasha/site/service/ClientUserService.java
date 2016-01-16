package com.horadrim.talrasha.site.service;

import com.horadrim.talrasha.common.model.User;
import com.horadrim.talrasha.common.service.UserService;
import com.horadrim.talrasha.site.service.impl.ClientUserServiceImpl;

import java.math.BigDecimal;

/**
 * Created by Administrator on 2015/6/4.
 * 客户端用到的userservice
 */
public interface ClientUserService extends UserService {
    /**
     * 通过openId取得user 判断openid是否绑定user
     * @param openId 微信平台用户openid
     * @return user对象
     */
    User getByOpenId(String openId);

    /**
     * 用户付款
     * @param userId   用户id
     * @param payable  应付款
     * @param targetId 实体id（订单id）
     * @param payType 支付方式
     * @param payPwd  支付密码
     * @return 支付成功或失败 参考codeDefine
     */
    int payment(int userId, BigDecimal payable, ClientUserServiceImpl.PayType payType,String payPwd, int targetId);
    /**
     * 用户付款
     * @param userId   用户id
     * @param payable  应付款
     * @param targetId 实体id（订单id）
     * @param payType 支付方式
     * @return 支付成功或失败 参考codeDefine
     */
    int payment(int userId, BigDecimal payable,ClientUserServiceImpl.PayType payType, int targetId);


}
