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
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>${canteenName}</title>
    <link href="css/base.css" rel="stylesheet">
    <script src="js/jquery-2.1.1.js"></script>
    <link href="css/new_top.css" rel="stylesheet">
    <style>
      .v_main{
        width: 100%;
        background-size: cover;
        height: 100%;
      }
      .v_container2{
        width: 100%;
        float: left;
        background-color: #ffffff;
        min-height: 100%;
      }
        .row_ {
            width: 94%;
            height: 0.45rem;
            margin-left: 3%;
            border: 0.01rem #8d8d8d solid;
            margin-top: 0.1rem;
        }

        .item_name {
            display: block;
            float: left;
            margin-top: 0.1rem;
            font-size: 0.15rem;
            margin-left: 0.1rem;
        }

        .right_img {
            float: right;
            margin-top: 0.08rem;
            font-size: 0.15rem;
            margin-right: 0.1rem;
            width: 0.3rem;
            border-radius: 0.15rem;
        }

    </style>
</head>
<body style="background-color: green">
<div class="v_main">
    <div class="v_container2 " style="margin-top: 0%;">
        <div class="fix_top">
            <div class="new_top">
                <a href="/user/toPersonCenterPage"><img src="image/back.gif" class="back_btn"></a>
              <div class="top_title"><span class="title">问卷调查</span></div>
            </div>
        </div>
        <div class="food_list">
            <c:forEach items="${categories}" var="category">
                <div class="row_"  data-id="${category.id}">
                    <span class="item_name">${category.description}</span>
                    <img src="image/arrow.png" class="right_img">
                </div>
            </c:forEach>
        </div>
    </div>
</div>
<form id="hidden_form" action="/questionary/toQuestionaryPage" method="post">
  <input type="hidden" value="" name="categoryId" id="category_id">
</form>
</body>
<script>
  $(".row_").click(function () {
    var id=$(this).data("id");
    $("#category_id").val(id);
    $("#hidden_form").submit();
  });
</script>

</html>
