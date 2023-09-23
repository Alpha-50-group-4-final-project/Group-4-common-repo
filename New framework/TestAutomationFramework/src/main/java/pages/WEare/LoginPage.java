package pages.WEare;

import org.openqa.selenium.WebDriver;

public class LoginPage extends WEareBasePage {
    public LoginPage(WebDriver driver) {
        super(driver, "WEare.loginPage");
    }

    public void clickOnLoginButton(){
        actions.waitForElementVisible("WEare.homepage.signInButton");
        actions.clickElement("WEare.homepage.signInButton");
    }
    public  void  fillUsernameField(String username){
        actions.waitForElementPresent("WEare.loginPage.UsernameField");
        actions.typeValueInField(username, "WEare.loginPage.UsernameField");
    }
    public  void  fillPasswordField(String password){
        actions.waitForElementPresent("Weare.loginPage.PasswordField");
        actions.typeValueInField(password, "Weare.loginPage.PasswordField");
    }

    public void  clickOnCreateButton(){
        actions.waitForElementClickable("WEare.loginPage.LoginButton");
        actions.clickElement("WEare.loginPage.LoginButton");
    }
    public void clickOnLogOutButton(){
        actions.waitForElementClickable("WEare.homePage.LogoutButton");
        actions.clickElement("WEare.homePage.LogoutButton");
    }

    public void navigateToHomePage() {
        driver.get("http://localhost:8081/");
    }

    public void assertErrorMessage(String message) {
        actions.assertElementText("WEare.RegisterLoginErrorMessage", message);
    }

    public void assertElementPresent(String locator) {
        actions.assertElementPresent(locator);
    }

    public void assertElementAttribute(String locator, String attributeName, String attributeValue) {
        actions.assertElementAttribute(locator, attributeName, attributeValue);
    }

}
