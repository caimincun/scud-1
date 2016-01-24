package com.horadrim.talrasha.common.dao.impl;


import com.horadrim.talrasha.common.dao.CanteenDao;
import com.horadrim.talrasha.common.dao.GenericHibernateDao;
import com.horadrim.talrasha.common.model.Canteen;
import org.springframework.stereotype.Repository;

/**
 * Created by Administrator on 2015/6/1.
 */
@Repository("canteenDao")
public class CanteenDaoImpl extends GenericHibernateDao<Canteen,Integer> implements CanteenDao {
}
