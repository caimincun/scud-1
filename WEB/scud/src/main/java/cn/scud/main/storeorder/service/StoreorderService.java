package cn.scud.main.storeorder.service;

import cn.scud.main.storeorder.model.Storeorder;

import java.util.List;

/**
 * Created by Administrator on 2015/10/3.
 */
public interface StoreorderService {

    /**
     * 保存商铺订单
     * @param storeorders
     */
    void saveStoreOrder(List<Storeorder> storeorders);
}
