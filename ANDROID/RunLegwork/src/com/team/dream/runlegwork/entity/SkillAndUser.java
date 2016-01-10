package com.team.dream.runlegwork.entity;

import java.io.Serializable;

public class SkillAndUser extends Skill implements Serializable{
	 //用户头像
    private String userPicture;
    //用户距离
    private Integer distance;
    //用户姓名
    private String userName;
    //年龄
    private int age;
    //性别
    private int userInfoSex;
    
    private String userToken;
    
    private String phoneNumber;
    
	/**
	 * @return the userPicture
	 */
	public String getUserPicture() {
		return userPicture;
	}
	/**
	 * @param userPicture the userPicture to set
	 */
	public void setUserPicture(String userPicture) {
		this.userPicture = userPicture;
	}
	/**
	 * @return the distance
	 */
	public String getDistance() {
		if(distance>1000){
			return ((float)distance)/1000+"km";
		}
		else{
			return distance+"m";
		}
	}
	/**
	 * @param distance the distance to set
	 */
	public void setDistance(Integer distance) {
		this.distance = distance;
	}
	/**
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}
	/**
	 * @param userName the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}
	/**
	 * @return the age
	 */
	public int getAge() {
		return age;
	}
	/**
	 * @param age the age to set
	 */
	public void setAge(int age) {
		this.age = age;
	}
	/**
	 * @return the userInfoSex
	 */
	public int getUserInfoSex() {
		return userInfoSex;
	}
	/**
	 * @param userInfoSex the userInfoSex to set
	 */
	public void setUserInfoSex(int userInfoSex) {
		this.userInfoSex = userInfoSex;
	}
	/**
	 * @return the userToken
	 */
	public String getUserToken() {
		return userToken;
	}
	/**
	 * @param userToken the userToken to set
	 */
	public void setUserToken(String userToken) {
		this.userToken = userToken;
	}
	/**
	 * @return the phoneNumber
	 */
	public String getPhoneNumber() {
		return phoneNumber;
	}
	/**
	 * @param phoneNumber the phoneNumber to set
	 */
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
	
}
