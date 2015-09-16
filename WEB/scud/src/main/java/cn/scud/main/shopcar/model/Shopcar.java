package cn.scud.main.shopcar.model;

import java.io.Serializable;

/**
 * Created by Administrator on 2015/9/16.
 */
public class Shopcar implements Serializable{

    private int id;
    //购物车 token ,用户的 userToken 代替
    private String shopToken;
    // 用户 userToken, 代表某一个人买的
    private String userToken;
    // 商店的token
    private String storeToken;
    // 商品数量
    private int productNum;
    //商品的token
    private String productToken;

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

    public String getShopToken() {
        return shopToken;
    }

    public void setShopToken(String shopToken) {
        this.shopToken = shopToken;
    }

    public int getProductNum() {
        return productNum;
    }

    public void setProductNum(int productNum) {
        this.productNum = productNum;
    }

    public String getProductToken() {
        return productToken;
    }

    public void setProductToken(String productToken) {
        this.productToken = productToken;
    }

    public String getStoreToken() {
        return storeToken;
    }

    public void setStoreToken(String storeToken) {
        this.storeToken = storeToken;
    }

    @Override
    public String toString() {
        return "Shopcar{" +
                "id=" + id +
                ", shopToken='" + shopToken + '\'' +
                ", userToken='" + userToken + '\'' +
                ", storeToken='" + storeToken + '\'' +
                ", productNum=" + productNum +
                ", productToken='" + productToken + '\'' +
                '}';
    }
}
