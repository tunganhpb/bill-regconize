package com.pmone.demo.rest;

import com.pmone.demo.rest.service.FtpService;
import com.pmone.demo.rest.service.MicrosoftAPICallerService;
import com.pmone.demo.calculate.BoundingBox;
import com.pmone.demo.rest.utils.ParseLidl;
import com.pmone.demo.model.Result;
import com.pmone.demo.rest.model.Bill;
import com.pmone.demo.rest.model.Item;
import com.pmone.demo.rest.model.SupermarketEnum;
import com.pmone.demo.rest.model.UploadDTO;
import com.pmone.demo.rest.repository.BillRepository;
import com.pmone.demo.rest.repository.ItemRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class RestAPIController {

  Logger logger = LoggerFactory.getLogger(RestAPIController.class);

  private final BillRepository billRepository;
  private final ItemRepository itemRepository;
  private final MicrosoftAPICallerService microsoftAPICallerService;
  private final FtpService ftpService;

  @Autowired
  public RestAPIController(BillRepository billRepository, ItemRepository itemRepository, MicrosoftAPICallerService microsoftAPICallerService, FtpService ftpService) {
    this.billRepository = billRepository;
    this.itemRepository = itemRepository;
    this.microsoftAPICallerService = microsoftAPICallerService;
    this.ftpService = ftpService;
  }

  @RequestMapping(value = "/upload", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
  public ResponseEntity<?> upload(@RequestBody UploadDTO uploadDTO) {
//    ftpService.downloadFile(uploadDTO.getImageName());
    Result result = microsoftAPICallerService.uploadPic("C:/ftp/ftproot/" + uploadDTO.getImageName(), SupermarketEnum.valueOf(uploadDTO.getSupermarket()));
    Bill bill = ParseLidl.parseLines(result.getRecognitionResult().getLines().stream().map(line -> new BoundingBox(line.getBoundingBox(), line.getText())).collect(Collectors.toList()));
    bill.setSuperMarket(uploadDTO.getSupermarket());
    bill.setCreatedTime(new Date());
    List<Item> items = bill.getItems();
    billRepository.save(bill);
    items.forEach(item -> item.setBill(bill));
    itemRepository.saveAll(items);
    logger.info("item size: " + bill.getItems().size());
    return new ResponseEntity<>(bill, HttpStatus.CREATED);
  }

  @RequestMapping(value = "/bills", method = RequestMethod.GET)
  public ResponseEntity<?> bills () {
    List<Bill> all = billRepository.findAll();
    return new ResponseEntity<>(all, HttpStatus.OK);
  }
}

