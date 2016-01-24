package com.horadrim.talrasha.common.service.impl;

import com.horadrim.talrasha.common.dao.ApplicationDao;
import com.horadrim.talrasha.common.dao.GenericDao;
import com.horadrim.talrasha.common.model.AppLication;
import com.horadrim.talrasha.common.service.ApplicationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("applicationService")
public class ApplicationServiceImpl extends GenericServiceImpl<AppLication, Integer> implements ApplicationService {
    private static final Logger logger = LoggerFactory.getLogger(ApplicationServiceImpl.class);

    @Resource(name="applicationDao")
    protected ApplicationDao applicationDao;

    @Override
    protected GenericDao<AppLication, Integer> getGenericDao() {
        return applicationDao;
    }

}
