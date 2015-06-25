package cn.scud.main.user.model;

import java.io.Serializable;

/**
 * Created by cmc on 14-12-9.
 * 用户信息表
 */
public class User implements Serializable {

    private int id;

    private String phoneNumber;

    private String password;
    // 唯一标识
    private String userToken;
    // 注册渠道，android或者ios
    private String regChannel;
    //注册时间
    private String regDate;
    // 最后一次登录时间
    private String lastLoginDate;
    // 最后一次登录Ip
    private String lastLoginIp;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserToken() {
        return userToken;
    }

    public void setUserToken(String userToken) {
        this.userToken = userToken;
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

    @java.lang.Override
    public java.lang.String toString() {
        return "User{" +
                "id=" + id +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", password='" + password + '\'' +
                ", userToken='" + userToken + '\'' +
                ", regChannel='" + regChannel + '\'' +
                ", regDate='" + regDate + '\'' +
                ", lastLoginDate='" + lastLoginDate + '\'' +
                ", lastLoginIp='" + lastLoginIp + '\'' +
                '}';
    }
}
