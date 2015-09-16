package cn.scud.main.shopcar.service;

import cn.scud.main.shopcar.model.Shopcar;

import java.util.List;

/**
 * Created by Administrator on 2015/9/16.
 */
public interface ShopcarService {

    /**
     * 保存购物车
     */
    void saveShopcar(Shopcar shopcar);

    /**
     *  根据 userToken 和 storeToken 查询个人购物车
     */
    List<Shopcar> listShopcar(String userToken,String storeToken);
}
