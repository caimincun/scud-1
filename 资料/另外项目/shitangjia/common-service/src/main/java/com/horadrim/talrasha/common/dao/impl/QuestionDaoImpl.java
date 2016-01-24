package com.horadrim.talrasha.common.dao.impl;

import com.horadrim.talrasha.common.dao.QuestionDao;
import com.horadrim.talrasha.common.model.Question;
import com.horadrim.talrasha.common.dao.GenericHibernateDao;
import org.springframework.stereotype.Repository;

/**
 * Created by Administrator on 2015/6/11.
 */
@Repository("questionDao")
public class QuestionDaoImpl extends GenericHibernateDao<Question,Integer> implements QuestionDao {

//    @Override
//    public List<Question> findAll() {
//        System.out.println("user findall");
//        final String hql = "FROM Question";
//        Query query = getSession().createQuery(hql);
//        query.setCacheable(true);
//        return query.list();
//    }
}
