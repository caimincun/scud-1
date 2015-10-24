package com.horadrim.talrasha.common.model;

import javax.persistence.*;

/**
 * Created by Administrator on 2015/6/2.
 */
@Entity
@Table(name="qc_order_item")
public class OrderItem extends AbstractDomain {
//    private Order order;
    private Product product;
    private int count;

//    @ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
//    @JoinColumn(name = "order_id",nullable = false)
//    public Order getOrder() {
//        return order;
//    }
//
//    public void setOrder(Order order) {
//        this.order = order;
//    }

    @ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinColumn(name = "product_id",nullable = false)
    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    @Column(name="count",length =5,nullable = false)
    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
