package com.horadrim.talrasha.common.service.impl;

import com.horadrim.talrasha.common.dao.GenericDao;
import com.horadrim.talrasha.common.dao.ProductCategoryDao;
import com.horadrim.talrasha.common.model.ProductCategory;
import com.horadrim.talrasha.common.service.ProductCategoryService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2015/6/10.
 */
@Service("productCategoryService")
public class ProductCategoryServiceImpl extends GenericServiceImpl<ProductCategory,Integer> implements ProductCategoryService {
    @Resource
    private ProductCategoryDao productCategoryDao;
    @Override
    protected GenericDao<ProductCategory, Integer> getGenericDao() {
        return productCategoryDao;
    }

    @Override
    public List<ProductCategory> listProduct(int canteedId) {

        final String hql = "FROM ProductCategory where canteenId = ?";
        Object[] params = {canteedId};
        return productCategoryDao.list(hql,params);
    }
}
