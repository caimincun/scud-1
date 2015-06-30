package cn.scud.main.order.controller;

import cn.scud.commoms.response.ObjSucRes;
import cn.scud.commoms.response.OperatorResponse;
import cn.scud.main.order.model.Order;
import cn.scud.main.order.service.OrderService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;

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
     * @param order
     * @return
     */
    @RequestMapping("/saveOrder")
    @ResponseBody
    public OperatorResponse saveOrder(Order order){


        ObjSucRes objSucRes = new ObjSucRes();
        objSucRes.setData(order);
        return objSucRes;
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
        return "success";
    }
}
