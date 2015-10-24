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
  <script src='assets/js/jquery-2.0.3.min.js'></script>
  <style>
    table{width:100%;text-align:center;border-collapse:collapse;border-spacing:0; }
    th{text-align:center}
  </style>

</head>
<body>
问卷调查版本添加：
<form action="/question/addQuestionCate" method="post">
<table border="1">
  <tr>
    <td>问卷版本名称</td>
    <td>版本状态</td>
    <td>操作</td>
  </tr>
  <tr>
    <td>
      <input type="text" name="description" required="true">
    </td>
    <td>
      <input type="radio" name="status" value="0" checked>使用
      <input type="radio" name="status" value="1">停用
    </td>
    <td>
      <input type="submit" value="确认添加">
    </td>
  </tr>
</table>
</form>

</body>
</html>
