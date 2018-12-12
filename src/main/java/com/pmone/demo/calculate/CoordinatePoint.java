package com.pmone.demo.calculate;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CoordinatePoint {
  private int x;
  private int y;

  public CoordinatePoint(int x, int y) {
    this.x = x;
    this.y = y;
  }
}
