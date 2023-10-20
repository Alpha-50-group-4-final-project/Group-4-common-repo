package weare.pages;

import io.qameta.allure.Allure;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.opentest4j.AssertionFailedError;

import java.io.ByteArrayInputStream;

import static com.telerikacademy.testframework.Utils.LOGGER;


public class LoginPage extends WEareBasePage {
    public LoginPage(WebDriver driver) {
        super(driver, "loginPage");
    }

    public void navigateToSignIn() {
        actions.waitForElementClickable("homepage.signInButton");
        actions.assertElementPresent("homepage.signInButton");
        actions.clickElement("homepage.signInButton");
    }

    public void fillUsername(String username) {
        actions.waitForElementPresent("loginPage.UsernameField");
        actions.assertElementPresent("loginPage.UsernameField");
        actions.typeValueInField(username, "loginPage.UsernameField");
    }

    public void fillPassword(String password) {
        actions.waitForElementPresent("loginPage.PasswordField");
        actions.assertElementPresent("loginPage.PasswordField");
        actions.typeValueInField(password, "loginPage.PasswordField");
    }

    public void submitForm() {
        actions.waitForElementClickable("loginPage.LoginButton");
        actions.assertElementPresent("loginPage.LoginButton");
        actions.clickElement("loginPage.LoginButton");
    }

    public void clickOnLogOutButton() {
            if (actions.isElementVisible("homePage.LogoutButton")) {
                actions.waitForElementClickable("homePage.LogoutButton");
                actions.waitForElementPresent("homePage.LogoutButton");
                actions.assertElementPresent("homePage.LogoutButton");
                actions.clickElement("homePage.LogoutButton");
            }
    }

    public void validateLoggedIn() {
        try {
            actions.assertElementAttribute("homePage.LogoutButton", "innerText", "LOGOUT");
            LOGGER.info("User was successfully logged in.");
        }catch (AssertionFailedError e) {
            Allure.addAttachment("Any text", new ByteArrayInputStream(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES)));
            Assertions.fail("User was not logged in.");
        }
    }

    public void validateErrorMessage() {
        try {
            actions.assertElementText("loginErrorMessage", "Wrong username or password.");
            LOGGER.info("Expected error message showed up.");
        }catch (AssertionFailedError e) {
            Assertions.fail("User was logged in despite wrong credentials provided.");
        }
    }
}
