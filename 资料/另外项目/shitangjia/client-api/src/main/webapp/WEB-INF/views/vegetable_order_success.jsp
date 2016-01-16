<%@ page import="com.horadrim.talrasha.site.controller.request.CartItem" %>
<%@ page import="com.horadrim.talrasha.common.CommonParamDefined" %>
<%@ page import="java.util.Map" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>

<!doctype html>
<html>
<base >
<head>
  <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
  <%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
  %>
  <base href="<%=basePath%>">
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title>${canteenName}</title>
  <link href="css/base.css" rel="stylesheet">
  <script src="js/jquery-2.1.1.js"></script>
  <script src="js/tool.js"></script>
  <link href="css/new_top.css" rel="stylesheet">
  <link href="css/vegetable_order_confirm.css" rel="stylesheet">
  <style>
    .v_container2{
      width: 100%;
      min-height: 100%;
      float: left;
      margin-left: 0%;
      background-color: #ffffff;
      margin-top: 0%;
    }
    .row_{
      width: 90%;
      min-height: 0.3em;
      margin-top: 0.1rem;
      font-size: 0.15rem;
      float: left;
      margin-left: 5%;
    }
    .row_p{
      width: 90%;
      min-height: 0.3em;
      font-size: 0.15rem;
      float: left;
      margin-left: 5%;
      border-left: 0.01rem #8d8d8d solid;
      border-right: 0.01rem #8d8d8d solid;
      border-bottom: 0.01rem #8d8d8d solid;
    }
    .note_row{
      width: 90%;
      min-height: 0.3em;
      margin-top: 0.1rem;
      font-size: 0.15rem;
      float: left;
      margin-left: 5%;
      border: 0.01rem #8d8d8d solid;
    }
    .down_img{
      float: right;
      margin-right: 0.1rem;
      width: 0.25rem;
      margin-top: 0.1rem;
    }
    .up_img{
      display: none;
      float: right;
      margin-right: 0.1rem;
      width: 0.25rem;
      margin-top: 0.1rem;
      display: none;
    }
    .note_text{
      float: left;
      display: block;
      margin-left: 0.1rem;
      margin-top: 0.15rem;
    }
  </style>
  <script>
    function getNextDay(d){
      d = new Date(d);
      d = +d + 1000*60*60*24;
      d = new Date(d);
      //return d;
      //格式化
     return d.getFullYear()+"-"+(d.getMonth()+1)+"-"+d.getDate();
      //return (d.getMonth()+1)+"-"+d.getDate();

    }
    $(function () {
      $("#accept_time").html("取货时间:"+getNextDay(new Date())+"&nbsp&nbsp17：00—18：30")
      $("#go_to_buy").click(function () {
        window.location.href="vegetable/list";
      });
      $("#go_to_recharge").click(function () {
        window.location.href="/user/recharge/test2";
      });

      $(".down_img").click(function () {
        var id=$(this).data("id");
        $(".up_img").hide();
        $(".down_img").show();
        $(this).hide();
        $("#up_"+id).show();
        $(".row_p").slideUp();
        $("#note_"+id).slideDown();
      });
      $(".up_img").click(function () {
        var id=$(this).data("id");
        $(this).hide();
        $("#note_"+id).slideUp();
        $("#down_"+id).show();
      });
    });
  </script>
</head>
<body>
<div class="v_main">
  <div class="v_container2" style="margin-top: 0%;">
    <div class="fix_top">
      <div class="new_top">
     <a href="vegetable/list"><img src="image/back.gif" class="back_btn"></a>
        <div class="top_title"><span class="title">
          <c:if test="${saveOrderDto.ret==0}">
            下单成功
          </c:if>
          <c:if test="${saveOrderDto.ret==1}">
            下单失败
          </c:if>
          </span></div>
        <a href="user/toIndexPage"><img src="image/home.png" class="home"></a>
      </div>
    </div>
  <div class="food_list">
    <c:if test="${saveOrderDto.ret==0}">
      <div style="width: 100%;height: 0.3rem;text-align: center;margin-top: 0.5rem;font-size: 0.19rem">恭喜您，订单提交成功</div>
      <div class="row_" id="accept_time">
      </div>
      <div class="row_">
        冻结金额:${saveOrderDto.order.totalPrice}元
      </div>
      <div class="row_">
        备注:
      </div>
      <%--<div class="row_" style="font-size: 0.13rem">
        <p>1、订单提交成功后，若您因故须取消订单，您仅可以在21：00前发起订单取消申请，逾期订单将无法取消。</p>
        <p>2、订单提交成功后，与支付金额等同的余额将被予以冻结，待您取货时再行据实结算。订单取消后，冻结金额将同时予以解冻。</p>
        <p>3、您的取货时间为订单提交成功后第二天17：00—18：30期间（质保期间）。</p>
        <p>4、若您因故无法及时取货的，您可以在订单提交成功的第三天17：00—18：30期间取货，但对于生鲜产品，届时销售方将无法保证您的货品质量。</p>
        <p>5、若您超过最长取菜时间，您的冻结金额的80%将会作为销售方的履约成本予以扣除，请您务必及时提取货品。</p>
      </div>--%>
      <div class="note_row" data-id="1" id="row_1">
        <span class="note_text">订单取消</span>
        <img src="image/down.png" class="down_img" data-id="1" id="down_1">
        <img src="image/up.png" class="up_img" data-id="1" id="up_1">
      </div>
      <div class="row_p" id="note_1" style="display: none;">
        <p style="padding: 0.1rem 0.1rem 0.1rem  0.1rem;">订单提交成功后，若您因故须取消订单，您
          仅可以在21：00前发起订单取消申请，
          逾期订单将无法取消。</p>
      </div>
      <div class="note_row" data-id="2" id="row_2">
        <span class="note_text">冻结金额</span>
        <img src="image/down.png" class="down_img" data-id="2" id="down_2">
        <img src="image/up.png" class="up_img" data-id="2" id="up_2">
      </div>
      <div class="row_p" id="note_2" style="display: none;">
        <p style="padding: 0.1rem 0.1rem 0.1rem  0.1rem;">订单提交成功后，与支付金额等同的余额将被予以冻结，待您取货时再行据实结算。订单取消后，冻结金额将同时予以解冻。。</p>
      </div>
      <div class="note_row" data-id="3" id="row_3">
        <span class="note_text">取货时间</span>
        <img src="image/down.png" class="down_img" data-id="3" id="down_3">
        <img src="image/up.png" class="up_img" data-id="3" id="up_3">
      </div>
      <div class="row_p" id="note_3" style="display: none;">
        <p style="padding: 0.1rem 0.1rem 0.1rem  0.1rem;">1、您的取货时间为订单提交成功后第二天17：00—18：30期间（质保期间）。</p>
        <p style="padding: 0.1rem 0.1rem 0.1rem  0.1rem;">2、若您因故无法及时取货的，您可以在订单提交成功的第三天17：00—18：30期间取货，但对于生鲜产品，届时销售方将无法保证您的货品质量。</p>
        <p style="padding: 0.1rem 0.1rem 0.1rem  0.1rem;">3、若您超过最长取菜时间，您的冻结金额的80%将会作为销售方的履约成本予以扣除，请您务必及时提取货品。</p>
      </div>
      <button id="go_to_buy" style="width: 50%;
    float: left;
    margin-left: 25%;height: 0.45rem;color:#ffffff;
    font-size:0.15rem;background-color: green;border: 0;
    margin-top: 0.2rem;border-radius: 0.01rem">继续买菜</button>
    </c:if>

    <c:if test="${saveOrderDto.ret==1}">
      <div style="width: 100%;height: 0.3rem;text-align: center;margin-top: 0.5rem;font-size: 0.19rem">余额不足，订单提交失败！</div>
      <button id="go_to_recharge" style="width: 50%;
    float: left;
    margin-left: 25%;height: 0.45rem;color:#ffffff;
    font-size:0.15rem;background-color: green;border: 0;
    margin-top: 0.2rem;border-radius: 0.01rem">去充值</button>
    </c:if>
  </div>
</div>
  </div>
</body>
</html>
