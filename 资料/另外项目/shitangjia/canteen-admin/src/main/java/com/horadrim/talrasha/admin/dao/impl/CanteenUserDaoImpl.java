package com.horadrim.talrasha.admin.dao.impl;

import com.horadrim.talrasha.admin.dao.CanteenUserDao;
import com.horadrim.talrasha.admin.model.CanteenUser;
import com.horadrim.talrasha.common.dao.GenericHibernateDao;
import org.springframework.stereotype.Repository;

/**
 * Created by Administrator on 2015/6/12.
 */
@Repository("canteenUserDao")
public class CanteenUserDaoImpl extends GenericHibernateDao<CanteenUser,Integer> implements CanteenUserDao {
}
