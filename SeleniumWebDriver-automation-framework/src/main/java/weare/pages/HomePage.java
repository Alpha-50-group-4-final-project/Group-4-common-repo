package weare.pages;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriver;
import org.opentest4j.AssertionFailedError;

import static com.telerikacademy.testframework.Utils.LOGGER;

public class HomePage extends WEareBasePage {

    public HomePage(WebDriver driver) {
        super(driver, "homePage");
    }



    public void navigateToHomePage() {
        actions.waitForElementVisible("homePageButton");
        actions.waitForElementClickable("homePageButton");
        actions.assertElementPresent("homePageButton");
        actions.clickElement("homePageButton");
    }

    public void navigateToRegisterPage(){
        navigateToHomePage();
        actions.waitForElementPresent("homepage.RegisterButton");
        actions.waitForElementVisible("homepage.RegisterButton");
        actions.waitForElementClickable("homepage.RegisterButton");
        actions.clickElement("homepage.RegisterButton");
        validateHeader("Join our community");
    }

    public void navigateToLoginPage(){
        actions.waitForElementClickable("homepage.signInButton");
        actions.clickElement("homepage.signInButton");
        validateHeader("Login Page");
    }

    public void navigateToLatestPosts(){
        actions.waitForElementVisible("homepage.latestPostsButton");
        actions.waitForElementClickable("homepage.latestPostsButton");
        actions.assertElementPresent("homepage.latestPostsButton");
        actions.clickElement("homepage.latestPostsButton");
        validateHeader("Explore all posts");
    }

    public  void typeIntoNameSearchBox(String name){
        actions.waitForElementVisible("homePage.NameField");
        actions.assertElementPresent("homePage.NameField");
        actions.typeValueInField(name,"homePage.NameField");
    }

    public void clickOnSearchButton(){
        actions.waitForElementClickable("homePage.SearchButton");
        actions.assertElementPresent("homePage.SearchButton");
        actions.clickElement("homePage.SearchButton");
    }
    public void navigateToPersonalProfileButton(){
        actions.waitForElementPresent("homePage.PersonalProfileButton");
        actions.assertElementPresent("homePage.PersonalProfileButton");
        actions.clickElement("homePage.PersonalProfileButton");
    }

    public void validateSearchResult(String name){

        try {
            actions.waitForElementVisible("homePage.nameInputField");
            actions.assertElementAttribute("homePage.nameInputField", "value", name);
            LOGGER.info("Search result validated.");
        } catch (AssertionFailedError e) {
            Assertions.fail("Search was unsuccessful.");
        }
    }
    public void searchUserByName(String name){
        actions.waitForElementClickable("ProfileConnectionPageSeeProfileByName",name);
        actions.assertElementPresent("ProfileConnectionPageSeeProfileByName",name);
        actions.clickElement("ProfileConnectionPageSeeProfileByName",name);
    }

}
