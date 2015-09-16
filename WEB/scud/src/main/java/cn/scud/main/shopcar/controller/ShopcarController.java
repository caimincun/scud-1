package cn.scud.main.shopcar.controller;

import cn.scud.commoms.CodeDefined;
import cn.scud.commoms.response.*;
import cn.scud.main.shopcar.model.Shopcar;
import cn.scud.main.shopcar.service.ShopcarService;
import cn.scud.utils.StreamSerializer;
import org.springframework.expression.spel.ast.OpOr;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Created by Administrator on 2015/9/16.
 */
@Controller
@RequestMapping("/shopcar")
public class ShopcarController {

    @Resource
    private ShopcarService shopcarService;

    /**
     * 添加购物车 ，暂时不返回东西 ，返回数据需要商量
     *
     * @param request
     * @return
     */
    @RequestMapping("/saveShopcar")
    @ResponseBody
    public OperatorResponse saveShopcar(HttpServletRequest request) {
        Shopcar shopcar = null;
        try {
            shopcar = StreamSerializer.streamSerializer(request.getInputStream(), Shopcar.class);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new ErrorJsonRes(CodeDefined.EXCEPTION_CODE_DATA_ERROR, CodeDefined.getMessage(CodeDefined.EXCEPTION_CODE_DATA_ERROR));
        }

        return new SuccessJsonRes();

    }

    /**
     *  查询个人所有商铺购物车
     * @return
     */
    @RequestMapping("/listShopcar")
    public OperatorResponse listShopcar(HttpSession session){

        ListSucRes listSucRes =new ListSucRes();
        return listSucRes;
    }

    /**
     * 查询用户在某一个商铺的购物车信息
     * @param session
     * @param storeToken
     * @return
     */
    public OperatorResponse listShopcarByStken(HttpSession session,String storeToken){

        ListSucRes listSucRes = new ListSucRes();
        return listSucRes;
    }
}
