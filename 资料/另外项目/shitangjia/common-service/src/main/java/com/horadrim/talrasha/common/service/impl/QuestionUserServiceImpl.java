package com.horadrim.talrasha.common.service.impl;


import com.horadrim.talrasha.common.dao.QuestionUserDao;
import com.horadrim.talrasha.common.model.QuestionUser;
import com.horadrim.talrasha.common.service.QuestionUserService;
import com.horadrim.talrasha.common.dao.GenericDao;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by Administrator on 2015/6/11.
 */
@Service("questionUserService")
public class QuestionUserServiceImpl extends GenericServiceImpl<QuestionUser,Integer> implements QuestionUserService{
    @Resource
    private QuestionUserDao questionUserDao;

    @Override
    protected GenericDao<QuestionUser, Integer> getGenericDao() {
        return questionUserDao;
    }

    @Override
    public boolean existIp(String ip) {
        String hql= "SElECT COUNT(id) from QuestionUser where ipaddr = ?";
        Object[] objects = {ip};
        return questionUserDao.count(hql,objects) > 0;

    }

    /**
     * 查询电话号码是否存在
     * @return 存在的话返回true 不存在返回false
     */
    @Override
    public boolean existPhone(String phone) {
        final String hql = "SElECT COUNT(id) from QuestionUser where phoneNumber = ?";
        return questionUserDao.count(hql,new Object[]{phone})>0;
    }
}
