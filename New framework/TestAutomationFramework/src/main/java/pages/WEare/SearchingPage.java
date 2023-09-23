package pages.WEare;

import org.openqa.selenium.WebDriver;

public class SearchingPage extends WEareBasePage{

    public SearchingPage(WebDriver driver){
        super(driver,"");
    }

    public void seeCurrentUserProfileByName(String name){
        actions.waitForElementClickable("ProfileConnectionPageSeeProfileByName",name);
        actions.clickElement("ProfileConnectionPageSeeProfileByName",name);
    }
    public void clickOnConnectButton(){
        actions.waitForElementClickable("ProfileConnectionPageConnectButton");
        actions.clickElement("ProfileConnectionPageConnectButton");
    }

    public void assertRequestIsSend(){
        actions.assertElementText("ProfileConnectionPageConnectAssert","Good job! You have send friend request!");
    }

    public void clickOnNewFriendRequestButton(){
        actions.waitForElementClickable("PersonalProfilePageNewFriendRequestButton");
        actions.clickElement("PersonalProfilePageNewFriendRequestButton");
    }
    public void approveRequestByUserFirstName(String name){
        actions.waitForElementClickable("PersonalProfileFriendRequestAcceptButton",name);
        actions.clickElement("PersonalProfileFriendRequestAcceptButton");
    }
}
