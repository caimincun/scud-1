package com.horadrim.talrasha.common.dao.impl;

import com.horadrim.talrasha.common.dao.GenericHibernateDao;
import com.horadrim.talrasha.common.dao.InformationCategoryDao;
import com.horadrim.talrasha.common.model.InformationCategory;
import org.springframework.stereotype.Repository;

/**
 * Created by Administrator on 2015/7/27.
 */
@Repository("informationCategoryDao")
public class InformationCategoryDaoImpl extends GenericHibernateDao<InformationCategory,Integer> implements InformationCategoryDao {
}
