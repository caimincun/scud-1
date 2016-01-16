package com.horadrim.talrasha.site.controller.request;

import java.math.BigDecimal;

/**
 * Created by Administrator on 2015/6/2.
 */
public class AddToCartReq {
    public Integer id;
    public Integer count;
    public BigDecimal price;

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
}
