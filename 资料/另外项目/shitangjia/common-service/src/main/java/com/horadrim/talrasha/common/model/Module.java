package com.horadrim.talrasha.common.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by Administrator on 2015/6/30.
 * 模块表
 */
@Table(name = "qc_module")
@Entity
public class Module extends AbstractDomain implements Comparable<Module> {

    private String moduleName;  //模块名称
    private String moduleCode;  //模块代码 用于唯一标识模块，前台通过模块代码判断界面显示情况 ex：m_diancan
    private String describe;   //模块描述

    @Column(name = "module_code",nullable = false,length = 20)
    public String getModuleCode() {
        return moduleCode;
    }

    @Column(name = "module_describe")
    public String getDescribe() {
        return describe;
    }

    @Column(name = "module_name",nullable = false,length = 20)
    public String getModuleName() {
        return moduleName;
    }

    public void setModuleCode(String moduleCode) {
        this.moduleCode = moduleCode;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
    }

    @Override
    public String toString() {
        return "Module{" +
                "moduleName='" + moduleName + '\'' +
                ", moduleCode='" + moduleCode + '\'' +
                ", describe='" + describe + '\'' +
                '}';
    }

    @Override
    public int compareTo(Module o) {
        if(this.getId()<o.getId())return -1;
        else if(this.getId()==o.getId())return 0;
        else return 1;
    }
}
