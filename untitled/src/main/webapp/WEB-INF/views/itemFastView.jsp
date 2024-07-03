<%@ page import="java.util.List" %>
<style><%@include file="/WEB-INF/css/style.css"%></style>
<div class="itemFastView">
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
%>

<button class="itemFastView_btn">X</button>
</div>
<script>

  $(".itemFastView_btn").click(function () {
    const div = document.querySelector(".itemFastView");
    div.innerHTML="";
    $('.emailSender,.header,.footer').css('filter', 'blur(0px)');
  })
</script>