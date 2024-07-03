package main.resources.models;

import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.mail.Flags;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.Session;
import javax.mail.Store;

/**
 * This program demonstrates how to remove e-mail messages on a mail server
 * using JavaMail API.
 * @author www.codejava.net
 *
 */
public class EmailMessageRemover {

  /**
   * Deletes all e-mail messages whose subject field contain
   * a string specified by 'subjectToDelete'
   * @param host
   * @param port
   * @param userName
   * @param password
   * @param subjectToDelete delete if the message's subject contains this value.
   */
  public void deleteMessages(String host, String port,
                             String userName, String password, List<Integer>readyToDelete) throws FileNotFoundException {
    Properties properties = new Properties();

    InitData removeData = new InitData();
    // server setting
    properties.put("mail.imap.host", host);
    properties.put("mail.imap.port", port);

    // SSL setting
    properties.setProperty("mail.imap.socketFactory.class",
        "javax.net.ssl.SSLSocketFactory");
    properties.setProperty("mail.imap.socketFactory.fallback", "false");
    properties.setProperty("mail.imap.socketFactory.port",
        String.valueOf(port));

    Session session = Session.getDefaultInstance(properties);

    try {
      // connects to the message store

      Store store = session.getStore("imap");
      store.connect(userName, password);

      // opens the inbox folder
      Folder folderInbox = store.getFolder("INBOX");
      folderInbox.open(Folder.READ_WRITE);

      // fetches new messages from server
      Message[] arrayMessages = folderInbox.getMessages();

//      for (int i = 0; i < arrayMessages.length; i++) {
//        Message message = arrayMessages[i];
//        String subject = message.getSubject();
//        if (subject.contains(subjectToDelete)) {
//          message.setFlag(Flags.Flag.DELETED, true);
//          System.out.println("Marked DELETE for message: " + subject);
//        }
//
//      }
      String from ="";
      String sbject="";
      String msg="";
      String attachment="";
      for(int i=0;i<readyToDelete.size();i++)
      {
      Message message = arrayMessages[readyToDelete.get(i)];
      sbject=message.getSubject();
      from = Arrays.toString(message.getFrom());
      message.setFlag(Flags.Flag.DELETED,true);
        if(from.contains("=?"))
        {
          String[] res = from.split(" ");
          from = res[res.length-1];
          from =from.replace("<","");
          from =from.replace(">","");


        }
      //


      }

      // expunges the folder to remove messages which are marked deleted
      boolean expunge = true;
      folderInbox.close(expunge);
      System.out.printf("cos tam zadzialalos");
      // another way:
      //folderInbox.expunge();
      //folderInbox.close(false);
      if(!from.isEmpty()){
      removeData.lastActivityCreateFile(from,sbject,"","",new Date(),1,false);}
      // disconnect
      store.close();
    } catch (NoSuchProviderException ex) {
      System.out.println("No provider.");
      ex.printStackTrace();
    } catch (MessagingException ex) {
      System.out.println("Could not connect to the message store.");
      ex.printStackTrace();
    }
  }

  /**
   * Runs this program to delete e-mail messages on a Gmail account
   * via IMAP protocol.
   */
}