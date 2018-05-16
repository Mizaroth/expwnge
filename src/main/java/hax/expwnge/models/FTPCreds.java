package hax.expwnge.models;

import hax.expwnge.processing.impl.rules.Value;

public class FTPCreds {
  @Value("#{ftp.host}")
  private String host;
  @Value("#{ftp.user}")
  private String user;
  @Value("#{ftp.pass}")
  private String pass;
  @Value("#{ftp.port}")
  private int port;
  @Value("#{ftp.is_enabled}")
  private boolean isEnabled;
  
  public boolean isEnabled() {
    return isEnabled;
  }
  public void setEnabled(boolean isEnabled) {
    this.isEnabled = isEnabled;
  }
  public String getHost() {
    return host;
  }
  public void setHost(String host) {
    this.host = host;
  }
  public String getUser() {
    return user;
  }
  public void setUser(String user) {
    this.user = user;
  }
  public String getPass() {
    return pass;
  }
  public void setPass(String pass) {
    this.pass = pass;
  }
  public int getPort() {
    return port;
  }
  public void setPort(int port) {
    this.port = port;
  }
}
