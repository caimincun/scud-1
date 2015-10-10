package com.horadrim.talrasha.common.dao.impl;

import com.horadrim.talrasha.common.dao.GenericHibernateDao;
import com.horadrim.talrasha.common.dao.ProductCategoryDao;
import com.horadrim.talrasha.common.model.ProductCategory;
import org.springframework.stereotype.Repository;

/**
 * Created by Administrator on 2015/6/10.
 */
@Repository("productCategoryDao")
public class ProductCategoryDaoImpl extends GenericHibernateDao<ProductCategory,Integer> implements ProductCategoryDao {
}
