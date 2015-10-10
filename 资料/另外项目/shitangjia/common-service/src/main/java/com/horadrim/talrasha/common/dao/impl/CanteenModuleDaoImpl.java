package com.horadrim.talrasha.common.dao.impl;

import com.horadrim.talrasha.common.dao.CanteenModuleDao;
import com.horadrim.talrasha.common.dao.GenericHibernateDao;
import com.horadrim.talrasha.common.model.Canteen_Module;
import org.springframework.stereotype.Repository;

/**
 * Created by Administrator on 2015/6/30.
 */
@Repository("canteenModuleDao")
public class CanteenModuleDaoImpl extends GenericHibernateDao<Canteen_Module,Integer> implements CanteenModuleDao {
}
