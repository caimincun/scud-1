package com.horadrim.talrasha.common.service;



import com.horadrim.talrasha.common.model.SignChain;

import java.util.List;


public interface SignChainService extends GenericService<SignChain, Integer> {

public List<SignChain> getSignChainByCanteenId(Integer canteenId);
}

