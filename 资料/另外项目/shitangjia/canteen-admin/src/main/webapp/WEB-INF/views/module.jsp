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
</head>
<body style="background-color: #ffffff">
<%--中部--%>
模块选择：（请选择需要的功能模块)
<form action="/module/setCanteenModule">
<c:forEach items="${moduleMap}" var="moduleMap" varStatus="index">
  <c:choose>
<c:when test="${empty moduleMap.value}"><%-- 一级如果节点没有被勾选中--%>
  <c:choose>
    <c:when test="${index.index+1 == 3}"><%--如果是信息公开模块--%>
      <br/><br/>
      <c:out value="${index.index+1}"></c:out>、
      <input type="checkbox" name="moduleCode" value="<c:out value="${moduleMap.key.moduleCode}"></c:out>" onclick="showXinxi(this)"><c:out value="${moduleMap.key.moduleName}"></c:out>:<c:out value="${moduleMap.key.describe}"></c:out>
     <span id="showInfa" style="visibility: hidden; display: none;">
        <c:forEach var="information" items="${informationCategories}">
       <c:if test="${information.moduleId == 3}"> <%--如果是信息公开模块的子模块--%>
          <input type="checkbox" name="informationCategory" value="${information.id}"><c:out value="${information.description}"></c:out>
       </c:if>
        </c:forEach>
      </span>
    </c:when>

    <c:otherwise>
      <br/><br/>
      <c:out value="${index.index+1}"></c:out>、
      <input type="checkbox" name="moduleCode" value="<c:out value="${moduleMap.key.moduleCode}"></c:out>"> <c:out value="${moduleMap.key.moduleName}"></c:out>：<c:out value="${moduleMap.key.describe}"></c:out>

    </c:otherwise>
  </c:choose>
</c:when>
  <c:otherwise> <%-- 一级如果节点被勾选中--%>
    <br/><br/>
    <c:out value="${index.index+1}"></c:out>、
    <c:choose>
    <c:when test="${index.index+1 == 6}"><%--如果是个人中心模块--%>
      <input type="checkbox" name="moduleCode" disabled value="<c:out value="${moduleMap.key.moduleCode}"></c:out>" checked><c:out value="${moduleMap.key.moduleName}"></c:out>:<c:out value="${moduleMap.key.describe}"></c:out>
      <input type="checkbox" name="moduleCode" checked value="<c:out value="${moduleMap.key.moduleCode}" ></c:out>" hidden="true">

      <span id="showgrenzhongx" style="visibility: visible; display: inline-block;">
          <c:forEach var="information" items="${informationCategories}">
            <c:if test="${information.moduleId == 6}"> <%--如果是个人中心模块的子模块--%>
              <input type="checkbox" name="usercenter" value="${information.id}" <c:if test="${information.status == 0}">checked="" </c:if>> <c:out value="${information.description}"></c:out>
            </c:if>
          </c:forEach>
        </span>

    </c:when>

      <c:when test="${index.index+1 == 3}"><%--如果是信息公开模块--%>
        <input type="checkbox" name="moduleCode" value="<c:out value="${moduleMap.key.moduleCode}"></c:out>" onclick="showXinxi(this)" checked><c:out value="${moduleMap.key.moduleName}"></c:out>:<c:out value="${moduleMap.key.describe}"></c:out>
        <span id="showInfa" style="visibility: visible; display: inline-block;">
          <c:forEach var="information" items="${informationCategories}">
            <c:if test="${information.moduleId == 3}"> <%--如果是信息公开模块的子模块--%>
            <input type="checkbox" name="informationCategory" value="${information.id}" <c:if test="${information.status == 0}">checked="" </c:if>> <c:out value="${information.description}"></c:out>
            </c:if>
          </c:forEach>
        </span>
      </c:when>


    <c:otherwise>
    <input type="checkbox" name="moduleCode" value="<c:out value="${moduleMap.key.moduleCode}"></c:out>" checked><c:out value="${moduleMap.key.moduleName}"></c:out>:<c:out value="${moduleMap.key.describe}"></c:out>
    </c:otherwise>
    </c:choose>
  </c:otherwise>
  </c:choose>
</c:forEach>
  <input type="submit" value="确定">
</form>
<%--中部结尾--%>

<script type="text/javascript">

  function showXinxi(obj)
  {
    var element  = document.getElementById('showInfa');
    if($(obj).is(':checked')){
      element.style.position   = "absolute";
      element.style.visibility = "visible";
      element.style.display = "inline-block";
    }else
    {
      element.style.position   = "absolute";
      element.style.visibility = "visible";
      element.style.display = "none";
    }
  }
</script>
<script type="text/javascript">
  window.jQuery || document.write("<script src='assets/js/jquery-2.0.3.min.js'>" + "<" + "script>");
</script>
<script type="text/javascript">
  window.jQuery || document.write("<script src='assets/js/jquery-1.10.2.min.js'>" + "<" + "script>");
</script>
<![endif]-->
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

<!-- inline scripts related to this page -->
<script src="/js/resizeIframe.js"></script>
<script type="text/javascript">

</script>
</body>
</html>