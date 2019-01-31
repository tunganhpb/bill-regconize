package com.pmone.demo.rest.utils;


import org.apache.commons.net.PrintCommandListener;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;

import java.io.*;
import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;


/**
 * Created by Anh Pham on 2019-01-11.
 * email: tunganhpb@gmail.com
 */
public class FtpClientUtils {
  private String server;
  private int port;
  private String user;
  private String password;
  private FTPClient ftp;

  public FtpClientUtils(String server, int port, String user, String password) {
    this.server = server;
    this.port = port;
    this.user = user;
    this.password = password;
  }

  public void open() throws IOException {
    ftp = new FTPClient();

    ftp.addProtocolCommandListener(new PrintCommandListener(new PrintWriter(System.out)));
    ftp.connect(server, port);
    int reply = ftp.getReplyCode();
    ftp.setFileTransferMode(FTP.BINARY_FILE_TYPE);
    ftp.setFileType(FTP.BINARY_FILE_TYPE);
    if (!FTPReply.isPositiveCompletion(reply)) {
      ftp.disconnect();
      throw new IOException("Exception in connecting to FTP Server");
    }

    ftp.login(user, password);
  }

  public Collection<String> listFiles(String path) throws IOException {
    FTPFile[] files = ftp.listFiles(path);
    return Arrays.stream(files)
        .map(FTPFile::getName)
        .collect(Collectors.toList());
  }

  public void downloadFile(String source, String destination) throws IOException {
    FileOutputStream out = new FileOutputStream("download/" + destination);
    ftp.retrieveFile(source, out);
  }

  public void close() throws IOException {
    ftp.disconnect();
  }
}
