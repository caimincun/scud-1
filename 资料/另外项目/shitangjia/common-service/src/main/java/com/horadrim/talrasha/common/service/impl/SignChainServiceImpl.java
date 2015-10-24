package com.horadrim.talrasha.common.service.impl;


import com.horadrim.talrasha.common.dao.GenericDao;
import com.horadrim.talrasha.common.dao.SignChainDao;
import com.horadrim.talrasha.common.model.SignChain;
import com.horadrim.talrasha.common.service.SignChainService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("signChainService")
public class SignChainServiceImpl extends GenericServiceImpl<SignChain, Integer> implements SignChainService {
    private static final Logger logger = LoggerFactory.getLogger(SignChainServiceImpl.class);

    @Resource(name="signChainDao")
    protected SignChainDao signChainDao;

    @Override
    protected GenericDao<SignChain, Integer> getGenericDao() {
        return signChainDao;
    }

    @Override
    public List<SignChain> getSignChainByCanteenId(Integer canteenId) {
        return signChainDao.list("from SignChain where canteenId=?",new Object[]{canteenId});
    }
}
