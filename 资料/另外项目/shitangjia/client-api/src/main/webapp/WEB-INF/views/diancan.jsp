<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>

<!doctype html>
<html>
<base>
<head>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%
        String path = request.getContextPath();
        String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
    %>
    <base href="<%=basePath%>">
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
    <title>${canteenName}</title>
    <link href="css/base.css" rel="stylesheet">
    <script src="js/tool.js"></script>
    <link href="css/diancan.css" rel="stylesheet">
    <link href="css/new_top.css" rel="stylesheet">
</head>
<body style="background: url(image/bg.png)">
<div class="main">
    <div class="container2 " style="margin-top: 0%;">
        <div class="fix_top">
            <div class="new_top" >
                <a href="user/toIndexPage"> <img src="image/back.gif" class="back_btn"></a>
                <div class="top_title" style="width: 64%"><span class="title">点餐</span></div>
                <a href="order/toOrderConfirmPage"><img src="image/cart.png" class="cart"></a>
                <div class="product_count">${productCount}</div>
            </div>
            <div class="nav_bar_div week_color">
                <c:forEach var="week" items="${week}" varStatus="weekStatus" >
                    <c:choose>
                        <c:when test="${weekStatus.index==0}">
                            <div class="nav_col btn_color_1" id="week_${weekStatus.index+1}" data-date="${week.value}">
                               <%-- <div style="width: 0.02rem;border-left: 0.01rem #ffffff dashed;float: left;height: 100%"></div>--%>
                                <span class="week week_color">${week.key}</span>

                            </div>
                        </c:when>
                        <c:otherwise>
                            <div class="nav_col btn_color_2" id="week_${weekStatus.index+1}" data-date="${week.value}">
                                <div style="width: 0.02rem;border-left: 0.01rem #ffffff dashed;float: left;height: 100%"></div>
                                <span class="week week_color">${week.key}</span>
                            </div>
                        </c:otherwise>
                    </c:choose>
                </c:forEach>
            </div>
            <div class="dinner_type_div">
                <div class="radio_div">
                    <div class="radio_col">
                       <%-- <input type="radio" name="dinner_radio" id="zao" class="radio" value="1">--%>
                        <div class="detail_time" data-id="1"></div>
                        <span class="radio_label">早</span>
                    </div>
                    <div class="radio_col">
                        <%--<input type="radio" name="dinner_radio" id="zhong" class="radio" checked="checked" value="2">--%>
                        <div class="detail_time detail_time_checked" data-id="2" id="noon"></div>
                        <span class="radio_label">中</span>
                    </div>
                    <div class="radio_col">
                        <%--<input type="radio" name="dinner_radio" id="night" class="radio" value="3">--%>
                        <div class="detail_time" data-id="3"></div>
                        <span class="radio_label">晚</span>
                    </div>

                </div>
            </div>
        </div>
        <div class="food_list" id="foodList">
        </div>
        <div class="footer" id="footer">
            <div class="count_div">
                <span id="all_count"></span>
            </div>
            <span class="all_price" id="all_price"></span>
            <a id="have_selected"><button class="have_check" >选好了</button></a>
        </div>
    </div>
</div>
<script src="js/jquery-2.1.1.js"></script>
</body>

<script>
    //订单相关变量定义
    var foodArray = new Array();
    var count = 0;
    var allPrice = 0;
    var allCount = 0;
    //日期相关定义
    var date = new Date();
    var fullYear = date.getFullYear();
    var month = date.getMonth()+1;
    var day = date.getDate();
    //生成菜品对象
    function createFood(id, count, price) {
        var o = new Object();
        o.id = id;
        o.count = count;
        o.price = price;
        o.getAllCount = function () {
            return this.count;
        }
        return o;//使用return返回生成的对象实例
    }
    function getProducts(dateTime ,timeNode){
        $.post("/product/list",{
            date:dateTime,
            timeNode:timeNode
        },function(products){
            if(confirmAjaxResult(products)){
                var data = products.data;
                var html = "";
                for(var i = 0 ; i<data.length;i++){
                    var productPojo = data[i];
                    html+="<div class=\"food_son_list\">\n" +
                    "<div class=\"type_div\">\n" +
                    "<span class=\"food_type_text\">"+productPojo.categoryName+"</span>\n" +
                    "</div>";
                    var sonProducts = productPojo.products;
                    for(var j=0 ;j<sonProducts.length;j++){
                        var sonProduct = sonProducts[j];
                        var pid = sonProduct.id;
                        var haveSelectedCount=0;

                        var foodDetail="";
                        if(sonProduct.description.length<=8){
                            foodDetail=sonProduct.description;
                        }else{
                            foodDetail=(sonProduct.description).substr(0,8)+"...";
                        }

                        for(var k=0;k<foodArray.length;k++){
                            if(sonProduct.id==foodArray[k].id){
                                haveSelectedCount=foodArray[k].count;
                            }
                        }
                        html+="<div class=\"food_item\">\n" +
                        "                    <img src=\"http://qingcai-images.bj.bcebos.com/"+sonProduct.img+"\" class=\"food_img\">\n" +
                        "                    <div class=\"food_info\">\n" +
                        "                        <span class=\"food_name\">"+sonProduct.name+"</span>\n" +
                        "                    <span class=\"food_detail\">"+foodDetail+"</span>" +
                        "                        <span class=\"food_price\" id=\"food_price_"+pid+"\" data-price=\""+sonProduct.price+"\" >"+sonProduct.price+"元/份</span>\n" +
                        "                    </div>\n" +
                        "                    <div class=\"food_item_right\">\n" ;
                        if(haveSelectedCount>0){
                            html+="<div class=\"subtract_div_1\" id=\"sub_"+pid+"\"  data-id=\""+pid+"\"><span class=\"btn_c\">-</span></div>\n" +
                            "<div class=\"food_count_1\" id=\"food_"+pid+"\"><span class=\"btn_c\" id=\"food_count_"+pid+"\">"+haveSelectedCount+"</span></div>\n" ;
                        }else{
                            html+="<div class=\"subtract_div\" id=\"sub_"+pid+"\"  data-id=\""+pid+"\"><span class=\"btn_c\">-</span></div>\n" +
                            "<div class=\"food_count\" id=\"food_"+pid+"\"><span class=\"btn_c\" id=\"food_count_"+pid+"\">1</span></div>\n";
                        }

                         html+="<div class=\"add_div\" id=\"add_"+pid+"\" data-id=\""+pid+"\" data-time=\""+sonProduct.createdTime+"\"><span class=\"btn_c\">+</span></div>\n" +
                        "                    </div>\n" +
                        "                </div>";
                    }
                    html +="</div>";
                }
                html+="<div class=\"footer2\"></div>";

                $("#foodList").html("");
                $("#foodList").append(html);
            }

        })
    }

    function validateDate(createTime){
        var currtTime = new Date(fullYear+"/"+month+"/"+day+" 00:00:00").getTime()
        if(createTime<currtTime){
            return false;
        }
        if(createTime == currtTime){
            //判断时间节点 当前时间是 10点之后就不能定早餐 14点之后不能定午餐 20点之后不能定晚餐
            var hour = date.getHours();
            //var timeNode = $("input[name='dinner_radio']:checked").val();
            var timeNode=$(".detail_time_checked").data("id");
            if((timeNode==1&&hour>10||(timeNode == 2&&hour>14)||(timeNode == 3 &&hour>20))) {
                 return false;
            }
        }
        return true;

    }
    $(function () {
        //加载菜品(默认加载当天中午的菜品)
        $("#week_"+date.getDay()).addClass("week_clicked_color");
        getProducts(fullYear+"-"+month+"-"+day,2);
        $(".nav_col").click(function(){
            $(".nav_col").removeClass("week_clicked_color");
            var date=$(this).data("date");
            $(this).addClass("week_clicked_color");
            var id=$(".detail_time_checked").data("id");
            //getProducts(date,$("input[name='dinner_radio']:checked").val());
            getProducts(date,id);
        });
        /*$("input[name='dinner_radio']").click(function(){
            getProducts($(".week_clicked_color").data("date"),$(this).val());
        });*/
        $(".detail_time").click(function () {
            var id=$(this).data("id");
            $(".detail_time").removeClass("detail_time_checked");
            $(this).addClass("detail_time_checked");
            getProducts($(".week_clicked_color").data("date"),id);
        });
        $("#foodList").on('click',".add_div",function(){
            var time = $(this).data("time");
            if(!validateDate(time)){
                alert("您不能预订之前的菜单！");
                return;
            }
            var id = $(this).data("id");
            var food;
            for (var i = 0; i < foodArray.length; i++) {
                var foodTemp = foodArray[i];
                if (foodTemp.id == id) {
                    foodTemp.count = foodTemp.count + 1;
                    food = foodTemp;
                    allCount = allCount + 1;
                }
            }
            if (food == null) {
                food = createFood(id, 1, $("#food_price_"+id).data("price"));
                foodArray.push(food);
                allCount = allCount + 1;
            }
            allPrice = allPrice + food.price;
            $("#all_count").html(allCount);
            $("#all_price").html(allPrice + "元");
            $("#sub_" + id).show();
            $("#food_count_" + id).html(food.count);
            $("#food_" + id).show();
            $("#footer").show();
        });
        $("#foodList").on('click',".subtract_div",function(){
            var id = $(this).data("id");
            var food;
            for (var i = 0; i < foodArray.length; i++) {
                var foodTemp = foodArray[i];
                if (foodTemp.id == id) {
                    if (foodTemp.count > 0) {
                        foodTemp.count = foodTemp.count - 1;
                        food = foodTemp;
                        allCount = allCount - 1;
                        allPrice = allPrice - food.price;
                    } else {
                        food = foodTemp;
                    }
                }
            }
            $("#all_count").html(allCount);
            $("#all_price").html(allPrice + "元");
            if (food.count == 0) {
                $("#sub_" + id).hide();
                $("#food_" + id).hide();
            } else {
                $("#food_count_" + id).html(food.count);
            }
            if (allCount == 0) {
                $("#footer").hide();
            }
        });

        $("#have_selected").click(function () {
            //alert("进入选择");
            //alert(JSON.stringify(foodArray));
            $.ajax({
                type: "POST",
                url: "order/addToCart",
                dataType: "json",
                data: JSON.stringify(foodArray),
                contentType: "application/json",
                success: function (res) {
                    var result = jQuery.parseJSON(JSON.stringify(res));
                    if(confirmAjaxResult(result)) {
                        window.location = "order/toOrderConfirmPage"
                    }

                }
            });
        });
    });

</script>
</html>
