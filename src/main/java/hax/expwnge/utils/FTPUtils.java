package hax.expwnge.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Logger;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;

import hax.expwnge.models.FTPCreds;

public class FTPUtils {
  private static final Logger LOGGER = Logger.getLogger(FTPUtils.class);

  public static boolean storeFile(FTPCreds ftpCreds, String fileName, InputStream inputStream) {
    boolean isStored = Boolean.FALSE;
    FTPClient ftpClient = new FTPClient();
    try {
      ftpClient.connect(ftpCreds.getHost(), ftpCreds.getPort());
      ftpClient.login(ftpCreds.getUser(), ftpCreds.getPass());
      ftpClient.enterLocalPassiveMode();
      ftpClient.setFileType(FTP.BINARY_FILE_TYPE);

      String remoteLocation = "personal/" + fileName + ".txt";
      isStored = ftpClient.appendFile(remoteLocation, inputStream);
      inputStream.close();
    }  catch (IOException e) {
      LOGGER.error("Error: " + e);
    } finally {
      try {
        if (ftpClient.isConnected()) {
          ftpClient.logout();
          ftpClient.disconnect();
        }
      } catch (IOException e) {
        LOGGER.error("Error: " + e);
      }
    }

    return isStored;
  }
}
