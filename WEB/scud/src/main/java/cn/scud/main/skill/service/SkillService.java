package cn.scud.main.skill.service;

import cn.scud.main.skill.model.Skill;

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
}
