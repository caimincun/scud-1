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
  <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
  <title>${canteenName}</title>
  <link href="css/bootstrap.min.css" rel="stylesheet">
  <link href="css/base.css" rel="stylesheet">
  <script src="js/jquery-2.1.1.js"></script>
  <!--<script src="js/bootstrap.min.js"></script>-->
  <script src="js/bootstrap.min.js" ></script>
  <script src="js/tool.js" ></script>
  <link href="css/pay_pwd_set.css" rel="stylesheet">
  <link href="css/new_top.css" rel="stylesheet">

</head>
<body>
<div class="main">
  <div class="container2">
    <div class="new_top">
    <a href="user/toZhangHuPage"><img src="image/back.gif" class="back_btn"></a>
      <div class="top_title"><span class="title">支付设置</span></div>
      <a href="user/toIndexPage"><img src="image/home.png" class="home"></a>
    </div>
    <div class="set_div" id="set_pwd_row">
      <c:choose>
        <c:when test="${isHavPwd=='1'}">
          <div class="set_text" id="set_pwd">设置支付密码</div>
        </c:when>
        <c:otherwise>
          <div class="set_text" id="set_pwd">修改支付密码</div>
        </c:otherwise>
      </c:choose>
      <img src="image/right.png" style="width: 0.2rem;float: right;margin-right: 0.1rem;margin-top: 0.08rem">
    </div>
    <div class="set_div" id="mini_balance">
      <div class="set_text">小额免密支付</div>
      <img src="image/right.png" style="width: 0.2rem;float: right;margin-right: 0.1rem;margin-top: 0.08rem">
    </div>
    <div class="money_div" id="money_div">
      <div class="col">
        <div class="my_row">
          <div class="circle" id="cir_1" data-id="1" data-price="0.0">
            <div class="inner_circle" id="in_cir_1"></div>
          </div>
        </div>
        <div class="my_row2">
          <span>0元</span>
        </div>
      </div>
      <div class="col">
        <div class="my_row">
          <div class="circle" id="cir_2" data-id="2" data-price="30.0">
            <div class="inner_circle" id="in_cir_2"></div>
          </div>
        </div>
        <div class="my_row2">
          <span>30元</span>
        </div>
      </div>
      <div class="col">
        <div class="my_row">
          <div class="circle" id="cir_3" data-id="3" data-price="50.0">
            <div class="inner_circle" id="in_cir_3"></div>
          </div>
        </div>
        <div class="my_row2">
          <span>50元</span>
        </div>
      </div>
      <div class="col">
        <div class="my_row">
          <div class="circle" id="cir_4" data-id="4" data-price="80.0">
            <div class="inner_circle" id="in_cir_4"></div>
          </div>
        </div>
        <div class="my_row2">
          <span>80元</span>
        </div>
      </div>
      <div class="col">
        <div class="my_row">
          <div class="circle" id="cir_5" data-id="5" data-price="100.0">
            <div class="inner_circle" id="in_cir_5"></div>
          </div>
        </div>
        <div class="my_row2">
          <span>100元</span>
        </div>
      </div>
    </div>

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
</body>
<script>
  function myAlert(value){
    $("#error_message").html(value);
    $("#error_result").show();
    setTimeout(function(){
      $("#error_result").hide();
    },3000);
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

  $(function () {
    $("#get_code").click(function(){
      var phone="${phone}";
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
                    window.location="user/toPaySetPage";
                  },500);
                }
              },
              "text");
    });

    $(".circle").each(function(){
      var price=$(this).data("price");
      var id=$(this).data("id");
      if(price==${safeAmount}){
        $("#in_cir_"+id).show();
      }
    });

    $(".circle").click(function () {
      var id=$(this).data("id");
      var price=$(this).data("price");
      $(".inner_circle").hide();
      $("#in_cir_"+id).show();
      $.post("user/changeSafeAmount",{
        safeAmount:price
      }, function (res) {
      });
    });

    $("#mini_balance").click(function () {
      if($("#money_div").is(":hidden")){
        $("#money_div").slideDown();
      }else{
        $("#money_div").slideUp();
      }

    });
    $("#myModal").modal('hide');
    $("#set_pwd_row").click(function () {
      $("#myModal").modal('show');
    });
  });

</script>
</html>
