package com.horadrim.talrasha.common.service;

import com.horadrim.talrasha.common.model.CanteenMessage;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2015/7/10.
 */
public interface CanteenMessageService extends GenericService<CanteenMessage,Integer> {

    List<Map<String,Object>> countNotReadMsg(int canteenId);



}
