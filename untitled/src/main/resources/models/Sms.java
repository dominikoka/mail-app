package main.resources.models;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

public class Sms {
  public String create(int code) throws FileNotFoundException {
    InitData data = new InitData();
    var secretP = data.getClient_secret();

    var authorizationToken = "Bearer SXRuosMqGeQBvjWLKbI4vbRenxbZLsH3WNAIZsOu";
    String requestBody = "from=Test&to="+"48730432932"+"&message=" + code+"&format=json";
    String tokenUrl = "https://api.smsapi.pl/sms.do";
    try {
      URL url = new URL(tokenUrl);
      HttpURLConnection connection = (HttpURLConnection) url.openConnection();
      connection.setRequestMethod("POST");
      connection.setRequestProperty("Authorization", authorizationToken);
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

    } catch (IOException e) {
      e.printStackTrace();
      ;
    }

    return String.valueOf(code);
  }

  public String check(String reqCode, int code) {

    int codeInput = Integer.parseInt(reqCode);
    return codeInput==code?"1":"0";
  }
}
