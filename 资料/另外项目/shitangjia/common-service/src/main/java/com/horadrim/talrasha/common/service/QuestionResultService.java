package com.horadrim.talrasha.common.service;

import com.horadrim.talrasha.common.model.QuestionResult;
import com.horadrim.talrasha.common.util.QuestResultsPojo;

import java.util.List;

/**
 * Created by Administrator on 2015/7/27.
 */
public interface QuestionResultService extends GenericService<QuestionResult,Integer>{

    void addQuestResultsByList(List<QuestResultsPojo> lists,int questionUserId);
}
