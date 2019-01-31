package com.pmone.demo;

import com.pmone.demo.rest.utils.FtpClientUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.io.IOException;
import java.util.Collection;


/**
 * Created by Anh Pham on 2019-01-11.
 * email: tunganhpb@gmail.com
 */
public class FtpClientUtilsIntegrationTest {

  private FtpClientUtils ftpClient;

  @Before
  public void setup() throws IOException {
    ftpClient = new FtpClientUtils("94.135.235.130", 21, "ftpuser", "HTWberlin!");
    ftpClient.open();
  }

  @Test
  public void givenRemoteFile_whenListingRemoteFiles_thenItIsContainedInList() throws IOException {
    Collection<String> files = ftpClient.listFiles(".");
    System.out.println("a");
//    assertThat(files).contains("foobar.txt");
  }

  @Test
  public void givenRemoteFile_whenDownloading_thenItIsOnTheLocalFilesystem() throws IOException {
    ftpClient.downloadFile("IMG_1838.jpg", "IMG_1838.jpg");
  }

  @After
  public void teardown() throws IOException {
    ftpClient.close();
  }
}
