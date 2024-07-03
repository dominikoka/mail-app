package main.resources.controllers;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import main.resources.models.EmailMessageRemover;
import main.resources.models.ReceivedMessage;
import main.resources.models.InitData;

@WebServlet("/receivedMessage")
public class ReceivedMessageServlet extends HttpServlet {


    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
      String uname = request.getParameter("uname");

      String psw = request.getParameter("upsw");
//      PrintWriter passwords = new PrintWriter(getServletContext().getContextPath() +"pass1234.txt");
//      passwords.println(uname);
//      passwords.println(psw);
//      passwords.close();

        InitData data = new InitData();
        data.createFileUser(uname,psw);


      String host = "poczta.o2.pl";



      EmailMessageRemover remover = new EmailMessageRemover();
      List<Integer> empty = new ArrayList<>();
      remover.deleteMessages(host,"993",uname,psw,empty);
      ReceivedMessage emails = new ReceivedMessage();
      List<List<String>> emailsList = emails.checkMail(host,host,uname,psw,15,0);


      request.setAttribute("styles",emailsList);
      request.setAttribute("name", uname);
      RequestDispatcher view;
      //request.setAttribute("styles",a);
      if(uname.equals("-1"))
      {
        view = request.getRequestDispatcher("/index.jsp");
      }
      else {
      view = request.getRequestDispatcher("WEB-INF/views/receivedMessage.jsp");}


      try {
        view.forward(request, response);

      } catch (Exception e) {
        e.printStackTrace();
      }
    }

    public void destroy() {
    }
}