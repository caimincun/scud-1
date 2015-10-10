<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>

<!doctype html>
<html>
<base >
<head>
  <%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
  %>
  <base href="<%=basePath%>">
  <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
  <title>${canteenName}</title>
  <link href="css/base.css" rel="stylesheet">
  <link href="css/recharge.css" rel="stylesheet">
  <link href="css/new_top.css" rel="stylesheet">
  <script type="text/javascript" src="http://res.wx.qq.com/open/js/jweixin-1.0.0.js"></script>
  <script src="js/jquery-2.1.1.js"></script>
  <style>
    .btn_bg_color{
      background-color: #ff7b02;
      color: #ffffff;
    }
  </style>
</head>
<body>
<div class="main">
  <div class="container2">
    <div class="new_top">
      <a href="user/toBalancePage"><img src="image/back.gif" class="back_btn"></a>
      <div class="top_title"><span class="title">充值</span></div>
      <a href="user/toIndexPage"><img src="image/home.png" class="home"></a>
    </div>
    <div class="balance_info_row">
      <div class="balance_info_div">
        <span class="can_use_balance_text">可用余额:</span>
        <span class="money_mark">￥</span>
        <span class="money">${account}</span>
       <%-- <span class="yuan">元</span>--%>
      </div>
    </div>
    <div class="center_line_div">
      <%--<img src="image/line.png" class="line_img">--%>
    </div>
    <div class="please_select_div">
      <span class="select_text">请选择充值金额</span>
    </div>
    <div class="btn_row_div">
      <div class="btn_row">
        <div class="btn_col">
          <button class="money_btn" data-money="49">50</button>
        </div>
        <div class="btn_col">
          <button class="money_btn" data-money="98">100</button>
        </div>
        <div class="btn_col">
          <button class="money_btn" data-money="0.01">150</button>
        </div>
      </div>
    </div>
    <div class="btn_row_div">
      <div class="btn_row">
        <div class="btn_col">
          <button class="money_btn" data-money="195">200</button>
        </div>
        <div class="btn_col">
          <button class="money_btn" data-money="243">250</button>
        </div>
        <div class="btn_col">
          <button class="money_btn" data-money="290">300</button>
        </div>
      </div>
    </div>
    <div class="price_out_div">
      <div class="price_in_div">
        <span class="price_text">售价:</span>
        <span class="money_mark_2">￥</span>
        <span class="money_2" id="realPrice"></span>
        <%--<span class="yuan_2">元</span>--%>
      </div>
    </div>
    <button class="charge_btn" id="recharge_now">立即充值</button>
    <div id="show"></div>
    <br/>
    <div id="show2"></div>

  </div>
</div>
<div style="display: none">
  <form action="user/recharge/test" method="post" id="hiddenForm">
    <input type="hidden" name="totalFee" value="0" id="totalFee">
  </form>
</div>
</body>
<script type="text/javascript">
  $(function(){
    var hiddenFiled = 0;
    var realFee=0;
    $(".money_btn").click(function(){
      $(".money_btn").removeClass("btn_bg_color");
      $(this).addClass("btn_bg_color");
      hiddenFiled=$(this).data("money");
      realFee=$(this).html();
      $("#realPrice").html(hiddenFiled);
    });
    $("#recharge_now").click(function(){
      if(hiddenFiled!=0){
//        $("#hiddenForm").submit();
        $.post("user/recharge",{
          totalFee:hiddenFiled,
          realFee:realFee
        },function(res){
          var jsparam = res.data;
          wxpay(jsparam);
        })
      }
      else
        alert("请选择充值金额");
    });


    //支付

    <%--var jsParam=${jsParam};--%>

    <%--wx.config({--%>
      <%--debug: false,--%>
      <%--appId: '${mxConfig.appId}',--%>
      <%--timestamp: '${mxConfig.timestamp}',--%>
      <%--nonceStr: '${mxConfig.nonceStr}',--%>
      <%--signature: '${mxConfig.signature}',--%>
      <%--jsApiList: [--%>
        <%--'checkJsApi',--%>
        <%--'onMenuShareTimeline',--%>
        <%--'onMenuShareAppMessage',--%>
        <%--'onMenuShareQQ',--%>
        <%--'onMenuShareWeibo',--%>
        <%--'hideMenuItems',--%>
        <%--'showMenuItems',--%>
        <%--'hideAllNonBaseMenuItem',--%>
        <%--'showAllNonBaseMenuItem',--%>
        <%--'translateVoice',--%>
        <%--'startRecord',--%>
        <%--'stopRecord',--%>
        <%--'onRecordEnd',--%>
        <%--'playVoice',--%>
        <%--'pauseVoice',--%>
        <%--'stopVoice',--%>
        <%--'uploadVoice',--%>
        <%--'downloadVoice',--%>
        <%--'chooseImage',--%>
        <%--'previewImage',--%>
        <%--'uploadImage',--%>
        <%--'downloadImage',--%>
        <%--'getNetworkType',--%>
        <%--'openLocation',--%>
        <%--'getLocation',--%>
        <%--'hideOptionMenu',--%>
        <%--'showOptionMenu',--%>
        <%--'closeWindow',--%>
        <%--'scanQRCode',--%>
        <%--'chooseWXPay',--%>
        <%--'openProductSpecificView',--%>
        <%--'addCard',--%>
        <%--'chooseCard',--%>
        <%--'openCard'--%>
      <%--]--%>
    <%--});--%>

    function wxpay(jsParam){
      WeixinJSBridge.invoke(
              'getBrandWCPayRequest', {
                "appId" : jsParam.appId,     //公众号名称，由商户传入
                "timeStamp":jsParam.timeStamp,         //时间戳，自1970年以来的秒数
                "nonceStr" : jsParam.nonceStr, //随机串
                "package" : jsParam.package,
                "signType" : "MD5",         //微信签名方式:
                "paySign" : jsParam.paySign //微信签名
              }
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
//        alert(res.err_code+" err_desc="+res.err_desc+" err_msg="+res.err_msg);
      }
    }
    function showPayResult(){
      alert("支付成功");
      window.location="/user/toBalancePage";
    }



  })
</script>
</html>
