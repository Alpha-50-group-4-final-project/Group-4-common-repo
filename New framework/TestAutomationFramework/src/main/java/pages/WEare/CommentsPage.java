package pages.WEare;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

import static com.telerikacademy.testframework.Utils.LOGGER;

public class CommentsPage extends WEareBasePage {

    public String deleteConfirmation = "delete";

    public CommentsPage(WebDriver driver) {
        super(driver, "");
    }

    public void addComment(String commentMessage,String userName) {
        clickOnExploreThisButtonByUsername(userName);
        writeComment(commentMessage);
        clickOnSubmitCommentButton();
        actions.pressKey(Keys.PAGE_UP);
        clickOnShowCommentsButton();
    }


    public void clickOnShowCommentsButton() {
        actions.waitForElementClickable("posts.showCommentsButton");
        actions.clickElement("posts.showCommentsButton");
    }

    public void clickOnSubmitCommentButton() {
        actions.waitForElementClickable("posts.submitCommentButton");
        actions.clickElement("posts.submitCommentButton");
    }

    public void writeComment(String commentMessage) {
        actions.waitForElementClickable("posts.commentField");
        actions.typeValueInField(commentMessage, "posts.commentField");
    }


    public void clickOnExploreThisButtonByUsername(String name) {
        actions.waitForElementClickable("posts.explorePostsByUserName",name);
        actions.clickElement("posts.explorePostsByUserName",name);
    }

    public void goToLatestComment() {
        PostsPage post = new PostsPage(actions.getDriver());
        post.goToLatestPosts();
        actions.waitForElementClickable("posts.browsePublicPosts");
        actions.clickElement("posts.browsePublicPosts");
    }

    public void clickShowComments() {
        actions.waitForElementPresent("latestPosts.showComments");

        actions.clickElement("latestPosts.showComments");
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
        actions.typeValueInField("editedCommentText", "posts.commentField");
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
