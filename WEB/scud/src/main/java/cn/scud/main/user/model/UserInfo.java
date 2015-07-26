package cn.scud.main.user.model;

import java.io.Serializable;

/**
 * Created by Administrator on 2015/6/12.
 * 用户的基本信息表
 */
public class UserInfo implements Serializable {

    private int userInfoId;

    private String userToken;

//    private String userName;
    // 真是姓名
    private String userRealName;
    // 身份证号码
    private String userIdCardNum;
    //邮箱
    private String userInfoEmail;
    // 性别
    private int userInfoSex;
    // 用户个性签名
    private String userInfoSignature;
    //职业
    private String userInfoJob;
    //图片
    private String pictures;
    //简介
    private String userInfoIntroduction;
    //精度
    private double longitude;
    //维度
    private double latitude;
    // lbs id
    private int lbsId;
    //距离
    private int distance;

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public int getLbsId() {
        return lbsId;
    }

    public void setLbsId(int lbsId) {
        this.lbsId = lbsId;
    }

    public int getUserInfoId() {
        return userInfoId;
    }

    public void setUserInfoId(int userInfoId) {
        this.userInfoId = userInfoId;
    }

    public String getUserToken() {
        return userToken;
    }

    public void setUserToken(String userToken) {
        this.userToken = userToken;
    }

    public String getUserRealName() {
        return userRealName;
    }

    public void setUserRealName(String userRealName) {
        this.userRealName = userRealName;
    }

    public String getUserIdCardNum() {
        return userIdCardNum;
    }

    public void setUserIdCardNum(String userIdCardNum) {
        this.userIdCardNum = userIdCardNum;
    }

    public String getUserInfoEmail() {
        return userInfoEmail;
    }

    public void setUserInfoEmail(String userInfoEmail) {
        this.userInfoEmail = userInfoEmail;
    }

    public int getUserInfoSex() {
        return userInfoSex;
    }

    public void setUserInfoSex(int userInfoSex) {
        this.userInfoSex = userInfoSex;
    }

    public String getUserInfoSignature() {
        return userInfoSignature;
    }

    public void setUserInfoSignature(String userInfoSignature) {
        this.userInfoSignature = userInfoSignature;
    }

    public String getUserInfoJob() {
        return userInfoJob;
    }

    public void setUserInfoJob(String userInfoJob) {
        this.userInfoJob = userInfoJob;
    }

    public String getPictures() {
        return pictures;
    }

    public void setPictures(String pictures) {
        this.pictures = pictures;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public String getUserInfoIntroduction() {
        return userInfoIntroduction;
    }

    public void setUserInfoIntroduction(String userInfoIntroduction) {
        this.userInfoIntroduction = userInfoIntroduction;
    }

    @Override
    public String toString() {
        return "UserInfo{" +
                "userInfoId=" + userInfoId +
                ", userToken='" + userToken + '\'' +
                ", userRealName='" + userRealName + '\'' +
                ", userIdCardNum='" + userIdCardNum + '\'' +
                ", userInfoEmail='" + userInfoEmail + '\'' +
                ", userInfoSex=" + userInfoSex +
                ", userInfoSignature='" + userInfoSignature + '\'' +
                ", userInfoJob='" + userInfoJob + '\'' +
                ", pictures='" + pictures + '\'' +
                ", userInfoIntroduction='" + userInfoIntroduction + '\'' +
                ", longitude=" + longitude +
                ", latitude=" + latitude +
                ", lbsId='" + lbsId + '\'' +
                ", distance=" + distance +
                '}';
    }
}
