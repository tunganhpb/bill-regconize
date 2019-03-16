package com.pmone.demo;

import com.pmone.demo.rest.model.Bill;
import com.pmone.demo.rest.model.Category;
import com.pmone.demo.rest.model.CategorySample;
import com.pmone.demo.rest.model.Item;
import com.pmone.demo.rest.repository.BillRepository;
import com.pmone.demo.rest.repository.CategoryRepository;
import com.pmone.demo.rest.repository.CategorySampleRepository;
import com.pmone.demo.rest.repository.ItemRepository;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@SpringBootApplication
public class DemoApplication {

  public static void main(String[] args) {
    SpringApplication.run(DemoApplication.class, args);
  }

  @Bean
  public CommandLineRunner demo(BillRepository billRepository, ItemRepository itemRepository, CategoryRepository categoryRepository, CategorySampleRepository categorySampleRepository) {
    return (args) -> {
      List<File> jsonFiles = getJsonFiles("C:\\Users\\Administrator\\workspace\\bill-regconize\\src\\main\\resources\\category");
      JSONParser jsonParser = new JSONParser();
      for (File file : jsonFiles) {
        Category category = new Category();
        category.setName(file.getName().replaceFirst("[.][^.]+$", ""));
        categoryRepository.save(category);
        try {
          Object parse = jsonParser.parse(new FileReader(file));
          JSONArray jsonArray = (JSONArray) parse;
          for (Object o : jsonArray.toArray()) {
            CategorySample categorySample = new CategorySample();
            categorySample.setName((String) o);
            categorySample.setCategorySample(category);
            categorySampleRepository.save(categorySample);
          }
        } catch (Exception e) {
          e.printStackTrace();
        }
      }
      Category category = new Category();
      category.setName("unknown");
      categoryRepository.save(category);

      List<Bill> bills = saveBill();
      billRepository.saveAll(bills);
      List<Item> items = saveItems(billRepository, categoryRepository);
      itemRepository.saveAll(items);
    };
  }

  private List<Item> saveItems(BillRepository billRepository, CategoryRepository categoryRepository) {
    String csvFile = "C:\\Users\\Administrator\\workspace\\bill-regconize\\src\\main\\resources\\item.txt";
    BufferedReader br = null;
    String line = "";
    String cvsSplitBy = ";";
    List<Item> items = new ArrayList<>();

    try {
      br = new BufferedReader(new FileReader(csvFile));
      while ((line = br.readLine()) != null) {
        Item item = new Item();
        // use comma as separator
        String[] b = line.split(cvsSplitBy);

        item.setDescription(b[1]);
        item.setName(b[2]);
        item.setPrice(b[3]);
        item.setBill(billRepository.findById(Long.valueOf(b[4])).get());
        item.setCategory(categoryRepository.findById(Long.valueOf(b[5])).get());
        items.add(item);
      }

    } catch (IOException e) {
      e.printStackTrace();
    } finally {
      if (br != null) {
        try {
          br.close();
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
    }
    return items;
  }


  private List<Bill> saveBill() {
    String csvFile = "C:\\Users\\Administrator\\workspace\\bill-regconize\\src\\main\\resources\\bill.txt";
    BufferedReader br = null;
    String line = "";
    String cvsSplitBy = ",";
    List<Bill> bills = new ArrayList<>();

    try {
      br = new BufferedReader(new FileReader(csvFile));
      while ((line = br.readLine()) != null) {
        Bill bill = new Bill();
        bill.setCreatedTime(new Date());
        // use comma as separator
        String[] b = line.split(cvsSplitBy);

        bill.setSum(Double.valueOf(b[2]));
        bill.setSuperMarket(b[3]);
        bills.add(bill);
      }

    } catch (IOException e) {
      e.printStackTrace();
    } finally {
      if (br != null) {
        try {
          br.close();
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
    }
    return bills;
  }

  private List<File> getJsonFiles(String directoryName) {
    File directory = new File(directoryName);

    List<File> resultList = new ArrayList<File>();

    // get all the files from a directory
    File[] fList = directory.listFiles();
    for (File file : fList) {
      if (file.isFile() && file.getName().endsWith(".json")) {
        resultList.add(file);
      } else if (file.isDirectory()) {
        resultList.addAll(getJsonFiles(file.getAbsolutePath()));
      }
    }
    return resultList;
  }
}
