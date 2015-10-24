package com.horadrim.talrasha.common.service.impl;

import com.horadrim.talrasha.common.dao.GenericDao;
import com.horadrim.talrasha.common.dao.VegetableCategoryDao;
import com.horadrim.talrasha.common.model.VegetableCategory;
import com.horadrim.talrasha.common.service.VegetableCategoryService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Administrator on 2015/7/7.
 */
@Service("vegetableCategoryService")
public class VegetableCategoryServiceImpl extends GenericServiceImpl<VegetableCategory,Integer> implements VegetableCategoryService {
    @Resource
    private VegetableCategoryDao vegetableCategoryDao;
    @Override
    protected GenericDao<VegetableCategory, Integer> getGenericDao() {
        return vegetableCategoryDao;
    }

    @Override
    public List<VegetableCategory> getVCategoryes() {
        String hql="FROM  VegetableCategory where deleted=?";
        return vegetableCategoryDao.list(hql,new Object[]{0});
    }
}
