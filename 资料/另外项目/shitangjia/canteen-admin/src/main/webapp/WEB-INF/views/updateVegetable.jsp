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
  <%@ taglib prefix='fmt' uri="http://java.sun.com/jsp/jstl/fmt" %>
  <link href="assets/css/bootstrap.min.css" rel="stylesheet" />
  <link rel="stylesheet" href="assets/css/font-awesome.min.css" />
  <link rel="stylesheet" href="assets/css/ace.min.css" />
  <link rel="stylesheet" href="assets/css/ace-rtl.min.css" />
  <link rel="stylesheet" href="assets/css/ace-skins.min.css" />
  <script src="assets/js/ace-extra.min.js"></script>
  <script src="js/jquery-2.1.1.js"></script>
  <script src="js/My97DatePicker/WdatePicker.js"></script>"
  <style>
    table{width:100%;text-align:center;border-collapse:collapse;border-spacing:1;border-spacing:0; }
  </style>
</head>
<body>



<%--正文头部结束--%>
<!-- #好像这儿是页面中部 -->
<div class="page-content">
  <%--中部开始--%>


  <h1>
    修改菜单信息
  </h1>


  <%--<div class="row">--%>
  <%--<div class="col-xs-12">--%>
  <%--<div class="table-responsive">--%>

  <form action="/store/updateVegetable" enctype="multipart/form-data" method="post">
    <table border="1">
      <tr>
        <td>  销售类别：</td>
        <td>

          <select name="vegetableCategoryId" style="width: 167" required="required">
            <c:forEach var="vegetableCategorie" items="${vegetableCategories}">
              <c:choose>
                <c:when test="${vegetable.vegetableCategoryId == vegetableCategorie.id}">
                  <option value="${vegetableCategorie.id}" selected>${vegetableCategorie.categoryName}</option>
                </c:when>
                <c:otherwise>
                  <option value="${vegetableCategorie.id}">${vegetableCategorie.categoryName}</option>
                </c:otherwise>
              </c:choose>
            </c:forEach>
          </select>


          <input type="text" name="id" value="${vegetable.id}" hidden="ture"><br/>
          <input type="text" name="canteenId" value="${vegetable.canteenId}" hidden="true">
        </td>
      </tr>
      <tr>
        <td>蔬菜名：</td>
        <td><input type="text" value="${vegetable.vegetableName}" name="vegetableName" required="required" maxlength="10"></td>
      </tr>
      <tr>
        <td>单价:</td>
        <td><input type="number" value="${vegetable.unitPrice}" name="unitPrice" style="width: 167" required="required" min="0" step="0.01" max="10000"> </td>
      </tr>
      <tr>
        <td>单位：</td>
        <td>
          <%--<input type="text" value="${vegetable.unitName}" name="unitName" style="width: 167" required="required">--%>
          <select name="unitName"  style="width: 167" required="required">
            <option value="元/个" <c:if test="${vegetable.unitName eq  '元/个'}">selected </c:if> >元/个</option>
            <option value="元/斤" <c:if test="${vegetable.unitName eq  '元/斤'}">selected</c:if> >元/斤</option>
            <option value="元/袋" <c:if test="${vegetable.unitName eq  '元/袋'}">selected</c:if> >元/袋</option>.
            <option value="元/份" <c:if test="${vegetable.unitName eq  '元/份'}">selected</c:if> >元/份</option>
            <option value="元/50克" <c:if test="${vegetable.unitName eq  '元/50克'}">selected</c:if>>元/50克</option>
            <option value="元/升" <c:if test="${vegetable.unitName eq  '元/升'}">selected</c:if>>元/升</option>
            <option value="元/桶" <c:if test="${vegetable.unitName eq  '元/桶'}">selected</c:if>>元/桶</option>
            <option value="元/瓶" <c:if test="${vegetable.unitName eq  '元/瓶'}">selected</c:if>>元/瓶</option>
          </select>
        </td>
      </tr>
      <tr>
        <td>包装规格：</td>
        <td>
          <%--<input type="text" value="${vegetable.vegetableDescribe}" name="vegetableDescribe" maxlength="20">--%>
            <select name="vegetableDescribe" style="width: 167">
              <option value="一人份"  <c:if test="${vegetable.vegetableDescribe eq  '一人份'}">selected </c:if>>一人份</option>
              <option value="三人份"  <c:if test="${vegetable.vegetableDescribe eq  '三人份'}">selected </c:if>>三人份</option>
              <option value="5升/桶"  <c:if test="${vegetable.vegetableDescribe eq  '5升/桶'}">selected </c:if>>5升/桶"</option>
              <option value="500克/袋"  <c:if test="${vegetable.vegetableDescribe eq  '500克/袋'}">selected </c:if>>500/袋</option>
              <option value="500克/袋"  <c:if test="${vegetable.vegetableDescribe eq  '750ml/瓶'}">selected </c:if>>750ml/瓶</option>
            </select>
        </td>
      </tr>
      <tr>
        <td>
          描述：
        </td>
        <td>
          <input type="text" name="description" value="${vegetable.description}">
        </td>
      </tr>
      <tr>
        <td>剩余数量：</td>
        <td><input type="number" value="${vegetable.surplusNum}" name="surplusNum" min="0" style="width: 167" required="required"></td>
      </tr>
      <tr>
        <td>销售时间:</td>
        <td>

          <input type="date" name="saleTime" required="true" id="saletime"  style="width: 146"  value="<fmt:formatDate value="${vegetable.saleTime}" type="date" dateStyle="default"/>">
          <img onclick="WdatePicker({el:'saletime'})" src="js/My97DatePicker/skin/datePicker.gif" width="16" height="22" align="absmiddle">

          <%--<input type="text" name="saletime"  readonly="readonly"  id="saletime2">--%>
          <%--<script type="application/javascript">--%>
            <%--$(document).ready(function() {--%>
              <%--var AddDayCount = 1;--%>
              <%--var dd = new Date();--%>
              <%--dd.setDate(dd.getDate()+AddDayCount);//获取AddDayCount天后的日期--%>
              <%--var y = dd.getFullYear();--%>
              <%--var m = dd.getMonth()+1;//获取当前月份的日期--%>
              <%--var d = dd.getDate();--%>
              <%--time = y+"-"+m+"-"+d;--%>
              <%--$("#saletime2").val(time)--%>
            <%--});--%>
          <%--</script>--%>
        </td>
      </tr>
      <tr>
        <td>菜品样式：<img src="http://qingcai-images.bj.bcebos.com${vegetable.img}" width="40" height="40"><font style="color: #ff0000;"> <c:out value="${errorAlertContent}"/></font></td>
        <td>
          <input type="file" name="img">
          <input type="text" value="${vegetable.img}" hidden="true"  name="path" >
        </td>
      </tr>
      <tr>
        <td colspan="2">  <input type="submit" value="确认修改"></td>
      </tr>
    </table>
  </form>

  <%--</div>--%>
  <%--</div>--%>
  <%--</div>--%>

  <%--中部结束--%>
</div><!-- /.page-content -->

<!--[if !IE]> -->

<script type="text/javascript">
  window.jQuery || document.write("<script src='assets/js/jquery-2.0.3.min.js'>"+"<"+"/script>");
</script>

<!-- <![endif]-->

<!--[if IE]>
<script type="text/javascript">
  window.jQuery || document.write("<script src='assets/js/jquery-1.10.2.min.js'>"+"<"+"/script>");
</script>
<![endif]-->

<script type="text/javascript">
  if("ontouchend" in document) document.write("<script src='assets/js/jquery.mobile.custom.min.js'>"+"<"+"/script>");
</script>
<script src="/js/resizeIframe.js"></script>
<script src="assets/js/bootstrap.min.js"></script>
<script src="assets/js/typeahead-bs2.min.js"></script>


<script src="assets/js/ace-elements.min.js"></script>
<script src="assets/js/ace.min.js"></script>


</body>
</html>
