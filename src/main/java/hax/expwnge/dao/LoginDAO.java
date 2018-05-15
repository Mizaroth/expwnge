package hax.expwnge.dao;

import java.nio.file.Path;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import hax.expwnge.builders.LoginBuilder;
import hax.expwnge.models.Login;

public class LoginDAO implements GenericDAO<Login> {
  @Autowired
  private LoginBuilder loginBuilder;
  private static final Logger LOGGER = Logger.getLogger(LoginDAO.class);
  
  @Override
  public List<Login> listAll() {
    return new ArrayList<>();
  }

  @Override
  public Login readById(long l) {
    return null;
  }
  
  public List<Login> getLoginDetails(Path source) {
    List<Login> logins = null;
    if(source != null && source.toFile().exists()) {
      String url = "jdbc:sqlite:" + source;
      String sql = "SELECT signon_realm, username_value, password_value FROM logins";
      try(Connection conn = DriverManager.getConnection(url); 
          PreparedStatement stat = conn.prepareStatement(sql);
          ResultSet rs = stat.executeQuery()) {
        while(rs.next()) {
          if(logins == null)
            logins = new ArrayList<>();
          logins.add(loginBuilder
              .signOnRealm(rs.getString(1))
              .username(rs.getString(2))
              .passwordBytes(rs.getBytes(3))
              .build());
        }
      } catch (SQLException e) {
        LOGGER.error(e);
      } 
    }
    
    return logins;
  }

}
