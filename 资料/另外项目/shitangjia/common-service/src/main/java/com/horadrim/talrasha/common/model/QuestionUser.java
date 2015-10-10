package com.horadrim.talrasha.common.model;

import com.horadrim.talrasha.common.model.AbstractDomain;
import org.hibernate.annotations.*;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Set;

/**
 * Created by Administrator on 2015/6/10.
 */
@Entity
@Table(name = "qc_questionuser")
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class QuestionUser extends AbstractDomain {

    private int age;

    private int sex;

    private String phoneNumber;

    private String ipAddr;

    private int isCanjia;

    private int isChoujiang;

    private int award;

    private int isConfirm; //前台用户是否确认奖品  0 后台保存奖品 1 前台确认 2 已经领取
    @Column(name = "ischoujiang")
    public int getIsChoujiang() {
        return isChoujiang;
    }

    public void setIsChoujiang(int isChoujiang) {
        this.isChoujiang = isChoujiang;
    }

    @Column(name = "isconfirm",nullable = false,length = 1)
    public int getIsConfirm() {
        return isConfirm;
    }

    public void setIsConfirm(int isConfirm) {
        this.isConfirm = isConfirm;
    }

    @Column(name = "phonenumber",length = 20)
    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    @Column(name = "award",nullable = false,length = 1)
    public int getAward() {
        return award;
    }

    public void setAward(int award) {
        this.award = award;
    }

    @Column(name = "sex" ,nullable = false)
    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    @Column(name="age")
    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
    @Column(name = "ipaddr",nullable = false)
    public String getIpAddr() {
        return ipAddr;
    }

    public void setIpAddr(String ipAddr) {
        this.ipAddr = ipAddr;
    }
    @Column(name = "iscanjia",nullable = false,length = 1)
    public int getIsCanjia() {
        return isCanjia;
    }

    public void setIsCanjia(int isCanjia) {
        this.isCanjia = isCanjia;
    }

    @Override
    public String toString() {
        return "QuestionUser{" +
                "age=" + age +
                ", sex=" + sex +
                '}';
    }
}
