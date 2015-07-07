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
        user.setUserToken(WebUtil.getGeratorID());
        user.setRegDate(WebUtil.getCurrentTime());
        user.setRegChannel(CommonParamDefined.ANDROID);
        userDao.saveUser(user);
    }

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

    @Override
    public void setUserInfo(UserInfo userInfo) {
        userDao.setUserInfo(userInfo);
    }

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


}

