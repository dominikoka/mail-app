package main.resources.models;

import javax.imageio.ImageIO;
import javax.mail.MessagingException;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Chatbot {
  final String help = "help";
  final String send = "send";
  final String whattime = "whattime";
  final String startrecording = "startrecording";
  final String screenshoot = "screenshoot";
  final String conventer = "conventer";
  final String weather = "weather";
  final String discount = "discount";
  final String helpMsg = "I'm ready to help you. Write what you need:<br> send - to send msg <br> whattime - to show actuall date <br> startrecording - to record video<br>screenshoot - if u want to do screenshoot<br>conventer - to conventer currency<br> discount - to show extra code to first shopping in popular shop";
  List<String> words = Arrays.asList(help, send, whattime, startrecording, screenshoot, conventer,weather,discount);
  InitData data = new InitData();
  List<String> pass = data.readFileUser();
  String uname = pass.get(0);
  String psw = pass.get(1);
  String host = "poczta.o2.pl";
  String port = "465";
  File f = new File("C:/folderek/");
  CustomScreenRecorder screenRecorder = new CustomScreenRecorder(f);
  int wordTwoCounter = 0;

  public Chatbot() throws IOException, AWTException {
  }
  boolean recordingSwitch = true;
  public boolean ifWriteCorrectlyWord(String word)
  {
    if(wordTwoCounter>0 || !recordingSwitch || wordSixCounter>0)
    {
      return true;
    }
    word = word.toLowerCase();
    for(String keyWord: words)
    {
      if(keyWord.equals(word))
      {
        return true;
      }
    }
    return false;
  }
  int wordSixCounter = 0;
  int wordSixchangedOption=0;

  public String correctlyWord(String word) throws MessagingException, IOException, AWTException {
//    PayuClient payuClient = PayuClient.init("KEY", "SALT");
    String result = "";
    String inputWord="";
    if(word.equals(send) || wordTwoCounter>0)
    {
    inputWord = word;
    word = send;
    wordTwoCounter++;
    }
    if(!recordingSwitch)
    {
      recordingSwitch = true;
      screenRecorder.stopRecording(true);
      result = "your video just stopped";
      word="";
    }
    if(wordSixCounter>0)
    {
      inputWord = word;
      word = conventer;

    }
    System.out.println("aa");
    switch (word)
    {

      case help:
        result = helpMsg;
        break;
      case send:
        result = sendEmailWithChatbot(inputWord);
        wordTwoCounter = wordTwoCounter==4?0:wordTwoCounter;
        break;
      case whattime:
        LocalDateTime myDate = LocalDateTime.now();
        DateTimeFormatter formatDate = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        DateTimeFormatter formatDate2 = DateTimeFormatter.ofPattern(" HH:mm:ss");
        result=myDate.format(formatDate)+ "<br>" + myDate.format(formatDate2);
        break;
      case startrecording:
        if(recordingSwitch)
        {
          screenRecorder.startRecording("video",false);
          recordingSwitch = false;
          result = "write whatever you want to stop the recording";
        }
        break;
      case screenshoot:
        DesktopScreenRecorder screenshoot = new DesktopScreenRecorder();
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
        result="you just done screenshoot";
        break;
      case conventer:
        System.out.println();
        if(wordSixCounter==0)
        {
          result="1 - to change euro to pln<br>2 - to change pln to euro";
          wordSixCounter++;
        }
        else if (wordSixCounter==1) {
          int changedNumber = Integer.parseInt(inputWord);
          System.out.println("wybrana opcja do przewalutowania" + changedNumber);
          wordSixchangedOption = changedNumber;
          result="write quantity";
          wordSixCounter++;
        }
        else
        {
          if(wordSixchangedOption == 1)
          {
            int inputMoney = Integer.parseInt(inputWord);
            double resultInt=inputMoney*4.35;
            result= String.format("%.2f",resultInt);
            wordSixCounter=0;
          }
          else
          {
            int inputMoney = Integer.parseInt(inputWord);
            double resultInt=inputMoney/4.35;
            result= String.format("%.2f",resultInt);
            wordSixCounter=0;
          }
        }
        break;
      case weather:
        result="Warsaw 10°C";
        break;
      case discount:
        result="you get a 20% discount on all items on All.com use word 'aezakmi'";
        break;
    }
    return result;
  }
  List<String> sendEmailData = new ArrayList<>();
  int counterSendEmail = 0;
  public String sendEmailWithChatbot(String msg) throws MessagingException, FileNotFoundException {
    //TODO ladnie napisac jak zacznie dzialac
    String rslt = "";
    if(counterSendEmail == 0)
    {
      rslt ="please write recipient's email";
      counterSendEmail++;
    }
    else if(counterSendEmail == 1)
    {
      sendEmailData.add(msg);
      rslt = "please write email subject";
      counterSendEmail++;
    }
    else if(counterSendEmail == 2)
    {
      sendEmailData.add(msg);
      rslt = "please write email msg";
      counterSendEmail++;
    }
    else
    {
      sendEmailData.add(msg);
      EmailSender.sendEmailWithAttachments(host,port,uname,psw,sendEmailData.get(0),sendEmailData.get(1),sendEmailData.get(2),null);
      rslt = "email is just sent.<br> Do you need more help? write /help/ to see more options";
      counterSendEmail = 0;
      sendEmailData = new ArrayList<>();
    }
    return rslt;
  }
}
