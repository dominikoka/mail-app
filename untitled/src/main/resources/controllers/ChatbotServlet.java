package main.resources.controllers;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import main.resources.models.Chatbot;

import javax.mail.MessagingException;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@WebServlet("/chatbot")
public class ChatbotServlet extends HttpServlet {
  int counterMsg = 0;
  List<String> msgList = new ArrayList<>();
  Chatbot chatbot;

  {
    try {
      chatbot = new Chatbot();
    } catch (IOException | AWTException e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
    String newMessage = ",s.";
    String welcomeText = "Welcome in online helper. if u want to see more info just write /help/";
    String msg = request.getParameter("msg");
    System.out.println(msg);
    if(!msg.isEmpty())
    {
      msgList.add(msg);
      try {
        msgList.add(chatbot.ifWriteCorrectlyWord(msg)? chatbot.correctlyWord(msg):welcomeText);
      } catch (MessagingException e) {
        throw new RuntimeException(e);
      } catch (AWTException e) {
        throw new RuntimeException(e);
      }

    }
    String result = msgList.stream()
        .map(n -> String.valueOf(n))
        .collect(Collectors.joining(newMessage));
    response.getWriter().write(result);
  }
}

