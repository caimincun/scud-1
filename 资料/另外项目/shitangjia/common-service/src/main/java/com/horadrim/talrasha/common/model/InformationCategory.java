package com.horadrim.talrasha.common.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by Administrator on 2015/7/27.
 */
@Entity
@Table(name="qc_information_category")
public class InformationCategory extends AbstractDomain {
    private String description;  //分类描述
    private int status;        // 0 使用中，1已停用
    private int canteenId;
    private int moduleId;       // 关联的主模块

    @Column(name="description",length = 100,nullable = false)
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    @Column(name="status")
    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Column(name = "canteen_id")
    public int getCanteenId() {
        return canteenId;
    }

    public void setCanteenId(int canteenId) {
        this.canteenId = canteenId;
    }

    @Column(name="module_id",length = 4)
    public int getModuleId() {
        return moduleId;
    }

    public void setModuleId(int moduleId) {
        this.moduleId = moduleId;
    }
}
