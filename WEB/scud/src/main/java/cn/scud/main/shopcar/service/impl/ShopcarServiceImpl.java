package cn.scud.main.shopcar.service.impl;

import cn.scud.main.shopcar.dao.ShopcarDao;
import cn.scud.main.shopcar.model.Shopcar;
import cn.scud.main.shopcar.service.ShopcarService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2015/9/16.
 */
@Service("shopcarService")
public class ShopcarServiceImpl implements ShopcarService{

    @Resource
    private ShopcarDao shopcarDao;

    @Override
    public void saveShopcar(Shopcar shopcar) {
        shopcarDao.saveShopcar(shopcar);
    }

    @Override
    public List<Shopcar> listShopcar(String userToken, String storeToken) {
        Map map = new Map();
        return null;
    }
}
