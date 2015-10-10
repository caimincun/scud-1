package com.horadrim.talrasha.common.dao.impl;

import com.horadrim.talrasha.common.dao.GenericHibernateDao;
import com.horadrim.talrasha.common.dao.ProductDao;
import com.horadrim.talrasha.common.model.Product;
import org.springframework.stereotype.Repository;

/**
 * Created by Administrator on 2015/6/1.
 */
@Repository("productDao")
public class ProductDaoImpl extends GenericHibernateDao<Product,Integer> implements ProductDao {
}
