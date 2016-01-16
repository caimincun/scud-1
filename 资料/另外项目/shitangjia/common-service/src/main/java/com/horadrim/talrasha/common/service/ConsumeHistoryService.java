package com.horadrim.talrasha.common.service;

import com.horadrim.talrasha.common.model.ConsumeHistory;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2015/6/4.
 * 消费记录service
 */
public interface ConsumeHistoryService extends GenericService<ConsumeHistory,Integer> {

    List<Map<String,Object>> queryConsumePage(int id,Integer accountType,Integer consumeType,int currentPage , int size);

}
