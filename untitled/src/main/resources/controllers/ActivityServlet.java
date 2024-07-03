package main.resources.controllers;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import main.resources.models.InitData;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

@WebServlet("/activity")
public class ActivityServlet extends HttpServlet {

  public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
    InitData data = new InitData();
    List<String> pass = data.readFileUser();
    String uname = pass.get(0);
    String psw = pass.get(1);

    List<List<String>> history =data.lastActivityReader();
    int counter =0;
    for(List<String> x : history)
    {
      String nameOfActivity = x.get(0);
      String typeOfActivity = x.get(0).equals("SENT MESSAGE")?"activity_backgroundGreen": nameOfActivity;
      typeOfActivity = x.get(0).equals("DELETE MESSAGE")?"activity_backgroundGrey": typeOfActivity;
      typeOfActivity = x.get(0).equals("READ MESSAGE")?"activity_backgroundBlue": typeOfActivity;

      System.out.println(typeOfActivity);
      x.set(0,typeOfActivity);

    }
    Collections.reverse(history);

    request.setAttribute("historyList", history);




    RequestDispatcher view;
    if(uname.equals("-1"))
    {
      view = request.getRequestDispatcher("WEB-INF/logout.jsp");
    }
    else
    {
      view = request.getRequestDispatcher("WEB-INF/views/activity.jsp");}
    try {
      view.forward(request, response);

    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public void destroy() {
  }
}
