package coolcar;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Properties;

public class Config {
  private static Config singleton = new Config();
  private HashMap<String, String> map;

  public static Config getInstance() {
    return singleton;
  }

  private Config() {
    Properties prop = new Properties();
    InputStream input = null;
    this.map = new HashMap<String, String>();
    try {
      input = new FileInputStream("coolcar.properties");
      prop.load(input);
      Enumeration<?> e = prop.propertyNames();
      while (e.hasMoreElements()) {
        String key = (String) e.nextElement();
        String value = prop.getProperty(key);
        this.map.put(key, value);
      }
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      if (input != null) {
        try {
          input.close();
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
    }
  }

  public String get(String key, String defaultValue) {
    String value = map.get(key);
    return value == null ? defaultValue : value;
  }
}
