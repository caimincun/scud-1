package cn.scud.main.user.service.impl;

import cn.scud.commoms.CommonParamDefined;
import cn.scud.main.user.dao.UserDao;
import cn.scud.main.user.model.User;
import cn.scud.main.user.model.UserInfo;
import cn.scud.main.user.service.UserService;
import cn.scud.utils.WebUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.sound.midi.Soundbank;
import java.util.List;

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
        user.setUserToken(WebUtil.getGeratorID());
        user.setRegDate(WebUtil.getCurrentTime());
        user.setRegChannel(CommonParamDefined.ANDROID);
        userDao.saveUser(user);
    }

    @Override
    public User loginUser(User user) {
        return userDao.loginUser(user);
    }

    @Override
    public UserInfo getUserInfoByToken(String token) {
        return null;
    }

    // 级联查询
    @Override
    public List<User> findAll() {

        return userDao.selectAll();
    }
}

