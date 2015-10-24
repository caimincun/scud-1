package com.horadrim.talrasha.admin.dao.impl;

import com.horadrim.talrasha.admin.dao.CanteenDeptDao;
import com.horadrim.talrasha.admin.model.CanteenDept;
import com.horadrim.talrasha.common.dao.GenericHibernateDao;
import org.springframework.stereotype.Repository;

/**
 * Created by Administrator on 2015/7/22.
 */
@Repository("canteenDeptDao")
public class CanteenDeptDaoImpl extends GenericHibernateDao<CanteenDept,Integer> implements CanteenDeptDao{
}
