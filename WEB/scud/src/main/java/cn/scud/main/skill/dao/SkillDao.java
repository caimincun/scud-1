package cn.scud.main.skill.dao;

import cn.scud.main.skill.model.Skill;

import java.util.List;

/**
 * Created by Administrator on 2015/8/13.
 */
public interface SkillDao {

    void saveSkill(Skill skill);

    List<Skill> listUserSkills(String userToken);
}
