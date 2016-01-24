package com.horadrim.talrasha.common.dao.impl;

import com.horadrim.talrasha.common.dao.CollectionDao;
import com.horadrim.talrasha.common.dao.GenericHibernateDao;
import com.horadrim.talrasha.common.dao.OrderItemDao;
import com.horadrim.talrasha.common.model.Collection;
import com.horadrim.talrasha.common.model.OrderItem;
import org.springframework.stereotype.Repository;

/**
 * Created by Administrator on 2015/6/1.
 */
@Repository("collectionDao")
public class CollectionDaoImpl extends GenericHibernateDao<Collection,Integer> implements CollectionDao {
}
