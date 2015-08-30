package cn.scud.main.skill.dao;

import cn.scud.main.skill.model.Skill;

import java.util.List;

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
}
