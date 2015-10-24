package com.horadrim.talrasha.common.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by Administrator on 2015/6/4.
 * 用户体现实体
 */
@Entity
@Table(name = "qc_tixian")
public class Tixian extends AbstractDomain{

    public enum AccountType{
        /**
         * 微信账号
         */
        WEIXIN,
        /**
         * 支付宝账号
         */
        ZHIFUBAO;

    }

    private int userId;            //用户id
    @DecimalMax(value = "2000")
    @DecimalMin(value = "0.01")
    private BigDecimal balance;    //提现金额
    private Date tixianTime;       //申请提现时间
    @NotNull
    private String realName;       //用户真实姓名
    @Min(value = 0)
    private int accountType;//提现账户类型
    private int isDeal;             //是否处理 默认为0未处理 1已处理
    private String phone;           //用户联系方式
    @Column(name = "user_id")
    public int getUserId() {
        return userId;
    }
    @Column(name = "balance")
    public BigDecimal getBalance() {
        return balance;
    }
    @Column(name = "tixian_time")
    public Date getTixianTime() {
        return tixianTime;
    }
    @Column(name = "realname")
    public String getRealName() {
        return realName;
    }
    @Column(name = "account_type")
    public int getAccountType() {
        return accountType;
    }
    @Column(name = "is_deal")
    public int getIsDeal() {
        return isDeal;
    }
    @Column
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setIsDeal(int isDeal) {
        this.isDeal = isDeal;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public void setTixianTime(Date tixianTime) {
        this.tixianTime = tixianTime;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public void setAccountType(int accountType) {
        this.accountType = accountType;
    }
}
