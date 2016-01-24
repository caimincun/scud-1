package com.horadrim.talrasha.common.dao.impl;


import com.horadrim.talrasha.common.dao.GenericHibernateDao;
import com.horadrim.talrasha.common.dao.VegetableOrderDao;
import com.horadrim.talrasha.common.model.VegetableOrder;
import org.springframework.stereotype.Repository;

/**
 * Created by Administrator on 2015/6/1.
 */
@Repository("vegetableOrderDao")
public class VegetableOrderDaoImpl extends GenericHibernateDao<VegetableOrder,Integer> implements VegetableOrderDao {

}
