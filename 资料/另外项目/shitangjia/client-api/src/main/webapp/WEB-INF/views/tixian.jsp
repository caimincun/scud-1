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
  <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
  <title>${canteenName}</title>
  <link href="css/bootstrap.min.css" rel="stylesheet">
  <link href="css/base.css" rel="stylesheet">
  <script src="js/jquery-2.1.1.js"></script>
  <link href="css/recharge.css" rel="stylesheet">
  <link href="css/new_top.css" rel="stylesheet">

</head>
<body>

<div class="main">
  <div class="container2">
    <div class="new_top">
      <a href="/user/toBalancePage" ><img src="image/back.gif" class="back_btn"></a>
      <div class="top_title"><span class="title">提现</span></div>
        <a href="user/toIndexPage"><img src="image/home.png" class="home"></a>
    </div>

    <form style="margin-top: 10px;"  method="post" id="tixianForm">
      <div class="form-group">
        <label for="balance">提现金额</label>
        <input type="text" class="form-control" id="balance" name="balance" placeholder="单笔最高2000元,最低0.01元">
      </div>
      <div class="form-group">
        <label for="account">提现账户</label>
        <select class="form-control" id="account" name="account">
          <option value="-1" selected="selected">请选择提现账户</option>
          <option value="0">微信</option>
          <option value="1">支付宝</option>
        </select>
      </div>
      <div class="form-group">
        <label for="realName">联系人姓名</label>
        <input type="text" class="form-control" name="realName" id="realName" placeholder="与您账户对应的联系人姓名">
      </div>
      <div class="form-group">
        <label for="phone">联系人电话</label>
        <input type="tel" class="form-control" name="phone" id="phone" value="${phone}" placeholder="方便我们与您取得联系">
      </div>
      <div id="err" style="color: #ff7241"></div>
      <button type="submit" class="btn btn-lg btn-default" style="float: right" onclick="submitForm()"> 提 交  </button>
    </form>

  </div>
</div>



</body>
<script>
  function validate(){
      $err = $("#err");
      var balance = $("#balance").val();
      if(balance==""||isNaN(balance)){
        $err.html("请输入有效的提现金额");
        return false;
      }
      if(balance.indexOf(".")!=-1&&balance.split(".")[1].length>2){
        $err.html("提现金额只能精确小数点后两位");
        return false;
      }
      var balanceFloat = parseFloat(balance);
      if(balanceFloat>2000||balanceFloat<0.01){
        $err.html("提现金额只能在0.01元到2000元");
        return false;
      }
      if($("#account").val()==-1){
        $err.html("请选择账户类型");
        return false;
      }
      if($("#realName").val()==""){
        $err.html("联系人姓名不能为空");
        return false;
      }
      return true;
  }
  function submitForm(){
      if(validate()) {
          console.log("提现");
          $.ajax({
              cache: true,
              type: "POST",
              url: "/user/tixian",
              data: $('#tixianForm').serialize(),// 你的formid
              async: false,
              error: function (request) {
                  alert("提现出错!请致电400 119 2918");
              },
              success: function (data) {
                  if(data.result==0) {
                      alert("提现申请成功!请耐心等待!");
                      window.location="/user/toBalancePage";
                  }else{
                      alert(data.msg);
                  }
              }
          });
      }
  }
</script>
</html>
