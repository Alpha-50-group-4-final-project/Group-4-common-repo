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

    public void navigateToLoginPage(){
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

    public  void typeIntoNameSearchBox(String name){
        actions.waitForElementPresent("HomePage.NameField");
        actions.typeValueInField(name,"HomePage.NameField");
    }
    public  void typeIntoProfessionSearchBox(String profession){
        actions.waitForElementPresent("HomePage.ProfessionField");
        actions.typeValueInField(profession,"HomePage.ProfessionField");
    }
    public void clickOnSearchButton(){
        actions.waitForElementClickable("HomePage.SearchButton");
        actions.clickElement("HomePage.SearchButton");
    }

}
