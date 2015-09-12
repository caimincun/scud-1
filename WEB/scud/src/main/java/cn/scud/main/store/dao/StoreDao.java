package cn.scud.main.store.dao;

import cn.scud.main.store.model.Store;

import java.util.List;

/**
 * Created by Administrator on 2015/9/6.
 */
public interface StoreDao {

    /**
     * 添加商店
     * @param store
     */
    void saveStore(Store store);
    /**
     * 根据 storeToken 获取 store 对象
     * @param storeToken
     * @return
     */
    Store loadStore(String storeToken);

    /**
     * 修改 store 信息
     * @param store
     */
    void updateStore(Store store);

    /**
     * 通过 store 保存的 lbsid 查询对象
     * @param storePoiIds
     * @return
     */
    List<Store> searchNearbyPoi(List storeLbsids);
}
