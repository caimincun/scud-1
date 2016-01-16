package com.horadrim.talrasha.common.dao.impl;

import com.horadrim.talrasha.common.dao.CommentLikeDao;
import com.horadrim.talrasha.common.dao.GenericHibernateDao;
import com.horadrim.talrasha.common.model.CommentLike;
import org.springframework.stereotype.Repository;

/**
 * Created by Administrator on 2015/7/2.
 */
@Repository("commentLikeDao")
public class CommentLikeDaoImpl extends GenericHibernateDao<CommentLike,Integer> implements CommentLikeDao {
}
