package com.horadrim.talrasha.admin.service;

import com.horadrim.talrasha.admin.model.Awards;
import com.horadrim.talrasha.common.service.GenericService;

/**
 * Created by Administrator on 2015/6/16.
 */
public interface AwardsService extends GenericService<Awards,Integer>  {

    Long countNotUse();

    Awards randAward();

}
