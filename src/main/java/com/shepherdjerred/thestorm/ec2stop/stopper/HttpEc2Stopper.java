package com.shepherdjerred.thestorm.ec2stop.stopper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@AllArgsConstructor
public class HttpEc2Stopper implements Ec2Stopper {
  private final String url;
  private final String secret;

  @Override
  public void stop() throws IOException {
    log.info("Trying to stop server");

    var url = new URL(this.url);
    var connection = url.openConnection();
    if (connection instanceof HttpURLConnection) {
      var body = String.format("{\"secret\": \"%s\"}", secret);
      var http = (HttpURLConnection) connection;
      http.setRequestMethod("POST");
      http.setDoOutput(true);
      http.setRequestProperty("Content-Type", "application/json; utf-8");
      http.setRequestProperty("Accept", "application/json");

      try (var outputStream = http.getOutputStream()) {
        var bodyBytes = body.getBytes();
        outputStream.write(bodyBytes);
      }

      try (var bufferedReader = new BufferedReader(new InputStreamReader(http.getInputStream(), StandardCharsets.UTF_8))) {
        var stringBuilder = new StringBuilder();
        String responseLine;
        while ((responseLine = bufferedReader.readLine()) != null) {
          stringBuilder.append(responseLine);
        }
        var response = stringBuilder.toString();
        log.info("API Response: {}", response);
      }
    } else {
      log.error("Connection invalid: {}", url);
    }

    log.info("Server stop request sent");
  }
}
