package com.horadrim.talrasha.common.service.impl;

import com.horadrim.talrasha.common.dao.GenericDao;
import com.horadrim.talrasha.common.dao.WechatRechargeLogDao;
import com.horadrim.talrasha.common.model.WechatRechargeLog;
import com.horadrim.talrasha.common.service.WechatRechargeLogService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by Administrator on 2015/6/15.
 * 微信充值Service
 */
@Service("WechatRechargeLogService")
public class WechatRechargeLogServiceImpl extends GenericServiceImpl<WechatRechargeLog,Integer> implements WechatRechargeLogService {
    @Resource
    private WechatRechargeLogDao wechatRechargeLogDao;

    @Override
    protected GenericDao<WechatRechargeLog, Integer> getGenericDao() {
        return wechatRechargeLogDao;
    }

    @Override
    public WechatRechargeLog getLog(String orderNum) {
        final String hql = "FROM WechatRechargeLog WHERE orderNum=?";
        return wechatRechargeLogDao.query(hql,new Object[]{orderNum});
    }
}
