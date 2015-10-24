package cn.scud.main.producttype.dao;

import cn.scud.main.producttype.model.Producttype;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2015/9/20.
 */
public interface ProducttypeDao {

    /**
     * 保存商品分类
     */
    void savetype(Producttype producttype);

    /**
     * 查询商铺 所有的商品分类
     * @param storeToken
     * @return
     */
    List<Producttype> listTpyes(String storeToken);

    /**
     * 删除商品分裂
     * @param map
     */
    void deleteType(Map map);
}
