package com.horadrim.talrasha.common.model;

import com.horadrim.talrasha.common.model.AbstractDomain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by Administrator on 2015/6/16.
 */
@Entity
@Table( name ="qc_other")
public class Other extends AbstractDomain{
    private String info;
    private  int userId;

    @Column(name="info",length = 1200)
    public String getInfo() {
        return info;
    }

    @Column(name="user_id",length = 11)
    public int getUserId() {
        return userId;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
