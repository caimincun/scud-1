package com.horadrim.talrasha.admin.dao.impl;

import com.horadrim.talrasha.admin.dao.CanteenAuthorityDao;
import com.horadrim.talrasha.admin.model.CanteenAuthority;
import com.horadrim.talrasha.common.dao.GenericHibernateDao;
import org.springframework.stereotype.Repository;

/**
 * Created by Administrator on 2015/6/12.
 */
@Repository("canteenAuthorityDao")
public class CanteenAuthorityDaoImpl extends GenericHibernateDao<CanteenAuthority,Integer> implements CanteenAuthorityDao {
}
