package com.pmone.demo.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Bill {
  private String superMarket;
  private List<Item> items;
  private String sum;

  @Override
  public String toString() {
    return "Bill{" +
        "superMarket='" + superMarket + '\'' +
        ", items=" + items +
        ", sum='" + sum + '\'' +
        '}';
  }
}
