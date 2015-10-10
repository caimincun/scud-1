package com.horadrim.talrasha.common.dao.impl;


import com.horadrim.talrasha.common.dao.GenericHibernateDao;
import com.horadrim.talrasha.common.dao.SignChainDao;
import com.horadrim.talrasha.common.dao.UserDao;
import com.horadrim.talrasha.common.model.SignChain;
import com.horadrim.talrasha.common.model.User;
import org.springframework.stereotype.Repository;

@Repository("signChainDao")
public class SignChainDaoImpl extends GenericHibernateDao<SignChain, Integer> implements SignChainDao {

}
