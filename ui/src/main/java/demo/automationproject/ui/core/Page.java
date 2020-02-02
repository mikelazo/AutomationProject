package demo.automationproject.ui.core;

import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Page {

    public Page() {
        // Initialize the elements of the page.
        PageFactory.initElements(Driver.getDriver(), this);
    }

    /**
     * Wait until the specified element becomes visible. Does not throw {@link TimeoutException} if the element never becomes visible.
     *
     * @param element The element to wait for to become visible.
     */
    protected void waitUntilVisible(WebElement element) {
        waitUntilVisible(element, false);
    }

    /**
     * Wait until the specified element becomes visible.
     *
     * @param element                 The element to wait for to become visible.
     * @param throwExceptionOnFailure Determines whether or not to throw {@link TimeoutException} if the element never becomes visible.
     */
    protected void waitUntilVisible(WebElement element, boolean throwExceptionOnFailure) {
        // Do not throw TimeoutException. Assume that code is written so that visibility is validated.
        try {
            new WebDriverWait(Driver.getDriver(), Driver.getExplicitTimeout()).until(ExpectedConditions.visibilityOf(element));
        } catch (TimeoutException e) {
            if (throwExceptionOnFailure) {
                throw e;
            } else {
                // TODO: Replace with log4j
                System.out.println("Timeout exception while waiting for element visibility.");
            }
        }
    }

    /**
     * Checks if the specified element is displayed on the page.
     *
     * @param element The element to validate.
     * @return True if the element is displayed. Otherwise, returns false.
     */
    protected boolean isDisplayed(WebElement element) {
        waitUntilVisible(element);

        return element.isDisplayed();
    }

    /**
     * Checks if the specified element is enabled.
     *
     * @param element The element to validate.
     * @return True if the element is enabled. Otherwise, returns false.
     */
    protected boolean isEnabled(WebElement element) {
        waitUntilVisible(element);

        return element.isEnabled();
    }

    /**
     * Inputs the specified text to the specified element.
     *
     * @param element The element to input the text.
     * @param text    The text to input.
     * @throws IllegalStateException if the element is not displayed.
     */
    protected void inputText(WebElement element, String text) {
        if (isDisplayed(element)) {
            element.sendKeys(text);
        } else {
            throw new IllegalStateException("Cannot input text into element because the element is not displayed.");
        }
    }

    /**
     * Clicks the specified element.
     *
     * @param element The element to click.
     * @throws IllegalStateException if the element is not displayed.
     */
    protected void click(WebElement element) {
        if (isDisplayed(element)) {
            element.click();
        } else {
            throw new IllegalStateException("Cannot click element because the element is not displayed.");
        }
    }

    /**
     * Retrieves the text of the specified element.
     *
     * @param element The element that contains the text.
     * @return The text of the element.
     * @throws IllegalStateException if the element is not displayed.
     */
    protected String getText(WebElement element) {
        String text;

        if (isDisplayed(element)) {
            text = element.getText();
        } else {
            throw new IllegalStateException("Cannot retrieve text for field because the element is not displayed.");
        }

        return text;
    }
}
