<%@ page import="java.util.List" %>
<%@ page import="java.util.Collections" %>
<%@ page import="java.util.Iterator" %>
<%@ page import="main.resources.models.CustomScreenRecorder" %>
<%@ page import="java.io.File" %><%--
  Created by IntelliJ IDEA.
  User: lenda
  Date: 09.12.2023
  Time: 19:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Title</title>
  <style><%@include file="/WEB-INF/css/style.css"%></style>
  <link href='https://unpkg.com/boxicons@2.1.4/css/boxicons.min.css' rel='stylesheet'>
  <link href='https://unpkg.com/css.gg@2.0.0/icons/css/facebook.css' rel='stylesheet'>
<%--  <script src="https://code.jquery.com/jquery-3.7.1.min.js"></script>--%>
<%--  <script>--%>
<%--    const yourServletURL = "http://localhost:8081/Recorder";--%>
<%--    $(document).on("click", "#received_video", function() { // When HTML DOM "click" event is invoked on element with ID "somebutton", execute the following function...--%>
<%--      $.get(yourServletURL, function(responseText) {--%>

<%--        $("#somediv").text(responseText);           // Locate HTML DOM element with ID "somediv" and set its text content with the response text.--%>
<%--      });--%>
<%--    });--%>
<%--  </script>--%>

<%--  t u t e j  chatgp--%>
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
    <div class="received_box">


      <div class="received_btn">
        <div class="received_btnInfo">
          <div class="received_page"> Page: <% Integer pageShow = (Integer) request.getAttribute("page");
          if(pageShow==null){pageShow=0;}
          pageShow=pageShow+1;
          out.print(pageShow);
          %></div>
          <div class="received_howmuch">
            set number of messages:
            <%
              Integer howMuch = (Integer) request.getAttribute("howMuch");
              if(howMuch==null){howMuch=15;}
              out.print(howMuch);
            %>

          </div>

        </div>
      <div class="received_btnText">number of messages on the page:</div>

        <div class="received_howMuch">
          <div class="received_btn20"><a href="http://localhost:8081/receivedMessageGET?count=15" class="received_btna">15</a> </div>
          <div class="received_btn20"><a href="http://localhost:8081/receivedMessageGET?count=30" class="received_btna">30</a> </div>
        </div>
        <div class="received_btnNavigation">
          <div class="received_btnBack"><a href="http://localhost:8081/receivedMessageGET?direction=-1" class="received_btna"> BACK</a> </div>
          <div class="received_btnNext"><a href="http://localhost:8081/receivedMessageGET?direction=1" class="received_btna">NEXT</a> </div>
        </div>
      </div>
      <div class="received_menu">
        <div class="navigation">
          <div class="navigation_box">
            <div class="navigation_link">
              <a href="http://localhost:8081/emailSender "class="navigation_a"> new message</a>
              <i class='bx bx-message-add navigation_iconOne' ></i>
            </div>
            <div class="navigation_link">
              <a href="http://localhost:8081/receivedMessageGET" class="navigation_a"> received message (<%
                List<List<String>> emails2 = (List<List<String>>) request.getAttribute("styles");
                out.print(emails2.get(emails2.size()-1).get(0));
              %>)</a>
              <i class='bx bx-message-square-detail navigation_iconTwo'></i>
            </div>
            <div class="navigation_link">
              <a href="http://localhost:8081/activity"class="navigation_a"> your activity</a>
              <i class='bx bxs-bar-chart-alt-2'></i>
            </div>
            <div class="navigation_link">
              <a href="http://localhost:8081/items"class="navigation_a"> items</a>
              <i class='bx bxs-bar-chart-alt-2'></i>
            </div>
          </div>
        </div>


      </div>


      <div class="received_emails" >
        <form action="deleteMessage" method="get">
      <%
        List<List<String>> emails = (List<List<String>>) request.getAttribute("styles");
        String time = emails.get(0).get(1);
        for(int i=0;i<emails.size();i++)
        {
          if(time!=emails.get(i).get(1) || i==0)
          {out.print("<div class=\"email_time\">"+emails.get(i).get(1)+"</div>");
            time = emails.get(i).get(1);
          }
          out.print("<div class=\"received_email email\">  " );
            //out.print("<a href="http://localhost:8081/emailSent"");
            String adress = "http://localhost:8081/viewMessage";
            String fullPath = adress+"?param=";
            fullPath = fullPath+emails.get(i).get(0);
            System.out.println(fullPath);

            String checboxValues = request.getParameter("email_delete");
            System.out.println(checboxValues);


            out.print("<a href = "+fullPath+" class=\"email_ahref\">");
                String email_nb = emails.get(i).get(0);
            out.println("<input type=\"hidden\" name=\"nbOfMail\" value=\""  + email_nb + "\">");
                out.print("<div class=\"email_nb\">"+emails.get(i).get(0)+"</div>");
                if(emails.get(i).get(6) == "false")
                {
                    out.print("<div class=\"email_from email_from-bold\">"+emails.get(i).get(4)+"</div>");
                  out.print("<div class=\"email_textBold email_text\" \"email_text\">"+emails.get(i).get(3)+"</div>");
                }
                else
                {
                    out.print("<div class=\"email_from\">"+emails.get(i).get(4)+"</div>");
                  out.print("<div class=\"email_text\">"+emails.get(i).get(3)+"</div>");
                }
            out.print("<div class=\"email_date\">"+emails.get(i).get(2)+"</div>");
            out.print("<input type=\"checkbox\" id=\"email_delete\" name=\"email_delete\" value=\""  + email_nb + "\">");
//            out.print(">");
            out.print("</a>");
            out.print("</div>");
        }
        String s[] = request.getParameterValues("email_delete");
        if (s != null && s.length != 0) {
          out.println("You have selected the option is: ");
          for (int i = 0; i < s.length; i++) {
            out.println(s[i] + "\n" + "Thank you");
          }
        }
      %>
          <input type="submit" value="Delete" class="received_submit">
        </form>
      </div>
    </div>
  <div class="received_logout">
    <div class="logoutBtn">
      <a href="http://localhost:8081/logout" class="logoutBtn_a">LOGOUT</a>
    </div>
    <div class="received_logout-screenshot">
<%--      <a href="http://localhost:8081/screenshot" class="logoutBtn_a">DO A SCREENSHOT</a>--%>
<%--      received_screenshot--%>
      <button id="received_screenshot" class="received_screenshot">SCREENSHOOT</button>
<%--      <input type="file" id="logoutBtn_screenshot" name="logoutBtn_screenshot" class="logoutBtn_screenshot">--%>
    </div>
    <div class="received_video">
      <button onclick="Play()" id="received_video" class="received_videoBtn"></button>
    </div>
    <div class="received_setting">
      <button onclick="visible()" id="setting_textScreenshot" class="setting_textScreenshot">SETTINGS</button>
      <div class="setting_btn" id="setting_btn">
        <div class="setting_screenshoot">
          <div class="setting_textScreenshotBox">save picture</div>
          <input type="button" id="setting_screenshot" name="setting_videoSave" class="setting_videoSave" value="Change direction">
          <div class="setting_screenshootPath" id="setting_screenshootPath"></div>
          <div id="resultContainer"></div>
        </div>
        <div class="setting_video">
          <div class="setting_textVideo">save video</div>
          <input type="button" id="setting_videoSave" name="setting_videoSave" class="setting_videoSave" value="Change direction">
          <div class="setting_videoPath" id="setting_videoPath"></div>
        </div>
      </div>
    </div>
<%--      <input id="logoutBtn_videoBtn" type="file" webkitdirectory = "true" directory  name="logoutBtn_videoBtn"/>--%>
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

  <%
    out.print("<div class=\"footer\" style=\"position:relative;\">");
  %>


  <div class="footer_box">
    <div class="footer_title">Code Opacity</div>
    <div class="footer_text">Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.</div>
    <div class="footer_icons">
      <i class="gg-facebook"></i>
      <i class="gg-facebook"></i>
      <i class="gg-facebook"></i>
    </div>
    <div class="footer_copyright">Copyritgh</div>
  </div></div>
  <script src="https://code.jquery.com/jquery-3.7.1.min.js" ></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.30.1/moment.min.js" ></script>
  <%
    File f = new File("C:/folderek/");
    CustomScreenRecorder record = new CustomScreenRecorder(f);
    Integer switchJava= 0;
  %>
  <script>
    $(document).ready(function () {
      var interval = setInterval(function (){
      var momentNow = moment();
      $('#received_date').html(momentNow.format('dddd').substring(0, 3).toUpperCase() + ', ' + momentNow.format('DD MMMM YYYY'));
      $('#received_time').html(momentNow.format('hh:mm:ss A'));
    },100);

    });
    //setTimeout("location.reload()",10000*12);

    var butn = document.getElementById("received_video");
    var switchNb = 0;


    function Play()
    {
      if(switchNb === 0)
      {
        butn.style.backgroundImage= "url(https://static.thenounproject.com/png/425109-200.png)";
        switchNb = 1;

        <%
          record.startRecording("movies",true);
          System.out.println("0");
        %>
      }
      else
      {
        butn.style.backgroundImage= "url(https://media.istockphoto.com/id/1192173219/pl/wektor/przycisk-odtwarzania.jpg?s=612x612&w=0&k=20&c=AR6qp9FbZGrQseFCjcB3F-Xcz3_qrHG-66K8DQ6TAhI=)"
        switchNb = 0;
        <%
        record.stopRecording(true);
        System.out.println("1");
        %>
      }
    }


  </script>
  <script>
    const box = document.getElementsByClassName('setting_btn')[0];
    //var box =document.getElementById("setting_btn");
    var switchNb1 = 0;
    function visible(){
      if(switchNb1 == 0)
      {
        box.style.visibility = 'visible';
        box.style.opacity = "1";
        box.style.position = "relative";
        switchNb1 =1;}
      else
      {
        box.style.visibility = 'hidden';
        box.style.opacity = "0";
        box.style.position = "absolute";
        switchNb1 = 0;}
    }
  </script>

  <script>
    $(document).ready(function () {
      $("#received_video").click(function () {
        var parameterValue = $("#logoutBtn_videoBtn").val();
        $.ajax({
          type: "POST",
          url: "http://localhost:8081/Recorder",
          data: { parameterName: parameterValue },
          success: function (result) {
          },
          error: function (error) {
            console.error("Ajax request failed: " + error);
          }
        });
      });


      $("#setting_screenshot").click(function () {
        // Get data or parameters
        var parameterValue3 ="load";// Replace with your actual data
        $.ajax({
          type: "POST",
          url: "http://localhost:8081/screenshot",
          data: { loader: parameterValue3 },
          success: function (result) {
            // Handle the result
            $("#resultContainer").html(result);
          },
          error: function (error) {
            console.error("Ajax request failed: " + error);
          }
        });
      });
      $("#received_screenshot").click(function () {
        // Get data or parameters
        var parameterValue2 = "load";  // Replace with your actual data

        //Make Ajax call
        $.ajax({
          type: "POST",
          url: "http://localhost:8081/screenshot",
          data: { parameterValue2: parameterValue2 },
          success: function (result) {
            // Handle the result
            //$("#resultContainer").html(result);
          },
          error: function (error) {
            console.error("Ajax request failed: " + error);
          }
        });
      });
      $("#setting_videoSave").click(function () {
        window.console&&console.log('foo');
        var parameterValue2 ="pathLoad";
        $.ajax({
          type: "POST",
          url: "http://localhost:8081/Recorder",
          data: { loader: parameterValue2 },
          success: function (result) {
            // Handle the result
            $("#setting_videoPath").html(result);
          },
          error: function (error) {
            console.error("Ajax request failed: " + error);
          }
        });
      });
    });

  </script>

  <script>
    const boxChatbot = document.querySelector(".Chatbot");
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
      boxChatbot.style.display="none";
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
