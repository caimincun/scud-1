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

  <script src="js/jquery-2.1.1.js"></script>
  <style>
    table{width:100%;text-align:center;border-collapse:collapse;border-spacing:0; }
    th{text-align:center}
  </style>

</head>
<body>
<table border="1">
  <tr>
    <td>问卷版本名称</td>
    <td>版本状态</td>
    <td>操作</td>
  </tr>

<c:forEach items="${questionCategoryList}"  var="questionCategory">
  <tr>
    <td>
      <c:out value="${questionCategory.description}"/>
    </td>
    <td>
      <c:if test="${questionCategory.status == 0}">
        版本正常使用
      </c:if>
      <c:if test="${questionCategory.status == 1}">
        版本已停用
      </c:if>
    </td>
    <td>
      <input type="button" value="修改" onclick="updateQuestionCate(${questionCategory.id})">
    </td>
  </tr>
</c:forEach>
  <%--<tr>--%>
    <%--<td>问卷版本名称</td>--%>
    <%--<td>版本状态</td>--%>
    <%--<td>操作</td>--%>
  <%--</tr>--%>
</table>
<script type="application/javascript">
  function updateQuestionCate(id)
  {
//    alert(id);
    window.location.href="/question/getQuestionCate?id="+id;
  }
</script>
<!--[if !IE]> -->
<%--<script type="text/javascript">--%>
  <%--window.jQuery || document.write("<script src='assets/js/jquery-2.0.3.min.js'>"+"<"+"/script>");--%>
<%--</script>--%>

<%--<!-- <![endif]-->--%>

<%--<!--[if IE]>--%>
<%--<script type="text/javascript">--%>
  <%--window.jQuery || document.write("<script src='assets/js/jquery-1.10.2.min.js'>"+"<"+"/script>");--%>
<%--</script>--%>
<%--<![endif]-->--%>

<%--<script type="text/javascript">--%>
  <%--if("ontouchend" in document) document.write("<script src='assets/js/jquery.mobile.custom.min.js'>"+"<"+"/script>");--%>
<%--</script>--%>
<%--<script src="assets/js/bootstrap.min.js"></script>--%>
<%--<script src="assets/js/typeahead-bs2.min.js"></script>--%>


<%--<script src="assets/js/ace-elements.min.js"></script>--%>
<%--<script src="assets/js/ace.min.js"></script>--%>
<%--<script src="/js/resizeIframe.js"></script>--%>
</body>
</html>
