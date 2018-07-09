package nl.bldn.example.springboot.helloworld.payment;

import java.io.ByteArrayOutputStream;
import org.apache.commons.codec.binary.Base64;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.impl.client.HttpClients;

final class Connection {
  private Merchant merchant;

  Connection(Merchant merchant) {
    this.merchant = merchant;
  }

  String sendTransaction(String data) throws Exception {
    HttpClient httpClient = HttpClients.createDefault();
    // [Snippet] howToSetCredentials - start
    // set the API Username and Password in the header authentication field.
    HttpUriRequest request = new HttpPut(merchant.getGatewayUrl());
    request.addHeader("Authorization", Base64.encodeBase64String((merchant.getApiUsername() + ":" + merchant.getPassword()).getBytes()));
    request.addHeader("Content-Type", "application/json");
    ((HttpPut) request).setEntity(new ByteArrayEntity(data.getBytes()));

    String body = null;
    // [Snippet] executeSendTransaction - start
    // send the transaction
    HttpResponse response = httpClient.execute(request);
    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    response.getEntity().writeTo(outputStream);
    body = outputStream.toString();
    // [Snippet] executeSendTransaction - end
    return body;
  }

  String getTransaction() throws Exception {
    HttpClient httpClient = HttpClients.createMinimal();
    // set the API Username and Password in the header authentication field.

    HttpUriRequest request = new HttpPut(merchant.getGatewayUrl());
    request.addHeader("Authorization", Base64.encodeBase64String((merchant.getApiUsername() + ":" + merchant.getPassword()).getBytes()));
    request.addHeader("Content-Type", "application/json");

    String body = null;

    // send the transaction
    HttpResponse response = httpClient.execute(request);
    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    response.getEntity().writeTo(outputStream);
    body = outputStream.toString();

    return body;
  }

}

