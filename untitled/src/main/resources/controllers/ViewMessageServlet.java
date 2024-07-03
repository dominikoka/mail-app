package main.resources.controllers;

import java.io.*;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import main.resources.models.InitData;
import main.resources.models.Message;

@WebServlet("/viewMessage")
public class ViewMessageServlet extends HttpServlet {

  public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
    response.setContentType("text/html; charset=utf-8");
    request.setCharacterEncoding("UTF-8");
    InitData data = new InitData();
    List<String> pass = data.readFileUser();
    String uname = pass.get(0);
    String psw = pass.get(1);
    String host = "poczta.o2.pl";
    Integer nb = Integer.valueOf(request.getParameter("param"));
    PrintWriter oute = response.getWriter();
    //oute.print(nb);
    Message msge = new Message();

      String msg = msge.checkMail(host,host,uname,psw,nb).toString();
      String attachment = msge.attachment;
    //System.out.println(msg);
    //oute.println(msg);
    String saveDirectory = "C:\\folderek";
    msge.setSaveDirectory(saveDirectory);
    request.setAttribute("styles",msg.toString());
    if(msg.toString().contains("DOCTYPE"))
    {

    }
    request.setAttribute("attachment",attachment);
    request.setAttribute("name",uname);
    RequestDispatcher view;
    //request.setAttribute("styles",a);
    if(uname.equals("-1"))
    {
      view = request.getRequestDispatcher("/index.jsp");
    }
    else {
    view = request.getRequestDispatcher("WEB-INF/views/viewMessage.jsp");}

    try {
      view.forward(request, response);
    } catch (Exception e) {
      //e.printStackTrace();
    }


  }

  public void destroy() {
  }
}