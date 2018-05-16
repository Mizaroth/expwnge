package hax.expwnge.models;

import org.springframework.beans.factory.annotation.Autowired;

public class ProcessingContext {
  private String strategyType;
  private String fileName;
  @Autowired
  private FTPCreds ftpCreds;
  
  public ProcessingContext() {
    
  }
  
  public ProcessingContext(String fileName) {
    this.fileName = fileName;
  }

  public String getStrategyType() {
    return strategyType;
  }

  public void setStrategyType(String strategyType) {
    this.strategyType = strategyType;
  }

  public String getFileName() {
    return fileName;
  }

  public void setFileName(String fileName) {
    this.fileName = fileName;
  }

  public FTPCreds getFtpCreds() {
    return ftpCreds;
  }

  public void setFtpCreds(FTPCreds ftpCreds) {
    this.ftpCreds = ftpCreds;
  }
}
