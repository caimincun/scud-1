package com.horadrim.talrasha.site.controller.web;


import com.horadrim.talrasha.common.CommonParamDefined;
import com.horadrim.talrasha.common.model.Product;
import com.horadrim.talrasha.common.response.json.AbstractJsonRes;
import com.horadrim.talrasha.common.response.json.GetObjSucRes;
import com.horadrim.talrasha.common.response.json.ListSucRes;
import com.horadrim.talrasha.common.service.ProductCategoryService;
import com.horadrim.talrasha.common.util.SplitDateUtil;
import com.horadrim.talrasha.site.controller.request.CartItem;
import com.horadrim.talrasha.site.controller.result.ProductListPojo;
import com.horadrim.talrasha.site.exception.DefaultExceptionHandler;
import com.horadrim.talrasha.site.service.ClientOrderService;
import com.horadrim.talrasha.site.service.ClientProductService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.*;

/**
 * Created by Administrator on 2014/10/28.
 */
@Controller
@RequestMapping("/product")
public class ProductController extends DefaultExceptionHandler {

    @Resource(name="clientOrderService")
    private ClientOrderService clientOrderService;
    @Resource(name="clientProductService")
    private ClientProductService clientProductService;
    @Resource
    private ProductCategoryService productCategoryService;

    /**
     * 跳转订餐页面
     * @return
     */
    @RequestMapping("/toDianCanPage")
    public ModelAndView toDianCanPage(HttpSession session){
        ModelAndView model=new ModelAndView("diancan");
        model.addObject("week", SplitDateUtil.getCurrentWeekDate());
//        model.addObject("productList",list);
        Map<Integer,CartItem> cart=(Map<Integer,CartItem>)session.getAttribute(CommonParamDefined.CART);
        Integer productCount=0;
        if(null!=cart&&cart.size()>0){
            for(Map.Entry<Integer,CartItem> entry:cart.entrySet()){
                productCount+=entry.getValue().getCount();
            }
        }
        model.addObject("productCount",productCount);
        return model;
    }

    /**
     * 跳转买菜首页
     * @return
     */
    @RequestMapping("/toVegetableListPage")
    public ModelAndView toVegetableListPage(){
        return new ModelAndView("vegetable_list");
    }
    /**
     * 请求产品列表
     */
    @RequestMapping("/list")
    @ResponseBody
    public AbstractJsonRes listProduct(HttpSession session,@DateTimeFormat(pattern = "yyyy-MM-dd")Date date,int timeNode){
//        int canteenId = (int) session.getAttribute(CommonParamDefined.CANTEEN_ID);
        List<Product> list=clientProductService.queryProducts(1,
                date,timeNode==0?2:timeNode);

//        Map<String,List<Product>> map= new LinkedHashMap<>();
        List<ProductListPojo> pojos = new ArrayList<>();
        for (Product product : list){
            boolean flag = false;
            for (ProductListPojo pojo : pojos){
                if (product.getProductCategory().getId()==pojo.getCategoryId()){
                    pojo.getProducts().add(product);
                    flag = true;
                    break;
                }
            }
            if (!flag){
                ProductListPojo pojo = new ProductListPojo();
                pojo.setCategoryId(product.getProductCategory().getId());
                pojo.setCategoryName(product.getProductCategory().getCategoryName());
                pojo.getProducts().add(product);
                pojos.add(pojo);
            }
        }
//        for (int i =0 ;i<list.size();i++){
//            Product product = list.get(i);
//            products.add(product);
//            for (int j = i+1 ; j<list.size();j++){
//                Product productj = list.get(j);
//                if (productj.getProductCategoryId()==product.getProductCategoryId()) {
//                    products.add(product);
//                    list.remove(productj);
//                }
//
//            }
//            map.put(product.getProductCategoryName(),products);
//        }
        ListSucRes res = new ListSucRes();
        res.setData(pojos);
//        GetObjSucRes res = new GetObjSucRes();
//        res.setData(map);
        return res;
    }
}
