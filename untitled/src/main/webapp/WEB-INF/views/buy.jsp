<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>#[[$Title$]]#</title>
  <style>
    <%@include file="/WEB-INF/css/style.css" %>
  </style>
  <link href='https://unpkg.com/boxicons@2.1.4/css/boxicons.min.css' rel='stylesheet'>
  <link href='https://unpkg.com/css.gg@2.0.0/icons/css/facebook.css' rel='stylesheet'>

</head>
<body>

<%@include file="_header.jsp" %>

<div class="buy">
  <div class="itemClass_box">
    <div class="buy_menu">
      <%@include file="_navigation.jsp" %>
    </div>
    <div class="buy_content">

      <div class="jsonresult"></div>

      <%--        <%--%>
      <%--          var price = request.getAttribute("price");--%>
      <%--          var nameItem = request.getAttribute("nameItem");--%>
      <%--          out.print("<div class=\"buyWindow_price\">");--%>
      <%--          out.print(price);--%>
      <%--          out.print("</div>");--%>
      <%--          out.print("<div class=\"buyWindow_nameItem\">");--%>
      <%--          out.print(nameItem);--%>
      <%--          out.print("</div>");--%>
      <%--        %>--%>
      <div class="buy_form">
        <div class="buy_formBox">
          <div class="buy_formTitle">item</div>
          <div class="buy_item">
            <div class="buy_itemText">name</div>
            <input type="text" class="buy_itemInput buy_itemName" value="<%
               var item = request.getAttribute("nameItem");
               out.print(item);
               %>" disabled>
          </div>
          <div class="buy_item">
            <div class="buy_itemText">quantity</div>
            <input type="text" class="buy_itemInput buy_itemQuaintity" value="<%
               var quantity = 1;
               out.print(quantity);
               %>" disabled>
          </div>
        </div>
        <div class="buy_formBox">
          <div class="buy_formTitle">user</div>

          <div class="buy_item">
            <div class="buy_itemText">email</div>
            <input type="text" class="buy_itemInput buy_userEmail" value="<%
               var uname = request.getAttribute("uname");
               out.print(uname);
               %>" disabled>
          </div>
          <div class="buy_item">
            <div class="buy_itemText ">phone</div>
            <input type="tel" class="buy_itemInput buy_userPhone">
          </div>
          <div class="buy_item">
            <div class="buy_itemText">Name</div>
            <input type="text" class="buy_itemInput buy_userName">
          </div>
          <div class="buy_item">
            <div class="buy_itemText">Surname</div>
            <input type="text" class="buy_itemInput buy_userSurname">
          </div>
        </div>

        <div class="buy_formBox">
          <div class="buy_formTitle">adress</div>
          <div class="buy_item">
            <div class="buy_itemText">city</div>
            <input type="text" class="buy_itemInput buy_adressCity">
          </div>
          <div class="buy_item">
            <div class="buy_itemText ">number house</div>
            <input type="text" class="buy_itemInput buy_adressHouse">
          </div>
          <div class="buy_item">
            <div class="buy_itemText">zip code</div>
            <input type="text" class="buy_itemInput buy_adressZip" inputmode="numeric">
          </div>
        </div>
        <div class="buy_formBox">
          <div class="buy_formTitle">Price</div>
          <div class="buy_item">
            <div class="buy_itemText">discount Code</div>
            <input type="text" class="buy_itemInput buy_discountCode">
            <button class="buy_discountBtn">Confirm</button>
          </div>
          <div class="buy_item">
            <div class="buy_itemText">SMS Code</div>
            <input type="text" class="buy_itemInput buy_smsCode">
            <button class="buy_smsBtn">Generate</button>
          </div>
          <div class="buy_item">
            <div class="buy_itemText">total Price</div>
            <div class="buy_itemPrice"><%
              var price = request.getAttribute("price");
              out.print(price);
            %></div>
          </div>
        </div>
        <div class="buy_formInfo"></div>
        <button class="buy_sender">BUY</button>

      </div>
    </div>

  </div>
  <button class="jsonsendere">json</button>
</div>
<%@include file="_footer.jsp" %>
<script src="https://code.jquery.com/jquery-3.7.1.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.30.1/moment.min.js"></script>
<%--<script>--%>
<%--  $(document).ready(function () {--%>
<%--    var interval = setInterval(function () {--%>
<%--      var momentNow = moment();--%>
<%--      $('#received_date').html(momentNow.format('dddd').substring(0, 3).toUpperCase() + ', ' + momentNow.format('DD MMMM YYYY'));--%>
<%--      $('#received_time').html(momentNow.format('hh:mm:ss A'));--%>
<%--    }, 100);--%>



<%--  });--%>


<%--  // function loadAccessToken(x)--%>
<%--  // {--%>
<%--  //--%>
<%--  //   $.ajax({--%>
<%--  //     type: "POST",--%>
<%--  //     url: "https://secure.payu.com/api/v2_1/orders \",--%>
<%--  //     data: {--%>
<%--  //       "Content-type" : "client_credentials",--%>
<%--  //       "Authorization": "Bearer 3e5cac39-7e38-4139-8fd6-30adc06a61bd",--%>
<%--  //--%>
<%--  //--%>
<%--  //--%>
<%--  //     },--%>
<%--  //     success: function (result) {--%>
<%--  //       // Handle the result--%>
<%--  //       console.log(result)--%>
<%--  //--%>
<%--  //--%>
<%--  //       $(".jsonresult").html(result.access_token+"<br>"+--%>
<%--  //               result.token_type+"<br>"+--%>
<%--  //               result.expires_in+"<br>"+--%>
<%--  //               result.grant_type--%>
<%--  //--%>
<%--  //       );--%>
<%--  //     },--%>
<%--  //     error: function (error) {--%>
<%--  //       console.error("Ajax request failed: " + error);--%>
<%--  //     }--%>
<%--  //   });--%>
<%--  // }--%>


<%--</script>--%>
<script>

  <%@include file="/WEB-INF/script/script.js" %>
</script>
</body>
</html>