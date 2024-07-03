package main.resources.models;

import jakarta.servlet.ServletContextListener;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

public class InitData implements ServletContextListener {

  String user = "pass1234.txt";
  String activityFileName = "activity.txt";
  String items = "items.txt";
  String discountCode = "aezakmi";
  String id_pos = "476462";
  String client_secret = "0d717e5235479761180b7dc93af12461";
  int smsCode =0;


  public InitData() throws FileNotFoundException {
  }

//  public void initData() throws FileNotFoundException {
//
//    PrintWriter passwords = new PrintWriter(this.user);
//    passwords.println("0");
//    passwords.println("0");
//
//  }
  public int getSmsCode()
  {
    Random rd = new Random();
    int res = rd.nextInt(9000)+1000;
    return res;
  }
  public String getClient_secret(){return  client_secret;}
  public String getId_pos(){return id_pos;}
  public String getDiscountCode()
  {
    return discountCode;
  }

  public void createItem(String itemName, String description, Integer quantity,Integer uniqueKey) throws FileNotFoundException {
    PrintWriter item = new PrintWriter(new FileOutputStream(new File(this.items),true));
    item.println(uniqueKey);
    item.println(quantity);
    item.println("name");
    item.println(itemName);
    item.println("desc");
    item.println(description);
    item.println("+++");
    item.close();
  }
  public List<List<String>> itemReader() throws IOException {
    List<List<String>> items = new ArrayList<>();
    List<String> item = new ArrayList<>();
    Scanner in = new Scanner(this.items);
    List<String> allLines = Files.readAllLines(Paths.get(this.items));
    for(int i=0;i<allLines.size();i++)
    {
      if(i%4==0)
      {
        item.add(allLines.get(i));
      }
      else if(i%4==1)
      {
        item.add(allLines.get(i));
      }
      else if(i%4==2)
      {
        item.add(allLines.get(i));
      }
      else
      {
        item.add(allLines.get(i));
        items.add(item);
        item = new ArrayList<>();
      }
    }
    return items;
  }
  public void createFileUser(String uname, String password) throws FileNotFoundException {
    PrintWriter passwords = new PrintWriter(this.user);
    passwords.println(uname);
    passwords.println(password);
    passwords.close();
  }
  public void clearFileUser() throws FileNotFoundException {
    PrintWriter passwords = new PrintWriter(this.user);
    passwords.println("-1");
    passwords.println("-1");
    passwords.close();
  }
  public List<String> readFileUser() throws FileNotFoundException {
    List<String> readFiles = new ArrayList<>();
    Scanner in = new Scanner(new File(this.user));
    String uname = in.nextLine();
    readFiles.add(uname);
    String psw = in.nextLine();
    readFiles.add(psw);
    return readFiles;
  }
  DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
  //String actuallDateString = new Date());
  public void lastActivityCreateFile(String toOrFrom, String sbject, String msg, String attachment, Date activityData, Integer typeOfActivity,boolean isHtmlFormat) throws FileNotFoundException {
      // activity 0 - sent email
      // activity 1 - delete email
      // activity 2 - read email
      PrintWriter activity = new PrintWriter(new FileOutputStream(new File(this.activityFileName),true));
      activity.println("typeOfActivity"+typeOfActivity);
      activity.println("toOrFrom");
      activity.println(toOrFrom);
      activity.println("sbject");
      activity.println(sbject);
      activity.println("msg");
      activity.println(msg);
      activity.println("attachment");
      activity.println(attachment);
      activity.println("activityData");
      activity.println(dateFormat.format(activityData));
      activity.println("htmlFormat");
      activity.println(isHtmlFormat);
      activity.println("++");
      activity.close();
  }
  String sent = "SENT MESSAGE";
  public List<List<String>> lastActivityReader() throws IOException {
    List<List<String>> activities = new ArrayList<List<String>>();
    List<String> activity = new ArrayList<>();

    List<String> allLines = Files.readAllLines(Paths.get(this.activityFileName));
    System.out.println("aa");
    int i = 0;
    //while (i<allLines.size())
    {
      //String line = allLines.get(i);
      while ( i<allLines.size())
      {
        while(!allLines.get(i).equals("++")){
        String nameOfActivity = allLines.get(i);
        String typeOfActivity = allLines.get(i).equals("typeOfActivity0")?"SENT MESSAGE": nameOfActivity;
        typeOfActivity = allLines.get(i).equals("typeOfActivity1")?"DELETE MESSAGE": typeOfActivity;
        typeOfActivity = allLines.get(i).equals("typeOfActivity2")?"READ MESSAGE": typeOfActivity;
        activity.add(typeOfActivity);
        i=i+2;
        activity.add(allLines.get(i));
        i=i+2;
        activity.add(allLines.get(i));
        i=i+2;
        //int msgCounter = 6;
        //int finishMsg=0;
        String msge = "";
        while(!allLines.get(i).equals("attachment"))
        {
          msge+=allLines.get(i)+" ";
          i++;
        }
        activity.add(msge);
        i=i+1;
        activity.add(allLines.get(i));
        i=i+2;
        activity.add(allLines.get(i));
        i=i+2;
        activity.add(allLines.get(i));
        i++;
          activities.add(activity);
          activity = new ArrayList<>();
        }
        i++;

      }

      i++;
    }

    return activities;
  }
}
