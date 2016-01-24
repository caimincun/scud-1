package cn.scud.main.storeorder.service.impl;

import cn.scud.main.product.dao.ProductDao;
import cn.scud.main.product.model.Product;
import cn.scud.main.receipt.model.Receipt;
import cn.scud.main.storeorder.dao.StoresorderDao;
import cn.scud.main.storeorder.model.StoreOrderListlEntity;
import cn.scud.main.storeorder.model.Storeorder;
import cn.scud.main.storeorder.service.StoreorderService;
import cn.scud.main.user.dao.UserDao;
import cn.scud.main.user.model.UserInfo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2015/10/3.
 */
@Service("storeorderService")
public class StoreorderServiceImpl implements StoreorderService{

    @Resource
    private StoresorderDao storesorderDao;

    @Resource
    private UserDao userDao;

    @Resource
    private ProductDao productDao;

    @Override
    public void saveStoreOrder(List<Storeorder> storeorders) {
        int isShow = 1;
        for(Storeorder storeorder:storeorders){
            storeorder.setIsShow(isShow);
            storesorderDao.saveStoreorder(storeorder);
            if(isShow == 1){                // 保证一个订单里面只有一个商品能够展示在订单列表
                isShow = 0;
            }
        }
    }

    /**
     * 查看商铺正在交易的订单列表
     * @return
     */
    @Transactional
    @Override
    public List<StoreOrderListlEntity> storeOrderList(String storeToken) {

        // 1. 查询下单人的用户名token
//        List<String> orderUsertokens= storesorderDao.listOrderUsertoken(storeToken);
//        List<StoreOrderListlEntity> storeOrderListlEntities = new ArrayList<StoreOrderListlEntity>();
//        for(String userToken: orderUsertokens){
//            StoreOrderListlEntity storeOrderListlEntity = new StoreOrderListlEntity();
//            UserInfo userInfo = userDao.getUserInfoByToken(userToken);
//            storeOrderListlEntity.setUserName(userInfo.getUserRealName());                                // 设置用户名
//            storeOrderListlEntity.setOrderState(1);                                     // 设置订单交易状态，此时默认正在交易中
//            // 2. 查询这个人下单的商品总数
//            Map map = new HashMap();
//            map.put("storeToken",storeToken);
//            map.put("userToken",userToken);
//            int totalProduct = storesorderDao.totalProductNum(map);
//            storeOrderListlEntity.setTotalProductNum(totalProduct);                     // 查询订单商品数量
//
//            storeOrderListlEntity.setStoreToken(storeToken);
//            storeOrderListlEntity.setUserToken(userToken);
//
//            //3. 将 isShow = 1 的商品 查询出来，方便订单列表展示
//            Product product = productDao.queryShowProduct(map);
//            storeOrderListlEntity.setProductPrcture(product.getProductPictures());
//            Storeorder storeorder = storesorderDao.queryShowProduct(map);
//            storeOrderListlEntity.setShowProductNum(storeorder.getProductNum());
//            storeOrderListlEntity.setShowProductToalPrice(storeorder.getProductNum() * product.getProductMoney());
//
//            //4 查询这个人订单的总价
//            double totalPrice = storesorderDao.totalOrderPrice(map);
//            storeOrderListlEntity.setTotalProductPrice(totalPrice);
//
//            //5.将数据实体添加进入List 集合
//            storeOrderListlEntities.add(storeOrderListlEntity);
//        }


        List<StoreOrderListlEntity> storeOrderListlEntities = new ArrayList<StoreOrderListlEntity>();
        // 查询商铺正在交易的订单的 storeTokens
        List<String> orderTokens = storesorderDao.queryOrderTokens(storeToken);
        for(String orderToken:orderTokens){
             StoreOrderListlEntity storeOrderListlEntity = new StoreOrderListlEntity();
            //1. 根据订单号，查询当前订单下单人的用户姓名
            String userName = storesorderDao.queryUnameBystken(orderToken);
            storeOrderListlEntity.setUserName(userName);                                // 设置用户名
            storeOrderListlEntity.setOrderState(1);                       // 设置订单交易状态，此时默认正在交易中

            //2. 将 isShow = 1 的商品 查询出来，方便订单列表展示
            Product product = productDao.queryProductByStoreorderToken(orderToken);
            storeOrderListlEntity.setProductPrcture(product.getProductPictures());
            storeOrderListlEntity.setProductName(product.getProductName());
            int buyNum = storesorderDao.queryProNum(orderToken);
            storeOrderListlEntity.setShowProductToalPrice(product.getProductMoney()*buyNum);
            storeOrderListlEntity.setShowProductNum(buyNum);

            //3. 查询这个订单 总共的商品数量
            int totalProductNum = storesorderDao.queryAllNum(orderToken);
            storeOrderListlEntity.setTotalProductNum(totalProductNum);
            // 4. 查询这个订单的总价
            double totalPrice = storesorderDao.queryTotalPricebyOrderken(orderToken);
            storeOrderListlEntity.setTotalProductPrice(totalPrice);

//            5.将数据实体添加进入List 集合
            storeOrderListlEntities.add(storeOrderListlEntity);

        }

        return storeOrderListlEntities;
    }

    @Override
    public List<StoreOrderListlEntity> orderDetailProducts(String storeOrderToken) {

        List<StoreOrderListlEntity> storeOrderListlEntities = new ArrayList<StoreOrderListlEntity>();
        // 1. 查询出该订单下所有商品的productToken
        List<Storeorder> storeorders = storesorderDao.listProductTokens(storeOrderToken);
        // 2. 遍历查询所有商品的相关信息
        Product product ;
        StoreOrderListlEntity storeOrderListlEntity = new StoreOrderListlEntity();
        for(Storeorder storeorder:storeorders){
            product = productDao.queryProductBytoken(storeorder.getProductToken());
            storeOrderListlEntity.setProductPrcture(product.getProductPictures());  // 默认商品只有一张图片
            storeOrderListlEntity.setProductName(product.getProductName());
            storeOrderListlEntity.setShowProductNum(storeorder.getProductNum());
            storeOrderListlEntity.setShowProductToalPrice(storeorder.getProductNum()*product.getProductMoney());
            storeOrderListlEntities.add(storeOrderListlEntity);
        }
        return storeOrderListlEntities;
    }

    @Override
    public void deleteStoreoOrder(String storeOrderToken) {
        storesorderDao.deleteStoreoOrder(storeOrderToken);
    }

    @Override
    public void setOrderComplete(String storeOrderToken) {
        storesorderDao.setOrderComplete(storeOrderToken);
    }


}
