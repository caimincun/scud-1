package com.horadrim.talrasha.common.service.dto;

import com.horadrim.talrasha.common.model.CommentLike;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2015/7/1.
 */
public class CommentResultPojo {
    private CommentPojo comment;
    private List<CommentPojo> replys = new ArrayList<>();
    private long likeNum;
    private List<CommentLike> likes = new ArrayList<>();

    public long getLikeNum() {
        return likeNum;
    }

    public void setLikeNum(long likeNum) {
        this.likeNum = likeNum;
    }

    public List<CommentLike> getLikes() {
        return likes;
    }

    public void setLikes(List<CommentLike> likes) {
        this.likes = likes;
    }

    public CommentPojo getComment() {
        return comment;
    }

    public void setComment(CommentPojo comment) {
        this.comment = comment;
    }

    public List<CommentPojo> getReplys() {
        return replys;
    }

    public void setReplys(List<CommentPojo> replys) {
        this.replys = replys;
    }
}
