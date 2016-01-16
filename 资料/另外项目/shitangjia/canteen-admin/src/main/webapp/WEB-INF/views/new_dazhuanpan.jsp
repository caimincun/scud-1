<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2015/6/11
  Time: 15:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
    <%
        String path = request.getContextPath();
        String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
    %>
    <base href="<%=basePath%>">
    <title>大转盘</title>
    <link href="/css/bootstrap.min.css" rel="stylesheet">
    <link href="/css/base.css" rel="stylesheet">
    <link href="/css/top.css" rel="stylesheet">
    <link href="/css/zhuanpan.css" rel="stylesheet">
    <script src="/js/jquery-2.1.1.js"></script>
    <script src="/js/jQueryRotate.2.2.js"></script>
    <script src="/js/jquery.easing.min.js"></script>
    <script src="/js/bootstrap.min.js"></script>
</head>
<body >
<div class="z_main">
    <div class ="pan_div">
        <div class="pan_out">
            <img id="lotteryBtn" src="img/rotate-static.png" class="pointer"/>
        </div>
    </div>
    <div class="info_div">
        <div class="info_row"><span class="set_text">奖品设置:</span></div>
        <div class="info_row"><span class="set_text_2">福临门 天天五谷食用调和油 900ml</span></div>
        <div class="info_row"><span class="set_text_2">金龙鱼松原小米400g</span></div>
        <div class="info_row"><span class="set_text_2">心相印抽纸 茶语系列2层200抽盒装面巾纸</span></div>
    </div>
    <div class="modal fade padding-top" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" style="display: none">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header " style="margin-top: 0rem;height: 0.45rem">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <div style="width: 100%;text-align: center"><h4 class="modal-title" id="myModalLabel"></h4></div>
                </div>
                <div class="modal-body" style="padding-top: 0rem ">
                    <%--<div class="pwd_input_div"><input type="tel" placeholder="请输入手机号" class="pwd_input" id="phone">
                    </div>
                    <div class="error_result" id="error_result">
                        <div class="error_message" id="error_message" ></div>
                    </div>--%>
                    <div style="font-size: 0.14rem">您本次中奖的的兑奖码是：<span id="awardCode" style="color: #ffab28"></span><br>请直接出示该页面(或者此页面截图)给公作人员领取奖品</div>
                      <div style="margin-top: 0.1rem">  稍后我们也会将兑奖码以短信形式发送至您的手机,您也可以凭短信领取奖品！</div>
                    </pre>
                </div>
              <%--  <div class="modal-footer alert_footer">
                    <button type="button" class="btn submit_btn"  id="sub_btn">关闭</button>
                </div>--%>
            </div>
        </div>
    </div>

</div>
</body>
<script>
    $(function () {
        var data = 1;
        var isCanjia = 0;
//        var award = 0;
        var awardCode=1232;
        $("#sub_btn").click(function () {
            $("#myModal").modal('hide');
        });
        $.post("awards/choujiang", {}, function (res) {
            var result = res.result;
            if (result == 0) {
                data = res.award;
                awardCode = res.awardCode;
            } else if (result == 2) {
                alert("请先参加问卷调查!");
                window.location.href = "questionuser/toQuestionIndexPage";
            } else if (result == 4) {
                alert(res.msg);
            }
        });

        var rotateFunc = function (awards, angle, text) {  //awards:奖项，angle:奖项对应的角度
//            award = awards;
            $('#lotteryBtn').stopRotate();
            $("#lotteryBtn").rotate({
                angle: 0,
                duration: 5000,
                animateTo: angle + 1800, //angle是图片上各奖项对应的角度，1440是我要让指针旋转4圈。所以最后的结束的角度就是这样子^^
                callback: function () {
                    isCanjia = 1;
                    if(awards==0){
                        alert(text);
                    }else {
                        $("#myModalLabel").html(text);
                        $("#awardCode").html(awardCode);
                        $("#myModal").modal('show');
                        $.post("awards/confirm", {
                            "award":data,
                            "awardCode":awardCode
                        }, function (res) {
                            var result = res.result;
                            if (result == 0) {
//                                //请确认查收短信
//                                alert("已经发送验证码到您的手机！请注意查收短信!");
//                                $("#myModal").modal('hide');
                            } else if (result == 2) {
                                alert("请先参加问卷调查!");
                                window.location.href = "questionuser/toQuestionIndexPage";
                            } else if (result == 4) {
                                alert(res.msg);
                                $("#myModal").modal('hide');
                            }
                        });
                    }

                }
            });
        };
        $("#lotteryBtn").rotate({
            bind: {
                click: function () {
                    /*var time = [0,1];
                     time = time[Math.floor(Math.random()*time.length)];
                     if(time==0){
                     timeOut(); //网络超时
                     }*/
                    if (isCanjia == 1) {
                        $(this).stopRotate();
                        if(data==0) {
                            alert("谢谢!您已经参加过抽奖");
                        }else{
                            $("#myModal").modal('show');
                        }
                    }else {
                        switch(data){
                            case -1:
                                $(this).stopRotate();
                                break;
                            case 1:
                                rotateFunc(1, 157, '恭喜您抽中五谷食用调和油一瓶');
                                break;
                            case 2:
                                rotateFunc(2, 22, '恭喜您抽中金龙鱼松原小米一袋')
                                break;
                            case 3:
                                rotateFunc(3, 247, '恭喜您抽中心心相印纸巾一提')
                                break;
                            case 0:
                                var angle = [67, 112, 202, 292, 337];
                                angle = angle[Math.floor(Math.random() * angle.length)];
                                rotateFunc(0, angle, '很遗憾，这次您未抽中奖');
                                break;
                        }

                    }
                    // }
                }
            }

        });

    })
</script>
</html>
