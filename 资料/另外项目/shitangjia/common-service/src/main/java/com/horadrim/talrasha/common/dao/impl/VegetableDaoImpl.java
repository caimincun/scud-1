package com.horadrim.talrasha.common.dao.impl;


import com.horadrim.talrasha.common.dao.GenericHibernateDao;
import com.horadrim.talrasha.common.dao.VegetableDao;
import com.horadrim.talrasha.common.model.Vegetable;
import org.springframework.stereotype.Repository;

@Repository("vegetableDao")
public class VegetableDaoImpl extends GenericHibernateDao<Vegetable, Integer> implements VegetableDao {

}
