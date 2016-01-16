<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8"%>

<!doctype html>
<html>
<base >
<head>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
	<%
		String path = request.getContextPath();
		String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
	%>
	<base href="<%=basePath%>">
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title>${canteenName}</title>
	<link href="css/base.css" rel="stylesheet">
	<link href="css/register.css" rel="stylesheet">
	<link href="css/top.css" rel="stylesheet">
</head>
<body>

<div class="main">
	<div class="top">
		<a id="back" href="javascript:history.go(-1)"><img src="image/icon_back.png" class="top_left"></a>
		<span class="top_right">注册</span>
	</div>
	<div class="bottom">
		<%--<div class="user_name">--%>
			<%--<input type="text" placeholder="输入姓名" class="user_name_input" id="user_name">--%>
		<%--</div>--%>
		<div class="refectory_div">
			<select class="refectory_select" id="canteen">
				<option value="no">请选择食堂</option>
				<c:forEach var="canteen" items="${canteenList}">
					<option value="${canteen.id}">${canteen.canteenName}</option>
				</c:forEach>
			</select>
		</div>
		<div class="check_phone_input_div">
			<input type="tel"  class="check_phone_input_tel " placeholder="输入手机号" id="phone" name="phone">
		</div>
		<div class="check_phone_code_div">
			<div class="check_phone_input_code_div">
				<input type="tel" id="code_input" class="check_phone_input_code" placeholder="输入手机验证码"  name="code" >
			</div>
			<a href="javascript:void(0)" id="get_code"><button class="check_phone_get_code" ><span class="code_btn_font_type" id="get_code_text">获取验证码</span></button></a>
			<a href="javascript:void(0)" id="after_get_code"><button class="check_phone_get_code" ><span class="code_btn_font_type" id="after_get_code_text">获取验证码</span></button></a>
		</div>
		<div class="check_result" id="check_result">
			<span class="check_result_text" id="message" ></span>
		</div>
		<div class="get_code_result" id="get_code_result">
			<div class="get_code_result_text" id="get_code_result_text">提醒：验证码已发送，请查收</div>
		</div>
		<div class="error_result" id="error_result">
			<div class="error_message" id="error_message" ></div>
		</div>
	</div>
	<a href="javascript:void(0)" id="register">
		<button class="check_phone_submit"><strong style="font-family: SimHei ">提交</strong>
		</button>
	</a>
	<a href="javascript:void(0)" id="to_test_canteen">
		<button class="check_phone_submit"><strong style="font-family: SimHei ">演示食堂</strong>
		</button>
	</a>
</div>
<form style="display: none" id="to_test_form" action="user/toTestIndexPage" method="post">
</form>
<script src="js/jquery-2.1.1.js"></script>
<script src="js/tool.js"></script>
</body>

<script type="text/javascript">
	function myAlert(value){
		$("#error_message").html(value);
		$("#error_result").show();
		setTimeout(function(){
			$("#error_result").hide();
		},3000);
	}


	/*$("#back").click(function(){
		window.location="user/toIndexPage";
	});*/

    var count=60;
	var time;
	var checkTime;

	function timeCount(){
		$("#after_get_code_text").html("倒计时"+(--count)+"s");
	}

	function check() {
		if(count<0)
		{
			$("#after_get_code").hide();
			$("#get_code").show();
			$("#get_code_text").html("获取验证码");
			window.clearInterval(time);  //结束setInterval循环
			window.clearInterval(checkTime);
			count=60;
		}
	}

	$(function(){
		$("#to_test_canteen").click(function () {
			$("#to_test_form").submit();
		});

		$("#after_get_code").hide();
		$("#check_result").hide();
		$("#get_code_result").hide();
		$("#error_result").hide();
		$("#get_code").click(function(){
			//var isPhone=/^(?:13\d|15\d|18\d)\d{5}(\d{3}|\*{3})$/;
			var isPhone=/^1\d{10}$/;
			var phone = $("#phone").val();
			if(!isPhone.test(phone)){ //如果用户输入的值不同时满足手机号和座机号的正则
				myAlert("提醒：请正确填写手机号码!");
				$("#phone").focus();       //输入框获得光标
				return false;         //返回一个错误，不向下执行
			}
			$.post("user/getSMSCode",{phone:phone},
					function(data){
						var result = jQuery.parseJSON(data);
						if(result.result==0){
							$("#get_code").hide();
							checkTime = setInterval(check, 500);
							time = setInterval(timeCount, 1000);
							$("#after_get_code").show();
							$("#get_code_result").show();
							setTimeout(function(){
								$("#get_code_result").hide();
							},1000);
						}
					},
					"text");
	});
		$("#register").click(function(){
			var canteenId=$("#canteen option:selected").val();
			if("no"==canteenId){
				myAlert("提示：请选择食堂");
				return false;
			}
//			var userName=$("#user_name").val();
//			if(""==userName){
//				myAlert("提醒：姓名不能为空");
//				return false;
//			}
			var validateCode=$("#code_input").val();
			//var count=validateCode.length;
			var phone = $("#phone").val();
			var isPhone=/^1\d{10}$/;
			if(phone.length==0){
				myAlert("提醒：手机号不能为空");
				$("#phone").focus();
				return false;
			}
			if(!isPhone.test(phone)){ //如果用户输入的值不同时满足手机号和座机号的正则
				myAlert("提醒：请正确填写手机号码");
				$("#phone").focus();       //输入框获得光标
				return false;         //返回一个错误，不向下执行
			}
			if(validateCode.length==0){
				myAlert("提醒：验证码不可以为空");
				$("#code_input").focus();
				return false;
			}
			var isCode=/\d{4}$/;
			if(!isCode.test(validateCode)){ //如果用户输入的值不同时满足手机号和座机号的正则
				myAlert("提醒：验证码格式不正确");
				$("#code_input").focus();       //输入框获得光标
				return false;         //返回一个错误，不向下执行
			}
			$.post("user/register",
					{
//						userName:userName,
						canteenId:canteenId,
						phone:phone,
						smsCode:validateCode
					},
					function(data){
						var result = jQuery.parseJSON(data);
						if(confirmAjaxResult(result)){
							$("#message").html("您已成功注册");
							$("#check_result").show();
							setTimeout(function(){
								$("#check_result").hide();
								//window.location.href = "${pre_url}";
								window.location.href = "user/toIndexPage";
							},500);
						}
						/*if(result.result==1011){
							$("#message").html(result.msg);
							$("#check_result").show();
							setTimeout(function(){
								$("#check_result").hide();
								return false;
							},500);
						}*/

					},
					"text");
		});
	});

</script>

</html>