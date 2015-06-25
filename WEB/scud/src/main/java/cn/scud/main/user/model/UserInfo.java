package cn.scud.main.user.model;

/**
 * Created by Administrator on 2015/6/12.
 * 用户的基本信息表
 */
public class UserInfo {

    private int userInfoId;

    private int userID;

    private String userName;
    // 真是姓名
    private String userRealName;
    // 身份证号码
    private String userIdCardNum;
    //邮箱
    private String userInfoEmail;
    // 性别
    private int userInfoSex;
    //用户标签
    private String userInfoLabel;
    // 用户个性签名
    private String userInfoSignature;

    public int getUserInfoId() {
        return userInfoId;
    }

    public void setUserInfoId(int userInfoId) {
        this.userInfoId = userInfoId;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
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

    public String getUserInfoLabel() {
        return userInfoLabel;
    }

    public void setUserInfoLabel(String userInfoLabel) {
        this.userInfoLabel = userInfoLabel;
    }

    public String getUserInfoSignature() {
        return userInfoSignature;
    }

    public void setUserInfoSignature(String userInfoSignature) {
        this.userInfoSignature = userInfoSignature;
    }

    @java.lang.Override
    public java.lang.String toString() {
        return "UserInfo{" +
                "userInfoId=" + userInfoId +
                ", userID=" + userID +
                ", userName='" + userName + '\'' +
                ", userRealName='" + userRealName + '\'' +
                ", userIdCardNum='" + userIdCardNum + '\'' +
                ", userInfoEmail='" + userInfoEmail + '\'' +
                ", userInfoSex=" + userInfoSex +
                ", userInfoLabel='" + userInfoLabel + '\'' +
                ", userInfoSignature='" + userInfoSignature + '\'' +
                '}';
    }
}
