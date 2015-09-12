package cn.scud.main.skill.service;

import cn.scud.main.skill.model.Skill;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by Administrator on 2015/8/13.
 */
public interface SkillService {

    /**
     * 保存 Skill
     */
    void saveSkill(Skill skill);
    /**
     * 获取用户技能
     */
    List<Skill> listUserSkills(String userToken);

    /**
     * 修改技能
     * @param skill
     */
    void updateSkill(Skill skill);

    /**
     * 根据skillToken 获取技能相关信息
     */
    Skill loadSkillBysktoken(String skillToken);

    /**
     * 查询附近技能对象
     * @param lng
     * @param lat
     * @param radius
     * @param page_index
     * @param page_size
     * @return
     */
    List<Skill> LbsNearSkill(HttpSession session,String lng,String lat,int radius,int page_index,int page_size,int userLbsId,String skillName);
}
