package pages.WEare;

import org.openqa.selenium.WebDriver;

public class LoginPage extends WEareBasePаge {
    public LoginPage(WebDriver driver) {
        super(driver, "");
    }


    public void login(String userName, String passWord) {
        driver.get("http://localhost:8081/");



        actions.waitForElementPresent("WEare.loginpage.UsernameField");
        actions.typeValueInField(userName, "WEare.loginpage.UsernameField");

        actions.waitForElementPresent("Weare.loginpage.PasswordField");
        actions.typeValueInField(passWord, "Weare.loginpage.PasswordField");

        actions.waitForElementClickable("WEare.loginpage.LoginButton");
        actions.clickElement("WEare.loginpage.LoginButton");
    }

    public void clickOnLoginButton{  actions.waitForElementVisible("WEare.homepage.signInButton");
        actions.clickElement("WEare.homepage.signInButton");}
    public  void  fillUsernameField(){

    }


}
