package demo.automationproject.utils.properties;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class PropertiesTest {
    /**
     * [0] = Key to retrieve
     * [1] = Value should contain a String of length > 0.
     */
    @DataProvider(name = "properties")
    public Object[][] properties() {
        return new Object[][]{
                {"baseURI", true},
                {"configPath", true},
                {"ThisIsNotARealKey", false}
        };
    }

    /**
     * [0] = Key to retrieve
     */
    @DataProvider(name = "invalidKeys")
    public Object[][] invalidKeys() {
        return new Object[][]{
                {""},
                {null}
        };
    }

    /**
     * Tests that a valid system property's value is successfully retrieved.
     */
    @Test
    public void getSystemProperty() {
        Assert.assertTrue(Properties.getSystemProperty("configPath").length() > 0);
    }

    /**
     * Tests that invalid keys will cause IllegalArgumentException for system properties.
     */
    @Test(dataProvider = "invalidKeys", expectedExceptions = IllegalArgumentException.class)
    public void getSystemPropertyInvalidKeys(String key) {
        Properties.getSystemProperty(key);
    }

    /**
     * Tests that an invalid system property throws IllegalStateException.
     */
    @Test(expectedExceptions = {IllegalStateException.class})
    public void getSystemPropertyInvalidKey() {
        Properties.getSystemProperty("ThisIsNotARealKey");
    }

    /**
     * Tests that valid properties' (system or from config.properties) values are successfully retrieved.
     * Also tests that invalid properties do not throw an exception and instead have null or empty values.
     */
    @Test(dataProvider = "properties")
    public void getProperty(String key, boolean containsValue) {
        String value = Properties.getProperty(key, false);
        Assert.assertEquals((value != null && !value.isEmpty()), containsValue, "Property '" + key + "' was retrieved with an unexpected result.");
    }

    /**
     * Tests that an invalid property throws IllegalStateException.
     */
    @Test(expectedExceptions = {IllegalStateException.class})
    public void getPropertyInvalidKey() {
        Properties.getProperty("ThisIsNotARealKey");
    }

    /**
     * Tests that an invalid property throws IllegalStateException when overridden method with isRequired set to true.
     */
    @Test(expectedExceptions = {IllegalStateException.class})
    public void getPropertyInvalidKeySpecifyRequired() {
        Properties.getProperty("ThisIsNotARealKey", true);
    }

    /**
     * Tests that invalid keys will cause IllegalArgumentException for system and non-system properties.
     */
    @Test(dataProvider = "invalidKeys", expectedExceptions = IllegalArgumentException.class)
    public void getPropertyInvalidKeys(String key) {
        Properties.getProperty(key);
    }

    /**
     * Tests that the system property value will overwrite the same key value in the properties file.
     */
    @Test
    public void propertyHierarchy() {
        final String key = "UsedForTestingOnly";
        final String sysVal = "SysVal";

        // Make sure the properties file's value exists.
        Assert.assertEquals(Properties.getProperty(key), "myVal");

        // Set the same key to the system properties with a different value.
        // Then test to make sure the system property's value overwrites the properties file's value.
        System.setProperty(key, sysVal);
        Assert.assertEquals(Properties.getProperty(key), sysVal, "The system value of the key '" + key + "' did not overwrite the properties file's key value");

        // Unset the system property.
        System.setProperty(key, "");
    }
}
