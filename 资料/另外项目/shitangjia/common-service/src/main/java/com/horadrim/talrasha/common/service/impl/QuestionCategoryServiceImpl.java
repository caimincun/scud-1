package com.horadrim.talrasha.common.service.impl;

import com.horadrim.talrasha.common.dao.GenericDao;
import com.horadrim.talrasha.common.dao.QuestionCategoryDao;
import com.horadrim.talrasha.common.model.QuestionCategory;
import com.horadrim.talrasha.common.service.QuestionCategoryService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by Administrator on 2015/7/27.
 */
@Service("questionCategoryService")
public class QuestionCategoryServiceImpl extends GenericServiceImpl<QuestionCategory,Integer> implements QuestionCategoryService{
    @Resource
    private QuestionCategoryDao questionCategoryDao;

    @Override
    protected GenericDao<QuestionCategory, Integer> getGenericDao() {
        return questionCategoryDao;
    }
}
