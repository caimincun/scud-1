package com.horadrim.talrasha.common.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by Administrator on 2015/6/1.
 */
@Entity
@Table(name = "qc_canteen")
public class Canteen extends AbstractDomain {

    private String canteenName;

    private String canteenLeader;
    @Column(name = "canteen_name",nullable = false,length = 50)
    public String getCanteenName() {
        return canteenName;
    }
    @Column(name = "canteen_leader",nullable = false,length = 20)
    public String getCanteenLeader() {
        return canteenLeader;
    }

    public void setCanteenName(String canteenName) {
        this.canteenName = canteenName;
    }

    public void setCanteenLeader(String canteenLeader) {
        this.canteenLeader = canteenLeader;
    }
}
