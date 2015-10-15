package cn.scud.main.storeorder.model;

import java.util.List;

/**
 * Created by Administrator on 2015/10/13.
 */
public class StoreOrderModel {

    private String userToken;
    // 关联地址 id
    private int receiptId;
    // 商铺token
    private String storeToken;

    public String getStoreToken() {
        return storeToken;
    }

    public void setStoreToken(String storeToken) {
        this.storeToken = storeToken;
    }

    private List<ProductAndNum> orders;

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

    public List<ProductAndNum> getOrders() {
        return orders;
    }

    public void setOrders(List<ProductAndNum> orders) {
        this.orders = orders;
    }

    @Override
    public String toString() {
        return "StoreOrderModel{" +
                "userToken='" + userToken + '\'' +
                ", receiptId=" + receiptId +
                ", orders=" + orders +
                '}';
    }
}
