<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>


<html>
<head>
    <base href="<%=basePath%>">
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <link href="assets/css/bootstrap.min.css" rel="stylesheet"/>
    <link rel="stylesheet" href="assets/css/font-awesome.min.css"/>
    <link rel="stylesheet" href="assets/css/ace.min.css"/>
    <link rel="stylesheet" href="assets/css/ace-rtl.min.css"/>
    <link rel="stylesheet" href="assets/css/ace-skins.min.css"/>
    <script src="assets/js/ace-extra.min.js"></script>
    <script src="js/jquery-2.1.1.js"></script>
    <script src="js/excel.js"/>

    <script src="assets/js/jquery.dataTables.min.js"></script>
    <script src="assets/js/jquery.dataTables.bootstrap.js"></script>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <%-- <link rel="stylesheet" type="text/css" href="/css/jquery.dataTables.css"/>--%>
    <title>listView</title>
<style>
    th{text-align:center}
</style>
</head>
<body>
<button onclick="dayin()" id="test">打 印</button>
<button onclick="saveAsExcel('xiazai')" id="test2">下载</button>
<table class="table table-bordered table-striped" id="xiazai">
    <thead>
    <tr>
        <th>id</th>
        <th>计划名称</th>
    </tr>
    </thead>
    <tbody>
        <tr>
            <td>1</td>
            <td>张女士，199923345443，茄子 一斤,茄子 一斤,茄子 一斤,苦瓜 一斤,甜椒 一斤,蔬菜 一斤张女士，199923345443，茄子 一斤,茄子 一斤,茄子 一斤,苦瓜 一斤,甜椒 一斤,蔬菜 一斤张女士，199923345443，茄子 一斤,茄子 一斤,茄子 一斤,苦瓜 一斤,甜椒 一斤,蔬菜 一斤 </td>
        </tr>
        <tr>
            <td>1</td>
            <td>张女士，199923345443，茄子 一斤,茄子 一斤,茄子 一斤,苦瓜 一斤,甜椒 一斤,蔬菜 一斤 </td>
        </tr>
        <tr>
            <td>1</td>
            <td>张女士，199923345443，茄子 一斤,茄子 一斤,茄子 一斤,苦瓜 一斤,甜椒 一斤,蔬菜 一斤 </td>
        </tr>
        <tr>
            <td>1</td>
            <td>张女士，199923345443，茄子 一斤,茄子 一斤,茄子 一斤,苦瓜 一斤,甜椒 一斤,蔬菜 一斤 </td>
        </tr>
        <tr>
            <td>1</td>
            <td>张女士，199923345443，茄子 一斤,茄子 一斤,茄子 一斤,苦瓜 一斤,甜椒 一斤,蔬菜 一斤 </td>
        </tr>
        <tr>
            <td>1</td>
            <td>张女士，199923345443，茄子 一斤,茄子 一斤,茄子 一斤,苦瓜 一斤,甜椒 一斤,蔬菜 一斤 </td>
        </tr>
        <tr>
            <td>1</td>
            <td>张女士，199923345443，茄子 一斤,茄子 一斤,茄子 一斤,苦瓜 一斤,甜椒 一斤,蔬菜 一斤 </td>
        </tr>
        <tr>
            <td>1</td>
            <td>张女士，199923345443，茄子 一斤,茄子 一斤,茄子 一斤,苦瓜 一斤,甜椒 一斤,蔬菜 一斤 </td>
        </tr>
        <tr>
            <td>1</td>
            <td>张女士，199923345443，茄子 一斤,茄子 一斤,茄子 一斤,苦瓜 一斤,甜椒 一斤,蔬菜 一斤 </td>
        </tr>
    </tbody>
    </table>
<script>
    function dayin(){
        console.log($(document).height());
        var  $test= $("#test");
        $test.hide();
       window.print();
        $test.show();
    }


</script>
<script src="/js/resizeIframe.js"></script>
</body>
</html>