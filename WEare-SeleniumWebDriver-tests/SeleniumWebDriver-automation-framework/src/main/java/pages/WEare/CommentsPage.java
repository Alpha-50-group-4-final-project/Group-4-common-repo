package pages.WEare;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.opentest4j.AssertionFailedError;

import static com.telerikacademy.testframework.Utils.LOGGER;
import static com.telerikacademy.testframework.Utils.getUIMappingByKey;

public class CommentsPage extends WEareBasePage {

    public String deleteConfirmation = "delete";

    public CommentsPage(WebDriver driver) {
        super(driver, "");
    }

    public void addComment(String userName) {
        actions.waitForElementClickable("posts.explorePostsByUserName", userName);
        actions.clickElement("posts.explorePostsByUserName", userName);
        writeComment();
        clickOnPostCommentButton();
        actions.pressKey(Keys.PAGE_UP);
        clickOnShowCommentsButton();
    }


    public void clickOnShowCommentsButton() {
        actions.waitForElementPresent("posts.showCommentsButton");
        actions.waitForElementVisible("posts.showCommentsButton");
        actions.waitForElementClickable("posts.showCommentsButton");
        actions.moveToElementAndClickOnit("posts.showCommentsButton");
    }

    public void clickOnPostCommentButton() {
        actions.waitForElementClickable("posts.submitCommentButton");
        actions.clickElement("posts.submitCommentButton");
    }

    public void writeComment() {
        actions.waitForElementClickable("posts.commentField");
        actions.typeValueInField("This is a valid comment made by Selenium WebDriver", "posts.commentField");
    }


    public void clickEditComment() {
        actions.waitForElementClickable("commentsPage.editComment");
        actions.clickElement("commentsPage.editComment");
        validateHeader("Edit comment");

    }

    public void editComment() {
        // assertPageNavigated();
        actions.waitForElementClickable("posts.commentField");
        actions.clickElement("posts.commentField");
        actions.typeValueInField(getUIMappingByKey("editedCommentText"), "posts.commentField");
        actions.clickElement("adminPage.submitButton");
    }

    public void validateCommentEdited() {
        clickOnShowCommentsButton();
        try {
            actions.assertElementAttribute("commentsPage.commentContent.validation", "innerText", getUIMappingByKey("editedCommentText"));
            LOGGER.info("Comment was successfully edited.");
        } catch (AssertionFailedError e) {
            Assertions.fail("Comment was not successfully edited.");
        }
    }

    public void clickDeleteComment() {
        actions.waitForElementClickable("commentsPage.deleteComment");
        actions.clickElement("commentsPage.deleteComment");
        validateHeader("Delete comment");
    }

    public void deleteComment() {
        actions.clickElement("adminPage.deleteConfirmation");
        actions.typeValueInField(deleteConfirmation, "adminPage.deleteConfirmation");
        actions.clickElement("adminPage.submitButton");
    }

    public void validateCommentCreated() {
        actions.pressKey(Keys.PAGE_UP);
        clickOnShowCommentsButton();
        actions.assertElementAttribute("commentsPage.commentContent", "innerText", "This is a valid comment made by Selenium WebDriver");
        actions.assertElementPresent("posts.deleteCommentButton");
        LOGGER.info("Comment was successfully added to existing post.");
    }

    public void clickOnLikeButton() {
        actions.waitForElementClickable("commentsPage.likeComment");
        actions.clickElement("commentsPage.likeComment");
    }

    public void validateCommentLiked() {
        actions.waitForElementVisible("commentsPage.dislikeButton");
        actions.assertElementPresent("commentsPage.dislikeButton");
        // actions.assertElementAttribute("commentsPage.likesCount", "value", "1");
    }


    public void validateCommentDeleted() {
        try {
            validateHeader("Comment deleted successfully");
            LOGGER.info("Comment was deleted successfully.");
        } catch (AssertionFailedError e) {
            Assertions.fail("Comment was not successfully deleted.");
        }
    }
}
