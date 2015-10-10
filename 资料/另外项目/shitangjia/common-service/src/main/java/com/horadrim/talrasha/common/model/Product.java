package com.horadrim.talrasha.common.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by Administrator on 2015/6/1.
 */
@Entity
@Table(name="qc_product")
public class Product extends AbstractDomain{

    private String img;
    private String name;
    private BigDecimal price;
    private String description;
    private int canteenId;
    private Date createdTime;           //销售时间
//    private int productCategoryId; //类别  ex 汤菜，荤菜...
    private ProductCategory productCategory; //类别  ex 汤菜，荤菜...
    private int timeNode;    // 默认0 ， 1早餐，2午餐 ，晚餐
    private int deleteFlag;
    private int book;       //是否支持预定 1:支持预定 2：不支持预定

    @Column(name="book",length = 4)
    public int getBook() {
        return book;
    }

    public void setBook(int book) {
        this.book = book;
    }

    @Column(name="img",length = 100,nullable = false)
    public String getImg() {
        return img;
    }
    @Column(name="name",length =20,nullable = false)
    public String getName() {
        return name;
    }
    @Column(name="price")
    public BigDecimal getPrice() {
        return price;
    }
    @Column(name="description",length = 255)
    public String getDescription() {
        return description;
    }
    @Column(name = "canteen_id",nullable = false)
    public int getCanteenId() {
        return canteenId;
    }
//    @Column(name = "product_category_id",nullable = false)
//    public int getProductCategoryId() {
//        return productCategoryId;
//    }


    @ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JoinColumn(name = "product_category_id",nullable = false)
    public ProductCategory getProductCategory() {
        return productCategory;
    }

    public void setProductCategory(ProductCategory productCategory) {
        this.productCategory = productCategory;
    }

    @Column(name = "created_time",nullable = false)
    public Date getCreatedTime() {
        return createdTime;
    }
    @Column(name = "time_node",nullable = false,length = 1)
    public int getTimeNode() {
        return timeNode;
    }

    public void setTimeNode(int timeNode) {
        this.timeNode = timeNode;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setCanteenId(int canteenId) {
        this.canteenId = canteenId;
    }

    @Column(name="deleteflag")
    public int getDeleteFlag() {
        return deleteFlag;
    }

    public void setDeleteFlag(int deleteFlag) {
        this.deleteFlag = deleteFlag;
    }

    @Override
    public String toString() {
        return "Product{" +
                "img='" + img + '\'' +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", description='" + description + '\'' +
                ", canteenId=" + canteenId +
                ", createdTime=" + createdTime +
                ", productCategory=" + productCategory +
                ", timeNode=" + timeNode +
                '}';
    }
}
