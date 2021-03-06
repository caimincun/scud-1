package cn.scud.main.receipt.controller;

import cn.scud.commoms.CodeDefined;
import cn.scud.commoms.CommonParamDefined;
import cn.scud.commoms.response.*;
import cn.scud.main.receipt.model.Receipt;
import cn.scud.main.receipt.service.ReceiptService;
import cn.scud.utils.StreamSerializer;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by Administrator on 2015/9/27.
 */
@Controller
@RequestMapping("/receipt")
public class ReceiptController {

    @Resource
    private ReceiptService receiptService;

    /**
     * 保存收货信息
     */
    @RequestMapping("/saveReceipt")
    @ResponseBody
    public OperatorResponse saveReceipt(HttpServletRequest request){
        Receipt receipt = null;
        try {
            receipt =  StreamSerializer.streamSerializer(request.getInputStream(), Receipt.class);
            System.out.println("receipt:"+receipt);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new ErrorJsonRes(CodeDefined.EXCEPTION_CODE_DATA_ERROR, CodeDefined.getMessage(CodeDefined.EXCEPTION_CODE_DATA_ERROR));
        }
        receipt.setUserToken((String)request.getSession().getAttribute(CommonParamDefined.USER_TOKEN));
        receiptService.saveReceipt(receipt);
        return  new SuccessJsonRes();
    }

    /**
     * 查询客户相关的 收货地址信息
     */
    @RequestMapping("/listReceipt")
    @ResponseBody
    public OperatorResponse listReceipt(HttpSession session){
        String userToken = session.getAttribute(CommonParamDefined.USER_TOKEN).toString();
        List<Receipt> receiptList = receiptService.listReceipt(userToken);
        ListSucRes listSucRes = new ListSucRes();
        listSucRes.setData(receiptList);
        return  listSucRes;
    }

    /**
     * 删除选中的收货信息 ，用 Id 标识
     */
    @RequestMapping("/delReceipt")
    @ResponseBody
    public OperatorResponse delReceipt(String id){
        System.out.println(id);
        receiptService.delReceipt(Integer.parseInt(id));
        return new SuccessJsonRes();
    }


    /**
     *  查询订单详情： 展示订单地址， 根据 orderToken
     */
    @RequestMapping("/showOrderReceipt")
    @ResponseBody
    public OperatorResponse showOrderReceipt(String orderToken){
        Receipt receipt = receiptService.getReceipt(orderToken);
        ObjSucRes objSucRes = new ObjSucRes();
        objSucRes.setData(receipt);
        return objSucRes;
    }
}
