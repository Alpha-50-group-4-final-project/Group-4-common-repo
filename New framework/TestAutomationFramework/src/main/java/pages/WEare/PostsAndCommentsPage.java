package pages.WEare;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

import static com.telerikacademy.testframework.Utils.LOGGER;
import static pages.WEare.Constants.*;


public class PostsAndCommentsPage extends WEareBasePage {
    public String deleteConfirmation = "delete";

    public PostsAndCommentsPage(WebDriver driver) {
        super(driver, "");
    }


    public void createPost() {
        actions.waitForElementClickable(ADD_NEW_POST_BUTTON);
        actions.clickElement(ADD_NEW_POST_BUTTON);
        actions.waitForElementClickable(VISIBILITY_BUTTON);
        actions.clickElement(VISIBILITY_BUTTON);
        actions.waitForElementClickable(VISIBILITY_PUBLIC);
        actions.clickElement(VISIBILITY_PUBLIC);
        actions.waitForElementClickable(POST_MESSAGE_FIELD);
        actions.clickElement(POST_MESSAGE_FIELD);
        actions.typeValueInField(POST_MESSAGE, POST_MESSAGE_FIELD);
        actions.waitForElementClickable(POST_SAVE_BUTTON);
        actions.clickElement(POST_SAVE_BUTTON);
    }

    public void editPost() {
        actions.waitForElementClickable(LATEST_POSTS_BUTTON);
        actions.clickElement(LATEST_POSTS_BUTTON);
        actions.waitForElementClickable(EXPLORE_POST_BUTTON);
        actions.clickElement(EXPLORE_POST_BUTTON);
        actions.waitForElementClickable(EDIT_POST_BUTTON);
        actions.clickElement(EDIT_POST_BUTTON);
        actions.waitForElementClickable(VISIBILITY_BUTTON);
        actions.clickElement(VISIBILITY_BUTTON);
        actions.waitForElementClickable(VISIBILITY_PUBLIC);
        actions.clickElement(VISIBILITY_PUBLIC);
        actions.typeValueInField(POST_EDITED_MESSAGE, POST_MESSAGE_FIELD);
        actions.clickElement(POST_SAVE_BUTTON);

    }

    public void likePost() {
        actions.waitForElementPresent(LATEST_POSTS_BUTTON);
        actions.clickElement(LATEST_POSTS_BUTTON);
        actions.waitForElementClickable(LIKE_POST_BUTTON);
        actions.clickElement(LIKE_POST_BUTTON);
        actions.waitForElementPresent(DISLIKE_POST_BUTTON);
    }

    public void dislikePost() {
        actions.waitForElementPresent(LATEST_POSTS_BUTTON);
        actions.clickElement(LATEST_POSTS_BUTTON);
        actions.waitForElementClickable(DISLIKE_POST_BUTTON);
        actions.clickElement(DISLIKE_POST_BUTTON);
        actions.waitForElementPresent(LIKE_POST_BUTTON);
    }

    public void deletePost() {
        actions.waitForElementClickable(LATEST_POSTS_BUTTON);
        actions.clickElement(LATEST_POSTS_BUTTON);
        actions.waitForElementClickable(EXPLORE_POST_BUTTON);
        actions.clickElement(EXPLORE_POST_BUTTON);
        actions.waitForElementClickable(DELETE_POST_BUTTON);
        actions.clickElement(DELETE_POST_BUTTON);
        actions.waitForElementClickable(DROPDOWN_BUTTON);
        actions.clickElement(DROPDOWN_BUTTON);
        actions.waitForElementClickable(SELECT_DELETE_DROPDOWN);
        actions.clickElement(SELECT_DELETE_DROPDOWN);
        actions.waitForElementClickable(SUBMIT_DELETE_POST);
        actions.clickElement(SUBMIT_DELETE_POST);
    }

    public void goToLatestPosts() {
        actions.waitForElementClickable(LATEST_POSTS_BUTTON);
        actions.clickElement(LATEST_POSTS_BUTTON);
    }

    public void goToLatestComment() {
        goToLatestPosts();
        actions.waitForElementClickable(EXPLORE_PUBLIC_POSTS);
        actions.clickElement(EXPLORE_PUBLIC_POSTS);
    }

    public void addComment(String commentMessage) {
        actions.waitForElementClickable(LAST_PUBLIC_POST);
        actions.clickElement(LAST_PUBLIC_POST);
        actions.waitForElementClickable(COMMENTS_MESSAGE_FIELD);
        actions.typeValueInField(commentMessage, COMMENTS_MESSAGE_FIELD);
        actions.clickElement(COMMENT_SUBMIT_BUTTON);
        actions.pressKey(Keys.PAGE_UP);
        actions.waitForElementClickable(SHOW_COMMENTS_BUTTON);
        actions.clickElement(SHOW_COMMENTS_BUTTON);
    }

    public void clickShowComments() {
        actions.waitForElementClickable("Weare.latestPosts.showComments");
        actions.clickElement("Weare.latestPosts.showComments");
    }
    public void clickEditComment() {
        actions.waitForElementClickable("Weare.commentsPage.editComment");
        actions.clickElement("Weare.commentsPage.editComment");
        validateHeader("Edit comment");

    }
    public void editComment() {

        // assertPageNavigated();
        actions.waitForElementClickable("Weare.commentsPage.messageField");
        actions.clickElement("Weare.commentsPage.messageField");
        actions.typeValueInField("editedCommentText", "Weare.commentsPage.messageField");
        actions.clickElement("WEare.adminPage.submitButton");
    }

    public void validateCommentEdited() {
        actions.waitForElementClickable("Weare.latestPosts.showComments");
        actions.clickElement("Weare.latestPosts.showComments");

        actions.assertElementAttribute("Weare.commentPage.editedText", "innerText", "editedCommentText");
        LOGGER.info("Comment validated successfully.");
    }
    public void clickDeleteComment() {
        actions.waitForElementClickable("Weare.commentsPage.deleteComment");
        actions.clickElement("Weare.commentsPage.deleteComment");
        validateHeader("Delete comment");
    }
    public void deleteComment() {
        actions.clickElement("WEare.adminPage.deleteConfirmation");
        actions.typeValueInField(deleteConfirmation, "WEare.adminPage.deleteConfirmation");
        actions.clickElement("WEare.adminPage.submitButton");
    }



}
