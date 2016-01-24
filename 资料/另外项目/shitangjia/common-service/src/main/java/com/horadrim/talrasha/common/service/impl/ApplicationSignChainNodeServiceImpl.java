package com.horadrim.talrasha.common.service.impl;


import com.horadrim.talrasha.common.dao.ApplicationSignChainNodeDao;
import com.horadrim.talrasha.common.dao.GenericDao;
import com.horadrim.talrasha.common.model.AppLicationSignChainNode;
import com.horadrim.talrasha.common.service.ApplicationSignChainNodeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("applicationSignChainNodeService")
public class ApplicationSignChainNodeServiceImpl extends GenericServiceImpl<AppLicationSignChainNode, Integer> implements ApplicationSignChainNodeService {
    private static final Logger logger = LoggerFactory.getLogger(ApplicationSignChainNodeServiceImpl.class);

    @Resource(name="applicationSignChainNodeDao")
    protected ApplicationSignChainNodeDao applicationSignChainNodeDao;

    @Override
    protected GenericDao<AppLicationSignChainNode, Integer> getGenericDao() {
        return applicationSignChainNodeDao;
    }

}
