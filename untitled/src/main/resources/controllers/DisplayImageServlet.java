package main.resources.controllers;

import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.*;


public class DisplayImageServlet extends HttpServlet {
//  public final String imagesBase = "C:\\Users\\lenda\\IdeaProjects\\servlet_piaskownica\\off01\\untitled\\src\\main\\webapp\\WEB-INF\\images/";
  public final String imagesBase = "C:\\Users\\lenda\\IdeaProjects\\servlet_piaskownica\\off01\\untitled\\src\\main\\webapp\\WEB-INF\\images/";

  public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException{
    String URLAfterWebDomain = request.getRequestURI();

    //Only accept mappings as src="/images/whatever.jpg", even if web.xml has other mappings to this servlet.
    if(URLAfterWebDomain.startsWith("/images/") == false)
      return;

    //get the image name, or even directory and image, e.g. /images/music/beethoven.jpg:
    String relativeImagePath = URLAfterWebDomain.substring("/images/".length());  //will get "music/beethoven.jpg"

    System.out.println("\nFetching image from "+imagesBase+relativeImagePath);
    response.setContentType("image/jpeg"); //as far as I know, this works for PNG as well. You might want to change the mapping to /images/*.jpg if it's giving problems

    ServletOutputStream outStream;
    outStream = response.getOutputStream();
    FileInputStream fin = new FileInputStream(imagesBase+relativeImagePath);

    BufferedInputStream bin = new BufferedInputStream(fin);
    BufferedOutputStream bout = new BufferedOutputStream(outStream);
    int ch =0; ;
    while((ch=bin.read())!=-1)
      bout.write(ch);

    bin.close();
    fin.close();
    bout.close();
    outStream.close();
  }
}
