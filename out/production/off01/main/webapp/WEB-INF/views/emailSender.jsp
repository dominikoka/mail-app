<%@ page import="java.util.List" %><%--
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
    <form method="get" action="emailSent" class="emailSenderForm">
      <label for="to" class="emailSender_label">to:</label>
      <input type="text" class="emailSender_to" name="to">
        <label for="subject" class="emailSender_label">sbject:</label>
        <input type="text" class="emailSender_subject" name="subject">

        <textarea name="message" class="emailSender_message"></textarea>
        <label for="file">Select a file:</label>
        <input type="file" id="file" name="file" class="emailSender_file" value="CHOOSE FILE">
        <input type="submit" class="emailSender_btn" value="SEND">
    </form>
  </div>
</div></div>
  <div class="EmailSender_info">
    <div class="Info">
      <div class="Info_box"><%
        List<List<String>> sentItem = (List<List<String>>) request.getAttribute("historyList");
        if(!sentItem.isEmpty()){
        out.print("<div class=\"Info_tittle\">PREVIEW OF SENT MESSAGES FROM THE LAST DAY:</div>");}

      %>

      <div class='accorderon'>
        <ul class='accorderon_items'>
            <%

        for(int i = 0;i<sentItem.size();i++)
        {
          String subject = sentItem.get(i).get(2).length()>31?sentItem.get(i).get(2).substring(0,31):sentItem.get(i).get(2);
          out.print("<li>");
          out.print("<a href='#' class=\"acc_items_a\"><i class=\"fa fa-graduation-cap\"></i><div class=\"accordeonItem_msg\"><div class=\"accordeonItem_from\">"+sentItem.get(i).get(1)+ "</div> <div class=\"accordeonItem_title\">"+subject+"</div> <div class=\"accordeonItem_date\">"+sentItem.get(i).get(5)+"</div></div></a>");
          out.print("<ul class='sub-items'>");
          out.print("<div class=\"accordeon_msg\">");
          out.print("<div class=\"msg_msg\">");
          out.print(sentItem.get(i).get(3));
          out.print("</div>");
          out.print("<div class=\"msg_attachment\">");
          out.print(sentItem.get(i).get(4));
          out.print("</div>");
          out.print("</div>");
          out.print("</ul>");
          out.print("</li>");
        }
      %>

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


    //accordeon
    $(".acc_items_a").click(function(e) {
      e.preventDefault();
      var $this = $(this);
      if ($this.hasClass("expanded")) {
        $this.removeClass("expanded");
      } else {
        $(".accorderon_items a.expanded").removeClass("expanded");
        $this.addClass("expanded");
        $(".sub-items").filter(":visible").slideUp("normal");
      }
      $this.parent().children("ul").stop(true, true).slideToggle("normal");
    });

    $(".sub-items a").click(function() {
      $(".sub-items a").removeClass("current");
      $(this).addClass("current");
    });

    // accordeon/




  </script>
</body>
</html>