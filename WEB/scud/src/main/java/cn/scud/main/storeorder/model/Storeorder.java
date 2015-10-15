package cn.scud.main.storeorder.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2015/9/27.
 */
public class Storeorder implements Serializable {
    private int id;
    //下单人 token
    private String userToken;
    // 关联地址 id
    private int receiptId;
    // 商品的 productToken
    private String productToken;
    // 商铺token
    private String storeToken;
    // 商品数量
    private int productNum;
    // 订单状态
    private int orderFlag;  // 1: 代表正在进行  2.表示已经完成

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserToken() {
        return userToken;
    }

    public void setUserToken(String userToken) {
        this.userToken = userToken;
    }

    public int getReceiptId() {
        return receiptId;
    }

    public void setReceiptId(int receiptId) {
        this.receiptId = receiptId;
    }

    public String getProductToken() {
        return productToken;
    }

    public void setProductToken(String productToken) {
        this.productToken = productToken;
    }

    public int getProductNum() {
        return productNum;
    }

    public void setProductNum(int productNum) {
        this.productNum = productNum;
    }

    public String getStoreToken() {
        return storeToken;
    }

    public void setStoreToken(String storeToken) {
        this.storeToken = storeToken;
    }

    public int getOrderFlag() {
        return orderFlag;
    }

    public void setOrderFlag(int orderFlag) {
        this.orderFlag = orderFlag;
    }

    @Override
    public String toString() {
        return "Storeorder{" +
                "id=" + id +
                ", userToken='" + userToken + '\'' +
                ", receiptId=" + receiptId +
                ", productToken='" + productToken + '\'' +
                ", storeToken='" + storeToken + '\'' +
                ", productNum=" + productNum +
                '}';
    }
}
