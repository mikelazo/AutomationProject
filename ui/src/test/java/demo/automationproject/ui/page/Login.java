package demo.automationproject.ui.page;

import demo.automationproject.ui.core.Page;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class Login extends Page {
    @FindBy(id = "username")
    WebElement txtUsername;

    @FindBy(id = "password")
    WebElement txtPassword;

    @FindBy(name = "submit")
    WebElement btnLogin;

    @FindBy(css = "#login-box .alert")
    WebElement invalidLoginError;

    public Login() {
        waitUntilVisible(txtUsername);
    }

    public boolean isUsernameFieldDisplayed() {
        return isDisplayed(txtUsername);
    }

    public boolean isPasswordFieldDisplayed() {
        return isDisplayed(txtPassword);
    }

    public boolean isLoginButtonDisplayed() {
        return isDisplayed(btnLogin);
    }

    public boolean isLoginButtonEnabled() {
        return isEnabled(btnLogin);
    }

    public void inputUsername(String username) {
        inputText(txtUsername, username);
    }

    public void inputPassword(String password) {
        inputText(txtPassword, password);
    }

    public void clickLoginButton() {
        click(btnLogin);
    }

    public boolean isInvalidLoginErrorDisplayed() {
        return isLoginButtonDisplayed();
    }

    public String getInvalidLoginErrorText() {
        return getText(invalidLoginError);
    }
}
