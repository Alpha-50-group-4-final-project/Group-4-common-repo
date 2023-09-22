package pages.WEare;

import org.openqa.selenium.WebDriver;

public class HomePage extends WEareBasePage {

    public HomePage(WebDriver driver) {
        super(driver, "WEare.homePage");
    }

    public void navigateToRegisterPage(){
        navigateToPage();
        actions.waitForElementClickable("WEare.homepage.RegisterButton");
        actions.clickElement("WEare.homepage.RegisterButton");
    }

    public void navigateToSigninPage(){
        actions.waitForElementClickable("WEare.homepage.signInButton");
        actions.clickElement("WEare.homepage.signInButton");
    }

    public void navigateToLatestPosts(){
        actions.waitForElementClickable("WEare.homepage.latestPostsButton");
        actions.clickElement("WEare.homepage.latestPostsButton");
    }
    public void navigateToAboutUs(){
        actions.waitForElementClickable("WEare.homepage.aboutUsButton");
        actions.clickElement("WEare.homepage.aboutUsButton");
    }

}
