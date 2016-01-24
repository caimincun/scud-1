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
  <script src="/js/resizeIframe.js"></script>
  <style>
    table{width:100%;text-align:center;border-collapse:collapse;border-spacing:0; }
    th{text-align:center}
  </style>

</head>
<body>
问卷调查版本修改
<form action="/question/upadateQuestionCate" method="post">
<table border="1">
  <tr>
    <td>问卷版本名称</td>
    <td>版本状态</td>
    <td>操作</td>
  </tr>
  <tr>
    <td>
      <input type="text" name="description" value="${questionCategory.description}" required="true">
      <input type="text" name="id" value="${questionCategory.id}" hidden="true">
      <input type="text" name="CanteedId" value="${questionCategory.canteenId}" hidden="true">
    </td>
    <td>
      <c:if test="${questionCategory.status == 0}">
        <input type="radio" name="status" value="0" checked>使用
        <input type="radio" name="status" value="1">停用
      </c:if>
      <c:if test="${questionCategory.status == 1}">
        <input type="radio" name="status" value="0" >使用
        <input type="radio" name="status" value="1" checked>使
      </c:if>
    </td>
    <td>
      <input type="submit" value="确定修改">
    </td>
  </tr>
</table>
</form>
<script type="application/javascript">
  function updateQuestionCate(id)
  {
    window.location.href="/question/getQuestionCate?id="+id;
  }
</script>
</body>
</html>
