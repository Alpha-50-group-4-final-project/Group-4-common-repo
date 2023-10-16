package pages.WEare;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriver;
import org.opentest4j.AssertionFailedError;

import static com.telerikacademy.testframework.Utils.LOGGER;

public class SearchingPage extends WEareBasePage{

    public SearchingPage(WebDriver driver){
        super(driver,"");
    }


    public void findUserProfileByName(String name){
        actions.waitForElementClickable("ProfileConnectionPageSeeProfileByName",name);
        actions.clickElement("ProfileConnectionPageSeeProfileByName",name);
    }
    public void clickConnectButton(){
        if (actions.isElementVisible("PersonalProfilePageDisconnectButton")) {
            actions.clickElement("PersonalProfilePageDisconnectButton");
        }
        actions.waitForElementClickable("ProfileConnectionPageConnectButton");
        actions.clickElement("ProfileConnectionPageConnectButton");
    }
    public void clickDisconnectButton(){
        actions.waitForElementClickable("PersonalProfilePageDisconnectButton");
        actions.assertElementPresent("PersonalProfilePageDisconnectButton");
        actions.clickElement("PersonalProfilePageDisconnectButton");
    }

    public void validateConnectionRequestSend(){
        try {
            actions.waitForElementVisible("ProfileConnectionPage.successMessage");
            actions.assertElementPresent("ProfileConnectionPage.successMessage");
            actions.assertElementText("ProfileConnectionPage.successMessage", "Good job! You have send friend request!");
            LOGGER.info("Connection request was successfully sent.");
        } catch (AssertionFailedError e) {
            Assertions.fail("Connection request was successfully sent.");
        }
    }

    public void clickOnNewFriendRequestButton(){
        actions.waitForElementClickable("PersonalProfilePageNewFriendRequestButton");
        actions.assertElementPresent("PersonalProfilePageNewFriendRequestButton");
        actions.clickElement("PersonalProfilePageNewFriendRequestButton");
    }
    public void approveRequestByUserFirstName(String name){
        actions.waitForElementClickable("PersonalProfileFriendRequestAcceptButton",name);
//        actions.assertElementPresent("PersonalProfileFriendRequestAcceptButton",name);
        actions.clickElement("PersonalProfileFriendRequestAcceptButton",name);
    }
    public void assertElementPresent(String locator) {
        actions.waitForElementVisible(locator);
        actions.assertElementPresent(locator);
    }
    
    public void validateRequestAccepted(String approverID){
        actions.navigateToPage("personalProfilePageById",approverID);
        assertElementPresent("PersonalProfilePageDisconnectButton");
    }
}
