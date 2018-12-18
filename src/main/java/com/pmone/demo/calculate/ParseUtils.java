package com.pmone.demo.calculate;

import com.pmone.demo.model.Bill;
import com.pmone.demo.model.Item;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

public class ParseUtils {

  String content = "{\"status\":\"Succeeded\",\"recognitionResult\":{\"lines\":[{\"boundingBox\":[1218,130,1587,131,1586,253,1217,252],\"text\":\"LiDL\",\"words\":[{\"boundingBox\":[1240,139,1536,134,1534,253,1245,250],\"text\":\"LiDL\"}]},{\"boundingBox\":[834,244,1998,270,1997,355,832,329],\"text\":\"Mollendorffstr. 75a/Storkower Str .\",\"words\":[{\"boundingBox\":[834,252,1374,257,1376,342,836,331],\"text\":\"Mollendorffstr.\",\"Confidence\":\"Low\"},{\"boundingBox\":[1389,257,1835,270,1839,353,1392,342],\"text\":\"75a/Storkower\"},{\"boundingBox\":[1856,270,1970,275,1974,356,1859,353],\"text\":\"Str\"},{\"boundingBox\":[1955,274,2007,276,2010,357,1958,356],\"text\":\".\"}]},{\"boundingBox\":[1212,341,1660,352,1658,436,1210,425],\"text\":\"10369 Berlin\",\"words\":[{\"boundingBox\":[1222,351,1404,348,1405,429,1225,428],\"text\":\"10369\"},{\"boundingBox\":[1420,348,1632,360,1630,434,1420,429],\"text\":\"Berlin\"}]},{\"boundingBox\":[1978,452,2109,446,2111,515,1981,520],\"text\":\"EUR\",\"words\":[{\"boundingBox\":[1986,451,2097,446,2099,515,1989,520],\"text\":\"EUR\"}]},{\"boundingBox\":[679,519,936,523,935,594,679,591],\"text\":\"Bananen\",\"words\":[{\"boundingBox\":[677,521,921,528,920,593,670,592],\"text\":\"Bananen\"}]},{\"boundingBox\":[701,591,1580,604,1578,693,699,679],\"text\":\"0,962 kg x 1,09 EUR/kg\",\"words\":[{\"boundingBox\":[743,601,931,598,931,684,744,680],\"text\":\"0,962\",\"Confidence\":\"Low\"},{\"boundingBox\":[941,598,1033,598,1032,685,941,684],\"text\":\"kg\"},{\"boundingBox\":[1048,598,1099,598,1098,686,1048,686],\"text\":\"x\"},{\"boundingBox\":[1119,598,1272,601,1270,689,1118,687],\"text\":\"1,09\",\"Confidence\":\"Low\"},{\"boundingBox\":[1353,604,1572,613,1568,695,1351,691],\"text\":\"EUR/kg\"}]},{\"boundingBox\":[1891,531,2101,523,2104,600,1894,609],\"text\":\"1,05 A\",\"words\":[{\"boundingBox\":[1893,537,2034,528,2035,602,1900,605],\"text\":\"1,05\",\"Confidence\":\"Low\"},{\"boundingBox\":[2057,527,2101,524,2098,603,2056,603],\"text\":\"A\"}]},{\"boundingBox\":[682,683,1191,689,1190,775,681,769],\"text\":\"Tara 0,002 kg\",\"words\":[{\"boundingBox\":[677,695,828,688,828,764,680,769],\"text\":\"Tara\"},{\"boundingBox\":[882,687,1067,690,1063,768,881,763],\"text\":\"0,002\"},{\"boundingBox\":[1082,690,1169,696,1164,775,1078,769],\"text\":\"kg\"}]},{\"boundingBox\":[679,769,1197,773,1196,851,678,847],\"text\":\"Mini Windbeutel\",\"words\":[{\"boundingBox\":[675,771,835,772,834,850,676,847],\"text\":\"Mini\"},{\"boundingBox\":[845,772,1204,774,1200,852,844,850],\"text\":\"Windbeutel\",\"Confidence\":\"Low\"}]},{\"boundingBox\":[1890,775,2103,779,2101,847,1888,843],\"text\":\"0,99 A\",\"words\":[{\"boundingBox\":[1891,777,2040,779,2040,846,1896,846],\"text\":\"0,99\",\"Confidence\":\"Low\"},{\"boundingBox\":[2058,779,2104,779,2101,847,2057,846],\"text\":\"A\"}]},{\"boundingBox\":[680,853,1207,855,1206,931,679,929],\"text\":\"Joghurt Vanille\",\"words\":[{\"boundingBox\":[685,854,932,856,934,931,691,931],\"text\":\"Joghurt\"},{\"boundingBox\":[947,856,1204,857,1201,931,949,931],\"text\":\"Vanille\"}]},{\"boundingBox\":[1897,856,2064,849,2064,926,1902,932],\"text\":\"0,35\",\"words\":[{\"boundingBox\":[1886,855,2040,849,2043,926,1889,932],\"text\":\"0,35\"}]},{\"boundingBox\":[689,934,1376,935,1375,1016,688,1015],\"text\":\"Joghurt Waldfr . 250g\",\"words\":[{\"boundingBox\":[689,940,930,936,932,1017,693,1017],\"text\":\"Joghurt\"},{\"boundingBox\":[945,936,1166,936,1167,1017,947,1017],\"text\":\"Waldfr\"},{\"boundingBox\":[1156,936,1206,937,1206,1017,1157,1017],\"text\":\".\"},{\"boundingBox\":[1221,937,1372,941,1371,1018,1221,1017],\"text\":\"250g\"}]},{\"boundingBox\":[1895,933,2111,936,2109,1015,1893,1011],\"text\":\"0,35 A\",\"words\":[{\"boundingBox\":[1891,938,2042,936,2039,1012,1896,1010],\"text\":\"0,35\",\"Confidence\":\"Low\"},{\"boundingBox\":[2061,937,2108,938,2102,1017,2057,1013],\"text\":\"A\"}]},{\"boundingBox\":[686,1020,1347,1018,1347,1099,687,1101],\"text\":\"Schweinehackfleisch\",\"words\":[{\"boundingBox\":[693,1024,1335,1019,1333,1101,693,1099],\"text\":\"Schweinehackfleisch\",\"Confidence\":\"Low\"}]},{\"boundingBox\":[1893,1017,2112,1020,2111,1095,1892,1092],\"text\":\"1,99 A\",\"words\":[{\"boundingBox\":[1893,1022,2040,1021,2039,1093,1898,1092],\"text\":\"1,99\",\"Confidence\":\"Low\"},{\"boundingBox\":[2059,1021,2105,1020,2101,1096,2057,1094],\"text\":\"A\"}]},{\"boundingBox\":[688,1100,1335,1104,1334,1188,687,1184],\"text\":\"ASC Lachsfilet m.H.\",\"words\":[{\"boundingBox\":[683,1105,806,1106,808,1184,686,1183],\"text\":\"ASC\"},{\"boundingBox\":[816,1106,1174,1107,1174,1188,818,1184],\"text\":\"Lachsfilet\"},{\"boundingBox\":[1185,1107,1343,1105,1341,1190,1184,1188],\"text\":\"m.H.\",\"Confidence\":\"Low\"}]},{\"boundingBox\":[1903,1105,2100,1101,2102,1170,1904,1173],\"text\":\"4 ,79 A\",\"words\":[{\"boundingBox\":[1895,1105,1937,1105,1939,1171,1897,1170],\"text\":\"4\"},{\"boundingBox\":[1929,1105,2040,1104,2037,1172,1931,1171],\"text\":\",79\",\"Confidence\":\"Low\"},{\"boundingBox\":[2061,1103,2103,1102,2099,1169,2058,1171],\"text\":\"A\"}]},{\"boundingBox\":[684,1278,1305,1266,1308,1410,687,1422],\"text\":\"zu zahlen\",\"words\":[{\"boundingBox\":[681,1280,840,1276,837,1419,678,1423],\"text\":\"zu\"},{\"boundingBox\":[887,1275,1300,1270,1297,1414,884,1418],\"text\":\"zahlen\",\"Confidence\":\"Low\"}]},{\"boundingBox\":[1822,1267,2098,1268,2107,1399,1818,1398],\"text\":\"9,52\",\"words\":[{\"boundingBox\":[1818,1267,2106,1268,2105,1399,1817,1398],\"text\":\"9,52\",\"Confidence\":\"Low\"}]},{\"boundingBox\":[671,1486,866,1487,866,1556,675,1557],\"text\":\"Karte\",\"words\":[{\"boundingBox\":[685,1486,869,1485,869,1556,685,1557],\"text\":\"Karte\"}]},{\"boundingBox\":[1898,1485,2041,1485,2039,1547,1896,1542],\"text\":\"9,52\",\"words\":[{\"boundingBox\":[1896,1482,2037,1485,2036,1547,1895,1544],\"text\":\"9,52\",\"Confidence\":\"Low\"}]},{\"boundingBox\":[724,1651,911,1650,911,1710,725,1712],\"text\":\"MWST\",\"words\":[{\"boundingBox\":[718,1653,865,1652,866,1712,721,1713],\"text\":\"MWST\",\"Confidence\":\"Low\"}]},{\"boundingBox\":[1102,1648,1356,1651,1355,1719,1101,1716],\"text\":\"MWST +\",\"words\":[{\"boundingBox\":[1089,1652,1241,1651,1244,1720,1093,1718],\"text\":\"MWST\"},{\"boundingBox\":[1293,1653,1337,1654,1338,1720,1295,1720],\"text\":\"+\"}]},{\"boundingBox\":[1408,1650,1630,1645,1632,1720,1410,1726],\"text\":\"Netto\",\"words\":[{\"boundingBox\":[1427,1654,1608,1648,1602,1722,1427,1722],\"text\":\"Netto\"}]},{\"boundingBox\":[1894,1644,2111,1648,2110,1716,1893,1712],\"text\":\"Brutto\",\"words\":[{\"boundingBox\":[1894,1650,2105,1649,2108,1717,1898,1712],\"text\":\"Brutto\"}]},{\"boundingBox\":[699,1734,906,1722,910,1792,703,1804],\"text\":\"A 7%\",\"words\":[{\"boundingBox\":[688,1740,729,1736,734,1800,695,1802],\"text\":\"A\"},{\"boundingBox\":[798,1730,895,1723,895,1795,801,1797],\"text\":\"7%\",\"Confidence\":\"Low\"}]},{\"boundingBox\":[1120,1731,1278,1733,1280,1800,1124,1801],\"text\":\"0,62\",\"words\":[{\"boundingBox\":[1129,1730,1274,1731,1273,1801,1129,1800],\"text\":\"0,62\",\"Confidence\":\"Low\"}]},{\"boundingBox\":[1489,1732,1635,1726,1637,1792,1488,1798],\"text\":\"8,90\",\"words\":[{\"boundingBox\":[1494,1731,1639,1725,1642,1791,1497,1797],\"text\":\"8,90\",\"Confidence\":\"Low\"}]},{\"boundingBox\":[1908,1728,2044,1730,2042,1786,1908,1786],\"text\":\"9.52\",\"words\":[{\"boundingBox\":[1896,1728,2035,1729,2035,1787,1896,1786],\"text\":\"9.52\",\"Confidence\":\"Low\"}]},{\"boundingBox\":[689,1900,864,1901,863,1961,691,1961],\"text\":\"Summe\",\"words\":[{\"boundingBox\":[693,1899,863,1900,863,1961,692,1960],\"text\":\"Summe\"}]},{\"boundingBox\":[1133,1894,1275,1894,1277,1963,1138,1961],\"text\":\"0,62\",\"words\":[{\"boundingBox\":[1128,1893,1273,1894,1273,1962,1128,1961],\"text\":\"0,62\",\"Confidence\":\"Low\"}]},{\"boundingBox\":[1493,1896,1639,1895,1640,1959,1499,1961],\"text\":\"8,90\",\"words\":[{\"boundingBox\":[1492,1895,1639,1894,1640,1959,1493,1960],\"text\":\"8,90\",\"Confidence\":\"Low\"}]},{\"boundingBox\":[1888,1891,2042,1892,2041,1961,1892,1956],\"text\":\"9,52\",\"words\":[{\"boundingBox\":[1892,1888,2039,1891,2038,1960,1890,1957],\"text\":\"9,52\",\"Confidence\":\"Low\"}]},{\"boundingBox\":[691,2408,845,2411,843,2472,690,2467],\"text\":\"4986\",\"words\":[{\"boundingBox\":[690,2406,829,2410,827,2471,689,2467],\"text\":\"4986\"}]},{\"boundingBox\":[916,2400,1264,2401,1263,2473,915,2472],\"text\":\"769564/02\",\"words\":[{\"boundingBox\":[930,2404,1243,2404,1242,2474,933,2474],\"text\":\"769564/02\"}]},{\"boundingBox\":[1591,2395,2121,2399,2120,2479,1590,2474],\"text\":\"07 . 12. 18 16:22\",\"words\":[{\"boundingBox\":[1625,2401,1712,2399,1715,2474,1631,2474],\"text\":\"07\"},{\"boundingBox\":[1692,2399,1740,2399,1744,2475,1697,2474],\"text\":\".\"},{\"boundingBox\":[1731,2399,1841,2398,1843,2475,1734,2474],\"text\":\"12.\",\"Confidence\":\"Low\"},{\"boundingBox\":[1831,2398,1903,2398,1904,2476,1833,2475],\"text\":\"18\"},{\"boundingBox\":[1932,2399,2113,2402,2111,2481,1932,2477],\"text\":\"16:22\"}]},{\"boundingBox\":[981,2482,1764,2474,1765,2551,982,2559],\"text\":\"UST-ID-NR: DE250063577\",\"words\":[{\"boundingBox\":[982,2487,1344,2479,1344,2557,987,2559],\"text\":\"UST-ID-NR:\",\"Confidence\":\"Low\"},{\"boundingBox\":[1358,2479,1743,2479,1739,2552,1358,2557],\"text\":\"DE250063577\"}]},{\"boundingBox\":[958,2649,1732,2634,1734,2718,960,2734],\"text\":\"-K-U-N-D-E-N-B-E-L-E-G-\",\"words\":[{\"boundingBox\":[955,2659,1743,2643,1746,2720,958,2736],\"text\":\"-K-U-N-D-E-N-B-E-L-E-G-\",\"Confidence\":\"Low\"}]},{\"boundingBox\":[959,2740,1493,2731,1495,2806,961,2815],\"text\":\"Terminal-ID :\",\"words\":[{\"boundingBox\":[955,2746,1338,2736,1338,2811,961,2816],\"text\":\"Terminal-ID\",\"Confidence\":\"Low\"},{\"boundingBox\":[1366,2736,1412,2736,1411,2809,1366,2810],\"text\":\":\"}]},{\"boundingBox\":[1463,2730,1780,2725,1782,2807,1465,2813],\"text\":\"60102072\",\"words\":[{\"boundingBox\":[1493,2736,1780,2729,1773,2810,1496,2810],\"text\":\"60102072\"}]},{\"boundingBox\":[957,2825,1414,2809,1417,2885,959,2901],\"text\":\"TA-Nr 954841\",\"words\":[{\"boundingBox\":[952,2825,1134,2819,1135,2892,956,2902],\"text\":\"TA-Nr\"},{\"boundingBox\":[1160,2818,1382,2813,1379,2888,1160,2891],\"text\":\"954841\"}]},{\"boundingBox\":[1471,2812,1771,2813,1770,2891,1470,2889],\"text\":\"BNr 4627\",\"words\":[{\"boundingBox\":[1492,2816,1610,2814,1612,2889,1496,2891],\"text\":\"BNr\"},{\"boundingBox\":[1630,2814,1778,2817,1777,2891,1632,2889],\"text\":\"4627\"}]},{\"boundingBox\":[1194,2902,1674,2901,1675,2977,1194,2978],\"text\":\"Kartenzah lung\",\"words\":[{\"boundingBox\":[1215,2903,1539,2903,1540,2978,1219,2980],\"text\":\"Kartenzah\"},{\"boundingBox\":[1529,2903,1676,2906,1675,2976,1530,2978],\"text\":\"lung\"}]},{\"boundingBox\":[1331,2987,1658,2979,1660,3024,1333,3024],\"text\":\"girocard\",\"words\":[{\"boundingBox\":[1361,2988,1642,2981,1640,3024,1361,3024],\"text\":\"girocard\"}]},{\"boundingBox\":[1187,3024,1746,3024,1747,3024,1188,3024],\"text\":\"EUR 9 , 52\",\"words\":[{\"boundingBox\":[1201,3024,1373,3024,1374,3024,1205,3024],\"text\":\"EUR\"},{\"boundingBox\":[1474,3024,1518,3024,1518,3024,1474,3024],\"text\":\"9\"},{\"boundingBox\":[1548,3024,1592,3024,1592,3024,1548,3024],\"text\":\",\"},{\"boundingBox\":[1606,3024,1720,3024,1718,3024,1605,3024],\"text\":\"52\"}]},{\"boundingBox\":[961,3024,1116,3024,1116,3024,966,3024],\"text\":\"PAN\",\"words\":[{\"boundingBox\":[956,3024,1067,3024,1067,3024,956,3024],\"text\":\"PAN\"}]},{\"boundingBox\":[1092,3024,1781,3024,1781,3024,1093,3024],\"text\":\"###############1470\",\"words\":[{\"boundingBox\":[1125,3024,1776,3024,1769,3024,1125,3024],\"text\":\"###############1470\"}]},{\"boundingBox\":[1212,3024,1491,3024,1490,3024,1211,3024],\"text\":\"Karte 0\",\"words\":[{\"boundingBox\":[1221,3024,1402,3024,1403,3024,1224,3024],\"text\":\"Karte\"},{\"boundingBox\":[1429,3024,1473,3024,1473,3024,1429,3024],\"text\":\"0\"}]},{\"boundingBox\":[1221,3024,1476,3024,1476,3024,1222,3024],\"text\":\"EMV-AID\",\"words\":[{\"boundingBox\":[1227,3024,1471,3024,1470,3024,1231,3024],\"text\":\"EMV-AID\"}]},{\"boundingBox\":[1086,3024,1765,3024,1763,3024,1085,3024],\"text\":\"A0000003591010028001\",\"words\":[{\"boundingBox\":[1095,3024,1773,3024,1771,3024,1095,3024],\"text\":\"A0000003591010028001\"}]},{\"boundingBox\":[963,3024,1212,3024,1209,3024,960,3024],\"text\":\"VU-Nr\",\"words\":[{\"boundingBox\":[958,3024,1141,3024,1137,3024,959,3024],\"text\":\"VU-Nr\"}]},{\"boundingBox\":[1315,3024,1766,3024,1767,3024,1316,3024],\"text\":\"455600953168\",\"words\":[{\"boundingBox\":[1364,3024,1776,3024,1772,3024,1364,3024],\"text\":\"455600953168\"}]},{\"boundingBox\":[958,3024,1546,3024,1545,3024,957,3024],\"text\":\"Genehm i gungs-Nr\",\"words\":[{\"boundingBox\":[963,3024,1169,3024,1167,3024,964,3024],\"text\":\"Genehm\"},{\"boundingBox\":[1164,3024,1214,3024,1211,3024,1162,3024],\"text\":\"i\"},{\"boundingBox\":[1194,3024,1475,3024,1468,3024,1191,3024],\"text\":\"gungs-Nr\",\"Confidence\":\"Low\"}]},{\"boundingBox\":[1511,3024,1770,3024,1771,3024,1512,3024],\"text\":\"162256\",\"words\":[{\"boundingBox\":[1563,3024,1777,3024,1772,3024,1563,3024],\"text\":\"162256\"}]},{\"boundingBox\":[959,3024,1774,3024,1772,3024,958,3024],\"text\":\"Datum 07 . 12 . 18 16:22 Uhr\",\"words\":[{\"boundingBox\":[964,3024,1134,3024,1134,3024,965,3024],\"text\":\"Datum\"},{\"boundingBox\":[1161,3024,1244,3024,1243,3024,1161,3024],\"text\":\"07\"},{\"boundingBox\":[1235,3024,1281,3024,1280,3024,1234,3024],\"text\":\".\"},{\"boundingBox\":[1267,3024,1340,3024,1339,3024,1266,3024],\"text\":\"12\"},{\"boundingBox\":[1331,3024,1377,3024,1376,3024,1330,3024],\"text\":\".\"},{\"boundingBox\":[1363,3024,1437,3024,1435,3024,1362,3024],\"text\":\"18\"},{\"boundingBox\":[1464,3024,1639,3024,1636,3024,1463,3024],\"text\":\"16:22\"},{\"boundingBox\":[1657,3024,1772,3024,1768,3024,1654,3024],\"text\":\"Uhr\"}]},{\"boundingBox\":[1049,3024,1626,3024,1624,3024,1047,3024],\"text\":\"Zahlung erfolgt\",\"words\":[{\"boundingBox\":[1100,3024,1339,3024,1337,3024,1097,3024],\"text\":\"Zahlung\",\"Confidence\":\"Low\"},{\"boundingBox\":[1361,3024,1605,3024,1604,3024,1360,3024],\"text\":\"erfolgt\"}]},{\"boundingBox\":[966,3024,1744,3024,1742,3024,964,3024],\"text\":\"BITTE BELEG AUFBEWAHREN\",\"words\":[{\"boundingBox\":[961,3024,1142,3024,1140,3024,961,3024],\"text\":\"BITTE\"},{\"boundingBox\":[1157,3024,1342,3024,1339,3024,1155,3024],\"text\":\"BELEG\"},{\"boundingBox\":[1357,3024,1739,3024,1733,3024,1354,3024],\"text\":\"AUFBEWAHREN\"}]}]}}";

  public static Bill parseLines(List<BoundingBox> boundingBoxes) {
    boundingBoxes.sort(Comparator.comparingInt(o -> (o.getRightBot().getY() + o.getLeftBot().getY())));
    List<List<BoundingBox>> result = new ArrayList<>();
    List<BoundingBox> a = boundingBoxes;
    int i = 0;
    do {
      a = find(a, result);
    } while (!a.isEmpty());
    Bill bill = gatherInfo(result);
    return bill;
  }

  //LIDL
  public static Bill gatherInfo(List<List<BoundingBox>> result) {
    Bill bill = new Bill();
    List<Item> items = new ArrayList<>();
    for (int i = 0; i < result.size(); i++) {
      List<BoundingBox> bb = result.get(i);
      if (i == 0 && bb.size() == 1) {
        bill.setSuperMarket(bb.get(0).getText());
      } else if (endOfBill(bb)) {
        if (bb.get(0).getText().equalsIgnoreCase("zu zahlen")) {
          bill.setSum(bb.get(1).getText());
        } else {
          bill.setSum(bb.get(0).getText());
        }
        break;
      } else if (bb.size() == 2) {
        items.add(extractItem(bb));
      } else {
        bb.forEach(boundingBox -> System.out.println(boundingBox.getText()));
      }
    }
    bill.setItems(items);
    return bill;
  }

  private static Item extractItem(List<BoundingBox> bb) {
    BoundingBox b1 = bb.get(0);
    BoundingBox b2 = bb.get(1);
    Item item = new Item();
    if (b1.getLeftBot().getX() < b2.getLeftBot().getX()) {
      item.setName(b1.getText());
      item.setPrice(b2.getText());
    } else {
      item.setName(b2.getText());
      item.setPrice(b1.getText());
    }
    return item;
  }

  private static boolean endOfBill(List<BoundingBox> bb) {
    return bb.stream().anyMatch(boundingBox -> boundingBox.getText().equalsIgnoreCase("zu zahlen"));
  }

  private static List<BoundingBox> find(List<BoundingBox> boundingBoxes, List<List<BoundingBox>> result) {
    List<BoundingBox> boxes = new ArrayList<>();

    Iterator<BoundingBox> iterator = boundingBoxes.iterator();
    BoundingBox firstBox = iterator.next();
    List<BoundingBox> list = new ArrayList<>();
    list.add(firstBox);

    while (iterator.hasNext()) {
      BoundingBox comparedBox = iterator.next();
      if (compareLine(firstBox, comparedBox)) {
        list.add(comparedBox);
      } else {
        boxes.add(comparedBox);
      }
    }
    result.add(list);
    return boxes;
  }

  private static boolean compareLine(BoundingBox origin, BoundingBox compared) {
    int botOrigin = origin.getRightBot().getY();
    int topOrigin = origin.getRightTop().getY();
    int height = botOrigin - topOrigin;
    int topCompared = compared.getRightTop().getY();
    int botCompared = compared.getRightBot().getY();
    double v = 0.70;
    return (topOrigin - (height * v) < topCompared && topCompared < topOrigin + (height * v)) && (botOrigin - (height * v) < botCompared && botCompared < botOrigin + (height * v));
  }

}
