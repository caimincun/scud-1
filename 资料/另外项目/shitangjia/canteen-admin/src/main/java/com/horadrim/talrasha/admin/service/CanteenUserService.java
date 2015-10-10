package com.horadrim.talrasha.admin.service;

import com.horadrim.talrasha.admin.model.CanteenAuthority;
import com.horadrim.talrasha.admin.model.CanteenUser;
import com.horadrim.talrasha.common.service.GenericService;

import java.util.List;

/**
 * Created by Administrator on 2015/6/10.
 */
public interface CanteenUserService extends GenericService<CanteenUser,Integer> {

    /**
     * 查询用户对象是否存在
     * @param canteenUser
     * @return
     */
    CanteenUser checkCanteenUser(CanteenUser canteenUser);

    /**
     * 根据用户信息，查询用户的权限
     * @param canteenUser
     * @return
     */
    List<CanteenAuthority> queryAuthByCantUser(CanteenUser canteenUser);
}
