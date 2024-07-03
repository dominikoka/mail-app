package main.resources.controllers;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import main.resources.models.EmailMessageRemover;
import main.resources.models.ReceivedMessage;
import main.resources.models.InitData;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@WebServlet("/receivedMessageGET")
public class ReceivedMessageHelperServlet extends HttpServlet {


  Integer howMuch = 15;
  Integer page = 0;
  public void doGet(HttpServletRequest request, HttpServletResponse response) throws FileNotFoundException {
    InitData data = new InitData();
    List<String> pass = data.readFileUser();
    String uname = pass.get(0);
    String psw = pass.get(1);

    String host = "poczta.o2.pl";
    if(request.getParameter("count")==null &&request.getParameter("direction")==null)
    {
      page = 0;
      howMuch = 15;
    }
    var howMuchOutput = (request.getParameter("count")==null)?String.valueOf(howMuch):request.getParameter("count");
    if(!Objects.equals(howMuch, Integer.valueOf(howMuchOutput)))
    {
      page = 0;
    }
    howMuch = Integer.valueOf(howMuchOutput);
    var direction = request.getParameter("direction")==null?String.valueOf(page):request.getParameter("direction");
    Integer directorInt = Integer.valueOf(direction);
    //page = page+directorInt;
    page = (page==0 && directorInt<0)?0:page+directorInt;

    System.out.printf(page + " K T U R A  S T R O N A");

//    System.out.printf(page);
//    if(howMuchOutput == null)
//    {
//      howMuchOutput = String.valueOf(howMuch);
//    }
//    if(howMuchOutput.equals("15"))
//    {
//      howMuch = Integer.valueOf("15");
//    }
//    if(howMuchOutput.equals("30"))
//    {
//      howMuch = 30;
//    }
    System.out.printf("T Y L E  J E S T"+howMuch);


    EmailMessageRemover remover = new EmailMessageRemover();
    List<Integer> empty = new ArrayList<>();
    remover.deleteMessages(host,"993",uname,psw,empty);
    ReceivedMessage emails = new ReceivedMessage();
    List<List<String>> emailsList = emails.checkMail(host,host,uname,psw,3,page);
//    File f = new File("C:/folderek/");
//    CustomScreenRecorder recorder;
//    try {
//      recorder = new CustomScreenRecorder(f);
//    } catch (IOException e) {
//      throw new RuntimeException(e);
//    } catch (AWTException e) {
//      throw new RuntimeException(e);
//    }


    //request.setAttribute("recorder",recorder);
    request.setAttribute("styles",emailsList);
    request.setAttribute("name", uname);
    request.setAttribute("page",page);
    request.setAttribute("howMuch",howMuch);

    RequestDispatcher view;
    //request.setAttribute(series);
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
