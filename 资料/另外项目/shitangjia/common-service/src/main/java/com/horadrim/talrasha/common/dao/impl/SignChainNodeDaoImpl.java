package com.horadrim.talrasha.common.dao.impl;


import com.horadrim.talrasha.common.dao.GenericHibernateDao;
import com.horadrim.talrasha.common.dao.SignChainNodeDao;
import com.horadrim.talrasha.common.model.SignChainNode;
import org.springframework.stereotype.Repository;

@Repository("signChainNodeDao")
public class SignChainNodeDaoImpl extends GenericHibernateDao<SignChainNode, Integer> implements SignChainNodeDao {

}
