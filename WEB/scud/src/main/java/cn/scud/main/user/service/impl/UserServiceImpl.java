package cn.scud.main.user.service.impl;

import cn.scud.commoms.CommonParamDefined;
import cn.scud.commoms.jsonModel.JsonPioContent;
import cn.scud.commoms.jsonModel.JsonPioSearch;
import cn.scud.main.user.dao.UserDao;
import cn.scud.main.user.model.User;
import cn.scud.main.user.model.UserInfo;
import cn.scud.main.user.service.UserService;
import cn.scud.utils.LbsHelper;
import cn.scud.utils.WebUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.sound.midi.Soundbank;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by cmc on 14-12-9.
 */
@Service("userService")
public class UserServiceImpl implements UserService {


    @Resource
    private UserDao userDao;


    @Override
    @Transactional
    public void saveUser(User user) {
        user.setLastLoginDate(WebUtil.getCurrentTime());
        user.setUserToken(WebUtil.getUserToken());
        user.setRegDate(WebUtil.getCurrentTime());
        user.setRegChannel(CommonParamDefined.ANDROID);
        userDao.saveUser(user);
    }

    @Override
    public void saveUserInfoTokenAndLbsId(String userToken,String scud,int lbsId) {
        HashMap<String , Object> map = new HashMap<String , Object>();
        map.put("userToken",userToken);
        map.put("scud",scud);
        map.put("lbsId",lbsId);
        userDao.saveUserInfoTokenAndLbsId(map);
    }

//    @Override
//    public void saveUserInfoLbs(String userToken, int lbsid) {
//        userDao.saveUserInfoLbs(userToken,lbsid);
//    }

    @Override
    public User loadUserByToken(String token) {

        return null;
    }

    /**
     * 还需要修改最后一次登录时间
     * @param user
     * @return
     */
//    @Override
//    public User loadUserByToken(String token) {
//        return userDao.loadUserByToken(token);
//    }



    @Override
    public boolean isExistUser(String phoneNumber) {
        int userNum = userDao.countUserByPhoneNum(phoneNumber);
        if(userNum == 0){
            return false;
        }
        return true;
    }

    @Override
    public User loadUserByUser(User user) {
        return userDao.loadUserByUser(user);
    }

//    @Override
//    public void setUserInfo(UserInfo userInfo) {
//        userDao.setUserInfo(userInfo);
//    }

    @Override
    public UserInfo getUserInfoByToken(String userToken) {
        return userDao.getUserInfoByToken(userToken);
    }

    @Override
    public void updateUserInfo(UserInfo userInfo) {
        userDao.updateUserInfo(userInfo);
    }

    @Override
    public void updateLatitude(double latitude, double longitude, String userToken) {
        Map map = new HashMap();
        map.put("latitude",latitude);
        map.put("longitude",longitude);
        map.put("userToken",userToken);
        userDao.updateLatitude(map);
    }


    @Override
    public void updateUserImage(String userToken, String path) {
        userDao.updateUserImage(userToken,path);
    }

    @Override
    public List<UserInfo> searchNearbyPoi(List userPoiIds) {
        return userDao.searchNearbyPoi(userPoiIds);
    }

    @Override
    @Transactional
    public List<UserInfo> LbsNearBy(String lng, String lat, int radius, int page_index, int page_size,int userLbsId) {
        //1.跟新当前用户lbs 经纬度
        LbsHelper.updatePio(lng,lat,userLbsId);
        //2. 搜索附近范围内 的对象
        JsonPioSearch jsonPioSearch = LbsHelper.pioSearch(lng, lat, radius, page_index, page_size);
        List<JsonPioContent> jsonPioContents = jsonPioSearch.getContents();
        List userLbsIds = new ArrayList();
        for(JsonPioContent jsonPioContent:jsonPioContents){
            userLbsIds.add(jsonPioContent.getUid());
        }
        List<UserInfo> userInfos = userDao.searchNearbyPoi(userLbsIds); // 取得附近人的信息，但是还需要把 jsonPioSearch 记录里面的 距离添加进去,此时是无序的
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
        return userInfoList;
    }


}

