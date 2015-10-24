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
    <link href="/css/new_top.css" rel="stylesheet">
    <link href="/css/questions.css" rel="stylesheet">
    <script src="/js/jquery-2.1.1.js"></script>

</head>
<body>
<div class="new_top">
        <a href="/questionary/toQuestionCategoryPage"><img src="image/back.gif" class="back_btn"></a>
        <div class="top_title"><span class="title">${categoryName}</span></div>
    <a href="user/toIndexPage"><img src="image/home.png" class="home"></a>
</div>

<div class="main">
    <div class="many_select_list_div">
        <c:forEach var="question" items="${questions}" varStatus="qStatus" begin="0">
        <div class="list_item">
            <c:choose>
                <c:when test="${question.questionType==1}">
                    <div class="question_div">
                        <span class="sex_text">${qStatus.index+1}、${question.title}</span>
                    </div>
                    <c:forEach var="option" items="${question.answers}" varStatus="oStatus" begin="0">
                        <div class="select_div">
                            <div class="option_div">
                                <div class="check_box_div"><input type="radio" class="check_box" id="${option.id}"
                                                                  name="${question.id}" value="${option.id}"
                                                                  data-id="${qStatus.index}"></div>
                                <div class="option_text_div"><label class="option_text"
                                                                    for="${option.id}">${optionChar[oStatus.index]}、${option.value}</label>
                                </div>
                            </div>
                        </div>
                    </c:forEach>
                </c:when>
                <c:otherwise>
                    <div class="question_div">
                        <span class="sex_text">${qStatus.index+1}、${question.title}(可多选)</span>
                    </div>
                    <c:forEach var="option" items="${question.answers}" varStatus="oStatus" begin="0">
                        <div class="select_div">
                            <div class="option_div">
                                <div class="check_box_div"><input type="checkbox" class="check_box" id="${option.id}"
                                                                  name="${question.id}" value="${option.id}"
                                                                  data-id="${qStatus.index}"></div>
                                <div class="option_text_div"><label class="option_text"
                                                                    for="${option.id}">${optionChar[oStatus.index]}、${option.value}</label>
                                </div>
                            </div>
                        </div>
                    </c:forEach>
                </c:otherwise>
            </c:choose>
        </div>
        </c:forEach>
    </div>

</div>
<div class="three">您认为食堂存在哪些问题？应该怎样改进？</div>
<!-- placeholder="听说填了这个中奖率高出不少哦，别怪小清没告诉你！"-->
<textarea class="area"  id="other" name="other"></textarea>

<div class="error_result" id="error_result">
    <div class="error_message" id="error_message"></div>
</div>
<div class="btn_div">
    <button class="sub_btn" id="sub_btn">提交</button>
</div>
</div>
<form id="hide_form" action="/questionary/addQuestionAnswers" style="display:none;" method="post">
    <input type="hidden" name="result_json" value="" id="result_json">
    <input type="hidden" name="other_info" value="" id="other_info">
</form>
</body>
<script>
    function myAlert(value) {
        $("#error_message").html(value);
        $("#error_result").show();
        setTimeout(function () {
            $("#error_result").hide();
        }, 3000);
    }
    function myAlert2(value) {
        $("#error_message").html(value);
        $("#error_result").show();
        setTimeout(function () {
            $("#error_result").hide();
            window.location.href="/questionary/toQuestionCategoryPage";
        }, 1000);
    }

    function createObj(id, questionId, answerId) {
        var o = {};
        o.id = id;
        o.questionId = questionId;
        o.answerId = answerId;
        return o;
    }

    var questionsCount =${count};
    $("#sub_btn").click(function () {
        var array = [];
        $("input").each(function () {

            if (this.checked) {
                var id = $(this).data("id");
                var obj;
                for (var i = 0; i < array.length; i++) {
                    if (this.name == array[i].questionId) {
                        obj = array[i];
                        array[i].answerId = array[i].answerId + "," + $(this).val();
                    }
                }
                if (obj == null) {
                    obj = createObj(id, this.name, $(this).val());
                    array.push(obj);
                }
            }

        });
        var length = array.length;
        if (length == 0) {
            myAlert("亲，第" + 1 + "题您还没答哦");
            return false;
        } else {
            var index = 0;
            for (var i = 0; i < questionsCount; i++) {
                if (array[i] == null || array[i].id != i) {
                    index = i + 1;
                    break;
                }
            }
            if (index != 0) {
                myAlert("亲，第" + index + "题您还没答哦");
                return false;
            } else {
                //$("#result_json").val(JSON.stringify(array));
                //$("#other_info").val($("#other").val());
                //$("#hide_form").submit();
                $.post("/questionary/addQuestionAnswers",{
                    result_json: JSON.stringify(array),
                    other_info:$("#other").val()
                }, function (res) {
                    if(res.result==0){
                        myAlert2("答题结束，谢谢参与！")
                    }
                });
            }
        }
    });
</script>
</html>
