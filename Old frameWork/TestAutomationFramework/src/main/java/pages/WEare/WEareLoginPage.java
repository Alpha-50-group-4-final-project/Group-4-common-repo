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

        actions.waitForElementPresent("WEare.loginpage.UsernameField");
        actions.typeValueInField(userName, "WEare.loginpage.UsernameField");

        actions.waitForElementPresent("Weare.loginpage.PasswordField");
        actions.typeValueInField(passWord, "Weare.loginpage.PasswordField");

        actions.waitForElementClickable("WEare.loginpage.LoginButton");
        actions.clickElement("WEare.loginpage.LoginButton");
    }

}
