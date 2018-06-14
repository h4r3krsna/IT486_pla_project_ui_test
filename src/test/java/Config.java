import java.util.HashMap;
import java.util.Map;

// TODO: make a JSON file for settings and load from it
public class Config {
  // TODO: improve OO design using fewer "static" properties and actions
  private static Map<String, Map<String, String>> config = new HashMap();

  public static void init() {
    HashMap<String, String> prodSettings = new HashMap(),
        devSettings = new HashMap();

    prodSettings.put("url", "http://request.greenrivertech.net");
    devSettings.put("url", "http://msreedaran.greenrivertech.net/plaform");

    config.put("PROD", prodSettings);
    config.put("DEV", devSettings);
  }

  public static String get(String environment, String settingName) {
    return config.get(environment).get(settingName);
  }
}