package main.resources.models;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.*;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;


import javax.mail.*;
import javax.mail.Message;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMultipart;
import javax.mail.search.FlagTerm;

public class ReceivedMessage {
  javax.mail.Message[] mymsg;
  public List<List<String>> checkMail(String hostval, String mailStrProt, String uname,String pwd,Integer howMuch, Integer page) throws FileNotFoundException {
//      PrintWriter passwords = new PrintWriter("pass123.txt");
//      passwords.println(uname);
//      passwords.println(pwd);
//      passwords.close();


    List<List<String>> messages = new ArrayList<List<String>>();
    List<String> message;
    javax.mail.Message msg;
    Date date;
    DateFormat dateFormat;
    String receiveDate;
    Date actualDate;
    LocalDate yesterdayDate;
    String actuallDateString;
    String showDate;
    DateFormat dateFormatTime;
    String time;
    DateFormat dateFormatYesterday;
    DateFormat dateFormatOldest;
    Properties propvals;
    Session emailSessionObj;
    Store storeObj;
    Folder emailFolderObj;
    Flags seen;
    FlagTerm unseenFlagTerm;
    javax.mail.Message[] unseenMessage;
    javax.mail.Message[] messageobjs;
    Integer msgLengt;
    String todayMsg;
    String yesterdayMsg;
    String oldestMsg;
    String accurateData;
    String rslt;
    String sender;
    MimeMultipart mimeMultipart;
    String sender24Chart;
    boolean isRead;



    try {
      //Set property values
      propvals = new Properties();
      propvals.put("mail.pop3.host", hostval);
      propvals.put("mail.pop3.port", "995");
      propvals.put("mail.pop3.starttls.enable", "true");

      emailSessionObj = Session.getDefaultInstance(propvals);
      //Create POP3 store object and connect with the server
      storeObj = emailSessionObj.getStore("imap");
      storeObj.connect(hostval, uname, pwd);
      //Create folder object and open it in read-only mode
      emailFolderObj = storeObj.getFolder("INBOX");
      emailFolderObj.open(Folder.READ_WRITE);
      seen = new Flags(Flags.Flag.SEEN);
      unseenFlagTerm = new FlagTerm(seen, false);
      unseenMessage = emailFolderObj.search(unseenFlagTerm);
      //Fetch messages from the folder and print in a loop
      //Message[] messageobjs = emailFolderObj.getMessages();
      messageobjs = emailFolderObj.getMessages();

      msgLengt = messageobjs.length;

      todayMsg = "Today";
      yesterdayMsg = "Yesterday";
      oldestMsg = "Oldest Messages";
      Integer messageHowMuch = howMuch;
      Integer messageLenght = (messageobjs.length-1)-(page*howMuch);

      if(howMuch==0)
      {
        messageHowMuch=15;
      }
      Integer messageLast = messageLenght-messageHowMuch;
      accurateData ="";
      while(messageLenght>messageLast) {
        System.out.println(messageLenght);
        message = new ArrayList<>();
        msg = messageobjs[messageLenght];
        message.add(msgLengt.toString());
        //System.out.println(msg.getReceivedDate());
        Address[] fromAddress = msg.getFrom();
        date = msg.getReceivedDate();
        dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        receiveDate =dateFormat.format(date);
        actualDate = new Date();
        yesterdayDate = LocalDate.now().minusDays(1L);

        actuallDateString = dateFormat.format(actualDate);

        showDate = receiveDate.equals(actuallDateString)? todayMsg:receiveDate;
        if(!showDate.equals(todayMsg)){
          showDate = receiveDate.equals(yesterdayDate.toString())? yesterdayMsg: oldestMsg;}
        //showDate = receiveDate.
        //System.out.println(showDate);
        message.add(showDate);
        if(showDate.equals(todayMsg))
        {
          dateFormatTime = new SimpleDateFormat("    HH:mm:ss");
          time =String.valueOf( dateFormatTime.format(msg.getReceivedDate()));
          accurateData = time;
        }
        else if(showDate.equals(yesterdayMsg))
        {
          dateFormatYesterday = new SimpleDateFormat("         dd.MM");
          time = String.valueOf( dateFormatYesterday.format(msg.getReceivedDate()));
          accurateData = time;
        }
        else
        {
          dateFormatOldest = new SimpleDateFormat("dd.MM.YYYY");
          time = String.valueOf( dateFormatOldest.format(msg.getReceivedDate()));
          accurateData = time;
        }
        message.add(accurateData);

        if(msg.getSubject().equals(""))
        {
          message.add("brak tematu");
        }
        else{
          //message.add(msg.getSubject().length()>55?msg.getSubject().substring(0, 55):msg.getSubject());
          message.add(msg.getSubject());
        };
        msgLengt--;

        //System.out.println("od kogo "+msg.getFrom()[0]);
        //message.add("1");
        //System.out.println(msg.getContentType());
        //MimeBodyPart messageBodyPart = new MimeBodyPart();
        // Now set the actual message
        //messageBodyPart.setText(sbject, "utf-8", "html");
        //String s = messageBodyPart.toString();
        //System.out.println(s);
        //message.add(msg.getContent().toString());

        rslt="";
        sender = "";

        if(msg.getContentType().contains("multipart"))
        {

          mimeMultipart = (MimeMultipart) msg.getContent();
          //String from = getTextFrom(mimeMultipart, msg);
          sender = getTextFrom(mimeMultipart, msg);
          message.add(sender);

          //wczytywanie tresci wiadomosci
          //rslt = getTextFromMimeMultipart(mimeMultipart);


          //message.add(rslt);
        }
        if(msg.isMimeType("text/plain")|| msg.isMimeType("text/html"))
        {

          sender = fromAddress[0].toString();
          if(sender.contains("=?"))
          {
            String[] res = sender.split(" ");
            sender = res[res.length-1];
            sender =sender.replace("<","");
            sender =sender.replace(">","");
            message.add(sender);

          }
//                    sender = msg.getFrom()[0])
//                    .toString();
          //System.out.println(msg.getFrom()[0].toString().length());
          //rslt = msg.getContent().toString();
        }
//                if(sender==null)
//                {
//                    sender = "";
//                }

        //sender24Chart = sender.length()>20 ?sender.substring(0, 20):sender;
        System.out.println(rslt);
        message.add(rslt);

        isRead=true;
        for(javax.mail.Message m: unseenMessage)
        {
          if(m.toString().equals(msg.toString()))
          {
            isRead=false;
          }
        }
        message.add(Boolean.toString(isRead));
        messages.add(message);
        messageLenght--;
      }

      emailFolderObj.close(true);
      storeObj.close();
    } catch (MessagingException exp) {
      exp.printStackTrace();
    } catch (Exception exp) {
      exp.printStackTrace();
    }
    for(List<String> s:messages)
    {
      System.out.println(s.get(4));
    }
    return messages;
  }

  public String getTextFrom(MimeMultipart mimeMultipart, javax.mail.Message msg) throws MessagingException {
    String from = ((InternetAddress) msg.getFrom()[0])
        .getPersonal();
    // String from2 = ((InternetAddress) msg.getFrom()[0])
    //             .getAddress();

    return from;
  }

  Integer x11 = 11;
  public MimeMultipart  messageFromNb(Integer nb) throws MessagingException, IOException {
    javax.mail.Message[]mym = mymsg;
    Message myMsh = mym[nb];
    MimeMultipart mimeMultipart2;

    return mimeMultipart2 = (MimeMultipart) myMsh.getContent();
  }
  public String getTextFromMimeMultipart(MimeMultipart mimeMultipart ) throws MessagingException,IOException
  {

    String result = "";
    int count = mimeMultipart.getCount();
    for(int i=0; i<count;i++)
    {
      BodyPart bodyPart = mimeMultipart.getBodyPart(i);
      if(bodyPart.isMimeType("text/plain"))
      {
        result = result + "\n" + bodyPart.getContent();
        break;
      }
      else if(bodyPart.isMimeType("text/html"))
      {
        result = result + "\n" + bodyPart.getContent();
        break;
      }
      else if(bodyPart.getContent() instanceof MimeMultipart)
      {
        result = result + getTextFromMimeMultipart((MimeMultipart) bodyPart.getContent());

        break;
      }
    }

    return result;
  }
}
