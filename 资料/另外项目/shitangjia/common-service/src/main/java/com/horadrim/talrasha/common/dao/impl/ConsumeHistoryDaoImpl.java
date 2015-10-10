package com.horadrim.talrasha.common.dao.impl;

import com.horadrim.talrasha.common.dao.ConsumeHistoryDao;
import com.horadrim.talrasha.common.dao.GenericHibernateDao;
import com.horadrim.talrasha.common.model.ConsumeHistory;
import org.springframework.stereotype.Repository;

/**
 * Created by Administrator on 2015/6/4.
 * 消费记录
 */
@Repository("consumeHistoryDao")
public class ConsumeHistoryDaoImpl extends GenericHibernateDao<ConsumeHistory,Integer> implements ConsumeHistoryDao {
}
