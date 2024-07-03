package main.resources.controllers;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import main.resources.models.InitData;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/items")

public class ItemsServlet extends HttpServlet {

  String query = "SELECT item_id,name,description,quantity,price FROM items";
  public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
//    InitData data = new InitData();
//    List<List<String>> items = data.itemReader();
//    request.setAttribute("items",items);

    List<Integer> item_id = new ArrayList<>();
    List<String> name = new ArrayList<>();
    List<String> desc = new ArrayList<>();
    List<Integer> qua = new ArrayList<>();
    List<Integer> price = new ArrayList<>();


    try {
      Class.forName("com.mysql.cj.jdbc.Driver");
      Connection connection = DriverManager.getConnection(
          "jdbc:mysql://localhost:3326/magictime", "root", ""
      );
      Statement statement = connection.createStatement();
      ResultSet rs = statement.executeQuery(query);
      while (rs.next())
      {
        item_id.add(rs.getInt("item_id"));
        name.add(rs.getString("name"));
        desc.add(rs.getString("description"));
        qua.add(rs.getInt("quantity"));
        price.add(rs.getInt("price"));
      }
      request.setAttribute("item_id",item_id);
      request.setAttribute("name_item",name);
      request.setAttribute("desc",desc);
      request.setAttribute("qua",qua);
      request.setAttribute("price",price);


    RequestDispatcher view = request.getRequestDispatcher("WEB-INF/views/items.jsp");
    try {
      view.forward(request,response);
    }catch(Exception e){
      e.printStackTrace();
  }
  } catch (SQLException e) {
      throw new RuntimeException(e);
    } catch (ClassNotFoundException e) {
      throw new RuntimeException(e);
    }

}
public void destroy()
  {}
}
