package com.horadrim.talrasha.common.service.impl;


import com.horadrim.talrasha.common.dao.ApplicationSignChainDao;

import com.horadrim.talrasha.common.dao.GenericDao;
import com.horadrim.talrasha.common.model.AppLicationSignChain;
import com.horadrim.talrasha.common.service.ApplicationSignChainService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("applicationSignChainService")
public class ApplicationSignChainServiceImpl extends GenericServiceImpl<AppLicationSignChain, Integer> implements ApplicationSignChainService {
    private static final Logger logger = LoggerFactory.getLogger(ApplicationSignChainServiceImpl.class);

    @Resource(name="applicationSignChainDao")
    protected ApplicationSignChainDao applicationSignChainDao;

    @Override
    protected GenericDao<AppLicationSignChain, Integer> getGenericDao() {
        return applicationSignChainDao;
    }

}
