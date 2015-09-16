package cn.scud.main.shopcar.dao;

import cn.scud.main.shopcar.model.Shopcar;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2015/9/16.
 */
public interface ShopcarDao {
    /**
     * 保存购物车
     */
    void saveShopcar(Shopcar shopcar);
    /**
     * 判断 商品是否在购物车里面
     */
    int numProductInShopcar(Shopcar shopcar);

    /**
     * 让购物车里面某件商品的数量加 1
     */
    void setProductNumAdd(Shopcar shopcar);
    /**
     *  查询个人购物车
     */
    List<Shopcar> listShopcar(Map map);
}
