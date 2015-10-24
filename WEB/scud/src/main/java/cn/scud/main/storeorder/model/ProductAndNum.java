package cn.scud.main.storeorder.model;

import java.io.Serializable;

/**
 * Created by Administrator on 2015/10/13.
 *  解析产品编号和数量的一个实体，json 解析专用，不映射数据库
 */
public class ProductAndNum{
    // 商品的 productToken
    private String productToken;
    // 商品数量
    private int productNum;

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

    @Override
    public String toString() {
        return "ProductAndNum{" +
                "productToken='" + productToken + '\'' +
                ", productNum=" + productNum +
                '}';
    }
}
