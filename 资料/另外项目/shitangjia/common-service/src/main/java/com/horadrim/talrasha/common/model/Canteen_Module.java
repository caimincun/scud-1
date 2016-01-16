package com.horadrim.talrasha.common.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by Administrator on 2015/6/30.
 * 食堂和模块的中间表
 */

@Entity
@Table(name = "qc_canteen_module")
public class Canteen_Module extends AbstractDomain{

    private int canteenId;
    private String moduleCode;
    @Column(name = "canteen_id",nullable = false)
    public int getCanteenId() {
        return canteenId;
    }
    @Column(name = "module_code",nullable = false,length = 20)
    public String getModuleCode() {
        return moduleCode;
    }

    public void setCanteenId(int canteenId) {
        this.canteenId = canteenId;
    }

    public void setModuleCode(String moduleCode) {
        this.moduleCode = moduleCode;
    }
}
