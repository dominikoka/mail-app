package main.resources.controllers;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import main.resources.models.EmailMessageRemover;
import main.resources.models.InitData;

import static java.lang.Integer.parseInt;

@WebServlet("/deleteMessage")
public class DeleteMessageServlet extends HttpServlet {


  public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
    response.setContentType("text/html");
    EmailMessageRemover remover = new EmailMessageRemover();

    InitData data = new InitData();
    List<String> pass = data.readFileUser();
    String uname = pass.get(0);
    String psw = pass.get(1);

    String host = "poczta.o2.pl";
    String port = "993";

    List<Integer> readyToDelete = new ArrayList<>();
    String[] readyToDeleteString = request.getParameterValues("email_delete");
    for(int i=0;i<readyToDeleteString.length;i++)
    {
      Integer nbToDelete = parseInt(readyToDeleteString[i]);
      nbToDelete = nbToDelete-1;
      readyToDelete.add(nbToDelete);
      //System.out.printf(String.valueOf(readyToDelete.get(i)));
    }

    remover.deleteMessages(host,port,uname,psw,readyToDelete);
    request.setAttribute("name",uname);
    // Hello
    RequestDispatcher view;
    if(uname.equals("-1"))
    {
      view = request.getRequestDispatcher("WEB-INF/index.jsp");
    }
    else
    {
      view = request.getRequestDispatcher("WEB-INF/views/deleteMessage.jsp");}
    try {
      view.forward(request, response);

    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public void destroy() {
  }
}