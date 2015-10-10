package com.horadrim.talrasha.common.service.impl;


import com.horadrim.talrasha.common.dao.CanteenDao;
import com.horadrim.talrasha.common.dao.GenericDao;
import com.horadrim.talrasha.common.model.Canteen;
import com.horadrim.talrasha.common.service.CanteenService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by Administrator on 2015/6/1.
 */
@Service("canteenService")
public class CanteenServiceImpl extends GenericServiceImpl<Canteen,Integer> implements CanteenService {
    @Resource(name="canteenDao")
    private CanteenDao canteenDao;
    @Override
    protected GenericDao<Canteen, Integer> getGenericDao() {
        return canteenDao;
    }
}
