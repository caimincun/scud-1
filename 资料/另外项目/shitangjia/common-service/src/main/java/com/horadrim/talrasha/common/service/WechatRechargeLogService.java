package com.horadrim.talrasha.common.service;

import com.horadrim.talrasha.common.model.WechatRechargeLog;

/**
 * Created by Administrator on 2015/6/15.
 */
public interface WechatRechargeLogService extends GenericService<WechatRechargeLog,Integer>{

    WechatRechargeLog getLog(String orderNum);

}
