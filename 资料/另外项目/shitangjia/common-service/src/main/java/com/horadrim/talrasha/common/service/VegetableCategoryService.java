package com.horadrim.talrasha.common.service;

import com.horadrim.talrasha.common.model.VegetableCategory;

import java.util.List;

/**
 * Created by Administrator on 2015/7/7.
 */
public interface VegetableCategoryService extends GenericService<VegetableCategory,Integer>{

    public List<VegetableCategory> getVCategoryes();
}
