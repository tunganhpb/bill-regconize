package com.pmone.demo.rest.model;

import lombok.Getter;

public enum SupermarketEnum {

  Lidl("Lidl"),
  Netto("Netto"),
  Real("Real");

  @Getter
  private String name;

  SupermarketEnum(String name) {
    this.name = name;
  }
}
