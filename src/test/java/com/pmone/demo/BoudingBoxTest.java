package com.pmone.demo;

import com.pmone.demo.calculate.BoundingBox;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

import static com.pmone.demo.rest.utils.BoundingBoxUtils.calculateInclined;
import static com.pmone.demo.rest.utils.BoundingBoxUtils.rotateImageNoCrop;

/**
 * Created by Anh Pham on 2019-01-15.
 * email: tunganhpb@gmail.com
 */
public class BoudingBoxTest {

  @Test
  public void rotate() {
    try {
      rotateImageNoCrop(34, new File("/Users/tunganhpb/Dev/bill-regconize/src/main/resources/IMG_1825.JPG"));
//      rotateImageCrop(-8, new File("D:\\Dev\\textRecognize\\src\\main\\resources\\Lidlnghieng.JPG"));
    } catch (IOException e) {
      e.printStackTrace();
    }
  }


  @Test
  public void test() {
    calculateInclined(new BoundingBox(3778, 1129, 3758, 2007, 3677, 2005, 3696, 1127));
    calculateInclined(new BoundingBox(834, 244, 1998, 270, 1997, 355, 832, 329));
    calculateInclined(new BoundingBox(250, 1251, 1044, 707, 1085, 767, 291, 1311));
  }
}
