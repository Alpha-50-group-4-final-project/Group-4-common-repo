package pages.WEare;

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
    }

    public void clickOnExplorePostButton(String name) {
        actions.waitForElementClickable("posts.explorePostsByUserName",name);
        actions.clickElement("posts.explorePostsByUserName",name);
    }

    public void clickOnLikePostButton() {
        actions.waitForElementClickable("posts.likePost");
        actions.clickElement("posts.likePost");
        actions.waitForElementPresent("posts.dislikePostButton");
    }

    public void clickOnDislikePostButton() {
        actions.waitForElementClickable("posts.dislikePostButton");
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

    public void goToLatestPosts() {
        actions.waitForElementPresent("posts.latestPost");
        actions.waitForElementClickable("posts.latestPost");
        actions.clickElement("posts.latestPost");
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
    }
}
