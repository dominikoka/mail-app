<html>
<head>
    <style><%@include file="/WEB-INF/css/style.css"%></style>
    <link href='https://unpkg.com/boxicons@2.1.4/css/boxicons.min.css' rel='stylesheet'>

</head>
<body class="background">
<div class="title">
  <div class="title_h2 title_first">Magic Mail</div>
  <div class="title_h2 title_second">Magic Mail</div>

</div>
  <div class="login">
    <div class="login_title">Login</div>
    <form action="receivedMessage" method="post" class="login_form">
      <%--    <div class="imgcontainer">--%>
      <%--        <img src="img_avatar2.png" alt="Avatar" class="avatar">--%>
      <%--    </div>--%>
      <div class="login_file">

        <div class="login_input">
        <input type="text" placeholder="Username" name="uname" required class="login_placeholder">
          <i class='bx bxs-user login_i'></i>
      </div>
          <div class="login_input">
        <input type="password" placeholder="Password" name="upsw" required class="login_placeholder">
              <i class='bx bxs-lock login_i' ></i>
  </div>
        <button type="submit" class="login_button">Login</button>

      </div>
    </form>
  </div>

</body>
</html>
