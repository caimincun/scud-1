package com.horadrim.talrasha.admin.controller.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.horadrim.talrasha.admin.controller.result.DataTableRes;
import com.horadrim.talrasha.admin.model.CanteenUser;
import com.horadrim.talrasha.common.CodeDefined;
import com.horadrim.talrasha.common.CommonParamDefined;
import com.horadrim.talrasha.common.model.Comment;
import com.horadrim.talrasha.common.response.json.AbstractJsonRes;
import com.horadrim.talrasha.common.response.json.ErrorJsonRes;
import com.horadrim.talrasha.common.response.json.ListSucRes;
import com.horadrim.talrasha.common.service.CommentService;
import org.hibernate.loader.custom.Return;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2015/7/16.
 */
@Controller
@RequestMapping("/comment")
public class CommentController {

    @Resource
    private CommentService commentService;

    @RequestMapping("/list")
    @ResponseBody
    public AbstractJsonRes listComments(HttpSession session,int pageIndex,int size){
        CanteenUser user = (CanteenUser) session.getAttribute(CommonParamDefined.USER);
        if (pageIndex<1||size>20)
            return new ErrorJsonRes(CodeDefined.COMMON_PARAMS_ERROR);
        ListSucRes res = new ListSucRes();
        res.setData(commentService.allComments(user.getCanteenId(), pageIndex, size));
        return res;
    }
    @RequestMapping("/toCommentPage")
    public String toCommentPage(){
        return "comment";
    }

    /**
     * 置顶功能
     * @param id
     * @return
     */
    @RequestMapping("/zhiding")
    public ModelAndView zhiding(String id){
        String hql ="update Comment set isTop = 1 where id ="+id;
        commentService.executeUpdate(hql,null);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/canteenUser/choicepage?id=18");
        return modelAndView;
    }

    /**
     * 取消置顶
     * @param id
     * @return
     */
    @RequestMapping("/quxiaozhiding")
    public ModelAndView quxiaozhiding(String id){
        String hql ="update Comment set isTop = 0 where id ="+id;
        commentService.executeUpdate(hql,null);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/canteenUser/choicepage?id=18");
        return modelAndView;
    }

    /**
     * 查看评论的详情
     * @return
     */
    @RequestMapping("/detailComment")
    @ResponseBody
    public DataTableRes detailComment(@RequestParam String aoData,int replay_id){
        String sEcho = null;
        int iDisplayStart = 0; // 起始索引
        int iDisplayLength = 0; // 每页显示的行数
        ObjectMapper mapper = new ObjectMapper();
        List<Map<String,Object>> mapList =null;
        try {
            mapList = mapper.readValue(aoData,mapper.getTypeFactory().constructParametricType(List.class, Map.class));
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (mapList==null){
            //错误处理。。。
            return null;
        }
        for (Map obj : mapList){
            if (obj.get("name").equals("sEcho"))
                sEcho = obj.get("value").toString();

            if (obj.get("name").equals("iDisplayStart"))
                iDisplayStart = (int) obj.get("value");

            if (obj.get("name").equals("iDisplayLength"))
                iDisplayLength = (int) obj.get("value");
        }
        int countComment = commentService.countCommentDetail(replay_id);

        List<Map<String,Object>> commentList = commentService.listCommentDetail(iDisplayStart / iDisplayLength, iDisplayLength,replay_id);
        List<String[]> lst = new ArrayList<String[]>();
        for(Map<String,Object> comment:commentList){
            String nameTemp = "";

            if(null == comment.get("nickname")){
                String[]  dts  = {"官方客服",comment.get("content").toString(),comment.get("comment_time").toString()};
                lst.add(dts);
            }else {
                String[]  dts = {comment.get("nickname").toString(), comment.get("content").toString(), comment.get("comment_time").toString()};
                lst.add(dts);
            }

        }

        DataTableRes res = new DataTableRes();
        res.setsEcho(sEcho);
        res.setiTotalRecords(countComment);
        res.setiTotalDisplayRecords(countComment);
        res.setAaData(lst);
        return res;
    }

    /**
     * 后台添加评论功能
     */
    @RequestMapping("/addComment")
    @ResponseBody
    public ModelAndView addComment(String content,int id,HttpSession session) throws UnsupportedEncodingException {
        CanteenUser canteenUser = (CanteenUser)session.getAttribute(CommonParamDefined.USER);
        Comment comment = new Comment();
        comment.setContent(java.net.URLDecoder.decode(content , "UTF-8"));
        comment.setCanteenId(canteenUser.getCanteenId());
        comment.setCommentTime(new Date());
        comment.setReplyId(id);
        comment.setIsTop(1);
        System.out.println(comment);
        commentService.save(comment);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/canteenUser/choicepage?id=18");
        return modelAndView;
    }


}
