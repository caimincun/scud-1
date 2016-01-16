package com.horadrim.talrasha.admin.model;


import com.horadrim.talrasha.common.model.AbstractDomain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by Administrator on 2015/6/10.
 */
@Entity
@Table(name="qc_canteenuser")
public class CanteenUser extends AbstractDomain {

    private String name;
    private String password;
    private int detpId;
    private int canteenId;
    private String phoneNumber;

    @Column(name="phone_number",length = 50)
    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Column(name="canteen_id",nullable = false,length = 5)
    public int getCanteenId() {
        return canteenId;
    }

    public void setCanteenId(int canteenId) {
        this.canteenId = canteenId;
    }

    @Column(name = "name",nullable = false,length = 50)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "password",nullable = false,length = 50)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Column(name = "dept_id",nullable = false,length = 5)
    public int getDetpId() {
        return detpId;
    }

    public void setDetpId(int detpId) {
        this.detpId = detpId;
    }

    @Override
    public String toString() {
        return "CanteenUser{" +
                "name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", detpId=" + detpId +
                ", canteenId=" + canteenId +
                ", phoneNumber='" + phoneNumber + '\'' +
                '}';
    }
}
