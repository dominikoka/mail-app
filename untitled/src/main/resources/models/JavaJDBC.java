package main.resources.models;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class JavaJDBC {
  public static void db() {
    try {
      Class.forName("com.mysql.cj.jdbc.Driver");
      Connection connection = DriverManager.getConnection(
          "jdbc:mysql://localhost:3316/test", "root", ""
      );
      Statement statement = connection.createStatement();
      ResultSet resultSet = statement.executeQuery("select * from student");
      while (resultSet.next()) {
        System.out.println(resultSet.getInt(1) + " " + resultSet.getString(2) + " " + resultSet.getString(3));
        ;

      }
      connection.close();
    } catch (Exception e) {
      System.out.println(e);
    }
  }
}