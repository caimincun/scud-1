package com.horadrim.talrasha.common.dao.impl;

import com.horadrim.talrasha.common.dao.CanteenMessageDao;
import com.horadrim.talrasha.common.dao.GenericHibernateDao;
import com.horadrim.talrasha.common.model.CanteenMessage;
import org.springframework.stereotype.Repository;

/**
 * Created by Administrator on 2015/7/10.
 */
@Repository("canteenMessageDao")
public class CanteenMessageDaoImpl extends GenericHibernateDao<CanteenMessage,Integer> implements CanteenMessageDao{
}
