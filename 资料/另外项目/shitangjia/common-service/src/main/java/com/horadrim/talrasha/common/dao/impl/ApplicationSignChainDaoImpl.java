package com.horadrim.talrasha.common.dao.impl;


import com.horadrim.talrasha.common.dao.ApplicationSignChainDao;
import com.horadrim.talrasha.common.dao.GenericHibernateDao;
import com.horadrim.talrasha.common.model.AppLicationSignChain;
import org.springframework.stereotype.Repository;

@Repository("applicationSignChainDao")
public class ApplicationSignChainDaoImpl extends GenericHibernateDao<AppLicationSignChain, Integer> implements ApplicationSignChainDao {

}
