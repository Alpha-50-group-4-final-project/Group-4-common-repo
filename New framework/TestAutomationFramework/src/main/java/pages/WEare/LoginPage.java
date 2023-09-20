package pages.WEare;

import org.openqa.selenium.WebDriver;

public class LoginPage extends WEareBasePаge {
    public LoginPage(WebDriver driver) {
        super(driver, "");
    }



    public void clickOnLoginButton(){
        actions.waitForElementVisible("WEare.homepage.signInButton");
        actions.clickElement("WEare.homepage.signInButton");
    }
    public  void  fillUsernameField(String username){
        actions.waitForElementPresent("WEare.loginpage.UsernameField");
        actions.typeValueInField(username, "WEare.loginpage.UsernameField");
    }
    public  void  fillPasswordField(String password){
        actions.waitForElementPresent("Weare.loginpage.PasswordField");
        actions.typeValueInField(password, "Weare.loginpage.PasswordField");
    }

    public void  clickOnCreateButton(){
        actions.waitForElementClickable("WEare.loginpage.LoginButton");
        actions.clickElement("WEare.loginpage.LoginButton");
    }
    public void clickOnLogOutButton(){
        actions.waitForElementClickable("WEare.homePage.LogoutButton");
        actions.clickElement("WEare.homePage.LogoutButton");
    }

}
