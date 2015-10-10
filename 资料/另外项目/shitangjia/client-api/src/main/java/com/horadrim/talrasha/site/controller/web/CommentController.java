package com.horadrim.talrasha.site.controller.web;

import com.horadrim.talrasha.common.CodeDefined;
import com.horadrim.talrasha.common.CommonParamDefined;
import com.horadrim.talrasha.common.model.Comment;
import com.horadrim.talrasha.common.model.CommentLike;
import com.horadrim.talrasha.common.model.User;
import com.horadrim.talrasha.common.response.json.*;
import com.horadrim.talrasha.common.service.CommentLikeService;
import com.horadrim.talrasha.common.service.CommentService;
import com.horadrim.talrasha.common.service.UserService;
import com.horadrim.talrasha.common.service.dto.CommentPojo;
import com.horadrim.talrasha.common.util.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.Date;

/**
 * Created by Administrator on 2015/7/1.
 * 用户吐槽评论交流，你说我说模块
 */
@Controller
@RequestMapping("/comment")
public class CommentController {
    @Resource
    private CommentService commentService;
    @Resource
    private CommentLikeService commentLikeService;
    @Resource
    private UserService userService;
    @RequestMapping("/test")
    public void test(HttpSession session){
       /* session.setAttribute(CommonParamDefined.CANTEEN_ID,1);
        session.setAttribute(CommonParamDefined.USERID,3);*/
    }
    @RequestMapping("/toCommentPage")
    public String toCommentPage(){
        return "comment";
    }

    @RequestMapping("/list")
    @ResponseBody
    public AbstractJsonRes listComments(HttpSession session,int pageIndex,int size){
        if (pageIndex<1||size>5)
            return new ErrorJsonRes(CodeDefined.COMMON_PARAMS_ERROR);
        ListSucRes res = new ListSucRes();
        res.setData(commentService.allComments((Integer) session.getAttribute(CommonParamDefined.CANTEEN_ID),pageIndex,size));
        return res;
    }

    @RequestMapping("/owner")
    @ResponseBody
    public AbstractJsonRes listOwnerComments(HttpSession session,int pageIndex,int size){
        ListSucRes res = new ListSucRes();
        res.setData(commentService.allComments((Integer) session.getAttribute(CommonParamDefined.CANTEEN_ID),
                (Integer)session.getAttribute(CommonParamDefined.USERID),pageIndex,size));
        return res;
    }


    @RequestMapping("/addReply")
    @ResponseBody
    public AbstractJsonRes addReply(HttpSession session,String content,Integer replyId){
        int userId =(int) session.getAttribute(CommonParamDefined.USERID);
        User user = userService.get(userId);
        if (user==null){
            return new ErrorJsonRes(CodeDefined.COMMON_NOT_AUTH);
        }
        if (StringUtils.isBlank(content)){
            return new ErrorJsonRes(4,"评论不能为空");
        }
        if (content.length()>255){
            return new ErrorJsonRes(4,"短评不能超过255个字符");
        }
        Comment comment = new Comment();
        if (replyId!=null&& replyId !=0){
            Comment commentParent = commentService.get(replyId);
            if (commentParent==null)
                return new ErrorJsonRes(4,"请选择短评进行回复");
            comment.setReplyId(commentParent.getId());
        }else {
            comment.setReplyId(0);
        }
        comment.setCanteenId((Integer) session.getAttribute(CommonParamDefined.CANTEEN_ID));
        comment.setUserId(userId);
//        comment.setCanteenId(1);
//        comment.setUserId(1);
        comment.setCommentTime(new Date());
        comment.setContent(StringUtils.htmlStringToEscapeChars(content));
        commentService.save(comment);
        CommentPojo pojo = new CommentPojo();
        BeanUtils.copyProperties(comment,pojo);
        pojo.setNickName(user.getNickName());
        pojo.setHeadImg(user.getHeadImg());
        GetObjSucRes res = new GetObjSucRes();
        res.setData(pojo);
        return res;
    }
    @RequestMapping("/addComment")
    @ResponseBody
    public AbstractJsonRes addComment(String content,HttpSession session){
        if (StringUtils.isBlank(content)){
            return new ErrorJsonRes(4,"短评不能为空");
        }
        if (content.length()>255){
            return new ErrorJsonRes(4,"短评不能超过255个字符");
        }
        Comment comment = new Comment();
        comment.setCanteenId((Integer) session.getAttribute(CommonParamDefined.CANTEEN_ID));
        comment.setUserId((Integer) session.getAttribute(CommonParamDefined.USERID));
        comment.setReplyId(0);
        comment.setContent(content);
        comment.setCommentTime(new Date());
        commentService.save(comment);
        return new SuccessJsonRes();
    }


    @RequestMapping("/delete")
    @ResponseBody
    public AbstractJsonRes delete(int commentId,HttpSession session){
        Comment comment = commentService.get(commentId);
        if (comment.getUserId()!=(int)session.getAttribute(CommonParamDefined.USERID)){
            return new ErrorJsonRes(4,"不能删除");
        }
        commentService.delete(comment);
        return new SuccessJsonRes();
    }
    @RequestMapping("/like")
    @ResponseBody
    public AbstractJsonRes like(int commentId,HttpSession session){
        User user = userService.get((Integer) session.getAttribute(CommonParamDefined.USERID));
        if(user==null){
            return new ErrorJsonRes(CodeDefined.COMMON_NOT_AUTH);
        }
        Comment comment = commentService.get(commentId);
        if (comment==null){
            return new ErrorJsonRes(CodeDefined.COMMON_PARAMS_ERROR);
        }
        if (comment.getUserId()==user.getId()){
            return new ErrorJsonRes(4,"您不能自己赞自己的点评");
        }
        if (commentLikeService.count("SELECT COUNT(*) FROM CommentLike WHERE userId="
                +user.getId()+" AND commentId="+commentId,null)>=1){
            return new ErrorJsonRes(4,"您已经对该条点评点过赞了");
        }
        CommentLike commentLike = new CommentLike();
        commentLike.setUserId(user.getId());
        commentLike.setNickName(user.getNickName());
        commentLike.setCommentId(commentId);
        commentLikeService.save(commentLike);
        return new SuccessJsonRes();
    }
}
