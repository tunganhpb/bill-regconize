package com.pmone.demo.rest.utils;

import com.pmone.demo.calculate.BoundingBox;
import com.pmone.demo.rest.model.Bill;
import com.pmone.demo.rest.model.Item;

import java.util.ArrayList;
import java.util.List;

public class ParseReal {
  public static Bill parseLines(List<BoundingBox> boundingBoxes) {
    List<List<BoundingBox>> result = BoundingBoxUtils.sort(boundingBoxes);
    return gatherInfoReal(result);
  }

  private static Bill gatherInfoReal(List<List<BoundingBox>> result) {
    Bill bill = new Bill();
    List<Item> items = new ArrayList<>();
    BoundingBox address = null;
    Item lastItem = null;
    for (int i = 0; i < result.size(); i++) {
      List<BoundingBox> bb = result.get(i);
      if (bb.size() == 2) {
//        lastItem = extractItem(bb, eur, lastItem);
        if (lastItem != null) {
          items.add(lastItem);
        }
      }
    }
    return new Bill();
  }
}
