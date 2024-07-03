package main.resources.models;

import java.io.FileInputStream;
import java.sql.*;

public class ItemDB {
  public void addItem(String name,String description,int quantity,int price) {
    try {
      Class.forName("com.mysql.cj.jdbc.Driver");
      Connection connection = DriverManager.getConnection(
          "jdbc:mysql://localhost:3326/magictime", "root", ""
      );
      Statement statement = connection.createStatement();

//      String q1="INSERT INTO Items (name, description, quantity, price, image_main, image_one, image_two, image_three) VALUES ('Przykładowy przedmiot', 'Opis przykładowego przedmiotu', 10, 50, LOAD_FILE('/'), LOAD_FILE('C:\\Users\\lenda\\IdeaProjects\\servlet_piaskownica\\off01\\untitled\\src\\main\\webapp\\WEB-INF\\images\\item00.jpg'), LOAD_FILE('C:\\Users\\lenda\\IdeaProjects\\servlet_piaskownica\\off01\\untitled\\src\\main\\webapp\\WEB-INF\\images\\item00.jpg'), LOAD_FILE('C:\\Users\\lenda\\IdeaProjects\\servlet_piaskownica\\off01\\untitled\\src\\main\\webapp\\WEB-INF\\images\\item00.jpg'));";

      String imagePathMain = "C:\\Users\\lenda\\IdeaProjects\\servlet_piaskownica\\off01\\untitled\\src\\main\\webapp\\WEB-INF\\images\\item00.jpg";
      String imagePathOne = "C:\\Users\\lenda\\IdeaProjects\\servlet_piaskownica\\off01\\untitled\\src\\main\\webapp\\WEB-INF\\images\\item01.jpg";
      String imagePathTwo = "C:\\Users\\lenda\\IdeaProjects\\servlet_piaskownica\\off01\\untitled\\src\\main\\webapp\\WEB-INF\\images\\item02.jpg";
      String imagePathThree = "C:\\Users\\lenda\\IdeaProjects\\servlet_piaskownica\\off01\\untitled\\src\\main\\webapp\\WEB-INF\\images\\item03.jpg";

      String q1 = "INSERT INTO items(name,description,quantity,price) VALUES(\""+name+"\",\""+description+"\","+quantity+","+price+")";
      System.out.println(q1);
      statement.executeUpdate(q1);

      connection.close();
    } catch (Exception e) {
      System.out.println(e);
    }
  }
}