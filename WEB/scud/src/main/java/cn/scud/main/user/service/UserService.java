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
    UserInfo loadUserInfoByToken(String token);

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

}

