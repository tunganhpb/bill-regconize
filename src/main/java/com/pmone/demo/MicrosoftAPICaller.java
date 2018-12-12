package com.pmone.demo;

import com.pmone.demo.calculate.BoundingBox;
import com.pmone.demo.calculate.BoundingBoxUtils;
import com.pmone.demo.model.Result;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.FileEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.codehaus.jackson.map.ObjectMapper;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.net.URI;
import java.util.stream.Stream;

public class MicrosoftAPICaller {

  @Test
  public void uploadPic() {
    HttpClient httpclient = HttpClients.createDefault();

    try {
      URIBuilder builder = new URIBuilder("https://northeurope.api.cognitive.microsoft.com/vision/v2.0/recognizeText");

      builder.setParameter("mode", "Printed");

      URI uri = builder.build();
      HttpPost request = new HttpPost(uri);
      request.setHeader("Content-Type", "application/octet-stream");
      request.setHeader("Ocp-Apim-Subscription-Key", "81fe91d12ec8417f812c688e167683a2");

      // Request body
      request.setEntity(new FileEntity(new File("D:\\Dev\\textRecognize\\src\\main\\resources\\IMG_1825.JPG")));

      HttpResponse response = httpclient.execute(request);
      String operation = response.getHeaders("Operation-Location")[0].getElements()[0].getName();
      Thread.sleep(15000);
      Result result = getResult(operation);
      Stream<BoundingBox> boundingBoxStream = result.getRecognitionResult().getLines().stream().map(line -> new BoundingBox(line.getBoundingBox()));
      double average = boundingBoxStream.mapToDouble(BoundingBoxUtils::calculateInclined).filter(value -> value != -1000.0).average().getAsDouble();
      System.out.println("average inclined: " + average);

    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
  }

  private Result getResult(String url) {
    HttpClient httpclient = HttpClients.createDefault();

    try {
      URIBuilder builder = new URIBuilder(url);

      URI uri = builder.build();
      HttpGet request = new HttpGet(uri);
      request.setHeader("Ocp-Apim-Subscription-Key", "81fe91d12ec8417f812c688e167683a2");

      HttpResponse response = httpclient.execute(request);
      HttpEntity entity = response.getEntity();

      if (entity != null) {
        ObjectMapper mapper = new ObjectMapper();
        String content = EntityUtils.toString(entity);
        return mapper.readValue(content, Result.class);
      }
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
    return null;
  }


}
