package cn.scud.main.user.model;

import java.io.Serializable;

/**
 * Created by Administrator on 2015/6/12.
 * 用户的基本信息表
 */
public class UserInfo implements Serializable,Comparable{

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
    private int userInfoSex;  // 1 ：男  0：女
    // 用户个性签名
    private String userInfoSignature;
    //职业
    private String userInfoJob;
    //图片
    private String userInfoPicture;
    //简介
    private String userInfoIntroduction;
    //年龄
    private int age;
    //精度
    private double longitude;
    //维度
    private double latitude;
    // lbs id
    private int lbsId;



    // VIEW 视图层 展示需要

    // 距离
    private Integer distance;
    //技能名称
    private String skillTitle;
    // 技能单价
    //价格
    private double skillMoney;
    // 单位
    private String skillUnit;


    public String getSkillTitle() {
        return skillTitle;
    }

    public void setSkillTitle(String skillTitle) {
        this.skillTitle = skillTitle;
    }

    public Integer getDistance() {
        return distance;
    }

    public void setDistance(Integer distance) {
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

    public String getUserInfoPicture() {
        return userInfoPicture;
    }

    public void setUserInfoPicture(String userInfoPicture) {
        this.userInfoPicture = userInfoPicture;
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

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setUserInfoIntroduction(String userInfoIntroduction) {
        this.userInfoIntroduction = userInfoIntroduction;
    }

    public double getSkillMoney() {
        return skillMoney;
    }

    public void setSkillMoney(double skillMoney) {
        this.skillMoney = skillMoney;
    }

    public String getSkillUnit() {
        return skillUnit;
    }

    public void setSkillUnit(String skillUnit) {
        this.skillUnit = skillUnit;
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
                ", userInfoPicture='" + userInfoPicture + '\'' +
                ", userInfoIntroduction='" + userInfoIntroduction + '\'' +
                ", age=" + age +
                ", longitude=" + longitude +
                ", latitude=" + latitude +
                ", lbsId=" + lbsId +
                ", distance=" + distance +
                ", skillTitle='" + skillTitle + '\'' +
                ", skillMoney=" + skillMoney +
                ", skillUnit='" + skillUnit + '\'' +
                '}';
    }

    @Override
    public int compareTo(Object o) {
        return this.distance.compareTo(((UserInfo) o).getDistance());
    }
}
