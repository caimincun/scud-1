package com.horadrim.talrasha.common.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by Administrator on 2015/7/7.
 * 蔬菜品类
 */
@Table(name = "qc_vegetable_category")
@Entity
public class VegetableCategory extends AbstractDomain {
    private String categoryName;
    private int canteenId;
    private int deleted;

    @Column(name = "deleted",nullable = false)
    public int getDeleted() {
        return deleted;
    }

    public void setDeleted(int deleted) {
        this.deleted = deleted;
    }

    @Column(name = "canteen_id",nullable = false)
    public int getCanteenId() {
        return canteenId;
    }

    public void setCanteenId(int canteenId) {
        this.canteenId = canteenId;
    }

    @Column(name = "category_name",nullable = false)
    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
}
