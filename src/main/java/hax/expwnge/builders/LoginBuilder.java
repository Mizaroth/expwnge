package hax.expwnge.builders;

import hax.expwnge.models.Login;
import hax.expwnge.utils.DecryptUtil;

public class LoginBuilder implements Builder<Login> {

  private String signOnRealm;
  private String username;
  private byte[] passwordBytes;
  
  @Override
  public Login build() {
    Login login = new Login();
    login.setSignOnUrl(signOnRealm);
    login.setUsername(username);
    login.setPassword(DecryptUtil.decrypt(passwordBytes));
    return login;
  }
  
  public LoginBuilder signOnRealm(String signOnRealm) {
    this.signOnRealm = signOnRealm;
    return this;
  }
  
  public LoginBuilder username(String username) {
    this.username = username;
    return this;
  }
  
  public LoginBuilder passwordBytes(byte[] passwordBytes) {
    this.passwordBytes = passwordBytes;
    return this;
  }
}
