package com.horadrim.talrasha.site.controller.request;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by Administrator on 2015/6/2.
 */
public class CartItem {
    public Integer id;
    public String name;
    public Integer count;
    public BigDecimal price;
    public String unitName;
    public String img;
    public String productDetail;
    public Date acceptTime;
    public int timeNode;

    public int getTimeNode() {
        return timeNode;
    }

    public void setTimeNode(int timeNode) {
        this.timeNode = timeNode;
    }

    public String getProductDetail() {
        return productDetail;
    }

    public void setProductDetail(String productDetail) {
        this.productDetail = productDetail;
    }

    public Date getAcceptTime() {
        return acceptTime;
    }

    public void setAcceptTime(Date acceptTime) {
        this.acceptTime = acceptTime;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getUnitName() {
        return unitName;
    }

    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
