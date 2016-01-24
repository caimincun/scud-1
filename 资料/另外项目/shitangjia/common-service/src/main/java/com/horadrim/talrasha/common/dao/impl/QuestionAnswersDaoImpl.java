package com.horadrim.talrasha.common.dao.impl;

import com.horadrim.talrasha.common.dao.QuestionAnswersDao;
import com.horadrim.talrasha.common.model.QuestionAnswers;
import com.horadrim.talrasha.common.dao.GenericHibernateDao;
import org.springframework.stereotype.Repository;

/**
 * Created by Administrator on 2015/6/11.
 */
@Repository("questionAnswersDao")
public class QuestionAnswersDaoImpl extends GenericHibernateDao<QuestionAnswers,Integer> implements QuestionAnswersDao {
}
