package cn.scud.main.evaluate.controller;

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
}
