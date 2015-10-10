package com.horadrim.talrasha.admin.dao.impl;

import com.horadrim.talrasha.admin.dao.AwardsDao;
import com.horadrim.talrasha.admin.model.Awards;
import com.horadrim.talrasha.common.dao.GenericHibernateDao;
import org.springframework.stereotype.Repository;

/**
 * Created by Administrator on 2015/6/16.
 */
@Repository("awardsDao")
public class AwardsDaoImpl extends GenericHibernateDao<Awards,Integer> implements AwardsDao {
}
