package com.pmone.demo.rest;

import com.pmone.demo.calculate.BoundingBox;
import com.pmone.demo.model.Result;
import com.pmone.demo.rest.model.Bill;
import com.pmone.demo.rest.model.Item;
import com.pmone.demo.rest.model.SupermarketEnum;
import com.pmone.demo.rest.model.UploadDTO;
import com.pmone.demo.rest.repository.BillRepository;
import com.pmone.demo.rest.repository.ItemRepository;
import com.pmone.demo.rest.service.MicrosoftAPICallerService;
import com.pmone.demo.rest.utils.ParseLidl;
import com.pmone.demo.rest.utils.ParseReal;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

  private Logger logger = LoggerFactory.getLogger(RestAPIController.class);

  private final BillRepository billRepository;
  private final ItemRepository itemRepository;
  private final MicrosoftAPICallerService microsoftAPICallerService;

  @Autowired
  public RestAPIController(BillRepository billRepository, ItemRepository itemRepository, MicrosoftAPICallerService microsoftAPICallerService) {
    this.billRepository = billRepository;
    this.itemRepository = itemRepository;
    this.microsoftAPICallerService = microsoftAPICallerService;
  }

  @RequestMapping(value = "/upload", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
  public ResponseEntity<?> upload(@RequestBody UploadDTO uploadDTO) {
    Result result = microsoftAPICallerService.uploadPic("C:/ftp/ftproot/" + uploadDTO.getImageName(), SupermarketEnum.valueOf(uploadDTO.getSupermarket()));
    List<BoundingBox> collect = result.getRecognitionResult().getLines().stream().map(line -> new BoundingBox(line.getBoundingBox(), line.getText())).collect(Collectors.toList());
    Bill bill = new Bill();
    switch (uploadDTO.getSupermarket()) {
      case "Lidl":
        bill = ParseLidl.parseLines(collect);
        break;
      case "Real":
        bill = ParseReal.parseLines(collect);
        break;
    }
    bill.setSuperMarket(uploadDTO.getSupermarket());
    bill.setCreatedTime(new Date());
    List<Item> items = bill.getItems();
    billRepository.save(bill);
    Bill finalBill = bill;
    items.forEach(item -> item.setBill(finalBill));
    itemRepository.saveAll(items);
    logger.info("item size: " + bill.getItems().size());
    return new ResponseEntity<>(bill, HttpStatus.CREATED);
  }

  @RequestMapping(value = "/bills", method = RequestMethod.GET)
  public ResponseEntity<?> bills() {
    logger.info("GET BILLS");
    List<Bill> all = billRepository.findAll();
    return new ResponseEntity<>(all, HttpStatus.OK);
  }
}

