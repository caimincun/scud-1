package com.horadrim.talrasha.common.service;

import com.horadrim.talrasha.common.model.Vegetable;

import java.util.List;

/**
 * Created by Administrator on 2015/7/7.
 */
public interface VegetableService extends GenericService<Vegetable,Integer>{
    List<Vegetable> listByCategory(int categoryId,int canteenId);

    /**
     * @param canteenId  食堂id
     * @param currentPage 当前页
     * @param size     每页条数
     * @param isExist  是否在售  0 在售 1下架 -1 全部
     */
     List<Vegetable> listByCanteen(int canteenId,int currentPage , int size,int isExist) ;
    /**
     * @param canteenId  食堂id
     * @param currentPage 当前页
     * @param size     每页条数
     */
    List<Vegetable> listByCanteen(int canteenId,int currentPage , int size);

    List<Vegetable> listByVegaId(int canteenId,int currentPage , int size,int vegetableCategoryId);
    /**
     * @param canteenId  食堂id
     * @param isExist  是否在售  0 在售 1下架 -1 全部
     */
    List<Vegetable> listByCanteen(int canteenId,int isExist);
    /**
     * @param canteenId  食堂id
     */
    List<Vegetable> listByCanteen(int canteenId);

}
