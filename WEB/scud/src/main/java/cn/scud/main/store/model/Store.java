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
    // 店主 userToken
    private String userToken;
    //商铺类型
    private String storeType;
    // 商店名称
    private String storeName;
    //平均送货时间(如，3公里平均半小时或者5平均一小时之类的)
    private String avgTime;
    // 开始时间
    private String startTime;
    // 结束时间
    private String endTime;
    // 商铺地址
    private String address;
    // 店主联系电话
    private String storePhone;
    // 商店广播信息或者宣传口号
    private String slogan;
    // 商店头像图标,
    private String storePicture;
    //经纬度定位lbsid
    private int storeLbsid;

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

    public String getAvgTime() {
        return avgTime;
    }

    public void setAvgTime(String avgTime) {
        this.avgTime = avgTime;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
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

    public String getUserToken() {
        return userToken;
    }

    public void setUserToken(String userToken) {
        this.userToken = userToken;
    }
}
