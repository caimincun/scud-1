package cn.scud.main.store.model;

import java.io.Serializable;

/**
 * Created by Administrator on 2015/9/6.
 */
public class Store implements Serializable {
    // 数据库自增id
    private int id;
    // 商店 token 标志
    private String storeToken;
    //商铺类型
    private String storeType;
    // 商店名称
    private String storeName;
    // 商铺地址
    private String address;
    // 店主联系电话
    private String storePhone;
    // 商店广播信息或者宣传口号
    private String slogan;
    // 商店头像图标,
    private String storePicture;
    // 起送价
    private double startPrice;
    //经纬度定位lbsid
    private int storeLbsid;
    // 距离
    private Integer distance;

    public double getStartPrice() {
        return startPrice;
    }

    public void setStartPrice(double startPrice) {
        this.startPrice = startPrice;
    }

    public Integer getDistance() {
        return distance;
    }

    public void setDistance(Integer distance) {
        this.distance = distance;
    }

    public String getStoreType() {
        return storeType;
    }

    public void setStoreType(String storeType) {
        this.storeType = storeType;
    }

    public int getStoreLbsid() {
        return storeLbsid;
    }

    public void setStoreLbsid(int storeLbsid) {
        this.storeLbsid = storeLbsid;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStoreToken() {
        return storeToken;
    }

    public void setStoreToken(String storeToken) {
        this.storeToken = storeToken;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getStorePhone() {
        return storePhone;
    }

    public void setStorePhone(String storePhone) {
        this.storePhone = storePhone;
    }

    public String getSlogan() {
        return slogan;
    }

    public void setSlogan(String slogan) {
        this.slogan = slogan;
    }

    public String getStorePicture() {
        return storePicture;
    }

    public void setStorePicture(String storePicture) {
        this.storePicture = storePicture;
    }

    @Override
    public String toString() {
        return "Store{" +
                "id=" + id +
                ", storeToken='" + storeToken + '\'' +
                ", storeType='" + storeType + '\'' +
                ", storeName='" + storeName + '\'' +
                ", address='" + address + '\'' +
                ", storePhone='" + storePhone + '\'' +
                ", slogan='" + slogan + '\'' +
                ", storePicture='" + storePicture + '\'' +
                ", startPrice=" + startPrice +
                ", storeLbsid=" + storeLbsid +
                ", distance=" + distance +
                '}';
    }
}
