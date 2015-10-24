package cn.scud.main.product.model;

import java.io.Serializable;

/**
 * Created by Administrator on 2015/9/15.
 */
public class Product implements Serializable{
    private int id;
    //storetoken,用于标志产品是属于哪一家商铺，或者说那一个人的
    private String storeToken;
    // 产品 token ,用于标志 产品 的唯一标识
    private String productToken;
    // 商品图片
    private String productPictures;
    //商品名称
    private String productName;
    // 商品类型
    private String productType;
    //价格
    private double productMoney;
    // 库存
    private int surplusNum;
    // 描述
    private String descritpion;

    // 是否删除下架
    private int deleteFlag;     // 1：正在销售中 0：产品下架，逻辑删除

    public String getProductToken() {
        return productToken;
    }

    public void setProductToken(String productToken) {
        this.productToken = productToken;
    }

    public int getDeleteFlag() {
        return deleteFlag;
    }

    public void setDeleteFlag(int deleteFlag) {
        this.deleteFlag = deleteFlag;
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

    public String getProductPictures() {
        return productPictures;
    }

    public void setProductPictures(String productPictures) {
        this.productPictures = productPictures;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public double getProductMoney() {
        return productMoney;
    }

    public void setProductMoney(double productMoney) {
        this.productMoney = productMoney;
    }

    public int getSurplusNum() {
        return surplusNum;
    }

    public void setSurplusNum(int surplusNum) {
        this.surplusNum = surplusNum;
    }

    public String getDescritpion() {
        return descritpion;
    }

    public void setDescritpion(String descritpion) {
        this.descritpion = descritpion;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", storeToken='" + storeToken + '\'' +
                ", productToken='" + productToken + '\'' +
                ", productPictures='" + productPictures + '\'' +
                ", productName='" + productName + '\'' +
                ", productType='" + productType + '\'' +
                ", productMoney=" + productMoney +
                ", surplusNum=" + surplusNum +
                ", descritpion='" + descritpion + '\'' +
                ", deleteFlag=" + deleteFlag +
                '}';
    }
}
