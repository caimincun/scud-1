<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>

<!doctype html>
<html>
<head>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%
        String path = request.getContextPath();
        String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path;
    %>
    <base href="<%=basePath%>">
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
    <title>${canteenName}</title>
    <link href="css/base.css" rel="stylesheet">
    <script src="js/jquery-2.1.1.js"></script>
    <link href="css/fabu_qiandan.css" rel="stylesheet">
    <link href="css/new_top.css" rel="stylesheet">
</head>
<body>
<div class="main" style="background-color: #d8d8d8">
    <div class="container2" style="background-color: #d8d8d8">
        <div class="new_top">
            <a href="signbill/toSignBillPage"><img src="image/back.gif" class="back_btn"></a>
            <div class="top_title"><span class="title">申请</span></div>
        </div>
        <div class="row" id="select_lin">
            <div class="row_left" id="select_line_text">选择审批链</div>
        </div>
        <div class="line_div" id="line_div">

            <c:forEach var="item" items="${signchans}">
                <div class="line_row">
                    <div class="link_name">
                        <div class="my_radio" data-id="${item.id}">
                            <div class="my_radio_in" id="my_radio_in_${item.id}"></div>
                        </div>
                        <div style="float: left;margin-top: 0.1rem;font-size: 0.13rem">${item.chainName}</div>
                    </div>
                    <div class="link_row_right">
                        <c:forEach var="node" items="${item.signChainNodes}" varStatus="status">
                            <c:choose>
                                <c:when test="${status.index==0}">
                                    <div class="node">
                                        <div class="node_row">${node.nodeName}</div>
                                    </div>
                                    <img src="image/right_arrow.png" class="array_img">
                                </c:when>
                                <c:otherwise>
                                    <div class="node">
                                        <div class="node_row">${node.nodeName}</div>
                                    </div>
                                    <c:if test="${!status.last}">
                                        <img src="image/right_arrow.png" class="array_img">
                                    </c:if>
                                </c:otherwise>
                            </c:choose>
                        </c:forEach>
                    </div>
                </div>
            </c:forEach>
        </div>

        <div class="nodes" id="nodes">
            <div class="chain_node">
                <div class="chain_node_name">提交审批</div>
                <div class="node_approver">
                    我
                </div>
            </div>
            <img src="image/right_arrow.png" class="array_img">
            <div class="chain_node">
                <div class="chain_node_name">部门审批</div>
                <div class="node_approver">
                <select>
                    <option>张总</option>
                    <option>王总</option>
                    <option>李总</option>
                </select>
                </div>
            </div>
            <img src="image/right_arrow.png" class="array_img">
            <div class="chain_node">
                <div class="chain_node_name">综合部审批</div>
                <div class="node_approver">
                    <select>
                        <option>赵总</option>
                    </select>
                </div>
            </div>
            <img src="image/right_arrow.png" class="array_img">
            <div class="chain_node">
                <div class="chain_node_name">食堂审批</div>
                <div class="node_approver">
                    <select>
                        <option>孙总</option>
                    </select>
                </div>
            </div>
        </div>

        <div class="apply_content">
            <input class="apply_title" placeholder="输入标题">

            <div class="reason_text">就餐原因</div>
			<textarea class="apply_reason">
			</textarea>

            <div class="input_row">
                <div class="reason_text">就餐人数:</div>
                <input type="text" class="people_count">
            </div>
            <div class="input_row">
                <div class="reason_text">就餐时间:</div>
                <input type="datetime-local" class="date_time">
            </div>
            <div class="input_row">
                <div class="reason_text">就餐标准:</div>
                <input type="text" class="people_count">
            </div>
            <div class="input_row" style="padding-bottom: 0.1rem">
                <div class="reason_text">就餐地址:</div>
                <input type="text" class="people_count" style="width: 2rem">
            </div>
        </div>

        <button class="btn">
            提交
        </button>
    </div>

</div>
</body>
<script>
    $(function () {
        $("#select_lin").click(function () {
            if ($("#line_div").is(":hidden")) {
                $("#line_div").slideDown();    //如果元素为隐藏,则将它显现
            } else {
                $("#line_div").slideUp();     //如果元素为显现,则将其隐藏
            }
        });
        $(".my_radio").click(function () {
            var id = $(this).data("id");
            $(".my_radio_in").hide();
            $("#my_radio_in_" + id).show();
    /*$.post("signbill/getSignNodes",{
                signChainId:id
            }, function (res) {
                var html='';
                if(res.result==0){
                    //var data=res.data;
                    console.log(res);
                    var nodes=res.nodes;
                    var approvers=res.approvers;
                    *//*for(var i=0;i<nodes.length;i++){
                        html+="<div class=\"chain_node\">"
                        +"<div class=\"chain_node_name\">"+nodes[i].nodeName+"</div>"+
                        +" <div class=\"node_approver\"> " +
                        +"<select>";
                        for(var j=0;j<approvers.length;j++){
                            html+="<option>"+approvers[j].user.nickName+"</option>";
                        }
                        html+="</select>"+
                        "</div></div>"+
                        +"<img src=\"image/right_arrow.png\" class=\"array_img\">";
                    }
*//*

                    *//*$("#nodes").html('');
                    $("#nodes").append(html);*//*
                }
            });*/

            $("#line_div").slideUp();
            $(".nodes").show();
            $("#select_line_text").html("修改审批链");
        });
    });
</script>
</html>
