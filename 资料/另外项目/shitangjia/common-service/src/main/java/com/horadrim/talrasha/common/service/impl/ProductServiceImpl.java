package com.horadrim.talrasha.common.service.impl;

import com.horadrim.talrasha.common.dao.GenericDao;
import com.horadrim.talrasha.common.dao.ProductDao;
import com.horadrim.talrasha.common.model.Product;
import com.horadrim.talrasha.common.service.ProductService;
import com.horadrim.talrasha.common.util.SplitDateUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Date;

/**
 * Created by Administrator on 2015/6/1.
 */
@Service("productService")
public class ProductServiceImpl extends GenericServiceImpl<Product,Integer> implements ProductService {
    @Resource(name="productDao")
    private ProductDao productDao;
    @Override
    protected GenericDao<Product, Integer> getGenericDao() {
        return productDao;
    }

    @Override
    public List<Product> queryProducts(int canteenId, Date createdTime, int timeNode, int currentPage, int size, int sort) {
//        if (null==createdTime||canteenId<=0||timeNode<=0){
//            return new ArrayList<>();
//        }
        StringBuilder hql =new StringBuilder("FROM Product  p JOIN FETCH p.productCategory WHERE p.deleteFlag = 1 and p.canteenId=?");
        List<Object> params= new ArrayList<>();
        params.add(canteenId);
        if (createdTime!=null){
            hql.append(" AND p.createdTime=? ");
            params.add(createdTime);
        }
        if (timeNode>0){
            hql.append(" AND p.timeNode=? ");
            params.add(timeNode);
        }
        if(sort==1){
            hql.append(" ORDER BY p.id DESC");
        }
//        if(currentPage>0&&size>1)
        return productDao.list(hql.toString(),currentPage*size,size, params.toArray());
//        return productDao.list(hql.toString(), params.toArray());
    }

    @Override
    public List<Product> queryProducts(int canteenId, Date createdTime, int timeNode, int currentPage, int size) {
//        if (null==createdTime||canteenId<=0||timeNode<=0){
//            return new ArrayList<>();
//        }
        return queryProducts(canteenId,createdTime,timeNode,currentPage,size,1);
    }

    @Override
    public List<Product> queryProducts(int canteenId, Date createdTime, int timeNode) {
        if (null==createdTime||canteenId<=0||timeNode<=0){
            return new ArrayList<>();
        }
        return queryProducts(canteenId,createdTime,timeNode,0,0,0);
    }



    @Override
    public List<Product> queryProducts(int canteenId,Date createTime) {
        return queryProducts(canteenId,createTime,0,0,0);
    }

    @Override
    public void updateProduct(int id, int canteenId) {

    }

    @Override
    public void deleteProduct(int id) {
        String hql = "update Product set deleteFlag = 0 where id ="+id;
        productDao.executeUpdate(hql,null);
    }
}
