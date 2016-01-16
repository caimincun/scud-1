package com.horadrim.talrasha.common.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by Administrator on 2015/7/2.
 */
@Table(name = "qc_comment_like")
@Entity
public class CommentLike extends AbstractDomain{

    private int commentId;
    private int userId;
    private String nickName;

    @Column(name = "comment_id",nullable = false)
    public int getCommentId() {
        return commentId;
    }
    @Column(name = "user_id",nullable = false)
    public int getUserId() {
        return userId;
    }
    @Column(name = "nickname",nullable = false)
    public String getNickName() {
        return nickName;
    }

    public void setCommentId(int commentId) {
        this.commentId = commentId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }
}
