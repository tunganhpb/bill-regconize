package com.pmone.demo.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Item {
  private String name;
  private String description;
  private String price;

  @Override
  public String toString() {
    return "Item{" +
        "name='" + name + '\'' +
        ", description='" + description + '\'' +
        ", price='" + price + '\'' +
        '}';
  }
}
