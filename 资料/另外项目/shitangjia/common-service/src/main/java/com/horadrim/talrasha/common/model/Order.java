package com.horadrim.talrasha.common.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Administrator on 2015/5/29.
 */
@Entity
@Table(name = "qc_order")
public class Order extends AbstractDomain {

    private String orderNum;

    private BigDecimal totalPrice;
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    private Date orderDate;  //下单时间

    private int status;    //订单状态 0    未取餐 1 已取餐 2 转赠 3 撤销

    private int isPayed;

    private User user;

    private int deleted;

    private Integer payType;

    private int canteenId;
    private Date takeDate;  //取餐时间
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    private int takeNode;   //取餐节点
    private Set<OrderItem> orderItems = new HashSet<>();

    @Column(name = "canteen_id" ,nullable = false)
    public int getCanteenId() {
        return canteenId;
    }
    @Column(name = "deleted", length = 1, nullable = false)
    public int getDeleted() {
        return deleted;
    }
    @OneToMany(cascade = { CascadeType.ALL },fetch = FetchType.EAGER,targetEntity = OrderItem.class)
    @JoinColumns(value = {@JoinColumn(name = "order_id",referencedColumnName = "id")})
    public Set<OrderItem> getOrderItems() {
        return orderItems;
    }
    @ManyToOne( cascade = {CascadeType.PERSIST, CascadeType.MERGE} ,fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id",nullable = false)
    public User getUser() {
        return user;
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
    @Column(name = "take_date")
    public Date getTakeDate() {
        return takeDate;
    }
    @Column(name = "take_node")
    public int getTakeNode() {
        return takeNode;
    }
    @Column(name = "pay_type")
    public Integer getPayType() {
        return payType;
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

    public void setOrderItems(Set<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setCanteenId(int canteenId) {
        this.canteenId = canteenId;
    }

    public void setDeleted(int deleted) {
        this.deleted = deleted;
    }

    public void setTakeDate(Date takeDate) {
        this.takeDate = takeDate;
    }

    public void setTakeNode(int takeNode) {
        this.takeNode = takeNode;
    }

    public void setPayType(Integer payType) {
        this.payType = payType;
    }
}
