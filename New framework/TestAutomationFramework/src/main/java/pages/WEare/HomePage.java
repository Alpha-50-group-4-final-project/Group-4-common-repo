package pages.WEare;

import org.openqa.selenium.WebDriver;

import static com.telerikacademy.testframework.Utils.LOGGER;

public class HomePage extends WEareBasePage {

    public HomePage(WebDriver driver) {
        super(driver, "homePage");
    }



    public void navigateToHomePage() {
        actions.navigateToPage("homePage");
    }

    public void navigateToRegisterPage(){
        navigateToPage();
        actions.waitForElementClickable("homepage.RegisterButton");
        actions.clickElement("homepage.RegisterButton");
    }

    public void navigateToLoginPage(){
        actions.waitForElementClickable("homepage.signInButton");
        actions.clickElement("homepage.signInButton");
    }

    public void navigateToLatestPosts(){
        actions.waitForElementClickable("homepage.latestPostsButton");
        actions.clickElement("homepage.latestPostsButton");
    }

    public void navigateToAboutUs(){
        actions.waitForElementClickable("homepage.aboutUsButton");
        actions.clickElement("homepage.aboutUsButton");
    }

    public  void typeIntoNameSearchBox(String name){

        actions.waitForElementVisible("homePage.NameField");
        actions.typeValueInField(name,"homePage.NameField");
    }
    public  void typeIntoProfessionSearchBox(String profession){
        actions.waitForElementPresent("homePage.ProfessionField");
        actions.typeValueInField(profession,"homePage.ProfessionField");
    }
    public void clickOnSearchButton(){
        actions.waitForElementClickable("homePage.SearchButton");
        actions.clickElement("homePage.SearchButton");
    }
    public void navigateToPersonalProfileButton(){
        actions.waitForElementPresent("homePage.PersonalProfileButton");
        actions.clickElement("homePage.PersonalProfileButton");
    }

    public void validateSearchResult(String name){
        actions.assertElementAttribute("homePage.nameInputField", "value", name);
        LOGGER.info("Search result validated.");
    }

}
