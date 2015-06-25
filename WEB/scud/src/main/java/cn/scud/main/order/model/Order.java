package cn.scud.main.order.model;

/**
 * Created by Administrator on 2015/6/25.
 */
public class Order {

    private int orderId;
    //订单发起人Id
    private int orderUserId;
    //订单发起时间
    private String orderCreateTime;
    //订单完成标志
    private boolean orderComplteFlag;
    // 订单唯一标志
    private String orderToken;
    //订单内容
    private String orderContent;
    //要求接单人性别
    private int orderAcptUserSex;
    //订单佣金
    private double orderCommission;
    //订单限时（订单完成有效时间）
    private String orderLimitTime;
    //服务地址
    private String orderServiceAddress;
    //订单呼叫范围（全行业，或者 关键词推送）
    private String orderCallScope;
    //订单相关图片
    private String orderPictures;

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getOrderUserId() {
        return orderUserId;
    }

    public void setOrderUserId(int orderUserId) {
        this.orderUserId = orderUserId;
    }

    public String getOrderCreateTime() {
        return orderCreateTime;
    }

    public void setOrderCreateTime(String orderCreateTime) {
        this.orderCreateTime = orderCreateTime;
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

    public String getOrderContent() {
        return orderContent;
    }

    public void setOrderContent(String orderContent) {
        this.orderContent = orderContent;
    }

    public int getOrderAcptUserSex() {
        return orderAcptUserSex;
    }

    public void setOrderAcptUserSex(int orderAcptUserSex) {
        this.orderAcptUserSex = orderAcptUserSex;
    }

    public double getOrderCommission() {
        return orderCommission;
    }

    public void setOrderCommission(double orderCommission) {
        this.orderCommission = orderCommission;
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

    public String getOrderPictures() {
        return orderPictures;
    }

    public void setOrderPictures(String orderPictures) {
        this.orderPictures = orderPictures;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderId=" + orderId +
                ", orderUserId=" + orderUserId +
                ", orderCreateTime='" + orderCreateTime + '\'' +
                ", orderComplteFlag=" + orderComplteFlag +
                ", orderToken='" + orderToken + '\'' +
                ", orderContent='" + orderContent + '\'' +
                ", orderAcptUserSex=" + orderAcptUserSex +
                ", orderCommission=" + orderCommission +
                ", orderLimitTime='" + orderLimitTime + '\'' +
                ", orderServiceAddress='" + orderServiceAddress + '\'' +
                ", orderCallScope='" + orderCallScope + '\'' +
                ", orderPictures='" + orderPictures + '\'' +
                '}';
    }
}
