package pages.WEare;

import org.openqa.selenium.WebDriver;

public class WEareCreateNewPostPage extends WEareBasePAge {

    public WEareCreateNewPostPage(WebDriver driver) {
        super(driver, "");
    }

    public void createNewPost() {



        actions.waitForElementVisible("WEare.homepage.AddNewPostButton");
        actions.clickElement("WEare.homepage.AddNewPostButton");

        actions.waitForElementClickable("WEare.CreateNewPostPage.messageField");
        actions.typeValueInField("This is first post in the system", "WEare.CreateNewPostPage.messageField");

        actions.waitForElementPresent("//input[@id=\"imagefile\"]");
        actions.typeValueInField("K:\\Group-4-common-repo\\TestAutomationFramework\\src\\test\\resources\\Hello.png","//input[@id=\"imagefile\"]");

        actions.waitForElementClickable("WEare.CreateNewPostPage.SavePostButton");
        actions.clickElement("WEare.CreateNewPostPage.SavePostButton");
    }
}
