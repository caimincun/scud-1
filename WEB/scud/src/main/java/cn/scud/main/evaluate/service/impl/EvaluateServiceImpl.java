package cn.scud.main.evaluate.service.impl;

import cn.scud.main.evaluate.dao.EvaluateDao;
import cn.scud.main.evaluate.model.Evaluate;
import cn.scud.main.evaluate.service.EvaluateService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Administrator on 2015/7/7.
 */
@Service("/evaluateService")
public class EvaluateServiceImpl implements EvaluateService {

    @Resource
    private EvaluateDao evaluateDao;

    @Override
    public void saveEvaluate(Evaluate evaluate) {
        evaluateDao.saveEavluate(evaluate);
    }

    @Override
    public List<Evaluate> listEvaluate(String userToken) {
        return evaluateDao.listEvaluate(userToken);
    }
}
