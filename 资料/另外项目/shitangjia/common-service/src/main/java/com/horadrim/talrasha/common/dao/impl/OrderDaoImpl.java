package com.horadrim.talrasha.common.dao.impl;


import com.horadrim.talrasha.common.dao.GenericHibernateDao;
import com.horadrim.talrasha.common.dao.OrderDao;
import com.horadrim.talrasha.common.model.Order;
import com.horadrim.talrasha.common.model.OrderItem;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

/**
 * Created by Administrator on 2015/6/1.
 */
@Repository("orderDao")
public class OrderDaoImpl extends GenericHibernateDao<Order,Integer> implements OrderDao {

}
