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
<%@include file="_header.jsp" %>
<div class="items">
    <div class="itemClass_box">
      <div class="items_menu">
        <%@include file="_navigation.jsp" %>
      </div>
      <div class="itemsContent">
        <div class="itemClass_box">
          <div class="itemsContent_title"> you wanna buy something ?</div>
          <div class="itemsContent_item">

            <%
              List<Integer> items_id = (List<Integer>) request.getAttribute("item_id");
              List<String> items_name = (List<String>) request.getAttribute("name_item");
              List<String> items_desc = (List<String>) request.getAttribute("desc");
              List<Integer> items_qua = (List<Integer>) request.getAttribute("qua");
              List<Integer> items_price = (List<Integer>) request.getAttribute("price");
              for(int i=0;i<items_id.size();i++)
              {
                String adress = "http://localhost:8081/item";
                String fullPath = adress+"?param=";
                fullPath = fullPath+i;
                out.print("<div class=\"item_product\">");
                out.print("<a href=\""+fullPath+"\" class=\"item_Link\">");
                out.print("<img src=\"/images/item"+i+"0.jpg\" class=\"item_img\" />");
                //<img src=\"/images/item"+i+"0.jpg\" class=\"item_img\" />
                out.print("<div class=\"item_name\">"+ items_name.get(i)+"</div>");
                out.print("<div class=\"item_quantity\">"+ "quantity: "+items_qua.get(i) +"</div>");
                String descShort = items_desc.get(i).toString().length()>30?items_desc.get(i).toString().substring(0,30):items_desc.toString();
//                    out.print("<div class=\"item_description\">"+ descShort+"</div>");
                out.print("<div class=\"item_price\">"+ "price: "+items_price.get(i)+"</div>");
                out.print("</a>");
                out.print("<button id=\""+i+"\" value =\""+i+"\" class=\"item_btnBacket\">Click Me!</button>");
                out.print("</div>");
//                out.print("<button onclick=\"visible()\" id=\"setting_textScreenshot\" class=\"setting_textScreenshot\">SETTINGS</button>");
              }
            %>


          </div>
        </div>
      </div>
    </div>
</div>
<div class="fastView"></div>
<%@include file="_footer.jsp" %>
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
    // var el;
    // function showMore(elem)
    // {
    //   el = elem.id;
    // }

    $(".item_btnBacket").click(function showMore(elem) {
      // Get data or parameters
      var parameterValue3 ="load";// Replace with your actual data
      var thebuttonclicked =$(this).attr("value");
      var ajaxF = true;
      console.log(thebuttonclicked);
      var adress = "http://localhost:8081/item?param=" + thebuttonclicked;
      $.ajax({
        type: "GET",
        url: adress,
        data: { loader: thebuttonclicked,
                ajaxF: ajaxF},
        success: function (result) {
          $(".fastView").html(result);
          $('.emailSender,.header,.footer').css('filter', 'blur(5px)');
        },
        error: function (error) {
          console.error("Ajax request failed: " + error);
        }
      });
    });

  </script>
</body>
</html>