package com.horadrim.talrasha.common.service.impl;

import com.horadrim.talrasha.common.dao.GenericDao;
import com.horadrim.talrasha.common.dao.QuestionResultDao;
import com.horadrim.talrasha.common.model.QuestionResult;
import com.horadrim.talrasha.common.service.QuestionResultService;
import com.horadrim.talrasha.common.util.QuestResultsPojo;
import com.horadrim.talrasha.common.util.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Administrator on 2015/7/27.
 */
@Service("questionResultService")
public class QuestionResultServiceImpl extends GenericServiceImpl<QuestionResult,Integer> implements QuestionResultService{
    @Resource
    private QuestionResultDao questionResultDao;
    @Override
    protected GenericDao<QuestionResult, Integer> getGenericDao() {
        return questionResultDao;
    }

    @Override
    public void addQuestResultsByList(List<QuestResultsPojo> lists,int questionUserId) {
        QuestionResult questionResult;
        for(QuestResultsPojo questAnswerPojo:lists){
            int[] quesitonIds = StringUtils.parseToIntArray(questAnswerPojo.getAnswerId(), ",") ;
            for(int qid: quesitonIds){
                questionResult = new QuestionResult();
                questionResult.setAnswersId(qid);
                questionResult.setQuestionId(questAnswerPojo.getQuestionId());
                questionResult.setUserId(questionUserId);
                save(questionResult);
            }
        }
    }
}
