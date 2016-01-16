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
  <link href="/css/bootstrap.min.css" rel="stylesheet">
  <link href="/css/base.css" rel="stylesheet">
  <link href="/css/balance.css" rel="stylesheet">
  <link href="/css/new_top.css" rel="stylesheet">
</head>
<body>
<div class="main">
  <div class="container2">
    <div class="new_top">
  <a href="user/toPersonCenterPage"><img src="/image/back.gif" class="back_btn"></a>
      <div class="top_title"><span class="title">余额</span></div>
      <a href="user/toIndexPage"><img src="image/home.png" class="home"></a>
    </div>
    <div class="balance_info_row">
      <div class="balance_info_div">
        <span class="can_use_balance_text">账户余额:</span>
        <span class="money_mark">￥</span>
        <span class="money">${qingcaiBalance}</span>
        <%--<span class="yuan">元</span>--%>
      </div>
    </div>
    <%--<div class="balance_info_row">
      <div class="balance_info_div">
        <span class="can_use_balance_text">清菜余额:</span>
        <span class="money_mark">￥</span>
        <span class="money">${shitangBalance}</span>
        &lt;%&ndash;<span class="yuan">元</span>&ndash;%&gt;
      </div>
    </div>--%>
    <div class="center_line_div">
      <%--<img src="/image/line.png" class="line_img">--%>
    </div>
    <div class="btn_div">
      <a class="btn bal_btn fl l_btn" href="user/recharge/toRechargePage">充值</a>
      <a class="btn bal_btn fr r_btn" href="/user/toTixianPage">提现</a>
    </div>
  </div>
</div>
</body>
<script>
</script>
</html>
