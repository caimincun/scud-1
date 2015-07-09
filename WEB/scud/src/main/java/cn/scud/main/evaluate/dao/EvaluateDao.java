package cn.scud.main.evaluate.dao;

import cn.scud.main.evaluate.model.Evaluate;

import java.util.List;

/**
 * Created by Administrator on 2015/7/7.
 */
public interface EvaluateDao {
    /**
     * 保存 Evaluate
     */
    void saveEavluate(Evaluate evaluate);

    /**
     * 获取 被评论者所有的信息
     * @param userToken
     * @return
     */
    List<Evaluate> listEvaluate(String userToken);
}
