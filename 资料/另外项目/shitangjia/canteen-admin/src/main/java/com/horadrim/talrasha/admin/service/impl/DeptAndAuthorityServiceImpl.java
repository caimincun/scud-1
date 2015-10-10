package com.horadrim.talrasha.admin.service.impl;

import com.horadrim.talrasha.admin.dao.DeptAndAuthorityDao;
import com.horadrim.talrasha.admin.model.DeptAndAuthority;
import com.horadrim.talrasha.admin.service.DeptAndAuthorityService;
import com.horadrim.talrasha.common.dao.GenericDao;
import com.horadrim.talrasha.common.service.impl.GenericServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Administrator on 2015/7/23.
 */
@Service("deptAndAuthorityService")
public class DeptAndAuthorityServiceImpl extends GenericServiceImpl<DeptAndAuthority,Integer> implements DeptAndAuthorityService {

    @Resource
    private DeptAndAuthorityDao deptAndAuthorityDao;

    @Override
    protected GenericDao<DeptAndAuthority, Integer> getGenericDao() {
        return deptAndAuthorityDao;
    }

    @Override
    public List<DeptAndAuthority> getDeptAndAuthorityBydeptId(int deptId) {
        String hql = "from DeptAndAuthority where detpId = ?";
        Object[] param = {deptId};
        System.out.println(deptAndAuthorityDao.list(hql,param));
        return deptAndAuthorityDao.list(hql,param);
    }
}
