package com.horadrim.talrasha.common.service.impl;

import com.horadrim.talrasha.common.dao.GenericDao;
import com.horadrim.talrasha.common.dao.VegetableDao;
import com.horadrim.talrasha.common.model.CanteenMessage;
import com.horadrim.talrasha.common.model.Vegetable;
import com.horadrim.talrasha.common.model.VegetableOrderItem;
import com.horadrim.talrasha.common.service.VegetableOrderItemService;
import com.horadrim.talrasha.common.service.VegetableService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2015/7/7.
 */
@Service("vegetableService")
public class VegetableServiceImpl extends GenericServiceImpl<Vegetable,Integer> implements VegetableService {
    @Resource
    private VegetableDao vegetableDao;

    @Resource
    private VegetableOrderItemService vegetableOrderItemService;
    @Override
    protected GenericDao<Vegetable, Integer> getGenericDao() {
        return vegetableDao;
    }

    @Override
    public List<Vegetable> listByCategory(int categoryId,int canteenId) {
        final String hql ="FROM Vegetable WHERE vegetableCategoryId = "+categoryId+" canteenId="+canteenId;
        return list(hql,null);
    }

    @Override
    public List<Vegetable> listByCanteen(int canteenId,int currentPage , int size,int isExist) {

        StringBuilder sb = new StringBuilder("FROM Vegetable WHERE canteenId=");
        sb.append(canteenId);
        //isExist==-1查询全部
        if (isExist!=-1){
            //前台展示，查询第二天
            sb.append(" AND saleTime=");
            Calendar c = Calendar.getInstance();
            c.setTime(new Date());
            c.add(Calendar.DATE, 1);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            sb.append("'");
            sb.append(sdf.format(c.getTime()));
            sb.append("'");
        }
        sb.append(" order by id desc");

        if (currentPage!=0&&size!=0){
            return vegetableDao.list(sb.toString(),currentPage*size,size,null);
        }
        return vegetableDao.list(sb.toString(),currentPage*size,size,null);
    }
    @Override
    public List<Vegetable> listByCanteen(int canteenId,int currentPage , int size) {
        return listByCanteen(canteenId,currentPage,size,-1);
    }

    @Override
    public List<Vegetable> listByVegaId(int canteenId, int currentPage, int size, int vegetableCategoryId) {
        StringBuilder sb = new StringBuilder("FROM Vegetable WHERE canteenId=");
        sb.append(canteenId);
        sb.append(" AND vegetableCategoryId ="+vegetableCategoryId);
        sb.append(" order by id desc");
        System.out.println(sb.toString());
        if (currentPage!=0&&size!=0){
            return vegetableDao.list(sb.toString(),currentPage*size,size,null);
        }
        return vegetableDao.list(sb.toString(),currentPage*size,size,null);
    }

    @Override
    public List<Vegetable> listByCanteen(int canteenId,int isExist) {
        return listByCanteen(canteenId,0,0,isExist);
    }

    @Override
    public List<Vegetable> listByCanteen(int canteenId) {
        return listByCanteen(canteenId,0,0,-1);
    }



}
