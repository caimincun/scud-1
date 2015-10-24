package com.horadrim.talrasha.common.service.impl;

import com.horadrim.talrasha.common.dao.ConsumeHistoryDao;
import com.horadrim.talrasha.common.dao.GenericDao;
import com.horadrim.talrasha.common.model.ConsumeHistory;
import com.horadrim.talrasha.common.service.ConsumeHistoryService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2015/6/4.
 * 消费历史实现类
 */
@Service("consumeHistoryService")
public class ConsumeHistoryServiceImpl extends GenericServiceImpl<ConsumeHistory,Integer> implements ConsumeHistoryService {
    @Resource
    private ConsumeHistoryDao consumeHistoryDao;

    @Override
    protected GenericDao<ConsumeHistory, Integer> getGenericDao() {
        return consumeHistoryDao;
    }

    @Override
    public List<Map<String, Object>> queryConsumePage(int id, Integer accountType, Integer consumeType, int currentPage, int size) {
        StringBuffer hql = new StringBuffer("select c.*,q.nickname from qc_consume_history c");
        hql.append(" left join qc_user q on c.user_id=q.id");
        hql.append(" where q.canteen_id ="+id);
        if(null != accountType && accountType != 5){
            hql.append(" and account_type ="+accountType);
        }
        if( null != consumeType && consumeType!= 5){
            hql.append(" and account_type ="+consumeType);
        }
        hql.append(" order by consume_time desc");
        List<Map<String,Object>> orderResult = consumeHistoryDao.listFieldBySQL(hql.toString(),currentPage*size,size,null);

        return orderResult;
    }




}
