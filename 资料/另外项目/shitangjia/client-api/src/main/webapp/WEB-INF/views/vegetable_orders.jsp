<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>

<!doctype html>
<html>
<base>
<head>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
    <%
        String path = request.getContextPath();
        String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
    %>
    <base href="<%=basePath%>">
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>${canteenName}</title>
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <link href="css/base.css" rel="stylesheet">
    <link href="css/new_top.css" rel="stylesheet">
    <link href="css/orders.css" rel="stylesheet">
    <script src="js/jquery-2.1.1.js"></script>
    <script src="js/bootstrap.min.js" ></script>
    <script src="js/tool.js"></script>
    <style>
        .border_right_div{
            border-right: 0.01rem #ffffff dashed;
            width: 0.02rem;
            height: 0.4rem;
            float: right;
        }
    </style>
</head>
<body style="background: url(image/bg.png)">
<div class="main">
    <div class="container2">
        <div class="new_top">
            <a href="user/toPersonCenterPage"><img src="image/back.gif" class="back_btn"></a>

            <div class="top_title"><span class="title">买菜记录</span></div>
            <a href="user/toIndexPage"><img src="image/home.png" class="home"></a>
        </div>
        <div class="tab_div">
            <div class="tab_btn  l_radius tab_click_bg_color" id="wait_accept" data-id="0">
                <div style="float: left;width: 96%;text-align: center"><span class="tab_btn_text">待取菜</span></div>
                <div class="border_right_div"></div>
            </div>
            <div class="tab_btn" data-id="1">
                <div style="float: left;width: 96%;text-align: center"><span class="tab_btn_text">待确认</span></div>
                <div class="border_right_div"></div>
            </div>
            <div class="tab_btn" data-id="2">
                <div style="float: left;width: 96%;text-align: center"><span class="tab_btn_text">已完成</span></div>
                <div class="border_right_div"></div>
            </div>
            <div class="tab_btn r_radius" data-id="3"><span class="tab_btn_text">超时订单</span></div>
        </div>
        <div class="order_list">
        </div>
    </div>
</div>
<%@include file="qc_modal.jsp"%>
<form action="order/toShowVOrderCodePage" method="post" style="display:none" id="pay_order_form">
    <input type="hidden" value="" name="orderId" id="pay_order_id">
</form>
<style>
    .real_money{
        font-size: 0.2rem;
        width: 100%;
        height: 0.45rem;
        text-align: center;
    }
    .pwd_div{
        display: none;
        font-size: 0.2rem;
        width: 100%;
        height: 0.45rem;
    }
    .money_text{
        display: block;
        margin-top: 0.1rem;
    }
    .alert_footer{
        padding-top: 0rem;
        width: 100%;
        height: 0.45rem;
    }
    .submit_btn{
        margin-top: 0.05rem;
        float: left;
        margin-left: 0.5rem;
        background-color: #129cfe;
        color: #ffffff;
        font-family: "Microsoft YaHei", \5FAE\8F6F\96C5\9ED1, STHei, \534E\6587\7EC6\9ED1, "Helvetica Neue", Helvetica, Arial, sans-serif;
        width: 20%;
    }
    .cancel_btn{
        margin-top: 0.05rem;
        float: right;
        margin-right: 0.5rem;
        background-color: #ff7c0d;
        color: #ffffff;
        font-family: "Microsoft YaHei", \5FAE\8F6F\96C5\9ED1, STHei, \534E\6587\7EC6\9ED1, "Helvetica Neue", Helvetica, Arial, sans-serif;
        width: 20%;
    }
    .pwd{
        border: 0.01rem solid #c8c8c8;
        border-radius: 0.05rem;
    }
    .pwd_text{
        display: none;
        font-size: 0.14rem;
        width: 100%;
        height: 0.2rem;
    }
</style>
</body>
<script>
    function   formatDate(now)   {
        var   year=now.getFullYear();
        var   month=now.getMonth()+1;
        var   date=now.getDate();
        var   hour=now.getHours();
        var   minute=now.getMinutes();
        var   second=now.getSeconds();
        return   year+"-"+month+"-"+date+"   "+hour+":"+minute+":"+second;
    }

    function getNextDay(d){
        var d1 = new Date(d);
        var time= d1.getTime()+1000*60*60*24;
        var d2 = new Date(time);
        return d2.getFullYear()+"-"+(d2.getMonth()+1)+"-"+d2.getDate();
    }
    function isPastOrder(t){
        var d=new Date(t);
        var d2=new Date(getNextDay(d.getFullYear()+"-"+(d.getMonth()+1)+"-"+d.getDate()));
        var t=d2.getFullYear()+"-"+(d2.getMonth()+1)+"-"+d2.getDate()+" "+"19:00:00";
        var d3=new Date(t);
        var currentDate=new Date();
        if(currentDate.getTime()<=d3.getTime()){
            return false;
        }else{
            return true;
        }
    }
    function judgeTime(t){
        var d=new Date();
        var t2=new Date(t);
        var   year=t2.getFullYear();
        var   month=t2.getMonth()+1;
        var   date=t2.getDate();
        var t1=year+"-"+month+"-"+date+" "+"21"+":"+"00"+":"+"00";
        var d2=new Date(t1);
        if(d.getTime()<=d2.getTime()){
            return true;
        }else{
            return false;
        }
    }

    $("#back").click(function () {
        window.location = "user/toPersonCenterPage";
    });

    function getOrders(isPayed, status,orderType) {
        $.post("order/getOrderPojos",
                {
                    isPayed: isPayed,
                    status: status,
                    orderType:orderType
                },
                function (res) {
                    if (confirmAjaxResult(res)) {
                        var html = "";
                        var orders = res.data;
                        for (var i = 0; i < orders.length; i++) {
                            var order = orders[i];
                            if(order.status==0){
                                if(!isPastOrder(order.orderDate)){


                                html+= "<div class=\"order_item\" id=\"order_item_" + order.orderId + "\">\n" +
                                "                    <div class=\"order_item_row\" style=\"background-color: rgba(253, 239, 144, 0.24)\">\n" +
                                "                        <span class=\"order_id_text\">\t订单号：</span>\n" +
                                "                        <span class=\"order_id\">" + order.orderNum + "</span>\n" +
                                "                        <img src=\"image/down.png\" class=\"down_img\" data-id=\"" + order.orderId + "\" id=\"down_"+order.orderId+"\">\n" +
                                "                        <img src=\"image/up.png\" class=\"up_img\" data-id=\"" + order.orderId + "\"  id=\"up_" + order.orderId + "\"\">\n" +
                                "                    </div>\n" +
                                "                    <div class=\"items\" data-id=\"0\" id=\"items_" + order.orderId + "\"></div>";


                                html+="<div class=\"order_item_row\">\n" +
                                "                            <span class=\"order_id_text\">下单时间:</span>\n" +
                                "                            <span class=\"order_id\">"+formatDate(new Date(order.orderDate))+"</span>" +
                                "</div>";

                                html+="<div class=\"order_item_row\">\n" +
                                "                            <span class=\"order_id_text\">冻结金额:</span>\n" +
                                "                            <span class=\"order_id\">"+order.totalPrice+"元</span>";

                                html+="</div>";
                                html+="<div class=\"order_item_row \">\n" +
                                "  <button class=\"btn margin_left_btn pay_btn\"   id=\"pay_order"+order.orderId+"\"     data-id=\""+order.orderId+"\">现在取菜</button>\n";
                                if(judgeTime(order.orderDate)){
                                    html+="  <button class=\"btn margin_right_btn cancel_btn\" id=\"cancel_order\" data-id=\""+order.orderId+"\">撤销</button>\n";
                                }
                                html+= "</div>";
                                html+="</div>"

                                }
                            }else{
                                html += "<div class=\"order_item\" id=\"order_item_" + order.orderId + "\">\n" +
                                "                    <div class=\"order_item_row\" style=\"background-color: rgba(253, 239, 144, 0.24)\">\n" +
                                "                        <span class=\"order_id_text\">\t订单号：</span>\n" +
                                "                        <span class=\"order_id\">" + order.orderNum + "</span>\n" +
                                "                        <img src=\"image/down.png\" class=\"down_img\" data-id=\"" + order.orderId + "\" id=\"down_"+order.orderId+"\">\n" +
                                "                        <img src=\"image/up.png\" class=\"up_img\" data-id=\"" + order.orderId + "\"  id=\"up_" + order.orderId + "\"\">\n" +
                                "                    </div>\n" +
                                "                    <div class=\"items\" data-id=\"0\" id=\"items_" + order.orderId + "\"></div>";


                                html+="<div class=\"order_item_row\">\n" +
                                "                            <span class=\"order_id_text\">下单时间:</span>\n" +
                                "                            <span class=\"order_id\">"+formatDate(new Date(order.orderDate))+"</span>" +
                                "</div>";
                                html+="<div class=\"order_item_row\">\n" +
                                "                            <span class=\"order_id_text\">总价:</span>\n" +
                                "                            <span class=\"order_id\">"+order.totalPrice+"元</span>";
                                html+="</div>";
                                if(order.status==1){
                                    html+="<div class=\"order_item_row \">\n" +
                                    "  <button class=\"btn margin_right_btn confirm_pay_btn\" id=\"confirm_pay\" data-id=\""+order.orderId+"\">确认付款</button>\n" +
                                    "</div>";
                                }
                                html+="</div>"
                            }
                            /*html += "<div class=\"order_item\" id=\"order_item_" + order.orderId + "\">\n" +
                            "                    <div class=\"order_item_row\" style=\"background-color: rgba(253, 239, 144, 0.24)\">\n" +
                            "                        <span class=\"order_id_text\">\t订单号：</span>\n" +
                            "                        <span class=\"order_id\">" + order.orderNum + "</span>\n" +
                            "                        <img src=\"image/down.png\" class=\"down_img\" data-id=\"" + order.orderId + "\" id=\"down_"+order.orderId+"\">\n" +
                            "                        <img src=\"image/up.png\" class=\"up_img\" data-id=\"" + order.orderId + "\"  id=\"up_" + order.orderId + "\"\">\n" +
                            "                    </div>\n" +
                            "                    <div class=\"items\" data-id=\"0\" id=\"items_" + order.orderId + "\"></div>";


                            html+="<div class=\"order_item_row\">\n" +
                            "                            <span class=\"order_id_text\">下单时间:</span>\n" +
                            "                            <span class=\"order_id\">"+formatDate(new Date(order.orderDate))+"</span>" +
                            "</div>";*/

                            /*if(order.status==0){
                                html+="<div class=\"order_item_row\">\n" +
                                "                            <span class=\"order_id_text\">冻结金额:</span>\n" +
                                "                            <span class=\"order_id\">"+order.totalPrice+"元</span>";

                            }else{
                                html+="<div class=\"order_item_row\">\n" +
                                "                            <span class=\"order_id_text\">总价:</span>\n" +
                                "                            <span class=\"order_id\">"+order.totalPrice+"元</span>";

                            }
                            html+="</div>";

                            if(order.status==0){
                                html+="<div class=\"order_item_row \">\n" +
                                "  <button class=\"btn margin_left_btn pay_btn\"   id=\"pay_order"+order.orderId+"\"     data-id=\""+order.orderId+"\">现在取菜</button>\n";
                                if(judgeTime(order.orderDate)){
                                    html+="  <button class=\"btn margin_right_btn cancel_btn\" id=\"cancel_order\" data-id=\""+order.orderId+"\">撤销</button>\n";
                                }
                                html+= "</div>";
                            }
                            if(order.status==1){
                                html+="<div class=\"order_item_row \">\n" +
                                "  <button class=\"btn margin_right_btn confirm_pay_btn\" id=\"confirm_pay\" data-id=\""+order.orderId+"\">确认付款</button>\n" +
                                "</div>";
                            }
                            html+="</div>"*/
                        }

                        $(".order_list").html('');
                        $(".order_list").append(html);
                    }
                });
    }

    function getOrderItems(orderId,orderType){
        $.post("order/getOrderItems",
                {
                    orderId:orderId,
                    orderType:orderType
                },
                function (res) {
                    if(confirmAjaxResult(res)){
                        var items=res.data;
                        var html='';
                        for (var j = 0; j < items.length; j++) {
                            var item = items[j];

                            html += "<div class=\"order_item_row item_row_bg\">\n" +
                            "                                <span class=\"text_margin_left\">" + item.productName + "</span>\n" +
                            "                                <span class=\"item_count\">" + item.count + "份</span>\n" +
                            "                                <span class=\"item_price\">￥" + item.price + "/份</span>\n" +
                            "                            </div>";

                        }
                        $("#items_"+orderId).html('');
                        $("#items_"+orderId).append(html);
                    }

                }

        );
    }

    var order_id;
        $(function () {
            getOrders(0,0,1);
            $("#myModal").modal('hide');
            $("#myModalLabel").html("确认支付");
            var mo_body_html="";
            mo_body_html+="<div class=\"real_money\">" +
            "<span class=\"money_text\" id=\"total_price\"></span></div>" +
            "<div class='pwd_text'><span>输入支付密码</span></div><div class=\"pwd_div\">" +
            "<input type='password' id='password' class='pwd'></div>"
            $("#modal-body").html('');
            $("#modal-body").append(mo_body_html);
            var mo_fottor_html="";
            mo_fottor_html+="<button type=\"butto\" class=\"btn submit_btn\"  id=\"sub_btn\">提交</button>"+
            "<button type=\"button\" class=\"btn  cancel_btn\" data-dismiss=\"modal\">取消</button>";
            $("#modal-footer").html('');
            $("#modal-footer").append(mo_fottor_html);

            $(".tab_btn").click(function () {
                var status = $(this).data("id");
                $(".tab_btn").removeClass("tab_click_bg_color");
                $(this).addClass("tab_click_bg_color");
                var isPayed=0;
                getOrders(isPayed,status,1);
            });

            $(".cancel_btn").click(function () {
                var id = $(this).data("id");
                $.post(
                        "order/deleteOrder",
                        {
                            orderId: id
                        },
                        function (data) {
                            var res = jQuery.parseJSON(data);
                            if (res.result == 0) {
                                $("#order_item_" + id).hide();
                            }

                        }, "text"
                );

            });
            $(".order_list").on("click",".down_img", function () {
                var id =$(this).data("id");
                var isLoad=$("#items_"+id).data("id");
                if(isLoad==0){
                    getOrderItems(id,1);
                    $("#items_"+id).attr("data-id",1);
                }
                $(".items").slideUp();
                $(".up_img").hide();
                $(".down_img").show();
                $(this).hide();
                $("#items_" + id).slideDown();
                $("#up_" + id).show();
            });

            $(".order_list").on("click",".up_img", function () {
                var id = $(this).data("id");
                $(this).hide();
                $("#items_" + id).slideUp();
                $("#down_" + id).show();
            });

            $(".order_list").on("click",".cancel_btn", function () {
                var id = $(this).data("id");
                if(confirm("确定撤销订单吗？")){
                    $.post(
                            "order/deleteOrder",
                            {
                                orderId: id,
                                orderType:1
                            },
                            function (data) {
                                var res = jQuery.parseJSON(data);
                                if (res.result == 0) {
                                    $("#order_item_" + id).hide();
                                }

                            }, "text"
                    );
                }

            })

            $(".order_list").on("click",".pay_btn", function () {
            var id = $(this).data("id");
            $("#pay_order_id").val(id);
                $("#pay_order_form").submit();
            });

            $(".order_list").on("click",".confirm_pay_btn", function () {
                var id = $(this).data("id");
                order_id=id;
                getConfirm(id);
            });
            $("#modal-footer").on("click","#sub_btn", function () {
                var password=$("#password").val();
                $.post("order/vegetable/confirm",
                        {
                            orderId:order_id,
                            pwd:password
                        },
                        function (res) {
                            if(res.result==0){
                                //$("#hide_form").submit();
                                $("#myModal").modal('hide');
                                $(".tab_btn").removeClass("tab_click_bg_color");
                                //$(this).addClass("tab_click_bg_color");
                                $("#wait_accept").addClass("tab_click_bg_color");
                                getOrders(0,0,1);
                            }else if(res.result==1008) {
                                $("#myModal").modal('hide');
                                alert(res.msg + ",去设置");
                                window.location.href = "user/toPaySetPage";
                            }else{
                                $("#myModal").modal('hide');
                                alert(res.msg);
                                $("#password").val('');
                                $("#myModal").modal('show');
                                }
                        });
            });

        });

    function getConfirm(orderId) {
        $.post("order/vegetable/checkConfirm", {
            orderId: orderId
        }, function (res) {
            if (res.result == 0) {
                var totalPrice=res.totalPrice;
                $("#total_price").html("￥"+totalPrice+"元");
                var safeAmount=res.safeAmount;
                if(totalPrice>=safeAmount){
                    $(".pwd_text").show();
                    $(".pwd_div").show();
                }
                $("#myModal").modal('show');
            }
        });
    }
</script>
</html>
