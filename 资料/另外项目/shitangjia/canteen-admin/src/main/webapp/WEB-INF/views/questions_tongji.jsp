<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>

<!doctype html>
<html>
<base>
<head>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%
        String path = request.getContextPath();
        String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
    %>
    <base href="<%=basePath%>">
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="/css/base.css" rel="stylesheet">
    <link href="/css/top.css" rel="stylesheet">
    <link href="/css/wenjuan_tongji.css" rel="stylesheet">
    <script src="/js/jquery-2.1.1.js"></script>
        <script src="http://echarts.baidu.com/build/dist/echarts.js"></script>
        <script type="text/javascript">
            // 路径配置
            require.config({
                paths: {
                    echarts: 'http://echarts.baidu.com/build/dist'
                }
            });
      </script>
</head>
<body>
<div class="top">
    <div class="right">
        <span class="title">员工食堂满意度调查统计(有效参与人数76人)</span>
    </div>
</div>
<div class="main" >
    <div class="many_select_list_div" >
        <c:forEach var="question" items="${questions}" varStatus="qStatus" begin="0">
        <div class="list_item">
            <div class="options">
                <div class="question_div">
                    <span class="sex_text">${qStatus.index+1}、${question.title} </span>
                </div>
                <div class="select_div">
                <c:forEach var="option" items="${question.answerPojos}" varStatus="oStatus" begin="0">
                    <div class="option_div">
                        <div class="option_text_div"><span class="option_text">${option.itemStr}、${option.item}<span style="color: #ff578e ;margin-left: 20px">(${option.num})</span></span></div>
                    </div>
                </c:forEach>
                </div>
            </div>
            <div class="chart" id="chart_${question.id}">
            </div>
            <script type="text/javascript">
                require(
                        [
                            'echarts',
                            'echarts/chart/bar' // 使用柱状图就加载bar模块，按需加载
                        ],
                        function (ec) {
                            // 基于准备好的dom，初始化echarts图表
                            var myChart = ec.init(document.getElementById('chart_${question.id}'));

                            var option = {
                                tooltip: {
                                    show: true
                                },
                                legend: {
                                    data:['每个选项人数']
                                },
                                xAxis : [
                                    {
                                        type : 'category',
                                        data : [${question.xAxis}]
                                    }
                                ],
                                yAxis : [
                                    {
                                        type : 'value'
                                    }
                                ],
                                series : [
                                    {
                                        "name":"人数",
                                        "type":"bar",
                                        "data":[${question.data}]
                                    }
                                ]
                            };

                            // 为echarts对象加载数据
                            myChart.setOption(option);
                        }
                );
            </script>
        </div>
        </c:forEach>

    </div>

</div>
</body>
</html>
