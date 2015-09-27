package cn.scud.main.product.controller;

import cn.scud.commoms.CodeDefined;
import cn.scud.commoms.response.*;
import cn.scud.main.product.model.Product;
import cn.scud.main.product.service.ProductService;
import cn.scud.utils.BosHelper;
import cn.scud.utils.StreamSerializer;
import cn.scud.utils.WebUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

/**
 * Created by Administrator on 2015/9/15.
 */
@Controller
@RequestMapping("/product")
public class ProductController {

    @Resource
    private ProductService productService;


    /**
     *  保存 product 数据
     * @return
     */
    @RequestMapping("/saveProduct")
    @ResponseBody
    public OperatorResponse saveProduct(HttpServletRequest request){
        Product product = null;
        try {
            product =  StreamSerializer.streamSerializer(request.getInputStream(), Product.class);
            System.out.println("product:"+product);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new ErrorJsonRes(CodeDefined.EXCEPTION_CODE_DATA_ERROR,CodeDefined.getMessage(CodeDefined.EXCEPTION_CODE_DATA_ERROR));
        }

        MultipartHttpServletRequest multipartRequest  =  (MultipartHttpServletRequest) request;
        MultipartFile img0  =  multipartRequest.getFile("productImage0");
        MultipartFile img1  =  multipartRequest.getFile("productImage1");
        MultipartFile img2  =  multipartRequest.getFile("productImage2");
        String prictureTemp = "";
        if(null != img0 && img0.getSize()>0){
            try {
                prictureTemp += BosHelper.putSkillImage(img0.getInputStream(), WebUtil.getBosOjectKey(), img0.getSize(), img0.getContentType());
            } catch (IOException e) {
                return new ErrorJsonRes(CodeDefined.EXCEPTION_CODE_PICTURE_ERROR,CodeDefined.getMessage(CodeDefined.EXCEPTION_CODE_PICTURE_ERROR));
            }
        }
        if(null != img1 && img1.getSize()>0){
            prictureTemp += ";";
            try {
                prictureTemp += BosHelper.putSkillImage(img1.getInputStream(), WebUtil.getBosOjectKey(), img1.getSize(), img1.getContentType());
            } catch (IOException e) {
                return new ErrorJsonRes(CodeDefined.EXCEPTION_CODE_PICTURE_ERROR,CodeDefined.getMessage(CodeDefined.EXCEPTION_CODE_PICTURE_ERROR));
            }
        }
        if(null != img2 && img2.getSize()>0){
            prictureTemp += ";";
            try {
                prictureTemp += BosHelper.putSkillImage(img2.getInputStream(), WebUtil.getBosOjectKey(), img2.getSize(), img2.getContentType());
            } catch (IOException e) {
                return new ErrorJsonRes(CodeDefined.EXCEPTION_CODE_PICTURE_ERROR,CodeDefined.getMessage(CodeDefined.EXCEPTION_CODE_PICTURE_ERROR));
            }
        }
        product.setProductPictures(prictureTemp);
        product.setDeleteFlag(1);
        return new SuccessJsonRes();
    }

    /**
     *  查询某一个商铺的所有产品
     * @return
     */
    public OperatorResponse listProducts(String storeToken){
        List<Product> productList = productService.listPorducts(storeToken);
        ListSucRes listSucRes = new ListSucRes();
        return listSucRes;
    }

    /**
     *  根据 productToken 查询某一个商品的详细信息
     * @return
     */
    @RequestMapping("/loadProduct")
    @ResponseBody
    public OperatorResponse loadProduct(String productToken){
        Product product = productService.loadProduct(productToken);
        ObjSucRes objSucRes = new ObjSucRes();
        return  objSucRes;
    }


}
