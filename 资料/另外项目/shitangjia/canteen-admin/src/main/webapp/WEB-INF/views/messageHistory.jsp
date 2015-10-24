<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
  String path = request.getContextPath();
  String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <base href="<%=basePath%>">
  <meta charset="utf-8"/>
  <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
  <link href="assets/css/bootstrap.min.css" rel="stylesheet" />
  <link rel="stylesheet" href="assets/css/font-awesome.min.css" />
  <link rel="stylesheet" href="assets/css/ace.min.css" />
  <link rel="stylesheet" href="assets/css/ace-rtl.min.css" />
  <link rel="stylesheet" href="assets/css/ace-skins.min.css" />
  <script src="assets/js/ace-extra.min.js"></script>
  <script src="js/jquery-2.1.1.js"></script>
  <style>
    table{width:100%;text-align:center;border-collapse:collapse;border-spacing:0; }
  </style>
</head>
<body style="background-color: #ffffff">

<table class="table table-striped table-bordered" id="messageTable">
    <thead>
    <tr>
        <th style="text-align: center;">消息内容</th>
        <th style="text-align: center;">时间</th>
        <th style="text-align: center;">是否已读</th>
        <th style="text-align: center; width: 30%">操作</th>
    </tr>
    </thead>
    <tbody>
    </tbody>
</table>

<%--用餐订单--%>
<div class="modal fade padding-top" id="myDetail" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" style="display: none">
  <div class="modal-dialog">
    <div class="modal-content">
      <div>
        <table id="orderMsg" class="table table-bordered">
          <thead>
          <tr>
            <th width="50">样式</th>
            <th>菜单</th>
            <th >价钱</th>
            <th> 描述</th>
            <th>数量</th>
          </tr>
          </thead>
        </table>
      </div>
    </div>
  </div>
</div>

<%--蔬菜订单--%>
<div class="modal fade padding-top" id="veDetail" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" style="display: none">
  <div class="modal-dialog">
    <div class="modal-content">
      <div>
        <table id="veMsg" class="table table-bordered">
          <thead>
          <tr>
            <th width="50" align="center">样式</th>
            <th>蔬菜名</th>
            <%--<th >蔬菜类别</th>--%>
            <th> 单价</th>
            <th>数量</th>
          </tr>
          </thead>
        </table>
      </div>
    </div>
  </div>
</div>
<%--<script src="assets/js/ace-elements.min.js"></script>--%>
<script src="assets/js/bootstrap.min.js"></script>
<script src="assets/js/typeahead-bs2.min.js"></script>
<script src="assets/js/jquery.dataTables.min.js"></script>
<script src="assets/js/jquery.dataTables.bootstrap.js"></script>
<script src="assets/js/ace-elements.min.js"></script>
<script src="assets/js/ace.min.js"></script>
<!-- inline scripts related to this page -->

<script type="text/javascript">
  var flag = true;
  var tab2 ;
  var tab3;

  var messageType =${messageType} ; //0点餐订单 1蔬菜订单
  var isRead = 0;//已读未读 0已读1未读
  //加载数据
  $('#messageTable').dataTable({
      "bAutoWidth": false,
      "bServerSide": true,
      "bSort":false,
      "bPaginate":true,
      "iDisplayLength":5,
      "bFilter":false,     //是否开启客户端过滤功能
      "bLengthChange":false,
      "bInfo" : false,
      "sAjaxSource": "/message/list",
      "sServerMethod": "POST",
      "fnServerData": function ( sSource,aoData , fnCallback ) {
          $.ajax({
              "dataType": 'json',
              "type": "POST",
              "url": sSource,
              "data":{
                  "aoData":JSON.stringify(aoData),
                  "messageType":messageType,
                  "isRead":isRead
              },
              "success": function (result){
                  fnCallback(result);
                  resizeIframe();
              }
          });
      },
      "aoColumns": [
          { "mDataProp": "messageType","fnRender": function(obj,messageType) {

              if(messageType==0){
                  str = "您有一条新的点餐订单";
              }else if(messageType==1){
                  str = "您有一条新的买菜订单";
              }

              return str;
          }},
          { "mDataProp": "createDate"},
          { "mDataProp": "isRead","fnRender": function(obj,isRead) {
              var str = "";
                  if(isRead=="0"){
                      str="未读";
                  }else if(isRead=="1"){
                      str = "已读";
                  }
              return "<span id='msgRead"+obj.aData.id+"'>"+str+"</span>";
          }},
          { "mDataProp": "targetId","fnRender": function(obj,targetId) {
              return "<a style='cursor:pointer' onclick='readMsg("+obj.aData.id+","+messageType+","+targetId+")'>查看详情</a>";
          }}
//          ,
//          { "mDataProp": "id","fnRender": function(obj,isRead) {
//              return "标记为已读";
//          }}
      ],
      "oLanguage" : {
          "sLengthMenu" : "每页显示 _MENU_ 条记录",
          "sZeroRecords" : "对不起，没有匹配的数据",
          "sInfo" : "第 _START_ - _END_ 条 / 共 _TOTAL_ 条数据",
          "sInfoEmpty" : "没有匹配的数据",
          "sInfoFiltered" : "(数据表中共 _MAX_ 条记录)",
          "sProcessing" : "正在加载中...",
          "sSearch" : "全文搜索：",
          "oPaginate" : {
              "sFirst" : "第一页",
              "sPrevious" : " 上一页 ",
              "sNext" : " 下一页 ",
              "sLast" : " 最后一页 "
          }
      }
  });


  function readMsg( msgId,messageType,targetId){
    if(flag && messageType == 0){
      flag = false;
      tab2 = $("#orderMsg").dataTable({
        "bLengthChange": false,
//      bAutoWidth:false,//自适应宽度
        "bFilter": false,// 去掉搜索框
        "iDisplayLength" : 5,// 每页显示行数
        "bProcessing": false, // 是否显示取数据时的那个等待提示
        "bServerSide": true,//这个用来指明是通过服务端来取数据
        "sAjaxSource": "/order/orderDetail?orderId="+targetId,//这个是请求的地址
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
      $("#myDetail").modal('show');
    }else if(!flag && messageType == 0){
      tab2.fnSettings().sAjaxSource="/order/orderDetail?orderId="+targetId,
      tab2.fnDraw();
      $("#myDetail").modal('show');
    }
    else if(flag && messageType == 1){
      flag = false;
      tab3 = $("#veMsg").dataTable({
        "bLengthChange": false,
//      bAutoWidth:false,//自适应宽度
        "bFilter": false,// 去掉搜索框
        "iDisplayLength" : 5,// 每页显示行数
        "bProcessing": false, // 是否显示取数据时的那个等待提示
        "bServerSide": true,//这个用来指明是通过服务端来取数据
        "sAjaxSource": "/store/getVege?vegeId="+targetId,//这个是请求的地址
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
      $("#veDetail").modal('show');
    }
    else if(!flag && messageType == 1){
      tab3.fnSettings().sAjaxSource="/store/getVege?vegeId="+targetId, tab3.fnDraw();
      $("#veDetail").modal('show');
    }

    var $msgRead = $("#msgRead"+msgId);
    if($msgRead.html()=="未读"){
      $.post("message/updateMsg",{
        msgId:msgId
      },function(res){
        if(res.result==0){
          $msgRead.html("已读");
          window.parent.titleNumSub(messageType);
        }
      });
    }
  }

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
<script src="/js/resizeIframe.js"></script>
</body>
</html>