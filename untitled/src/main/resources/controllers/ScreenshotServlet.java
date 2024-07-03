package main.resources.controllers;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

@WebServlet("/screenshot")

public class ScreenshotServlet extends HttpServlet {
  String pathFile = "";
  String result = "";
  String fileName = "screenshot.png";
  public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
    String loadFileClass0 = request.getParameter("parameterValue3");
    String loadFileClass = request.getParameter("loader");
    System.out.println(loadFileClass0);
    System.out.println(loadFileClass);
    if(loadFileClass == null)
    {
      loadFileClass = "";
    }
    if(loadFileClass.equals("load"))
    {
      String pathf = save() ;
      pathFile = pathf + fileName;
      result = pathFile;
    }
    else
    {
      pathFile = fileName;

      Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
      Rectangle screenRectangle = new Rectangle(screenSize);
      Robot robot = null;
      try {
        robot = new Robot();
      } catch (AWTException e) {
        throw new RuntimeException(e);
      }
      BufferedImage image = robot.createScreenCapture(screenRectangle);
      ImageIO.write(image, "png", new File("C:/folderek/screen.png"));
    }


    response.getWriter().write(result);


  }
  public String save()
  {

    JFileChooser f = new JFileChooser();
    f.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
    f.showSaveDialog(null);
    String result = f.getCurrentDirectory()+ "\\";
    System.out.println(f.getCurrentDirectory() + "current direcroty");



    System.out.println(result);
    return  result;
  }
}
