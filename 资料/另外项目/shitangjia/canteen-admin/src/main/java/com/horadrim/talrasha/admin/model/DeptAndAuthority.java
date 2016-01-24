package com.horadrim.talrasha.admin.model;

import com.horadrim.talrasha.common.model.AbstractDomain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by Administrator on 2015/6/10.
 */
@Entity
@Table(name="qc_deptandauthority")
public class DeptAndAuthority extends AbstractDomain {


    private int detpId;

    private int authortyId;

    @Column(name = "dept_id",nullable = false,length = 5)
    public int getDetpId() {
        return detpId;
    }

    public void setDetpId(int detpId) {
        this.detpId = detpId;
    }

    @Column(name = "authorty_id",nullable = false,length = 5)
    public int getAuthortyId() {
        return authortyId;
    }

    public void setAuthortyId(int authortyId) {
        this.authortyId = authortyId;
    }

    @Override
    public String toString() {
        return "DeptAndAuthority{" +
                "detpId=" + detpId +
                ", authortyId=" + authortyId +
                '}';
    }
}
