<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8"%>
<html>
<head>
	<title>微信支付</title>
	<style type="text/css">
		.btn {
			display: inline-block;
			padding: 12px 22px;
			margin-bottom: 0;
			background-color: #31B0D5;
			font-size: 14px;
			font-weight: 400;
			line-height: 1.42857;
			text-align: center;
			white-space: nowrap;
			vertical-align: middle;
			cursor: pointer;
			-moz-user-select: none;
			background-image: none;
			border: 1px solid transparent;
			border-radius: 4px;
		}
	</style>
	<script type="text/javascript" src="http://res.wx.qq.com/open/js/jweixin-1.0.0.js"/>
	<script type="text/javascript">
		var jsParam=${jsParam};

		wx.config({
			debug: true,
			appId: '${mxConfig.appId}',
			timestamp: '${mxConfig.timestamp}',
			nonceStr: '${mxConfig.nonceStr}',
			signature: '${mxConfig.signature}',
			jsApiList: [
				'checkJsApi',
				'onMenuShareTimeline',
				'onMenuShareAppMessage',
				'onMenuShareQQ',
				'onMenuShareWeibo',
				'hideMenuItems',
				'showMenuItems',
				'hideAllNonBaseMenuItem',
				'showAllNonBaseMenuItem',
				'translateVoice',
				'startRecord',
				'stopRecord',
				'onRecordEnd',
				'playVoice',
				'pauseVoice',
				'stopVoice',
				'uploadVoice',
				'downloadVoice',
				'chooseImage',
				'previewImage',
				'uploadImage',
				'downloadImage',
				'getNetworkType',
				'openLocation',
				'getLocation',
				'hideOptionMenu',
				'showOptionMenu',
				'closeWindow',
				'scanQRCode',
				'chooseWXPay',
				'openProductSpecificView',
				'addCard',
				'chooseCard',
				'openCard'
			]
		});

		function wxpay(){
			WeixinJSBridge.invoke(
					'getBrandWCPayRequest'
					,jsParam
					,callback
			);
		}

		function callback(res){
			//  返回 res.err_msg,取值
			// get_brand_wcpay_request:cancel   用户取消
			// get_brand_wcpay_request:fail  发送失败
			// get_brand_wcpay_request:ok 发送成功
			WeixinJSBridge.log(res.err_msg);
			if(res.err_msg=='get_brand_wcpay_request:ok')
			{
				showPayResult();
			}
			else
			{
				var msg='支付失败,请重新支付.';
				alert(msg);
				alert(res.err_code+" err_desc="+res.err_desc+" err_msg="+res.err_msg);
			}
		}
		function showPayResult(){
			alert("支付成功");
		}
	</script>
</head>
<body>
<div style="width: 80%;margin-left:50px;">
	<h1>Hello WXPay!</h1>

	<P>The time on the server is ${serverTime}.</P>
	<h3>微信调起支付，此页面请在微信客户端打开</h3>
	<ul>
		<li>来自Sunlight的微信支付测试</li>
		<li>支付测试</li>
		<li>预支付订单号：${prepayid }</li>
		<li>支付JS调起参数：${jsParam }</li>
		<li>JSCONFIG：appId: '${mxConfig.appId}',
			timestamp: '${mxConfig.timestamp}',
			nonceStr: '${mxConfig.nonceStr}',
			signature: '${mxConfig.signature}'</li>
	</ul>
	<button onclick="wxpay()" class="btn btn-default">点击支付</button>
</div>
</body>
</html>
