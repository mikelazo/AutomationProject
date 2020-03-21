package demo.automationproject.ui.test;

import demo.automationproject.ui.page.Home;
import demo.automationproject.ui.page.Login;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class LoginTest extends UiTest {
    /**
     * [0] = username
     * [1] = password
     */
    @DataProvider(name = "validUsers")
    public Object[][] validUsers() {
        return new Object[][]{
                {"admin", "admin"},
                {"sfisher", "password"},
                {"hlamarr", "password"}
        };
    }

    @BeforeTest
    public void forceWait() throws InterruptedException {
        Thread.sleep(10000);
    }

    /**
     * Validates the various elements on the login page are in the expected state.
     */
    @Test
    public void validateElements() {
        Login login = new Login();

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(login.isUsernameFieldDisplayed(), "The username text field is not displayed.");
        softAssert.assertTrue(login.isPasswordFieldDisplayed(), "The password text field is not displayed.");
        softAssert.assertTrue(login.isLoginButtonDisplayed(), "The login button is not displayed.");
        softAssert.assertTrue(login.isLoginButtonEnabled(), "The login button is not enabled.");
        softAssert.assertAll();
    }

    /**
     * Ensures that the login error is displayed when login is unsuccessful.
     */
    @Test
    public void invalidLogin() {
        Login login = new Login();
        login.inputUsername("a");
        login.clickLoginButton();

        // Recreate object to prevent stale elements.
        login = new Login();

        Assert.assertTrue(login.isInvalidLoginErrorDisplayed(), "The invalid login error message is not displayed.");
        Assert.assertEquals(login.getInvalidLoginErrorText(), "Invalid username or password", "The invalid login error message did not contain the expected text.");
    }

    /**
     * Tests the successful login functionality with various credentials.
     */
    @Test(dataProvider = "validUsers", threadPoolSize = 3)
    public void login(String username, String password) {
        Login login = new Login();
        login.inputUsername(username);
        login.inputPassword(password);
        login.clickLoginButton();

        Home home = new Home();
        Assert.assertTrue(home.isLogoutButtonDisplayed(), "Failed to login. The logout button was not found.");
    }
}
