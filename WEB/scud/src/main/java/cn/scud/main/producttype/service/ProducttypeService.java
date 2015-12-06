package cn.scud.main.producttype.service;

import cn.scud.main.producttype.model.Producttype;

import java.util.List;

/**
 * Created by Administrator on 2015/9/20.
 */
public interface ProducttypeService {
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
     * 删除商品分类
     * @param stroreToken
     * @param typeToken
     */
    void deleteType(String storeToken,String typeToken);
}
