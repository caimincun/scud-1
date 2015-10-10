package com.horadrim.talrasha.common.service;

import com.horadrim.talrasha.common.model.ProductCategory;

import java.util.List;

/**
 * Created by Administrator on 2015/6/10.
 */
public interface ProductCategoryService extends GenericService<ProductCategory,Integer> {

    List<ProductCategory> listProduct(int canteenId);



}
