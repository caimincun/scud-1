package cn.scud.main.shopcar.service.impl;

import cn.scud.main.shopcar.dao.ShopcarDao;
import cn.scud.main.shopcar.model.Shopcar;
import cn.scud.main.shopcar.service.ShopcarService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2015/9/16.
 */
@Service("shopcarService")
public class ShopcarServiceImpl implements ShopcarService{

    @Resource
    private ShopcarDao shopcarDao;

    /**
     * 往购物车里面添加商品
     * @param shopcar
     */
    @Override
    public void saveShopcar(Shopcar shopcar) {
        int productNum = shopcarDao.numProductInShopcar(shopcar);
        if(productNum == 0){    // 如果购物车里面没有商品，则往购物车里面添加商品
            shopcarDao.saveShopcar(shopcar);
        }else{
            //如果购物车里面有商品，则使其数量 +1
            shopcarDao.setProductNumAdd(shopcar);
        }

    }

    /**
     * 查询用户在某一个商铺里面的购物车信息
     * @param userToken
     * @param storeToken
     * @return
     */
    @Override
    public List<Map<Object,Object>> listShopcarInstore(String userToken, String storeToken) {
        Map map = new HashMap();
        map.put("userToken",userToken);
        map.put("storeToken",storeToken);
        return shopcarDao.listShopcarInstore(map);
    }

//    @Override
//    public List<Shopcar> listShopcar(String userToken, String storeToken) {
//        Map map = new HashMap();
//        map.put("userToken",userToken);
//        map.put("storeToken",storeToken);
//        return shopcarDao.listShopcar(map);
//    }
}
