package com.team.dream.runlegwork_data.entity;

public class User {
	private int id;
	private String lastLoginDate;
	private String lastLoginIp;
	private String password;
	private String phoneNumber;
	private String regChannel;
	private String regDate;
	private String userToken;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getLastLoginDate() {
		return lastLoginDate;
	}

	public void setLastLoginDate(String lastLoginDate) {
		this.lastLoginDate = lastLoginDate;
	}

	public String getLastLoginIp() {
		return lastLoginIp;
	}

	public void setLastLoginIp(String lastLoginIp) {
		this.lastLoginIp = lastLoginIp;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getRegChannel() {
		return regChannel;
	}

	public void setRegChannel(String regChannel) {
		this.regChannel = regChannel;
	}

	public String getRegDate() {
		return regDate;
	}

	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}

	public String getUserToken() {
		return userToken;
	}

	public void setUserToken(String userToken) {
		this.userToken = userToken;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", lastLoginDate=" + lastLoginDate + ", lastLoginIp=" + lastLoginIp + ", password=" + password + ", phoneNumber=" + phoneNumber + ", regChannel=" + regChannel
				+ ", regDate=" + regDate + ", userToken=" + userToken + "]";
	}
	
	

}
