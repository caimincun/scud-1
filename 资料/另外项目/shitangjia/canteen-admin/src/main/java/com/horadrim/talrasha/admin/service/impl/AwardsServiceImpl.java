package com.horadrim.talrasha.admin.service.impl;

import com.horadrim.talrasha.admin.dao.AwardsDao;
import com.horadrim.talrasha.admin.model.Awards;
import com.horadrim.talrasha.admin.service.AwardsService;
import com.horadrim.talrasha.common.dao.GenericDao;
import com.horadrim.talrasha.common.service.impl.GenericServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Administrator on 2015/6/16.
 */
@Service("awardsService")
public class AwardsServiceImpl extends GenericServiceImpl<Awards,Integer> implements AwardsService {
    @Resource
    private AwardsDao awardsDao;
    @Override
    protected GenericDao<Awards, Integer> getGenericDao() {
        return awardsDao;
    }

    @Override
    public Long countNotUse() {
        final String hql = "SELECT COUNT(*) FROM Awards WHERE isUse=0";
        return count(hql,null);
    }

    @Override
    public Awards randAward() {
        final String hql =" FROM Awards WHERE isUse=0 order by rand()";
        final String sql ="SELECT * FROM qc_awards WHERE isuse=0 order by rand() limit 1";
        List<Awards> awardses = listBySQL(sql,null);
        return awardses.size()>0?awardses.get(0):null;
    }
}
