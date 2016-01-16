package com.horadrim.talrasha.common.dao.impl;


import com.horadrim.talrasha.common.dao.ApplicationSignChainNodeDao;
import com.horadrim.talrasha.common.dao.GenericHibernateDao;
import com.horadrim.talrasha.common.dao.UserDao;
import com.horadrim.talrasha.common.model.AppLicationSignChainNode;
import com.horadrim.talrasha.common.model.User;
import org.springframework.stereotype.Repository;

@Repository("applicationSignChainNodeDao")
public class ApplicationSignChainNodeDaoImpl extends GenericHibernateDao<AppLicationSignChainNode, Integer> implements ApplicationSignChainNodeDao {

}
