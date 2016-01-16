<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>

<!doctype html>
<html>
<base >
<head>
  <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
  <%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
  <%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
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
  <script src="/js/jquery-2.1.1.js"></script>
  <!--<script src="js/bootstrap.min.js"></script>-->
  <link href="/css/center.css" rel="stylesheet">
  <link href="/css/new_top.css" rel="stylesheet">
</head>
<body>
<div class="main">
  <div class="container2">
    <div class="new_top">
     <a href="user/toIndexPage"><img src="/image/back.gif" class="back_btn"></a>
      <div class="top_title"><span class="title">个人中心</span></div>
        <a href="user/toIndexPage"><img src="image/home.png" class="home"></a>
    </div>
    <div class="center_top_div">
      <div class="center_top_left">
       <a href="user/toPersonInfoPage"><img src="${user.headImg}" class="tou_img"></a>
      </div>
      <div class="center_top_right">
        <div class="center_top_right_row">
          <span class="welcome_row">欢迎光临！${user.nickName}</span>
          <span class="welcome_row" id="date">今天是2013年10月15日 星期三</span>
        </div>
        <div class="center_top_right_row" style="margin-top: 0.1rem;color: #000000">
          <span class="welcome_row">余额￥${user.qingcaiBalance}元</span>
        </div>
      </div>
    </div>
    <div class="list">
    <a href="user/toZhangHuPage"><div class="list_item">
        <img src="/image/wallet.png" class="item_img">
        <span class="item_name">钱包</span>
        <img src="/image/arrow.png" class="arrow_img">
      </div>
    </a>

        <c:forEach var="module" items="${canteenModules}">
            <c:if test="${module.moduleCode=='m_dingcan'}">
                <div class="list_item list_order" data-id="0">
                    <img src="/image/list46.png" class="item_img">
                    <span class="item_name">用餐记录</span>
                    <img src="/image/arrow.png" class="arrow_img">
                </div>
            </c:if>

            <c:if test="${module.moduleCode=='m_maicai'}">
                <div class="list_item list_order" data-id="1">
                    <img src="/image/fruits_vegetables.png" class="item_img">
                    <span class="item_name">买菜记录</span>
                    <img src="/image/arrow.png" class="arrow_img">
                </div>
            </c:if>
            <c:if test="${module.moduleCode=='m_hudong'}">
            <a href="comment/toCommentPage"><div class="list_item" >
                    <img src="/image/bubbles-alt-icon.png" class="item_img">
                    <span class="item_name">你说我说</span>
                    <img src="/image/arrow.png" class="arrow_img">
                </div></a>
            </c:if>
            <c:if test="${module.moduleCode=='m_wenjuan'}">
                <a href="/questionary/toQuestionCategoryPage"><div class="list_item" >
                    <img src="/image/questionnaire.png" class="item_img">
                    <span class="item_name">问卷调查</span>
                    <img src="/image/arrow.png" class="arrow_img">
                </div></a>
            </c:if>
            <c:if test="${module.moduleCode=='m_signbill'}">
                <a href="/signbill/toSignBillPage"><div class="list_item" >
                    <img src="/image/questionnaire.png" class="item_img">
                    <span class="item_name">签单</span>
                    <img src="/image/arrow.png" class="arrow_img">
                </div></a>
            </c:if>
        </c:forEach>
    </div>
  </div>
</div>
<form style="display: none" id="hide_form" action="order/queryOrders" method="post">
    <input type="hidden" value="" name="orderType" id="order_type">
</form>
</body>
<script>
    var dayNames = new Array("星期日","星期一","星期二","星期三","星期四","星期五","星期六");
    function   formatDate(now)   {
        var   year=now.getFullYear();
        var   month=now.getMonth()+1;
        var   date=now.getDate();
        var   hour=now.getHours();
        var   minute=now.getMinutes();
        var   second=now.getSeconds();
        return   year+"年"+month+"月"+date+"日";
    }
  /*$("#back").click(function(){
    window.location="we_index.html";
  });*/

  $(function () {
      var date=new Date();
      $("#date").html("今天是"+formatDate(date)+" "+dayNames[date.getDay()]);
      $("#orders").click(function(){
          window.location="order/queryOrders"
      });
      $("#balance").click(function(){
          window.location="user/toBalancePage"
      });
      $(".list_order").click(function () {
          var id=$(this).data("id");
          $("#order_type").val(id);
          $("#hide_form").submit();
      });
  });
</script>
</html>
