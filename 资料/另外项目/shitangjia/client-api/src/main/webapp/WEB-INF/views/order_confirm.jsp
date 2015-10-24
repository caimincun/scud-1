<%@ page import="com.horadrim.talrasha.site.controller.request.CartItem" %>
<%@ page import="com.horadrim.talrasha.common.CommonParamDefined" %>
<%@ page import="java.util.Map" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!doctype html>
<html>
<base >
<head>
  <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
  <%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
  %>
  <base href="<%=basePath%>">
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title>${canteenName}</title>
  <link href="css/base.css" rel="stylesheet">
  <script src="js/tool.js"></script>
  <link href="css/new_top.css" rel="stylesheet">
  <link href="css/cart.css" rel="stylesheet">
</head>
<body style="background: url(image/bg.png);">
<div class="main">
  <div class="container2 " style="margin-top: 0%;">
    <div class="fix_top">
      <div class="new_top">
     <a href="product/toDianCanPage"><img src="image/back.gif" class="back_btn"></a>
        <div class="top_title"><span class="title">订单确认</span></div>
        <a href="user/toIndexPage"><img src="image/home.png" class="home"></a>
      </div>
    </div>
  <div class="food_list">
    <c:choose>
      <c:when test="${isCartNull!=0}">
        <c:forEach var="item" items="${cart}">
          <div class="food_item" id="food_item_${item.key}">
            <img src="http://qingcai-images.bj.bcebos.com/${item.value.img}" class="food_img">
            <div class="food_info">
              <span class="food_name">${item.value.name}</span>
              <span class="food_price">${item.value.price}元/份</span>
              <span class="food_time">取餐时间:<fmt:formatDate value="${item.value.acceptTime}" type="both" pattern="MM-dd"/>
              <c:if test="${item.value.timeNode==1}">
                早上
              </c:if>
              <c:if test="${item.value.timeNode==2}">
                  中午
              </c:if>
              <c:if test="${item.value.timeNode==3}">
                  晚上
              </c:if>
              </span>
            </div>
            <div class="food_item_right">
              <div class="subtract_div" id="sub_${item.key}" data-id="${item.key}"><span class="btn_c">-</span></div>
              <div class="food_count" id="food_${item.key}"><span class="btn_c" id="food_count_${item.key}">${item.value.count}</span></div>
              <div class="add_div" id="add_${item.key}" data-id="${item.key}"><span class="btn_c">+</span></div>
            </div>
          </div>
        </c:forEach>
      </c:when>
      <c:otherwise>
        <div style="width:100%;float: left;margin-top: 0.5rem;text-align: center">
          <span>您购物车还没有菜品</span>
          <a href="product/toDianCanPage">
            <button  style="width:60%;float:left;margin-left: 20%;
            margin-top: 0.5rem;height: 0.45rem;
            color:#ffffff;background-color: #ff7c0d;border: 0rem">去点餐</button></a>
        </div>
      </c:otherwise>
    </c:choose>
    <div class="footer2">
    </div>
  </div>
    <c:if test="${isCartNull==1}">
      <div class="footer">
        <div class="footer_bottom" id="footer">
          <div class="count_div" >
            <span id="all_count"></span>
          </div>
          <span class="all_price" id="all_price"></span>
          <button class="have_check" id="submit_order">提交订单</button>
        </div>
      </div>
    </c:if>
</div>
  </div>
<form id="hiddenForm" action="order/addOrder" method="post">

</form>
<script src="js/jquery-2.1.1.js"></script>
</body>
<script>
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
  var foodArray=new Array();
  <%
   Map<Integer,CartItem> map=((Map<Integer,CartItem>)request.getSession().getAttribute(CommonParamDefined.CART));
   int foodId=0;
   int count=0;
   double  price=0.00;
   CartItem item=null;
  if(null!=map){
  for(Integer key:map.keySet()){
          item=map.get(key);
          foodId=item.getId();
          count=item.getCount();
          price=item.getPrice().doubleValue();
           %>
   var food=createFood(<%=foodId%>,<%=count%>,<%=price%>)
   foodArray.push(food);
   <%
   }
   }
  %>

  var allPrice=${totalPrice};
  var allCount=${totalCount};
  $(function(){
    $("#all_count").html(allCount);
    $("#all_price").html(allPrice+"元");
    $(".add_div").click(function(){
      var id=$(this).data("id");
      var food;
      for(var i=0;i<foodArray.length;i++){
        var foodTemp=foodArray[i];
        if(foodTemp.id==id){
          foodTemp.count=foodTemp.count+1;
          food=foodTemp;
          allCount=allCount+1;
          allPrice=allPrice+food.price;
        }
      }

      $.ajax({
        type : "POST",
        url : "order/changeCart",
        dataType : "json",
        data : JSON.stringify(food),
        contentType : "application/json",
        success : function(res) {
          var result =  jQuery.parseJSON(JSON.stringify(res));
          if(confirmAjaxResult(result))
          {
            $("#food_count_"+id).html(result.count);
          }
        }
      });
      $("#all_count").html(allCount);
      $("#all_price").html(allPrice+"元");
    });
    $(".subtract_div").click(function(){
      var id=$(this).data("id");
      var food;
      for(var i=0;i<foodArray.length;i++){
        var foodTemp=foodArray[i];
        if(foodTemp.id==id){
          if(foodTemp.count>0){
            foodTemp.count=foodTemp.count-1;
            food=foodTemp;
            allCount=allCount-1;
            allPrice=allPrice-food.price;
          }else{
            food=foodTemp;
          }
        }
      }
      $.ajax({
        type : "POST",
        url : "order/changeCart",
        dataType : "json",
        data : JSON.stringify(food),
        contentType : "application/json",
        success : function(result) {
          //var result =  jQuery.parseJSON(JSON.stringify(res));
          if(confirmAjaxResult(result))
          {
            var num=result.count;
            if(0==num){
              $("#food_item_"+id).hide();
            }
            $("#food_count_"+id).html(num);
          }
        }
      });
      $("#all_count").html(allCount);
      $("#all_price").html(allPrice+"元");

      if(allCount==0){
        window.location="product/toDianCanPage"
      }
    });

    $("#submit_order").click(function(){
    $("#hiddenForm").submit();
    });
  });

</script>
</html>
