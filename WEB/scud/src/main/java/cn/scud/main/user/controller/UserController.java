package cn.scud.main.user.controller;

import cn.scud.commoms.CodeDefined;
import cn.scud.commoms.CommonParamDefined;
import cn.scud.commoms.jsonModel.JsonPioContent;
import cn.scud.commoms.jsonModel.JsonPioSearch;
import cn.scud.commoms.jsonModel.JsonPioSimple;
import cn.scud.commoms.response.*;
import cn.scud.main.user.model.User;
import cn.scud.main.user.model.UserInfo;
import cn.scud.main.user.service.UserService;
import cn.scud.utils.BosHelper;
import cn.scud.utils.LbsHelper;
import cn.scud.utils.StreamSerializer;
import cn.scud.utils.WebUtil;
import org.apache.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
     * 用户注册,只返回token
     * @return
     */
    @RequestMapping(value="/add")
    @ResponseBody
    public OperatorResponse saveUser(HttpServletRequest request,HttpServletResponse response) throws Exception {
        User user =  StreamSerializer.streamSerializer(request.getInputStream(), User.class);
        System.out.println("用户注册user:"+user);
        boolean flag = userService.isExistUser(user.getPhoneNumber());
        if(flag){//如果注册对象存在
            return new ErrorJsonRes(CodeDefined.ACCOUNT_USER_EXIST_ERROR,CodeDefined.getMessage(CodeDefined.ACCOUNT_USER_EXIST_ERROR));
            //注册失败"respStatus":{"result":1002,"msg":"对不起，该手机号码已经被注册！"}
        }
        String ip = WebUtil.getRemoteHost(request);// 获取注册ip
        user.setLastLoginIp(ip);
        userService.saveUser(user);
        userService.saveUserInfoToken(user.getUserToken(), "scud");
        JsonPioSimple jsonPioSimple = LbsHelper.savePio("0","0");
        request.getSession().setAttribute(CommonParamDefined.USER_LBS_ID,jsonPioSimple.getId()); // 先默认保存一个lbs位置，session保存 lbsid
        request.getSession().setAttribute(CommonParamDefined.TOKEN, user.getUserToken());
        response.setHeader("sessionid:",request.getSession().getId());   // 显示设置sessionId
        return new SuccessJsonRes();
    }

    /**
     * 用户登录,将token保存进入session,将用户完整信息返回
     * @param request
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
        request.getSession().setAttribute(CommonParamDefined.TOKEN,user.getUserToken());
        response.setHeader("sessionid",request.getSession().getId());  // 显示设置 sessionid
        return new SuccessJsonRes();
    }

    /**
     * 用户信息完善
     * @param request
     * @return
     * @throws Exception
     */
//    @RequestMapping("/setUserInfo")
//    @ResponseBody
//    public OperatorResponse setUserInfo(HttpServletRequest request) throws Exception {
//        UserInfo userInfo =  StreamSerializer.streamSerializer(request.getInputStream(), UserInfo.class);
//        System.out.println("userInfo:"+userInfo);
//        userService.setUserInfo(userInfo);
//        return new SuccessJsonRes();
//    }


    /**
     * 用户信息修改
     * @param request
     * @return
     */
    @RequestMapping("/updateUserInfo")
    @ResponseBody
    public OperatorResponse updateUserInfo(HttpServletRequest request) throws Exception{
        UserInfo userInfo =  StreamSerializer.streamSerializer(request.getInputStream(), UserInfo.class);
        String userTOken = (String)request.getSession().getAttribute(CommonParamDefined.TOKEN);
        System.out.println("updateUserInfo :"+userTOken);
        userService.updateUserInfo(userInfo);
        SuccessJsonRes successJsonRes = new SuccessJsonRes();
        return  successJsonRes;
    }

    /**
     * 获取经纬度信息，修改用户经纬度
     * @return
     */
    @RequestMapping("/updateLatitude")
    @ResponseBody
    public OperatorResponse updateLatitude(String lat,String lng,HttpSession session){
        System.out.println("lat:"+lat+"log:"+lng);
        Integer lbsid = (Integer)session.getAttribute(CommonParamDefined.USER_LBS_ID);
        LbsHelper.updatePio(lng,lat,lbsid);
        SuccessJsonRes successJsonRes = new SuccessJsonRes();
        return  successJsonRes;
    }


    /**
     *根据userToekn, 获取UserIofo,登录之后调用此方法,将用户经纬度lbsid 保存到session
     * @return
     */
    @RequestMapping("/getUserInfoByToken")
    @ResponseBody
    public  OperatorResponse getUserInfoByToken(HttpSession session){
        String userToken = (String)session.getAttribute(CommonParamDefined.TOKEN);
        UserInfo userInfo = userService.getUserInfoByToken(userToken);
        userInfo.setUserToken(userToken);
        ObjSucRes objSucRes = new ObjSucRes();
        objSucRes.setData(userInfo);
        return  objSucRes;
    }


    /**
     * 查询附近的对象
     * @param session
     * @return
     */
    public OperatorResponse getNearbyPoi(HttpSession session,String lat,String lng,int page_index){ //page_index，当前页数，起始页数为1
        int userLbsId = (Integer)session.getAttribute(CommonParamDefined.USER_LBS_ID);
        //跟新当前用户lbs 经纬度
        LbsHelper.updatePio(lng,lat,userLbsId);
        //根据当亲经纬度查询附近范围类的对象
        int radius = 100000; //默认查询50公里距离内的
        int page_size = 2;// 设置每一页返回的条数，这儿默认两条
        JsonPioSearch jsonPioSearch = LbsHelper.pioSearch(lng,lat,radius,page_index,page_size);
        List<JsonPioContent> jsonPioContents = jsonPioSearch.getContents();
        List userLbsIds = new ArrayList();
        for(JsonPioContent jsonPioContent:jsonPioContents){
            userLbsIds.add(jsonPioContent.getUid());
        }
        List<UserInfo> userInfos = userService.searchNearbyPoi(userLbsIds); // 取得附近人的信息，但是还需要把 jsonPioSearch 记录里面的 距离添加进去,此时是无序的
        List<UserInfo> userInfoList = new ArrayList<UserInfo>();
        for(JsonPioContent jsonPioContent:jsonPioContents){
            for(UserInfo userInfo:userInfos){
                if(jsonPioContent.getUid() == userInfo.getLbsId()){
                    userInfo.setDistance(jsonPioContent.getDistance());
                    userInfoList.add(userInfo); //将有序由近到远的添加进去
                    break;
                }
            }
        }
        ListSucRes listSucRes = new ListSucRes();
        listSucRes.setData(userInfoList);
        return  listSucRes;
    }

    /**
     * 图片上传测试
     * @param img
     * @return
     */
    @RequestMapping("/testup")
    @ResponseBody
    public OperatorResponse updateUserImage(HttpServletRequest request){
        String userToken = (String)request.getSession().getAttribute(CommonParamDefined.TOKEN);
        MultipartHttpServletRequest multipartRequest  =  (MultipartHttpServletRequest) request;
        MultipartFile img  =  multipartRequest.getFile("userImage");
        if(img.getSize()<=0){
           return new ErrorJsonRes(CodeDefined.EXCEPTION_CODE_PICTURE_ERROR,CodeDefined.getMessage(CodeDefined.EXCEPTION_CODE_PICTURE_ERROR));
        }
        String path = null;
        try {
            BosHelper bosHelper = new BosHelper();
            SimpleDateFormat sdf = new SimpleDateFormat("yyMMddHHmmss");
            String newName = sdf.format(new Date());
            // 这个path 是图片上传到百度bos的返回路径，如：/upload/150701105336， 加上图片访问前缀"http://scud-images.bj.bcebos.com";就可以进行访问了
            path = bosHelper.putFile(img.getInputStream(), newName, img.getSize(), img.getContentType());
        } catch (Exception e) {
            e.printStackTrace();
        }
        userService.updateUserImage(userToken,path);
        return new SuccessJsonRes();
    }



}
