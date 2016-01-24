package com.horadrim.talrasha.common.service.impl;

import com.horadrim.talrasha.common.dao.CollectionDao;
import com.horadrim.talrasha.common.dao.GenericDao;
import com.horadrim.talrasha.common.dao.OrderItemDao;
import com.horadrim.talrasha.common.model.Collection;
import com.horadrim.talrasha.common.model.OrderItem;
import com.horadrim.talrasha.common.service.CollectionService;
import com.horadrim.talrasha.common.service.OrderItemService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by Administrator on 2015/6/1.
 */
@Service("collectionService")
public class CollectionServiceImpl extends GenericServiceImpl<Collection,Integer> implements CollectionService {
    @Resource(name="collectionDao")
    private CollectionDao collectionDao;
    @Override
    protected GenericDao<Collection, Integer> getGenericDao() {
        return collectionDao;
    }
}
