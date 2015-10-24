package com.horadrim.talrasha.common.dao.impl;

import com.horadrim.talrasha.common.dao.GenericHibernateDao;
import com.horadrim.talrasha.common.dao.OtherDao;
import com.horadrim.talrasha.common.model.Other;
import org.springframework.stereotype.Repository;

/**
 * Created by Administrator on 2015/6/11.
 */
@Repository("otherDao")
public class OtherDaoImpl extends GenericHibernateDao<Other,Integer> implements OtherDao {

//    @Override
//    public List<Question> findAll() {
//        System.out.println("user findall");
//        final String hql = "FROM Question";
//        Query query = getSession().createQuery(hql);
//        query.setCacheable(true);
//        return query.list();
//    }
}
