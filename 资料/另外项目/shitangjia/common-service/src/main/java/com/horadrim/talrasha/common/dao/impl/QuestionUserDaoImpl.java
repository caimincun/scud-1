package com.horadrim.talrasha.common.dao.impl;

import com.horadrim.talrasha.common.dao.QuestionUserDao;
import com.horadrim.talrasha.common.model.QuestionUser;
import com.horadrim.talrasha.common.dao.GenericHibernateDao;
import org.springframework.stereotype.Repository;

/**
 * Created by Administrator on 2015/6/11.
 */
@Repository("questionUserDao")
public class QuestionUserDaoImpl extends GenericHibernateDao<QuestionUser,Integer> implements QuestionUserDao {

//    @Override
//    public List<QuestionUser> findAll() {
//        final String hql = "FROM QuestionUser";
//        Query query = getSession().createQuery(hql);
//        query.setCacheable(true);
//        return query.list();
//    }
}
