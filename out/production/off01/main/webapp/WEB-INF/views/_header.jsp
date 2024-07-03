<div class="header">
  <div class="header_box">
    <div class="header_name">
      <%
        String name = (String) request.getAttribute("name");
        out.print(name);
      %>
    </div>
    <div class="header_title">Magic Mail - Get Better Email</div>

    <div class="header_actuallDate">
      <div id="received_date" class="received_date"></div>
      <div id="received_time" class="received_time"></div></div>
  </div>
</div>