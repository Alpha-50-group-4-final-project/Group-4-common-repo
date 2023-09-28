package pages.WEare;

import org.openqa.selenium.WebDriver;

public class CreateNewPostPage extends WEareBasePage {

    public CreateNewPostPage(WebDriver driver) {
        super(driver, "");
    }

    public void createNewPost() {



        actions.waitForElementVisible("homepage.AddNewPostButton");
        actions.clickElement("homepage.AddNewPostButton");

        actions.waitForElementClickable("createNewPostPage.messageField");
        actions.typeValueInField("This is first post in the system", "createNewPostPage.messageField");

        actions.waitForElementPresent("//input[@id=\"imagefile\"]");
        actions.typeValueInField("K:\\Group-4-common-repo\\TestAutomationFramework\\src\\test\\resources\\Hello.png","//input[@id=\"imagefile\"]");

        actions.waitForElementClickable("createNewPostPage.SavePostButton");
        actions.clickElement("createNewPostPage.SavePostButton");
    }
}
