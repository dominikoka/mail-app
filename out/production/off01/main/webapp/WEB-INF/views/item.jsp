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

</head>
<body>
<%@include file="_header.jsp" %>

  <div class="itemClass">
    <div class="itemClass_box">
      <div class="itemClass_menu">
        <%@include file="_navigation.jsp" %>
      </div>

      <div class="singleItem">
      <%
        Integer price = (Integer) request.getAttribute("price");
        String nameItem = (String) request.getAttribute("name_item");
        String description = (String) request.getAttribute("description");
        String quantity = (String) request.getAttribute("qua");
        String numberOfItem = (String) request.getAttribute("numberOfItem");

        out.print("<div class=\"singleItem_nameItem\">" + nameItem + "</div>");
        out.print("<div class=\"singleItem_description\">" + description + "</div>");
        out.print("<div class=\"singleItem_quantity\">" + quantity + "</div>");
        out.print("<div class=\"singleItem_price\">" + price + "</div>");
        out.print("<div class=\"item_images\">");
        out.print("<div class=\"images_imageBox\">");
        out.print("<img src=\"/images/item"+numberOfItem+"0.jpg\" class=\"item_image\" />");
        out.print("</div>");
        out.print("<div class=\"images_imageBox\">");
        out.print("<img src=\"/images/item"+numberOfItem+"1.jpg\" class=\"item_image\" />");
        out.print("</div>");
        out.print("<div class=\"images_imageBox\">");
        out.print("<img src=\"/images/item"+numberOfItem+"2.jpg\" class=\"item_image\" />");
        out.print("</div>");
        out.print("<div class=\"images_imageBox\">");
        out.print("<img src=\"/images/item"+numberOfItem+"3.jpg\" class=\"item_image\" />");
        out.print("</div>");
        out.print("<div class=\"images_imageBox\">");
        out.print("<img src=\"/images/item"+numberOfItem+"4.jpg\" class=\"item_image\" />");
        out.print("</div>");
        out.print("</div>");



        String adress = "http://localhost:8081/buy";
        String fullPath = adress+"?param=";
        fullPath = fullPath+numberOfItem;
        out.print("<a href=\""+fullPath+"\" class=\"singleItem_btn\">");
        out.print("BUY");
        out.print("</a>");
        out.print("</div>");
      %>
      </div>
      <button></button>
<%--      <img src="/images/background.jpg" />--%>
    </div>
  </div>
<%@include file="_footer.jsp"%>
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
</body>
</html>