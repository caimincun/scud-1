package cn.scud.main.skill.dao;

import cn.scud.main.skill.model.Skill;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2015/8/13.
 */
public interface SkillDao {

    /**
     * 技能发布
     * @param skill
     */
    void saveSkill(Skill skill);

    /**
     * 根据 userToken  获取用户 所有技能
     * @param userToken
     * @return
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
     * 更具 userToken 和 skill_sort 查询skill
     * @return
     */
    List<Skill> getSkillByUskenAndSksort(Map map);
}
