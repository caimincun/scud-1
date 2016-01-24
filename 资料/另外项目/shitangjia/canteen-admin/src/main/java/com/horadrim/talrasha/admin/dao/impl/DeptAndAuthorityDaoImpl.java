package com.horadrim.talrasha.admin.dao.impl;

import com.horadrim.talrasha.admin.dao.DeptAndAuthorityDao;
import com.horadrim.talrasha.admin.model.DeptAndAuthority;
import com.horadrim.talrasha.common.dao.GenericHibernateDao;
import org.springframework.stereotype.Repository;

/**
 * Created by Administrator on 2015/7/23.
 */
@Repository("deptAndAuthorityDao")
public class DeptAndAuthorityDaoImpl extends GenericHibernateDao<DeptAndAuthority,Integer> implements DeptAndAuthorityDao {
}
