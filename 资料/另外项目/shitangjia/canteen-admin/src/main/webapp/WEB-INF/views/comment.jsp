<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <base href="<%=basePath%>">
    <meta charset="utf-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <link href="assets/css/bootstrap.min.css" rel="stylesheet"/>
    <link rel="stylesheet" href="assets/css/font-awesome.min.css"/>
    <link rel="stylesheet" href="assets/css/font-awesome-ie7.min.css"/>
    <link rel="stylesheet" href="assets/css/ace.min.css"/>
    <link rel="stylesheet" href="assets/css/ace-rtl.min.css"/>
    <link rel="stylesheet" href="assets/css/ace-skins.min.css"/>
    <script src="assets/js/ace-extra.min.js"></script>
    <script src="js/jquery-2.1.1.js"></script>
    <style>
        .commentNumDiv{float:right;margin-right: 5px;}
        .commentNum{cursor: pointer}
        table{width:100%;text-align:center;border-collapse:collapse;border-spacing:0;}
        th{text-align:center}
    </style>
</head>



<body style="height: 600px;background-color: white;">



    <div class="widget-box ">
        <div class="widget-header">
            <h4 class="lighter smaller">
                <i class="icon-comment blue"></i>
                你说我说
            </h4>
        </div>

        <div class="widget-body">
            <div class="widget-main no-padding">
                <div class="dialogs">
                </div>
            </div>
            <!-- /widget-main -->
        </div>
        <!-- /widget-body -->
    </div>
    <div>
        <button type="button" onclick="list()" id="getMoreBtn" style="float: right" class="btn btn-info btn-lg">&nbsp;&nbsp;更&nbsp;&nbsp;多&nbsp;&nbsp;</button>
    </div>

    <%-- 互动详情--%>
    <%--中部结束--%>


    <div class="modal fade padding-top" id="commentDiv" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div>
                    <table id="comDetail" border="1">
                        <thead>
                        <tr>
                            <th>用户名称：</th>
                            <th>评论内容</th>
                            <th>时间</th>
                        </tr>
                        </thead>
                    </table>
                </div>
            </div>
        </div>
    </div>
    <!-- /widget-box -->

<!-- /span -->

<script type="text/javascript">
    window.jQuery || document.write("<script src='assets/js/jquery-2.0.3.min.js'>" + "<" + "script>");
</script>
<script type="text/javascript">
    if ("ontouchend" in document) document.write("<script src='assets/js/jquery.mobile.custom.min.js'>" + "<" + "script>");
</script>


    <script src="assets/js/bootstrap.min.js"></script>
    <script src="assets/js/typeahead-bs2.min.js"></script>
    <script src="assets/js/jquery.dataTables.min.js"></script>
    <script src="assets/js/jquery.dataTables.bootstrap.js"></script>
    <script src="assets/js/ace-elements.min.js"></script>
    <script src="assets/js/ace.min.js"></script>


<script type="text/javascript">
    var pageIndex = 1;
    var size = 5;
    $(function(){
        list();
    });
    function list() {
        $.post("/comment/list",
                {
                    pageIndex: pageIndex,
                    size: size
                }, function (res) {
                    if (res.result == 0) {
                        pageIndex += 1;
                        var comments = res.data;
                        if(comments.length<size){
                            $("#getMoreBtn").html("没有更多了!").attr("disabled", "disabled");
                        }
                        var html="";
                        for(var i = 0 ; i<comments.length;i++) {
                            var commenPojo = comments[i];
                            var pcomment = commenPojo.comment;
                            var replys = commenPojo.replys;
                            var pcommentId = pcomment.commentId;
                            html+="<div class='itemdiv dialogdiv'>" +
                            "                        <div class='user'>" +
                            "                            <img src='"+pcomment.headImg+"'/>" +
                            "                        </div>" +
                            "                        <div class='body'>" +
                            "                            <div class='time'>" +
                            "                                <i class='icon-time'></i>" +
                            "                                <span class='green'>"+pcomment.commentTime+"</span>" +
                            "                            </div>" +
                            "                            <div class='commentNumDiv'>" +
                            "                                |点赞 <a class='commentNum'>"+ commenPojo.likeNum +"</a>" +
                            "                            </div>" +

                            "                            <div class='commentNumDiv'>" +
                            "                                |<a class='commentNum' onclick='prom("+pcommentId+")'>"+"添加评论" +"</a>" +
                            "                            </div>" +

                            "                            <div class='commentNumDiv'>" +
                            "                                |评论 <a class='commentNum' onclick='detailComment("+pcommentId+")'>" + replys.length + "</a>" +
//                             "                                    |评论 <span class='green'>" + replys.length + "</span>" +
                              "                            </div>";

                            if(pcomment.isTop==1){
                                html+="                           <div class='commentNumDiv'>"+
                                "                           <a class='commentNum' onclick='quxiaozhiding("+pcommentId+")'>已置顶,取消置顶</a>"+
                                "                           </div>";
                            }else{
                                html+= "                            <div class='commentNumDiv'>" +
                                "                                <a class='commentNum' onclick='zhiding("+pcommentId+")'>置顶</a>" +
                                "                            </div>" ;
                            }
                           html +="                            <div class='name'>" +pcomment.nickName+"</div>" +
                            "                            <div class='text'>" + pcomment.content + "</div>" +
                            "                        </div>" +
                            "                    </div>";

                        }
                        $(".dialogs").append(html);
                    }
                })
    }
    function zhiding(id){
        window.location.href="/comment/zhiding?id="+id;
        //置顶操作未实现
    }
    function quxiaozhiding(id){
        //取消置顶操作未实现
        window.location.href="/comment/quxiaozhiding?id="+id;
    }

    function prom(id)

    {

        var content=prompt("请输入评论内容","");//将输入的内容赋给变量 name ，

        //这里需要注意的是，prompt有两个参数，前面是提示的话，后面是当对话框出来后，在对话框里的默认值

        if(content)//如果返回的有内容
        {
            window.location.href="/comment/addComment?content="+encodeURI(encodeURI(content))+"&id="+id;

        }

    }
</script>

    <script type="text/javascript">
        var flag = true;
        var tab2;
        function detailComment(id){
            if(flag){
                flag = false;
                console.log(flag);
                tab2 = $("#comDetail").dataTable({
                    "bLengthChange": false,
                    "bFilter": false,// 去掉搜索框
                    "iDisplayLength" : 5,// 每页显示行数
                    "bProcessing": false, // 是否显示取数据时的那个等待提示
                    "bServerSide": true,//这个用来指明是通过服务端来取数据
                    "sAjaxSource": "/comment/detailComment?replay_id="+id,//这个是请求的地址
                    "fnServerData": retrieveData ,// 获取数据的处理函数
                    "bInfo" : false,//左下角显示信息
                    "bSort":false,
                    "oLanguage":{
                        "sProcessing": "正在加载中......",
                        "sLengthMenu": "每页显示 _MENU_ 条记录",
                        "sZeroRecords": "对不起，查询不到相关数据！",
                        "sEmptyTable": "对不起，查询不到相关数据！"
                    }
                });
                $("#commentDiv").modal('show');
            }else{
                console.log(flag);
                tab2.fnSettings().sAjaxSource="/comment/detailComment?replay_id="+id,
                        tab2.fnDraw();
                $("#commentDiv").modal('show');
            }

        }

        function retrieveData( sSource111,aoData111, fnCallback111) {
            $.ajax({
                url : sSource111,//这个就是请求地址对应sAjaxSource
                data : {"aoData":JSON.stringify(aoData111)},//这个是把datatable的一些基本数据传给后台,比如起始位置,每页显示的行数
                type : 'post',
                dataType : 'json',
                async : false,
                success : function(result) {
                    fnCallback111(result);//把返回的数据传给这个方法就可以了,datatable会自动绑定数据的
                },
                error : function(msg) {
                }
            });
        }
    </script>

<script src="/js/resizeIframe.js"></script>
</body>
</html>