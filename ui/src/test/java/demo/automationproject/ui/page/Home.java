package demo.automationproject.ui.page;

import demo.automationproject.ui.core.Page;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class Home extends Page {
    @FindBy(css = ".header-menu-full-row .fa-sign-out-alt")
    WebElement btnLogout;

    public Home() {
        waitUntilVisible(btnLogout);
    }

    /**
     * Determines whether or not the logout button is displayed on the page.
     *
     * @return True when the logout button is displayed. Otherwise, returns false.
     */
    public boolean isLogoutButtonDisplayed() {
        return isDisplayed(btnLogout);
    }
}
