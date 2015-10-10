<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!doctype html>
<html>
<base>
<head>
    <%
        String path = request.getContextPath();
        String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
    %>
    <base href="<%=basePath%>">
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
    <title>${canteenName}</title>
    <title></title>
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <link href="css/base.css" rel="stylesheet">
    <link href="css/new_top.css" rel="stylesheet">
    <script src="js/jquery-2.1.1.js"></script>
    <script src="js/bootstrap.min.js"></script>
    <style>
        .main{
            width: 94%;
            float: left;
            margin-left: 3%;
            margin-top: 0.2rem;
            padding-bottom: 0.5rem;
            color: #000000;
        }
        .table{
            width: 100%;
            float: left;
            border-right:0.01rem #000000 solid ;
            border-bottom:0.01rem #000000 solid ;
            margin-top: 0.1rem;
            font-size: 0.14rem;
        }
        /* td{
             border-left:0.01rem #000000 solid;
             border-top:0.01rem #000000 solid;
         }
         th{
             border-left:0.01rem #000000 solid;
             border-top:0.01rem #000000 solid;
         }*/
        .contain{
            width: 80%;
            margin-left: auto;
            margin-right: auto;
            min-height: 2rem;
            border-top: 0.01rem  #c8c8c8 solid;
        }
        .row{
            float: left;
            width: 100%;
            height: 0.3rem;
            border-bottom: 0.01rem  #c8c8c8 solid;
        }
        .col{
            float: left;
            width: 25%;
            height: 100%;
        }
        .col_right_line {
            width: 0.02rem;
            border-right: 0.01rem #c8c8c8 solid;
            float: right;
            height: 100%;
        }
        .col_left_line {
            width: 0.02rem;
            border-left: 0.01rem #c8c8c8 solid;
            float: left;
            height: 100%;
        }
    </style>
</head>
<body>
<div class="new_top">
    <a href="/user/toIndexPage"><img src="image/back.gif" class="back_btn"></a>
    <div class="top_title"><span class="title">${categoryName}</span></div>
    <a href="user/toIndexPage"><img src="image/home.png" class="home"></a>
</div>
<div class="main">
    <div style="width: 100%;text-align: center;font-size: 0.18rem">本数据来自于成都市发改委官方统计</div>
    <table class="table table table-bordered" border="0" cellspacing="0" cellpadding="0" >
        <thead>
        <tr>
            <th>名称</th>
            <th>产品规格</th>
            <th>计量单位</th>
            <th>最高价</th>
            <th>最低价</th>
        </tr>
        </thead>
        <tbody>
        <tr><td>籼米</td><td>标一，汉中</td><td>元/500克</td><td>2.8</td><td>2.3</td></tr>
        <tr><td>粳米</td><td>标一，东北珍珠</td><td>元/500克</td><td>3.0</td><td>2.6</td></tr>
        <tr><td>面粉</td><td>特一粉</td><td>元/500克</td><td>3.0</td><td>2.3</td></tr>
        <tr><td>切面</td><td>韭菜叶子</td><td>元/500克</td><td>3.5</td><td>2.4</td></tr>
        <tr><td>绿豆</td><td>一级</td><td>元/500克</td><td>7.0</td><td>6.0</td></tr>
        <tr><td>调和油</td><td>金龙鱼二代调和油，5升桶装</td><td>元/桶</td><td>68.0</td><td>55.0</td></tr>
        <tr><td>大白菜</td><td>上等</td><td>元/500克</td><td>2.0</td><td>1.0</td></tr>
        <tr><td>莲花白</td><td>上等</td><td>元/500克</td><td>2.0</td><td>1.2</td></tr>
        <tr><td>芹菜</td><td>上等</td><td>元/500克</td><td>5.0</td><td>2.5</td></tr>
        <tr><td>韭菜</td><td>上等</td><td>元/500克</td><td>4.0</td><td>3.0</td></tr>
        <tr><td>花菜</td><td>上等</td><td>元/500克</td><td>3.5</td><td>2.5</td></tr>
        <tr><td>空心菜</td><td>上等</td><td>元/500克</td><td>3.0</td><td>1.5</td></tr>
        <tr><td>青笋</td><td>上等</td><td>元/500克</td><td>3.5</td><td>2.0</td></tr>
        <tr><td>黄瓜</td><td>上等</td><td>元/500克</td><td>3.5</td><td>1.5</td></tr>
        <tr><td>冬瓜</td><td>上等</td><td>元/500克</td><td>2.0</td><td>1.0</td></tr>
        <tr><td>西红柿</td><td>上等</td><td>元/500克</td><td>3.0</td><td>1.5</td></tr>
        <tr><td>茄子</td><td>上等</td><td>元/500克</td><td>3.0</td><td>1.5</td></tr>
        <tr><td>白萝卜</td><td>上等</td><td>元/500克</td><td>1.5</td><td>1.2</td></tr>
        <tr><td>大青椒</td><td>上等</td><td>元/500克</td><td>3.5</td><td>2.0</td></tr>
        <tr><td>四季豆</td><td>上等</td><td>元/500克</td><td>4.0</td><td>2.0</td></tr>
        <tr><td>土豆</td><td>上等</td><td>元/500克</td><td>2.5</td><td>2.0</td></tr>
        <tr><td>大葱</td><td>上等</td><td>元/500克</td><td>5.0</td><td>3.0</td></tr>
        <tr><td>蒜苔</td><td>上等</td><td>元/500克</td><td>7.0</td><td>4.0</td></tr>
        <tr><td>韭黄</td><td>上等</td><td>元/500克</td><td>8.0</td><td>6.0</td></tr>
        <tr><td>豇豆</td><td>上等</td><td>元/500克</td><td>4.0</td><td>2.5</td></tr>
        <tr><td>莲藕</td><td>上等</td><td>元/500克</td><td>7.5</td><td>5.0</td></tr>
        <tr><td>大蒜</td><td>独蒜上等</td><td>元/500克</td><td>10.0</td><td>8.0</td></tr>
        <tr><td>豆腐</td><td>水豆腐（散装）</td><td>元/500克</td><td>2.8</td><td>1.5</td></tr>
        <tr><td>食用盐</td><td>350克装</td><td>元/袋</td><td>2.5</td><td>1.5</td></tr>
        <tr><td>白砂糖</td><td>散装</td><td>元/500克</td><td>4.5</td><td>3.0</td></tr>
        <tr><td>猪肉</td><td>精瘦肉</td><td>元/500克</td><td>18.0</td><td>15.0</td></tr>
        <tr><td>猪肉</td><td>五花肉</td><td>元/500克</td><td>14.0</td><td>12.0</td></tr>
        <tr><td>猪肉</td><td>去骨一级腿肉</td><td>元/500克</td><td>14.0</td><td>13.0</td></tr>
        <tr><td>猪肉</td><td>大排</td><td>元/500克</td><td>20.0</td><td>16.0</td></tr>
        <tr><td>鸡肉</td><td>鲜冻土公鸡</td><td>元/500克</td><td>35.0</td><td>20.0</td></tr>
        <tr><td>鸡蛋</td><td>洋鸡蛋</td><td>元/500克</td><td>5.8</td><td>4.0</td></tr>
        <tr><td>鲫鱼</td><td>鲜活</td><td>元/500克</td><td>11.0</td><td>8.5</td></tr>
        <tr><td>草鱼</td><td>鲜活</td><td>元/500克</td><td>9.0</td><td>7.5</td></tr>
        <tr><td>苹果</td><td>一级红富士</td><td>元/500克</td><td>10.0</td><td>5.0</td></tr>
        <tr><td>香蕉</td><td>一级</td><td>元/500克</td><td>5.0</td><td>3.0</td></tr>
        </tbody>
    </table>

</div>
</body>
</html>

