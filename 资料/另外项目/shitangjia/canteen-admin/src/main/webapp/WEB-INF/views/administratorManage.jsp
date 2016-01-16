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
</head>
<body>
<div class="page-content">
  <h3 class="header smaller lighter blue">系统管理员列表</h3><input type="button" onclick="showDemo()" value="添加管理员">
  <table  class="table table-striped table-bordered table-hover">
    <thead>
    <tr>
      <th>用户</th>
      <th>手机号</th>
      <th>部门</th>
    </tr>
    </thead>
    <tbody>


    <c:forEach var="canteenUser" items="${canteenUserList}">
      <tr>
        <td>
          <c:out value="${canteenUser.name}"/>
        </td>
        <td>
          <c:out value="${canteenUser.phone_number}"/>
        </td>
        <td>
          <c:out value="${canteenUser.dept_name}"/>
        </td>
      </tr>
    </c:forEach>



    </tbody>
  </table>

  <div class="modal fade padding-top" id="demo" style="display: none;">
    <div class="modal-dialog" style="width: 280">
      <div class="modal-content">
        <form action="/canteenUser/addUser" method="post">
          <table border="1">
          <tr>
            <td>用户名：</td>
            <td><input type="text" name = "name"></td>
          </tr>
          <tr>
            <td>密码：</td>
            <td><input type="password" name = "password" ></td>
          </tr>
            <tr>
              <td>联系方式：</td>
              <td>
                <input type="text" name="phoneNumber">
              </td>
            </tr>
            <tr>
              <td>部门：</td>
              <td>
              <select name="detpId" style="width:170px">
                <c:forEach items="${deptList}" var="dept">
                  <option value="${dept.id}">
                   <c:out value="${dept.deptName}"/>
                  </option>
                </c:forEach>
              </select>
              </td>
            </tr>
            <tr>
              <td colspan="2" align="center">
                <input type="submit" value="提交"/>
              </td>
            </tr>
          </table>
      </div>
    </div>
  </div>
</div>

  <script>

    function showDemo(){
      $("#demo").modal('show');
    }

  </script>

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
