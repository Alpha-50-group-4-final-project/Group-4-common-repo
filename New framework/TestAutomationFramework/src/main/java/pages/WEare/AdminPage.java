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
        super(driver, "WEare.homePage");
    }


    public void validateAdminPageNavigated() {
        actions.assertElementAttribute("WEare.adminPage.adminZoneButton", "innerText", "GO TO admin zone");
        LOGGER.info("Admin logged in.");
    }

    public void clickEditButton() {
        actions.waitForElementClickable("WEare.adminPage.editPostButton");
        actions.clickElement("WEare.adminPage.editPostButton");
        validateHeader("Edit post");
    }

    public void editPostInformation() {
        actions.clickElement("WEare.adminPage.postVisibility");
        actions.typeValueInField(postVisibilityPublic, "WEare.adminPage.postVisibility");
        actions.typeValueInField(updatedMessage, "WEare.adminPage.editMessage");
        actions.clickElement("WEare.adminPage.submitButton");
    }

    public void validatePostEdited() {
        actions.assertElementAttribute("WEare.adminPage.editedPostMessage", "innerText", updatedMessage);
        LOGGER.info("Admin edited post successfully.");
    }

    public void clickDeleteButton() {
        actions.waitForElementClickable("WEare.adminPage.deletePostButton");
        actions.clickElement("WEare.adminPage.deletePostButton");
        validateHeader("Delete post");
    }

    public void deletePost() {
        actions.clickElement("WEare.adminPage.deleteConfirmation");
        actions.typeValueInField(deleteConfirmation, "WEare.adminPage.deleteConfirmation");
        actions.clickElement("WEare.adminPage.submitButton");
    }

    public void validateDeletion() {
        WebElement pageMessage = driver.findElement(By.xpath("//h1[@class='mb-3 bread']"));
        actions.waitForElementVisible("WEare.adminPage.successDeletionHeader");
        Assertions.assertEquals(deletedMessage, pageMessage.getText(),
                "Page message doesn't match. Expected: " + deletedMessage + ". Actual: " + pageMessage.getText());
        LOGGER.info("Item deleted successfully.");
    }

    public void clickGOTOadminzoneButton(){
        actions.waitForElementClickable("WEare.adminPage.adminZoneButton");
        actions.clickElement("WEare.adminPage.adminZoneButton");
    }

    public void clickOnViewUsersButton(){
        actions.waitForElementClickable("adminPage.ViewUsersButton");
        actions.clickElement("adminPage.ViewUsersButton");
    }
    public void clickDisableButton(){
        actions.waitForElementClickable("adminPage.disableButton");
        actions.clickElement("adminPage.disableButton");
    }

    public void clickOnEnableButton(){
        actions.waitForElementClickable("adminPage.enableButton");
        actions.clickElement("adminPage.enableButton");
    }
}
