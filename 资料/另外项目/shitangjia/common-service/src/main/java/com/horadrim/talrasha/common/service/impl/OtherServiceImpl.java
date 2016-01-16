package com.horadrim.talrasha.common.service.impl;

import com.horadrim.talrasha.common.dao.GenericDao;
import com.horadrim.talrasha.common.dao.OtherDao;
import com.horadrim.talrasha.common.model.Other;
import com.horadrim.talrasha.common.service.OtherService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by Administrator on 2015/6/11.
 */
@Service("otherService")
public class OtherServiceImpl extends GenericServiceImpl<Other,Integer> implements OtherService {
    @Resource
    private OtherDao otherDao;
    @Override
    protected GenericDao<Other, Integer> getGenericDao() {
        return otherDao;
    }

    @Override
    public void addOther(Other other) {
        otherDao.save(other);
    }
}
