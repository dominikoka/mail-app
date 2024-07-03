package main.resources.controllers;

import com.google.gson.Gson;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import main.resources.models.*;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

import static java.lang.System.out;


@WebServlet("/payuPay")
public class payuPayServlet extends HttpServlet {
  public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

    String itemName = request.getParameter("itemName");
    String itemQuantity = request.getParameter("itemQuantity");

    String userEmail = request.getParameter("userEmail");
    String userPhone = request.getParameter("userPhone");
    String userName = request.getParameter("userName");
    String userSurname = request.getParameter("userSurname");

    String adressCity = request.getParameter("adressCity");
    String adressHouse = request.getParameter("adressHouse");
    String adressZip = request.getParameter("adressZip");

    String itemPrice = request.getParameter("itemPrice");


    var newLine ="\n";
    out.println("oto muj ziomek");
    out.println(itemName+newLine+itemQuantity+newLine+userEmail+newLine+userPhone+newLine+userName+newLine+userSurname+newLine+adressCity+newLine+adressHouse+newLine+adressZip+newLine+itemPrice);

    createTokenAuth tokenObj = new createTokenAuth();
    var token = tokenObj.createToken();
    var tokenParts = token.split(":");
    var tokenKey = tokenParts[1].replace("\"","").substring(0,36);

    var order = tokenObj.CreateOrder(tokenKey,itemName,itemQuantity,userEmail,userPhone,userName,userSurname,adressCity,adressHouse,adressZip,itemPrice);
    out.println(order);

//    Example ex = new Example();
//    Example.main(null);
//
//    JavaJDBC.db();
    response.setContentType("text/html");
    out.println("oto mun order" + order);
    response.getWriter().write(order);




  }
}
