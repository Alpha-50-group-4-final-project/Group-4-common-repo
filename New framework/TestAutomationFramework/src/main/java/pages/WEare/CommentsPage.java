package pages.WEare;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

import static com.telerikacademy.testframework.Utils.LOGGER;

public class CommentsPage extends WEareBasePage {

    public String deleteConfirmation = "delete";
    public CommentsPage(WebDriver driver) {
        super(driver, "");
    }

    public void addComment(String commentMessage) {
        clickOnLastPublicPost();
        writeComment(commentMessage);
        actions.clickElement("posts.submitCommentButton");
        actions.pressKey(Keys.PAGE_UP);
        actions.waitForElementClickable("posts.showCommentsButton");
        actions.clickElement("posts.showCommentsButton");
    }

    public void writeComment(String commentMessage) {
        actions.waitForElementClickable("posts.commentField");
        actions.typeValueInField(commentMessage, "posts.commentField");
    }


    public void clickOnLastPublicPost() {
        actions.waitForElementClickable("posts.lastPublicPost");
        actions.clickElement("posts.lastPublicPost");
    }

    public void goToLatestComment() {
        PostsPage post = new PostsPage(actions.getDriver());
        post.goToLatestPosts();
        actions.waitForElementClickable("posts.browsePublicPosts");
        actions.clickElement("posts.browsePublicPosts");
    }

    public void clickShowComments() {
        actions.waitForElementClickable("latestPosts.showComments");
        actions.clickElement("latestPosts.showComments");
    }

    public void clickEditComment() {
        actions.waitForElementClickable("commentsPage.editComment");
        actions.clickElement("commentsPage.editComment");
        validateHeader("Edit comment");

    }

    public void editComment() {

        // assertPageNavigated();
        actions.waitForElementClickable("commentsPage.messageField");
        actions.clickElement("commentsPage.messageField");
        actions.typeValueInField("editedCommentText", "commentsPage.messageField");
        actions.clickElement("adminPage.submitButton");
    }

    public void validateCommentEdited() {
        actions.waitForElementClickable("latestPosts.showComments");
        actions.clickElement("latestPosts.showComments");

        actions.assertElementAttribute("commentPage.editedText", "innerText", "editedCommentText");
        LOGGER.info("Comment validated successfully.");
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
}
