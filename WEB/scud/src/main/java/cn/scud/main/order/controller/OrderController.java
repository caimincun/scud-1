package cn.scud.main.order.controller;

import cn.scud.commoms.CodeDefined;
import cn.scud.commoms.CommonParamDefined;
import cn.scud.commoms.response.*;
import cn.scud.main.order.model.Order;
import cn.scud.main.order.service.OrderService;
import cn.scud.utils.BosHelper;
import cn.scud.utils.StreamSerializer;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2015/6/25.
 */
@Controller()
@RequestMapping("/order")
public class OrderController {

    @Resource
    private OrderService orderService;

    /**
     * 添加订单信息
     * @return
     */
    @RequestMapping("/saveOrder")
    @ResponseBody
    public OperatorResponse saveOrder(HttpServletRequest request) throws Exception{
        Order order = StreamSerializer.streamSerializer(request.getInputStream(),Order.class);
        orderService.saveOrder(order);
        ObjSucRes objSucRes = new ObjSucRes();
        objSucRes.setData(order);
        return objSucRes;
    }


    /**
     * 根据用户userToken获取用户订单
     * @param userToken
     * @return
     */
    @RequestMapping("/listOrdersByToken")
    @ResponseBody
    public OperatorResponse listOrdersByToken(String userToken){
        if(null == userToken || "".equals(userToken)){
            return new ErrorJsonRes(CodeDefined.USER_TOKEN_NULL,CodeDefined.getMessage(CodeDefined.USER_TOKEN_NULL));
            //10002，用户userToken 为空
        }
        List<Order> orders = orderService.listOrdersByToken(userToken);
        ListSucRes listSucRes = new ListSucRes();
        listSucRes.setData(orders);
        return listSucRes;
    }

    /**
     * 根据orderToken 获取订单信息
     * @param orderToken
     * @return
     */
    @RequestMapping("/getOrderByToken")
    @ResponseBody
    public OperatorResponse getOrderByToken(String orderToken){
        if(null == orderToken || "".equals(orderToken)){
            return new ErrorJsonRes(CodeDefined.ORDER_TOKEN_NULL,CodeDefined.getMessage(CodeDefined.ORDER_TOKEN_NULL));
            //30002，订单token 为空
        }
        Order order = orderService.getOrderByToken(orderToken);
        ObjSucRes objSucRes = new ObjSucRes();
        objSucRes.setData(order);
        return objSucRes;
    }

    /**
     * 标记 order 完成
     * @param orderToken
     * @return
     */
    @RequestMapping("/setOrderComplete")
    @ResponseBody
    public  OperatorResponse setOrderComplete(String orderToken){
        if(null == orderToken || "".equals(orderToken)){
            return new ErrorJsonRes(CodeDefined.ORDER_TOKEN_NULL,CodeDefined.getMessage(CodeDefined.ORDER_TOKEN_NULL));
            //30002，订单token 为空
        }
        orderService.setOrderComplete(orderToken);
        SuccessJsonRes successJsonRes = new SuccessJsonRes();
        return successJsonRes;
    }


    /**
     *  测试图片上传到百度存储
     *  测试图片路径
     *  http://scud-images.bj.bcebos.com/upload/%E9%9D%92%E8%8F%9C.jpg
     * @param img
     * @return
     */
    @RequestMapping("/testup")
    @ResponseBody
    public String testUpImage(MultipartFile img){
        System.out.println("img:"+img.getSize());
        String path = null;
        try {
            BosHelper bosHelper = new BosHelper();
            SimpleDateFormat sdf = new SimpleDateFormat("yyMMddHHmmss");
            String newName = sdf.format(new Date());
            // 这个path 是图片上传到百度bos的返回路径，如：/upload/150701105336， 加上图片访问前缀"http://scud-images.bj.bcebos.com";就可以进行访问了
            path = bosHelper.putFile(img.getInputStream(), newName, img.getSize(), img.getContentType());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return path;
    }


}
