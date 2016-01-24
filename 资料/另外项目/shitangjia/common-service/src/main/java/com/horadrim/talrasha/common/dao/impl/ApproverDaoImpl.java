package com.horadrim.talrasha.common.dao.impl;


import com.horadrim.talrasha.common.dao.ApproverDao;
import com.horadrim.talrasha.common.dao.GenericHibernateDao;
import com.horadrim.talrasha.common.model.Approver;
import org.springframework.stereotype.Repository;

@Repository("approverDao")
public class ApproverDaoImpl extends GenericHibernateDao<Approver, Integer> implements ApproverDao {

}
