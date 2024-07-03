package main.resources.controllers;

import java.io.*;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import main.resources.models.EmailSender;
import main.resources.models.InitData;

@WebServlet("/emailSent")
public class EmailSentServlet extends HttpServlet {

  public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

    EmailSender sender = new EmailSender();
    String subject = request.getParameter("subject");
    String message = request.getParameter("message");
    String to = request.getParameter("to");
    InitData data = new InitData();
    List<String> pass = data.readFileUser();
    String uname = pass.get(0);
    String psw = pass.get(1);
    String host = "poczta.o2.pl";
    String port = "465";
    //odczytanie

    data.lastActivityReader();


    //odczytanie


    String attachment = request.getParameter("file");
    String[] attachFiles = new String[1];
    String folderName = "C:\\\\folderek\\";
    String fullPatchAttachment = folderName+ attachment;
    if(fullPatchAttachment.equals(folderName))
    {
      attachFiles=null;
    }
    else {
    attachFiles[0] = fullPatchAttachment;}

    request.setAttribute("name",uname);
    RequestDispatcher view;
    //request.setAttribute("styles",a);
    if(uname.equals("-1"))
    {
      view = request.getRequestDispatcher("/index.jsp");
    }
    else {
    view = request.getRequestDispatcher("WEB-INF/views/emailSent.jsp");}

    try {
      view.forward(request, response);
      sender.sendEmailWithAttachments(host,port,uname,psw,to,subject,message,attachFiles);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }


  public void destroy() {
  }
}