package hax.expwnge.utils;

import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.log4j.Logger;

import hax.expwnge.models.FTPCreds;
import hax.expwnge.models.ProcessingContext;

public class FTPUtils {
  private static final Logger LOGGER = Logger.getLogger(FTPUtils.class);

  private FTPUtils() {
    //Do not instantiate.
  }

  public static boolean storeFile(ProcessingContext processingContext, String suffix, InputStream inputStream) {
    boolean isStored = Boolean.FALSE;
    FTPCreds ftpCreds = processingContext.getFtpCreds();
    if(ftpCreds != null && inputStream != null) {
      FTPClient ftpClient = new FTPClient();
      try {
        ftpClient.connect(ftpCreds.getHost(), ftpCreds.getPort());
        ftpClient.login(ftpCreds.getUser(), ftpCreds.getPass());
        ftpClient.enterLocalPassiveMode();
        ftpClient.setFileType(FTP.BINARY_FILE_TYPE);

        String normalizedPath = normalizeString(processingContext.getFileName());
        String realSuffix = suffix != null ? suffix : "";
        String remoteLocation = "personal/" + (normalizedPath != null ? normalizedPath + "/" + normalizedPath : "base/default") +
                                realSuffix + new SimpleDateFormat("-yyyyMMdd").format(new Date()) + ".txt";

        if(normalizedPath != null) {
          ftpClient.makeDirectory("personal/" + normalizedPath);
        } else {
          ftpClient.makeDirectory("personal/base");
        }
        
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
    }
    
    return isStored;
  }
  
  private static String normalizeString(String string) {
    String normalized = null;
    if(string != null && !string.isEmpty()) {
      normalized = string.replaceAll("[^a-zA-Z0-9]", "");
    }
    return normalized;
  }
}
