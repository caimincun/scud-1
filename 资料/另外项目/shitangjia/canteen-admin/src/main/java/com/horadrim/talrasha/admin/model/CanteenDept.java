package com.horadrim.talrasha.admin.model;

import com.horadrim.talrasha.common.model.AbstractDomain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by Administrator on 2015/6/10.
 */
@Entity
@Table(name="qc_canteendept")
public class CanteenDept extends AbstractDomain {

    private String deptName;

    private int canteenId;

    @Column(name="canteen_id",nullable = false,length = 5)
    public int getCanteenId() {
        return canteenId;
    }

    public void setCanteenId(int canteenId) {
        this.canteenId = canteenId;
    }

    @Column(name = "dept_name",nullable = false,length = 50)
    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    @Override
    public String toString() {
        return "CanteenDept{" +
                "deptName='" + deptName + '\'' +
                ", canteenId=" + canteenId +
                '}';
    }
}
