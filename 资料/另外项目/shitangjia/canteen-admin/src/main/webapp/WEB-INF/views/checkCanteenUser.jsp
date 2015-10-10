<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2015/6/12
  Time: 10:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<base href="<%=basePath%>">
<meta charset="utf-8">
<html>
<head>
    <title></title>

    <link href="assets/css/bootstrap.min.css" rel="stylesheet"/>
    <link rel="stylesheet" href="assets/css/font-awesome.min.css"/>
    <link rel="stylesheet" href="assets/css/ace.min.css"/>
    <link rel="stylesheet" href="assets/css/ace-rtl.min.css"/>
    <script src="js/jquery-2.1.1.js" type="application/javascript"></script>
</head>
<body class="login-layout" style="background-color: #3399FF">
<div class="main-container">
    <div class="main-content">
        <div class="row">
            <div class="col-sm-10 col-sm-offset-1">
                <div class="login-container">
                    <div class="center">
                        <h1>
                            <img src="img/admin_logo.png" style="width: 36px;margin-top: -7px">
                            <span class="white">管理后台</span>
                        </h1>
                        <%--<h4 class="blue">&copy; Company Name</h4>--%>
                    </div>

                    <div class="space-6"></div>

                    <div class="position-relative">
                        <div id="login-box" class="login-box visible widget-box no-border" style="padding: 0px">
                            <div class="widget-body">
                                <div class="widget-main">
                                    <h4 class="header blue lighter bigger">
                                        <i class="icon-coffee green"></i>
                                       请填写您的信息
                                    </h4>

                                    <div class="space-6"></div>

                                    <form action="/canteenUser/showAuthority" method="post">
                                        <fieldset>
                                            <label class="block clearfix">
														<span class="block input-icon input-icon-right">
															<input type="text" class="form-control"
                                                                   placeholder="用户名" name="name"
                                                                   value="<c:if test="${cookie.name.value!=null}"><c:out value="${cookie.name.value}"></c:out></c:if>"/>
															<i class="icon-user"></i>
														</span>
                                            </label>

                                            <label class="block clearfix">
														<span class="block input-icon input-icon-right">
															<input type="password" class="form-control"
                                                                   placeholder="密码" name="password"
                                                                   value="<c:if test="${cookie.passWord.value!=null}"><c:out value="${cookie.passWord.value}"></c:out></c:if>"/>
															<i class="icon-lock"></i>
														</span>
                                            </label>

                                            <div>
                                                <font color="red"><c:out value="${error}"/></font>
                                            </div>

                                            <div class="clearfix">
                                                <label class="inline">
                                                    <input type="checkbox" name="rember" value="1" class="ace"/>
                                                    <span class="lbl">记住我</span>
                                                </label>

                                                <button type="submit"
                                                        class="width-35 pull-right btn btn-sm btn-primary">
                                                    <i class="icon-key"></i>
                                                    登 录
                                                </button>
                                            </div>

                                            <div class="space-4"></div>
                                        </fieldset>
                                    </form>

                                  <%--  <div class="social-or-login center">
                                        <span class="bigger-110">Or Login Using</span>
                                    </div>

                                    <div class="social-login center">
                                        <a class="btn btn-primary">
                                            <i class="icon-facebook"></i>
                                        </a>

                                        <a class="btn btn-info">
                                            <i class="icon-twitter"></i>
                                        </a>

                                        <a class="btn btn-danger">
                                            <i class="icon-google-plus"></i>
                                        </a>
                                    </div>--%>
                                </div>
                                <!-- /widget-main -->
                            </div>
                        </div>
                        <!-- /widget-body -->
                    </div>
                    <!-- /login-box -->
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
