package com.horadrim.talrasha.common.service.dto;

import java.math.BigDecimal;

/**
 * Created by Administrator on 2015/7/2.
 */
public class OrderItemPojo {
    private String productName;
    private Integer count;
    private BigDecimal price;

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
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
}
