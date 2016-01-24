package com.team.dream.runlegwork.entity;

import java.io.Serializable;

/**
 * Created by Administrator on 2015/9/15.
 */
public class Product implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
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
    
    private int count;//购买数量
    
    

    public Product() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Product(String storeToken, String productToken,
			String productPictures, String prductName, String productType,
			double productMoney, int surplusNum, String descritpion,
			int deleteFlag, int count) {
		super();
		this.storeToken = storeToken;
		this.productToken = productToken;
		this.productPictures = productPictures;
		this.productName = prductName;
		this.productType = productType;
		this.productMoney = productMoney;
		this.surplusNum = surplusNum;
		this.descritpion = descritpion;
		this.deleteFlag = deleteFlag;
		this.count = count;
	}

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

    public String getPrductName() {
        return productName;
    }

    public void setPrductName(String prductName) {
        this.productName = prductName;
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

	/**
	 * @return the count
	 */
	public int getCount() {
		return count;
	}

	/**
	 * @param count the count to set
	 */
	public void setCount(int count) {
		this.count = count;
	}
	
}
