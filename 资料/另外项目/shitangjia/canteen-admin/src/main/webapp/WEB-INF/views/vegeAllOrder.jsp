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
    table{width:100%;text-align:center;border-collapse:collapse;border-spacing:0; }
    th{text-align:center}
  </style>
</head>
<body>

      <button onclick="gerendingdanhuizhogndayin()" id="daying">打 印</button>
      <button onclick="saveAsExcel('veOrTable')" id="xiazaivege">下载</button>
      <input  name="btmExcel"  class="button1" type="button" id="btmExcel" value="导出当前页" onclick="toExcel();">
      <input  name="btmExcel"  class="button1" type="button" id="btmExcel" value="导出当前页"onclick="saveCode('veOrTable');">

      <table id="veOrTable" class="table table-striped table-bordered table-hover">
        <thead>
        <tr>
          <th width="50px">序列号</th>
          <th width="50px">姓名</th>
          <th width="50px">联系方式</th>
          <th width="300px">订单内容</th>
        </tr>
        </thead>
      </table>

      <script language='javascript'>
        function saveCode(obj){

          var winname = window.open('', '_blank', 'top=10000');
          var strHTML = document.all.tableExcel.innerHTML;

          winname.document.open('text/html', 'replace');
          winname.document.writeln(strHTML);
          winname.document.execCommand('saveas','',' 假植情况报表.xls');
          winname.close();
        }

        function toExcel(){
          window.clipboardData.setData("Text",document.all('veOrTable').outerHTML);
          try
          {
            var ExApp = new ActiveXObject("Excel.Application");
            var ExWBk = ExApp.workbooks.add();
            var ExWSh = ExWBk.worksheets(1);
            ExApp.DisplayAlerts = false;
            ExApp.visible = true;
          }
          catch(e)
          {
            alert(e.message);
            return false;
          }
          ExWBk.worksheets(1).Paste;
        }
      </script>
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
    tab = $("#veOrTable").dataTable({

      "bLengthChange": false,
      "bFilter": false,// 去掉搜索框
      "iDisplayLength" : 10,// 每页显示行数
      "bProcessing": false, // 是否显示取数据时的那个等待提示
      "bServerSide": true,//这个用来指明是通过服务端来取数据
      "sAjaxSource": "/order/vegeAllOrder",//这个是请求的地址
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


</script>

      <script>
        function gerendingdanhuizhogndayin(){
          console.log($(document).height());
          var  $test= $("#daying");
          $test.hide();
          window.print();
          $test.show();
        }
        </script>
<script src="/js/resizeIframe.js"></script>
</body>
</html>
