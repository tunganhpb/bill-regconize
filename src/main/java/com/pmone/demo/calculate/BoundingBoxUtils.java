package com.pmone.demo.calculate;

import org.junit.jupiter.api.Test;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;
import java.io.File;
import java.io.IOException;

public class BoundingBoxUtils {

  public static Double calculateInclined(BoundingBox boundingBox) {
    //vertical-coordinate
    int xLength = boundingBox.getRightTop().getY() - boundingBox.getRightBot().getY();
    int xInclined = boundingBox.getRightTop().getX() - boundingBox.getRightBot().getX();
    double vertical = Math.toDegrees(Math.atan(xInclined * 1.000000 / xLength));

    //horizontal-coordinate
    int yLength = boundingBox.getRightBot().getX() - boundingBox.getLeftBot().getX();
    int yInclinted = boundingBox.getRightBot().getY() - boundingBox.getLeftBot().getY();
    double horizontal = Math.toDegrees(Math.atan(yInclinted * 1.000000 / yLength));

    if (Math.abs(vertical + horizontal) < 2) {
      return vertical;
    }

    return -1000.0;
  }

  public static String rotateImageNoCrop(double degree, File file) throws IOException {
    BufferedImage originalImage = ImageIO.read(file);
    int w = originalImage.getWidth();
    int h = originalImage.getHeight();
    double toRad = Math.toRadians(degree + 90);
    int hPrime = (int) (w * Math.abs(Math.sin(toRad)) + h * Math.abs(Math.cos(toRad)));
    int wPrime = (int) (h * Math.abs(Math.sin(toRad)) + w * Math.abs(Math.cos(toRad)));

    if (hPrime > 4150) {
      double zoomLevel = hPrime / 4150.0;
      hPrime = (int) (hPrime / zoomLevel);
      wPrime = (int) (wPrime / zoomLevel);
    }

    BufferedImage rotatedImage = new BufferedImage(wPrime, hPrime, BufferedImage.TYPE_INT_RGB);
    Graphics2D g = rotatedImage.createGraphics();
    g.setColor(Color.BLACK);
    g.fillRect(0, 0, wPrime, hPrime);  // fill entire area
    g.translate(wPrime / 2, hPrime / 2);
    g.rotate(toRad);
    g.translate(-w / 2, -h / 2);
    g.drawImage(originalImage, 0, 0, null);
    g.dispose();  // release used resources before g is garbage-collected
    String pathname = file.getParent() + "\\" + "rotated_" + file.getName();
    ImageIO.write(rotatedImage, "jpg", new File(pathname));
    return pathname;
  }

  public static String rotateImageCrop(double degree, File file) throws IOException {
    Double d = degree;
    System.out.println("rotate: " + degree);
    float radianAngle = (float) Math.toRadians(270 - d.intValue());
    float sin = (float) Math.sin(radianAngle);
    float cos = (float) Math.cos(radianAngle);

    BufferedImage pic1 = ImageIO.read(file);

    int width = pic1.getWidth(null);
    int height = pic1.getHeight(null);

    double x0 = 0.5 * (width - 1);     // point to rotate about
    double y0 = 0.5 * (height - 1);     // center of image

    WritableRaster inRaster = pic1.getRaster();
    int type = pic1.getType() == 0 ? BufferedImage.TYPE_INT_ARGB : pic1.getType();
    BufferedImage pic2 = new BufferedImage(width, height, type);
    WritableRaster outRaster = pic2.getRaster();
    int[] pixel = new int[3];

    // rotation
    for (int x = 0; x < width; x++) {
      for (int y = 0; y < height; y++) {
        double a = x - x0;
        double b = y - y0;
        int xx = (int) (+a * cos - b * sin + x0);
        int yy = (int) (+a * sin + b * cos + y0);

        if (xx >= 0 && xx < width && yy >= 0 && yy < height) {
          outRaster.setPixel(x, y, inRaster.getPixel(xx, yy, pixel));
        }
      }
    }
    String pathname = file.getParent() + "\\" + "rotated_cop_" + file.getName();
    ImageIO.write(pic2, "jpg", new File(pathname));
    return pathname;
  }

  @Test
  public void rotate() {
    try {
      rotateImageNoCrop(-8, new File("D:\\Dev\\textRecognize\\src\\main\\resources\\Lidlnghieng.JPG"));
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
