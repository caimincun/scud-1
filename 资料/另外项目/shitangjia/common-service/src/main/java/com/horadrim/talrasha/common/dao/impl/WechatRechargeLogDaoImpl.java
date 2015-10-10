package com.horadrim.talrasha.common.dao.impl;

import com.horadrim.talrasha.common.dao.GenericHibernateDao;
import com.horadrim.talrasha.common.dao.WechatRechargeLogDao;
import com.horadrim.talrasha.common.model.WechatRechargeLog;
import org.springframework.stereotype.Repository;

/**
 * Created by Administrator on 2015/6/15.
 * 微信充值记录DAO
 */
@Repository("wechatRechargeLogDao")
public class WechatRechargeLogDaoImpl extends GenericHibernateDao<WechatRechargeLog,Integer> implements WechatRechargeLogDao {
}
