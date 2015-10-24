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
  <link href="css/wait_shenpi_qiandan.css" rel="stylesheet">
  <link href="css/new_top.css" rel="stylesheet">
</head>
<body>
<div class="main">
  <div class="container2" >
    <div class="new_top">
      <a href="signbill/toSignBillPage"><img src="image/back.gif" class="back_btn"></a>
      <div class="top_title"><span class="title">待审批签单</span></div>
    </div>
    <div class="list">
      <div class="item">
        <div class="s_title">
          <span class="s_title_text">公务接待</span>
          <span class="s_time_text">2015-08-19</span>
        </div>
        <div class="s_content">
          <div class="s_content_row">
            <span class="row_head">用餐时间:</span>
            <span class="row_head">2015-08-20 12:00</span>
          </div>
          <div class="s_content_row">
            <span class="row_head">申请事由:</span>
            <p class="reason">
              &nbsp&nbsp&nbsp&nbsp&nbsp&nbsp申请人在选择审批链时，要选择相应的审批人，可以通过搜索姓名的方式查找具体的审批人（姓名+部门）。审批人群组由后台进行编辑，权限开放给食堂管理员。
              仅将审批人实行实名制（只有在审批时是实名制），控制实名制的范围，方便用户参与食堂事务。
            </p>
          </div>
          <div class="s_content_row">
            <span class="row_head">申请人:</span>
            <span class="row_head">张三</span>
          </div>
          <div class="s_content_row" >
            <span class="row_head">联系方式:</span>
            <span class="row_head">18782114166</span>
          </div>
          <div class="s_content_row" style="padding-bottom: 0.1rem">
            <span class="row_head">附件:</span>
            <span class="row_head"></span>
          </div>
        </div>
        <div class="btn_div">
          <div style="margin-left: auto;margin-right: auto;width: 70%">
            <div class="my_radio" data-id="1">
              <div class="my_radio_in" id="my_radio_in_1" style="display: block">
              </div>
            </div>
            <div class="radio_lable">
              同意
            </div>
            <div class="my_radio" data-id="2">
              <div class="my_radio_in" id="my_radio_in_2">
              </div>
            </div>
            <div class="radio_lable">
              不同意
            </div>
            <div class="my_radio" data-id="3">
              <div class="my_radio_in" id="my_radio_in_3">
              </div>
            </div>
            <div class="radio_lable">
              转发
            </div>
          </div>
        </div>

        <div class="btn_div">
          <button class="confirm">确定</button>
        </div>

      </div>

    </div>
  </div>
</div>
</body>
<script>
  $(function () {
    var id="1";
    $(".my_radio").click(function () {
      var checked=$(this).data("id");
      $(".my_radio_in").hide();
      $("#my_radio_in_"+checked).show();
      id=checked;
    });
  });
</script>
</html>
