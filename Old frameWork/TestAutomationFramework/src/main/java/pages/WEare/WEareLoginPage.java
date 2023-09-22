package pages.WEare;

import org.openqa.selenium.WebDriver;

public class WEareLoginPage extends WEareBasePAge {
    public WEareLoginPage(WebDriver driver) {
        super(driver, "");
    }


    public void login(String userName, String passWord) {
        driver.get("http://localhost:8081/");

        actions.waitForElementVisible("WEare.homepage.signInButton");
        actions.clickElement("WEare.homepage.signInButton");

        actions.waitForElementPresent("WEare.loginPage.UsernameField");
        actions.typeValueInField(userName, "WEare.loginPage.UsernameField");

        actions.waitForElementPresent("Weare.loginPage.PasswordField");
        actions.typeValueInField(passWord, "Weare.loginPage.PasswordField");

        actions.waitForElementClickable("WEare.loginPage.LoginButton");
        actions.clickElement("WEare.loginPage.LoginButton");
    }

}
