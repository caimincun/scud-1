<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>

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
  <link href="/css/base.css" rel="stylesheet">
  <link href="/css/top.css" rel="stylesheet">
  <link href="/css/index.css" rel="stylesheet">
  <script src="/js/jquery-2.1.1.js"></script>
  <script src="/js/resizeIframe.js"></script>
</head>
<body>
<form action="/questionuser/addQuestionUsers" onsubmit="return valide()" method="post">
<div class="top">
  <span class=" h1_title_04 title">员工食堂满意度调查</span>
</div>
<div class="message_info_div ">
  <p class="text">
    &nbsp;&nbsp;&nbsp;&nbsp;为提升食堂服务水平，食堂联合清菜科技开展员工食堂满意度调研，诚邀您就员工食堂现状、改
    善提供宝贵意见，您的参与将是对我们工作的最大
    支持与鼓励。<br>
    &nbsp;&nbsp;&nbsp;&nbsp;调查结束后，您还将有机会抽取一份小礼品。
    20名幸运的参与者将会获得由清菜科技送出小礼
    品，您可以凭借兑奖码至食堂充值处领取，祝您
    中奖！</p>
</div>
<div class="input_div">

  <div class="age_div">
    <span class="label">年龄:</span>
    <input type="number" class="name_input" name="age" id="age">
  </div>
  <div class="sex_div">
    <span class="label">性别:</span>
    <input name="sex" type="radio" class="label" style="margin-top: 0.14rem" checked="true" value="1"/><span class="label">男</span>
    <input name="sex" type="radio" class="label" style="margin-top: 0.14rem" value="0"/><span class="label">女</span>
  </div>
  <div class="phone_div">
    <span class="label">电话:</span>
    <input type="number" class="phone_input" name="phoneNumber" id="phoneNumber" placeholder="电话号码方便在中奖时联系您">
  </div>
</div>
 <input type="submit" class="next" value="下一步">
</form>
</body>

<script>
  $(function(){
    var phoneErr = "${phone}";
    if(phoneErr=="err")
      alert("手机号有误");
  });
    function valide(){
      var age = $("#age").val();
      var phone = $("#phoneNumber").val();
      if(age==null||age==""||age>70||age<10){
        alert("请认真填写年龄！");
        return false;
      }
      if(phone==null||phone==""||phone.length!=11||phone.substr(0,1)!="1"){
        alert("手机号格式有误");
        return false;
      }
      return true;
    }

</script>
</html>
