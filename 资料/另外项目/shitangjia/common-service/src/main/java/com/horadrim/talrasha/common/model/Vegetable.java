package com.horadrim.talrasha.common.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by Administrator on 2015/7/7.
 * 蔬菜
 */
@Table(name = "qc_vegetable")
@Entity
public class Vegetable extends AbstractDomain {

    private int vegetableCategoryId;   //关联类别id
    private String vegetableName;      //蔬菜名
    private BigDecimal unitPrice;       //单价
    private String unitName;            //单位   元/个 元/斤 元/袋
    private String img;                 //菜品图片
    private String vegetableDescribe;            //蔬菜描述  ex:一个两百克
    private String description;                 // 菜品描述
    private int surplusNum;          //剩余数量
    private Date saleTime;              //销售时间，精度年月日 ex 2015-07-07
    private int canteenId;              //食堂
//    private boolean exist;            // 上架下架标志

    @Column(name = "vegetable_category_id")
    public int getVegetableCategoryId() {
        return vegetableCategoryId;
    }
    @Column(name = "vegetable_name",nullable = false,length = 20)
    public String getVegetableName() {
        return vegetableName;
    }
    @Column(name = "unit_price",nullable = false)
    public BigDecimal getUnitPrice() {
        return unitPrice;
    }
    @Column(name = "unit_name",nullable = false,length = 10)
    public String getUnitName() {
        return unitName;
    }
    @Column(name = "img",nullable = false)
    public String getImg() {
        return img;
    }
    @Column(name = "surplus_num")
    public int getSurplusNum() {
        return surplusNum;
    }
    @Column(name = "sale_time")
    public Date getSaleTime() {
        return saleTime;
    }
    @Column(name = "vegetable_describe")
    public String getVegetableDescribe() {
        return vegetableDescribe;
    }
    @Column(name = "canteen_id")
    public int getCanteenId() {
        return canteenId;
    }

    public void setCanteenId(int canteenId) {
        this.canteenId = canteenId;
    }

    public void setVegetableCategoryId(int vegetableCategoryId) {
        this.vegetableCategoryId = vegetableCategoryId;
    }

    public void setVegetableName(String vegetableName) {
        this.vegetableName = vegetableName;
    }

    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }

    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public void setVegetableDescribe(String vegetableDescribe) {
        this.vegetableDescribe = vegetableDescribe;
    }

    public void setSurplusNum(int surplusNum) {
        this.surplusNum = surplusNum;
    }

    public void setSaleTime(Date saleTime) {
        this.saleTime = saleTime;
    }

    @Column(name="description",length = 100)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Vegetable{" +
                "vegetableCategoryId=" + vegetableCategoryId +
                ", vegetableName='" + vegetableName + '\'' +
                ", unitPrice=" + unitPrice +
                ", unitName='" + unitName + '\'' +
                ", img='" + img + '\'' +
                ", vegetableDescribe='" + vegetableDescribe + '\'' +
                ", surplusNum=" + surplusNum +
                ", saleTime=" + saleTime +
                ", canteenId=" + canteenId +
//                ", exist=" + exist +
                '}';
    }
}
