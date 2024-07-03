package main.resources.controllers;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import main.resources.models.InitData;
import main.resources.models.Sms;

import java.io.FileNotFoundException;
import java.io.IOException;

@WebServlet("/smsApi")
public class SmsApi extends HttpServlet {
  boolean ifNoMsg = true;
  int code = 0;
  public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
    var res="";
    Sms sms = new Sms();
    var reqCode = request.getParameter("generateCode");

    if(ifNoMsg){
    InitData data = new InitData();
    code = data.getSmsCode();
    res = sms.create(code);
    ifNoMsg = false;
    }
    else
    {
      res = sms.check(reqCode, code);
    }


//    var res =reqCode.equals("6669666")?sms.create():sms.check(reqCode);


    response.getWriter().write(res);

  }
}
