package com.horadrim.talrasha.common.dao.impl;

import com.horadrim.talrasha.common.dao.GenericHibernateDao;
import com.horadrim.talrasha.common.dao.TixianDao;
import com.horadrim.talrasha.common.model.Tixian;
import org.springframework.stereotype.Repository;

/**
 * Created by Administrator on 2015/7/17.
 */
@Repository("tixianDao")
public class TixianDaoImpl extends GenericHibernateDao<Tixian,Integer> implements TixianDao {
}
