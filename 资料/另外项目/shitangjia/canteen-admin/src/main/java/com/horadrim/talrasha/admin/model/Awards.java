package com.horadrim.talrasha.admin.model;

import com.horadrim.talrasha.common.model.AbstractDomain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by Administrator on 2015/6/16.
 */
@Table(name = "qc_awards")
@Entity
public class Awards extends AbstractDomain {

    private int awardCode;
    private int award;
    private int isUse;
    @Column(name = "awardcode")
    public int getAwardCode() {
        return awardCode;
    }
    @Column(name = "award")
    public int getAward() {
        return award;
    }
    @Column(name = "isuse")
    public int getIsUse() {
        return isUse;
    }

    public void setAwardCode(int awardCode) {
        this.awardCode = awardCode;
    }

    public void setAward(int award) {
        this.award = award;
    }

    public void setIsUse(int isUse) {
        this.isUse = isUse;
    }
}
