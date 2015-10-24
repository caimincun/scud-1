package com.horadrim.talrasha.common.service.impl;

import com.horadrim.talrasha.common.dao.CanteenModuleDao;
import com.horadrim.talrasha.common.dao.GenericDao;
import com.horadrim.talrasha.common.model.Canteen_Module;
import com.horadrim.talrasha.common.service.CanteenModuleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Administrator on 2015/6/30.
 */
@Service("canteenModuleService")
public class CanteenModuleServiceImpl extends GenericServiceImpl<Canteen_Module,Integer> implements CanteenModuleService {
    @Resource
    private CanteenModuleDao canteenModuleDao;
    @Override
    protected GenericDao<Canteen_Module, Integer> getGenericDao() {
        return canteenModuleDao;
    }

    @Override
    public List<Canteen_Module> getByCanteenId(int canteenId) {
        final String hql = "FROM Canteen_Module WHERE canteenId=?";
        return list(hql,new Object[]{canteenId});
    }

    @Override
    public void delCanModuleByCanteenId(int canteenId) {
        String sql = "delete from Canteen_Module where canteenId = ?";
        Object[] params = {canteenId};
        canteenModuleDao.executeUpdate(sql,params);
        }

    @Override
    public void saveCanModule(String[] moduleCode, int canteenId) {
        Canteen_Module module;
        delCanModuleByCanteenId(canteenId);
        for(int i = 0;i<moduleCode.length;i++){
            module= new Canteen_Module();
                module.setCanteenId(canteenId);
                module.setModuleCode(moduleCode[i]);
                canteenModuleDao.save(module);
        }
    }
}
