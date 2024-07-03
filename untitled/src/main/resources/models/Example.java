package main.resources.models;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import pl.smsapi.OAuthClient;
import pl.smsapi.api.SmsFactory;
import pl.smsapi.api.action.sms.SMSSend;
import pl.smsapi.api.response.MessageResponse;
import pl.smsapi.api.response.StatusResponse;
import pl.smsapi.exception.SmsapiException;
import pl.smsapi.proxy.ProxyNative;

import java.util.Optional;

public class Example {



  public static void main(String[] args) {
    try {

      //paste code heres !!!  !!!  !!! !!!!ggg
      SmsFactory smsApi = new SmsFactory(client, proxy);
      System.out.println("przed wysylkom");
      SMSSend action = smsApi.actionSend()
          .setTo("")
          .setText("test");
      System.out.println("po wysylce");
      StatusResponse result = action.execute();

      Optional<MessageResponse> status = result.getList().stream().findFirst();
      if (status.isEmpty()) {
        throw new RuntimeException();
      }

      System.out.println("Phone number: " + status.get().getNumber());
      System.out.println("Shipment id: " + status.get().getId());
      System.out.println("Shipment status: " + status.get().getStatus());

    } catch (SmsapiException e) {
      System.out.println("Exception: " + e.getMessage());
    }
  }
}