package pages.WEare;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

import static com.telerikacademy.testframework.Utils.LOGGER;
import static java.lang.String.format;


public class PostsPage extends WEareBasePage {


    public PostsPage(WebDriver driver) {
        super(driver, "latestPostsPage");
    }

    public void clickPostSubmitButton() {
        actions.waitForElementClickable("posts.submitButton");
        actions.clickElement("posts.submitButton");
    }

    public void clickOnEditPostButton() {
        actions.waitForElementClickable("posts.editPost");
        actions.clickElement("posts.editPost");
        validateHeader("Edit post");
    }

    public void clickOnExplorePostButton(String name) {
        actions.waitForElementClickable("posts.explorePostsByUserName",name);
        actions.clickElement("posts.explorePostsByUserName",name);
        validateHeader("Explore post");
    }
    public void clickExplorePost(){
        actions.waitForElementClickable("latestPosts.exploreThisPost");
        actions.clickElement("latestPosts.exploreThisPost");
    }
    public void clickShowComments() {
        actions.pressKey(Keys.PAGE_DOWN);
        actions.waitForElementClickable("posts.showCommentsButton");
        actions.clickElement("posts.showCommentsButton");
    }

    public void clickOnLikePostButton() {
        actions.waitForElementClickable("posts.likePost");
        actions.clickElement("posts.likePost");
        actions.waitForElementPresent("posts.dislikePostButton");
    }

    public void clickOnDislikePostButton() {
        actions.waitForElementPresent("posts.dislikePostButton");
        actions.clickElement("posts.dislikePostButton");
        actions.waitForElementPresent("posts.likePost");
    }

    public void postPublicVisibilityChoice() {
        actions.waitForElementClickable("posts.publicVisibility");
        actions.clickElement("posts.publicVisibility");
    }

    public void clickOnPostVisibilityButton() {
        actions.waitForElementClickable("posts.visibility");
        actions.clickElement("posts.visibility");
    }

    public void clickOnSavePostButton() {
        actions.waitForElementClickable("posts.savePostButton");
        actions.clickElement("posts.savePostButton");
    }

    public void typeMessageInMessageField(String message) {
        actions.waitForElementClickable("posts.commentField");
        actions.clickElement("posts.commentField");
        actions.typeValueInField(message, "posts.commentField");
        LOGGER.info(format("Comment was set to : \"%s\"",message));
    }

    public void clickOnAddNewPostButton() {
        actions.waitForElementClickable("posts.addNewPost");
        actions.clickElement("posts.addNewPost");
    }
    public void choosingDeletePostOption() {
        actions.waitForElementClickable("posts.deleteDropDown");
        actions.clickElement("posts.deleteDropDown");
        actions.waitForElementClickable("posts.selectDeleteDropDown");
        actions.clickElement("posts.selectDeleteDropDown");
    }
    public void clickOnDeletePostButton() {
        actions.waitForElementClickable("posts.deletePost");
        actions.clickElement("posts.deletePost");
        validateHeader("Delete post");
    }


    public void validatePostCreated() {
        actions.assertElementPresent("posts.postExist");
        actions.assertElementPresent("posts.postIsPublic");
        LOGGER.info("New public post was created successfully.");
    }

    public void validatePostEdited() {
        actions.assertElementPresent("posts.postEditExist");
        LOGGER.info("Public post was edited successfully.");
    }

    public void validatePostLiked() {
        actions.assertElementPresent("posts.dislikePostButton");
        LOGGER.info("Public post was liked successfully.");
    }

    public void clickBrowsePublicPost() {
        actions.waitForElementClickable("posts.browsePublicPosts");
            actions.clickElement("posts.browsePublicPosts");
    }

    public void validatePostDeleted() {
        //actions.assertElementAttribute("post.deleteMessage", "value", "Post deleted successfully");
        validateHeader("Post deleted successfully");
        //actions.assertElementPresent("post.deleteMessage");
        LOGGER.info("Public post was deleted successfully.");
    }
}
