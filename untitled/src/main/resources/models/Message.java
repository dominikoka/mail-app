package main.resources.models;

import java.io.FileNotFoundException;
import java.util.*;
import java.io.IOException;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.search.FlagTerm;

public class Message {
  public String attachment="";
  boolean htmlFormat = false;
  public String checkMail(String hostval, String mailStrProt, String uname,String pwd,Integer nb) throws FileNotFoundException {
    InitData readMsg = new InitData();
    List<List<String>> messages = new ArrayList<List<String>>();
    List<String> message;
    javax.mail.Message msg;
    Properties propvals;
    Session emailSessionObj;
    Store storeObj;
    Folder emailFolderObj;
    Flags seen;
    FlagTerm unseenFlagTerm;
    String sbject="";
    String from ="";
    String isAttachment = "bez zalacznika";

    javax.mail.Message[] messageobjs;

    String rslt="";

    MimeMultipart mimeMultipart;





    try {
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

      //Fetch messages from the folder and print in a loop
      //Message[] messageobjs = emailFolderObj.getMessages();
      messageobjs = new javax.mail.Message[]{emailFolderObj.getMessage(nb)};
      MimeMessage Mimemessage = new MimeMessage(emailSessionObj);






      Integer messageLenght = messageobjs.length-1;
      Integer messageHowMuch = 15;
      Integer messageLast = messageLenght-messageHowMuch;

      //while(messageLenght>messageLast) {
        System.out.println(messageLenght);
        message = new ArrayList<>();
        msg = messageobjs[0];
        Address[] fromAddress = msg.getFrom();
        sbject = msg.getSubject();


        from = msg.getFrom().toString();
      System.out.println(msg.getSize());
        Part p = msg;
      System.out.println(msg.getContentType());

        rslt="";

        if(msg.getContentType().contains("multipart"))
        {


          mimeMultipart = (MimeMultipart) msg.getContent();
          from = getTextFrom(mimeMultipart, msg);
          System.out.println(from);

          if(msg.getContentType().contains("multipart/mixed"))
          {
            System.out.println("sprawdzanie zalacznika");
          //sprawdzenie zalacznika

          attachment = checkAttachment(msg);
          isAttachment = "załącznik";

          }
          //wczytywanie tresci wiadomosci

          rslt = getTextFromMimeMultipart(mimeMultipart);
          //System.out.println(getMessageBodyOrContentType(Mimemessage,true));
          rslt = rslt.replace("\n","<br />");


          message.add(rslt);
        }
        if(msg.isMimeType("text/plain")|| msg.isMimeType("text/html"))
        {
          from = fromAddress[0].toString();
          htmlFormat = true;
          rslt = msg.getContent().toString();
        }

        message.add(rslt);
      if(from.contains("=?"))
      {
        String[] res = from.split(" ");
        from = res[res.length-1];
        from =from.replace("<","");
        from =from.replace(">","");


      }
      if(!rslt.isEmpty()){
        readMsg.lastActivityCreateFile(from, sbject,rslt,isAttachment,new Date(),2,htmlFormat);}

        messages.add(message);
//      if(msg.getContentType().contains("multipart/alternative"))
//      {
//        rslt = rslt.replace("\n","<br>");
//      }


      emailFolderObj.close(false);
      storeObj.close();
    } catch (MessagingException exp) {
      exp.printStackTrace();
    } catch (Exception exp) {
      exp.printStackTrace();
    }
    //System.out.println(rslt + "z klasy message");



    return rslt;
  }

  public String getTextFrom(MimeMultipart mimeMultipart, javax.mail.Message msg) throws MessagingException {
    String from = ((InternetAddress) msg.getFrom()[0])
        .getPersonal();
    // String from2 = ((InternetAddress) msg.getFrom()[0])
    //             .getAddress();

    return from;
  }
  private String saveDirectory;

  /**
   * Sets the directory where attached files will be stored.
   * @param dir absolute path of the directory
   */
  public void setSaveDirectory(String dir) {
    this.saveDirectory = dir;
  }
  public String checkAttachment(javax.mail.Message msg) throws MessagingException, IOException {

    String attachFiles = "";
    String fileName="";
    Multipart multiPart = (Multipart) msg.getContent();
    int numberOfParts = multiPart.getCount();
    for (int partCount = 0; partCount < numberOfParts; partCount++) {
      MimeBodyPart part = (MimeBodyPart) multiPart.getBodyPart(partCount);
      if (Part.ATTACHMENT.equalsIgnoreCase(part.getDisposition())) {
        // this part is attachment
        fileName = part.getFileName();
        System.out.println(fileName);
        System.out.println("jest załoncznik");
        attachFiles+=fileName+",";
        part.saveFile("C:\\folderek\\"+ fileName);
        //part.saveFile(saveDirectory + File.separator + fileName);
      } else {
        // this part may be the message content


      }}
    return fileName;
  }
  public String getTextFromMimeMultipart(MimeMultipart mimeMultipart ) throws MessagingException,IOException
  {

    String result = "";
    int count = mimeMultipart.getCount();
    for(int i=0; i<count;i++)
    {
  BodyPart bodyPart = mimeMultipart.getBodyPart(i);
//      MimeBodyPart part = (MimeBodyPart) mimeMultipart.getBodyPart(i);
//      if (Part.ATTACHMENT.equalsIgnoreCase(part.getDisposition())) {
//        System.out.println("załoncznik");
//        break;
//      }
       if(bodyPart.isMimeType("text/html"))
    {
      result = result + "\n" + bodyPart.getContent();
      break;
    }
      else if(bodyPart.isMimeType("text/plain"))
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

  //read
  private String getMessageBodyOrContentType( Part p, final boolean returnContentType ) throws MessagingException,
      IOException {
    if ( p.isMimeType( "text/*" ) ) {
      String s = (String) p.getContent();
      return returnContentType ? p.getContentType() : s;
    }
    if ( p.isMimeType( "multipart/alternative" ) ) {
      // prefer html text over plain text
      Multipart mp = (Multipart) p.getContent();
      String text = null;
      for ( int i = 0; i < mp.getCount(); i++ ) {
        Part bp = mp.getBodyPart( i );
        if ( bp.isMimeType( "text/plain" ) ) {
          if ( text == null ) {
            text = getMessageBodyOrContentType( bp, returnContentType );
          }
        }
      }
      return text;
    } else if ( p.isMimeType( "multipart/*" ) ) {
      Multipart mp = (Multipart) p.getContent();
      for ( int i = 0; i < mp.getCount(); i++ ) {
        String s = getMessageBodyOrContentType( mp.getBodyPart( i ), returnContentType );
        if ( s != null ) {
          return s;
        }
      }
    }
    return null;
  }
  //read

}
