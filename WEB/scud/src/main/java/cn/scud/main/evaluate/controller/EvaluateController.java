package cn.scud.main.evaluate.controller;

import cn.scud.commoms.CodeDefined;
import cn.scud.commoms.response.ErrorJsonRes;
import cn.scud.commoms.response.ListSucRes;
import cn.scud.commoms.response.OperatorResponse;
import cn.scud.commoms.response.SuccessJsonRes;
import cn.scud.main.evaluate.model.Evaluate;
import cn.scud.main.evaluate.service.EvaluateService;
import cn.scud.utils.StreamSerializer;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

/**
 * Created by Administrator on 2015/7/7.
 */
@Controller
@RequestMapping("/evaluate")
public class EvaluateController {

    @Resource
    private EvaluateService evaluateService;


    /**
     * 保存评论
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping("/saveEvaluate")
    @ResponseBody
    public OperatorResponse saveEvaluate(HttpServletRequest request) throws Exception {
        Evaluate evaluate =  StreamSerializer.streamSerializer(request.getInputStream(), Evaluate.class);
        evaluateService.saveEvaluate(evaluate);
        SuccessJsonRes successJsonRes = new SuccessJsonRes();
        return successJsonRes;
    }

    /**
     * 根据userToken 获取某个对象所有的 评论
     * @param userToken
     * @return
     */
    @RequestMapping("/listEvaluate")
    @ResponseBody
    public OperatorResponse listEvaluate(String userToken){
        if(null == userToken || "".equals(userToken)){
            return new ErrorJsonRes(CodeDefined.USER_TOKEN_NULL,CodeDefined.getMessage(CodeDefined.USER_TOKEN_NULL));
            //10002，用户userToken 为空
        }
        List<Evaluate> evaluateList = evaluateService.listEvaluate(userToken);
        ListSucRes listSucRes = new ListSucRes();
        listSucRes.setData(evaluateList);
        return  listSucRes;
    }

}
