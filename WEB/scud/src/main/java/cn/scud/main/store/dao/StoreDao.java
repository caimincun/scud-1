package cn.scud.main.store.dao;

import cn.scud.main.store.model.Store;

import java.util.List;
import java.util.Map;

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
     * 根据userToken 获取商铺信息
     * @param userToken
     * @return
     */
    Store loadStoreByUsken(String userToken);

    /**
     * 修改 store 信息
     * @param store
     */
    void updateStore(Store store);

    /**
     * 修改头像
     */
    void updateStorePicture(Map map);

    /**
     * 修改商铺的经纬度
     * @param lbsid
     * @param userToken
     */
    void updateLbs(Map map);

    /**
     * 通过 store 保存的 lbsid 查询对象
     * @param storePoiIds
     * @return
     */
    List<Store> searchNearbyPoi(List storeLbsids);

    /**
     * 判断用户是否开启了商铺
     * @param userToken
     * @return
     */
    int countNumstore(String userToken);
}
