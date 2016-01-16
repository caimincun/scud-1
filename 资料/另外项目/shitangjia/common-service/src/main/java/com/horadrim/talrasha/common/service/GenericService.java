package com.horadrim.talrasha.common.service;


import com.horadrim.talrasha.common.dao.GenericDao;

import java.io.Serializable;

public interface GenericService<T, K extends Serializable> extends GenericDao<T, K> {
}
