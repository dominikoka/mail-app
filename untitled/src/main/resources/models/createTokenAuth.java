package main.resources.models;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Random;

public class createTokenAuth {
  public String createToken() throws FileNotFoundException {
    var res = "";
    InitData data = new InitData();
    var id_pos = data.getId_pos();
    var secretP = data.getClient_secret();
    System.out.println("wywołano serwlet pau");
    tokenAuth token = new tokenAuth("client_credentials", id_pos, secretP);
    String requestBody = "grant_type=client_credentials&client_id=" + id_pos + "&client_secret=" + secretP;
    String tokenUrl = "https://secure.snd.payu.com/pl/standard/user/oauth/authorize";
    try {
      URL url = new URL(tokenUrl);
      HttpURLConnection connection = (HttpURLConnection) url.openConnection();
      connection.setRequestMethod("POST");
      connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
      connection.setDoOutput(true);
      try (OutputStream os = connection.getOutputStream()) {
        byte[] input = requestBody.getBytes("utf-8");
        os.write(input, 0, input.length);
      }
      int responseCode = connection.getResponseCode();
      System.out.println("Response Code: " + responseCode);
      BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
      String inputLine;
      StringBuffer responseBody = new StringBuffer();
      while ((inputLine = in.readLine()) != null) {
        responseBody.append(inputLine);
      }
      in.close();
      res = responseBody.toString();
    } catch (IOException e) {
      e.printStackTrace();
      ;
    }
    return res;
  }

  public String CreateOrder(String key, String itemName, String itemQuantity, String userEmail, String userPhone, String userName, String userSurname, String adressCity, String adressHouse, String adressZip, String itemPrice) {
    String tokenUrl = "https://secure.snd.payu.com/api/v2_1/orders";
    Random rd = new Random();
    var idOrder = rd.nextInt(89999)+10000;
    String price = String.valueOf((Integer.parseInt(itemPrice)*100));

    var res ="";
    String requestBody = "{\n" +
        "    \"notifyUrl\": \"https://your.eshop.com/notify\",\n" +
        "    \"customerIp\": \"127.0.0.1\",\n" +
        "    \"merchantPosId\": \"476462\",\n" +
        "    \"description\": \""+itemName+"\",\n" +
        "    \"currencyCode\": \"PLN\",\n" +
        "    \"totalAmount\": \""+price+"\",\n" +
        "    \"extOrderId\": \""+idOrder+"\",\n" +
        "    \"buyer\": {\n" +
        "        \"email\": \""+userEmail+"\",\n" +
        "        \"phone\": \""+userPhone+"\",\n" +
        "        \"firstName\": \""+userName+"\",\n" +
        "        \"lastName\": \""+userSurname+"\",\n" +
        "        \"language\": \"pl\"\n" +
        "    },\n" +
        "    \"products\": [\n" +
        "        {\n" +
        "            \"name\": \""+itemName+"\",\n" +
        "            \"unitPrice\": \""+itemPrice+"\",\n" +
        "            \"quantity\": \""+itemQuantity+"\"\n" +
        "        }\n" +
        "    ]\n" +
        "}";
//    char q = '"';
    var authorizationToken = "Bearer " + key;


    System.out.println(key);
    try {
      URL url = new URL(tokenUrl);
      HttpURLConnection connection = (HttpURLConnection) url.openConnection();
      connection.setRequestMethod("POST");
      connection.setRequestProperty("Content-Type", "application/json");
      connection.setRequestProperty("Authorization", authorizationToken);
      connection.setDoOutput(true);
      try (OutputStream os = connection.getOutputStream()) {
        byte[] input = requestBody.getBytes("utf-8");
        os.write(input, 0, input.length);
      }
      int responseCode = connection.getResponseCode();
      BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
      String inputLine;
//      StringBuffer responseBody = new StringBuffer();
//      while ((inputLine = in.readLine()) != null) {
//        responseBody.append(inputLine);
//      }
      in.close();
      res = connection.getURL().toString();
      //System.out.println(res);
    } catch (IOException e) {
      e.printStackTrace();
      ;
    }
    return res;
  }}

