package com.horadrim.talrasha.common.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by Administrator on 2015/6/4.
 * 客户消费记录
 */
@Entity
@Table(name = "qc_consume_history")
public class ConsumeHistory extends AbstractDomain{

    public enum ConsumeType{
        /**
         * 充值
         */
        CHONGZHI,
        /**
         * 撤销订单退款
         */
        CHEXIAODINGDAN,
        /**
         * 点餐扣费
         */
        DIANCAN,
        /**
         * 买菜扣费
         */
        MAICAI,
        /**
         *买菜时冻结金额
         */
        MAICAI_DONGJIE,
        /**
         * 买菜支付解冻
         */
        MAICAI_JIEDONG;
    }

    /**
     * 账户类型
     */
    public enum AccountType{
        /**
         * 食堂账户
         */
        SHITANG,
        /**
         * 平台账户
         */
        PINGTAI,
        /**
         * 线下转账
         */
        XIANXIA;
        public static AccountType getAccount(int index){
            for (AccountType type : AccountType.values()){
                if (index==type.ordinal())
                    return type;
            }
            return null;
        }
    }

    private int userId;               //用户id
    private int canteenId;              // 餐厅id
    private BigDecimal balance;       //消费金额
    private ConsumeType consumeType;  //消费类型
    private AccountType accountType; //账户类型
    private Date consumeTime;         //消费时间
    private int targetId;             //目标id
    private int c_year;               //增加年月日 便于统计
    private int c_month;
    private int c_day;

    @Column(name="canteen_id",nullable = false)
    public int getCanteenId() {
        return canteenId;
    }

    public void setCanteenId(int canteenId) {
        this.canteenId = canteenId;
    }

    @Column(name = "user_id",nullable = false)
    public int getUserId() {
        return userId;
    }
    @Column
    public BigDecimal getBalance() {
        return balance;
    }
    @Column(name = "consume_type")
    public ConsumeType getConsumeType() {
        return consumeType;
    }
    @Column(name = "consume_time")
    public Date getConsumeTime() {
        return consumeTime;
    }
    @Column(name = "target_id")
    public int getTargetId() {
        return targetId;
    }
    @Column(name = "c_year")
    public int getC_year() {
        return c_year;
    }
    @Column(name = "c_month")
    public int getC_month() {
        return c_month;
    }
    @Column(name = "c_day")
    public int getC_day() {
        return c_day;
    }
    @Column(name = "account_type")
    public AccountType getAccountType() {
        return accountType;
    }

    public void setAccountType(AccountType accountType) {
        this.accountType = accountType;
    }

    public void setC_year(int c_year) {
        this.c_year = c_year;
    }

    public void setC_month(int c_month) {
        this.c_month = c_month;
    }

    public void setC_day(int c_day) {
        this.c_day = c_day;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public void setConsumeType(ConsumeType consumeType) {
        this.consumeType = consumeType;
    }

    public void setConsumeTime(Date consumeTime) {
        this.consumeTime = consumeTime;
    }

    public void setTargetId(int targetId) {
        this.targetId = targetId;
    }
}
