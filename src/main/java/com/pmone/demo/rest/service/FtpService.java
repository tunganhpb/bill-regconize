package com.pmone.demo.rest.service;

import com.pmone.demo.rest.utils.FtpClientUtils;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * Created by Anh Pham on 2019-01-13.
 * email: tunganhpb@gmail.com
 */
@Service
public class FtpService {

  public void downloadFile(String fileName) {
    FtpClientUtils ftpClient = new FtpClientUtils("94.135.235.130", 21, "ftpuser", "HTWberlin!");
    try {
      ftpClient.open();
      ftpClient.downloadFile(fileName, fileName);
    } catch (IOException e) {
      e.printStackTrace();
    } finally {
      try {
        ftpClient.close();
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }
}
