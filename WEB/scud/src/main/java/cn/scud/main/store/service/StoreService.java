package cn.scud.main.store.service;

import cn.scud.main.store.model.Store;
import cn.scud.main.user.model.UserInfo;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by Administrator on 2015/9/6.
 */
public interface StoreService {

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
     *  修改store
     * @param store
     */
    void updateStore(Store store);

    /**
     * 修改头像
     */
    void updateStorePicture(String storePicture,String storeToken);
    /**
     * 查询附近的商铺
     * @param session
     * @param lng
     * @param lat
     * @param radius
     * @param page_index
     * @param page_size
     * @param storeType
     * @return
     */
    List<Store> storeNearby(HttpSession session,String lng,String lat,int radius,int page_index,int page_size,String storeType);
}
