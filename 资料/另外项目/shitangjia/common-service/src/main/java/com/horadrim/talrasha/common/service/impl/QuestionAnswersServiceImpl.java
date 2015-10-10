package com.horadrim.talrasha.common.service.impl;

import com.horadrim.talrasha.common.dao.QuestionAnswersDao;
import com.horadrim.talrasha.common.model.QuestionAnswers;
import com.horadrim.talrasha.common.service.QuestionAnswersService;
import com.horadrim.talrasha.common.dao.GenericDao;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by Administrator on 2015/6/11.
 */
@Service("questionAnswersService")
public class QuestionAnswersServiceImpl extends GenericServiceImpl<QuestionAnswers,Integer> implements QuestionAnswersService {

    @Resource
    private QuestionAnswersDao questionAnswersDao;

    @Override
    protected GenericDao<QuestionAnswers, Integer> getGenericDao() {
        return questionAnswersDao;
    }


}
