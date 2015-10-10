<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>

<!doctype html>
<html>
<base >
<head>
  <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
  <%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
  <%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
  <%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
  %>
  <base href="<%=basePath%>">
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title>${canteenName}</title>
  <link href="css/base.css" rel="stylesheet">
  <link href="css/new_top.css" rel="stylesheet">
  <link href="css/qucan.css" rel="stylesheet">
  <script src="js/jquery-2.1.1.js"></script>
  <script src="js/jquery.qrcode.min.js"></script>
  <script>
    function toUtf8(str) {
      var out, i, len, c;
      out = "";
      len = str.length;
      for(i = 0; i < len; i++) {
        c = str.charCodeAt(i);
        if ((c >= 0x0001) && (c <= 0x007F)) {
          out += str.charAt(i);
        } else if (c > 0x07FF) {
          out += String.fromCharCode(0xE0 | ((c >> 12) & 0x0F));
          out += String.fromCharCode(0x80 | ((c >>  6) & 0x3F));
          out += String.fromCharCode(0x80 | ((c >>  0) & 0x3F));
        } else {
          out += String.fromCharCode(0xC0 | ((c >>  6) & 0x1F));
          out += String.fromCharCode(0x80 | ((c >>  0) & 0x3F));
        }
      }
      return out;
    }
  </script>
</head>
<body style="background: url(image/bg.png)">
<div class="main">
  <div class="container2">
  <div class="new_top">
    <a href="user/toIndexPage"><img src="image/back.gif" class="back_btn"></a>
    <div class="top_title"><span class="title">取餐</span></div>
    <a href="user/toIndexPage"><img src="image/home.png" class="home"></a>
  </div>
  <div class="alert_info">
    <span>请出示二维码给工作人员</span>
  </div>
  <div class="order_list">
    <c:choose>
      <c:when test="${count==0}">
        <div style="width: 100%;float: left;margin-top: 0.5rem;text-align: center;font-size: 0.15rem">
          您当前没有可取订单
        </div>
      </c:when>
      <c:otherwise>
      <c:forEach var="order"   begin="0" end="${count}" items="${orders}" varStatus="status">
      <div class="order">
        <div class="order_info_div">
          <span class="text">订单号:</span>
          <span class="text">${order.orderNum}</span>
          <c:choose>
          <c:when test="${status.index==0}">
          <img src="image/up.png" class="up_img" data-id="${order.orderNum}" id="up_${order.orderNum}">
          <img src="image/down.png" class="down_img" data-id="${order.orderNum}" id="down_${order.orderNum}" style="display: none">
        </div>
        <div class="code_div" id="code_${order.orderNum}">
          <div id="two_code_${order.orderNum}" class="code_img one"><script>
            $(".one").qrcode({
              render: "canvas",
              width: 200,
              height:200,
              text: "${order.orderNum}"
            });
          </script></div>
          <div class="order_info_div2">
            <div class="order_row">
            <span class="order_row_left">取餐时间</span>
              <span class="order_row_right">
                <c:forEach var="item" items="${order.orderItems}" varStatus="status">
                  <c:if test="${status.index==0}">
                    <fmt:formatDate value="${item.product.createdTime}" type="both" pattern="MM-dd"/>
                    <c:if test="${item.product.timeNode==1}">
                      早上
                    </c:if>
                    <c:if test="${item.product.timeNode==2}">
                      中午
                    </c:if>
                    <c:if test="${item.product.timeNode==3}">
                      晚上
                    </c:if>
                  </c:if>
                </c:forEach>
              </span>
            </div>
            <c:forEach var="item" items="${order.orderItems}">
              <div class="order_row">
                <span class="order_row_left">${item.product.name}</span>
                <span class="order_row_right">${item.product.price}元/份</span>
              </div>
            </c:forEach>
            <div class="order_row2">
              <span class="order_row_left">总价</span>
              <span class="order_row_right">${order.totalPrice}元</span>
            </div>
          </div>
        </div>
        </c:when>
        <c:otherwise>
        <img src="image/up.png" class="up_img" data-id="${order.orderNum}" id="up_${order.orderNum}" style="display: none">
        <img src="image/down.png" class="down_img" data-id="${order.orderNum}" id="down_${order.orderNum}" >
      </div>
      <div class="code_div hide" id="code_${order.orderNum}">
        <div id="two_code_${order.orderNum}" class="code_img"></div>
        <div class="order_info_div2">
          <div class="order_row">
            <span class="order_row_left">取餐时间</span>
              <span class="order_row_right">
                <c:forEach var="item" items="${order.orderItems}" varStatus="status">
                  <c:if test="${status.index==0}">
                    <fmt:formatDate value="${item.product.createdTime}" type="both" pattern="MM-dd"/>
                    <c:if test="${item.product.timeNode==1}">
                      早上
                    </c:if>
                    <c:if test="${item.product.timeNode==2}">
                      中午
                    </c:if>
                    <c:if test="${item.product.timeNode==3}">
                      晚上
                    </c:if>
                  </c:if>
                </c:forEach>
              </span>
          </div>
          <c:forEach var="item" items="${order.orderItems}">
            <div class="order_row">
              <span class="order_row_left">${item.product.name}</span>
              <span class="order_row_right">${item.product.price}元/份</span>
            </div>
          </c:forEach>
          <div class="order_row2">
            <span class="order_row_left">总价</span>
            <span class="order_row_right">${order.totalPrice}元</span>
          </div>

        </div>
      </div>
      </c:otherwise>
      </c:choose>
  </div>
    </c:forEach>
      </c:otherwise>
    </c:choose>

  </div>
</div>
</body>

<script>
  $(function(){
    $(".up_img").click(function () {
      var id=$(this).data("id");
      $(this).hide();
      $("#code_" + id).slideUp();
      $("#down_" + id).show();
    });
    $(".down_img").click(function () {
      var id=$(this).data("id");
      console.log(id);
      $(".code_div").hide();
      $(".up_img").hide();
      $(".down_img").show();
      $(this).hide();
      $twocod = $("#two_code_"+id);
      $twocod.html('');
      $twocod.qrcode({
        render: "canvas",
        text:""+id,
        width: 200,
        height:200
      });
      $("#code_" + id).slideDown();
      $("#up_" + id).show();
    });

  });
</script>
</html>
