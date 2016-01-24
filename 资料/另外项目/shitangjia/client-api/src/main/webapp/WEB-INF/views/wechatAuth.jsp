<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>${canteenName}</title>
</head>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<base href="<%=basePath%>">
<body>
<%--<%=request.getAttribute("url") %>--%>
 <script type="application/javascript">
    window.location="<%=request.getAttribute("url")%>";
  </script>
</body>
</html>
