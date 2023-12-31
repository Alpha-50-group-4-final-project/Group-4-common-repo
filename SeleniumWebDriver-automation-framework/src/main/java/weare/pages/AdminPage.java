package weare.pages;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.opentest4j.AssertionFailedError;

import static com.telerikacademy.testframework.Utils.*;

public class AdminPage extends WEareBasePage {

    public String deletedMessage = "Post deleted successfully";
    public String deleteConfirmation = "delete";

    public AdminPage(WebDriver driver) {
        super(driver, "homePage");
    }


    public void validateAdminPageNavigated() {
        try {
            actions.waitForElementVisible("adminPage.adminZoneButton");
            actions.assertElementAttribute("adminPage.adminZoneButton", "innerText", "GO TO admin zone");
            LOGGER.info("Admin logged in.");
        } catch (AssertionFailedError e) {
            Assertions.fail("Admin is not logged in.");
        }
    }

    public void clickEditButton() {
        actions.waitForElementClickable("adminPage.editPostButton");
        actions.clickElement("adminPage.editPostButton");
        validateHeader("Edit post");
    }

    public void editPostInformation() {
        actions.waitForElementClickable("adminPage.postVisibility");
        actions.assertElementPresent("adminPage.postVisibility");
        actions.clickElement("adminPage.postVisibility");
        actions.typeValueInField(getUIMappingByKey("visibility.public"), "adminPage.postVisibility");
        actions.waitForElementPresent("adminPage.editMessage");
        actions.typeValueInField(getUIMappingByKey("postPage.postMessage.edit"), "adminPage.editMessage");
        actions.assertElementPresent("adminPage.submitButton");
        actions.clickElement("adminPage.submitButton");
    }

    public void validatePostEdited() {
        try {
            actions.waitForElementVisible("adminPage.editedPostMessage.validation");
            actions.assertElementAttribute("adminPage.editedPostMessage.validation", "innerText", getUIMappingByKey("postPage.postMessage.edit"));
            LOGGER.info("Admin edited post successfully.");
        } catch (AssertionFailedError e) {
            Assertions.fail("Admin did not edit the post successfully.");
        }
    }

    public void clickDeleteButton() {
        actions.waitForElementVisible("adminPage.deletePostButton");
        actions.waitForElementClickable("adminPage.deletePostButton");
        actions.assertElementPresent("adminPage.deletePostButton");
        actions.clickElement("adminPage.deletePostButton");
        validateHeader("Delete post");
    }

    public void deletePost() {
        actions.waitForElementClickable("adminPage.deleteConfirmation");
        actions.assertElementPresent("adminPage.deleteConfirmation");
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

    public void clickGOTOadminzoneButton() {
        actions.waitForElementClickable("adminPage.adminZoneButton");
        actions.assertElementPresent("adminPage.adminZoneButton");
        actions.clickElement("adminPage.adminZoneButton");
    }

    public void clickOnViewUsersButton() {
        actions.waitForElementClickable("adminPage.ViewUsersButton");
        actions.assertElementPresent("adminPage.ViewUsersButton");
        actions.clickElement("adminPage.ViewUsersButton");
    }

    public void clickDisableButton() {
        actions.waitForElementVisible("adminPage.disableButton");
        actions.assertElementPresent("adminPage.disableButton");
        if (actions.isElementVisible("adminPage.enableButton")) {
            actions.assertElementPresent("adminPage.enableButton");
            actions.clickElement("adminPage.enableButton");
        }
        actions.waitForElementClickable("adminPage.disableButton");
        actions.assertElementPresent("adminPage.disableButton");
        actions.clickElement("adminPage.disableButton");
    }

    public void clickOnEnableButton() {
        if (actions.isElementVisible("adminPage.disableButton")) {
            actions.assertElementPresent("adminPage.disableButton");
            actions.clickElement("adminPage.disableButton");
        }
        actions.waitForElementClickable("adminPage.enableButton");
        actions.assertElementPresent("adminPage.enableButton");
        actions.clickElement("adminPage.enableButton");
    }

    public void validateProfileDisabled() {
        try {
            actions.assertElementAttribute("adminPage.enableButton", "value", "enable");
            LOGGER.info(getUIMappingByKey("adminPage.disableProfile.success"));
        } catch (AssertionFailedError e) {
            Assertions.fail("Profile was not successfully disabled.");
        }
    }

    public void validateProfileEnabled() {
        try {
            actions.assertElementAttribute("adminPage.disableButton", "value", "disable");
            LOGGER.info(getUIMappingByKey("adminPage.enableProfile.success"));
        } catch (AssertionFailedError e) {
            Assertions.fail("Profile was not successfully enabled.");
        }
    }
}
