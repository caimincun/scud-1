<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>

<!doctype html>
<html>
<head>
  <%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path;
  %>
  <base href="<%=basePath%>">
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
  <title>${canteenName}</title>
  <link href="css/base.css" rel="stylesheet">
  <script src="js/jquery-2.1.1.js"></script>
  <link href="css/qiandan.css" rel="stylesheet">
  <link href="css/new_top.css" rel="stylesheet">
</head>
<body>
<div class="main">
  <div class="container2">
    <div class="new_top">
      <img src="image/back.gif" class="back_btn">
      <div class="top_title"><span class="title">签单</span></div>
    </div>
    <a href="signbill/toSignBillListPage"><div class="row">
      <div class="row_left">我的签单</div>
      <img src="image/arrow.png" class="row_right">
    </div>
    </a>
    <a href="signbill/toFabuSignBillPage"><div class="row">
      <div class="row_left">发布签单</div>
      <img src="image/arrow.png" class="row_right">
    </div>
    </a>
    <a href="signbill/toWaitSignBillPage"><div class="row">
      <div class="row_left">待审批签单</div>
      <img src="image/arrow.png" class="row_right">
    </div>
    </a>
    <!--<div class="row">
        <div class="row_left">我审批过的签单</div>
        <img src="image/arrow.png" class="row_right">
    </div>-->


  </div>
</div>
</body>
<script>
</script>
</html>
