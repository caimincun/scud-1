package com.horadrim.talrasha.common;

import java.util.HashMap;
import java.util.Map;

/**
 *错误码定义
 */
public class CodeDefined {

    public static final int SUCCESS = 0;

    // 通用错误码定义
    private static final int COMMON_CODE_PREFIX = 0;
    public static final int COMMON_PARAMS_ERROR = COMMON_CODE_PREFIX + 1;   // 参数错误
    public static final int COMMON_NOT_AUTH = COMMON_CODE_PREFIX + 2;       // 请登录
    public static final int COMMON_SERVICE_ERROR = COMMON_CODE_PREFIX+3;   //程序异常


    // 账户相关错误码定义
    private static final int ACCOUNT_CODE_PREFIX = 1000;
    public static final int ACCOUNT_USER_INPUT_NULL = ACCOUNT_CODE_PREFIX + 1;           // 用户名或密码为空
    public static final int ACCOUNT_USER_PASSWORD_ERROR = ACCOUNT_CODE_PREFIX + 2;      // 密码不正确
    public static final int ACCOUNT_USER_CHECKCODE_BLANK = ACCOUNT_CODE_PREFIX + 3;    // 验证码错误
    public static final int ACCOUNT_USER_CHECKCODE_ERROR = ACCOUNT_CODE_PREFIX + 4;    // 验证码错误
    public static final int ACCOUNT_USER_PHONE_IS_WRONG = ACCOUNT_CODE_PREFIX +5;     // 手机号格式错误
    public static final int ACCOUNT_USER_PAYTYPE_WRONG = ACCOUNT_CODE_PREFIX+6;        //支付方式选择错误
    public static final int ACCOUNT_USER_PAYPWD_ISNULL = ACCOUNT_CODE_PREFIX+7;         //未填写支付密码
    public static final int ACCOUNT_USER_PAYPWD_ISNOTSET= ACCOUNT_CODE_PREFIX+8;        //未设置支付密码
    public static final int ACCOUNT_USER_PAYPWD_ERROR= ACCOUNT_CODE_PREFIX+9;        //支付密码有误
    public static final int ACCOUNT_USER_INSUFFICIENT_BALANCE= ACCOUNT_CODE_PREFIX+10;        //账户余额不足
    public static final int ACCOUNT_USER_HAVE_BEEN_REGISTED= ACCOUNT_CODE_PREFIX+11;        //该用户已存在


    //用户信息相关错误码定义
    private static final int USER_CODE_PREFIX = 2000;
    public static final int USER_INFO_NULL = USER_CODE_PREFIX + 1;               //查询不到该用户

    //订单相关
    private static final int ORDER_CODE_PREFIX = 3000;
    public static final int ORDER_INFO_NULL = ORDER_CODE_PREFIX + 1;               //查询订单信息
    public static final int ORDER_CAN_NOT_CANCELED=ORDER_CODE_PREFIX+2;//订单不可撤销


    private static final Map<Integer, String> code2msg = new HashMap<>();
    static {
        code2msg.put(SUCCESS, "ok");

        code2msg.put(COMMON_PARAMS_ERROR, "参数错误");
        code2msg.put(COMMON_NOT_AUTH, "请先登录");
        code2msg.put(COMMON_SERVICE_ERROR,"服务器异常");
        // account
        code2msg.put(ACCOUNT_USER_INPUT_NULL, "输入不能为空");
        code2msg.put(ACCOUNT_USER_PASSWORD_ERROR, "密码错误");
        code2msg.put(ACCOUNT_USER_CHECKCODE_ERROR, "验证码错误");
        code2msg.put(ACCOUNT_USER_CHECKCODE_BLANK, "验证码不能为空");

        code2msg.put(ACCOUNT_USER_PAYTYPE_WRONG, "支付方式选择错误");
        code2msg.put(ACCOUNT_USER_PAYPWD_ISNULL, "未填写支付密码");
        code2msg.put(ACCOUNT_USER_PAYPWD_ISNOTSET, "未设置支付密码");
        code2msg.put(ACCOUNT_USER_PAYPWD_ERROR, "支付密码有误");
        code2msg.put(ACCOUNT_USER_INSUFFICIENT_BALANCE, "账户余额不足");

        //user
        code2msg.put(USER_INFO_NULL,"查询不到用户信息");
        code2msg.put(ACCOUNT_USER_PHONE_IS_WRONG,"手机号格式不正确");

        //order
        code2msg.put(ORDER_INFO_NULL,"查询不到相应的订单");
        code2msg.put(ACCOUNT_USER_HAVE_BEEN_REGISTED,"该手机号已被注册");


    }

    public static String getMessage(int code) {
        return code2msg.get(code);
    }
}
