package main.resources.controllers;

import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import main.resources.models.InitData;

@WebServlet("/emailSender")
public class EmailSenderServlet extends HttpServlet {

  public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

    InitData data = new InitData();
    List<String> file =data.readFileUser();
    String uname = file.get(0);
    List<List<String>> history =data.lastActivityReader();
    for (int i = 0; i < history.size(); i++) {
      if(!history.get(i).get(0).equals("SENT MESSAGE"))
      {
        history.remove(i);
        i=0;
      }

    }
    Collections.reverse(history);
    //last sent
    LocalDate yesterdayDate = LocalDate.now().minusDays(1L);
    Date actuallDate =new Date();
    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    String actuallDateString = dateFormat.format(actuallDate);
    for(List<String> x : history)
    {
      String dateMsg = x.get(5);
//      LocalDate date = LocalDate.parse(dateMsg);
//      String dateOfMsg = dateFormat.format(x.get(5));
//      if(x.get(0).equals(actuallDate) || x.get(0).equals(yesterdayDate))
//      {
//        System.out.println("wczorajszy");
//      }
    }
    // ***



    RequestDispatcher view;
    request.setAttribute("name",uname);
    request.setAttribute("historyList", history);
    if(uname.equals("-1"))
    {
      view = request.getRequestDispatcher("/index.jsp");
    }
    else {
    view = request.getRequestDispatcher("WEB-INF/views/emailSender.jsp");}
    try {
      view.forward(request, response);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public void destroy() {
  }
}