package com.horadrim.talrasha.admin.service.impl;

import com.horadrim.talrasha.admin.dao.CanteenDeptDao;
import com.horadrim.talrasha.admin.dao.DeptAndAuthorityDao;
import com.horadrim.talrasha.admin.model.CanteenDept;
import com.horadrim.talrasha.admin.model.DeptAndAuthority;
import com.horadrim.talrasha.admin.service.CanteenDeptService;
import com.horadrim.talrasha.common.dao.GenericDao;
import com.horadrim.talrasha.common.service.impl.GenericServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Administrator on 2015/7/22.
 */
@Service("canteenDeptService")
public class CanteenDeptServiceImpl extends GenericServiceImpl<CanteenDept,Integer> implements CanteenDeptService {

    @Resource
    private CanteenDeptDao canteenDeptDao;

    @Resource
    private DeptAndAuthorityDao deptAndAuthorityDao;

    @Override
    protected GenericDao<CanteenDept, Integer> getGenericDao() {
        return canteenDeptDao;
    }

    @Override
    public List<CanteenDept> listDept(int canteenId) {
        String hql = "from CanteenDept where canteen_id = ?";
        Object[] params = {canteenId};
        return canteenDeptDao.list(hql,params);
    }

    @Override
    public void saveDetpAndAuthority(int deptid,int[] authority) {
        DeptAndAuthority deptAndAuthority = null;
        if(null != authority){
            for(int i =0;i<authority.length;i++){
                deptAndAuthority = new DeptAndAuthority();
                deptAndAuthority.setAuthortyId(authority[i]);
                deptAndAuthority.setDetpId(deptid);
                deptAndAuthorityDao.save(deptAndAuthority);
            }
        }
    }

    @Override
    public void deptDeptAndAuthority(int deptid) {
       String hql = "delete DeptAndAuthority where detpId = ?";
        Object[] params = {deptid};
        deptAndAuthorityDao.executeUpdate(hql,params);
    }
}
