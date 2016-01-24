package com.horadrim.talrasha.common.dao.impl;


import com.horadrim.talrasha.common.dao.ApplicationDao;
import com.horadrim.talrasha.common.dao.GenericHibernateDao;
import com.horadrim.talrasha.common.dao.UserDao;
import com.horadrim.talrasha.common.model.AppLication;
import com.horadrim.talrasha.common.model.User;
import org.springframework.stereotype.Repository;

@Repository("applicationDao")
public class ApplicationDaoImpl extends GenericHibernateDao<AppLication, Integer> implements ApplicationDao {

}
