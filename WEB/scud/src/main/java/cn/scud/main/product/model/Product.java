package cn.scud.main.product.model;

import java.io.Serializable;

/**
 * Created by Administrator on 2015/9/15.
 */
public class Product implements Serializable{
    private int id;
    // 商品图片
    private String productPictures;
    //商品名称
    private String prductName;
    // 商品类型
    private String productType;
    //价格
    private double productMoney;
    // 库存
    private int surplusNum;
    // 描述
    private String descritpion;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProductPictures() {
        return productPictures;
    }

    public void setProductPictures(String productPictures) {
        this.productPictures = productPictures;
    }

    public String getPrductName() {
        return prductName;
    }

    public void setPrductName(String prductName) {
        this.prductName = prductName;
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
}
