package main.resources.controllers;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import main.resources.models.InitData;
import main.resources.models.ItemDB;

import java.io.IOException;
import java.util.List;

@WebServlet("/buy")
public class BuyServlet extends HttpServlet {
  public void doGet(HttpServletRequest req, HttpServletResponse response) throws IOException {
    int itemNumber = Integer.parseInt(req.getParameter("param"));
    InitData data = new InitData();
    var items = data.itemReader();
    var price = items.get(itemNumber).get(1);
    var name = items.get(itemNumber).get(2);
    var description = items.get(itemNumber).get(3);
    req.setAttribute("price", price);
    req.setAttribute("nameItem", name);
    req.setAttribute("description", description);
    List<String> pass = data.readFileUser();
    String uname = pass.getFirst();

    ItemDB item = new ItemDB();
    //item.addItem("T-shirt Z Nadrukiem Kota","Swobodny Top Z Krótkim Rękawem I Okrągłym Dekoltem Na Wiosnę I Lato, Odzież Damska",30,3000);

    req.setAttribute("uname", uname);
    response.setStatus(HttpServletResponse.SC_OK);
    RequestDispatcher view = req.getRequestDispatcher("WEB-INF/views/buy.jsp");

    try {
      view.forward(req, response);

    } catch (Exception e) {
      e.printStackTrace();
    }
  }
  public void destroy() {
  }
}
