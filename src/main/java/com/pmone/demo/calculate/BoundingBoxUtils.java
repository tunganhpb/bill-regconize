package com.pmone.demo.calculate;

import org.junit.jupiter.api.Test;

public class BoundingBoxUtils {

  public static Double calculateInclined(BoundingBox boundingBox) {
    //vertical-coordinate
    int xLength = boundingBox.getRightTop().getY() - boundingBox.getRightBot().getY();
    int xInclined = boundingBox.getRightTop().getX() - boundingBox.getRightBot().getX();
    double vertical = Math.toDegrees(Math.atan(xInclined * 1.000000 / xLength));
    System.out.println(vertical);

    //horizontal-coordinate
    int yLength = boundingBox.getRightBot().getX() - boundingBox.getLeftBot().getX();
    int yInclinted = boundingBox.getRightBot().getY() - boundingBox.getLeftBot().getY();
    double horizontal = Math.toDegrees(Math.atan(yInclinted * 1.000000 / yLength));
    System.out.println(horizontal);

    if(Math.abs(vertical + horizontal) < 2) {
      return vertical;
    }

    return -1000.0;
  }

  @Test
  public void test() {
    calculateInclined(new BoundingBox( 2078,
        3024,
        2605,
        3024,
        2645,
        3024,
        2118,
        3024));
  }

}
