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
              <h3 class="header smaller lighter blue">菜单列表</h3>
              <%--<input id="d11" type="text" onClick="WdatePicker()"/>--%>

              <form>
              请选择想要查看的菜单日期：<input id="d12" type="text" />
              <img onclick="WdatePicker({el:'d12'})" src="js/My97DatePicker/skin/datePicker.gif" width="16" height="22" align="absmiddle">
                <input type="button" value="提交" onclick="search()">
              </form>
              <%--请选择想要查看的日期：<input type="date" name="createdTime">--%>

              <div class="table-responsive">
                <table id="sample-table-2" class="table table-striped table-bordered table-hover">
                  <thead>
                  <tr>
                    <th>菜单样式</th>
                    <th>名称</th>
                    <th >价钱</th>
                    <th> 描述</th>
                    <th>供应时间</th>
                    <th>供应时段</th>
                    <th>菜品类别</th>
                    <th>操作</th>
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

<!-- page specific plugin scripts -->

<script src="assets/js/jquery.dataTables.min.js"></script>
<script src="assets/js/jquery.dataTables.bootstrap.js"></script>

<!-- ace scripts -->

<script src="assets/js/ace-elements.min.js"></script>
<script src="assets/js/ace.min.js"></script>

<!-- inline scripts related to this page -->
<script type="text/javascript">

  function deleteProduct(id){
    window.location.href="/product/deleteProduct?id="+id;
  }

  function update(id){
    window.location.href="/product/getProduct?id="+id;
  }

  var tab;
  $(document).ready(function() {
    tab = $("#sample-table-2").dataTable({

      "bLengthChange": false,
      "bFilter": false,// 去掉搜索框
      "iDisplayLength" : 5,// 每页显示行数
      "bProcessing": false, // 是否显示取数据时的那个等待提示
      "bServerSide": true,//这个用来指明是通过服务端来取数据
      "sAjaxSource": "/product/productListsPage",//这个是请求的地址
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
    var val = $("#d12").val();
    if(val){
      tab.fnSettings().sAjaxSource='product/productListsPage?searchDate=' +val;
      tab.fnDraw();
    }

  }
</script>
          <script src="/js/resizeIframe.js"></script>
</body>
</html>
