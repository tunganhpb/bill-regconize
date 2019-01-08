package com.pmone.demo.rest;

import com.pmone.demo.MicrosoftAPICaller;
import com.pmone.demo.calculate.BoundingBox;
import com.pmone.demo.calculate.ParseUtils;
import com.pmone.demo.model.Result;
import com.pmone.demo.rest.model.Bill;
import com.pmone.demo.rest.model.Item;
import com.pmone.demo.rest.model.SupermarketEnum;
import com.pmone.demo.rest.model.UploadDTO;
import com.pmone.demo.rest.repository.BillRepository;
import com.pmone.demo.rest.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class RestAPIController {

  private final BillRepository billRepository;
  private final ItemRepository itemRepository;
  private final MicrosoftAPICaller microsoftAPICaller;

  @Autowired
  public RestAPIController(BillRepository billRepository, ItemRepository itemRepository, MicrosoftAPICaller microsoftAPICaller) {
    this.billRepository = billRepository;
    this.itemRepository = itemRepository;
    this.microsoftAPICaller = microsoftAPICaller;
  }

  @RequestMapping(value = "/upload", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
  public ResponseEntity<?> upload(@RequestBody UploadDTO uploadDTO) {
    Result result = microsoftAPICaller.uploadPic(uploadDTO.getImageUrl(), SupermarketEnum.valueOf(uploadDTO.getSupermarket()));
    Bill bill = ParseUtils.parseLines(result.getRecognitionResult().getLines().stream().map(line -> new BoundingBox(line.getBoundingBox(), line.getText())).collect(Collectors.toList()));
    bill.setSuperMarket(uploadDTO.getSupermarket());
    bill.setCreatedTime(new Date());
    List<Item> items = bill.getItems();
    billRepository.save(bill);
    items.forEach(item -> item.setBill(bill));
    itemRepository.saveAll(items);
    return new ResponseEntity<>(bill, HttpStatus.CREATED);
  }
}

