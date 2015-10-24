<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!doctype html>
<html>
<base >
<head>
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
  <link href="css/vegetable_confirm_pay.css" rel="stylesheet">
  <link href="css/new_top.css" rel="stylesheet">
  <script src="js/tool.js" ></script>
</head>
<body style="background-color: green">
<div class="v_main">
  <div class="v_container2 " style="margin-top: 0%;">
    <div class="new_top">
      <a href="vegetable/list"><img src="image/back.gif" class="back_btn"></a>
      <div class="top_title"><span class="title">确认支付</span></div>
      <a href="user/toIndexPage"><img src="image/home.png" class="home"></a>
    </div>
    <div class="order_list">
    <c:set value="0" var="sum" />
    <c:forEach var="order" items="${orders}">
      <c:set value="${sum+order.totalPrice}" var="sum" />
      <div class="order_item" id="order_item_${order.id}" data-id="${order.id}">
        <div class="order_item_row" style="background-color: #38af0c">
          <span class="order_id_text">	订单号：</span>
          <span class="order_id">${order.id}</span>
          <span class="order_status">金额:￥${order.totalPrice}</span>
          <img src="image/down.png" class="down_img" data-id="${order.id}" id="down_${order.id}">
          <img src="image/up.png" class="up_img" data-id="${order.id}" id="up_${order.id}">
        </div>
        <div class="items" id="items_${order.id}">
          <c:forEach var="item" items="${order.orderItems}">
            <div class="order_item_row" style="background-color: #ffffff">
              <span class="text_margin_left">${item.vegetable.vegetableName}</span>
              <span class="item_count">${item.count}</span>
              <span class="item_price">${item.vegetable.unitPrice}${item.vegetable.unitName}</span>
            </div>
          </c:forEach>
          <div class="order_item_row" style="background-color: #ffffff">
            <span class="order_id_text">下单时间:</span>
            <span class="order_id"><fmt:formatDate value="${order.orderDate}" type="both" /> </span>
            <%--<span class="all_price">￥${order.totalPrice}元</span>
            <span class="all_price_text">总价:</span>--%>
          </div>
        </div>
      </div>
    </c:forEach>
      </div>
    <div class="pay_types_div">
      <span class="pay_type_text">总金额:${sum} , 请选择支付方式</span>
    </div>
    <div class="pay_types_div">
      <div style="float: right;margin-right: 0.2rem">
        <input type="radio" name="pay_type" value="2" class="s_pay" id="s_pay">
        <label for="s_pay" class="label_pay">货到付款</label>
      </div>
      <div style="float: right;margin-right: 0.2rem">
        <input type="radio" name="pay_type" value="1" class="q_pay" id="q_pay" checked="checked">
        <label for="q_pay" class="label_pay">在线支付</label>
      </div>

    </div>
    <div class="pay_password_div" id="pay_password">
      <input type="password" placeholder="输入支付密码" class="pay_password" id="pwd">
     <span class="f_p" id="forget_pwd_btn">忘记密码</span>
      <span class="s_p"  id="set_pwd_btn">设置密码</span>
    </div>
    <div class="pay_result_alert" id="pay_result_alert">
      <div class="pay_result_alert_text" id="pay_result_alert_text"></div>
    </div>
    <div class="pay_result_error" id="pay_result_error">
      <div class="pay_result_error_text" id="pay_result_error_text"></div>
    </div>
    <button class="go_to_charge" id="go_to_charge" style="display: none;width: 30%;
    margin-left: 5%;
    height: 0.45rem;
    border: 0rem;
    background-color: #38af0c;
    color: #ffffff;
    font-size: 0.16rem;
    margin-top: 0.2rem;
    font-family: 'Microsoft YaHei';">去充值</button>
    <button class="pay_btn" id="pay">确定</button>
    <div class="modal fade padding-top" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" style="display: none">
      <div class="modal-dialog">
        <div class="modal-content">
          <div class="modal-header " style="margin-top: 0rem;height: 0.45rem">
            <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
            <div style="width: 100%;text-align: center"><h4 class="modal-title" id="myModalLabel">设置支付密码</h4></div>
          </div>
          <div class="modal-body" style="padding-top: 0rem">
            <div class="pwd_input_div">
              <input type="password" placeholder="请输入密码" class="pwd_input" id="password">
            </div>
            <div class="pwd_input_div">
              <input type="password" placeholder="再次输入密码" class="pwd_input" id="re_password">
            </div>
            <div class="pwd_input_div">
              <input type="password" placeholder="输入验证码" class="code_input" id="code">
              <button class="btn code_btn" id="get_code"><span id="get_code_text">获取验证码</span></button>
              <button class="btn code_btn" style="display: none;" id="after_get_code"><span id="after_get_code_text"> 获取验证码</span></button>
            </div>
            <div class="set_result" id="check_result">
              <span class="set_result_text" id="message" ></span>
            </div>
            <div class="get_code_result" id="get_code_result">
              <div class="get_code_result_text" id="get_code_result_text">提醒：验证码已发送，请查收</div>
            </div>
            <div class="error_result" id="error_result">
              <div class="error_message" id="error_message"></div>
            </div>
          </div>
          <div class="modal-footer alert_footer">
            <button type="button" class="btn submit_btn"  id="sub_btn">提交</button>
            <button type="button" class="btn  cancel_btn" data-dismiss="modal">取消</button>
          </div>
        </div>
      </div>
    </div>
  </div>
</div>
<form style="display: none;" action="order/queryOrders" method="post" id="hide_form">
  <input type="hidden" value="1" name="orderType">
</form>
<script src="js/jquery-2.1.1.js" ></script>
<script src="js/bootstrap.min.js" ></script>
</body>

<script>
  function myAlert(value){
    $("#error_message").html(value);
    $("#error_result").show();
    setTimeout(function(){
      $("#error_result").hide();
    },3000);
  }

  function payErrorAlert(value){
    $("#pay_result_error_text").html(value);
    $("#pay_result_error").show();
    setTimeout(function(){
      $("#pay_result_error").hide();
    },3000);
  }
  function paySuccessAlert(value){
    $("#pay_result_alert_text").html(value);
    $("#pay_result_alert").show();
    setTimeout(function(){
      $("#pay_result_alert").hide();
      //window.location="/order/queryOrders?orderType=1";
      $("#hide_form").submit();
    },1000);
  }

  var count=60;
  var time;
  var checkTime;

  function timeCount(){
    $("#after_get_code_text").html("倒计时"+(--count)+"s");
  }

  function check() {
    if(count<0)
    {
      $("#after_get_code").hide();
      $("#get_code").show();
      $("#get_code_text").html("获取验证码");
      window.clearInterval(time);  //结束setInterval循环
      window.clearInterval(checkTime);
      count=60;
    }
  }

  var totalPrice=parseFloat(${sum});

  $(function(){

    if(totalPrice>${safeAmount}){
         $("#pay_password").show();
         $.post("user/isHavingPwd",
                 function(data){
                   if(data.result==1008){
                     $("#set_pwd_btn").show();
                   }
                 },
                 "text");
       }

  $("input[name='pay_type']").click(function(){
    var payType=$("input[name='pay_type']:checked").val();
    if(payType==2){
      $("#pay_password").hide();
      $("#pay").html("提交");
      $("#go_to_charge").hide();
    }
    if(payType==1){
      $("#pay").html('');
      $("#pay").html("支付");
      if(totalPrice>${safeAmount}){
        $("#pay_password").show();
        $.post("user/isHavingPwd",
                function(data){
                  if(data.result==1008){
                    $("#set_pwd_btn").show();
                    //phone=data.phone;
                  }
                },
                "text");
      }
    }
     });

    $("#myModal").modal('hide');
    $("#set_pwd_btn").click(function(){
      $("#myModal").modal('show');
    });
    $("#forget_pwd_btn").click(function () {
      $("#myModal").modal('show');
      $("#myModalLabel").html("重新设置支付密码");
    });
       $("#pay").click(function () {
         var orderId="";
         var items=$(".order_item");
         items.each(function(){
           orderId=orderId+","+$(this).data("id");
         });

         var payType=$("input[name='pay_type']:checked").val();
         var payPwd=$("#pwd").val();

         if(payType==""||payType==null){
           payErrorAlert("提示:请选择支付方式");
           return false;
         }

         if(totalPrice>=${safeAmount}&&payType==0){
           if(payPwd==""||payPwd==null){
             payErrorAlert("提示:请输入支付密码");
             return false;
           }
         }
         //
         var data={
             orderIds:orderId.substr(1),
             payType:payType,
             payPwd:payPwd
         };
         $(this).attr({"disabled":"disabled"});
         $.ajax({
           type : "POST",
           url : "vegetable/payOrder",
           data :data,
           success : function(res) {

             var result =  jQuery.parseJSON(JSON.stringify(res));
             if(res.result==0)
             {
               $(this).attr({"disabled":"disabled"});
               if(payType==2){
                 paySuccessAlert("提交成功");
               }else{
                 paySuccessAlert("支付成功");
               }
             }else{
               if(res.result==1010){
                 $("#go_to_charge").show();
               }
               payErrorAlert(result.msg);
               $("#pay").removeAttr("disabled");
             }

           }
         });
       });

    $("#go_to_charge").click(function () {
      window.location.href="/user/toRechargePage";
    });

    $("#get_code").click(function(){
      var phone="${user.phone}";
      $.post("user/getSMSCode",{phone:phone},
              function(data){
                var result = jQuery.parseJSON(data);
                if(confirmAjaxResult(result)){
                  $("#get_code_result").show();
                  $("#get_code").hide();
                  setTimeout(function(){
                    $("#get_code_result").hide();
                  },1000);
                  checkTime = setInterval(check, 500);
                  time = setInterval(timeCount, 1000);
                  $("#after_get_code").show();
                }
              },
              "text");
    });

    $("#sub_btn").click(function(){
      var password=$("#password").val();
      var rePassword=$("#re_password").val();
      var code=$("#code").val();

      if(password.length<6){
        myAlert("提示:密码至少6位");
        return false;
      }
      if(password!=rePassword){
        myAlert("提示:两次密码不一致");
        return false;
      }
      if(code==""){
        myAlert("提示:验证码不能为空");
        return false;
      }
      $.post("user/updatePayPwd",
              {
                payPwd:password,
                smsCode:code
              },
              function(data){
                var result = jQuery.parseJSON(data);
                if(confirmAjaxResult(result)){
                  $("#message").html("密码设置成功");
                  $("#check_result").show();
                  setTimeout(function(){
                    $("#check_result").hide();
                    $("#myModal").modal('hide');
                  },500);
                }
              },
              "text");
    });

    $(".up_img").click(function () {
      var id=$(this).data("id");
      $(this).hide();
      $("#items_" + id).hide();
      $("#down_" + id).show();
    });
    $(".down_img").click(function () {
      var id=$(this).data("id");
      $(".items").hide();
      $(".up_img").hide();
      $(".down_img").show();
      $(this).hide();
      $("#items_" + id).show();
      $("#up_" + id).show();
    });
  });
</script>
</html>
