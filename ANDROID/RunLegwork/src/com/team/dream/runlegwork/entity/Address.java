package com.team.dream.runlegwork.entity;

import java.io.Serializable;

public class Address implements Serializable{
	private int id;
    // 想关联的用户 token
    private String userToken;
    //收货地址
    private String receiptAddress;
    // 收货人姓名
    private String receiptName;
    // 收货人联系方式
    private String receiptPhone;
    
    private int flag;
    
	public int getFlag() {
		return flag;
	}
	public void setFlag(int flag) {
		this.flag = flag;
	}

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

    public String getReceiptAddress() {
        return receiptAddress;
    }

    public void setReceiptAddress(String receiptAddress) {
        this.receiptAddress = receiptAddress;
    }

    public String getReceiptName() {
        return receiptName;
    }

    public void setReceiptName(String receiptName) {
        this.receiptName = receiptName;
    }

    public String getReceiptPhone() {
        return receiptPhone;
    }

    public void setReceiptPhone(String receiptPhone) {
        this.receiptPhone = receiptPhone;
    }
    
    public Address(String receiptAddress, String receiptName,
			String receiptPhone) {
		super();
		this.receiptAddress = receiptAddress;
		this.receiptName = receiptName;
		this.receiptPhone = receiptPhone;
	}

	@Override
    public String toString() {
        return "Receipt{" +
                "id=" + id +
                ", userToken='" + userToken + '\'' +
                ", receiptAddress='" + receiptAddress + '\'' +
                ", receiptName='" + receiptName + '\'' +
                ", receiptPhone='" + receiptPhone + '\'' +
                '}';
    }
	
}
