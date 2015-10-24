package com.horadrim.talrasha.common.service.impl;

import com.horadrim.talrasha.common.dao.CanteenMessageDao;
import com.horadrim.talrasha.common.dao.GenericDao;
import com.horadrim.talrasha.common.model.CanteenMessage;
import com.horadrim.talrasha.common.model.Vegetable;
import com.horadrim.talrasha.common.service.CanteenMessageService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2015/7/10.
 */
@Service("canteenMessage")
public class CanteenMessageServcieImpl extends GenericServiceImpl<CanteenMessage,Integer> implements CanteenMessageService {
    @Resource
    private CanteenMessageDao canteenMessageDao;
    @Override
    protected GenericDao<CanteenMessage, Integer> getGenericDao() {
        return canteenMessageDao;
    }

    @Override
    public List<Map<String, Object>> countNotReadMsg(int canteenId) {
        final String sql ="SELECT count(id) AS number , message_type AS messageType" +
                " FROM qc_canteen_message WHERE canteen_id = "+canteenId+" AND is_read = 0 GROUP BY message_type";
        return listFieldBySQL(sql,null);
    }


}
