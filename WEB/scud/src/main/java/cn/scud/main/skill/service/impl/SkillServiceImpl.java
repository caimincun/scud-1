package cn.scud.main.skill.service.impl;

import cn.scud.commoms.jsonModel.JsonPioContent;
import cn.scud.commoms.jsonModel.JsonPioSearch;
import cn.scud.main.skill.dao.SkillDao;
import cn.scud.main.skill.model.Skill;
import cn.scud.main.skill.service.SkillService;
import cn.scud.main.user.dao.UserDao;
import cn.scud.main.user.model.User;
import cn.scud.main.user.model.UserInfo;
import cn.scud.utils.LbsHelper;
import cn.scud.utils.WebUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2015/8/13.
 */
@Service("/skillService")
public class SkillServiceImpl implements SkillService {

    @Resource
    private SkillDao skillDao;

    @Resource
    private UserDao userDao;

    @Override
    public void saveSkill(Skill skill) {
        skill.setSkillToken(WebUtil.getSkillToken());
        skillDao.saveSkill(skill);
    }

    @Override
    public List<Skill> listUserSkills(String userToken) {
        return skillDao.listUserSkills(userToken);
    }

    @Override
    public void updateSkill(Skill skill) {

    }

    @Override
    public Skill loadSkillBysktoken(String skillToken) {
        return skillDao.loadSkillBysktoken(skillToken);
    }

    /**
     * 根据技能查询附近的对象
     * @param session
     * @param lng
     * @param lat
     * @param radius
     * @param page_index
     * @param page_size
     * @param userLbsId
     * @param skillName
     * @return
     */
    @Override
    public List<Skill> LbsNearSkill(HttpSession session, String lng, String lat, int radius, int page_index, int page_size, int userLbsId, String skillName) {
        //1.跟新当前用户lbs 经纬度
        LbsHelper.updatePio(lng, lat, userLbsId);
        //2. 搜索附近范围内 的对象
        Boolean ifLoop = true;
        List<Skill> skillList = new ArrayList<Skill>();
        if(page_index == 0){
            session.setAttribute("skill_differ_num",0);
        }
        int loopTime = 0;                                                                               // 为了避免数据库数据不够为空的死循环，对循环次数进行限定
        while(ifLoop) {
            loopTime++;
            int searchNum =  Integer.parseInt(session.getAttribute("skill_differ_num").toString());
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
                        // 此时查询到附近的人，然后便利查询附近的人的技能是否包含 skillName
                        Map map = new HashMap();
                        map.put("userToken",userInfo.getUserToken());
                        map.put("skillSort",skillName);
                        User user = userDao.loadUserByToken(userInfo.getUserToken());
                        String phoneNumber = "";
                        if(null!= user){
                            phoneNumber = user.getPhoneNumber();
                        }

                        List<Skill> skills =skillDao.getSkillByUskenAndSksort(map); // 查询到某一个人符合条件的技能对象
                        for(Skill skill:skills){
                            skill.setUserPicture(userInfo.getUserInfoPicture());
                            skill.setDistance(jsonPioContent.getDistance());
                            skill.setUserName(userInfo.getUserRealName());
                            skill.setAge(userInfo.getAge());
                            skill.setUserInfoSex(userInfo.getUserInfoSex());
                            skill.setPhoneNumber(phoneNumber);
                            skillList.add(skill);
                            break;
                        }
                        break;
                    }
                }
            }
            if(skillList.size() == 0){                   // 判断这次分页查询是否有值
                ifLoop = true;
                session.setAttribute("skill_differ_num",searchNum+1);
            }else{
                ifLoop = false;
            }
            if(loopTime>5){
                ifLoop = false;             // 如果超过如 5 次 分页查询都没有数据，则判定数据库为空数据跳出循环
            }
        }
        return skillList;
    }
}
