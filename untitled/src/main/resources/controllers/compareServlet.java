package main.resources.controllers;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import main.resources.models.InitData;

import java.io.FileNotFoundException;
import java.io.IOException;

@WebServlet("/compare")
public class compareServlet extends HttpServlet {

  public void doPost(HttpServletRequest req, HttpServletResponse response) throws IOException {
    InitData data = new InitData();
    String secretPassword = data.getDiscountCode();
    String uname = req.getParameter("discountCode");
    boolean ifTheSame = secretPassword.equals(uname);
    System.out.println(ifTheSame);
//    String getKey = "0";
    String getKey = req.getParameter("getKey") == null ?"0":"1";

    System.out.println(getKey);
    String accessKeys="";
    String id_pos = data.getId_pos();
    String secret_Auth = data.getClient_secret();
    accessKeys = id_pos +" " +secret_Auth;
    response.setStatus(HttpServletResponse.SC_OK);
    if(getKey.equals("1"))
    {
      System.out.println("rowna sie jeden wczytuje klucze");
      response.getWriter().write(String.valueOf(accessKeys));
    }
    else
    {
      System.out.println("rowna sie zero porownuje kody discount");
      response.getWriter().write(String.valueOf(ifTheSame));
    }

  }
}
