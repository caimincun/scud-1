package com.horadrim.talrasha.admin.service;

import com.horadrim.talrasha.admin.model.DeptAndAuthority;
import com.horadrim.talrasha.common.service.GenericService;

import java.util.List;

/**
 * Created by Administrator on 2015/7/23.
 */
public interface DeptAndAuthorityService extends GenericService<DeptAndAuthority,Integer> {

    List<DeptAndAuthority> getDeptAndAuthorityBydeptId(int deptId);

}
