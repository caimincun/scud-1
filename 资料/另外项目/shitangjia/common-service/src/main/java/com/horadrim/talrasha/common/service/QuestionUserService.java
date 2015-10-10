package com.horadrim.talrasha.common.service;

import com.horadrim.talrasha.common.model.QuestionUser;
import com.horadrim.talrasha.common.service.GenericService;

/**
 * Created by Administrator on 2015/6/11.
 */
public interface QuestionUserService extends GenericService<QuestionUser,Integer> {

    boolean existIp(String ip);

    boolean existPhone(String phone);

}
