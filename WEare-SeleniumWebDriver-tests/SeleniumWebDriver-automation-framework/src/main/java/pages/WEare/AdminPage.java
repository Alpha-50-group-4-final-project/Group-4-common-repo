package pages.WEare;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import static com.telerikacademy.testframework.Utils.LOGGER;

public class AdminPage extends WEareBasePage {

    public String updatedMessage = "my automation edited post by Selenium Driver";
    public String deletedMessage = "Post deleted successfully";
    public String postVisibilityPublic = "public";
    public String deleteConfirmation = "delete";

    public AdminPage(WebDriver driver) {
        super(driver, "homePage");
    }


    public void validateAdminPageNavigated() {
        actions.assertElementAttribute("adminPage.adminZoneButton", "innerText", "GO TO admin zone");
        LOGGER.info("Admin logged in.");
    }

    public void clickEditButton() {
        actions.waitForElementClickable("adminPage.editPostButton");
        actions.clickElement("adminPage.editPostButton");
        validateHeader("Edit post");
    }

    public void editPostInformation() {
        actions.clickElement("adminPage.postVisibility");
        actions.waitForElementPresent("adminPage.postVisibility");
        actions.typeValueInField(postVisibilityPublic, "adminPage.postVisibility");
        actions.waitForElementPresent("adminPage.editMessage");
        actions.typeValueInField(updatedMessage, "adminPage.editMessage");
        actions.clickElement("adminPage.submitButton");
    }

    public void validatePostEdited() {
        actions.assertElementAttribute("adminPage.editedPostMessage", "innerText", updatedMessage);
        LOGGER.info("Admin edited post successfully.");
    }

    public void clickDeleteButton() {
        actions.waitForElementClickable("adminPage.deletePostButton");
        actions.clickElement("adminPage.deletePostButton");
        validateHeader("Delete post");
    }

    public void deletePost() {
        actions.clickElement("adminPage.deleteConfirmation");
        actions.typeValueInField(deleteConfirmation, "adminPage.deleteConfirmation");
        actions.clickElement("adminPage.submitButton");
    }

    public void validateDeletion() {
        WebElement pageMessage = driver.findElement(By.xpath("//h1[@class='mb-3 bread']"));
        actions.waitForElementVisible("adminPage.successDeletionHeader");
        Assertions.assertEquals(deletedMessage, pageMessage.getText(),
                "Page message doesn't match. Expected: " + deletedMessage + ". Actual: " + pageMessage.getText());
        LOGGER.info("Item deleted successfully.");
    }

    public void clickGOTOadminzoneButton(){
        actions.waitForElementClickable("adminPage.adminZoneButton");
        actions.clickElement("adminPage.adminZoneButton");
    }

    public void clickOnViewUsersButton(){
        actions.waitForElementClickable("adminPage.ViewUsersButton");
        actions.clickElement("adminPage.ViewUsersButton");
    }
    public void clickDisableButton(){
        if (actions.isElementVisible("adminPage.enableButton")) {
            actions.clickElement("adminPage.enableButton");
        }
        actions.waitForElementClickable("adminPage.disableButton");
        actions.clickElement("adminPage.disableButton");
    }

    public void clickOnEnableButton(){
        if (actions.isElementVisible("adminPage.disableButton")) {
            actions.clickElement("adminPage.disableButton");
        }
        actions.waitForElementClickable("adminPage.enableButton");
        actions.clickElement("adminPage.enableButton");
    }
}
