package com.horadrim.talrasha.common.dao.impl;

import com.horadrim.talrasha.common.dao.CommentDao;
import com.horadrim.talrasha.common.dao.GenericHibernateDao;
import com.horadrim.talrasha.common.model.Comment;
import org.springframework.stereotype.Repository;

/**
 * Created by Administrator on 2015/7/1.
 */
@Repository("commentDao")
public class CommentDaoImpl extends GenericHibernateDao<Comment,Integer> implements CommentDao {
}
