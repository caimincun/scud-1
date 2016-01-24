package com.horadrim.talrasha.common.service.impl;

import com.horadrim.talrasha.common.dao.GenericDao;
import com.horadrim.talrasha.common.dao.InformationCategoryDao;
import com.horadrim.talrasha.common.dao.QuestionCategoryDao;
import com.horadrim.talrasha.common.model.InformationCategory;
import com.horadrim.talrasha.common.model.QuestionCategory;
import com.horadrim.talrasha.common.service.InformationCategoryService;
import com.horadrim.talrasha.common.service.QuestionCategoryService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Administrator on 2015/7/27.
 */
@Service("informationCategoryService")
public class InformationCategoryServiceImpl extends GenericServiceImpl<InformationCategory,Integer> implements InformationCategoryService {
    @Resource
    private InformationCategoryDao informationCategoryDao;

    @Override
    protected GenericDao<InformationCategory, Integer> getGenericDao() {
        return informationCategoryDao;
    }

    @Override
    public List<InformationCategory> listIncaByCanteenId(int canteenId) {
        String hql = "from InformationCategory where canteenId ="+canteenId;
        return informationCategoryDao.list(hql,null);
    }

    @Override
    public void updateInfoStatuFalse(int canteenId) {
        String sql = "update InformationCategory set status = 1 where moduleId = 3 and canteenId ="+canteenId;
        informationCategoryDao.executeUpdate(sql,null);
    }

    @Override
    public void saveInformationCa(int canteenId,String[] infroCode) {
        updateInfoStatuFalse(canteenId);
        for(int i = 0;i<infroCode.length;i++){
            String sql =  "update InformationCategory set status = 0 where moduleId = 3 and canteenId ="+canteenId+" and id ="+infroCode[i];
            informationCategoryDao.executeUpdate(sql,null);
        }
    }
    @Override
    public void saveUserCenter(String usercenter,int canteenId,boolean flag) {
        String sql = "update InformationCategory set status = 1 where moduleId = 6 and canteenId =" + canteenId;
        informationCategoryDao.executeUpdate(sql, null);
        if (flag) {
            String[] userCenter = usercenter.split(",");
            System.out.println(userCenter);
            for (int i = 0; i < userCenter.length; i++) {
                System.out.println("userCenter[i]:" + userCenter[i]);
                String sql2 = "update InformationCategory set status = 0 where moduleId = 6 and canteenId =" + canteenId + " and id =" + userCenter[i];
                informationCategoryDao.executeUpdate(sql2, null);
            }
        }
    }


}
