package com.horadrim.talrasha.common.service;


import com.horadrim.talrasha.common.model.Product;

import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2015/6/1.
 */
public interface ProductService extends GenericService<Product,Integer> {

    /**
     * 返回带分页数据
     * @param createdTime 产品供应时间
     * @param timeNode  1 早餐 2 午餐 3晚餐
     * @param currentPage 当前页 默认从1开始
     * @param size 每页显示条数
     * @param sort 排序 0按id升序 1按id降序
     */
    List<Product> queryProducts(int canteenId,Date createdTime, int timeNode,int currentPage , int size,int sort);
    /**
     * 返回带分页数据
     * @param createdTime 产品供应时间
     * @param timeNode  1 早餐 2 午餐 3晚餐
     * @param currentPage 当前页 默认从1开始
     * @param size 每页显示条数
     */
    List<Product> queryProducts(int canteenId,Date createdTime, int timeNode,int currentPage , int size);

    /**
     * 返回全部，不带分页
     * @param createdTime 产品供应时间
     * @param timeNode  1 早餐 2 午餐 3晚餐
     */
    List<Product> queryProducts(int canteenId,Date createdTime, int timeNode);

    /**
     * 更具日期查询菜品
     * @param canteenId 食堂id
     */
    List<Product> queryProducts(int canteenId,Date createTime);

    /**
     * 修改菜单列表
     * @param id
     * @param canteenId
     */
    void updateProduct(int id,int canteenId);

    void deleteProduct(int id);


}
