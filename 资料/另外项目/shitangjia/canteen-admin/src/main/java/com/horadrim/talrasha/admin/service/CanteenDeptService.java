package com.horadrim.talrasha.admin.service;

import com.horadrim.talrasha.admin.model.CanteenDept;
import com.horadrim.talrasha.common.service.GenericService;

import java.util.List;

/**
 * Created by Administrator on 2015/7/22.
 */
public interface CanteenDeptService extends GenericService<CanteenDept,Integer> {

    List<CanteenDept> listDept(int canteenId);

    void saveDetpAndAuthority(int deptid,int[] authority);
    void deptDeptAndAuthority(int deptid);
}
