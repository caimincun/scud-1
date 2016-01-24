package com.horadrim.talrasha.common.dao.impl;

import com.horadrim.talrasha.common.dao.GenericHibernateDao;
import com.horadrim.talrasha.common.dao.QuestionCategoryDao;
import com.horadrim.talrasha.common.model.QuestionCategory;
import org.springframework.stereotype.Repository;

/**
 * Created by Administrator on 2015/7/27.
 */
@Repository("questionCategoryDao")
public class QuestionCategoryDaoImpl extends GenericHibernateDao<QuestionCategory,Integer> implements QuestionCategoryDao {
}
