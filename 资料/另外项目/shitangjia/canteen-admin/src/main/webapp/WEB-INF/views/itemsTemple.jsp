<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--<%
  String path = request.getContextPath();
  String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<base href="<%=basePath%>">

<html>
<head>
  <title></title>

  <link href="assets/css/bootstrap.min.css" rel="stylesheet" />
  <link rel="stylesheet" href="assets/css/font-awesome.min.css" />
  <link rel="stylesheet" href="assets/css/ace.min.css" />
  <link rel="stylesheet" href="assets/css/ace-rtl.min.css" />
  <link rel="stylesheet" href="assets/css/ace-skins.min.css" />
  <script src="assets/js/ace-extra.min.js"></script>
</head>
<body>--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<ul class="nav nav-list">
  <c:forEach var="canteenAuthorities" items="${canteenAuthoritieMap}" varStatus="status">

    <c:choose>
      <c:when test="${empty canteenAuthorities.value}">

        <li class="active">
          <a href="index.html">
            <i class="icon-dashboard"></i>
            <span class="menu-text">${canteenAuthorities.key.authorityName}</span>
          </a>
        </li>

      </c:when>
      <c:otherwise>

        <li>
          <a href="#" class="dropdown-toggle">
            <i class="icon-list"></i>
            <span class="menu-text"> ${canteenAuthorities.key.authorityName} </span>
            <b class="arrow icon-angle-down"></b>
          </a>

          <ul class="submenu">
            <c:forEach var="items" items="${canteenAuthorities.value}">
              <li>
                <a href="/canteenUser/choicepage?id=${items.id}">
                  <i class="icon-double-angle-right"></i>
                  <c:out value="${items.authorityName}"></c:out>
                </a>
              </li>
            </c:forEach>
          </ul>
        </li>
      </c:otherwise>
    </c:choose>
  </c:forEach>
</ul>
<%--</body>
</html>--%>
