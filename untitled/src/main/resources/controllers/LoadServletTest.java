package main.resources.controllers;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/testt")
public class LoadServletTest extends HttpServlet {

  public void doGet(HttpServletRequest request, HttpServletResponse response)
  {
    RequestDispatcher view = request.getRequestDispatcher("WEB-INF/views/tester.jsp");
    try {
      view.forward(request, response);

    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
