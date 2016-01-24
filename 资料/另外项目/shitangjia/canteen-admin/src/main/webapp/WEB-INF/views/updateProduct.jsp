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
  <script src="js/My97DatePicker/WdatePicker.js"></script>
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



        <form action="/product/updateProduct" enctype="multipart/form-data" method="post">
          <table border="1">
            <tr>
              <td>  菜品名称：</td>
              <td><input type="text" value="${product.name}" name="name">
                <input type="text" name="id" value="${product.id}" hidden="ture"><br/>
                <input type="text" name="canteenId" value="${product.canteenId}" hidden="true">
              </td>
            </tr>
            <tr>
              <td>价格:</td>
              <td><input type="text" value="${product.price}" name="price"></td>
            </tr>
            <tr>
              <td>描述：</td>
              <td><input type="text" value="${product.description}" name="description"></td>
            </tr>
            <tr>
              <td>供应时间：</td>
              <td><input type="date" id="createTime" name="createTime" required="true" value="<fmt:formatDate value="${product.createdTime}" type="date" dateStyle="default"/>">
                <img onclick="WdatePicker({el:'createTime'})" src="js/My97DatePicker/skin/datePicker.gif" width="16" height="22" align="absmiddle">
              </td>
            </tr>
            <tr>
              <td>供应时段:</td>
              <td>
                <select name="timeNode"  value="${product.timeNode}">
                  <option value="1" <c:if test="${'1' eq product.timeNode}">selected</c:if>>早餐</option>
                  <option value="2" <c:if test="${'2' eq product.timeNode}">selected</c:if>>午餐</option>
                  <option value="3" <c:if test="${'3' eq product.timeNode}">selected</c:if>>晚餐</option>
                </select>
              </td>
            </tr>
            <tr>
              <td>
                是否支持预定：
              </td>
              <td>
                <input type="radio" name="book" value="1" <c:if test="${'1' eq product.book}"> checked="" </c:if> > 支持预定
                <input type="radio" name="book" value="0"  <c:if test="${'0' eq product.book}"> checked="" </c:if>> 不支持预定
              </td>
            </tr>
            <tr>
              <td> 菜品类类别:</td>
              <td><select name="productCategoryId" >
                <c:forEach var="productCategorie" items="${productCategories}">
                  <c:choose>
                    <c:when test="${product.productCategory.id == productCategorie.id}">
                      <option value="${productCategorie.id}" selected>${productCategorie.categoryName}</option>
                    </c:when>
                    <c:otherwise>
                      <option value="${productCategorie.id}">${productCategorie.categoryName}</option>
                    </c:otherwise>
                  </c:choose>
                </c:forEach>
              </select></td>
            </tr>
            <tr>
              <td>菜单样式：<img src="http://qingcai-images.bj.bcebos.com${product.img}" width="40" height="40"><font style="color: #ff0000;"> <c:out value="${errorAlertContent}"/></font></td>
              <td>
                <input type="file" name="img">
                <input type="text" value="${product.img}" hidden="true"  name="path" >
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
<script src="assets/js/bootstrap.min.js"></script>
<script src="assets/js/typeahead-bs2.min.js"></script>


<script src="assets/js/ace-elements.min.js"></script>
<script src="assets/js/ace.min.js"></script>
<script src="/js/resizeIframe.js"></script>

</body>
</html>
