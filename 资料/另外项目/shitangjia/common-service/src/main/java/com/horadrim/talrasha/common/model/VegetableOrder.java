package com.horadrim.talrasha.common.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Administrator on 2015/5/29.
 */
@Entity
@Table(name = "qc_vegetable_order")
public class VegetableOrder extends AbstractDomain {

    private String orderNum;

    private BigDecimal totalPrice;
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    private Date orderDate;

    private Date takeDate;

    private int status;    // 0 : 待取菜  1：待确认  2:已完成 3:超时订单

    private int isPayed;

    private int payType;  //关联 消费记录中的的支付类型的索引值 ConsumeHistory.AccountType

    private User user;

    private int deleted;

    private int canteenId;

    private String note;

    @Column(name = "note", length = 128, nullable = false)
    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    private Set<VegetableOrderItem> orderItems = new HashSet<>();
    @Column(name = "take_date")
    public Date getTakeDate() {
        return takeDate;
    }

    @Column(name = "pay_type", length = 1, nullable = true)
    public int getPayType() {
        return payType;
    }

    public void setPayType(int payType) {
        this.payType = payType;
    }

    @Column(name = "canteen_id" ,nullable = false)
    public int getCanteenId() {
        return canteenId;
    }

    public void setCanteenId(int canteenId) {
        this.canteenId = canteenId;
    }

    public void setDeleted(int deleted) {
        this.deleted = deleted;
    }

    @Column(name = "deleted", length = 1, nullable = false)
    public int getDeleted() {
        return deleted;
    }

    @OneToMany(cascade = { CascadeType.ALL },fetch = FetchType.EAGER,targetEntity = VegetableOrderItem.class)
    @JoinColumns(value = {@JoinColumn(name = "vegetable_order_id",referencedColumnName = "id")})
    public Set<VegetableOrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(Set<VegetableOrderItem> orderItems) {
        this.orderItems = orderItems;
    }

    @ManyToOne( cascade = {CascadeType.PERSIST, CascadeType.MERGE} ,fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id",nullable = false)
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Column(name = "is_payed", length = 1, nullable = false)
    public int getIsPayed() {
        return isPayed;
    }
    @Column(name = "order_num", length = 20, nullable = false)
    public String getOrderNum() {
        return orderNum;
    }

    @Column(name = "total_price", length = 8, nullable = false)
    public BigDecimal getTotalPrice() {
        return totalPrice;
    }
    @Column(name = "order_date", nullable = false)
    public Date getOrderDate() {
        return orderDate;
    }
    @Column(name = "status", length = 1, nullable = false)
    public int getStatus() {
        return status;
    }

    public void setOrderNum(String orderNum) {
        this.orderNum = orderNum;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public void setIsPayed(int isPayed) {
        this.isPayed = isPayed;
    }

    public void setTakeDate(Date takeDate) {
        this.takeDate = takeDate;
    }

    @Override
    public String toString() {
        return "VegetableOrder{" +
                "orderNum='" + orderNum + '\'' +
                ", totalPrice=" + totalPrice +
                ", orderDate=" + orderDate +
                ", status=" + status +
                ", isPayed=" + isPayed +
                ", payType=" + payType +
                ", user=" + user +
                ", deleted=" + deleted +
                ", canteenId=" + canteenId +
                ", orderItems=" + orderItems +
                '}';
    }
}
