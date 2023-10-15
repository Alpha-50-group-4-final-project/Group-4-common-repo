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
        submitComment();
        actions.pressKey(Keys.PAGE_UP);
        showComments();
    }


    public void showComments() {
        actions.waitForElementPresent("posts.showCommentsButton");
        actions.waitForElementVisible("posts.showCommentsButton");
        actions.waitForElementClickable("posts.showCommentsButton");
        actions.moveToElementAndClickOnit("posts.showCommentsButton");
    }

    public void submitComment() {
        actions.waitForElementClickable("posts.submitCommentButton");
        actions.clickElement("posts.submitCommentButton");
    }

    public void writeComment() {
        actions.waitForElementClickable("posts.commentField");
        actions.typeValueInField(getUIMappingByKey("commentPage.validCommentMessage"), "posts.commentField");
    }


    public void clickEditCommentButton() {
        actions.waitForElementClickable("commentsPage.editComment");
        actions.clickElement("commentsPage.editComment");
        validateHeader("Edit comment");

    }

    public void editComment() {
        actions.waitForElementClickable("posts.commentField");
        actions.clickElement("posts.commentField");
        actions.typeValueInField(getUIMappingByKey("editedCommentText"), "posts.commentField");
        actions.clickElement("adminPage.submitButton");
    }

    public void validateCommentEdited() {
        showComments();
        try {
            actions.assertElementAttribute("commentsPage.commentContent.validation", "innerText", getUIMappingByKey("editedCommentText"));
            LOGGER.info("Comment was successfully edited.");
        } catch (AssertionFailedError e) {
            Assertions.fail("Comment was not successfully edited.");
        }
    }

    public void deleteComment() {
        actions.waitForElementClickable("commentsPage.deleteComment");
        actions.clickElement("commentsPage.deleteComment");
        validateHeader("Delete comment");
    }

    public void deleteCommentConfirmation() {
        actions.waitForElementClickable("adminPage.deleteConfirmation");
        actions.clickElement("adminPage.deleteConfirmation");
        actions.typeValueInField(deleteConfirmation, "adminPage.deleteConfirmation");
        actions.clickElement("adminPage.submitButton");
    }

    public void validateCommentCreated() {
        showComments();
        try {
            actions.assertElementAttribute("commentsPage.commentContent.validation", "innerText", getUIMappingByKey("commentPage.validCommentMessage"));
            LOGGER.info("Comment was successfully created.");
        } catch (AssertionFailedError e) {
            Assertions.fail("Comment was not created.");
        }
    }


    public void likeComment() {
        if(actions.isElementVisible("commentsPage.dislikeButton")){
            actions.clickElement("commentsPage.dislikeButton");
        }
        actions.waitForElementClickable("commentsPage.likeComment");
        actions.clickElement("commentsPage.likeComment");
        actions.waitForElementVisible("commentsPage.dislikeButton");
    }

    public void validateCommentLiked() {
        try {
            actions.assertElementPresent("commentsPage.dislikeButton");
            LOGGER.info("Comment was successfully liked.");
        } catch (AssertionFailedError e) {
            Assertions.fail("Comment was not liked.");
        }
    }

    public void validateCommentDeleted() {
        try {
            validateHeader("Comment deleted successfully");
            LOGGER.info("Comment was deleted successfully.");
        } catch (AssertionFailedError e) {
            Assertions.fail("Comment was not successfully deleted.");
        }
    }

    public void unlikeComment() {
        if(actions.isElementVisible("commentsPage.likeComment")){
            actions.clickElement("commentsPage.likeComment");
        }
        actions.waitForElementClickable("commentsPage.dislikeButton");
        actions.clickElement("commentsPage.dislikeButton");
        actions.waitForElementVisible("commentsPage.likeComment");
    }

    public void validateCommentUnliked() {
        try {
            actions.assertElementPresent("commentsPage.likeComment");
            LOGGER.info("Comment was successfully disliked.");
        } catch (AssertionFailedError e) {
            Assertions.fail("Comment was not disliked.");
        }
    }
}
