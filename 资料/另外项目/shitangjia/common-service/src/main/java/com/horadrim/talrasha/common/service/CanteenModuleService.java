package com.horadrim.talrasha.common.service;

import com.horadrim.talrasha.common.model.Canteen_Module;

import java.util.List;

/**
 * Created by Administrator on 2015/6/30.
 */
public interface CanteenModuleService extends  GenericService<Canteen_Module,Integer>{

    List<Canteen_Module> getByCanteenId(int canteenId);
    void delCanModuleByCanteenId(int canteenId);
    void saveCanModule(String[] moduleCode,int canteenId);

}
