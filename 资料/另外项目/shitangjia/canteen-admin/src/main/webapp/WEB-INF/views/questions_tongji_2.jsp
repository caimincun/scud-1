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
    <link href="/css/wenjuan_tongji2.css" rel="stylesheet">
    <script src="/js/jquery-2.1.1.js"></script>
</head>
<body>
<div class="top">
    <div class="right">
        <span class="title">员工食堂满意度调查统计</span>
    </div>
</div>
<div class="main" >

    <table style="width: 60%;margin-left: auto;margin-right: auto;margin-top: 0.1rem" cellspacing="0" cellpadding="0">
       <c:forEach var="info" items="${info}">
           <tr>
               <td>${info.phonenumber}</td>
               <td>
                       ${info.info}
               </td>
           </tr>
       </c:forEach>


    </table>
</div>
</body>
<script>
    function myAlert(value) {
        $("#error_message").html(value);
        $("#error_result").show();
        setTimeout(function () {
            $("#error_result").hide();
        }, 3000);
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
                console.log("题号:" + id);
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
                //console.log("value"+$(this).val());
            }

        });
        // console.log("问题数量:"+questionsCount);
        // console.log("已选择数量"+array.length)
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
                $("#result_json").val(JSON.stringify(array));
                $("#other_info").val($("#other").val());
                $("#hide_form").submit();
            }
        }


//    if(index==0&&array.length!=0){
//      index=array.length+1;
//    }
//    if(array.length==0){
//      index=1;
//    }
//    console.log("index："+index);
//    if(index>0&&index<=20){
//      myAlert("亲，第"+index+"题您还没答哦");
//      return false;
//    }else{
//     $("#result_json").val("");
//      $("#result_json").val(JSON.stringify(array));
//      $("#hide_form").submit();
//    }
    });
</script>
</html>
