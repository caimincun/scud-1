package cn.scud.main.storeorder.service.impl;

import cn.scud.main.storeorder.dao.StoresorderDao;
import cn.scud.main.storeorder.model.Storeorder;
import cn.scud.main.storeorder.service.StoreorderService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Administrator on 2015/10/3.
 */
@Service("storeorderService")
public class StoreorderServiceImpl implements StoreorderService{

    @Resource
    private StoresorderDao storesorderDao;

    @Override
    public void saveStoreOrder(List<Storeorder> storeorders) {
        for(Storeorder storeorder:storeorders){
            storesorderDao.saveStoreorder(storeorder);
        }
    }
}
