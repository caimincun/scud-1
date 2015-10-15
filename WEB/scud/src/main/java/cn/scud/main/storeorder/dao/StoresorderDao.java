package cn.scud.main.storeorder.dao;

import cn.scud.main.storeorder.model.Storeorder;

/**
 * Created by Administrator on 2015/10/3.
 */
public interface StoresorderDao {

    /**
     * 保存商铺订单
     * @param storeorder
     */
    void saveStoreorder(Storeorder storeorder);
}
