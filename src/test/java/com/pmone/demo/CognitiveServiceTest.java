package com.pmone.demo;

import com.pmone.demo.calculate.BoundingBox;
import com.pmone.demo.rest.utils.ParseLidl;
import com.pmone.demo.model.Result;
import com.pmone.demo.rest.model.Bill;
import com.pmone.demo.rest.model.SupermarketEnum;
import com.pmone.demo.rest.service.MicrosoftAPICallerService;
import org.junit.jupiter.api.Test;

import java.util.stream.Collectors;

/**
 * Created by Anh Pham on 2019-01-13.
 * email: tunganhpb@gmail.com
 */


public class CognitiveServiceTest {

  @Test
  public void test() {
    MicrosoftAPICallerService microsoftAPICallerService = new MicrosoftAPICallerService();
//    Result result = microsoftAPICallerService.uploadPic("download/IMG_1838.JPG", SupermarketEnum.Lidl);
    Result result = microsoftAPICallerService.uploadPic("/Users/tunganhpb/Dev/bill-regconize/src/main/resources/IMG_1838.JPG", SupermarketEnum.Lidl);
//    Result result = microsoftAPICallerService.uploadPic("/Users/tunganhpb/Dev/bill-regconize/src/main/resources/IMG_1825.JPG", SupermarketEnum.Lidl);
    Bill bill = ParseLidl.parseLines(result.getRecognitionResult().getLines().stream().map(line -> new BoundingBox(line.getBoundingBox(), line.getText())).collect(Collectors.toList()));
    System.out.println(bill);
    System.out.println("Done");
  }
}
