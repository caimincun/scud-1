package cn.scud.main.storeorder.controller;

import cn.scud.commoms.CommonParamDefined;
import cn.scud.commoms.jsonModel.JsonPioSimple;
import cn.scud.commoms.response.OperatorResponse;
import cn.scud.commoms.response.SuccessJsonRes;
import cn.scud.main.storeorder.model.ProductAndNum;
import cn.scud.main.storeorder.model.ProductModel;
import cn.scud.main.storeorder.model.StoreOrderModel;
import cn.scud.main.storeorder.model.Storeorder;
import cn.scud.main.storeorder.service.StoreorderService;
import cn.scud.utils.JsonSerializer;
import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.apache.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Administrator on 2015/10/3.
 */
@RequestMapping("/storeOrderController")
@Controller
public class StoreorderController {

    @Resource
    private StoreorderService storeorderService;

    /**
     *  保存商铺订单
     * @return
     */
    @RequestMapping("/saveStoder")
    @ResponseBody
    public OperatorResponse saveStoreOrder(@RequestBody String receiptId,HttpSession session){
        String userToken = (String)session.getAttribute(CommonParamDefined.USER_TOKEN);
        Gson gson = new Gson();//new一个Gson对象
        System.out.println(receiptId);
        StoreOrderModel model = JsonSerializer.deSerialize(receiptId, StoreOrderModel.class);
        System.out.println(model.getOrders());
        List<Storeorder> storeorders = new ArrayList<Storeorder>();
        Storeorder storeorder = new Storeorder();
        for(ProductModel productModel:model.getOrders()){
            storeorder.setOrderFlag(1);
            storeorder.setStoreToken(model.getStoreToken());
            storeorder.setReceiptId(model.getReceiptId());
            storeorder.setUserToken(userToken);
            storeorder.setProductToken(productModel.getProductToken());
            storeorder.setProductNum(productModel.getCount());
            storeorder.setUserToken((String)session.getAttribute(CommonParamDefined.USER_TOKEN));
            storeorders.add(storeorder);
        }
        System.out.println(storeorders);
        storeorderService.saveStoreOrder(storeorders);
        return new SuccessJsonRes();
    }

}
