package pages.WEare;

import org.openqa.selenium.WebDriver;

public class HomePage extends WEareBasePаge {

    public HomePage(WebDriver driver) {
        super(driver, "WEare.homePage");
    }

    public void navigateToRegisterPage(){
        navigateToPage();
        actions.waitForElementClickable("WEare.homepageRegisterButton");
        actions.clickElement("WEare.homepageRegisterButton");
    }

    public void navigateToSigninPage(){
        actions.waitForElementClickable("");
        actions.clickElement("");
    }


}
