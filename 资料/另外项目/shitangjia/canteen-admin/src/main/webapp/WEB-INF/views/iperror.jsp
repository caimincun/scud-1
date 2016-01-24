<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2015/6/11
  Time: 18:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
  String path = request.getContextPath();
  String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
<head>
    <base href="<%=basePath %>">
    <meta name="viewport" content="width=device-width, initial-scale=1,user-scalable=no">
    <link href="css/base.css" rel="stylesheet">
    <title>谢谢参与</title>
</head>
<body>
    <div align="center" style="width: 100%">
        <span style="font-weight: bold;font-size: 0.2rem">对不起，一台设备只能参与一次有奖调查，谢谢你的参与！！</span>
        <br/>
        <img src="img/thank.jpg" width="320px">
    </div>

</body>
</html>
