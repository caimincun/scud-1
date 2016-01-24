<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2015/9/6
  Time: 17:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
  <title></title>
</head>
<body>



<%--<style>--%>
<%--.order_detail{--%>
<%--width: 80%;--%>
<%--float: left;--%>
<%--border: 1px #c8c8c8 solid;--%>
<%--min-height: 500px;--%>
<%--}--%>
<%--.order_row{--%>
<%--width: 100%;--%>
<%--float: left;--%>
<%--height: 40px;--%>
<%--border-bottom: 1px #c8c8c8 solid;--%>
<%--}--%>
<%--.order_col{--%>
<%--text-align: center;--%>
<%--float: left;--%>
<%--width: 24%;--%>
<%--height: 100%;--%>
<%--border-right:1px #c8c8c8 solid ;--%>

<%--}--%>
<%--.text{--%>
<%--display: block;--%>
<%--margin-top: 12px;--%>
<%--}--%>
<%--.btn{--%>
<%--width: 50%;--%>
<%--margin-top: 3px;--%>
<%--border: 0;--%>
<%--background-color: #129cfe;--%>
<%--color: #ffffff;--%>
<%--height:80%;--%>
<%--}--%>
<%--</style>--%>
<%--<div class="order_detail">--%>
<%--<div class="order_row">--%>
<%--<div class="order_col"><span class="text">订单号</span></div>--%>
<%--<div class="order_col"><span class="text">下单时间</span></div>--%>
<%--<div class="order_col"><span class="text">总价</span></div>--%>
<%--<div class="order_col" style="border-right: 0px"><span class="text">订单明细</span></div>--%>
<%--</div>--%>
<%--<c:forEach var="order" items="${orders}">--%>
<%--<div class="order_row">--%>
<%--<div class="order_col"><span class="text">${order.orderNum}</span></div>--%>
<%--<div class="order_col"><span class="text">${order.orderDate}</span></div>--%>
<%--<div class="order_col" style="border-right: 0px"><span class="text">${order.totalPrice}</span></div>--%>
<%--<div class="order_col" style="border-right: 0px">--%>
<%--<button class="btn">订单明细</button>--%>
<%--</div>--%>
<%--</div>--%>
<%--</c:forEach>--%>



<%--</div>--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
  String path = request.getContextPath();
  String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<base href="<%=basePath%>">

<html>
<head>
  <title></title>

  <link href="assets/css/bootstrap.min.css" rel="stylesheet" />
  <link rel="stylesheet" href="assets/css/font-awesome.min.css" />
  <link rel="stylesheet" href="assets/css/ace.min.css" />
  <link rel="stylesheet" href="assets/css/ace-rtl.min.css" />
  <link rel="stylesheet" href="assets/css/ace-skins.min.css" />
  <script src="assets/js/ace-extra.min.js"></script>
  <script src="js/jquery-2.1.1.js"></script>
  <script src="js/My97DatePicker/WdatePicker.js"></script>
  <style>
    table{width:100%;text-align:center;border-collapse:collapse;border-spacing:0; }
    th{text-align:center}
  </style>
</head>
<body>



<div class="row">
  <div class="col-xs-12">
    <h3 class="header smaller lighter blue">订单列表</h3>


    <div class="table-responsive">
      <table id="sample-table-3" class="table table-striped table-bordered table-hover">
        <thead>
        <tr>
          <th >订单号</th>
          <th>下单时间</th>
          <th>总价</th>
          <th>订单明细</th>
        </tr>
        </thead>
      </table>
    </div>
  </div>
</div>

<div class="modal fade padding-top" id="oDetail" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" style="display: none">
  <div class="modal-dialog">
    <div class="modal-content">
      <div>
        <table id="orderDetail" class="table table-bordered" style="border:1px">
          <thead>
          <tr>
          <tr>
            <th width="50">样式</th>
            <th>菜单</th>
            <th >价钱</th>
            <th> 描述</th>
            <th>数量</th>
          </tr>
          </tr>
          </thead>
        </table>
      </div>
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

<!-- page specific plugin scripts -->

<script src="assets/js/jquery.dataTables.min.js"></script>
<script src="assets/js/jquery.dataTables.bootstrap.js"></script>

<!-- ace scripts -->

<script src="assets/js/ace-elements.min.js"></script>
<script src="assets/js/ace.min.js"></script>

<!-- inline scripts related to this page -->
<script type="text/javascript">

  var tab;
  $(document).ready(function() {
    tab = $("#sample-table-3").dataTable({

      "bLengthChange": false,
      "bFilter": false,// 去掉搜索框
      "iDisplayLength" : 5,// 每页显示行数
      "bProcessing": false, // 是否显示取数据时的那个等待提示
      "bServerSide": true,//这个用来指明是通过服务端来取数据
      "sAjaxSource": "/user/orderDetail?id=${id}",//这个是请求的地址
      "fnServerData": retrieveData ,// 获取数据的处理函数
      "bInfo" : true,//左下角显示信息
      "bSort":false,
//      "sPaginationType" : "full_numbers",
      "oLanguage" : { //主要用于设置各种提示文本
        "sProcessing" : "正在处理...", //设置进度条显示文本
        "sLengthMenu" : "每页_MENU_行",//显示每页多少条记录
        "sEmptyTable" : "没有找到记录",//没有记录时显示的文本
        "sZeroRecords" : "没有找到记录",//没有记录时显示的文本
        "sInfo" : "总记录数_TOTAL_当前显示_START_至_END_",
        "sInfoEmpty" : "",//没记录时,关于记录数的显示文本
        "sSearch" : "搜索:",//搜索框前的文本设置
        "oPaginate" : {
          "sFirst" : "首页",
          "sLast" : "未页",
          "sNext" : "下页",
          "sPrevious" : "上页"
        }
      }//用于设置提示文本

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


  var flag = true;
  var tab2;
function userOrderDetail(id){
  if(flag){
    flag = false;
    tab2 = $("#orderDetail").dataTable({
      "bLengthChange": false,
//      bAutoWidth:false,//自适应宽度
      "bFilter": false,// 去掉搜索框
      "iDisplayLength" : 5,// 每页显示行数
      "bProcessing": false, // 是否显示取数据时的那个等待提示
      "bServerSide": true,//这个用来指明是通过服务端来取数据
      "sAjaxSource": "/order/orderDetail?orderId="+id,//这个是请求的地址
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
    $("#oDetail").modal('show');
  }else{
    tab2.fnSettings().sAjaxSource="/order/orderDetail?orderId="+id;
    tab2.fnDraw();
    $("#oDetail").modal('show');
  }
}


</script>
<script src="/js/resizeIframe.js"></script>
</body>
</html>


</body>
</html>
