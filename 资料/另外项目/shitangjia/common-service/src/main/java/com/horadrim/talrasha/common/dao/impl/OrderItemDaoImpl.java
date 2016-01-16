package com.horadrim.talrasha.common.dao.impl;

import com.horadrim.talrasha.common.dao.GenericHibernateDao;
import com.horadrim.talrasha.common.dao.OrderItemDao;
import com.horadrim.talrasha.common.model.OrderItem;
import org.springframework.stereotype.Repository;

/**
 * Created by Administrator on 2015/6/1.
 */
@Repository("orderItemDao")
public class OrderItemDaoImpl extends GenericHibernateDao<OrderItem,Integer> implements OrderItemDao {
}
