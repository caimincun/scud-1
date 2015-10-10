package com.horadrim.talrasha.common.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by Administrator on 2015/6/15.
 * 保存微信充值是的充值日志，记录保存时间，充值金额，是否真正充值等....
 */
@Table(name = "qc_wechat_recharge_log")
@Entity
public class WechatRechargeLog extends AbstractDomain {

    private String orderNum;              //本地保存的订单号
    private String transactionId;           //微信保存的订单号
    private String openId;                 //微信下用户标识
    private int userId;                     //平台下用户标识
    private BigDecimal totalFee;                //充值金额
    private BigDecimal realFee;                 //用户实际金额  ex 充值金额98元 实际金额100元
    private Date saveTime;                  //保存时间
    private int isConfirm;                  //默认为0 0：表示用户选择了充值

    @Column(name = "userid",nullable = false)
    public int getUserId() {
        return userId;
    }
    @Column(name = "ordernum",nullable = false,length = 50)
    public String getOrderNum() {
        return orderNum;
    }
    @Column(name = "transactionid",length = 50)
    public String getTransactionId() {
        return transactionId;
    }
    @Column(name = "openid",nullable = false,length = 30)
    public String getOpenId() {
        return openId;
    }
    @Column(name = "totalfee",nullable = false,length = 8)
    public BigDecimal getTotalFee() {
        return totalFee;
    }
    @Column(name = "savetime",nullable = false)
    public Date getSaveTime() {
        return saveTime;
    }
    @Column(name = "isconfirm",nullable = false,length = 1)
    public int getIsConfirm() {
        return isConfirm;
    }
    @Column(name = "realfee",nullable = false)
    public BigDecimal getRealFee() {
        return realFee;
    }

    public void setRealFee(BigDecimal realFee) {
        this.realFee = realFee;
    }

    public void setOrderNum(String orderNum) {
        this.orderNum = orderNum;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public void setTotalFee(BigDecimal totalFee) {
        this.totalFee = totalFee;
    }

    public void setSaveTime(Date saveTime) {
        this.saveTime = saveTime;
    }

    public void setIsConfirm(int isConfirm) {
        this.isConfirm = isConfirm;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
