<%--
  Created by IntelliJ IDEA.
  User: ${USER}
  Date: ${DATE}
  Time: ${TIME}
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>#[[$Title$]]#</title>
  <style><%@include file="/WEB-INF/css/style.css"%></style>
  <link href='https://unpkg.com/boxicons@2.1.4/css/boxicons.min.css' rel='stylesheet'>
  <link href='https://unpkg.com/css.gg@2.0.0/icons/css/facebook.css' rel='stylesheet'>
  <script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>

</head>
<body>
<div class="received">
  <div class="header">
    <div class="header_box">
      <div class="received_name">
        <%
          String name = (String) request.getAttribute("name");
          out.print(name);
        %>
      </div>
      <div class="header_title">Magic Mail - Get Better Email</div>

      <div class="received_actuallDate">
        <div id="received_date" class="received_date"></div>
        <div id="received_time" class="received_time"></div></div>
    </div>
  </div>

  <div class="emailSender">
    <div class="itemClass_box">
      <div class="emailSender_menu">
        <div class="navigation">
          <div class="navigation_box">
            <div class="navigation_link">
              <a href="http://localhost:8081/emailSender "class="navigation_a"> new message</a>
              <i class='bx bx-message-add navigation_iconOne' ></i>
            </div>
            <div class="navigation_link">
              <a href="http://localhost:8081/receivedMessageGET" class="navigation_a"> received message</a>
              <i class='bx bx-message-square-detail navigation_iconTwo'></i>
            </div>
            <div class="navigation_link">
              <a href="http://localhost:8081/activity"class="navigation_a"> your activity</a>
              <i class='bx bxs-bar-chart-alt-2'></i>
            </div>
          </div>
        </div>
      </div>
      <div class="buy_content">
        <div class="emailSent_contentBox">
<%--          <img src="https://i.pinimg.com/originals/af/8d/9a/af8d9ac62dd43026957e1cf82a027637.gif" alt="email sent image">--%>
        </div>
      </div>


      <div class="payment">

        <form action='https://test.payu.in/_payment' method='post'>
          <input type="hidden" name="key" value="JP***g" />
          <input type="hidden" name="txnid" value="t6svtqtjRdl4ws" />
          <input type="hidden" name="productinfo" value="iPhone" />
          <input type="hidden" name="amount" value="10" />
          <input type="hidden" name="email" value="test@gmail.com" />
          <input type="hidden" name="firstname" value="Ashish" />
          <input type="hidden" name="lastname" value="Kumar" />
          <input type="hidden" name="surl" value="https://apiplayground-response.herokuapp.com/" />
          <input type="hidden" name="furl" value="https://apiplayground-response.herokuapp.com/" />
          <input type="hidden" name="phone" value="9988776655” />
<input type="hidden" name="hash" value="eabec285da28fd0e3054d41a4d24fe9f7599c9d0b66646f7a9984303fd6124044b6206daf831e9a8bda28a6200d318293a13d6c193109b60bd4b4f8b09c90972" />
          <input type="submit" value="submit"> </form>

      </div>>
    </div>

  </div>
  <div class="Chatbot" >
    <%--        onclick="switchWindow()"--%>
    <div class="Chatbot_Box">
      <div class="Chatbot_CloseBtnBox">
        <button class="Chatbot_CloseBtn" onclick="closeChatbot()">X</button>
      </div>
      <div class="Chatbot_robot" onclick="changeSpeakingBox()">
        <div class="Chatbot_WelcomeText">
          <%--              <i class="light-switch icon-lightbulb"></i>--%>
          <div class="Chatbot_text"> > CLICK TO SHOW MORE</div>
        </div>
        <img src="https://mir-s3-cdn-cf.behance.net/project_modules/fs/200e8d139737079.6234b0487404d.gif" class="Chatbot_img">
      </div>
      <div class="Chatbot_speakingBox">
        <div class="speakingBox_top" onclick="changeRobot()">
          <img src="https://mir-s3-cdn-cf.behance.net/project_modules/fs/200e8d139737079.6234b0487404d.gif" class="speakingBox_img">
          <div class="speakingBox_dot"></div>
          <div class="speakingBox_text">
            <div class="speakingBox_title"> Support Bot</div>
            <div class="speakingBox_stan">Online</div>
          </div>
        </div>
        <%--            <div class="Chatbot_"--%>
        <div class="speakingBox_chat" id="speakingBox_chat">
          write if u need help
        </div>
        <div class="speakingBox_write">
          <textarea class="speakingBox_textarea" id="textareaID"></textarea>
          <button class="speakingBox_sendbtn" id="speakingBox_sendbtn" ></button>
        </div>
      </div>
    </div>
  </div>
  <div class="footer">
    <div class="footer_box">
      <div class="footer_title">Code Opacity</div>
      <div class="footer_text">Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.</div>
      <div class="footer_icons">
        <i class="gg-facebook"></i>
        <i class="gg-facebook"></i>
        <i class="gg-facebook"></i>
      </div>
      <div class="footer_copyright">Copyritgh</div>
    </div>
  </div>
  <script src="https://code.jquery.com/jquery-3.7.1.min.js" ></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.30.1/moment.min.js" ></script>
  <script>
    $(document).ready(function () {
      var interval = setInterval(function (){
        var momentNow = moment();
        $('#received_date').html(momentNow.format('dddd').substring(0, 3).toUpperCase() + ', ' + momentNow.format('DD MMMM YYYY'));
        $('#received_time').html(momentNow.format('hh:mm:ss A'));
      },100);

    });
  </script>
  <script>
    const box = document.querySelector(".Chatbot");
    const robot = document.querySelector(".Chatbot_robot");
    const speakingBox = document.querySelector(".Chatbot_speakingBox");
    var textAreaSendMsg = document.querySelector(".speakingBox_textarea").value;
    const textAreaSendMsgField = document.querySelector(".speakingBox_textarea");
    const msgsendBtn = document.querySelector(".speakingBox_sendbtn");

    function changeSpeakingBox()
    {
      speakingBox.style.display = 'block';
      robot.style.display = 'none';
    }
    function changeRobot()
    {
      speakingBox.style.display = 'none';
      robot.style.display = 'flex';
    }

    function closeChatbot()
    {
      box.style.display="none";
    }
    textAreaSendMsgField.addEventListener("keydown", function(event) {
      if(event.keyCode === 13)
      {
        msgsendBtn.click();

        event.preventDefault();

      }
    });
  </script>
  <script>
    $(document).ready(function () {
      $("#speakingBox_sendbtn").click(function () {
        //var parameterValue = $("#logoutBtn_videoBtn").val();
        var textAreaSendMsg = document.querySelector(".speakingBox_textarea").value;
        console.log(textAreaSendMsg);
        document.querySelector(".speakingBox_textarea").value = "";
        $.ajax({
          type: "POST",
          url: "http://localhost:8081/chatbot",
          data: { msg: textAreaSendMsg },
          success: function (result) {
            //parzyste - uzytkownik
            //nieparzyste - kąkuter
            var listOfHtmlMsg="";
            var text = "\"http://example.com\"";
            //var text = "<div class="+"\"speakingBox_robot\"+">"";
            var array = result.split(",s.");
            for(let i=0;i<array.length;i++)
            {
              if(i%2===0)
              {
                listOfHtmlMsg+="<div class="+"\"speakingBox_user\""+">";
                listOfHtmlMsg+=array[i];
                listOfHtmlMsg+="</div>";
              }
              else
              {
                listOfHtmlMsg+="<div class="+"\"speakingBox_robot\""+">";
                listOfHtmlMsg+="<div class="+"\"speakingBox_robotText\""+">";
                listOfHtmlMsg+=array[i];
                listOfHtmlMsg+="</div>";
                listOfHtmlMsg+="<div class="+"\"speakingBox_robotImage\""+">";
                listOfHtmlMsg +="<img src="+"\"https://mir-s3-cdn-cf.behance.net/project_modules/fs/200e8d139737079.6234b0487404d.gif\""+
                             "class="+"\"speakingBox_robotImg\""+"/>";
                listOfHtmlMsg += "</div>";
                listOfHtmlMsg += "</div>";
              }
            }

            $("#speakingBox_chat").html(listOfHtmlMsg);
            var element = document.getElementById("speakingBox_chat");
            element.scrollTop = element.scrollHeight;
          },
          error: function (error) {
            console.error("Ajax request failed: " + error);
          }
        });
      }); });
  </script>
</body>
</html>