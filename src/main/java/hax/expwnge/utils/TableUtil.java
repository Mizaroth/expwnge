package hax.expwnge.utils;

import static hax.expwnge.constants.Constants.PASSWORD;
import static hax.expwnge.constants.Constants.PWD_LENGTH;
import static hax.expwnge.constants.Constants.URL;
import static hax.expwnge.constants.Constants.URL_LENGTH;
import static hax.expwnge.constants.Constants.USERNAME;
import static hax.expwnge.constants.Constants.USR_LENGTH;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import hax.expwnge.models.Login;

public class TableUtil {

  private static final String TABLE_FORMAT = "%s | %s | %s";

  private TableUtil() {
    //Do not instantiate.
  }

  public static Map<String, Integer> getColumnsWidth(List<Login> logins) {
    Map<String, Integer> maxLengthMap = null;
    if(logins != null && !logins.isEmpty()) {
      maxLengthMap = buildColumnsMap();
      putMaxPerColumn(logins, maxLengthMap);
    }
    return maxLengthMap;
  }
  

  public static String populateTable(List<Login> logins, Map<String, Integer> columnsWidth) {
    StringBuilder tableRows = null;
    
    if(columnsWidth != null) {
      int maxUrlSize = columnsWidth.get(URL_LENGTH);
      int maxUserSize = columnsWidth.get(USR_LENGTH);
      int maxPwdSize = columnsWidth.get(PWD_LENGTH);
      
      tableRows = new StringBuilder();
      //Header
      tableRows.append(String.format(TABLE_FORMAT, StringUtils.center(URL, maxUrlSize), 
                                            StringUtils.center(USERNAME, maxUserSize), 
                                            StringUtils.center(PASSWORD, maxPwdSize)));
      //Body
      for (Login login : logins) {
        tableRows.append(String.format(TABLE_FORMAT, StringUtils.center(login.getSignOnUrl(), maxUrlSize), 
                                              StringUtils.center(login.getUsername(), maxUserSize), 
                                              StringUtils.center(login.getPassword(), maxPwdSize)));
      }
    }
    
    return tableRows != null ? tableRows.toString() : null;
  }

  private static void putMaxPerColumn(List<Login> logins, Map<String, Integer> maxLengthMap) {
    for(Login login : logins) {
      if(login.getSignOnUrl().length() > maxLengthMap.get(URL_LENGTH)) {
        maxLengthMap.put(URL_LENGTH, login.getSignOnUrl().length());
      }
      if(login.getUsername().length() > maxLengthMap.get(USR_LENGTH)) {
        maxLengthMap.put(USR_LENGTH, login.getUsername().length());
      }
      if(login.getPassword().length() > maxLengthMap.get(PWD_LENGTH)) {
        maxLengthMap.put(PWD_LENGTH, login.getPassword().length());
      }
    }
  }

  private static Map<String, Integer> buildColumnsMap() {
    Map<String, Integer> maxLengthMap;
    maxLengthMap = new HashMap<>();
    maxLengthMap.put(URL_LENGTH, URL.length());
    maxLengthMap.put(USR_LENGTH, USERNAME.length());
    maxLengthMap.put(PWD_LENGTH, PASSWORD.length());
    return maxLengthMap;
  }
}
