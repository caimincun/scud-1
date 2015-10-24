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


</head>
<body style="height: 1500px">
<form action="/dept/saveDept" method="post">
  <h1>部门添加：</h1>
请输入部门名称：<input type="text" name="deptName" max="10">
<ul class="nav nav-list">
  <c:forEach var="canteenAuthorities" items="${canteenAuthoritieMap}" varStatus="status">
    <c:choose>
      <c:when test="${empty canteenAuthorities.value}">  <%--这是一级节点--%>

        <li class="active">
            <input type="checkbox" name="authority" onclick="check(this)" value="${canteenAuthorities.key.id}"><c:out value="${canteenAuthorities.key.authorityName}"/>
        </li>
      </c:when>
      <c:otherwise>     <%--这个是二级节点--%>
        <li>
            <input type="checkbox" name="authority" onclick="check(this)" value="${canteenAuthorities.key.id}"><c:out value="${canteenAuthorities.key.authorityName}"/>
          </a>
          <ul class="submenu">
            <c:forEach var="items" items="${canteenAuthorities.value}">
              <li>
                  <input type="checkbox" name="authority" onclick="check(this)" value="${items.id}"><c:out value="${items.authorityName}"/>
                </a>
              </li>
            </c:forEach>
          </ul>
        </li>
      </c:otherwise>
    </c:choose>
  </c:forEach>
</ul>
  <input type="submit" value="确认添加">
</form>
<script type="application/javascript">
  function check(obj){

    if($(obj).parent().parent().prev().attr("type") == "checkbox"){

      $(obj).parent().parent().prev().attr("checked", true);

      if($(obj).parent().parent().prev().attr("checked")){
//        console.log("父级被选中");
      }else
      {
//       console.log("父级没有别选中");
      }
    }
    else{ // 这儿是一级节点
//      console.log($(obj));
      if($(obj).attr("checked")){
          $(obj).removeAttr("checked");
//        alert("此一级节点取消选中");
      }else{

         $(obj).attr("checked", true);
//        alert("此一级节点被选中");
      }
    }

  }
</script>
<script src="js/jquery-2.1.1.js"></script>
<script src="/js/resizeIframe.js"></script>
</body>
</html>
