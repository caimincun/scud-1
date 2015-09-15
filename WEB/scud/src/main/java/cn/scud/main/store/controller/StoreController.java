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
import java.util.List;

/**
 * Created by Administrator on 2015/9/6.
 */
@Controller
@RequestMapping("/store")
public class StoreController {

    @Resource
    private StoreService storeService;

    /**
     *  开启商铺 ,此时只上传了商铺的头像和上面的名称 ，还有开启经纬度
     * @param request
     * @return
     */
    @RequestMapping("/startStore")
    @ResponseBody
    public OperatorResponse startStore(HttpServletRequest request,String lng,String lat){
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
        store.setStoreToken(WebUtil.getStoreToken());
        store.setUserToken((String)request.getSession().getAttribute(CommonParamDefined.USER_TOKEN));
        store.setStorePicture(path);
        // 然后定位经纬度
        if(lng == null || lat == null){
            return new ErrorJsonRes(CodeDefined.EXCEPTION_CODE_PICTURE_ERROR,CodeDefined.getMessage(CodeDefined.EXCEPTION_CODE_PICTURE_ERROR));// 地图定位失败，请检查网络
        }
        JsonPioSimple jsonPioSimple = LbsHelper.saveStorePio(lng,lat);
        store.setStoreLbsid(jsonPioSimple.getId());
        storeService.saveStore(store);
        return new SuccessJsonRes();
    }


    /**
     * 修改商铺头像
     * @param request
     * @return
     */
    @RequestMapping("/updateStorePicture")
    @ResponseBody
    public OperatorResponse updateStorePicture(HttpServletRequest request){
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
        /*然后取出远端的商店图片删除*/
        Store store = storeService.loadStoreByUsken((String)request.getSession().getAttribute(CommonParamDefined.USER_TOKEN));
        BosHelper.deleteStoreObject(store.getStorePicture());
        storeService.updateStorePicture(path,store.getStoreToken());
        return new SuccessJsonRes();
    }





    /**
     * 保存商店信息数据
     * @param request
     * @return
     */
    @RequestMapping("/saveStore")
    @ResponseBody
    public OperatorResponse saveStore(HttpServletRequest request){
        Store store = null;
        try {
            store =  StreamSerializer.streamSerializer(request.getInputStream(), Store.class);
            System.out.println("store:"+store);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new ErrorJsonRes(CodeDefined.EXCEPTION_CODE_DATA_ERROR,CodeDefined.getMessage(CodeDefined.EXCEPTION_CODE_DATA_ERROR));
        }

//        if(lng == null || lat == null){
//            return new ErrorJsonRes(CodeDefined.EXCEPTION_CODE_PICTURE_ERROR,CodeDefined.getMessage(CodeDefined.EXCEPTION_CODE_PICTURE_ERROR));// 地图定位失败，请检查网络
//        }
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
     * 修改商铺信息
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
     * 修改商铺经纬度
     * @param session
     * @param lng
     * @param lat
     * @return
     */
    public OperatorResponse updateLbs(HttpSession session,String lng,String lat){
        if(lng == null || lat == null){
            return new ErrorJsonRes(CodeDefined.EXCEPTION_CODE_PICTURE_ERROR,CodeDefined.getMessage(CodeDefined.EXCEPTION_CODE_PICTURE_ERROR));// 地图定位失败，请检查网络
        }
        JsonPioSimple jsonPioSimple = LbsHelper.saveStorePio(lng,lat);
        Store store = new Store();
        store.setStoreLbsid(jsonPioSimple.getId());
        return new SuccessJsonRes();
    }

    /**
     *  分类查询附近范围内的商铺
     * @return
     */
    @RequestMapping("/storeNearby")
    @ResponseBody
    public OperatorResponse storeNearby(HttpSession session,String lng,String lat,int page_index,String storeType){
        int radius = 100000; //默认查询50公里距离内的
        int page_size = 2;// 设置每一页返回的条数，这儿默认两条
        List<Store> stores = storeService.storeNearby(session, lng, lat, radius, page_index, page_size,storeType);
        ListSucRes listSucRes = new ListSucRes();
        listSucRes.setData(stores);
        return listSucRes;
    }

}
