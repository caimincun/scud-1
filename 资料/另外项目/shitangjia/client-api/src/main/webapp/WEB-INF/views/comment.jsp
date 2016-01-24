<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
  String path = request.getContextPath();
  String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
  <base href="<%=basePath%>">
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1，user-scalable=no">
  <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
  <title>${canteenName}</title>
  <!-- Bootstrap -->
  <link href="css/bootstrap.min.css" rel="stylesheet">
  <style type="text/css">

    .commentdiv{
      background-color: #eeeeee;
      margin-left: 4%;
    }
    .published{
      width: 100%;
      height: 6rem;
      border: 1px solid #F0AD4E;
    }
    .published_btn {
        width: 6rem;
        height: 4rem;
        font-size: 1.5rem;
        margin-top: 1rem;
    }
    .likes{
        color: #4E91C7;font-size: 10px ;margin-top: 1rem; margin-left: 0.3rem;
    }
  </style>
</head>
<body style="background-color: rgba(16, 16, 16, 0.28);">
<div style="width: 100%;height: 40px;background-color: gainsboro;position:absolute;position: fixed;
  left:0px;
  top:0px;
  z-index:9999;">
    <a href="user/toPersonCenterPage"><img src="image/back.gif" style="float:left;width:1.5rem;margin-top: 5px;margin-left: 2%"></a>
    <div style="width: 80%;text-align: center;float: right;margin-right: 8%">
        <span style="display: block;margin-top: 10px;font-size: 18px">你说我说</span>
    </div>
    <%--<a href="user/toIndexPage"><img src="image/home.png" class="home"></a>--%>
</div>
<div class="container" style="padding-top: 40px;">
    <div class="row" style="margin-top:15px">
    <div class="col-xs-8 ">
      <textarea class="published" placeholder="说点什么吧" id="context"></textarea>
    </div>
    <div class="col-xs-3">
      <input type="button" id="published_btn" value="发表" class="btn btn-warning published_btn">
    </div>
  </div>
  <div id="listComment">

  </div>
 <%--<!--一个评论-->
  <div class="row " style="margin-top:15px">
    <div class="col-xs-11  commentdiv">
      <div class="row">
        <div class="col-xs-3">
          <img src="http://qingcai-images.bj.bcebos.com/default/m01.gif" width="75px" height="75px">
        </div>
        <div class="col-xs-9">
          <div class="row" style="margin-top:10%">
            <div class="col-xs-11 ">
              王浩
            </div>
          </div>
          <div class="row">
            <div class="col-xs-11">
              发表时间：2015-07-06
            </div>
          </div>
        </div>
      </div>
      <div class="row">
        <div class="col-xs-12 ">
			 			<pre>这个菜很难吃啊！这个菜很难吃啊！这个菜很难吃啊！这个菜很难吃啊！这个菜很难吃啊！这个菜很难吃啊！
食堂态度也不好</pre>
        </div>
      </div>
      <div class="row" style="font-size:8px;">
        <div class="col-xs-4">评论(10)</div>
        <div class="col-xs-4">赞(10)</div>
      </div>
      <div class="row" class="likes">
        张三，李四，王五...等觉得很赞
      </div>
      <div class="row" style="margin-top:20px">
        <div class="col-xs-3 col-sm-3">
          <img src="img/m01.gif" width="50px" height="50px">
        </div>
        <div class="col-xs-8 col-sm-8">
          <pre style="float:left;margin-left:-20px;">我觉得大家说得很对</pre>
        </div>
      </div>

      <div class="row" style="margin-top:20px">

        <div class="col-xs-8 col-xs-offset-1 ">
          <pre style="float:right;margin-right:-20px;">我觉得大家说得很对ddddddd</pre>
        </div>

        <div class="col-xs-3 ">
          <img src="img/n02.gif" width="50px" height="50px">
        </div>
      </div>


      <div class="row" style="margin-top:20px">
        <div class="col-xs-3 ">
          <img src="img/m02.gif" width="50px" height="50px">
        </div>
        <div class="col-xs-8 ">
          <pre style="float:left;margin-left:-20px;">我觉得大家说得很对</pre>
        </div>
      </div>

      <div class="row" style="margin-top:20px">

        <div class="col-xs-8 col-xs-offset-1 ">
			 			<pre  style="float:right;margin-right:-20px;">我觉得大家说得很对
1.菜一直都不变,
2.工作人员态度不好</pre>
        </div>

        <div class="col-xs-3 ">
          <img src="img/n05.gif" width="50px" height="50px">
        </div>
      </div>

    </div> </div>
  <!--一个评论结束-->--%>
    <div style="height: 15px;"></div>
    <div>
        <button type="button" onclick="list()" id="getMoreBtn" class="btn btn-info btn-lg btn-block">&nbsp;&nbsp;更&nbsp;&nbsp;多&nbsp;&nbsp;</button>
    </div>
  <div style="height: 20px;"></div>
</div>

<!--模态框-->
<div class="modal fade" style="padding-top: 20%" id="myModal">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title">请输入您的回复</h4>
      </div>
      <div class="modal-body">
         <input type="text" class="form-control" id="reply_content" name="reply_content">
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
        <button type="button" class="btn btn-primary" id="replyBtn">提交</button>
      </div>
    </div><!-- /.modal-content -->
  </div><!-- /.modal-dialog -->
</div><!-- /.modal -->
<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<script src="js/jquery-2.1.1.js"></script>
<!-- Include all compiled plugins (below), or include individual files as needed -->
<script src="js/bootstrap.min.js"></script>
<script src="js/tool.js"></script>
<script>
  var pageIndex = 1;
  var size = 5;
  var replyId = 0;
  function list(){
      $.post('comment/list',{
          pageIndex:pageIndex,
          size:size
      },function(res){
          if(res.result==0){
              pageIndex +=1;
              var comments = res.data;
              var html="";
              if(comments.length<size){
                    $("#getMoreBtn").html("没有更多了!").attr("disabled", "disabled");
              }
              for(var i = 0 ; i<comments.length;i++) {
                  var commenPojo = comments[i];
                  var pcomment = commenPojo.comment;
                  var replys = commenPojo.replys;
                  var pcommentId = pcomment.commentId;
                  html += " <div class=\"row \" style=\"margin-top:15px\">" +
                  "    <div class=\"col-xs-11  commentdiv\">" +
                  "      <div class=\"row\">" +
                  "        <div class=\"col-xs-3\">" +
                  "          <img src=\"" + pcomment.headImg + "\" class=\"img-circle\" width=\"60px\" height=\"60px\">" +
                  "        </div>" +
                  "        <div class=\"col-xs-9\" style=\"margin:-7px 0 12px -10px;\">" +
                  "          <div class=\"row\" style=\"margin-top:10%\">" +
                  "            <div class=\"col-xs-11 \">" + pcomment.nickName +
                  "            </div>" +
                  "          </div>" +
                  "          <div class=\"row\">" +
                  "            <div class=\"col-xs-11\"> 发表时间：" + pcomment.commentTime +
                  "            </div>" +
                  "          </div>" +
                  "        </div>" +
                  "      </div>" +
                  "<div class=\"row\">" +
                  "        <div class=\"col-xs-12 \">" +
                  " <pre>" + pcomment.content + "</pre>" +
                  "        </div>" +
                  "      </div><div class=\"row\" style=\"font-size:11px;\">" +
                  "        <div class=\"col-xs-4\">评论(<span id='rely" + pcommentId + "'>" + replys.length + "</span>)</div>" +
                  "        <div class=\"col-xs-4\"><span onclick=\"addLike('" + pcommentId + "')\">赞(<span id='zan" + pcommentId + "'>"
                  + commenPojo.likeNum + "</span>)</div>" +
                  "        <div class=\"col-xs-3\"><span onclick=\"reply('" + pcommentId + "')\">回复</span></div>" +
                  "      </div> <div id='listReply" + pcommentId + "'>";

                  var likes = commenPojo.likes;
                  var likeLength = likes.length;
                  if (likeLength > 0) {
                      likeLength=likeLength>20?20:likeLength;
                      html += "<div class=\"row likes\">";
                      for (var m = 0; m < likes.length; m++) {
                          var like = likes[m];
                          html += like.nickName + " ";
                      }
                      if(likeLength==20)
                          html+="等";
                      html+="觉得很赞</div>"
                  }

                  for(var j=0 ; j<replys.length;j++){
                      var scomment = replys[j];
                      if(j%2==0) {
                          html += "    <div class=\"row\" style=\"margin-top:20px\">" +
                          "        <div class=\"col-xs-3 \">" +
                          "         <img src=\"" + scomment.headImg + "\" class=\"img-circle\" width=\"50px\" height=\"50px\">" +
                          "        </div>" +
                          "        <div class=\"col-xs-8 \">" +
                          "          <pre style=\"float:left;margin-left:-20px;\">" + scomment.content + "</pre>" +
                          "        </div>" +
                          "      </div>";
                      }else{
                          html+="<div class=\"row\" style=\"margin-top:20px\">" +
                          "        <div class=\"col-xs-8 col-xs-offset-1 \">" +
                          "          <pre style=\"float:right;margin-right:-20px;\">"+ scomment.content +" </pre>" +
                          "        </div>" +
                          "        <div class=\"col-xs-3 \">" +
                          "         <img src=\"" + scomment.headImg + "\" class=\"img-circle\" width=\"50px\" height=\"50px\">" +
                          "        </div>" +
                          "      </div>";
                      }
                  }
                  html+="</div> </div></div>";
              }
//        console.log(html);
              $("#listComment").append(html);
          }
      });
  }
  $(function(){
      list();
    $("#published_btn").click(function(){
        var content = $("#context").val();
        if(content.length>255) {
          alert("短评的长度不能超过255");
        }else if(content.length==0){
          alert("请填写短评")
        }else{
          $.post("comment/addComment",{
             content : content
          },function(res){
             if(res.result==0){
               location.reload();
             }
          })
        }
    });
    $("#replyBtn").click(function(){
      var content = $("#reply_content").val();
      if(content.length>255) {
        alert("回复的长度不能超过255");
      }else if(content.length==0){
        alert("请填写回复")
      }else {
        $("#reply_content").val("");
        $.post("comment/addReply", {
          content:content,
          replyId:replyId
        },function(res){
          if(res.result==0){
            var obj = $("#listReply"+replyId);
            var relyRow="";
            var scomment = res.data;
            if(obj.children("div").length%2==0) {
              relyRow += "    <div class=\"row\" style=\"margin-top:20px\">" +
              "        <div class=\"col-xs-3 \">" +
              "         <img src=\"" + scomment.headImg + "\" class=\"img-circle\" class=\"img-circle\" width=\"50px\" height=\"50px\">" +
              "        </div>" +
              "        <div class=\"col-xs-8 \">" +
              "          <pre style=\"float:left;margin-left:-20px;\">" + scomment.content + "</pre>" +
              "        </div>" +
              "      </div>";
            }else{
              relyRow+="<div class=\"row\" style=\"margin-top:20px\">" +
              "        <div class=\"col-xs-8 col-xs-offset-1 \">" +
              "          <pre style=\"float:right;margin-right:-20px;\">"+ scomment.content +" </pre>" +
              "        </div>" +
              "        <div class=\"col-xs-3 \">" +
              "         <img src=\"" + scomment.headImg + "\" class=\"img-circle\" width=\"50px\" height=\"50px\">" +
              "        </div>" +
              "      </div>";
            }
            obj.append(relyRow);
            $("#rely"+replyId).html(parseInt($("#rely"+replyId).html())+1);
            $("#myModal").modal("hide");
          }
        });
      }
    });
  });
  function reply(commentId){
    replyId = commentId;
     $("#myModal").modal("show");
  }
  function addLike(commentId){
    $.post("comment/like", {
      commentId:commentId
    },function(res){
      if(res.result==0){
         $("#zan"+commentId).html(parseInt($("#zan"+commentId).html())+1);
      }else{
          alert(res.msg);
      }
    });
  }
</script>

</body>
</html>