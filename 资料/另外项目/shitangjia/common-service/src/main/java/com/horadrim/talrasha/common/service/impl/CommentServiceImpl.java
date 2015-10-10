package com.horadrim.talrasha.common.service.impl;

import com.horadrim.talrasha.common.dao.CommentDao;
import com.horadrim.talrasha.common.dao.CommentLikeDao;
import com.horadrim.talrasha.common.dao.GenericDao;
import com.horadrim.talrasha.common.model.Comment;
import com.horadrim.talrasha.common.model.CommentLike;
import com.horadrim.talrasha.common.service.CommentService;
import com.horadrim.talrasha.common.service.dto.CommentPojo;
import com.horadrim.talrasha.common.service.dto.CommentResultPojo;
import com.horadrim.talrasha.common.util.MapToPojoUtil;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2015/7/1.
 */
@Service("commentService")
public class CommentServiceImpl extends GenericServiceImpl<Comment,Integer> implements CommentService {
    @Resource
    private CommentDao commentDao;
    @Resource
    private CommentLikeDao commentLikeDao;
    @Override
    protected GenericDao<Comment, Integer> getGenericDao() {
        return commentDao;
    }
    @SuppressWarnings("unchecked")
    @Override
    public List<CommentResultPojo> allComments(int canteenId,int userId, int pageIndex, int size) {
        String sql="SELECT c.id AS commentId,c.content,c.comment_time AS commentTime ," +
                "u.nickname AS nickName,u.head_img AS headImg,u.id AS userId,c.is_top AS isTop FROM qc_comment c " +
                "LEFT JOIN qc_user u ON c.user_id = u.id WHERE c.reply_id = ? AND c.canteen_id =?  ";
        String queryLike="FROM CommentLike WHERE commentId = ? ORDER BY id DESC";
        List<Map<String,Object>>  list = listFieldBySQL((userId>0?sql+(" AND u.id="+userId):sql)+" ORDER BY isTop DESC,commentId DESC ",
                (pageIndex-1)*size,size,new Object[]{0,canteenId});
        List<CommentResultPojo> results = new ArrayList<>();
        for (Map<String,Object> map : list){
            CommentResultPojo commentResultPojo = new CommentResultPojo();
            //取得父级评论
            CommentPojo result = new CommentPojo();
            MapToPojoUtil.mapToPojo(map,result);
            commentResultPojo.setComment(result);
            //取得父级评论的回复
            List<Map<String,Object>>  replyMaps = listFieldBySQL(sql,new Object[]{result.getCommentId(),canteenId});
            for (Map<String,Object> replyMap : replyMaps){
                CommentPojo reply = new CommentPojo();
                MapToPojoUtil.mapToPojo(replyMap,reply);
                commentResultPojo.getReplys().add(reply);
            }
            //取得评论的点赞
            List<CommentLike> likes = commentLikeDao.list(queryLike,0,10,new Object[]{result.getCommentId()});
            commentResultPojo.setLikes(likes);
            commentResultPojo.setLikeNum(likes.size()==10?commentLikeDao.count():likes.size());
            results.add(commentResultPojo);
        }
        return results;
    }

    @Override
    public List<CommentResultPojo> allComments(int canteenId, int pageIndex, int size) {
        return allComments(canteenId,0,pageIndex,size);
    }

    /**
     *  查看某条说说的所有评论
     * @param currentPage
     * @param size
     * @param id
     * @return
     */
    @Override
    public  List<Map<String,Object>> listCommentDetail(int currentPage, int size, int id) {
        String sql = "select c.*,q.nickname from qc_comment c left join qc_user q on c.user_id = q.id where reply_id ="+id;
        List<Map<String,Object>> mapList = commentDao.listFieldBySQL(sql,currentPage*size,size,null);
        return mapList;
    }

    @Override
    public int countCommentDetail(int replay_id) {
        String hql = "select count(id) from Comment where replyId ="+replay_id;
        return commentDao.count(hql,null).intValue();
    }


    @Override
    public void delete(Integer id) {
        Comment comment = get(id);
        delete(comment);
    }

    @Override
    public void delete(Comment comment) {
        if (comment==null)
            return;
        if (comment.getReplyId()==0){
            //父节点 要先删除子节点
            String hql = "FROM Comment WHERE replyId = "+comment.getId();
            List<Comment> comments = list(hql,null);
            for (Comment c : comments)
                delete(c);
        }
        super.delete(comment);
    }
}
