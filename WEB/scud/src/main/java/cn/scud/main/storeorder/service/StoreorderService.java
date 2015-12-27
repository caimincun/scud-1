package cn.scud.main.storeorder.service;

import cn.scud.main.receipt.model.Receipt;
import cn.scud.main.storeorder.model.StoreOrderListlEntity;
import cn.scud.main.storeorder.model.Storeorder;

import java.util.List;

/**
 * Created by Administrator on 2015/10/3.
 */
public interface StoreorderService {

    /**
     * 保存商铺订单
     * @param storeorders
     */
    void saveStoreOrder(List<Storeorder> storeorders);
    /**
     * 查看商铺正在交易的订单信息
     */
    List<StoreOrderListlEntity> storeOrderList(String storeToken);

    /**
     *  查询订单的商品详情
     * @param storeOrderToken
     * @return
     */
    List<StoreOrderListlEntity> orderDetailProducts(String storeOrderToken);

    /**
     * 删除订单
     */
    void deleteStoreoOrder(String storeOrderToken);

    /**
     * 设置订单为完成
     */
    void setOrderComplete(String storeOrderToken);


}
