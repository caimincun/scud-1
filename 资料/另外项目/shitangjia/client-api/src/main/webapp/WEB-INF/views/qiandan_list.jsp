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
  <link href="css/qiandan_list.css" rel="stylesheet">
  <link href="css/new_top.css" rel="stylesheet">
</head>
<body>
<div class="main">
  <div class="container2">
    <div class="new_top">
      <a href="signbill/toSignBillPage"><img src="image/back.gif" class="back_btn"></a>
      <div class="top_title"><span class="title">我的签单</span></div>
    </div>

    <div class="qian_dan_list">
      <div class="qiandan_item">
        <div class="create_time">
          <span class="create_time_span">2015-08-16</span>
        </div>
        <div class="status_div">
          <div class="node">
            <span class="node_content">提交成功</span>
          </div>
          <img src="image/right_arrow.png" class="right_arrow">
          <div class="node">
            <span class="node_content">部门审批</span>
          </div>
          <img src="image/right_arrow.png" class="right_arrow">
          <div class="node gray_back">
            <span class="node_content">办公室审批</span>
          </div>
          <img src="image/right_arrow.png" class="right_arrow">
          <div class="node gray_back">
            <span class="node_content">食堂审批</span>
          </div>
        </div>
      </div>
      <div class="qiandan_item">
        <div class="create_time">
          <span class="create_time_span">2015-08-19</span>
        </div>
        <div class="status_div">
          <div class="node">
            <span class="node_content">提交成功</span>
          </div>
          <img src="image/right_arrow.png" class="right_arrow">
          <div class="node">
            <span class="node_content">部门审批</span>
          </div>
          <img src="image/right_arrow.png" class="right_arrow">
          <div class="node gray_back">
            <span class="node_content">办公室审批</span>
          </div>
          <img src="image/right_arrow.png" class="right_arrow">
          <div class="node gray_back">
            <span class="node_content">食堂审批</span>
          </div>
        </div>
      </div>
      <div class="qiandan_item">
        <div class="create_time">
          <span class="create_time_span">2015-08-20</span>
        </div>
        <div class="status_div">
          <div class="node">
            <span class="node_content">提交成功</span>
          </div>
          <img src="image/right_arrow.png" class="right_arrow">
          <div class="node">
            <span class="node_content">部门审批</span>
          </div>
          <img src="image/right_arrow.png" class="right_arrow">
          <div class="node gray_back">
            <span class="node_content">办公室审批</span>
          </div>
          <img src="image/right_arrow.png" class="right_arrow">
          <div class="node gray_back">
            <span class="node_content">食堂审批</span>
          </div>
        </div>
      </div>
    </div>

  </div>
</div>
</body>
<script>
</script>
</html>
