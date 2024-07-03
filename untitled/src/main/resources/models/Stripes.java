package main.resources.models;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Customer;
import com.stripe.model.PaymentIntent;
import com.stripe.model.PaymentMethod;
import com.stripe.model.Token;
import com.stripe.param.CustomerCreateParams;
import com.stripe.param.CustomerRetrievePaymentMethodParams;
import com.stripe.param.PaymentIntentCreateParams;
import com.stripe.param.PaymentMethodCreateParams;

import java.util.HashMap;
import java.util.Map;

public class Stripes {
  public void ii() throws StripeException {
    Stripe.apiKey = "sk_test_51OlBOTJ8IOijh301pQETWIpYfA31PnX0I4f22JS3tWv51827PXUjwe0H9W7xM8bEoDHm2SNFVPS2ZQTO1E6wVHKm00RaUM1iFM";


//    CustomerCreateParams params =
//        CustomerCreateParams.builder()
//            .setName("Jenny Rosen")
//            .setEmail("jennyrosen@example.com")
//            .build();



//    PaymentMethodCreateParams card =
//        PaymentMethodCreateParams.builder()
//            .setType(PaymentMethodCreateParams.Type.CARD)
//            .setCard(
//                PaymentMethodCreateParams.Card.builder()
//                    .setNumber("4242424242424242")
//                    .setExpMonth(8L)
//                    .setExpYear(2026L)
//                    .setCvc("314")
//                    .build()
//            )
//            .build();
    //PaymentMethod paymentMethod = PaymentMethod.create(card);

//    Customer resource = Customer.retrieve("cus_PagvMId2qFwBga","pm_1OlWfZJ8IOijh301BGAC6t1c");


//    CustomerRetrievePaymentMethodParams params =
//        CustomerRetrievePaymentMethodParams.builder().build();
//    PaymentMethod paymentMethod = resource.retrievePaymentMethod(params);
    //System.out.println(paymentIntent);









  }
}
