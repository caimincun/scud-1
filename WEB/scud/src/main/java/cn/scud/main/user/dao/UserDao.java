package cn.scud.main.user.dao;


import cn.scud.main.user.model.User;
import cn.scud.main.user.model.UserInfo;
import org.apache.ibatis.annotations.Param;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
//    User loadUserByToken(String token);

    void saveUserInfoToken(String userToken);
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

    /**
     * 通过userToken 获取 userInfo
     * @param userToken
     * @return
     */
    UserInfo getUserInfoByToken(String userToken);

    /**
     * 修改用户信息
     * @param userInfo
     */
    void updateUserInfo(UserInfo userInfo);

    /**
     * 修改经纬度
     */
    void  updateLatitude(Map map);

    /**
     *  根据 userToken, 修改头像
     * @param userToken
     * @param path
     */
    void updateUserImage(@Param(value="userToken")String userToken,@Param(value="path") String path);
}
