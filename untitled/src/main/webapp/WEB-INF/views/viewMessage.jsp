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
  <script src="https://code.jquery.com/jquery-3.7.1.min.js"></script>

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

      <%

        out.print("<div class=\"emailSender_box\" style=\"min-height: calc(100vh - 367px);\">");
      %>
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
      <div class="received_mail">
        <div class="received_box">
<%--          <div class="received_title">title</div>--%>
<%--          <div class="received_btn"> btns</div>--%>
          <div class="received_emails" >
<%--            <a href="http://localhost:8081/emailSent">--%>
<%--              <div>--%>
<%--                tutaj cos do zrobienia eeee--%>
<%--              </div>--%>
<%--            </a>--%>
<%--            <a href="http://localhost:8081/emailSent">--%>
<%--              <div>--%>
<%--                tutaj cos do zrobienia drugiego--%>
<%--              </div>--%>
<%--            </a>--%>

            <%
              String s = request.getAttribute("styles").toString();
              System.out.println( "z jsp");
              //out.print(s.length());

                out.print(pageContext.findAttribute("styles"));

              var attachment = request.getAttribute("attachment");
              if(attachment!="")
              {
                out.print(attachment);
              }

            %>

          </div>
        </div>
        <div class="received_logout">logout</div>
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
    </div>
  </div>
</body>
</html>