package pages.WEare;

import org.openqa.selenium.WebDriver;

public class LoginPage extends WEareBasePage {
    public LoginPage(WebDriver driver) {
        super(driver, "loginPage");
    }

    public void clickOnLoginButton() {
        actions.waitForElementClickable("homepage.signInButton");
        actions.clickElement("homepage.signInButton");
    }

    public void fillUsernameField(String username) {
        actions.waitForElementPresent("loginPage.UsernameField");
        actions.typeValueInField(username, "loginPage.UsernameField");
    }

    public void fillPasswordField(String password) {
        actions.waitForElementPresent("loginPage.PasswordField");
        actions.typeValueInField(password, "loginPage.PasswordField");
    }

    public void clickOnSubmitButton() {
        actions.waitForElementClickable("loginPage.LoginButton");
        actions.clickElement("loginPage.LoginButton");
    }

    public void clickOnLogOutButton() {
        if (actions.isElementVisible("homePage.LogoutButton")) {
            actions.waitForElementClickable("homePage.LogoutButton");
            actions.clickElement("homePage.LogoutButton");
        }
        validateHeader("Login Page");
    }

    public void navigateToHomePage() {
        driver.get("http://localhost:8081/");
    }

    public void assertErrorMessage(String message) {
        actions.assertElementText("loginErrorMessage", message);
    }

    public void assertElementPresent(String locator) {
        actions.assertElementPresent(locator);
    }

    public void assertElementAttribute(String locator, String attributeName, String attributeValue) {
        actions.assertElementAttribute(locator, attributeName, attributeValue);
    }

}
