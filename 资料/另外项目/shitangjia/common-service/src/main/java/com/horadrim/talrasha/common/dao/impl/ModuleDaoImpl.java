package com.horadrim.talrasha.common.dao.impl;

import com.horadrim.talrasha.common.dao.GenericHibernateDao;
import com.horadrim.talrasha.common.dao.ModuleDao;
import com.horadrim.talrasha.common.model.Module;
import org.springframework.stereotype.Repository;

@Repository("moduleDao")
public class ModuleDaoImpl extends GenericHibernateDao<Module,Integer> implements ModuleDao {
}
