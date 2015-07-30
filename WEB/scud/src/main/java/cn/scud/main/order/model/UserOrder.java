package cn.scud.main.order.model;


import java.io.Serializable;

/**
 * Created by Administrator on 2015/6/25.
 */
public class UserOrder implements Serializable{

    private int orderId;
    //订单发起人 token
    private String orderUserToken;
    //订单完成标志
    private boolean orderComplteFlag;
    // 订单唯一标志
    private String orderToken;
    // 订单名称
    private String orderTitle;
    //订单内容
    private String orderContent;
    // 邀约时间
    private String orderStartTime;
    //订单限时（订单完成有效时间）
    private String orderLimitTime;
    //邀约地址
    private String orderServiceAddress;
    //消息推送范围
    private String orderCallScope;
    //定金
    private double orderMoney;

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public String getOrderUserToken() {
        return orderUserToken;
    }

    public void setOrderUserToken(String orderUserToken) {
        this.orderUserToken = orderUserToken;
    }

    public boolean isOrderComplteFlag() {
        return orderComplteFlag;
    }

    public void setOrderComplteFlag(boolean orderComplteFlag) {
        this.orderComplteFlag = orderComplteFlag;
    }

    public String getOrderToken() {
        return orderToken;
    }

    public void setOrderToken(String orderToken) {
        this.orderToken = orderToken;
    }

    public String getOrderTitle() {
        return orderTitle;
    }

    public void setOrderTitle(String orderTitle) {
        this.orderTitle = orderTitle;
    }

    public String getOrderContent() {
        return orderContent;
    }

    public void setOrderContent(String orderContent) {
        this.orderContent = orderContent;
    }

    public String getOrderStartTime() {
        return orderStartTime;
    }

    public void setOrderStartTime(String orderStartTime) {
        this.orderStartTime = orderStartTime;
    }

    public String getOrderLimitTime() {
        return orderLimitTime;
    }

    public void setOrderLimitTime(String orderLimitTime) {
        this.orderLimitTime = orderLimitTime;
    }

    public String getOrderServiceAddress() {
        return orderServiceAddress;
    }

    public void setOrderServiceAddress(String orderServiceAddress) {
        this.orderServiceAddress = orderServiceAddress;
    }

    public String getOrderCallScope() {
        return orderCallScope;
    }

    public void setOrderCallScope(String orderCallScope) {
        this.orderCallScope = orderCallScope;
    }

    public double getOrderMoney() {
        return orderMoney;
    }

    public void setOrderMoney(double orderMoney) {
        this.orderMoney = orderMoney;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderId=" + orderId +
                ", orderUserToken='" + orderUserToken + '\'' +
                ", orderComplteFlag=" + orderComplteFlag +
                ", orderToken='" + orderToken + '\'' +
                ", orderTitle='" + orderTitle + '\'' +
                ", orderContent='" + orderContent + '\'' +
                ", orderStartTime='" + orderStartTime + '\'' +
                ", orderLimitTime='" + orderLimitTime + '\'' +
                ", orderServiceAddress='" + orderServiceAddress + '\'' +
                ", orderCallScope='" + orderCallScope + '\'' +
                ", orderMoney=" + orderMoney +
                '}';
    }
}
