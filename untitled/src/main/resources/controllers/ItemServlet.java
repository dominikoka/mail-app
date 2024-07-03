package main.resources.controllers;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import main.resources.models.InitData;

import java.io.IOException;
import java.sql.*;

@WebServlet("/item")
public class ItemServlet extends HttpServlet {
  public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
    Integer nb = Integer.valueOf(request.getParameter("param"));
    request.setAttribute("number",nb);
    boolean ajaxFunction= Boolean.parseBoolean(request.getParameter("ajaxF"));
    String numberItem = request.getParameter("loader")!=null?request.getParameter("loader"):nb.toString();
//    InitData data = new InitData();
//    var items = data.itemReader();
//    int number = Integer.parseInt(numberItem);
//    var item = items.get(number);
//    request.setAttribute("quantity",item.get(1));
//    request.setAttribute("name",item.get(2));
//    request.setAttribute("description",item.get(3));
    System.out.println("wybrano item nr " + nb);
    //request.setAttribute("item",item);
    String q1 = "SELECT item_id,name,description,quantity,price FROM items where item_id="+(nb+1);
    System.out.println(q1);
    Integer item_id;

    String name = null;
    String desc = null;
    String qua = null;
    int price = 0;
    String nl = "\n";
    try{
      Class.forName("com.mysql.cj.jdbc.Driver");
      Connection cn = DriverManager.getConnection(
        "jdbc:mysql://localhost:3326/magictime","root",""
      );

      Statement statement = cn.createStatement();
      ResultSet rs = statement.executeQuery(q1);
      while (rs.next()){
        name=(rs.getString("name"));
        desc=(rs.getString("description"));
        qua= String.valueOf((rs.getInt("quantity")));
        price=(rs.getInt("price"));
       
      }


      request.setAttribute("name_item",name);
      request.setAttribute("description",desc);
      request.setAttribute("qua",qua);
      request.setAttribute("price",price);
      request.setAttribute("numberOfItem",numberItem);


    } catch (SQLException e) {
      throw new RuntimeException(e);
    } catch (ClassNotFoundException e) {
      throw new RuntimeException(e);
    }
    System.out.println(name + nl+desc + nl + qua + nl + price);

    if(ajaxFunction)
    {
      request.setAttribute("numberofItem",numberItem);
      response.getWriter().write(numberItem);
      RequestDispatcher req = request.getRequestDispatcher("WEB-INF/views/itemFastView.jsp");
      try {
        req.forward(request,response);
      }catch(Exception e){
        e.printStackTrace();
      }
    }
    else {
      RequestDispatcher req = request.getRequestDispatcher("WEB-INF/views/item.jsp");
      try {
        req.forward(request,response);
      }catch(Exception e){
        e.printStackTrace();
      }
    }

  }
}
