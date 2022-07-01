package utils;

import java.io.FileReader;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import static java.lang.System.exit;

public class ConfigurationUtil {
    private static Properties properties;
    private static final Logger logger = Logger.getLogger(ConfigurationUtil.class.getName());

    private ConfigurationUtil() { }

    public static Properties getCurrentConfiguration() {
        if (properties != null)
            return properties;
        else {
            try {
                FileReader reader= new FileReader("src/test/resources/properties/configuration.properties");
                properties = new Properties();
                properties.load(reader);
            } catch (Exception e) {
                logger.log(Level.WARNING, "Config file was not found!");
                exit(0);
            }
        }
        return properties;
    }

    public static String GetValue(String key) {
        if(properties != null)
            return properties.getProperty(key);
        else
            return getCurrentConfiguration().getProperty(key);
    }
}
