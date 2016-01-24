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
  <div class="page-content">
    <h3 class="header smaller lighter blue">蔬菜信息录入</h3>
    <%--<table border="1">
      <form action="/store/saveVegetable"  enctype="multipart/form-data" method="post">
        <tr>
          <td>销售类别</td>
          <td>
            <select name="vegetableCategoryId" style="width: 167" required="required">
              <c:forEach var="vegetableCategorie" items="${vegetableCategories}">
                <option value="${vegetableCategorie.id}">${vegetableCategorie.categoryName}</option>
              </c:forEach>
            </select>
          </td>
        </tr>
        <tr>
          <td>蔬菜名:</td>
          <td><input type="text" name="vegetableName" maxlength="10" required="required"></td>
        </tr>
        <tr>
          <td>单价：</td>
          <td><input type="number" name="unitPrice" min="0" style="width: 167" required="required" step="0.01" max="10000"></td>
        </tr>
        <tr>
          <td>单位:</td>
          <td>
            <select name="unitName"  style="width: 167" required="required">
              <option value="元/个"> 元/个</option>
              <option value="元/斤">元/斤</option>
              <option value="元/袋">元/袋</option>.
              <option value="元/50克">元/50克</option>
              <option value="元/升">元/升</option>
              <option value="元/桶">元/桶</option>
              <option value="元/瓶">元/瓶</option>
              <option value="元/份">元/份</option>
            </select>
          </td>
        </tr>
        <tr>
          <td>包装规格：</td>
          <td>
            &lt;%&ndash;<input type="text" name="vegetableDescribe" maxlength="20">&ndash;%&gt;
            <select name="vegetableDescribe" style="width: 167">
              <option value="一人份">一人份</option>
              <option value="三人份">三人份</option>
              <option value="5升/桶">5升/桶"</option>
              <option value="500克/袋">500/袋</option>
              <option value="750ml/瓶">750ml/瓶</option>
            </select>
          </td>
        </tr>
        <tr>
          <td>
            描述：
          </td>
          <td>
            <input type="text" name="description" style="width: 167">
          </td>
        </tr>
        <tr>
          <td>剩余数量：</td>
          <td><input type="number" name="surplusNum" min="0" style="width: 167" required="required"> </td>
        </tr>
        <tr>
          <td>上线时间:</td>
          <td>
            <input type="date" name="saleTime"   required="true" id="saletime"  style="width: 146">
            <img onclick="WdatePicker({el:'saletime'})" src="js/My97DatePicker/skin/datePicker.gif" width="16" height="22" align="absmiddle">

          &lt;%&ndash;<input type="date" name="createTime" required="true" id="createTime" style="width: 146">&ndash;%&gt;
            &lt;%&ndash;<img onclick="WdatePicker({el:'createTime'})" src="js/My97DatePicker/skin/datePicker.gif" width="16" height="22" align="absmiddle">&ndash;%&gt;
            &lt;%&ndash;<input type="text" name="saleTime"  readonly="readonly"  id="saletime">&ndash;%&gt;
            &lt;%&ndash;<script type="application/javascript">&ndash;%&gt;
              &lt;%&ndash;$(document).ready(function() {&ndash;%&gt;
                &lt;%&ndash;var AddDayCount = 1;&ndash;%&gt;
                &lt;%&ndash;var dd = new Date();&ndash;%&gt;
                &lt;%&ndash;dd.setDate(dd.getDate()+AddDayCount);//获取AddDayCount天后的日期&ndash;%&gt;
                &lt;%&ndash;var y = dd.getFullYear();&ndash;%&gt;
                &lt;%&ndash;var m = dd.getMonth()+1;//获取当前月份的日期&ndash;%&gt;
                &lt;%&ndash;var d = dd.getDate();&ndash;%&gt;
                &lt;%&ndash;time = y+"-"+m+"-"+d;&ndash;%&gt;
                &lt;%&ndash;$("#saletime").val(time)&ndash;%&gt;
              &lt;%&ndash;});&ndash;%&gt;
            &lt;%&ndash;</script>&ndash;%&gt;
          </td>
        </tr>
        <tr>
          <td>图片样式：</td>
          <td align="center"><input name="img" type="file">(图片大小不能大于100k)<font style="color: #ff0000;"> <c:out value="${errorAlertContent}"/></font></td>
        </tr>
        <tr>
          <td colspan="2"><input type="submit" value="确定"></td>
        </tr>
      </form>
    </table>--%>
    <form action="/store/saveVegetable"  enctype="multipart/form-data" method="post">
      <div class="product_inputs">
        <div class="product_input_row">
          <div class="product_input_col"><span class="cate_name">销售类别:</span></div>
          <div class="product_input_r_col">
            <select name="vegetableCategoryId"  required="required" class="input">
              <c:forEach var="vegetableCategorie" items="${vegetableCategories}">
                <option value="${vegetableCategorie.id}">${vegetableCategorie.categoryName}</option>
              </c:forEach>
            </select>
          </div>
        </div>

        <div class="product_input_row">
          <div class="product_input_col"><span class="cate_name">蔬菜名:</span></div>
          <div class="product_input_r_col">
            <input type="text" name="vegetableName" maxlength="10" required="required" class="input">
          </div>
        </div>

        <div class="product_input_row">
          <div class="product_input_col"><span class="cate_name">单价:</span></div>
          <div class="product_input_r_col">
            <input type="number" name="unitPrice" min="0"  required="required" step="0.01" max="10000" class="input">          </div>
        </div>


        <div class="product_input_row">
          <div class="product_input_col"><span class="cate_name">单位:</span></div>
          <div class="product_input_r_col">
            <select name="unitName"   required="required" class="input">
              <option value="元/个"> 元/个</option>
              <option value="元/斤">元/斤</option>
              <option value="元/袋">元/袋</option>.
              <option value="元/50克">元/50克</option>
              <option value="元/升">元/升</option>
              <option value="元/桶">元/桶</option>
              <option value="元/瓶">元/瓶</option>
              <option value="元/份">元/份</option>
            </select>
          </div>
        </div>

        <div class="product_input_row">
          <div class="product_input_col"><span class="cate_name">包装规格:</span></div>
          <div class="product_input_r_col">
            <select name="vegetableDescribe" class="input">
              <option value="一人份">一人份</option>
              <option value="三人份">三人份</option>
              <option value="5升/桶">5升/桶"</option>
              <option value="500克/袋">500/袋</option>
              <option value="750ml/瓶">750ml/瓶</option>
            </select>
          </div>
        </div>

        <div class="product_input_row">
          <div class="product_input_col"><span class="cate_name">描述:</span></div>
          <div class="product_input_r_col">
            <input type="text" name="description" class="input">
          </div>
        </div>

        <div class="product_input_row">
          <div class="product_input_col"><span class="cate_name">剩余数量:</span></div>
          <div class="product_input_r_col">
            <input type="number" name="surplusNum" min="0"  required="required" class="input">
          </div>
        </div>

        <div class="product_input_row">
          <div class="product_input_col"><span class="cate_name">上线时间:</span></div>
          <div class="product_input_r_col">
            <input type="date" name="saleTime"   required="true" id="saletime" class="input">
            <img onclick="WdatePicker({el:'saletime'})" src="js/My97DatePicker/skin/datePicker.gif" width="16" height="22" align="absmiddle">
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


  </div>

  <%--中部结束--%>

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
