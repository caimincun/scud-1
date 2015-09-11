package cn.scud.main.store.controller;

import cn.scud.commoms.CodeDefined;
import cn.scud.commoms.CommonParamDefined;
import cn.scud.commoms.jsonModel.JsonPioSimple;
import cn.scud.commoms.response.*;
import cn.scud.main.store.model.Store;
import cn.scud.main.store.service.StoreService;
import cn.scud.utils.BosHelper;
import cn.scud.utils.LbsHelper;
import cn.scud.utils.StreamSerializer;
import cn.scud.utils.WebUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by Administrator on 2015/9/6.
 */
@Controller
@RequestMapping("/store")
public class StoreController {

    @Resource
    private StoreService storeService;


    /**
     * 保存商店信息数据, 应该保存经纬度
     * @param request
     * @return
     */
    @RequestMapping("/saveStore")
    @ResponseBody
    public OperatorResponse saveStore(HttpServletRequest request,String lng,String lat){
        Store store = null;
        try {
            store =  StreamSerializer.streamSerializer(request.getInputStream(), Store.class);
            System.out.println("store:"+store);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new ErrorJsonRes(CodeDefined.EXCEPTION_CODE_DATA_ERROR,CodeDefined.getMessage(CodeDefined.EXCEPTION_CODE_DATA_ERROR));
        }

        MultipartHttpServletRequest multipartRequest  =  (MultipartHttpServletRequest) request;
        MultipartFile img  =  multipartRequest.getFile("storeImage");
        String path = "";
        if(img != null && img.getSize()>0){
            try {
                path = BosHelper.putStoreImage(img.getInputStream(), WebUtil.getBosOjectKey(), img.getSize(), img.getContentType());
            } catch (IOException e) {
                return new ErrorJsonRes(CodeDefined.EXCEPTION_CODE_PICTURE_ERROR,CodeDefined.getMessage(CodeDefined.EXCEPTION_CODE_PICTURE_ERROR));// 图片上传异常，请重新上传
            }
        } // 此时头像保存成功

        if(lng == null || lat == null){
            return new ErrorJsonRes(CodeDefined.EXCEPTION_CODE_PICTURE_ERROR,CodeDefined.getMessage(CodeDefined.EXCEPTION_CODE_PICTURE_ERROR));// 地图定位失败，请检查网络
        }
        JsonPioSimple jsonPioSimple = LbsHelper.saveStorePio(lng, lat);
        store.setStoreLbsid(jsonPioSimple.getId());                          // 将商铺和经纬度联系起来
        store.setStorePicture(path);
        store.setUserToken((String)request.getSession().getAttribute(CommonParamDefined.USER_TOKEN));
        storeService.saveStore(store);
        return new SuccessJsonRes();
    }


    /**
     *  根据 storeToken 获取商店信息
     * @param session
     * @param storeToken
     * @return
     */
    @RequestMapping("/loadStore")
    @ResponseBody
    public OperatorResponse loadStore(HttpSession session,String storeToken){
        Store store = storeService.loadStore(storeToken);
        ObjSucRes objSucRes = new ObjSucRes();
        return objSucRes;
    }

    /**
     * 修改商铺信息  ,            目前暂时没有考虑修改 store 商店经纬度
     * @param request
     * @return
     */
    @RequestMapping("/updateStore")
    @ResponseBody
    public OperatorResponse updateStore(HttpServletRequest request){
        Store store = null;
        try {
            store =  StreamSerializer.streamSerializer(request.getInputStream(), Store.class);
            System.out.println("store:"+store);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new ErrorJsonRes(CodeDefined.EXCEPTION_CODE_DATA_ERROR,CodeDefined.getMessage(CodeDefined.EXCEPTION_CODE_DATA_ERROR));
        }
        String storePicture = storeService.loadStore(store.getStoreToken()).getStorePicture();      // 保存store 原来 picture path
        MultipartHttpServletRequest multipartRequest  =  (MultipartHttpServletRequest) request;
        MultipartFile img  =  multipartRequest.getFile("storeImage");
        String path = "";
        if(img != null && img.getSize()>0){
            try {
                path = BosHelper.putStoreImage(img.getInputStream(), WebUtil.getBosOjectKey(), img.getSize(), img.getContentType());
            } catch (IOException e) {
                return new ErrorJsonRes(CodeDefined.EXCEPTION_CODE_PICTURE_ERROR,CodeDefined.getMessage(CodeDefined.EXCEPTION_CODE_PICTURE_ERROR));// 图片上传异常，请重新上传
            }
        }
        store.setStorePicture(path);

        if(null != storePicture && "".equals(storePicture)){        // 删除 商店在 bos 上的存储
            BosHelper.deleteStoreObject(storePicture);
        }
        return new SuccessJsonRes();
    }

    /**
     *  分类查询附近范围内的商铺
     * @return
     */
    @RequestMapping("/storeNearby")
    @ResponseBody
    public OperatorResponse storeNearby(HttpServletRequest request,String lng,String lat,int page_index,String storeType){

        return new SuccessJsonRes();
    }

}