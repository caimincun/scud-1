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
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
  %>
  <base href="<%=basePath%>">
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title>${canteenName}</title>
  <link href="css/bootstrap.min.css" rel="stylesheet">
  <link href="css/base.css" rel="stylesheet">
  <link href="css/new_top.css" rel="stylesheet">
  <link href="css/qucan.css" rel="stylesheet">
  <script src="js/jquery-2.1.1.js"></script>
  <script src="js/tool.js"></script>
  <script src="js/bootstrap.min.js" ></script>
  <script src="js/jquery.qrcode.min.js"></script>
</head>
<body style="background: url(image/bg.png)">
<div class="main">
  <div class="container2">
  <div class="new_top">
    <img src="image/back.gif" class="back_btn" id="list_order">
    <div class="top_title"><span class="title">取菜</span></div>
    <a href="user/toIndexPage"><img src="image/home.png" class="home"></a>
  </div>
  <div class="alert_info">
    <span>请出示二维码给工作人员</span>
  </div>
<div class="order_list">
<div class="order">
    <div class="order_info_div">
      <span class="text">订单号:</span>
      <span class="text">${order.orderNum}</span>
      <img src="image/up.png" class="up_img" data-id="${order.orderNum}" id="up_${order.orderNum}">
      <img src="image/down.png" class="down_img" data-id="${order.orderNum}" id="down_${order.orderNum}" style="display: none">
    </div>
    <div class="code_div" id="code_${order.orderNum}">
      <div id="two_code_${order.orderNum}" class="code_img"></div>
    </div>
  <form style="display: none" id="hide_form" action="order/queryOrders" method="post">
    <input type="hidden" value="1" name="orderType" id="order_type">
  </form>
  <%@include file="qc_modal.jsp"%>
  </div>
</div>
</div>
</div>
<style>
  .real_money{
    font-size: 0.2rem;
    width: 100%;
    height: 0.45rem;
    text-align: center;
  }
  .pwd_div{
    display: none;
    font-size: 0.2rem;
    width: 100%;
    height: 0.45rem;
  }
  .money_text{
    display: block;
    margin-top: 0.1rem;
  }
  .alert_footer{
    padding-top: 0rem;
    width: 100%;
    height: 0.45rem;
  }
  .submit_btn{
    margin-top: 0.05rem;
    float: left;
    margin-left: 0.5rem;
    background-color: #129cfe;
    color: #ffffff;
    font-family: "Microsoft YaHei", \5FAE\8F6F\96C5\9ED1, STHei, \534E\6587\7EC6\9ED1, "Helvetica Neue", Helvetica, Arial, sans-serif;
    width: 20%;
  }
  .cancel_btn{
    margin-top: 0.05rem;
    float: right;
    margin-right: 0.5rem;
    background-color: #129cfe;
    color: #ffffff;
    font-family: "Microsoft YaHei", \5FAE\8F6F\96C5\9ED1, STHei, \534E\6587\7EC6\9ED1, "Helvetica Neue", Helvetica, Arial, sans-serif;
    width: 20%;
  }
  .pwd{
    border: 0.01rem solid #c8c8c8;
    border-radius: 0.05rem;
  }
  .pwd_text{
    display: none;
    font-size: 0.14rem;
    width: 100%;
    height: 0.2rem;
  }
</style>
</body>
<script>
  var count=0;
  var timer;
  var orderId=${order.id};
  //var checkCode;
  $(function(){
    $(".code_div").show();
    $("#list_order").click(function () {
      $("#hide_form").submit();
    });

    $("#myModal").modal('hide');
    $("#myModalLabel").html("确认支付");
    var mo_body_html="";
    mo_body_html+="<div class=\"real_money\">" +
    "<span class=\"money_text\" id=\"total_price\"></span></div>" +
    "<div class='pwd_text'><span>输入支付密码</span></div><div class=\"pwd_div\">" +
    "<input type='password' id='password' class='pwd'></div>"
    $("#modal-body").html('');
    $("#modal-body").append(mo_body_html);
    var mo_fottor_html="";
    mo_fottor_html+="<button type=\"butto\" class=\"btn submit_btn\"  id=\"sub_btn\">提交</button>"+
    "<button type=\"button\" class=\"btn  cancel_btn\" data-dismiss=\"modal\">取消</button>";
    $("#modal-footer").html('');
    $("#modal-footer").append(mo_fottor_html);

    timer=setInterval(task,5000);
    $(".up_img").click(function () {
      var id=$(this).data("id");
      $(this).hide();
      $("#code_" + id).slideUp();
      $("#down_" + id).show();
    });

    $(".code_img").html('');
    $(".code_img").qrcode({
      render: "canvas",
      text:"${order.orderNum}",
      width: 200,
      height:200
    });
    $(".down_img").click(function () {
      var id=$(this).data("id");
      console.log(id);
      $(".code_div").hide();
      $(".up_img").hide();
      $(".down_img").show();
      $(this).hide();
      $twocod = $("#two_code_"+id);
      $twocod.html('');
      $twocod.qrcode({
        render: "canvas",
        text:""+id+"",
        width: 200,
        height:200
      });
      $("#code_" + id).slideDown();
      $("#up_" + id).show();
    });

    $("#modal-footer").on("click","#sub_btn", function () {
      $.post("order/vegetable/confirm",
              {
                orderId:orderId
              },
              function (res) {
                if(res.result==0){
                  $("#hide_form").submit();
                }else if(res.result==1008){
                  $("#myModal").modal('hide');
                  alert(res.msg+",去设置");
                  window.location.href="user/toPaySetPage";
                }else{
                  $("#myModal").modal('hide');
                  alert(res.msg);
                  $("#password").val('');
                  $("#myModal").modal('show');
                }

              });
    });

    $("#sub_btn").click(function () {
      var password=$("#password").val();
      $.post("order/vegetable/confirm",
              {
                orderId:orderId,
                pwd:password
              },
              function (res) {
                if(res.result==0){
                  $("#hide_form").submit();
                }
              });
    });

  });

  function task(){
    count=count+1;
    if(count>=120){
      clearInterval(timer);
    }
    getConfirm();
  }

  function getConfirm(){
    $.post("order/vegetable/checkConfirm",{
      orderId:orderId
    }, function (res) {
      if(res.result==0){
        var totalPrice=res.totalPrice;
        $("#total_price").html(totalPrice);
        var safeAmount=res.safeAmount;
        if(totalPrice>=safeAmount){
          $(".pwd_text").show();
          $(".pwd_div").show();
        }
        $("#myModal").modal('show');
        clearInterval(timer);
      }
    });
  }
</script>
</html>
