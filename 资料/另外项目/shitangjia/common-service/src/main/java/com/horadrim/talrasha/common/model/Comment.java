package com.horadrim.talrasha.common.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

/**
 * Created by Administrator on 2015/7/1.
 * 用户吐槽评论
 */
@Table(name = "qc_comment")
@Entity
public class Comment extends AbstractDomain {

    private int userId;         //用户id
    private int canteenId;      //食堂id
    private String content;     //评论内容
    private Date commentTime; //评论时间
    private int replyId;        //回复id 默认为0，1
    private int isTop;          //是否置顶 0不置顶 1置顶
    @Column(name = "user_id")
    public int getUserId() {
        return userId;
    }
    @Column(name = "canteen_id",nullable = false)
    public int getCanteenId() {
        return canteenId;
    }
    @Column(name = "content",nullable = false)
    public String getContent() {
        return content;
    }
    @Column(name = "comment_time",nullable = false)
    public Date getCommentTime() {
        return commentTime;
    }
    @Column(name = "reply_id",nullable = false)
    public int getReplyId() {
        return replyId;
    }
    @Column(name = "is_top",nullable = false)
    public int getIsTop() {
        return isTop;
    }

    public void setIsTop(int isTop) {
        this.isTop = isTop;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setCanteenId(int canteenId) {
        this.canteenId = canteenId;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setCommentTime(Date commentTime) {
        this.commentTime = commentTime;
    }

    public void setReplyId(int replyId) {
        this.replyId = replyId;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "userId=" + userId +
                ", canteenId=" + canteenId +
                ", content='" + content + '\'' +
                ", commentTime=" + commentTime +
                ", replyId=" + replyId +
                ", isTop=" + isTop +
                '}';
    }
}
