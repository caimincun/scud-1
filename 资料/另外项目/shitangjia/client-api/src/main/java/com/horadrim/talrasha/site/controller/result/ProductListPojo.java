package com.horadrim.talrasha.site.controller.result;

import com.horadrim.talrasha.common.model.Product;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2015/6/15.
 */
public class ProductListPojo {

    private int categoryId;
    private String categoryName;
    private List<Product> products = new ArrayList<>();

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
}
