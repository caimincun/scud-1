package cn.scud.main.user.service;


import cn.scud.main.user.model.User;

import java.util.List;

/**
 * Created by cmc on 14-12-9.
 */

public interface UserService {

    /**
     * 用户注册
     * @param user
     */
    void addUser(User user);

    /**
     * 用户登录
     * @param user
     * @return
     */
    User loginUser(User user);

//    int queryUserByPhoneNmuber();

    List<User> findAll();

}
