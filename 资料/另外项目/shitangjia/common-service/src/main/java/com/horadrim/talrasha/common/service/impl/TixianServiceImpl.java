package com.horadrim.talrasha.common.service.impl;

import com.horadrim.talrasha.common.dao.GenericDao;
import com.horadrim.talrasha.common.dao.TixianDao;
import com.horadrim.talrasha.common.model.Tixian;
import com.horadrim.talrasha.common.service.TixianService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by Administrator on 2015/7/17.
 */
@Service("tixianService")
public class TixianServiceImpl extends GenericServiceImpl<Tixian,Integer> implements TixianService{
    @Resource
    private TixianDao tixianDao;
    @Override
    protected GenericDao<Tixian, Integer> getGenericDao() {
        return tixianDao;
    }
}
