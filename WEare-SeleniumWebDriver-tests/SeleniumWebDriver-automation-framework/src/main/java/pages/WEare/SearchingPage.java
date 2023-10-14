package pages.WEare;

import org.openqa.selenium.WebDriver;

public class SearchingPage extends WEareBasePage{

    public SearchingPage(WebDriver driver){
        super(driver,"");
    }


    public void findUserProfileByName(String name){
        actions.waitForElementPresent("ProfileConnectionPageSeeProfileByName",name);
        actions.clickElement("ProfileConnectionPageSeeProfileByName",name);
    }
    public void clickOnConnectButton(){
        if (actions.isElementVisible("PersonalProfilePageDisconnectButton")) {
            actions.clickElement("PersonalProfilePageDisconnectButton");
        }
        actions.waitForElementClickable("ProfileConnectionPageConnectButton");
        actions.clickElement("ProfileConnectionPageConnectButton");
    }
    public void clickOnDisconnectButton(){
        actions.waitForElementClickable("PersonalProfilePageDisconnectButton");
        actions.clickElement("PersonalProfilePageDisconnectButton");
    }

    public void assertRequestIsSend(){
        actions.waitForElementVisible("ProfileConnectionPageConnectAssert");
        actions.assertElementText("ProfileConnectionPageConnectAssert","Good job! You have send friend request!");
    }

    public void clickOnNewFriendRequestButton(){
        actions.waitForElementClickable("PersonalProfilePageNewFriendRequestButton");
        actions.clickElement("PersonalProfilePageNewFriendRequestButton");
    }
    public void approveRequestByUserFirstName(String name){
        actions.waitForElementClickable("PersonalProfileFriendRequestAcceptButton",name);
        actions.clickElement("PersonalProfileFriendRequestAcceptButton",name);
    }
    public void assertElementPresent(String locator) {
        actions.waitForElementVisible(locator);
        actions.assertElementPresent(locator);
    }
}
