package com.horadrim.talrasha.common.service.impl;

import com.horadrim.talrasha.common.dao.CommentLikeDao;
import com.horadrim.talrasha.common.dao.GenericDao;
import com.horadrim.talrasha.common.model.CommentLike;
import com.horadrim.talrasha.common.service.CommentLikeService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by Administrator on 2015/7/2.
 */
@Service("commentLikeService")
public class CommentLikeServiceImpl extends GenericServiceImpl<CommentLike,Integer> implements CommentLikeService {
    @Resource
    private CommentLikeDao commentLikeDao;
    @Override
    protected GenericDao<CommentLike, Integer> getGenericDao() {
        return commentLikeDao;
    }
}
