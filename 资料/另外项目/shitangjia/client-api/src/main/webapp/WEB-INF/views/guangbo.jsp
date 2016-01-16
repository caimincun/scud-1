<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!doctype html>
<html>
<base>
<head>
    <%
        String path = request.getContextPath();
        String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
    %>
    <base href="<%=basePath%>">
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
    <title>${canteenName}</title>
    <title></title>
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <link href="css/base.css" rel="stylesheet">
    <link href="css/new_top.css" rel="stylesheet">
    <script src="js/jquery-2.1.1.js"></script>
    <script src="js/bootstrap.min.js"></script>
    <style>
        .main{
            width: 94%;
            float: left;
            margin-left: 3%;
            margin-top: 0.2rem;
            padding-bottom: 0.5rem;
            color: #000000;
        }
        .table{
            width: 100%;
            float: left;
            border-right:0.01rem #000000 solid ;
            border-bottom:0.01rem #000000 solid ;
            margin-top: 0.1rem;
            font-size: 0.14rem;
        }
        /* td{
             border-left:0.01rem #000000 solid;
             border-top:0.01rem #000000 solid;
         }
         th{
             border-left:0.01rem #000000 solid;
             border-top:0.01rem #000000 solid;
         }*/
        .contain{
            width: 80%;
            margin-left: auto;
            margin-right: auto;
            min-height: 2rem;
            border-top: 0.01rem  #c8c8c8 solid;
        }
        .row{
            float: left;
            width: 100%;
            height: 0.3rem;
            border-bottom: 0.01rem  #c8c8c8 solid;
        }
        .col{
            float: left;
            width: 25%;
            height: 100%;
        }
        .col_right_line {
            width: 0.02rem;
            border-right: 0.01rem #c8c8c8 solid;
            float: right;
            height: 100%;
        }
        .col_left_line {
            width: 0.02rem;
            border-left: 0.01rem #c8c8c8 solid;
            float: left;
            height: 100%;
        }
    </style>
</head>
<body>
<div class="new_top">
    <a href="/user/toIndexPage"><img src="image/back.gif" class="back_btn"></a>
    <div class="top_title"><span class="title">${categoryName}</span></div>
    <a href="user/toIndexPage"><img src="image/home.png" class="home"></a>
</div>
<div class="main">
    <pre style="padding: 0.1rem;font-size: 0.15rem">&nbsp&nbsp&nbsp&nbsp关于我院“食堂家”上线测试的通知历经三个月的研发，建材设计院“食堂家”即将上线测试了。这是我院食堂第一次尝试在云端建立网上食堂，在正式推广之前，我们将在小范围进行测试。测试期间，除买菜尚未开放外，其他功能均可以正常使用。
    如您在使用中有任何意见或建议，请及时联系食堂（王进 13908072116）、食堂家（罗忠涛13651764386）。我们将会根据您的意见对产品的各项功能和体验进行持续优化，衷心感谢您的参与、支持！
                   建材设计院食堂
                   二0一五年八月十日
    </pre>

</div>
</body>
</html>

