package cn.scud.main.shopcar.service;

import cn.scud.main.shopcar.model.Shopcar;

import java.util.List;
import java.util.Map;

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
//    List<Shopcar> listShopcar(String userToken,String storeToken);

    /**
     * 查询用户在某一个商铺的里面的购物车信息
     * @param userToken
     * @param storeToken
     * @return
     */
    List<Map<Object,Object>> listShopcarInstore(String userToken,String storeToken);


}
