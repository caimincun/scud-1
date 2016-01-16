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
  <%--<style>--%>
    <%--table{width:100%;text-align:center;border-collapse:collapse;border-spacing:1;border-spacing:0; }--%>
  <%--</style>--%>
</head>
<body>


    <!-- 正文页头部 -->





      <%--正文头部结束--%>
      <!-- #好像这儿是页面中部 -->
      <div class="page-content">
      <%--中部开始--%>

        <h3 class="header smaller lighter blue">菜品种类列表</h3>

        <%--<div class="row">--%>
          <%--<div class="col-xs-12">--%>
            <%--<div class="table-responsive">--%>
              <table  class="table table-striped table-bordered table-hover">
                <thead>
                <tr>
                  <%--<th class="center">--%>
                    <%--<label>--%>
                      <%--<input type="checkbox" class="ace" />--%>
                      <%--<span class="lbl"></span>--%>
                    <%--</label>--%>
                  <%--</th>--%>
                  <th>种类列表</th>
                  <th>操作</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="productList" items="${productCategorys}">
                <tr>
                  <%--<td class="center" height="50">--%>
                    <%--<label>--%>
                      <%--<input type="checkbox" class="ace" />--%>
                      <%--<span class="lbl"></span>--%>
                    <%--</label>--%>
                  <%--</td>--%>
                  <td>

                     <c:out value="${productList.categoryName}"/>

                  </td>
                  <td>
                    <div class="visible-md visible-lg hidden-sm hidden-xs btn-group">
                      <button class="btn btn-xs btn-info" onclick="update(${productList.id})">
                        <i class="icon-edit bigger-120"></i>
                      </button>
                    </div>
                  </td>
                </tr>
                </c:forEach>
                <tr>
                  <td height="60"><input id='addPcroductCategory' type="button" value="新增" onclick="popup_show()"></td>
                  <td colspan="2">
                    <div class="sample_popup"     id="popup" style="visibility: hidden; display: none;">
                      <div class="menu_form_body">
                        <form method="post" action="/product/addProductCateGory">
                              请输入菜品名:
                              <input class="field" type="text" name="categoryName"/>
                             <input type="submit" value="确定" />
                        </form>
                      </div>
                    </div>

                  </td>
                </tr>
                </tbody>
                </table>
              <%--</div>--%>
            <%--</div>--%>
          <%--</div>--%>
        <script language="javascript">

          function update(id){
            window.location.href="/product/getProductCategory?id="+id;
          }
          function popup_show()
          {
            var element  = document.getElementById('popup');
            if(element.style.display == 'none'){
              element.style.position   = "absolute";
              element.style.visibility = "visible";
              element.style.display = "block";
             document.getElementById('addPcroductCategory').value="取消";
            }
            else{
              element.style.position   = "absolute";
              element.style.visibility = "visible";
              element.style.display = "none";
              document.getElementById('addPcroductCategory').value="新增";
            }

          }
        </script>

      <%--中部结束--%>
      </div><!-- /.page-content -->

    <!-- #这儿是页面中部结尾 -->


<!-- basic scripts -->



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
