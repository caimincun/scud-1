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
  <%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
  %>
  <base href="<%=basePath%>">
    <title>大转盘</title>


</head>
<body>
<div class="ly-plate">
  <div class="rotate-bg"></div>
  <div class="lottery-star"><img src="img/rotate-static.png" id="lotteryBtn"></div>
</div>
</body>
<script src="js/jquery-2.1.1.js"></script>
<script src="js/jQueryRotate.2.2.js"></script>
<script src="js/jquery.easing.min.js"></script>
<script>
  $(function(){
    var data = -1;
    var isCanjia = 0 ;
    $.post("/awards/choujiang",{},function(res){
      var result = res.result;
      if(result==0) {
        data = res.award;
      }else if(result ==2){
          window.location.href = "questionuser/toQuestionIndexPage";
      }else if(result ==4){
        alert("谢谢!您已经参加过抽奖!");
      }
    });
//    var timeOut = function(){  //超时函数
//      $("#lotteryBtn").rotate({
//        angle:0,
//        duration: 10000,
//        animateTo: 2160, //这里是设置请求超时后返回的角度，所以应该还是回到最原始的位置，2160是因为我要让它转6圈，就是360*6得来的
//        callback:function(){
//          alert('网络超时')
//        }
//      });
//    };
    var rotateFunc = function(awards,angle,text){  //awards:奖项，angle:奖项对应的角度
      $('#lotteryBtn').stopRotate();
      $("#lotteryBtn").rotate({
        angle:0,
        duration: 5000,
        animateTo: angle+1440, //angle是图片上各奖项对应的角度，1440是我要让指针旋转4圈。所以最后的结束的角度就是这样子^^
        callback:function(){
          alert(text);
          isCanjia =1;
          $.post("/awards/confirm",{
//            "phone":
          },function(res){
            var result = res.result;
            if(result==0) {
              //请确认查收短信
            }else if(result ==2){
              window.location.href = "questionuser/toQuestionIndexPage";
            }else if(result ==4){
              alert("谢谢!您确认了抽奖信息!");
            }
          });

        }
      });
    };
    $("#lotteryBtn").rotate({
      bind:
      {
        click: function(){

          /*var time = [0,1];
           time = time[Math.floor(Math.random()*time.length)];
           if(time==0){
           timeOut(); //网络超时
           }*/
          // if(time==1){
          // var data = [1,2,3,0]; //返回的数组
          // 	data = data[Math.floor(Math.random()*data.length)];
          if(data==-1){
           $(this).stopRotate();
          }
          if(isCanjia==1){
            $(this).stopRotate();
            alert("谢谢!您已经参加过抽奖");
          }
          if(data==1){
            rotateFunc(1,157,'恭喜您抽中油一瓶')
          }
          if(data==2){
            rotateFunc(2,247,'恭喜您抽中米一袋')
          }
          if(data==3){
            rotateFunc(3,22,'恭喜您抽中纸一袋')
          }
          if(data==0){
            var angle = [67,112,202,292,337];
            angle = angle[Math.floor(Math.random()*angle.length)]
            rotateFunc(0,angle,'很遗憾，这次您未抽中奖')
          }
          // }
        }
      }

    });

  })
</script>
</html>