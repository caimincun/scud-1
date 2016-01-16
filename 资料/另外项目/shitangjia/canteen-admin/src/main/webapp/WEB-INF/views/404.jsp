<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page session="false" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html lang="cn">
    <head>
        <base href="<%=basePath%>">
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">

        <title>页面未找到</title>
        <link rel="shortcut icon" href="images/favicon.ico">
        <link rel="stylesheet" type="text/css" href="css/error.css" />
    </head>
    <body>
    <div class="content">
        <h1>404 页面未找到 <span>:(</span></h1>
        <p class="message">您请求的页面不存在，请检查请求地址是否正确!</p>
    </div>
    </body>
</html>
