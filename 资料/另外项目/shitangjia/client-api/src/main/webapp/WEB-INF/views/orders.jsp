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
    <link href="css/base.css" rel="stylesheet">
    <link href="css/new_top.css" rel="stylesheet">
    <link href="css/orders.css" rel="stylesheet">
    <script src="js/jquery-2.1.1.js"></script>
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
            <div class="top_title"><span class="title">订餐记录</span></div>
            <a href="user/toIndexPage"><img src="image/home.png" class="home"></a>
        </div>
        <div class="tab_div">
            <div class="tab_btn2  l_radius tab_click_bg_color" data-id="1">
                <div style="float: left;width: 96%;text-align: center"><span class="tab_btn_text">待支付</span></div>
                <div class="border_right_div"></div>
            </div>
            <div class="tab_btn2" data-id="2">
                <div style="float: left;width: 96%;text-align: center"><span class="tab_btn_text">待取餐</span></div>
                <div class="border_right_div"></div>
            </div>
            <div class="tab_btn2 r_radius" data-id="3"><span class="tab_btn_text">已取餐</span></div>
        </div>
        <div class="order_list">
        </div>
    </div>
</div>
<form action="order/toPayOrderPage" method="post" style="display:none" id="pay_order_form">
    <input type="hidden" value="" name="orderId" id="pay_order_id">
    <input type="hidden" value="0" name="orderType">
</form>
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
                            html += "<div class=\"order_item\" id=\"order_item_" + order.orderId + "\">\n" +
                            "                    <div class=\"order_item_row\" style=\"background-color: rgba(253, 239, 144, 0.24)\">\n" +
                            "                        <span class=\"order_id_text\">\t订单号：</span>\n" +
                            "                        <span class=\"order_id\">" + order.orderNum + "</span>\n" +
                            "                        <img src=\"image/down.png\" class=\"down_img\" data-id=\"" + order.orderId + "\" id=\"down_"+order.orderId+"\">\n" +
                            "                        <img src=\"image/up.png\" class=\"up_img\" data-id=\"" + order.orderId + "\"  id=\"up_" + order.orderId + "\"\">\n" +
                            "                    </div>\n" +
                            "                    <div class=\"items\" data-id=\"0\" id=\"items_" + order.orderId + "\"></div>";
                            /*for (var j = 0; j < order.orderItems.length; j++) {
                                var item = order.orderItems[j];

                                html += "<div class=\"order_item_row\">\n" +
                                "                                <span class=\"text_margin_left\">" + item.product.name + "</span>\n" +
                                "                                <span class=\"item_count\">" + item.count + "份</span>\n" +
                                "                                <span class=\"item_price\">￥" + item.product.price + "/粉</span>\n" +
                                "                            </div>";

                            }*/

                            html+="<div class=\"order_item_row\">\n" +
                            "                            <span class=\"order_id_text\">订餐时间:</span>\n" +
                            "                            <span class=\"order_id\">"+formatDate(new Date(order.orderDate))+"</span>" +
                            "                            <span class=\"all_price\">￥"+order.totalPrice+"元</span>\n" +
                            "                            <span class=\"all_price_text\">总价:</span>\n" +
                            "                        </div>";

                            if(order.status!=1&&order.isPayed!=1){

                                html+="<div class=\"order_item_row \">\n" +
                                "  <button class=\"btn margin_left_btn pay_btn\"   id=\"pay_order"+order.orderId+"\"     data-id=\""+order.orderId+"\">支付</button>\n"+
                                "  <button class=\"btn margin_right_btn cancel_btn\"   id=\"cancel_order"+order.orderId+"\"     data-id=\""+order.orderId+"\">撤销</button>\n" +
                                "</div>";
                            }
                            html+="</div>"
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
                            "                                <span class=\"item_price\">￥" + item.price + "/粉</span>\n" +
                            "                            </div>";

                        }
                        $("#items_"+orderId).html('');
                        $("#items_"+orderId).append(html);
                    }

                }

        );
    }

        $(function () {
            getOrders(0, 0,0);
            $(".tab_btn2").click(function () {
                var param = $(this).data("id");
                $(".tab_btn2").removeClass("tab_click_bg_color");
                $(this).addClass("tab_click_bg_color");
                var isPayed=0;
                var status=0;
                if (param == 1) {
                    isPayed = 0;
                    status = 0
                }
                if (param == 2) {
                    isPayed = 1;
                    status = 0;
                }
                if (param == 3) {
                    isPayed = 1;
                    status = 1
                }
                getOrders(isPayed,status,0);
            });

            $(".btn").click(function () {
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
                    getOrderItems(id,0);
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
                var flag=confirm("真的要撤销订单么？")
                if(flag==true){
                    $.post(
                            "order/deleteOrder",
                            {
                                orderId: id,
                                orderType:0
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
            })

        });
</script>
</html>
