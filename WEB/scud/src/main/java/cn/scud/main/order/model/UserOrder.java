package cn.scud.main.order.model;


import java.io.Serializable;

/**
 * Created by Administrator on 2015/6/25.
 */
public class UserOrder implements Serializable{

    private int orderId;
    //订单发起人 token
    private String orderUserToken;
    //订单接单人 token
    private String orderAcptUsken;
    //订单状态标志
    private int orderComplteFlag;       // 0:发布中 1：确认接单中  2. 完成 3：撤销
    // 订单唯一标志
    private String orderToken;
    // 订单名称
    private String orderTitle;
    //订单内容
    private String orderContent;
    // 邀约时间
    private String orderStartTime;
    //订单限时（订单完成有效时间）现在变成了预约时间
    private String orderLimitTime;
    //邀约地址
    private String orderServiceAddress;
    //（订单行业）
    private String orderCallScope;
    //定金
    private double orderMoney;
    // 意向接单人数量
    private int aptUserNum;


    // 这些属性是为了前台 展示
    //用户头像
    private String userPicture;
    //订单距离
    private Integer distance;
    // 订单发布人性别
    private int userSex;
    // 订单发布人年龄
    private int age;
    //订单发布人名称
    private String userName;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getUserPicture() {
        return userPicture;
    }

    public void setUserPicture(String userPicture) {
        this.userPicture = userPicture;
    }

    public Integer getDistance() {
        return distance;
    }

    public void setDistance(Integer distance) {
        this.distance = distance;
    }

    public int getUserSex() {
        return userSex;
    }

    public void setUserSex(int userSex) {
        this.userSex = userSex;
    }

    public int getAptUserNum() {
        return aptUserNum;
    }

    public void setAptUserNum(int aptUserNum) {
        this.aptUserNum = aptUserNum;
    }

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

    public String getOrderAcptUsken() {
        return orderAcptUsken;
    }

    public void setOrderAcptUsken(String orderAcptUsken) {
        this.orderAcptUsken = orderAcptUsken;
    }

    public int getOrderComplteFlag() {
        return orderComplteFlag;
    }

    public void setOrderComplteFlag(int orderComplteFlag) {
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
        return "UserOrder{" +
                "orderId=" + orderId +
                ", orderUserToken='" + orderUserToken + '\'' +
                ", orderAcptUsken='" + orderAcptUsken + '\'' +
                ", orderComplteFlag=" + orderComplteFlag +
                ", orderToken='" + orderToken + '\'' +
                ", orderTitle='" + orderTitle + '\'' +
                ", orderContent='" + orderContent + '\'' +
                ", orderStartTime='" + orderStartTime + '\'' +
                ", orderLimitTime='" + orderLimitTime + '\'' +
                ", orderServiceAddress='" + orderServiceAddress + '\'' +
                ", orderCallScope='" + orderCallScope + '\'' +
                ", orderMoney=" + orderMoney +
                ", userPicture='" + userPicture + '\'' +
                ", distance=" + distance +
                ", userSex=" + userSex +
                ", aptUserNum=" + aptUserNum +
                '}';
    }
}
