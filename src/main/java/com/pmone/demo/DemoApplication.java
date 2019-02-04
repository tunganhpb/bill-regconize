package com.pmone.demo;

import com.pmone.demo.rest.model.Category;
import com.pmone.demo.rest.model.CategorySample;
import com.pmone.demo.rest.repository.CategoryRepository;
import com.pmone.demo.rest.repository.CategorySampleRepository;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class DemoApplication {

  public static void main(String[] args) {
    SpringApplication.run(DemoApplication.class, args);
  }

  @Bean
  public CommandLineRunner demo(CategoryRepository categoryRepository, CategorySampleRepository categorySampleRepository) {
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
    };
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
