package hax.expwnge.utils;

import org.apache.log4j.Logger;

import com.sun.jna.platform.win32.Crypt32Util;

public class DecryptUtil {
  private static final Logger LOGGER = Logger.getLogger(DecryptUtil.class);
  
  private DecryptUtil() {
    //Do not instantiate.
  }
  
  public static String decrypt(byte[] passwordBytes) {
    byte[] uncryptedVal = null;
    try {
      uncryptedVal = Crypt32Util.cryptUnprotectData(passwordBytes);
    } catch(Exception e) {
      LOGGER.error(e);
    }
    
    return new String(uncryptedVal);
  }
  
}
