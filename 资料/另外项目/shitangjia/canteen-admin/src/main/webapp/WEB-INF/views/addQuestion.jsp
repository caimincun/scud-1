<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2015/6/11
  Time: 10:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
  %>
  <base href="<%=basePath%>">
    <title></title>
</head>
<style type="text/css">
  td {
    text-align:center; /*设置水平居中*/
    vertical-align:middle;/*设置垂直居中*/
  }
  body{text-align:center}
  table{width:60%;text-align:center;border-collapse:collapse;border-spacing:0; }

</style>
<body style="height: 600px">
青菜科技问卷调查问题录入：
<table align="center" border="1">
<form action="question/addQuestion" method="post">
  <tr>
    <td>
      请选择问卷版本
    </td>
    <td>
      <select name="questionCategoryId">
        <c:forEach items="${questionCategoryList}" var="qiestionCategory">
          <option value="${qiestionCategory.id}"/><c:out value="${qiestionCategory.description}"/>
        </c:forEach>
      </select>
    </td>
  </tr>
  <tr>
    <td> 请输入题目：</td>
    <td><input type="text" name="title" size="45"></td>
  <tr>
  <td>问题类型：</td>
  <td> <input type="radio" name="questionType" value="1" checked> 单选 <input type="radio" name="questionType" value="0">多选</td>
  </tr>
  <tr>
    <td>A: </td>
    <td><input name="A" type="text" size="45"></td>
  </tr>
  <tr>
    <td>B:</td>
    <td> <input name="B" type="text" size="45"> </td>
  </tr>
  <tr>
    <td>C:</td>
    <td><input name="C" type="text" size="45"></td>
  </tr>
  <tr>
    <td> D:</td>
    <td><input name="D" type="text" size="45"></td>
  </tr>
  <tr>
    <td colspan="2"> <input type="submit" value="提交问题"></td>
  </tr>

</form>
</table>
</body>
</html>
