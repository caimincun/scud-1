<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>

<!doctype html>
<html>
<base>
<head>
    <%
        String path = request.getContextPath();
        String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path ;
    %>
    <base href="<%=basePath%>">
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
    <title>${canteenName}</title>
    <link href="/css/base.css" rel="stylesheet">
    <link href="/css/new_index.css" rel="stylesheet">
    <link href="/css/top.css" rel="stylesheet">
</head>

<style type="text/css">
    /* css 重置 */


    /* 本例子css -------------------------------------- */
    .slideBox {
        position: relative;
        overflow: hidden;
        margin: auto;
        max-width: 100%; /* 设置焦点图最大宽度 */
    }

    .slideBox .hd {
        position: absolute;
        height: 28px;
        line-height: 28px;
        bottom: 0;
        right: 0;
        z-index: 1;
    }

    .slideBox .hd li {
        display: inline-block;
        width: 5px;
        height: 5px;
        -webkit-border-radius: 5px;
        -moz-border-radius: 5px;
        border-radius: 5px;
        background: #333;
        text-indent: -9999px;
        overflow: hidden;
        margin: 0 6px;
    }

    .slideBox .hd li.on {
        background: #fff;
    }

    .slideBox .bd {
        position: relative;
        z-index: 0;
    }

    .slideBox .bd li {
        position: relative;
        text-align: center;
    }

    .slideBox .bd li img {
        background: url(image/loading.gif) center center no-repeat;
        vertical-align: top;
        width: 100%; /* 图片宽度100%，达到自适应效果 */
    }

    .slideBox .bd li a {
        -webkit-tap-highlight-color: rgba(0, 0, 0, 0);
    }

    /* 去掉链接触摸高亮 */
    .slideBox .bd li .tit {
        display: block;
        width: 100%;
        position: absolute;
        bottom: 0;
        text-indent: 10px;
        height: 28px;
        line-height: 28px;
        background: url(images/focusBg.png) repeat-x;
        color: #fff;
        text-align: left;
    }

</style>

<body style="background: url(image/bg.png)">
<div class="main">
    <div class="container2">

        <div id="slideBox" class="slideBox">
            <div class="bd">
                <ul>
                    <li>
                        <a id="to_guangbo" class="pic"><img src="/image/zt_01.png"/></a>
                    </li>
                    <li>
                        <a  class="pic"><img src="/image/zt-02.jpg"/></a>
                    </li>
                    <li>
                        <a  class="pic"><img src="/image/zt-03.jpg"/></a>
                    </li>
                    <li>
                        <a  class="pic"><img src="/image/zt-04.jpg"/></a>
                    </li>
                </ul>
            </div>

            <div class="hd">
                <ul></ul>
            </div>
        </div>
        <!-- 本例主要代码 End ================================-->
        <div class="bark">
            <img src="/image/bark.png" class="bark_img">

            <div class="run_lamp">
                <marquee direction="right">感谢您使用${canteenName}微信平台!</marquee>
            </div>
        </div>

        <div class="index_bottom">
            <c:forEach var="module" items="${canteenModules}" varStatus="index">
        <c:choose>
            <c:when test="${(index.index+1)%3==1}">
            <div class="model_row">
                <div class="model_col">
                    <c:if test="${module.moduleCode=='m_dingcan'}">
                        <a href="product/toDianCanPage"><img src="/image/03.gif" class="model_img"/></a>
                        <%--<a href="user/recharge/test2"><img src="/image/03.gif" class="model_img"/></a>--%>
                    </c:if>
                    <c:if test="${module.moduleCode=='m_qucan'}">
                        <a href="order/toAcceptDinnerPage"> <img src="/image/04.gif" class="model_img"/></a>
                    </c:if>
                    <c:if test="${module.moduleCode=='m_qucai'}">
                        <img src="/image/08.gif" class="model_img list_order" data-id="1"/>
                    </c:if>
                    <c:if test="${module.moduleCode=='m_xinxi'}">
                        <a href="/information/toInfoCategoryPage"><img src="/image/05.gif" class="model_img"/></a>
                    </c:if>
                    <c:if test="${module.moduleCode=='m_maicai'}">
                      <a href="vegetable/list"><img src="/image/07.gif" class="model_img"/></a>
                    </c:if>
                    <c:if test="${module.moduleCode=='m_center'}">
                        <a href="user/toPersonCenterPage"><img src="/image/01.gif" class="model_img"/></a>
                    </c:if>
                </div>
            </c:when>
            <c:when test="${(index.index+1)%3==0}">
                <div class="model_col">
                    <c:if test="${module.moduleCode=='m_dingcan'}">
                        <a href="product/toDianCanPage">
                            <img src="/image/03.gif" class="model_img"/>
                        </a>
                    </c:if>
                    <c:if test="${module.moduleCode=='m_qucan'}">
                        <a href="order/toAcceptDinnerPage"> <img src="/image/04.gif" class="model_img"/></a>
                    </c:if>
                    <c:if test="${module.moduleCode=='m_qucai'}">
                        <img src="/image/08.gif" class="model_img list_order" data-id="1"/>
                    </c:if>
                    <c:if test="${module.moduleCode=='m_xinxi'}">
                        <a href="/information/toInfoCategoryPage"><img src="/image/05.gif" class="model_img"/></a>
                    </c:if>
                    <c:if test="${module.moduleCode=='m_maicai'}">
                        <a href="vegetable/list"><img src="/image/07.gif" class="model_img"/></a>
                    </c:if>
                    <c:if test="${module.moduleCode=='m_center'}">
                        <a href="user/toPersonCenterPage"><img src="/image/01.gif" class="model_img"/></a>
                    </c:if>
                </div>
            </div>
            </c:when>
            <c:otherwise>
            <div class="model_col">
                <c:if test="${module.moduleCode=='m_dingcan'}">
                    <a href="product/toDianCanPage">
                        <img src="image/03.gif" class="model_img"/>
                    </a>
                </c:if>
                <c:if test="${module.moduleCode=='m_qucan'}">
                    <a href="order/toAcceptDinnerPage"> <img src="/image/04.gif" class="model_img"/></a>
                </c:if>
                <c:if test="${module.moduleCode=='m_qucai'}">
                    <img src="/image/08.gif" class="model_img list_order" data-id="1"/>
                </c:if>
                <c:if test="${module.moduleCode=='m_xinxi'}">
                    <a href="/information/toInfoCategoryPage"><img src="/image/05.gif" class="model_img"/></a>
                </c:if>
                <c:if test="${module.moduleCode=='m_maicai'}">
                    <a href="vegetable/list"><img src="/image/07.gif" class="model_img"/></a>
                </c:if>
                <c:if test="${module.moduleCode=='m_center'}">
                    <a href="user/toPersonCenterPage"><img src="/image/01.gif" class="model_img"/></a>
                </c:if>
            </div>
        </c:otherwise>
        </c:choose>

        </c:forEach>
    </div>
</div>
</div>
<script src="/js/jquery-2.1.1.js"></script>
<script src="/js/TouchSlide.1.1.js"></script>
<script type="text/javascript">
    TouchSlide({
        slideCell: "#slideBox",
        titCell: ".hd ul", //开启自动分页 autoPage:true ，此时设置 titCell 为导航元素包裹层
        mainCell: ".bd ul",
        effect: "leftLoop",
        autoPage: true,//自动分页
        autoPlay: true //自动播放
    });
</script>
<form style="display: none" id="hide_form" action="order/queryOrders" method="post">
    <input type="hidden" value="" name="orderType" id="order_type">
</form>
<form style="display: none" id="too_guangbo_form" action="information/toInformationPage" method="post">
    <input type="hidden" value="4" name="categoryId" >
</form>
</body>
<script>
    $(function () {
        $(".list_order").click(function () {
            var id=$(this).data("id");
            $("#order_type").val(id);
            $("#hide_form").submit();
        });
        $("#to_guangbo").click(function () {
            $("#too_guangbo_form").submit();
        });
    });
</script>
</html>
