package com.horadrim.talrasha.common.dao.impl;

import com.horadrim.talrasha.common.dao.GenericHibernateDao;
import com.horadrim.talrasha.common.dao.VegetableOrderItemDao;
import com.horadrim.talrasha.common.model.VegetableOrderItem;
import org.springframework.stereotype.Repository;

/**
 * Created by Administrator on 2015/6/1.
 */
@Repository("vegetableOrderItemDao")
public class VegetableOrderItemDaoImpl extends GenericHibernateDao<VegetableOrderItem,Integer> implements VegetableOrderItemDao {
}
