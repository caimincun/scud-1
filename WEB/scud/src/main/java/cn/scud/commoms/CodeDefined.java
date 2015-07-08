package cn.scud.commoms;

import java.util.HashMap;
import java.util.Map;

/**
 *错误码定义
 */
public class CodeDefined {

    public static final int SUCCESS = 0;

    // 通用错误码定义
    private static final int COMMON_CODE_PREFIX = 0;

    // 账户相关错误码定义
    private static final int ACCOUNT_CODE_PREFIX = 1000;
    public static final int ACCOUNT_USER_LOGIN= ACCOUNT_CODE_PREFIX + 1;           // 登录错误
    public static final int ACCOUNT_USER_EXIST_ERROR = ACCOUNT_CODE_PREFIX + 2;      // 用用户已经存在
//    public static final int ACCOUNT_USER_CHECKCODE_BLANK = ACCOUNT_CODE_PREFIX + 3;    // 验证码错误
//    public static final int ACCOUNT_USER_CHECKCODE_ERROR = ACCOUNT_CODE_PREFIX + 4;    // 验证码错误
//    public static final int ACCOUNT_USER_PHONE_IS_WRONG = ACCOUNT_CODE_PREFIX +5;     // 手机号格式错误
//    public static final int ACCOUNT_USER_PAYTYPE_WRONG = ACCOUNT_CODE_PREFIX+6;        //支付方式选择错误
//    public static final int ACCOUNT_USER_PAYPWD_ISNULL = ACCOUNT_CODE_PREFIX+7;         //未填写支付密码
//    public static final int ACCOUNT_USER_PAYPWD_ISNOTSET= ACCOUNT_CODE_PREFIX+8;        //未设置支付密码
//    public static final int ACCOUNT_USER_PAYPWD_ERROR= ACCOUNT_CODE_PREFIX+9;        //支付密码有误
//    public static final int ACCOUNT_USER_INSUFFICIENT_BALANCE= ACCOUNT_CODE_PREFIX+10;        //账户余额不足


    //用户信息相关错误码定义
    private static final int USER_CODE_PREFIX = 2000;
    public static final int USER_INFO_NULL = USER_CODE_PREFIX + 1;               //查询不到该用户
    public static final int USER_TOKEN_NULL = USER_CODE_PREFIX + 2;            // 用户userToken 为空

    //订单相关
    private static final int ORDER_CODE_PREFIX = 3000;
    public static final int ORDER_INFO_NULL = ORDER_CODE_PREFIX + 1;               //查询订单信息
    public static final  int ORDER_TOKEN_NULL = ORDER_CODE_PREFIX +2;               // 订单token 为空


    private static final Map<Integer, String> code2msg = new HashMap<Integer, String>();
    static {
        code2msg.put(SUCCESS, "ok");

//        code2msg.put(COMMON_PARAMS_ERROR, "参数错误");
//        code2msg.put(COMMON_NOT_AUTH, "请先登录");
//        code2msg.put(COMMON_SERVICE_ERROR,"服务器异常");
        // account
        code2msg.put(ACCOUNT_USER_LOGIN, "用户登录失败，请检查用户名或密码！");
        code2msg.put(ACCOUNT_USER_EXIST_ERROR, "对不起，该手机号码已经被注册！");
//        code2msg.put(ACCOUNT_USER_CHECKCODE_ERROR, "验证码错误");
//        code2msg.put(ACCOUNT_USER_CHECKCODE_BLANK, "验证码不能为空");
//

        //user
        code2msg.put(USER_INFO_NULL,"查询不到用户信息");
        code2msg.put(USER_TOKEN_NULL,"用户usterToken为空");

//        code2msg.put(ACCOUNT_USER_PHONE_IS_WRONG,"手机号格式不正确");



        //order
        code2msg.put(ORDER_INFO_NULL,"查询不到相应的订单");
        code2msg.put(ORDER_TOKEN_NULL,"订单token为空");


    }

    public static String getMessage(int code) {
        return code2msg.get(code);
    }
}
