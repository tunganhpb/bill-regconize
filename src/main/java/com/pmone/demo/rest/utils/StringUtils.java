package com.pmone.demo.rest.utils;

public class StringUtils {

  public static Double par(String a) {
    String[] split = a.split(",");
    Double d = Double.valueOf(split[0].replaceAll(" ", ""));

    Double e = Double.valueOf("0." + split[1].replaceAll(" ", ""));

    return d + e;
  }
}
