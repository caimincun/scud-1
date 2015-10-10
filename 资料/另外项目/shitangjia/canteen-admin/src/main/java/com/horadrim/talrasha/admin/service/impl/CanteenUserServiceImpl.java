package com.horadrim.talrasha.admin.service.impl;

import com.horadrim.talrasha.admin.dao.CanteenAuthorityDao;
import com.horadrim.talrasha.admin.dao.CanteenUserDao;
import com.horadrim.talrasha.admin.model.CanteenAuthority;
import com.horadrim.talrasha.admin.model.CanteenUser;
import com.horadrim.talrasha.admin.service.CanteenUserService;
import com.horadrim.talrasha.common.dao.GenericDao;
import com.horadrim.talrasha.common.service.impl.GenericServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Administrator on 2015/6/10.
 */

@Service("canteenUserService")
public class CanteenUserServiceImpl extends GenericServiceImpl<CanteenUser,Integer> implements CanteenUserService{

    @Resource
    private CanteenAuthorityDao canteenAuthorityDao;

    @Resource
    protected CanteenUserDao canteenUserDao;

    @Override
    protected GenericDao<CanteenUser, Integer> getGenericDao() {
        return canteenUserDao;
    }

    @Override
    public CanteenUser checkCanteenUser(CanteenUser canteenUser) {
        String hql = "from CanteenUser where name = ? and password = ?";
        Object[] params = {canteenUser.getName(),canteenUser.getPassword()};
        CanteenUser user = canteenUserDao.query(hql,params);
        return user;
    }

    @Override
    public List<CanteenAuthority> queryAuthByCantUser(CanteenUser canteenUser) {
        List<CanteenAuthority> canteenAuthorities;
        String sql = "select * from qc_canteenauthority where id in (select authorty_id from qc_deptandauthority where dept_id in (select dept_id from qc_canteenuser where name = ? and password = ?))";
        Object[] params = {canteenUser.getName(),canteenUser.getPassword()};
        canteenAuthorities = canteenAuthorityDao.listBySQL(sql,params);
        return canteenAuthorities;
    }
}
