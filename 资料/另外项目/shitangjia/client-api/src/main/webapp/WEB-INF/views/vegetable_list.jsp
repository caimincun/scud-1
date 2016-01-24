<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!doctype html>
<head>
    <%
        String path = request.getContextPath();
        String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
    %>
    <base href="<%=basePath%>">
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
    <title>${canteenName}</title>
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <link href="css/base.css" rel="stylesheet">
    <script src="js/tool.js"></script>
    <link href="css/vegetable.css" rel="stylesheet">
    <link href="css/new_top.css" rel="stylesheet">
</head>
<body style="background-color: white">
<div class="main">
    <div class="container2">
        <div class="fix_top">
            <div class="new_top" style="width:100% ">
                <a href="user/toIndexPage"> <img src="image/back.gif" class="back_btn"></a>
                <div class="top_title" style="width: 60%;margin-left: 6%"><span class="title">买菜</span></div>
                <a href="order/toVOrderConfirmPage"><img src="image/cart.png" class="cart"></a>
                <div class="product_count">${productCount}</div>
            </div>
        <div class="top_border"></div>
        <div class="js-menu-bar" id="ma-menu-bar">
            <div class="menu-mask"><--请在此左右滑动切换分类--></div>
            <ul class="menu fl">
                <!--输出菜单栏-->
                <c:forEach items="${categories}" var="category" varStatus="categoryStatus">
                    <c:choose>
                        <c:when test="${categoryStatus.index==0}">
                            <li class="active" data-id="${category.id}">${category.categoryName}</li>
                        </c:when>
                        <c:otherwise>
                            <li data-id="${category.id}">${category.categoryName}</li>
                        </c:otherwise>
                    </c:choose>
                </c:forEach>
            </ul>
            <!--<div style="height:2px;background_collor:red"></div>-->
            <!-- <div class="clear"></div> -->
        </div>
        <div class="hiddenScroll"></div>
        </div>
        <div class="food_list" id="food_list">
        </div>
        <div class="footer2"> </div>
        <div class="footer" id="footer">
            <div class="count_div">
                <span id="all_count"></span>
            </div>
            <span class="all_price" id="all_price"></span>
            <button class="have_check" id="have_selected">选好了</button>
        </div>
    </div>
</div>
<script src="js/jquery-2.1.1.js"></script>
</body>
<script src="/js/tool.js"></script>
<script type="text/javascript">
    var vegetables = ${vegetables};
    var categories = ${categories};
    var data = [];
    var foodArray = new Array();
    var count = 0;
    var allPrice = 0;
    var allCount = 0;
    function initData() {
        for (var i = 0; i < categories.length; i++) {
            var cid = categories[i].id;
            for (var j = 0; j < vegetables.length; j++) {
                var vegetable = vegetables[j];
                if (vegetable.vegetableCategoryId == cid) {
                    var objs = data[cid];
                    if (objs == null) {
                        objs = new Array();
                    }
                    objs.push(vegetable);
                    data[cid] = objs;
                }
            }
        }
        $("#food_list").html(genericContent($(".active").data("id")));
    }
    function genericContent(categoryId){
        var vegetables = data[categoryId];
        var html="";
        if(vegetables==undefined) {
            html+="<div style=\"width: 100%;float:left;text-align: center;margin-top: 0.5rem;font-size: 0.14rem\">当前分类没有菜品哦！</div>";
            return html;
        }
        for(var i = 0 ; i<vegetables.length;i++){
            var vegetable = vegetables[i];
            var id = vegetable.id;
            html+=" <div class=\"food_item\">" +
            "                <img src=\"http://qingcai-images.bj.bcebos.com/"+vegetable.img+"\" class=\"food_img\">" +
            "                <div class=\"food_info\">" +
            "                    <span class=\"food_name\">"+vegetable.vegetableName+"</span>" +
            "                    <span class=\"food_price\">"+vegetable.unitPrice+""+vegetable.unitName+"</span>" +
            "                </div>" +
            "                <div class=\"food_item_right\">" +
            "                    <div class=\"subtract_div\" id=\"sub_"+id+"\" data-id=\""+id+"\">-</div>" +
            "                    <div class=\"food_count\" id=\"food_"+id+"\"><span id=\"food_count_"+id+"\">1</span></div>" +
            "                    <div class=\"add_div\" id=\"add_"+id+"\" data-id=\""+id+"\" data-price=\""+vegetable.unitPrice+"\">+</div>" +
            "                </div>" +
            "            </div>";
        }
        return html;
    }
    function createFood(id, count, price) {
        var o = new Object();
        o.id = id;
        o.count = count;
        o.price = price;
        o.getAllCount = function () {
            return this.count;
        };
        return o;//使用return返回生成的对象实例
    }


    $(function(){
        initData();
        $food_list = $("#food_list");
        $('.menu-mask').on('touchstart click', function(){
            $(this).hide();
        });
        $(".menu li").click(function () {
            $(".menu li").removeClass("active");
            $(this).addClass("active");
            $("#food_list").html(genericContent($(this).data("id")));
        });
        $food_list.on('click',".add_div",function(){
            var id = $(this).data("id");
            var price = $(this).data("price");
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
                food = createFood(id, 1, price);
                foodArray.push(food);
                allCount = allCount + 1;
            }
            allPrice =accAdd(allPrice,food.price);
            $("#all_count").html(allCount);
            $("#all_price").html(allPrice + "元");
            $("#sub_" + id).show();
            $("#food_count_" + id).html(food.count);
            $("#food_" + id).show();
            $("#footer").show();
        });
        $food_list.on('click',".subtract_div",function(){
            var id = $(this).data("id");
            var food;
            for (var i = 0; i < foodArray.length; i++) {
                var foodTemp = foodArray[i];
                if (foodTemp.id == id) {
                    if (foodTemp.count > 0) {
                        foodTemp.count = foodTemp.count - 1;
                        food = foodTemp;
                        allCount = allCount - 1;
                        allPrice = accSub(allPrice , food.price);
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
                url: "order/addToVegetableCart",
                dataType: "json",
                data: JSON.stringify(foodArray),
                contentType: "application/json",
                success: function (res) {
                    var result = jQuery.parseJSON(JSON.stringify(res));
                    if(confirmAjaxResult(result)) {
                        window.location = "order/toVOrderConfirmPage"
                    }
                }
            });
        });
    });
</script>
</html>
