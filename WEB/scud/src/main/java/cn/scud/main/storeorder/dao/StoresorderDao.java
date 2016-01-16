package cn.scud.main.storeorder.dao;

import cn.scud.main.storeorder.model.Storeorder;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2015/10/3.
 */
public interface StoresorderDao {

    /**
     * 保存商铺订单
     * @param storeorder
     */
    void saveStoreorder(Storeorder storeorder);
    /**
     * 根据 storeToken 查询 正在交易下单人的用户姓名
     */
    List<String> listOrderUsertoken(String orderToken);

    /**
     * 查询这个人订单的商品总数量
     */
    int totalProductNum(Map map);
    /**
     * 查询个人订单总价
     */
    double totalOrderPrice(Map map);

    /**
     * 查询订单列表展示的商品的数量
     */
    Storeorder queryShowProduct(Map map);

    /**
     *  查询商铺正在交易的订单列表 orderTokens
     */
    List<String> queryOrderTokens(String storeToken);

    /**
     * 根据订单的orderToken 查询下单人姓名
     * @param orderToken
     * @return
     */
    String queryUnameBystken(String orderToken);

    /**
     *  查询展示的商品的购买数量
     * @param orderToken
     * @return
     */
    int queryProNum(String orderToken);

    /**
     * 查询订单下所有商品数量
     * @param orderToken
     * @return
     */
    int queryAllNum(String orderToken);

    /**
     *  查询这个订单的总价
     */
    double queryTotalPricebyOrderken(String orderToken);

    /**
     * 查询出这个订单下所有商品porductToken
     */
    List<Storeorder> listProductTokens(String storeOrderToken);
    /**
     * 删除订单
     */
    void deleteStoreoOrder(String storeOrderToken);
    /**
     * 设置订单为完成
     */
    void setOrderComplete(String storeOrderToken);
}
