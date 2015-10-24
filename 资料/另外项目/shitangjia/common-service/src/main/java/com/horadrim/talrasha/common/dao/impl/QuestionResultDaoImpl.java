package com.horadrim.talrasha.common.dao.impl;

import com.horadrim.talrasha.common.dao.GenericHibernateDao;
import com.horadrim.talrasha.common.dao.QuestionResultDao;
import com.horadrim.talrasha.common.model.QuestionResult;
import org.springframework.stereotype.Repository;

/**
 * Created by Administrator on 2015/7/27.
 */
@Repository("questionResultDao")
public class QuestionResultDaoImpl extends GenericHibernateDao<QuestionResult,Integer> implements QuestionResultDao {
}
