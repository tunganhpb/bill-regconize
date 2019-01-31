package com.pmone.demo.rest.utils;

import com.pmone.demo.calculate.BoundingBox;
import com.pmone.demo.rest.model.Bill;
import com.pmone.demo.rest.model.Item;

import java.util.*;

public class ParseLidl {

  public static Bill parseLines(List<BoundingBox> boundingBoxes) {
    List<List<BoundingBox>> result = BoundingBoxUtils.sort(boundingBoxes);
    return gatherInfoLidl(result);
  }

  //LIDL
  private static Bill gatherInfoLidl(List<List<BoundingBox>> result) {
    Bill bill = new Bill();
    List<Item> items = new ArrayList<>();
    BoundingBox eur = null;
    Item lastItem = null;
    for (int i = 0; i < result.size(); i++) {
      List<BoundingBox> bb = result.get(i);
      if (i == 0 && bb.size() == 1) {
//        bill.setSuperMarket(bb.get(0).getText());
//        lastItem = null;
      } else if (endOfBill(bb)) {
        if (bb.get(0).getText().equalsIgnoreCase("zu zahlen")) {
          bill.setSum(bb.get(1).getText());
        } else {
          bill.setSum(bb.get(0).getText());
        }
        break;
      } else if (bb.size() == 2) {
        lastItem = extractItem(bb, eur, lastItem);
        if (lastItem != null) {
          items.add(lastItem);
        }
      } else if (bb.size() == 1 && bb.get(0).getText().equals("EUR")) {
        eur = bb.get(0);
        lastItem = null;
      } else if (bb.size() == 1) {
        if (lastItem != null) {
          lastItem.setDescription((lastItem.getDescription() == null ? "" : lastItem.getDescription() + "\n") + bb.get(0).getText());
        }
      } else {
        bb.forEach(boundingBox -> System.out.println(boundingBox.getText()));
      }
    }
    bill.setItems(items);
    return bill;
  }

  private static Item extractItem(List<BoundingBox> bb, BoundingBox eu, Item lastItem) {
    long count = bb.stream().filter(boundingBox -> {
      int euWidth = eu.getRightBot().getX() - eu.getLeftBot().getX();
      int boxRightBot = boundingBox.getRightBot().getX();
      int euRightBot = eu.getRightBot().getX();
      return euRightBot - euWidth < boxRightBot && boxRightBot < euRightBot + euWidth;
    }).count();

    if (count != 0) {
      return BoundingBoxUtils.extractItem(bb);
    } else {
      bb.sort(Comparator.comparingInt(o -> o.getRightBot().getX()));
      StringBuilder sb = new StringBuilder();
      bb.forEach(boundingBox -> {
        sb.append(boundingBox.getText());
      });
      if (lastItem != null) {
        lastItem.setDescription(sb.toString());
      }
    }
    return null;
  }

  private static boolean endOfBill(List<BoundingBox> bb) {
    return bb.stream().anyMatch(boundingBox -> boundingBox.getText().equalsIgnoreCase("zu zahlen"));
  }

}
