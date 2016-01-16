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
<form action="/dept/updateDept" method="post">
  <h1>部门修改：</h1>
  请输入部门名称：<input type="text" name="deptName" max="10" value="${canteenDept.deptName}">
                  <input type="text" name="canteenId" value="${canteenDept.canteenId}" hidden="true">
                  <input type="text" name="id" value="${canteenDept.id}" hidden="true">
  <ul class="nav nav-list">
    <c:forEach var="canteenAuthorities" items="${canteenAuthoritieMap}" varStatus="status">
      <c:choose>
        <c:when test="${empty canteenAuthorities.value}">  <%--这是一级节点--%>
          <c:set value="${0}" var="flag" scope="request"/>
          <li class="active">
            <c:forEach items="${deptAndAuthorityList}" var="deptAndAuthority">
              <c:choose>
              <c:when test="${deptAndAuthority.authortyId == canteenAuthorities.key.id}">
                <c:set value="${1}" var="flag" scope="request"/>
              </c:when>
              </c:choose>
            </c:forEach>
            <c:if test="${flag == 1}">
              <input type="checkbox" name="authority" onclick="check(this)" value="${canteenAuthorities.key.id}" checked="checked"><c:out value="${canteenAuthorities.key.authorityName}"/>
            </c:if>
            <c:if test="${flag != 1}">
              <input type="checkbox" name="authority" onclick="check(this)" value="${canteenAuthorities.key.id}"><c:out value="${canteenAuthorities.key.authorityName}"/>
            </c:if>

          </li>
        </c:when>
        <c:otherwise>     <%--这个是二级节点--%>
          <li>
            <c:set value="${0}" var="flag2"/>
            <c:forEach items="${deptAndAuthorityList}" var="deptAndAuthority">
              <c:choose>
                <c:when test="${deptAndAuthority.authortyId == canteenAuthorities.key.id}">
                  <c:set value="${1}" var="flag2"/>
                </c:when>
              </c:choose>
            </c:forEach>
            <c:if test="${flag2 ==1}">
              <input type="checkbox" name="authority" onclick="check(this)" value="${canteenAuthorities.key.id}" checked="checked"><c:out value="${canteenAuthorities.key.authorityName}"/>
            </c:if>
            <c:if test="${flag2 != 1}">
            <input type="checkbox" name="authority" onclick="check(this)" value="${canteenAuthorities.key.id}"><c:out value="${canteenAuthorities.key.authorityName}"/>
            </c:if>
          </li>
            </a>
            <ul class="submenu">
              <c:forEach var="items" items="${canteenAuthorities.value}">
                <li>
                  <c:set value="${0}" var="flag3"/>
                  <c:forEach items="${deptAndAuthorityList}" var="deptAndAuthority">
                    <c:choose>
                      <c:when test="${deptAndAuthority.authortyId == items.id}">
                        <c:set value="${1}" var="flag3"/>
                      </c:when>
                    </c:choose>
                  </c:forEach>
                  <c:if test="${flag3 == 1}">
                    <input type="checkbox" name="authority" onclick="check(this)" value="${items.id}" checked="checked"><c:out value="${items.authorityName}"/>
                  </c:if>
                  <c:if test="${flag3 != 1}">
                    <input type="checkbox" name="authority" onclick="check(this)" value="${items.id}"><c:out value="${items.authorityName}"/>
                  </c:if>

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
    console.log($(obj).parent().parent().prev().children());
    if($(obj).parent().parent().prev().children().attr("type") == "checkbox"){
      $(obj).parent().parent().prev().children().attr("checked", true);
    }
    else{ // 这儿是一级节点
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
