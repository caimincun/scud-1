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
    <%--<input id="d11" type="text" onClick="WdatePicker()"/>--%>

    <form>
      订单日期：
      <input id="d13" type="text" />
      <img onclick="WdatePicker({el:'d13'})" src="js/My97DatePicker/skin/datePicker.gif" width="16" height="22" align="absmiddle">
      <select name="status" id="sta">
        <option value="0">未取餐</option>
        <option value="1">已取餐</option>
        <option value="2">转赠</option>
        <option value="3">取消</option>
      </select>
      <select name="isPayed" id="pay">
        <option value="1">已支付</option>
        <option value="0">未支付</option>
      </select>
      <input type="button" value="查询" onclick="search()">
    </form>
    <%--请选择想要查看的日期：<input type="date" name="createdTime">--%>

    <div class="table-responsive">
      <table id="orderTable" class="table table-striped table-bordered table-hover">
        <thead>
        <tr>
          <th >订单号</th>
          <th>订单日期</th>
          <th >订单人名称</th>
          <th> 订单状态</th>
          <th>订单总价</th>
          <th>是否付钱</th>
          <th>取餐时间</th>
          <th>详情</th>
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

  var flag = true;
  var tab2;
  function detailOrder(id){
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

  var tab;
  $(document).ready(function() {
    tab = $("#orderTable").dataTable({
      "bLengthChange": false,
      "bFilter": false,// 去掉搜索框
      "iDisplayLength" : 10,// 每页显示行数
      "bProcessing": false, // 是否显示取数据时的那个等待提示
      "bServerSide": true,//这个用来指明是通过服务端来取数据
      "sAjaxSource":"/order/listOrder",//这个是请求的地址
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

  function search(){
    var sta = $("#sta").val();
    var pay = $("#pay").val();
    var order_date = $("#d13").val();
//    alert(sta+"  "+pay);
    if(sta && pay){
      tab.fnSettings().sAjaxSource='/order/listOrder?sta=' +sta+'&pay='+pay+'&order_date='+order_date;
      tab.fnDraw();
    }

  }

</script>
<script src="/js/resizeIframe.js"></script>
</body>
</html>
