package com.horadrim.talrasha.common.service.impl;

import com.horadrim.talrasha.common.dao.GenericDao;
import com.horadrim.talrasha.common.dao.ModuleDao;
import com.horadrim.talrasha.common.model.Module;
import com.horadrim.talrasha.common.service.ModuleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("moduleService")
public class ModuleServiceImpl extends GenericServiceImpl<Module,Integer> implements ModuleService{
    @Resource
    private ModuleDao moduleDao;
    @Override
    protected GenericDao<Module, Integer> getGenericDao() {
        return moduleDao;
    }

    @Override
    public Module save(Module entity) {
        final String hql = "FROM Module WHERE moduleCode=?";
        if (query(hql,new Object[]{entity.getModuleCode()})!=null)
            return null;
        return super.save(entity);
    }
}
