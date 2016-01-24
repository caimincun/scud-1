package com.horadrim.talrasha.common.model;

import javax.persistence.*;

/**
 * Created by Administrator on 2015/6/2.
 */
@Entity
@Table(name="qc_vegetable_order_item")
public class VegetableOrderItem extends AbstractDomain {
//    private Order order;
    private Vegetable vegetable;
    private int count;

    @ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinColumn(name = "vegetable_id",nullable = false)
    public Vegetable getVegetable() {
        return vegetable;
    }
    @Column(name="count",length =5,nullable = false)
    public int getCount() {
        return count;
    }

    public void setVegetable(Vegetable vegetable) {
        this.vegetable = vegetable;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
