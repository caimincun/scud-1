package com.team.dream.runlegwork.entity;

import java.io.Serializable;

/**
 * Created by Administrator on 2015/9/20. 商品分类
 */
public class Producttype implements Serializable {

	private int id;
	// 分类对应的商铺 StireToken
	private String storeToken;

	// 分类的token
	private String typeToken;

	// 分类的名称
	private String typeName;

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

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public String getTypeToken() {
		return typeToken;
	}

	public void setTypeToken(String typeToken) {
		this.typeToken = typeToken;
	}
}
