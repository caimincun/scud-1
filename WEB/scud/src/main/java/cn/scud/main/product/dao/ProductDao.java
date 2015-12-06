package cn.scud.main.product.dao;

import cn.scud.main.product.model.Product;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2015/9/15.
 */
public interface ProductDao {

    /**
     * 保存 product
     */
    void saveProduct(Product product);


    /**
     * 根据 product 的 userToken 查询 产品列表
     * @param userToken
     * @return
     */
    List<Product> listPorducts(String userToken);

    /**
     * 查看所有下架商品
     * @param userToken
     * @return
     */
    List<Product> listxiajiaPorducts(String userToken);

    /**
     * 根据 productToken 查询 product 信息
     * @param productToken
     * @return
     */
    Product loadProduct(String productToken);

    /**
     * 下架产品
     * @param productToken
     */
    void xiajiaProduct(String productToken);

    /**
     * 上架产品
     * @param productToken
     */
    void shangjiaProduct(String productToken);
    /**
     *删除商品
     * @param productToken
     */
    void deleProduct(String productToken);

    /**
     *  查询订单列表需要展示的那个商品
     */
    Product queryShowProduct(Map map);

    /**
     * 根据商铺订单的订单号查询对应商品且 falg = 1 正在展示商品的信息
     * @param orderToken
     * @return
     */
    Product queryProductByStoreorderToken(String orderToken);

    /**
     *  根据productToken 查询相关商铺的信息
     */
    Product queryProductBytoken(String productToken);
}
