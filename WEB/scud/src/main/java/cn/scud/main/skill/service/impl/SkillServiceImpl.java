package cn.scud.main.skill.service.impl;

import cn.scud.main.skill.dao.SkillDao;
import cn.scud.main.skill.model.Skill;
import cn.scud.main.skill.service.SkillService;
import cn.scud.utils.WebUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Administrator on 2015/8/13.
 */
@Service("/skillService")
public class SkillServiceImpl implements SkillService {

    @Resource
    private SkillDao skillDao;
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
}
