package com.horadrim.talrasha.common.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.math.BigDecimal;


@Entity
//@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Table(name = "qc_user")
public class User extends AbstractDomain {

    private String nickName;
    private String phone;
    private String openId;
    private int canteenId;
    private BigDecimal qingcaiBalance;
    private BigDecimal canteenBalance;
    private String payPassword;
    private int isWithoutPwd;
    private int sex;                            //用户性别 1男性 2女性 0未知
    private String email;
    private String qq;
    private String department;
    private String headImg;
    @Column(name = "head_img")
    public String getHeadImg() {
        return headImg;
    }
    @Column(name = "department", length = 55, nullable = true)
    public String getDepartment() {
        return department;
    }

    @Column(name = "qq", length = 20, nullable = true)
    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }

    @Column(name = "email", length = 22, nullable = true)
    public String getEmail() {
        return email;
    }

    @Column(name = "sex", length = 1, nullable = true)
    public int getSex() {
        return sex;
    }
    @Column(name = "safe_amount", nullable = false)
    public float getSafeAmount() {
        return safeAmount;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    @Column(name = "nickname", length = 50, nullable = false)
    public String getNickName() {
        return nickName;
    }

    @Column(name = "phone", length = 11, nullable = false)
    public String getPhone() {
        return phone;
    }

    @Column(name = "openid", length = 50, nullable = false)
    public String getOpenId() {
        return openId;
    }

    @Column(name = "canteen_id", length = 11, nullable = false)
    public int getCanteenId() {
        return canteenId;
    }

    @Column(name = "qingcai_balance")
    public BigDecimal getQingcaiBalance() {
        return qingcaiBalance;
    }
    @Column(name = "canteen_balance")
    public BigDecimal getCanteenBalance() {
        return canteenBalance;
    }

    @Column(name = "pay_password",length = 100)
    public String getPayPassword() {
        return payPassword;
    }

    @Column(name = "is_without_pwd")
    public int getIsWithoutPwd() {
        return isWithoutPwd;
    }

    public void setHeadImg(String headImg) {
        this.headImg = headImg;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }


    private float safeAmount=50L;  //用户设置的安全金额，超过该金额则需要支付密码 ， 默认50

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public void setCanteenId(int canteenId) {
        this.canteenId = canteenId;
    }

    public void setCanteenBalance(BigDecimal canteenBalance) {
        this.canteenBalance = canteenBalance;
    }

    public void setQingcaiBalance(BigDecimal qingcaiBalance) {

        this.qingcaiBalance = qingcaiBalance;
    }

    public void setPayPassword(String payPassword) {
        this.payPassword = payPassword;
    }

    public void setIsWithoutPwd(int isWithoutPwd) {
        this.isWithoutPwd = isWithoutPwd;
    }

    public void setSafeAmount(float safeAmount) {
        this.safeAmount = safeAmount;
    }



}
