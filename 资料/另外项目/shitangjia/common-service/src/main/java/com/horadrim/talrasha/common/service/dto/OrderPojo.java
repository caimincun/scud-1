package com.horadrim.talrasha.common.service.dto;


import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by Administrator on 2015/7/1.
 */
public class OrderPojo {
    private Integer orderId;

    private String orderNum;

    private BigDecimal totalPrice;

    private Date orderDate;

    private Integer status;    //订单状态 0 未取餐 1 已取餐 2 转赠 3 撤销

    private Integer isPayed;

    private Integer deleted;

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public String getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(String orderNum) {
        this.orderNum = orderNum;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getIsPayed() {
        return isPayed;
    }

    public void setIsPayed(Integer isPayed) {
        this.isPayed = isPayed;
    }

    public Integer getDeleted() {
        return deleted;
    }

    public void setDeleted(Integer deleted) {
        this.deleted = deleted;
    }
}
