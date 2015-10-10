<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
  String path = request.getContextPath();
  String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<base href="<%=basePath%>">

<html>
<head>
  <title></title>
  <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
  <link href="assets/css/bootstrap.min.css" rel="stylesheet" />
  <link rel="stylesheet" href="assets/css/font-awesome.min.css" />
  <link rel="stylesheet" href="assets/css/ace.min.css" />
  <link rel="stylesheet" href="assets/css/ace-rtl.min.css" />
  <link rel="stylesheet" href="assets/css/ace-skins.min.css" />
  <script src="js/excel.js"/>
  <script src="assets/js/ace-extra.min.js"></script>
  <script src="js/jquery-2.1.1.js"></script>
  <script src="js/My97DatePicker/WdatePicker.js"></script>
  <style>
    /*table{width:100%;text-align:center;border-collapse:collapse;border-spacing:0; }*/
    th{text-align:center}
  </style>
</head>
<body>



<div class="row">
  <div class="col-xs-12">
    <h3 class="header smaller lighter blue">订单列表</h3>


    <form>
      消费方式：
      <select name="consumeType" id="consumeType">
        <option value="0">充值</option>
        <option value="1">撤销订单退款</option>
        <option value="2">点餐扣费</option>
        <option value="3">买菜扣费</option>
      </select>账户类型：
      <select name="accountTpye" id="accountTpye">
        <option value="0">食堂账户</option>
        <option value="1">平台账户</option>
        <option value="2">线下转账</option>
      </select>
      <input type="button" value="查询" onclick="search()">
    </form>

    <button onclick="dayin()" id="test">打 印</button>
    &nbsp;  &nbsp;  &nbsp;  &nbsp;
    <button onclick="saveAsExcel('orderTable')" id="test3">下载</button>

    <div class="table-responsive">
      <table id="orderTable" class="table table-striped table-bordered table-hover">
        <thead>
        <tr>
          <th>用户名称</th>
          <th>账户类型</th>
          <th>消费方式</th>
          <th >消费金额</th>
          <th> 消费时间</th>
          <%--<th>订单总价</th>--%>
          <%--<th>是否付钱</th>--%>
          <%--<th>取餐时间</th>--%>
          <%--<th>详情</th>--%>
        </tr>
        </thead>
      </table>
    </div>
  </div>
</div>
<%--中部结束--%>

<script type="text/javascript">
  window.jQuery || document.write("<script src='assets/js/jquery-2.0.3.min.js'>"+"<"+"/script>");
</script>


<script type="text/javascript">
  if("ontouchend" in document) document.write("<script src='assets/js/jquery.mobile.custom.min.js'>"+"<"+"/script>");
</script>
<script src="assets/js/bootstrap.min.js"></script>
<script src="assets/js/typeahead-bs2.min.js"></script>
<script src="assets/js/jquery.dataTables.min.js"></script>
<script src="assets/js/jquery.dataTables.bootstrap.js"></script>
<script src="assets/js/ace-elements.min.js"></script>
<script src="assets/js/ace.min.js"></script>

<script type="text/javascript">



  var tab;
  $(document).ready(function() {
    tab = $("#orderTable").dataTable({
      "bLengthChange": false,
//      bAutoWidth:false,//自适应宽度
      "bFilter": false,// 去掉搜索框
      "iDisplayLength" : 5,// 每页显示行数
      "bProcessing": false, // 是否显示取数据时的那个等待提示
      "bServerSide": true,//这个用来指明是通过服务端来取数据
      "sAjaxSource": "/consume/listConsume",//这个是请求的地址
      "fnServerData": retrieveData ,// 获取数据的处理函数
      "bInfo" : false,//左下角显示信息
      "bSort":false,
      "oLanguage":{
        "sProcessing": "正在加载中......",
        "sLengthMenu": "每页显示 _MENU_ 条记录",
        "sZeroRecords": "对不起，查询不到相关数据！",
        "sEmptyTable": "对不起，查询不到相关数据！"
      }
    });
  });

  // 3个参数的名字可以随便命名,但必须是3个参数,少一个都不行
  function retrieveData( sSource111,aoData111, fnCallback111) {
    $.ajax({
      url : sSource111,//这个就是请求地址对应sAjaxSource
      data : {"aoData":JSON.stringify(aoData111)},//这个是把datatable的一些基本数据传给后台,比如起始位置,每页显示的行数
      type : 'post',
      dataType : 'json',
      async : false,
      success : function(result) {
        fnCallback111(result);//把返回的数据传给这个方法就可以了,datatable会自动绑定数据的
      },
      error : function(msg) {
      }
    });
  }

  function search(){
    var accountTpye = $("#accountTpye").val();
    var consumeType = $("#consumeType").val();
//    alert(accountTpye+"  "+consumeType);
      tab.fnSettings().sAjaxSource='/consume/listConsume?sta=' +accountTpye+'&pay='+consumeType;
      tab.fnDraw();

  }

  function dayin(){
    console.log($(document).height());
    var  $test= $("#test");
    $test.hide();
    window.print();
    $test.show();
  }
</script>
<script src="/js/resizeIframe.js"></script>
</body>
</html>
