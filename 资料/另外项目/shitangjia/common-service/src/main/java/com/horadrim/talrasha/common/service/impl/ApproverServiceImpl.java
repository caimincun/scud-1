package com.horadrim.talrasha.common.service.impl;


import com.horadrim.talrasha.common.dao.ApproverDao;
import com.horadrim.talrasha.common.dao.GenericDao;
import com.horadrim.talrasha.common.model.Approver;
import com.horadrim.talrasha.common.service.ApproverService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("approverService")
public class ApproverServiceImpl extends GenericServiceImpl<Approver, Integer> implements ApproverService {
    private static final Logger logger = LoggerFactory.getLogger(ApproverServiceImpl.class);

    @Resource(name="approverDao")
    protected ApproverDao approverDao;

    @Override
    protected GenericDao<Approver, Integer> getGenericDao() {
        return approverDao;
    }

}
