package cn.scud.main.user.service;


import cn.scud.main.user.model.User;
import cn.scud.main.user.model.UserInfo;

import java.util.List;

/**
 * Created by cmc on 14-12-9.
 */

public interface UserService {

    /**
     * 用户注册
     * @param user
     */
    void saveUser(User user);

    void saveUserInfoToken(String userToken);
    /**
     * 通过token获取User
     * @param
     * @return
     */
    User loadUserByToken(String token);
    /**
     * 根据token获取UserInfo
     * @param token
     * @return
     */
//    UserInfo loadUserInfoByToken(String token);
    /**
     * 通过phoneNum 判断用户是否存在
     * @param phoneNumber
     * @return
     */
    boolean isExistUser(String phoneNumber);
    /**
     * 通过 phoneNum 和 pwd 获取User
     * @param user
     * @return
     */
    User loadUserByUser(User user);
    /**
     * 完善用户信息
     * @param userInfo
     */
    void setUserInfo(UserInfo userInfo);
    /**
     * 获取用户信息
     * @param userToken
     * @return
     */
    UserInfo getUserInfoByToken(String userToken);
    /**
     * 修改 UserInfo
     * @param userInfo
     */
    void updateUserInfo(UserInfo userInfo);

    /**
     * 修改经纬度
     * @param atitude
     * @param longitude
     * @param userToken
     */
    void  updateLatitude(double atitude,double longitude,String userToken);

    /**
     * 根据 userToken ，修改 user头像
     * @param userToken
     * @param path
     */
    void updateUserImage(String userToken,String path);

    /**
     * 根据附近对象的 lbsid 查询对象
     * @param ids
     */
    List<UserInfo> searchNearbyPoi(List userPoiIds);

}

