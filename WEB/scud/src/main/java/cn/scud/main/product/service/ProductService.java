package cn.scud.main.product.service;

import cn.scud.main.product.model.Product;

import java.util.List;

/**
 * Created by Administrator on 2015/9/15.
 */
public interface ProductService {

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
}
