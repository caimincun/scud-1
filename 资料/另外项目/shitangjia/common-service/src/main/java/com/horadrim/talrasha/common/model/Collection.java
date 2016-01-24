package com.horadrim.talrasha.common.model;

import javax.persistence.*;

/**
 * Created by Administrator on 2015/6/1.
 */
@Entity
@Table(name = "qc_collection")
public class Collection extends AbstractDomain {
    private User user;
    private Product product;

    @ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id",nullable = false)
    public User getUser() {
        return user;
    }

    @ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinColumn(name = "product_id",nullable = false)
    public Product getProduct() {
        return product;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
