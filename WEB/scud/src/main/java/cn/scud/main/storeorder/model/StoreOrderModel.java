package cn.scud.main.storeorder.model;

import java.util.List;

/**
 * Created by Administrator on 2015/10/13.
 */
public class StoreOrderModel {

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

    private List<ProductModel> orders;


    public int getReceiptId() {
        return receiptId;
    }

    public void setReceiptId(int receiptId) {
        this.receiptId = receiptId;
    }

    public List<ProductModel> getOrders() {
        return orders;
    }

    public void setOrders(List<ProductModel> orders) {
        this.orders = orders;
    }

    @Override
    public String toString() {
        return "StoreOrderModel{" +
                ", receiptId=" + receiptId +
                ", orders=" + orders +
                '}';
    }
}
