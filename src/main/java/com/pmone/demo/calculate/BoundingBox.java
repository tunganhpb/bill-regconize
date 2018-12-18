package com.pmone.demo.calculate;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class BoundingBox {
  private CoordinatePoint leftTop;
  private CoordinatePoint rightTop;
  private CoordinatePoint rightBot;
  private CoordinatePoint leftBot;
  private String text;

  public BoundingBox(int x1, int y1, int x2, int y2, int x3, int y3, int x4, int y4) {
    this.leftTop = new CoordinatePoint(x1, y1);
    this.rightTop = new CoordinatePoint(x2, y2);
    this.rightBot = new CoordinatePoint(x3, y3);
    this.leftBot = new CoordinatePoint(x4, y4);
  }

  public BoundingBox(List<Integer> list, String text) {
    assert list.size() == 8;
    this.leftTop = new CoordinatePoint(list.get(0), list.get(1));
    this.rightTop = new CoordinatePoint(list.get(2), list.get(3));
    this.rightBot = new CoordinatePoint(list.get(4), list.get(5));
    this.leftBot = new CoordinatePoint(list.get(6), list.get(7));
    this.text = text;
  }

  @Override
  public String toString() {
    return "BoundingBox{" +
        "text='" + text + '\'' +
        '}';
  }
}
