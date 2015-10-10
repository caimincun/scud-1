<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <base href="<%=basePath%>">
    <meta charset="utf-8"/>
    <title>食堂家后台管理系统</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <!-- basic styles -->
    <link href="assets/css/bootstrap.min.css" rel="stylesheet"/>
    <link rel="stylesheet" href="assets/css/font-awesome.min.css"/>
    <!--[if IE 7]>
    <link rel="stylesheet" href="assets/css/font-awesome-ie7.min.css"/>
    <![endif]-->
    <!-- page specific plugin styles -->
    <!-- fonts -->
    <!-- ace styles -->
    <link rel="stylesheet" href="assets/css/ace.min.css"/>
    <link rel="stylesheet" href="assets/css/ace-rtl.min.css"/>
    <link rel="stylesheet" href="assets/css/ace-skins.min.css"/>
    <!--[if lte IE 8]>
    <link rel="stylesheet" href="assets/css/ace-ie.min.css"/>
    <![endif]-->
    <!-- inline styles related to this page -->
    <!-- ace settings handler -->
    <script src="assets/js/ace-extra.min.js"></script>
    <!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
    <script src="assets/js/html5shiv.js"></script>
    <script src="assets/js/respond.min.js"></script>
    <![endif]-->
</head>
<body>
<div class="navbar navbar-default" id="navbar">
    <script type="text/javascript">
        try {
            ace.settings.check('navbar', 'fixed')
        } catch (e) {
        }
    </script>

    <div class="navbar-container" id="navbar-container">
        <div class="navbar-header pull-left">
            <a href="#" class="navbar-brand">
                <small>
                    <i class="icon-leaf"></i>
                    食堂家后台管理系统
                </small>
            </a><!-- /.brand -->
        </div>
        <!-- /.navbar-header -->

        <div class="navbar-header pull-right" role="navigation">
            <ul class="nav ace-nav">

                <li class="purple">
                    <a data-toggle="dropdown" class="dropdown-toggle" href="#" id="notifyIcon">
                       <i class="icon-bell-alt icon-animated-bell"></i>
                        <span class="badge badge-important" id="notifyHead">${diancanNum+shucaiNum}</span>
                    </a>

                    <ul class="pull-right dropdown-navbar navbar-pink dropdown-menu dropdown-caret dropdown-close">
                        <li class="dropdown-header">
                            <i class="icon-warning-sign"></i>
                            <span id="notifyTitle">${diancanNum+shucaiNum}</span>条通知
                        </li>
                        <li>
                            <a onclick="go('message/toHistoryMsgPage?messageType=0')">
                                <div class="clearfix">
											<span class="pull-left">
												<i class="btn btn-xs no-hover btn-pink icon-comment"></i>
												点餐订单
											</span>
                                    <span class="pull-right badge badge-info">+<span id="diancanNum">${diancanNum}</span></span>
                                </div>
                            </a>
                        </li>
                        <li>
                            <a onclick="go('message/toHistoryMsgPage?messageType=1')">
                                <div class="clearfix">
											<span class="pull-left">
												<i class="btn btn-xs no-hover btn-success icon-shopping-cart"></i>
												蔬菜订单
											</span>
                                    <span class="pull-right badge badge-success">+<span id="shucaiNum">${shucaiNum}</span></span>
                                </div>
                            </a>
                        </li>
                    </ul>
                </li>

                <li class="light-blue">
                    <a data-toggle="dropdown" href="#" class="dropdown-toggle">
                        <%--<img class="nav-user-photo" src="assets/avatars/user.jpg" alt="Jason's Photo"/>--%>
								<span class="user-info">
									<small>欢迎光临,</small>
										 <c:out value="${user.name}"/>
								</span>

                        <i class="icon-caret-down"></i>
                    </a>

                    <ul class="user-menu pull-right dropdown-menu dropdown-yellow dropdown-caret dropdown-close">
                        <li class="divider"></li>
                        <li>
                            <a href="canteenUser/logout">
                                <i class="icon-off"></i>
                                退出
                            </a>
                        </li>
                        </a>
                    </ul>
                </li>
            </ul>
            <!-- /.ace-nav -->
        </div>
        <!-- /.navbar-header -->
    </div>
    <!-- /.container -->
</div>

<div class="main-container" id="main-container">
    <script type="text/javascript">
        try {
            ace.settings.check('main-container', 'fixed')
        } catch (e) {
        }
    </script>

    <div class="main-container-inner">
        <a class="menu-toggler" id="menu-toggler" href="#">
            <span class="menu-text"></span>
        </a>

        <div class="sidebar" id="sidebar">
            <script type="text/javascript">
                try {
                    ace.settings.check('sidebar', 'fixed')
                } catch (e) {
                }
            </script>

            <div class="sidebar-shortcuts" id="sidebar-shortcuts">
                <div class="sidebar-shortcuts-large" id="sidebar-shortcuts-large">
                    <%--<button class="btn btn-success">--%>
                        <%--<i class="icon-signal"></i>--%>
                    <%--</button>--%>

                    <%--<button class="btn btn-info">--%>
                        <%--<i class="icon-pencil"></i>--%>
                    <%--</button>--%>

                    <%--<button class="btn btn-warning">--%>
                        <%--<i class="icon-group"></i>--%>
                    <%--</button>--%>

                    <%--<button class="btn btn-danger">--%>
                        <%--<i class="icon-cogs"></i>--%>
                    <%--</button>--%>
                </div>

                <div class="sidebar-shortcuts-mini" id="sidebar-shortcuts-mini">
                    <span class="btn btn-success"></span>

                    <span class="btn btn-info"></span>

                    <span class="btn btn-warning"></span>

                    <span class="btn btn-danger"></span>
                </div>
            </div>
            <!-- #sidebar-shortcuts -->
            <ul class="nav nav-list">
                <c:forEach var="canteenAuthorities" items="${canteenAuthoritieMap}" varStatus="status">

                    <c:choose>
                        <c:when test="${empty canteenAuthorities.value}">

                            <li class="active">
                                <a onclick="go('/canteenUser/choicepage?id=${canteenAuthorities.key.id}')">
                                    <i class="icon-dashboard"></i>
                                    <span class="menu-text">${canteenAuthorities.key.authorityName}</span>
                                </a>
                            </li>

                        </c:when>
                        <c:otherwise>

                            <li>
                                <a class="dropdown-toggle">
                                    <i class="icon-list"></i>
                                    <span class="menu-text"> ${canteenAuthorities.key.authorityName} </span>
                                    <b class="arrow icon-angle-down"></b>
                                </a>

                                <ul class="submenu">
                                    <c:forEach var="items" items="${canteenAuthorities.value}">
                                        <li>
                                            <a onclick="go('/canteenUser/choicepage?id=${items.id}')">
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
            <!-- /.nav-list -->

            <div class="sidebar-collapse" id="sidebar-collapse">
                <i class="icon-double-angle-left" data-icon1="icon-double-angle-left"
                   data-icon2="icon-double-angle-right"></i>
            </div>

            <script type="text/javascript">
                try {
                    ace.settings.check('sidebar', 'collapsed')
                } catch (e) {
                }
            </script>
        </div>

        <div class="main-content">
            <div class="breadcrumbs" id="breadcrumbs">
                <script type="text/javascript">
                    try {
                        ace.settings.check('breadcrumbs', 'fixed')
                    } catch (e) {
                    }
                </script>

                <%--<ul class="breadcrumb">--%>
                    <%--<li>--%>
                        <%--<i class="icon-home home-icon"></i>--%>
                        <%--<a href="#">首页</a>--%>
                    <%--</li>--%>
                    <%--<li class="active">控制台</li>--%>
                <%--</ul>--%>
                <!-- .breadcrumb -->
                <!-- #nav-search -->
            </div>

            <div class="page-content">

                <iframe id="content" src="" frameborder="0" width="100%"  scrolling="no"
                        style="background-color: transparent" height="1500px"></iframe>

            </div>
            <!-- /.page-content -->
        </div>
        <!-- /.main-content -->


        <!-- /#ace-settings-container -->
    </div>
    <!-- /.main-container-inner -->

    <a href="#" id="btn-scroll-up" class="btn-scroll-up btn btn-sm btn-inverse">
        <i class="icon-double-angle-up icon-only bigger-110"></i>
    </a>
</div>
<!-- /.main-container -->
<div style="display: none">
<audio id="xiaoxi" src="/js/xiaoxi.mp3"  controls></audio>
    </div>
<!-- basic scripts -->
<!--[if !IE]> -->
<script type="text/javascript">
    window.jQuery || document.write("<script src='assets/js/jquery-2.0.3.min.js'>" + "<" + "script>");
</script>
<!-- <![endif]-->
<!--[if IE]>
<script type="text/javascript">
    window.jQuery || document.write("<script src='assets/js/jquery-1.10.2.min.js'>" + "<" + "script>");
</script>
<![endif]-->
<script type="text/javascript">
    if ("ontouchend" in document) document.write("<script src='assets/js/jquery.mobile.custom.min.js'>" + "<" + "script>");
</script>

<script src="assets/js/bootstrap.min.js"></script>
<script src="assets/js/typeahead-bs2.min.js"></script>

<!-- page specific plugin scripts -->

<!--[if lte IE 8]>
<script src="assets/js/excanvas.min.js"></script>
<![endif]-->

<script src="assets/js/jquery-ui-1.10.3.custom.min.js"></script>
<script src="assets/js/jquery.ui.touch-punch.min.js"></script>
<script src="assets/js/jquery.slimscroll.min.js"></script>
<script src="assets/js/jquery.easy-pie-chart.min.js"></script>
<script src="assets/js/jquery.sparkline.min.js"></script>
<script src="assets/js/flot/jquery.flot.min.js"></script>
<script src="assets/js/flot/jquery.flot.pie.min.js"></script>
<script src="assets/js/flot/jquery.flot.resize.min.js"></script>

<!-- ace scripts -->

<script src="assets/js/ace-elements.min.js"></script>
<script src="assets/js/ace.min.js"></script>

<!-- inline scripts related to this page -->
<!-[if lt IE 7]>
<script type="text/javascript" src="js/json2.js"></script>
<![endif]->
<script type="text/javascript" src="js/socket.io-1.3.5.min.js"></script>
<script type="text/javascript" src="js/yunba-js-sdk.js"></script>
<script type="text/javascript">
        //消息推送服务
        var yunba = new Yunba({appkey: '55c198e1504438f24763bcc8'});
        yunba.init(function (success) {
            if (success) {
                yunba.connect(function (success, msg) {
                    if (!success) {
                        console.log("连接失败"+msg);
                    }
                });
            }
        }, function () {
            console.log("连接失败");
        });
        yunba.set_alias({'alias': '${canteenId}'}, function (data) {
            if (!data.success) {
                console.log(data.msg);
            }
        });
        yunba.set_message_cb(function (data) {
            var msg = data.msg;
            switch (msg){
                case "0":
                    titleNumPlus();
                    var $diancanNum = $("#diancanNum");
                    $diancanNum.html(parseInt($diancanNum.html())+1);
                    break;
                case "1":
                    titleNumPlus();
                    var $shucaiNum = $("#shucaiNum");
                    $shucaiNum.html(parseInt($shucaiNum.html())+1);
                    break;

            }
            $("#notifyIcon i").remove();
            $("#notifyIcon").prepend("<i class=\"icon-bell-alt icon-animated-bell\"></i>");
            xiaoxi.play();
        });


   /* var PATH = "<%--${pageContext.request.contextPath}--%>";
    var websocket = null;
    if (window['WebSocket'])
    // ws://host:port/project/websocketpath
        websocket = new WebSocket("ws://" + window.location.host + PATH + '/websocket');
    else
        websocket = new new SockJS(PATH + '/websocket/socketjs');

    websocket.onopen = function(event) {
        console.log('open', event);
    };
    websocket.onmessage = function(event) {
        var data = event.data;
        switch (data){
            case "0":
                titleNumPlus();
                var $diancanNum = $("#diancanNum");
                $diancanNum.html(parseInt($diancanNum.html())+1);
                break;
            case "1":
                titleNumPlus();
                var $shucaiNum = $("#shucaiNum");
                $shucaiNum.html(parseInt($shucaiNum.html())+1);
                break;

        }
        $("#notifyIcon i").remove();
        $("#notifyIcon").prepend("<i class=\"icon-bell-alt icon-animated-bell\"></i>");
        xiaoxi.play();
    };*/
    function titleNumPlus(){
        var $notifyHead = $("#notifyHead");
        var num =parseInt($notifyHead.html())+1;
        $notifyHead.html(num);
        $("#notifyTitle").html(num);
    }
    function titleNumSub(msgType){
        var $notifyHead = $("#notifyHead");
        var num =parseInt($notifyHead.html())-1;
        $notifyHead.html(num);
        $("#notifyTitle").html(num);
        switch (msgType){
            case 0:
                var $diancanNum = $("#diancanNum");
                console.log($diancanNum);
                $diancanNum.html(parseInt($diancanNum.html())-1);
                break;
            case 1:
                var $shucaiNum = $("#shucaiNum");
                console.log($shucaiNum);
                $shucaiNum.html(parseInt($shucaiNum.html())-1);
                break;
        }
    }
    function go(url) {
        $("#content").attr("src", url);
    }
</script>
</body>
</html>


