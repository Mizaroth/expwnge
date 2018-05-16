package hax.expwnge.models;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.List;

import hax.expwnge.utils.TableUtil;

public class Login {
  private String signOnUrl;
  private String username;
  private String password;
  public static final Login LOGIN_TABLE_FACTORY = new Login();

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getSignOnUrl() {
    return signOnUrl;
  }

  public void setSignOnUrl(String signOnUrl) {
    this.signOnUrl = signOnUrl;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public class Table {
    private String content;
    
    public Table() {
      //Explicit default constructor
    }

    public Table(List<Login> logins) {
        this.content = TableUtil.populateTable(logins, TableUtil.getColumnsWidth(logins));
    }

    public String getContent() {
      return content;
    }
    
    public InputStream getContentAsStream() {
      return new ByteArrayInputStream(content.getBytes());
    }
  }
}
