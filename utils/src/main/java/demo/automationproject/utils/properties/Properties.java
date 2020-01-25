package demo.automationproject.utils.properties;

import demo.automationproject.utils.common.Strings;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Properties {
    private static java.util.Properties prop = new java.util.Properties();

    // Prevent construction of this class.
    private Properties() {
    }

    static {
        // Load config.properties from project root.
        try {
            FileInputStream stream = new FileInputStream(getSystemProperty("configPath"));
            prop.load(stream);
            prop.put("UsedForTestingOnly", "myVal"); // Used for unit testing to verify property hierarchical logic.
        } catch (FileNotFoundException e) {
            throw new IllegalStateException("The configuration file was not found in the project.");
        } catch (IOException e) {
            throw new IllegalStateException("Unable to read configuration file: IOException. The file may not be formatted properly.");
        }
    }

    /**
     * Validates the specified key contains a non-null and non-empty value.
     *
     * @param key The key to validate.
     */
    private static void validateKey(String key) {
        if (Strings.IsNullOrEmpty(key)) {
            throw new IllegalArgumentException("The key must contain a value.");
        }
    }

    /**
     * Retrieves the value for the specified key from the system properties.
     *
     * @param key The key name associated with the value.
     * @return The value of the key.
     * @throws IllegalStateException the key does not exist.
     */
    public static String getSystemProperty(String key) {
        validateKey(key);

        String value = System.getProperty(key);

        // Throw an exception if the key does not exist.
        if (Strings.IsNullOrEmpty(value)) {
            throw new IllegalStateException("Required system property '" + key + "' was not found.");
        }

        return value;
    }

    /**
     * Retrieves the value for the specified key from the system properties. If the key is not found in the system properties, then attempt to retrieve the value from config.properties.
     *
     * @param key The key name associated with the value.
     * @return he value of the key, or null if it is not found.
     */
    public static String getProperty(String key) {
        validateKey(key);

        String value = System.getProperty(key);

        // Get the property value from the config.properties file if the value does not exist in the system properties.
        if (Strings.IsNullOrEmpty(value)) {
            value = prop.getProperty(key);
        }

        return value;
    }
}
