package com.horadrim.talrasha.common.service;

import com.horadrim.talrasha.common.model.InformationCategory;
import com.horadrim.talrasha.common.model.QuestionCategory;

import java.util.List;

/**
 * Created by Administrator on 2015/7/27.
 */
public interface InformationCategoryService extends GenericService<InformationCategory,Integer>{

    List<InformationCategory> listIncaByCanteenId(int canteenId);
    void updateInfoStatuFalse(int canteenId);
    void saveInformationCa(int canteenId,String[] infroCode);

    /**
     * 修改 个人中心模块下载的子模块选择
     * @param usercenter
     */
    void saveUserCenter(String usercenter,int canteenId,boolean flag);
}
