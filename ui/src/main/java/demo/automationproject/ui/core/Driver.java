package demo.automationproject.ui.core;

import demo.automationproject.utils.properties.Properties;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

public class Driver {
    private static final String URL = Properties.getProperty("baseURI");
    private static final Browser BROWSER = Browser.valueOf(Properties.getProperty("browser").toUpperCase());
    private static final String SELENIUM_HUB_URL = Properties.getProperty("seleniumHubUrl");
    private static final int EXPLICIT_TIMEOUT = Integer.parseInt(Properties.getProperty("explicitTimeout"));

    private static ThreadLocal<EventFiringWebDriver> DRIVER = new ThreadLocal<>();

    // Prevent this class from being initialized.
    private Driver() {
    }

    /**
     * Retrieves the Selenium driver for the current thread.
     *
     * @return Instance of EventFiringWebDriver.
     */
    public static EventFiringWebDriver getDriver() {
        return DRIVER.get();
    }

    /**
     * Creates a new Selenium driver for the current thread if one has not already been created. If one already exists, then it will be returned instead.
     * When creating a new driver, the browser capabilities are determined by the browser property specified as a system property, or in the properties file.
     *
     * @return A new or existing instance of EventFiringWebDriver.
     */
    public static EventFiringWebDriver createDriver() {
        // Attempt to retrieve an existing driver for the thread if one exists.
        EventFiringWebDriver driver = getDriver();

        // Create a new driver.
        if (driver == null) {
            java.net.URL hubUrl;
            DesiredCapabilities capabilities;

            // Initialize the Selenium Hub URL.
            try {
                hubUrl = new URL(SELENIUM_HUB_URL);
            } catch (MalformedURLException e) {
                throw new IllegalStateException("Unable to create new RemoteWebDriver due to malformed URL.");
            }

            // Determine the browser.
            switch (BROWSER) {
                case CHROME:
                    capabilities = DesiredCapabilities.chrome();
                    break;

                case FIREFOX:
                    capabilities = DesiredCapabilities.firefox();
                    break;

                default:
                    throw new IllegalArgumentException("Invalid BROWSER specified: '" + BROWSER.name() + "'.");
            }

            // Create a new driver and assign it to the current thread.
            driver = new EventFiringWebDriver(new RemoteWebDriver(hubUrl, capabilities));
            driver.manage().window().maximize();
            driver.get(URL);
            DRIVER.set(driver);
        }

        return driver;
    }

    /**
     * Get the default timeout value used for explicit waits.
     *
     * @return the default timeout value in seconds.
     */
    public static int getExplicitTimeout() {
        return EXPLICIT_TIMEOUT;
    }

    /**
     * Gets the current thread's Selenium driver, quits the browser, then removes the driver from the thread.
     */
    public static void quit() {
        EventFiringWebDriver driver = getDriver();

        // Only quit and remove the driver if it exists.
        if (driver != null) {
            driver.quit();
            DRIVER.remove();
        }
    }
}
