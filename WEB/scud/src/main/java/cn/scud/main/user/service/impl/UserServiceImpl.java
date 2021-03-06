package cn.scud.main.user.service.impl;

import cn.scud.commoms.CommonParamDefined;
import cn.scud.commoms.jsonModel.JsonPioContent;
import cn.scud.commoms.jsonModel.JsonPioSearch;
import cn.scud.main.skill.dao.SkillDao;
import cn.scud.main.skill.model.Skill;
import cn.scud.main.user.dao.UserDao;
import cn.scud.main.user.model.User;
import cn.scud.main.user.model.UserInfo;
import cn.scud.main.user.service.UserService;
import cn.scud.utils.LbsHelper;
import cn.scud.utils.WebUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
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

    @Resource
    private SkillDao skillDao;


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
        return userDao.loadUserByToken(token);
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

    /**
     *  查询附近所有的人
     * @param lng
     * @param lat
     * @param radius
     * @param page_index
     * @param page_size
     * @param userLbsId
     * @return
     */
    @Override
    @Transactional
    public List<UserInfo> LbsNearBy(HttpSession session,String lng, String lat, int radius, int page_index, int page_size,int userLbsId,String skillName) {
        //1.跟新当前用户lbs 经纬度，跟新经纬度对自己没什么影响，但是可以方便别人查看
        LbsHelper.updatePio(lng,lat,userLbsId);
        //2. 搜索附近范围内 的对象
        Boolean ifLoop = true;
        List<UserInfo> userInfoList = new ArrayList<UserInfo>();
        if(page_index == 0){
            session.setAttribute("user_differ_num",-1);
        }
        int loopTime = 0;                                                                            // 为了避免数据库数据不够为空的死循环，对循环次数进行限定
        int numTemp = 0;
        while(ifLoop) {
            loopTime++;
            int searchNum =  Integer.parseInt(session.getAttribute("user_differ_num").toString());
            numTemp = searchNum+1;
            session.setAttribute("user_differ_num",searchNum+1);
            JsonPioSearch jsonPioSearch = LbsHelper.pioSearch(lng, lat, radius,numTemp , page_size);
            List<JsonPioContent> jsonPioContents = jsonPioSearch.getContents();
            List userLbsIds = new ArrayList();
            for (JsonPioContent jsonPioContent : jsonPioContents) {
                userLbsIds.add(jsonPioContent.getUid());
            }
            List<UserInfo> userInfos = null;
            if(userLbsIds.size()>0){
                userInfos = userDao.searchNearbyPoi(userLbsIds); // 取得附近人的信息，但是还需要把 jsonPioSearch 记录里面的 距离添加进去,此时是无序的
            }
            else{
                    if(loopTime <= 3){
                        ifLoop = true;
                        continue;
                    }else {
                        break;
                    }
            }
            for (JsonPioContent jsonPioContent : jsonPioContents) {
                for (UserInfo userInfo : userInfos) {
                    if (jsonPioContent.getUid() == userInfo.getLbsId()) {
                        // 这一步确定附近人的人的基本信息，然后再判断是否包含某个技能
                            List<Skill> skills = skillDao.listUserSkills(userInfo.getUserToken());
                        for(Skill skill:skills){
                            System.out.println("附件人所有技能："+skill.getSkillTitle());
                        }

                            for (Skill skill : skills) {
//                                System.out.println(skillName+":skill.getSkillSort():"+skill.getSkillSort());
                                if (("全部").equals(skillName) || skill.getSkillSort().equals(skillName)) {
                                    userInfo.setSkillTitle(skill.getSkillTitle());
                                    userInfo.setSkillMoney(skill.getSkillMoney());
                                    userInfo.setSkillUnit(skill.getSkillUnit());
                                    userInfo.setDistance(jsonPioContent.getDistance());
                                    userInfo.setLocation(jsonPioContent.getProvince()+jsonPioContent.getCity()+jsonPioContent.getDistrict());
                                    userInfoList.add(userInfo); //将有序由近到远的添加进去
                                    System.out.println(userInfoList.toString());
                                    break;
                                }
                            }
                            break;
                    }
                }
            }
            if(userInfoList.size() == 0){                   // 判断这次分页查询是否有值
                ifLoop = true;
            }else{
                ifLoop = false;
            }
            if(loopTime>3){
                ifLoop = false;             // 如果超过如 5 次 分页查询都没有数据，则判定数据库为空数据跳出循环
            }
        }
        return userInfoList;
    }

    @Override
    public void updatePwd(String userToken, String password) {
        Map map = new HashMap();
        map.put("userToken",userToken);
        map.put("password",password);
        userDao.updatePassword(map);
    }


    /**
     * 条件查询的方法
     * @param session
     * @param lng
     * @param lat
     * @param radius
     * @param page_index
     * @param page_size
     * @param userLbsId
     * @param fanwei
     * @return
     */
    public List<UserInfo> LbsNearBy(HttpSession session,String lng, String lat, int radius, int page_index, int page_size,int userLbsId,int fanwei) {
        //1.跟新当前用户lbs 经纬度
        LbsHelper.updatePio(lng,lat,userLbsId);
        //2. 搜索附近范围内 的对象
        Boolean ifLoop = true;
        List<UserInfo> userInfoList = new ArrayList<UserInfo>();
        if(page_index == 0){
            session.setAttribute("user_differ_num",0);
        }
        int loopTime = 0;                                                                               // 为了避免数据库数据不够为空的死循环，对循环次数进行限定
        while(ifLoop) {
            loopTime++;
            int searchNum =  Integer.parseInt(session.getAttribute("user_differ_num").toString());
            JsonPioSearch jsonPioSearch = LbsHelper.pioSearch(lng, lat, radius, searchNum+1, page_size);
            List<JsonPioContent> jsonPioContents = jsonPioSearch.getContents();
            List userLbsIds = new ArrayList();
            for (JsonPioContent jsonPioContent : jsonPioContents) {
                userLbsIds.add(jsonPioContent.getUid());
            }
            List<UserInfo> userInfos = userDao.searchNearbyPoi(userLbsIds); // 取得附近人的信息，但是还需要把 jsonPioSearch 记录里面的 距离添加进去,此时是无序的
            for (JsonPioContent jsonPioContent : jsonPioContents) {
                for (UserInfo userInfo : userInfos) {
                    if (jsonPioContent.getUid() == userInfo.getLbsId()) {
                        userInfo.setDistance(jsonPioContent.getDistance());
                        userInfoList.add(userInfo); //将有序由近到远的添加进去
                        break;
                    }
                }
            }
            if(userInfoList.size() == 0){                   // 判断这次分页查询是否有值
                ifLoop = true;
                session.setAttribute("user_differ_num",searchNum+1);
            }else{
                ifLoop = false;
            }
            if(loopTime>= 3){
                ifLoop = false;             // 如果超过如 5 次 分页查询都没有数据，则判定数据库为空数据跳出循环
            }
        }
        return userInfoList;
    }

}

