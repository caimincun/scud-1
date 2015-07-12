package cn.scud.main.evaluate.service;

import cn.scud.main.evaluate.model.Evaluate;

import java.util.List;

/**
 * Created by Administrator on 2015/7/7.
 */
public interface EvaluateService {
    /**
     * 保存评价
     */
    void saveEvaluate(Evaluate evaluate);

    /**
     * 根据userToken  获取 某个对象被评论的信息
     * @param userToken
     * @return
     */
    List<Evaluate> listEvaluate(String userToken);
}
