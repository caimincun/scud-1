package com.horadrim.talrasha.common.dao.impl;


import com.horadrim.talrasha.common.dao.GenericHibernateDao;
import com.horadrim.talrasha.common.dao.VegetableCategoryDao;
import com.horadrim.talrasha.common.model.VegetableCategory;
import org.springframework.stereotype.Repository;

@Repository("vegetableCategoryDao")
public class VegetableCategoryDaoImpl extends GenericHibernateDao<VegetableCategory, Integer> implements VegetableCategoryDao {

}
