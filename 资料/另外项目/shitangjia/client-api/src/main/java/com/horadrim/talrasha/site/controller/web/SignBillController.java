package com.horadrim.talrasha.site.controller.web;


import com.horadrim.talrasha.common.CodeDefined;
import com.horadrim.talrasha.common.CommonParamDefined;
import com.horadrim.talrasha.common.model.*;
import com.horadrim.talrasha.common.model.Collection;
import com.horadrim.talrasha.common.response.json.AbstractJsonRes;
import com.horadrim.talrasha.common.response.json.ErrorJsonRes;
import com.horadrim.talrasha.common.response.json.GetObjSucRes;
import com.horadrim.talrasha.common.response.json.SuccessJsonRes;
import com.horadrim.talrasha.common.service.*;
import com.horadrim.talrasha.common.util.BaeSmsUtil;
import com.horadrim.talrasha.common.util.CheckCodeUtil;
import com.horadrim.talrasha.common.util.StringUtils;
import com.horadrim.talrasha.site.controller.result.GetChainNodeRes;
import com.horadrim.talrasha.site.exception.DefaultExceptionHandler;
import com.horadrim.talrasha.site.service.ClientCollectionService;
import com.horadrim.talrasha.site.service.ClientProductService;
import com.horadrim.talrasha.site.service.ClientUserService;
import com.horadrim.talrasha.site.utils.OrderUtil;
import com.horadrim.talrasha.site.wechat.WeChat;
import com.horadrim.talrasha.site.wechat.bean.UserInfo;
import com.horadrim.talrasha.site.wechat.oauth.Pay;
import com.horadrim.talrasha.site.wechat.util.ConfKit;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.RandomStringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.util.*;

/**
 * Created by Administrator
 * 用户控制
 */
@Controller
@RequestMapping("/signbill")
public class SignBillController extends DefaultExceptionHandler {

    @Resource(name="userService")
    private UserService userService;

    @Resource(name="canteenService")
    private CanteenService canteenService;

    @Resource(name="applicationService")
    private ApplicationService applicationService;
    @Resource(name="signChainService")
    private SignChainService signChainService;

    @Resource(name="signChainNodeService")
    private SignChainNodeService signChainNodeService;

    @Resource(name="applicationSignChainService")
    private ApplicationSignChainService applicationSignChainService;
    @Resource(name="approverService")
    private ApproverService approverService;

    @Resource(name="applicationSignChainNodeService")
    private ApplicationSignChainNodeService applicationSignChainNodeService;

    @RequestMapping("/toSignBillPage")
   public ModelAndView toSignBillPage(){
       return new ModelAndView("qiandan");
   }
    @RequestMapping("/toFabuSignBillPage")
    public ModelAndView toFabuSignBillPage(HttpSession session){
        ModelAndView model=new ModelAndView("fabu_qiandan");
        List<SignChain> signChains=signChainService.getSignChainByCanteenId((Integer)session.getAttribute(CommonParamDefined.CANTEEN_ID));
        model.addObject("signchans",signChains);
        return  model;
    }
    @RequestMapping("/toSignBillListPage")
    public ModelAndView toSignBillListPage(){
        return new ModelAndView("qiandan_list");
    }
    @RequestMapping("/toWaitSignBillPage")
    public ModelAndView toWaitSignBillPage(){
        return new ModelAndView("wait_shenpi_qiandan");
    }

    @RequestMapping("/getSignNodes")
    @ResponseBody
    public AbstractJsonRes changeSafeAmount(HttpSession session,Integer signChainId){
       // User user=getUserFromSession(session);
        //user.setSafeAmount(safeAmount);
        List<SignChainNode> signChainNodes=signChainService.get(signChainId).getSignChainNodes();
        List<Approver> approvers=approverService.findAll();
        GetChainNodeRes res=new GetChainNodeRes();
        res.setApprovers(approvers);
        res.setNodes(signChainNodes);
        return res;
    }

}
