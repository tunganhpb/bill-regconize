package com.pmone.demo;

import com.pmone.demo.calculate.BoundingBox;
import com.pmone.demo.calculate.BoundingBoxUtils;
import com.pmone.demo.calculate.ParseUtils;
import com.pmone.demo.model.Result;
import com.pmone.demo.rest.model.Bill;
import com.pmone.demo.rest.model.SupermarketEnum;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.FileEntity;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.codehaus.jackson.map.ObjectMapper;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Comparator;
import java.util.stream.Collectors;

@Service
public class MicrosoftAPICaller {

  public Result uploadPic(String imagePath, SupermarketEnum supermarket) {
    HttpClient httpclient = HttpClients.createDefault();

    URIBuilder builder;
    Result result = null;
    try {
      builder = new URIBuilder("https://northeurope.api.cognitive.microsoft.com/vision/v2.0/recognizeText");


      builder.setParameter("mode", "Printed");

      URI uri = builder.build();
      HttpPost request = new HttpPost(uri);
//      request.setHeader("Content-Type", "application/octet-stream");
      request.setHeader("Content-Type", "application/json");
      request.setHeader("Ocp-Apim-Subscription-Key", "81fe91d12ec8417f812c688e167683a2");

      // Request body
      JSONObject jsonObject = new JSONObject();
      jsonObject.put("url", imagePath);
      StringEntity reqEntity = new StringEntity(jsonObject.toString());

      request.setEntity(reqEntity);
//      request.setEntity(new FileEntity(new File(imagePath)));

      HttpResponse response = httpclient.execute(request);
      String operation = response.getHeaders("Operation-Location")[0].getElements()[0].getName();
      Thread.sleep(15000);
      result = getResult(operation);
      BoundingBox longestBouding = result.getRecognitionResult().getLines().stream().map(line -> new BoundingBox(line.getBoundingBox(), line.getText())).max(Comparator.comparingInt(o -> o.getText().length())).get();
      double average = BoundingBoxUtils.calculateInclined(longestBouding);
      System.out.println("average inclined: " + average);

      if (Math.abs(average) > 3) {
        String path = BoundingBoxUtils.rotateImageNoCrop(average, new File(imagePath));
        return uploadPic(path, supermarket);
      }

    } catch (URISyntaxException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    } catch (InterruptedException e) {
      e.printStackTrace();
    } catch (JSONException e) {
      e.printStackTrace();
    }

    return result;
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

  @Test
  public void test() {
    Result result = uploadPic("D:\\Dev\\textRecognize\\src\\main\\resources\\IMG_1838.JPG", SupermarketEnum.Lidl);
    Bill bill = ParseUtils.parseLines(result.getRecognitionResult().getLines().stream().map(line -> new BoundingBox(line.getBoundingBox(), line.getText())).collect(Collectors.toList()));
    System.out.println(bill);
    System.out.println("Done");
  }


}
