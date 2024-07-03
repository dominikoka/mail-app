package main.resources.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import main.resources.models.CustomScreenRecorder;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

@WebServlet("/Recorder")
public class TESTERRServletTest extends HttpServlet {

  int i = 0;
  String text;
  Boolean loadRecorder = true;
  String result = "";
  File f;
  String path="";
  CustomScreenRecorder recorder = null;
  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

//  String pathFiles = request.getParameter("design");
//
//  //String parameterValue = request.getParameter("parameterName");
//    //System.out.println(parameterValue + "nazwa folderu");
//
String loadFileClass = request.getParameter("loader");
if(loadFileClass == null)
{
  loadFileClass="";
}

//  System.out.println(loadFileClass);

  if(loadFileClass.equals("pathLoad"))
  {

      //showFilePath file = new showFilePath();
      path = String.valueOf(save());
      System.out.println(path);
      result = path;
  }
  else {

    try {

      if(i==0){

        if(!path.equals(""))
        {
          f = new File(path);
        }
        else
        {
         f= new File("C:/folderek/");
        }


        System.out.println("wczytuje objekt rekordera");
      recorder = new CustomScreenRecorder(f);}
    } catch (AWTException e) {
      throw new RuntimeException(e);
    }


    if(i==0){
      text="recording";
      System.out.println("przed nagrywaniem");
      recorder.startRecording("rekordvidjo",true);
    i=1;
    }
    else
    {
      recorder.stopRecording(true);
      System.out.println("po nagrywaniu");
    text = "just stopped";
    i=0;}


    //You want world domination, huh?


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