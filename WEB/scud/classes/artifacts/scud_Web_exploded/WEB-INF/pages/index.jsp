<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2015/6/9
  Time: 15:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="false" %>
<%
  String path = request.getContextPath();
  String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
  <head>
    <base href="<%=basePath%>">
    <meta charset="utf-8">
    <title></title>
  </head>
  <body>
  用户注册：
  <form action="/user/add" method="post">
    <p>First name: <input type="text" name="phoneNumber" /></p>
    <p>Last name: <input type="password" name="Password" /></p>
    <input type="submit" value="Submit" />
  </form>
  </body>
</html>
