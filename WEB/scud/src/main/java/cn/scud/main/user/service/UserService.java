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
     * 用户登录
     * @param user
     * @return
     */
    User loginUser(User user);

    /**
     * 根据tokenId获取用户完整信息
     * @param tokenId
     * @return
     */
    UserInfo getUserInfoByToken(String token);

//    int queryUserByPhoneNmuber();

    List<User> findAll();

}

