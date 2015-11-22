package cn.scud.main.user.controller;

import cn.scud.commoms.CodeDefined;
import cn.scud.commoms.CommonParamDefined;
import cn.scud.commoms.jsonModel.JsonPioSimple;
import cn.scud.commoms.response.*;
import cn.scud.main.user.model.User;
import cn.scud.main.user.model.UserInfo;
import cn.scud.main.user.service.UserService;
import cn.scud.utils.BosHelper;
import cn.scud.utils.LbsHelper;
import cn.scud.utils.StreamSerializer;
import cn.scud.utils.WebUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by cmc on 14-12-9.
 */
@Controller
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserService userService;


    /**
     * 判断session 是否过期
     * @param session
     * @return
     */
    @RequestMapping("/decideSessionExist")
    @ResponseBody
    public OperatorResponse decideSessionExist(HttpSession session){
        if(null == session.getAttribute(CommonParamDefined.USER_TOKEN)){
            return new ErrorJsonRes(CodeDefined.EXCEPTION_CODE_SEESION_ERROR,CodeDefined.getMessage(CodeDefined.EXCEPTION_CODE_SEESION_ERROR));
        }
        return new SuccessJsonRes();
    }


    /**
     * 用户注册
     * @return
     */
    @RequestMapping(value="/add")
    @ResponseBody
    public OperatorResponse saveUser(HttpServletRequest request,HttpServletResponse response) throws Exception {
        User user =  StreamSerializer.streamSerializer(request.getInputStream(), User.class);
        boolean flag = userService.isExistUser(user.getPhoneNumber());
        if(flag){//如果注册对象存在
            return new ErrorJsonRes(CodeDefined.ACCOUNT_USER_EXIST_ERROR,CodeDefined.getMessage(CodeDefined.ACCOUNT_USER_EXIST_ERROR));
            //注册失败"respStatus":{"result":1002,"msg":"对不起，该手机号码已经被注册！"}
        }
        userService.saveUser(user);
        JsonPioSimple jsonPioSimple = LbsHelper.savePio("0.0", "0.0");
        userService.saveUserInfoTokenAndLbsId(user.getUserToken(), "scud", jsonPioSimple.getId());

        request.getSession().setAttribute(CommonParamDefined.USER_LBS_ID, jsonPioSimple.getId()); // 先默认保存一个lbs位置，session保存 lbsid
        request.getSession().setAttribute(CommonParamDefined.USER_TOKEN, user.getUserToken());
        response.setHeader("sessionid:", request.getSession().getId());   // 显示设置sessionId
        return new SuccessJsonRes();
    }

    /**
     * 用户登录
     * @return
     * @throws Exception
     */
    @RequestMapping("/userLogin")
    @ResponseBody
    public OperatorResponse loginUser(HttpServletRequest request,HttpServletResponse response)throws Exception{
        User user =  StreamSerializer.streamSerializer(request.getInputStream(), User.class); // 这个是为andorid端json数据解析准备
             user= userService.loadUserByUser(user); //user里面只有那么和passowrd
        if(user == null){
            return new ErrorJsonRes(CodeDefined.ACCOUNT_USER_LOGIN,CodeDefined.getMessage(CodeDefined.ACCOUNT_USER_LOGIN));
            //登录失败{"respStatus":{"result":1001,"msg":"用户登录失败，请检查用户名或密码！"}}
        }
       UserInfo userInfo = userService.getUserInfoByToken(user.getUserToken());
        request.getSession().setAttribute(CommonParamDefined.USER_TOKEN, user.getUserToken());
        request.getSession().setAttribute(CommonParamDefined.USER_LBS_ID,userInfo.getLbsId());
        response.setHeader("sessionid",request.getSession().getId());  // 显示设置 sessionid
        return new SuccessJsonRes();
    }



    /**
     * 获取经纬度信息，修改用户经纬度
     * @return
     */
    @RequestMapping("/updateLatitude")
    @ResponseBody
    public OperatorResponse updateLatitude(String lat,String lng,HttpSession session){
        System.out.println("调用跟新经纬度方法"+"lat:"+lat+"log:"+lng);
        Integer lbsid = (Integer)session.getAttribute(CommonParamDefined.USER_LBS_ID);
        LbsHelper.updatePio(lng,lat,lbsid);
        return  new SuccessJsonRes();
    }


    /**
     *根据userToekn, 获取UserIofo信息
     * @return
     */
    @RequestMapping("/getUserInfoByToken")
    @ResponseBody
    public  OperatorResponse getUserInfoByToken(HttpSession session){
        String userToken = (String)session.getAttribute(CommonParamDefined.USER_TOKEN);
        UserInfo userInfo = userService.getUserInfoByToken(userToken);
        ObjSucRes objSucRes = new ObjSucRes();
        objSucRes.setData(userInfo);
        return  objSucRes;
    }


    /**
     * 用户信息修改
     * @param request
     * @return
     */
    @RequestMapping("/updateUserInfo")
    @ResponseBody
    public OperatorResponse updateUserInfo(HttpServletRequest request) throws Exception{
        UserInfo userInfo =  StreamSerializer.streamSerializer(request.getInputStream(), UserInfo.class);
        userService.updateUserInfo(userInfo);
        ObjSucRes objSucRes = new ObjSucRes();
        objSucRes.setData(userInfo);
        return objSucRes;
    }

    /**
     * 查询附近的对象，添加条件查询
     * @param session
     * @return
     */
    @RequestMapping("/getNearByPoi")
    @ResponseBody
    public OperatorResponse getNearbyPoi(HttpSession session,String lat,String lng,int page_index,String skillName) throws UnsupportedEncodingException { //page_index，当前页数，起始页数为1
        System.out.println("lat:"+lat+"lng:"+lng+"page_index:"+page_index);
        int userLbsId = (Integer)session.getAttribute(CommonParamDefined.USER_LBS_ID);
        int radius = 100000; //默认查询50公里距离内的
        int page_size = 2;// 设置每一页返回的条数，这儿默认两条
        List<UserInfo> userInfoList = userService.LbsNearBy(session,lng,lat,radius,page_index,page_size,userLbsId,skillName);
        System.out.println("查询附近的对象，添加条件查询+userInfoList.size:"+userInfoList.size());
        ListSucRes listSucRes = new ListSucRes();
        listSucRes.setData(userInfoList);
        return  listSucRes;
    }

    /**
     * 头像上传
     * @return
     */
    @RequestMapping("/updateUserImage")
    @ResponseBody
    public OperatorResponse updateUserImage(HttpSession session,HttpServletRequest request){
        String userToken =(String)session.getAttribute(CommonParamDefined.USER_TOKEN);
        MultipartHttpServletRequest multipartRequest  =  (MultipartHttpServletRequest) request;
        MultipartFile img  =  multipartRequest.getFile("userImage");
        if(img.getSize()<=0){
           return new ErrorJsonRes(CodeDefined.EXCEPTION_CODE_PICTURE_ERROR,CodeDefined.getMessage(CodeDefined.EXCEPTION_CODE_PICTURE_ERROR));
        }
        String path = null;
        try {
            System.out.println("stram:"+img.getInputStream());
            System.out.println( "key:"+WebUtil.getBosOjectKey());
            System.out.println( img.getSize());
            System.out.println( img.getContentType());
            // 这个path 是图片上传到百度bos的返回路径，如：/upload/150701105336， 加上图片访问前缀"http://scud-images.bj.bcebos.com";就可以进行访问了
            path = BosHelper.putUserImage(img.getInputStream(), WebUtil.getBosOjectKey(), img.getSize(), img.getContentType());
            String picture =userService.getUserInfoByToken((String)session.getAttribute(CommonParamDefined.USER_TOKEN)).getUserInfoPicture();
            if(null != picture || "".equals(picture)){
                BosHelper.deleteUserObject(picture); // 如果用户上传了新的头像，则删除原来头像
            }
        } catch (Exception e) {
            return new ErrorJsonRes(CodeDefined.EXCEPTION_CODE_PICTURE_ERROR,CodeDefined.getMessage(CodeDefined.EXCEPTION_CODE_PICTURE_ERROR));// 图片上传错误
        }
        userService.updateUserImage(userToken,path);
        return new SuccessJsonRes();
    }



}
