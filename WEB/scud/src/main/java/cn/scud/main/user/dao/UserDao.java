package cn.scud.main.user.dao;


import cn.scud.main.user.model.User;
import cn.scud.main.user.model.UserInfo;

import java.util.List;

/**
 * Created by cmc on 14-12-9.
 */
public interface UserDao {
    /**
     * 用户保存
     * @param user
     */
    void saveUser(User user);

    /**
     * 根据tonken获取对象
     * @return
     */
    User loadUserByToken(String token);


    /**
     * 通过phoneNum 和 pwd 获取 user
     * @param user
     * @return
     */
    User loadUserByUser(User user);

    /**
     * 获取User数量
     * @param phoneNumber
     * @return
     */
    int countUserByPhoneNum(String phoneNumber);

    /**
     * 完善用户信息
     * @param userInfo
     */
    void setUserInfo(UserInfo userInfo);

    UserInfo getUserInfoByToken(String userToken);
}
