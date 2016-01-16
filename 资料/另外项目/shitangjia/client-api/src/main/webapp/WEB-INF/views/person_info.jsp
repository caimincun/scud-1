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
  <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
  <title>${canteenName}</title>
  <link href="css/bootstrap.min.css" rel="stylesheet">
  <link href="css/base.css" rel="stylesheet">
  <script src="js/jquery-2.1.1.js"></script>
  <!--<script src="js/bootstrap.min.js"></script>-->
  <link href="css/person_detail.css" rel="stylesheet">
  <link href="css/new_top.css" rel="stylesheet">
  <script src="js/bootstrap.min.js" ></script>
  <script src="js/tool.js" ></script>
</head>
<body>
<div class="main">
  <div class="container2">
    <div class="new_top">
    <a href="user/toPersonCenterPage">  <img src="image/back.gif" class="back_btn"></a>
      <div class="top_title"><span class="title">个人信息</span></div>
      <a href="user/toIndexPage"><img src="image/home.png" class="home"></a>
    </div>
    <div class="info_list">
      <div class="info_row">
        <span class="row_title">昵称:</span>
        <c:choose>
          <c:when test="${user.nickName==null||user.nickName==''}">
            <div class="tow_right2" data-id="name" id="name">去设置</div>
          </c:when>
          <c:otherwise>
            <div class="tow_right2" data-id="name" id="name">${user.nickName}</div>
          </c:otherwise>
        </c:choose>
      </div>
      <%--<div class="info_row">
        <span class="row_title">姓别:</span>
        <c:choose>
          <c:when test="${user.sex==0}">
            <div class="tow_right" data-id="sex" id="sex">男</div>
          </c:when>
          <c:when test="${user.sex==1}">
            <div class="tow_right" data-id="sex" id="sex">女</div>
          </c:when>
          <c:otherwise>
            <div class="tow_right" data-id="sex" id="sex">去设置</div>
          </c:otherwise>
        </c:choose>
      </div>--%>
      <div class="info_row">
        <span class="row_title">手机号:</span>
        <c:choose>
          <c:when test="${user.phone==null||user.phone==''}">
            <div class="tow_right" data-id="phone" id="phone">去设置</div>
          </c:when>
          <c:otherwise>
            <div class="tow_right" data-id="phone" id="phone">${user.phone}</div>
          </c:otherwise>
        </c:choose>
      </div>
      <%--<div class="info_row">
        <span class="row_title">所在食堂:</span>
        <c:choose>
          <c:when test="${user.canteen==null||user.canteen==''}">
            <div class="tow_right" data-id="canteen" id="canteen">去设置</div>
          </c:when>
          <c:otherwise>
            <div class="tow_right" data-id="canteen" id="canteen">${canteen.canteenName}</div>
          </c:otherwise>
        </c:choose>
      </div>--%>
      <%--<div class="info_row">
        <span class="row_title">部门:</span>
        <c:choose>
          <c:when test="${user.department==null||user.department==''}">
            <div class="tow_right" data-id="part" id="part">去设置</div>
          </c:when>
          <c:otherwise>
            <div class="tow_right" data-id="part" id="part">${user.department}</div>
          </c:otherwise>
        </c:choose>
      </div>--%>
      <div class="info_row">
        <span class="row_title" >邮箱:</span>
        <c:choose>
          <c:when test="${user.email==null||user.email==''}">
            <div class="tow_right" data-id="email" id="email">去设置</div>
          </c:when>
          <c:otherwise>
            <div class="tow_right" data-id="email" id="email">${user.email}</div>
          </c:otherwise>
        </c:choose>
      </div>
      <div class="info_row">
        <span class="row_title" >QQ:</span>
        <c:choose>
          <c:when test="${user.qq==null||user.qq==''}">
            <div class="tow_right" data-id="qq" id="qq">去设置</div>
          </c:when>
          <c:otherwise>
            <div class="tow_right" data-id="qq" id="qq">${user.qq}</div>
          </c:otherwise>
        </c:choose>
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
            <div class="input_div" id="input_div">
            </div>
            <input type="hidden" value="" id="info_name">
           <div class="set_result" id="check_result">
              <span class="set_result_text" id="message" ></span>
            </div>
            <%--<div class="get_code_result" id="get_code_result">
               <div class="get_code_result_text" id="get_code_result_text">提醒：验证码已发送，请查收</div>
             </div>
             <div class="error_result" id="error_result">
               <div class="error_message" id="error_message" ></div>
             </div>--%>
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
  /*$("#back").click(function(){
    window.location="we_index.html";
  });*/
  $("#orders").click(function(){
    window.location="order/queryOrders"
  });
  $("#balance").click(function(){
    window.location="user/toBalancePage"
  });

  $(function () {
    $(".tow_right").click(function () {
      var value=$(this).html();
      var id=$(this).data("id");
      console.log(value);
      $("#myModal").modal('show');
      var common_html="<input type=\"text\" placeholder=\"\" class=\"pwd_input\" id=\"info_value\">";

      if(id=="name"){
        $("#input_div").html("");
        $("#input_div").append(common_html);
        if(value=="去设置"){
          $("#myModalLabel").html("设置姓名");
          $("#info_value").val("请输入姓名");
        }else{
          $("#myModalLabel").html("修改姓名");
          $("#info_value").val(value);
        }
      }

      if(id=="sex")
      {
        var index=0;
        if(value=="男"){
          index=0;
        }
        if(value=="女"){
          index=1;
        }
        if(value=="去设置"){
          $("#myModalLabel").html("设置性别");
          var html="<input type=\"radio\" name=\"sex\" value=\"0\" id=\"maile\" class=\"maile_radio\">"+
                  "<label for=\"maile\" class=\"label\">男</label>"+
                  "<input type=\"radio\" name=\"sex\" value=\"1\" id=\"femaile\" class=\"femaile_radio\">"+
                  "<label for=\"femaile\">女</label>";
          /*$("input[type=radio]").eq(index).attr("checked","checked");*/
          $("input[type=radio][value=index]").attr("checked",'checked');
          $("#input_div").html("");
          $("#input_div").append(html);
        }else{
          $("#myModalLabel").html("修改姓别");
          var html="<input type=\"radio\" name=\"sex\" value=\"0\" id=\"maile\">"+
          "<label for=\"maile\">男</label>"+
          "<input type=\"radio\" name=\"sex\" value=\"1\" id=\"femaile\">"+
          "<label for=\"femaile\">女</label>";
          $("input[name='sex']").eq(index).attr("checked","checked");
          $("#input_div").html("");
          $("#input_div").append(html);
        }
      }

      if(id=="phone")
      {
        $("#input_div").html("");
        $("#input_div").append(common_html);
        if(value=="去设置"){
          $("#myModalLabel").html("设置手机号");
          $("#info_value").val("请输入手机号");
        }else{
          $("#myModalLabel").html("修改手机号");
          $("#info_value").val(value);
        }
      }

      if(id=="canteen")
      {
        $("#input_div").html("");
        $("#input_div").append(common_html);
        if(value=="去设置"){
          $("#myModalLabel").html("设置餐厅");
          $("#info_value").val("请选择餐厅");
        }else{
          $("#myModalLabel").html("修改餐厅");
          $("#info_value").val(value);
        }
      }


      if(id=="part")
      {
        $("#input_div").html("");
        $("#input_div").append(common_html);
        if(value=="去设置"){
          $("#myModalLabel").html("设置部门");
          $("#info_value").val("请选择部门");
        }else{
          $("#myModalLabel").html("修改部门");
          $("#info_value").val(value);
        }
      }


      if(id=="email")
      {
        $("#input_div").html("");
        $("#input_div").append(common_html);
        if(value=="去设置"){
          $("#myModalLabel").html("设置邮箱");
          $("#info_value").val("请输入邮箱");
        }else{
          $("#myModalLabel").html("修改邮箱");
          $("#info_value").val(value);
        }
      }

      if(id=="qq")
      {
        $("#input_div").html("");
        $("#input_div").append(common_html);
        if(value=="去设置"){
          $("#myModalLabel").html("设置QQ");
          $("#info_value").val("请输入qq号");
        }else{
          $("#myModalLabel").html("修改QQ");
          $("#info_value").val(value);
        }
      }
      $("#info_name").val(id);
    });

    $("#input_div").on("click","#info_value", function () {
      $(this).val('');
    });
    /*$("#info_value").click(function () {

    });
*/
    $("#sub_btn").click(function(){
      var infoValue="";
      var infoName=$("#info_name").val();

      if(infoName=='sex'){
        infoValue=$("input[name='sex']:checked").val();
      }else{
        infoValue=$("#info_value").val();
      }

      if(infoValue.indexOf("输入")>0||infoValue==''){
        alert("请输入信息");
        return false;
      }
      $.post("user/updateUserInfo",
              {
                infoName:infoName,
                infoValue:infoValue
              },
              function(data){
                var result = jQuery.parseJSON(data);
                if(confirmAjaxResult(result)){
                  $("#message").html("修改成功");
                  $("#check_result").show();
                  if(infoName=='sex'){
                    if(infoValue==0){
                      $("#"+infoName).html("男");
                    }else{
                      $("#"+infoName).html("女");
                    }
                  }else{
                    $("#"+infoName).html(infoValue);
                  }
                  setTimeout(function(){
                    $("#check_result").hide();
                    $("#myModal").modal('hide');
                  },500);
                }
              },
              "text");
    });
  });
</script>
</html>
