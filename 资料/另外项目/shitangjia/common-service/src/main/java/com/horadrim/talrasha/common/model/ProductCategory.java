package com.horadrim.talrasha.common.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by Administrator on 2015/6/10.
 */
@Entity
@Table(name = "qc_product_category")
public class ProductCategory extends AbstractDomain {

    private String categoryName;
    private int canteenId;

//    private Set<Product> products;
//    @OneToMany(cascade = { CascadeType.REFRESH, CascadeType.PERSIST,CascadeType.MERGE, CascadeType.REMOVE },
//            mappedBy ="productCategory",fetch=FetchType.EAGER)
//    public Set<Product> getProducts() {
//        return products;
//    }
//
//    public void setProducts(Set<Product> products) {
//        this.products = products;
//    }

    @Column(name = "categroy_name",nullable = false,length = 20)
    public String getCategoryName() {
        return categoryName;
    }

    @Column(name = "canteen_id")
    public int getCanteenId() {
        return canteenId;
    }

    public void setCanteenId(int canteenId) {
        this.canteenId = canteenId;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    @Override
    public String toString() {
        return "ProductCategory{" +
                "categoryName='" + categoryName + '\'' +
                ", canteenId=" + canteenId +
                '}';
    }
}
