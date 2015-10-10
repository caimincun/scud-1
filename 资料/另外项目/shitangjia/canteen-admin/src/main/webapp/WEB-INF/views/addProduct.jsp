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
  <script src="/js/resizeIframe.js"></script>
  <script src="js/My97DatePicker/WdatePicker.js"></script>
  <style>
    .product_inputs{
      width: 60%;
      min-height: 100px;
      margin-left: auto;
      margin-right: auto;
    }
    .product_input_row{
      width: 100%;
      height: 50px;
    }
    .product_input_col{
      width: 20%;
      height: 100%;
      float: left;
      font-size: 13px;
      color: #000000;
      text-align: center;
      font-family: "Microsoft YaHei", \5FAE\8F6F\96C5\9ED1, STHei, \534E\6587\7EC6\9ED1, "Helvetica Neue", Helvetica, Arial, sans-serif;
    }
    .product_input_r_col{
      width: 70%;
      height: 100%;
      float: left;
    }
    .input{
      height: 30px;
      padding: 2px;
      font-size: 13px;
      font-family: "Microsoft YaHei", \5FAE\8F6F\96C5\9ED1, STHei, \534E\6587\7EC6\9ED1, "Helvetica Neue", Helvetica, Arial, sans-serif;

    }
    .cate_name{
      display: block;
      margin-top: 2px;
    }
    .text{
      font-family: "Microsoft YaHei", \5FAE\8F6F\96C5\9ED1, STHei, \534E\6587\7EC6\9ED1, "Helvetica Neue", Helvetica, Arial, sans-serif;
      font-size:13px;
    }
    .btn{
      height: 20px;
      border: 0px;

    }
    .sub_btn{
      width: 100px;
      height: 30px;
      font-size: 13px;
      font-family: "Microsoft YaHei", \5FAE\8F6F\96C5\9ED1, STHei, \534E\6587\7EC6\9ED1, "Helvetica Neue", Helvetica, Arial, sans-serif;
      border: 0px;
      background-color: #0088cc;
      color: white;
    }
  </style>
</head>
<body>


      <%--正文头部结束--%>
      <!-- #好像这儿是页面中部 -->
      <div class="page-content">
        <%--中部开始--%>
          <h3 class="header smaller lighter blue">添加菜单信息</h3>

       <%--<div class="row">--%>
        <%--<div class="col-xs-12">--%>
        <%--<div class="table-responsive">--%>
       <%-- <table border="1">
          <form method="post" action="/product/saveProduct"  enctype="multipart/form-data"  modelAttribute="product">
          <tr>
            <td>菜单名称：</td>
            <td><input name="name" type="text" maxlength="20" required="required"> </td>
          </tr>
            <tr>
              <td>价格:</td>
              <td><input name="price" type="number" min="0" style="width: 167" required="required"></td>
            </tr>
            <tr>
              <td>描述:</td>
              <td><input name="description" type="text" maxlength="50"></td>
            </tr>
            <tr>
              <td>供应时间：</td>
              <td><input type="date" name="createTime" required="true" id="createTime" style="width: 146">
                <img onclick="WdatePicker({el:'createTime'})" src="js/My97DatePicker/skin/datePicker.gif" width="16" height="22" align="absmiddle">
              </td>
            </tr>
            <tr>
            <td>供应时段：</td>
            <td>
              <select name="timeNode" style="width: 165" required="required">
                <option value="1" >早餐</option>
                <option value="2" selected>午餐</option>
                <option value="3" >晚餐</option>
              </select>
            </td>
          </tr>
            <tr>
              <td>
                是否支持预定：
              </td>
              <td>
                <input type="radio" name="book" value="1"> 支持预定
                <input type="radio" name="book" value="0"> 不支持预定
              </td>
            </tr>
            <tr>
              <td>菜品类别：</td>
              <td>
                <select name="productCategoryId" style="width: 165" required="required">
                  <c:forEach var="productCategory" items="${productCategoryList}" varStatus="status">
                    <option value="${status.index+1}">${productCategory.categoryName}</option>
                  </c:forEach>
                </select>
              </td>
            </tr>
            <tr>
              <td>图片样式：</td>
              <td align="center"><input type="file" name="img"><font style="color: #ff0000;"> <c:out value="${errorAlertContent}"/></font></td>
            </tr>
            <tr>
              <td colspan="2"><input type="submit" value="确认添加"></td>
            </tr>
          </form>
        </table>--%>

          <form method="post" action="/product/saveProduct"  enctype="multipart/form-data"  modelAttribute="product">

          <div class="product_inputs">
            <div class="product_input_row">
              <div class="product_input_col"><span class="cate_name">菜单名称:</span></div>
              <div class="product_input_r_col">
                <input name="name" type="text" maxlength="20" required="required" class="input">
              </div>
            </div>
            <div class="product_input_row">
              <div class="product_input_col"><span class="cate_name">价&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp格:</span></div>
              <div class="product_input_r_col">
                <input name="price" type="number" min="0"  required="required" class="input">
              </div>
            </div>
            <div class="product_input_row">
              <div class="product_input_col"><span class="cate_name">描&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp述:</span></div>
              <div class="product_input_r_col">
                <input name="description" type="text" maxlength="50" class="input">
              </div>
            </div>
            <div class="product_input_row">
              <div class="product_input_col"><span class="cate_name">供应时间:</span></div>
              <div class="product_input_r_col">
                <input type="date" name="createTime" required="true" id="createTime"  class="input">
                <img onclick="WdatePicker({el:'createTime'})" src="js/My97DatePicker/skin/datePicker.gif" width="16" height="22" align="absmiddle">
              </div>
            </div>
            <div class="product_input_row">
              <div class="product_input_col"><span class="cate_name">供应时段:</span></div>
              <div class="product_input_r_col">
                <select name="timeNode" required="required" class="input">
                  <option value="1" >早餐</option>
                  <option value="2" selected>午餐</option>
                  <option value="3" >晚餐</option>
                </select>
              </div>
            </div>
            <div class="product_input_row">
              <div class="product_input_col"><span class="cate_name">是否支持预订:</span></div>
              <div class="product_input_r_col">
                <input type="radio" name="book" value="1"> <span class="text">支持预定</span>
                <input type="radio" name="book" value="0"> <span class="text">不支持预定</span>
              </div>
            </div>
            <div class="product_input_row">
              <div class="product_input_col"><span class="cate_name">菜品类别:</span></div>
              <div class="product_input_r_col">
                <select name="productCategoryId" required="required" class="input">
                  <c:forEach var="productCategory" items="${productCategoryList}" varStatus="status">
                    <option value="${status.index+1}">${productCategory.categoryName}</option>
                  </c:forEach>
                </select>
              </div>
            </div>
            <div class="product_input_row">
              <div class="product_input_col"><span class="cate_name">图片样式:</span></div>
              <div class="product_input_r_col">
                <input type="file" name="img" class="input" style="width: 70%;"><c:out value="${errorAlertContent}"/>
              </div>
            </div>
            <div class="product_input_row">
              <input type="submit" value="确认添加" class="sub_btn" style="float: left;margin-left: 10%">
              <input type="reset" value="重置" class="sub_btn" style="float: left;margin-left: 10%">
            </div>
          </div>
          </form>
        <%--中部结束--%>
      </div><!-- /.page-content -->


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


</body>
</html>
