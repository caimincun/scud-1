package com.horadrim.talrasha.common.service.impl;


import com.horadrim.talrasha.common.dao.GenericDao;
import com.horadrim.talrasha.common.dao.SignChainNodeDao;
import com.horadrim.talrasha.common.model.SignChainNode;
import com.horadrim.talrasha.common.service.SignChainNodeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;

@Service("signChainNodeService")
public class SignChainNodeServiceImpl extends GenericServiceImpl<SignChainNode, Integer> implements SignChainNodeService {
    private static final Logger logger = LoggerFactory.getLogger(SignChainNodeServiceImpl.class);

    @Resource(name="signChainNodeDao")
    protected SignChainNodeDao signChainNodeDao;

    @Override
    protected GenericDao<SignChainNode, Integer> getGenericDao() {
        return signChainNodeDao;
    }

}
