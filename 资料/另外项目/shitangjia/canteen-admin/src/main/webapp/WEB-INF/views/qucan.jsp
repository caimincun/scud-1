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
    <link href="assets/css/bootstrap.min.css" rel="stylesheet"/>
    <link rel="stylesheet" href="assets/css/font-awesome.min.css"/>
    <link rel="stylesheet" href="assets/css/font-awesome-ie7.min.css"/>
    <!-- fonts -->
    <!-- ace styles -->
    <link rel="stylesheet" href="assets/css/ace.min.css"/>
    <link rel="stylesheet" href="assets/css/ace-rtl.min.css"/>
    <link rel="stylesheet" href="assets/css/ace-skins.min.css"/>
    <!--[if lte IE 8]>
    <link rel="stylesheet" href="assets/css/ace-ie.min.css"/>
    <![endif]-->
    <!-- inline styles related to this page -->
    <!-- ace settings handler -->
    <script src="assets/js/ace-extra.min.js"></script>
    <!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
    <script src="assets/js/html5shiv.js"></script>
    <script src="assets/js/respond.min.js"></script>
    <![endif]-->
    <style>
        .notPay{color: #ff0000}
        th{text-align:center}
    </style>
</head>
<body style="background-color: #ffffff">

<%--中部--%>
<div class="row">
    <div class="well-lg">
        <%-- <h4 class="green smaller lighter">Normal Well</h4>
         Use the well as a simple effect on an element to give it an inset effect.--%>
        <div class="form-group">
            <label class="col-sm-3 control-label no-padding-right" for="orderNum"> 订单号: </label>

            <div class="col-sm-9 input-group">
                <input type="text" id="orderNum" placeholder="订单号" maxlength="17" class="col-xs-10 col-sm-5">
                &nbsp;&nbsp;
                <button type="button" id ="getInfo" class="btn btn-default">取餐</button>
            </div>
        </div>
    </div>
    <div id="getFail">
        <span id="failMsg" style="margin-left: 40%;font-size: 20px;"></span>
    </div>
    <div id="getSuc">
        <div style="float:left;width:100%;font-size: 20px;background-color: rgb(130, 155, 210);color: white;height: 40px;line-height: 40px;">
            <div class="col-xs-2 col-sm-2 col-sm-offset-1 col-xs-offset-1">
                姓名：<span id="nickName"></span>
            </div>
            <div class="col-xs-3 col-sm-3">
               联系方式：<span id="phone"></span>
            </div>
            <div class="col-xs-3 col-sm-3">
                下单时间：<span id="orderDate"></span>
            </div>
            <div class="col-xs-2 col-sm-2">
               是否支付: <span id="isPayed"></span>
            </div>
            <%--   <div class="col-xs-12">
                   姓名：<span>张三</span>
                   联系方式：<span>13111111111</span>
                   下单时间：<span>2010-07-07</span>
                   是否支付: <span>未支付</span>
               </div>--%>
        </div>
        <div class="col-xs-12">
            <table class="table table-striped table-bordered">
                <thead>
                <tr>
                    <th class="center">#</th>
                    <th>菜品图片</th>
                    <th class="hidden-xs">菜品名称</th>
                    <th class="hidden-480">份数</th>
                </tr>
                </thead>
                <tbody id="orderContent">
                </tbody>
            </table>
        </div>
    </div>
</div>


<%--中部结尾--%>


<script type="text/javascript">
    window.jQuery || document.write("<script src='assets/js/jquery-2.0.3.min.js'>" + "<" + "script>");
</script>
<script type="text/javascript">
    if ("ontouchend" in document) document.write("<script src='assets/js/jquery.mobile.custom.min.js'>" + "<" + "script>");
</script>

<script src="assets/js/bootstrap.min.js"></script>
<script src="assets/js/typeahead-bs2.min.js"></script>

<script src="assets/js/excanvas.min.js"></script>

<script src="assets/js/jquery-ui-1.10.3.custom.min.js"></script>
<script src="assets/js/jquery.ui.touch-punch.min.js"></script>
<script src="assets/js/jquery.slimscroll.min.js"></script>
<script src="assets/js/jquery.easy-pie-chart.min.js"></script>
<script src="assets/js/jquery.sparkline.min.js"></script>
<script src="assets/js/flot/jquery.flot.min.js"></script>
<script src="assets/js/flot/jquery.flot.pie.min.js"></script>
<script src="assets/js/flot/jquery.flot.resize.min.js"></script>

<!-- ace scripts -->

<script src="assets/js/ace-elements.min.js"></script>
<script src="assets/js/ace.min.js"></script>
<script src="/js/resizeIframe.js"></script>
<!-- inline scripts related to this page -->

<script type="text/javascript">
    var lastTime = new Date().getTime()-5000;
    function getFocus(){
        var $orderNum = $("#orderNum");
        $orderNum.val("");
        $orderNum.focus();
    }
    function getInfo(orderNum){
        $.post("order/qucan",{
            orderNum:orderNum
        },function(res){
            if(res.result==0){
                //生成订单产品列表
                var html ="";
                var data = res.data;
                if(data.length>0) {
                    var order = data[0];
                    $("#nickName").html(order.nickname);
                    $("#phone").html(order.phone);
                    var date = new Date(order.orderDate);
                    var minutes = date.getMinutes();
                    $("#orderDate").html(date.getFullYear()+"-"+date.getMonth()+"-"+
                    date.getDate()+" "+date.getHours()+":"+(minutes<10?"0"+minutes:minutes));
                    var isPayed = order.isPayed;
                    if(isPayed==0){
                        $("#isPayed").addClass("notPay").html("未支付");
                    }else{
                        $("#isPayed").removeClass("notPay").html("已支付");
                    }
                    for (var i = 0; i < data.length; i++) {
                        order = data[i];
                        html += " <tr>" +
                        "           <td class=\"center\">" + (i + 1) + "</td>" +
                        "                <td><img src=\"http://qingcai-images.bj.bcebos.com/" + order.img + "\" width=\"50\" height=\"50\"></td>" +
                        "                <td class=\"hidden-xs\">" + order.name + "</td>" +
                        "                <td class=\"hidden-480\">" + order.pcount + "</td>" +
                        "            </tr>";
                    }
                }
                $("#orderContent").html(html);
                $("#getFail").hide();
                $("#getSuc").show();
                lastTime = new Date().getTime();
            }else{
                $("#failMsg").html(res.msg);
                $("#getFail").show();
                $("#getSuc").hide();
                getFocus();
            }
            resizeIframe();
        })
    }
    $(function(){
        getFocus();
        $("#getInfo").click(function(){
            getInfo($("#orderNum").val())
        });
        $("#orderNum").keyup(function(e) {
            var theEvent = window.event || e;
            var code = theEvent.keyCode || theEvent.which;
            if (code == 13) {
                var value = $(this).val();
                if (value.length != 17) {
                    return false;
                } else {
                    var time = new Date().getTime();
                    if ((time - lastTime) < 5000)
                        alert("请间隔5秒再操作");
                    else {
                        getInfo(value);
                        lastTime = time;
                    }
                    getFocus();
                }
            }
        });
//        $("#orderNum").change(function(){
//            var value = $(this).val();
//            if(value.length==17){
//                var time = new Date().getTime();
//                if((time-lastTime)<5000)
//                    alert("请间隔5秒再操作");
//                else {
//                    getInfo(value);
//                    lastTime = time;
//                }
//                getFocus();
//            }
//
//        });
    })
</script>
</body>
</html>