package main.resources.models;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class EmailSender {
   static List<String> sentMsg = new ArrayList<>();

  public static void sendEmailWithAttachments(String host, String port,
                                              final String userName, final String password, String toAddress,
                                              String subject, String message, String[] attachFiles)
      throws AddressException, MessagingException, FileNotFoundException {
    // sets SMTP server properties
    Properties properties = new Properties();
    properties.put("mail.smtp.host", host);
    properties.put("mail.smtp.port", port);
    properties.put("mail.smtp.auth", "true");
    properties.put("mail.smtp.starttls.enable", "true");
    properties.put("mail.user", userName);
    properties.put("mail.password", password);
    properties.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");
    InitData file = new InitData();

    // creates a new session with an authenticator
    Authenticator auth = new Authenticator() {
      public PasswordAuthentication getPasswordAuthentication() {
        return new PasswordAuthentication(userName, password);
      }
    };
    Session session = Session.getInstance(properties, auth);

    // creates a new e-mail message
    Message msg = new MimeMessage(session);

    msg.setFrom(new InternetAddress(userName));
    InternetAddress[] toAddresses = { new InternetAddress(toAddress) };
    msg.setRecipients(Message.RecipientType.TO, toAddresses);
    msg.setSubject(subject);
    msg.setSentDate(new Date());

    // creates message part
    MimeBodyPart messageBodyPart = new MimeBodyPart();
    messageBodyPart.setContent(message, "text/html");

    // creates multi-part
    Multipart multipart = new MimeMultipart();
    multipart.addBodyPart(messageBodyPart);

    // adds attachments
    if (attachFiles != null && attachFiles.length > 0) {
      for (String filePath : attachFiles) {
        MimeBodyPart attachPart = new MimeBodyPart();

        try {
          attachPart.attachFile(filePath);
        } catch (IOException ex) {
          ex.printStackTrace();
        }

        multipart.addBodyPart(attachPart);
      }
    }
    String attachment ="";
    if(attachFiles == null) {
      attachment = "bez zalacznika";

    }
    else
    {
      attachment = "załącznik";
    }

    if(!attachment.equals("")){

    file.lastActivityCreateFile(toAddress,subject,message,attachment,new Date(),0,false);}


    // sets the multi-part as e-mail's content
    msg.setContent(multipart);

    // sends the e-mail
    Transport.send(msg);

  }
}
