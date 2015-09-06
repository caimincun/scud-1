package cn.scud.main.store.dao;

import cn.scud.main.store.model.Store;

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
}
