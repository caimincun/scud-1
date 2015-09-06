package cn.scud.main.store.service.impl;

import cn.scud.main.store.dao.StoreDao;
import cn.scud.main.store.model.Store;
import cn.scud.main.store.service.StoreService;
import cn.scud.utils.WebUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by Administrator on 2015/9/6.
 */
@Service("storeService")
public class StoreServiceImpl implements StoreService {

    @Resource
    private StoreDao storeDao;


    @Override
    public void saveStore(Store store) {
        store.setStoreToken(WebUtil.getStoreToken());
        storeDao.saveStore(store);
    }

    @Override
    public Store loadStore(String storeToken) {
        return storeDao.loadStore(storeToken);
    }

    @Override
    public void updateStore(Store store) {
        storeDao.updateStore(store);
    }

    /**
     * 分类查询附近的商铺
     * @param session
     * @param lng
     * @param lat
     * @param radius
     * @param page_index
     * @param page_size
     * @param storeType
     * @return
     */
    @Override
    public List<Store> storeNearby(HttpSession session, String lng, String lat, int radius, int page_index, int page_size, String storeType) {
        return null;
    }


}
