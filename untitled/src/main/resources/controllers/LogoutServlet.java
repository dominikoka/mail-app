package main.resources.controllers;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import main.resources.models.InitData;

import java.io.FileNotFoundException;

@WebServlet("/logout")
public class LogoutServlet extends HttpServlet {

  public void doGet(HttpServletRequest request, HttpServletResponse response) throws FileNotFoundException {
    InitData i = new InitData();
    i.clearFileUser();


    RequestDispatcher view = request.getRequestDispatcher("WEB-INF/views/logout.jsp");
    try {
      view.forward(request, response);

    } catch (Exception e) {
      e.printStackTrace();
    }

  }
}
