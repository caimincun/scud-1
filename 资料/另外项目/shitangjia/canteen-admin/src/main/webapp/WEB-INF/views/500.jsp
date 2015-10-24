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
        <title>服务器错误</title>
        <link rel="shortcut icon" href="images/favicon.ico">
        <link rel="stylesheet" type="text/css" href="css/error.css" />
    </head>
    <body>
        <div class="content">
            <h1>服务器异常 <span>:(</span></h1>
            <p class="message">请稍后再试...</p>
        </div>
    </body>
</html>
