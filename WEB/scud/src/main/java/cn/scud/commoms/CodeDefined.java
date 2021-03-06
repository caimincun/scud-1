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


    //用户信息相关错误码定义
    private static final int USER_CODE_PREFIX = 2000;
    public static final int USER_INFO_NULL = USER_CODE_PREFIX + 1;               //查询不到该用户
    public static final int USER_TOKEN_NULL = USER_CODE_PREFIX + 2;            // 用户userToken 为空

    //订单相关
    private static final int ORDER_CODE_PREFIX = 3000;
    public static final int ORDER_INFO_NULL = ORDER_CODE_PREFIX + 1;               //查询订单信息
    public static final  int ORDER_TOKEN_NULL = ORDER_CODE_PREFIX +2;               // 订单token 为空
    public static final  int ORDER_TOKEN_JIEDAN = ORDER_CODE_PREFIX +3;               // 已经表达接单意向


    // 异常错误相关
    private static final int EXCEPTION_CODE_PREFIX = 3000;
    public static final int EXCEPTION_CODE_NULL = EXCEPTION_CODE_PREFIX+1;          // 空指针错误
    public static final int EXCEPTION_CODE_NUM = EXCEPTION_CODE_PREFIX+2;           // num 转换异常
    public static final int EXCEPTION_CODE_ERROR = EXCEPTION_CODE_PREFIX+3;          // 程序出现异常
    public static final int EXCEPTION_CODE_PICTURE_ERROR = EXCEPTION_CODE_PREFIX+4;  // 图片修改异常
    public static final int EXCEPTION_CODE_SEESION_ERROR = EXCEPTION_CODE_PREFIX+5;     // session 过期
    public static final int EXCEPTION_CODE_DATA_ERROR = EXCEPTION_CODE_PREFIX+6;          // 数据转换出现异常，用户数据实体转换保存
    public static final int EXCEPTION_CODE_DINGWER_ERROR = EXCEPTION_CODE_PREFIX+7;          // 经纬度定位异常，请检查网络


    // 商铺开启返回数据
    public static final int EXCEPTION_CODE_STORE = 4000;
    public static final int EXCEPTION_CODE_STORE_DELETEORDER_ERROR = EXCEPTION_CODE_STORE+1; // 删除订单失败


    private static final Map<Integer, String> code2msg = new HashMap<Integer, String>();
    static {
        code2msg.put(SUCCESS, "ok");

        // account
        code2msg.put(ACCOUNT_USER_LOGIN, "用户登录失败，请检查用户名或密码！");
        code2msg.put(ACCOUNT_USER_EXIST_ERROR, "对不起，该手机号码已经被注册！");

        //user
        code2msg.put(USER_INFO_NULL,"查询不到用户信息");
        code2msg.put(USER_TOKEN_NULL,"用户usterToken为空");


        //order
        code2msg.put(ORDER_INFO_NULL,"查询不到相应的订单");
        code2msg.put(ORDER_TOKEN_NULL,"订单token为空");
        code2msg.put(ORDER_TOKEN_JIEDAN,"你已经对此需求表达接单意向，无需重复操作！");

        // excepiton
        code2msg.put(EXCEPTION_CODE_PREFIX,"程序出现异常");
        code2msg.put(EXCEPTION_CODE_NULL,"空指针异常");
        code2msg.put(EXCEPTION_CODE_NUM,"number数字转换异常");
        code2msg.put(EXCEPTION_CODE_ERROR,"程序出现异常");
        code2msg.put(EXCEPTION_CODE_PICTURE_ERROR,"图片上传异常，请重新上传！");
        code2msg.put(EXCEPTION_CODE_SEESION_ERROR,"你长时间未登陆，请重新登陆！！！");
        code2msg.put(EXCEPTION_CODE_DATA_ERROR,"数据保存出现异常，请重试");
        code2msg.put(EXCEPTION_CODE_DINGWER_ERROR,"地图定位异常，请检查网络重试");

        // 商铺判断标识
        code2msg.put(EXCEPTION_CODE_STORE,"请开启商铺");
        code2msg.put(EXCEPTION_CODE_STORE_DELETEORDER_ERROR,"删除订单失败");


    }

    public static String getMessage(int code) {
        return code2msg.get(code);
    }
}
