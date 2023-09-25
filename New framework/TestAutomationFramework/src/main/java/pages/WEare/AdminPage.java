package pages.WEare;

import org.openqa.selenium.WebDriver;

import static com.telerikacademy.testframework.Utils.LOGGER;

public class AdminPage extends WEareBasePage {

    public String updatedMessage = "my edited post";
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
        actions.waitForElementClickable("WEare.adminPage.editPost");
        actions.clickElement("WEare.adminPage.editPost");
        validateHeader("Edit post");
    }

    public void editPostInformation() {
        actions.clickElement("WEare.adminPage.postVisibility");
        actions.typeValueInField(postVisibilityPublic, "WEare.adminPage.postVisibility");
        actions.typeValueInField(updatedMessage, "WEare.adminPage.editMessage");
        actions.clickElement("WEare.adminPage.saveButton");
    }
    public void validatePostEdited(){
        actions.assertElementAttribute("WEare.adminPage.editedPostMessage", "innerText", updatedMessage);
        LOGGER.info("Admin edited post successfully.");
    }

    public void clickDeleteButton() {
        actions.waitForElementClickable("WEare.adminPage.deletePost");
        actions.clickElement("WEare.adminPage.deletePost");
        validateHeader("Delete post");
    }

    public void deletePost(){
        actions.clickElement("WEare.adminPage.deleteConfimration");
        actions.typeValueInField(deleteConfirmation, "WEare.adminPage.deleteConfimration");
        actions.clickElement("WEare.adminPage.saveButton");
    }

    public void validatePostDeleted(){
        actions.assertElementAttribute("WEare.adminPage.successDeletionHeader", "value", deletedMessage);
        LOGGER.info("Admin deleted post successfully.");
    }
}
