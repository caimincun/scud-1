package cn.scud.main.storeorder.model;

/**
 * Created by Administrator on 2015/10/31.
 * 商铺订单界面显示的数据封装
 */
public class StoreOrderListlEntity {
    //userToken
//    private String userToken;
//
//    // storeToken
//    private String storeToken;

    // 订单 token 标识
    private String storeOrderToken;

    // 下单人姓名
    private String userName;
    //订单状态 1： 正在交易 2：交易进行中
    private int orderState;


    // 商品图片 路径
    private String productPrcture;
    // 商品名称
    private String productName;
    //显示的这件商品数量
    private int showProductNum;
    // 订单列表显示的商品的总价
     private double showProductToalPrice;


    // 订单总共的商品的数量
    private int totalProductNum;
    // 订单的总价
    private double totalProductPrice;




    public String getStoreOrderToken() {
        return storeOrderToken;
    }

    public void setStoreOrderToken(String storeOrderToken) {
        this.storeOrderToken = storeOrderToken;
    }

//    public String getUserToken() {
//        return userToken;
//    }
//
//    public void setUserToken(String userToken) {
//        this.userToken = userToken;
//    }
//
//    public String getStoreToken() {
//        return storeToken;
//    }
//
//    public void setStoreToken(String storeToken) {
//        this.storeToken = storeToken;
//    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getOrderState() {
        return orderState;
    }

    public void setOrderState(int orderState) {
        this.orderState = orderState;
    }

    public int getTotalProductNum() {
        return totalProductNum;
    }

    public void setTotalProductNum(int totalProductNum) {
        this.totalProductNum = totalProductNum;
    }

    public double getTotalProductPrice() {
        return totalProductPrice;
    }

    public void setTotalProductPrice(double totalProductPrice) {
        this.totalProductPrice = totalProductPrice;
    }


    public String getProductPrcture() {
        return productPrcture;
    }

    public void setProductPrcture(String productPrcture) {
        this.productPrcture = productPrcture;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getShowProductNum() {
        return showProductNum;
    }

    public void setShowProductNum(int showProductNum) {
        this.showProductNum = showProductNum;
    }

    public double getShowProductToalPrice() {
        return showProductToalPrice;
    }

    public void setShowProductToalPrice(double showProductToalPrice) {
        this.showProductToalPrice = showProductToalPrice;
    }

    @Override
    public String toString() {
        return "StoreOrderListlEntity{" +
//                "userToken='" + userToken + '\'' +
//                ", storeToken='" + storeToken + '\'' +
                ", userName='" + userName + '\'' +
                ", orderState=" + orderState +
                ", productPrcture='" + productPrcture + '\'' +
                ", productName='" + productName + '\'' +
                ", showProductNum=" + showProductNum +
                ", showProductToalPrice=" + showProductToalPrice +
                ", totalProductNum=" + totalProductNum +
                ", totalProductPrice=" + totalProductPrice +
                ", storeOrderToken='" + storeOrderToken + '\'' +
                '}';
    }
}