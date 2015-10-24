package com.horadrim.talrasha.common.service;

import com.horadrim.talrasha.common.model.Comment;
import com.horadrim.talrasha.common.service.dto.CommentResultPojo;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2015/7/1.
 */
public interface CommentService extends GenericService<Comment,Integer> {
    /**
     * 查询某个用户的评论
     */
    List<CommentResultPojo> allComments(int canteenId ,int userId, int pageIndex , int size);

    /**
     * 查看所有评论
     */
    List<CommentResultPojo> allComments(int canteenId , int pageIndex , int size);

    /**
     *   查看某一条说说的详细评论
     */
    List<Map<String,Object>> listCommentDetail(int currentPage,int size,int id);

    int countCommentDetail(int replay_id);
}
