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
  <link href="assets/css/bootstrap.min.css" rel="stylesheet" />
  <link rel="stylesheet" href="assets/css/font-awesome.min.css" />
  <link rel="stylesheet" href="assets/css/ace.min.css" />
  <link rel="stylesheet" href="assets/css/ace-rtl.min.css" />
  <link rel="stylesheet" href="assets/css/ace-skins.min.css" />
  <script src="assets/js/ace-extra.min.js"></script>
  <script src="js/jquery-2.1.1.js"></script>
  <style>
    table{width:100%;text-align:center;border-collapse:collapse;border-spacing:1;border-spacing:0; }
  </style>
</head>
<body>


    <!-- 正文页头部 -->
      <div class="breadcrumbs" id="breadcrumbs">
        <script type="text/javascript">
          try{ace.settings.check('breadcrumbs' , 'fixed')}catch(e){}
        </script>

        <ul class="breadcrumb">
          <li>
            <i class="icon-home home-icon"></i>
            <a href="#">Home</a>
          </li>

          <li>
            修改菜品信息
          </li>

        </ul><!-- .breadcrumb -->
      </div>




      <%--正文头部结束--%>
      <!-- #好像这儿是页面中部 -->
      <div class="page-content">
        <%--中部开始--%>


        <h1>
          修改菜品种类
        </h1>


        <%--<div class="row">--%>
        <%--<div class="col-xs-12">--%>
        <%--<div class="table-responsive">--%>
          <form action="/product/updateProductCategory" method="post">
            类别名称：<input type="text" value="${productCategory.categoryName}" name="categoryName">
            <input type="text" name="id" value="${productCategory.id}" hidden="ture">
            <input type="submit" value="确认修改">

          </form>


        <%--</div>--%>
        <%--</div>--%>
        <%--</div>--%>


        <%--中部结束--%>
      </div><!-- /.page-content -->

    <!-- #这儿是页面中部结尾 -->








<!--[if !IE]> -->

<script type="text/javascript">
  window.jQuery || document.write("<script src='assets/js/jquery-2.0.3.min.js'>"+"<"+"/script>");
</script>

<!-- <![endif]-->

<!--[if IE]>
<script type="text/javascript">
  window.jQuery || document.write("<script src='assets/js/jquery-1.10.2.min.js'>"+"<"+"/script>");
</script>
<![endif]-->

<script type="text/javascript">
  if("ontouchend" in document) document.write("<script src='assets/js/jquery.mobile.custom.min.js'>"+"<"+"/script>");
</script>

<script src="assets/js/bootstrap.min.js"></script>
<script src="assets/js/typeahead-bs2.min.js"></script>


<script src="assets/js/ace-elements.min.js"></script>
<script src="assets/js/ace.min.js"></script>
    <script src="/js/resizeIframe.js"></script>

</body>
</html>
