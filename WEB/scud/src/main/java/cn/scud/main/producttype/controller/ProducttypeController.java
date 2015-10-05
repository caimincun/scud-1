package cn.scud.main.producttype.controller;

import cn.scud.commoms.CodeDefined;
import cn.scud.commoms.CommonParamDefined;
import cn.scud.commoms.response.ErrorJsonRes;
import cn.scud.commoms.response.ListSucRes;
import cn.scud.commoms.response.OperatorResponse;
import cn.scud.commoms.response.SuccessJsonRes;
import cn.scud.main.producttype.model.Producttype;
import cn.scud.main.producttype.service.ProducttypeService;
import cn.scud.utils.StreamSerializer;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by Administrator on 2015/9/20.
 */
@Controller
@RequestMapping("/producttype")
public class ProducttypeController {

    @Resource
    private ProducttypeService producttypeService;

    /**
     * 保存商品分裂
     * @param request
     * @return
     */
    @RequestMapping("saveType")
    @ResponseBody
    public OperatorResponse saveType(HttpServletRequest request){
        Producttype producttype = new Producttype();
        String typeName = request.getParameter("typeName");
//        try {
//            producttype =  StreamSerializer.streamSerializer(request.getInputStream(), Producttype.class);
//            System.out.println("producttype:"+producttype);
//        } catch (Exception e) {
//            System.out.println(e.getMessage());
//            return new ErrorJsonRes(CodeDefined.EXCEPTION_CODE_DATA_ERROR,CodeDefined.getMessage(CodeDefined.EXCEPTION_CODE_DATA_ERROR));
//        }
        producttype.setTypeName(typeName);
        producttype.setStoreToken((String)request.getSession().getAttribute(CommonParamDefined.USER_TOKEN));
        producttypeService.savetype(producttype);
        return new SuccessJsonRes();
    }

    /**
     * 根据 storeToken 查询商铺 所有 商品分类
     * @param storeToken
     * @return
     */
    @RequestMapping("/listproductTypes")
    @ResponseBody
    public OperatorResponse listproductTypes(HttpSession session){
        List<Producttype> producttypeList = producttypeService.listTpyes((String)session.getAttribute(CommonParamDefined.USER_TOKEN));
        ListSucRes listSucRes = new ListSucRes();
        listSucRes.setData(producttypeList);
        return listSucRes;

    }


    /**
     * 删除商品分类  ， 暂时不级联删除相关的商品
     * @param storeToken
     * @param typeToken
     * @return
     */
    @RequestMapping("/deleteType")
    @ResponseBody
    public OperatorResponse deleteType(HttpSession session,String typeToken){
            producttypeService.deleteType((String)session.getAttribute(CommonParamDefined.USER_TOKEN),typeToken);
        return new SuccessJsonRes();
    }
}
