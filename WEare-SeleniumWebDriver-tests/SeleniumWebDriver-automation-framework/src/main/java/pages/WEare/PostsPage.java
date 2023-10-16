package pages.WEare;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.opentest4j.AssertionFailedError;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static com.telerikacademy.testframework.Utils.LOGGER;
import static com.telerikacademy.testframework.Utils.getUIMappingByKey;
import static java.lang.String.format;


public class PostsPage extends WEareBasePage {


    public PostsPage(WebDriver driver) {
        super(driver, "latestPostsPage");
    }

    public void submitDeletion() {
        actions.waitForElementClickable("posts.submitButton");
        actions.clickElement("posts.submitButton");
    }

    public void clickEditPost() {
        actions.waitForElementClickable("posts.editPost");
        actions.clickElement("posts.editPost");
        validateHeader("Edit post");
    }

    public void explorePost(String name) {
        actions.waitForElementVisible("posts.explorePostsByUserName", name);
        actions.waitForElementPresent("posts.explorePostsByUserName", name);
        actions.waitForElementClickable("posts.explorePostsByUserName", name);
        actions.clickElement("posts.explorePostsByUserName", name);
        validateHeader("Explore post");
    }

    public void clickShowComments() {
        actions.waitForElementClickable("posts.showCommentsButton");
        actions.moveToElementAndClickOnit("posts.showCommentsButton");
    }

    public void likePostByUsername(String name) {
        actions.waitForElementClickable("posts.clickLikeByUserName", name);
        actions.clickElement("posts.clickLikeByUserName", name);
        actions.waitForElementVisible("posts.dislikePostButton");
    }

    public void dislikePostByUsername(String name) {
        actions.waitForElementClickable("posts.clickDislikeByUserName", name);
        actions.clickElement("posts.clickDislikeByUserName", name);
        actions.waitForElementVisible("posts.likePost");
    }


    public void selectPostVisibility() {
        actions.waitForElementClickable("posts.visibility");
        actions.clickElement("posts.visibility");
        actions.clickElement("posts.publicVisibility");
    }

    public void submitPost() {
        actions.clickElement("posts.savePostButton");
    }

    public void writePostMessage() {
        actions.clickElement("posts.commentField");
        actions.typeValueInField(getUIMappingByKey("postPage.postMessage"), "posts.commentField");
        LOGGER.info(format("Comment was set to : \"%s\"", getUIMappingByKey("postPage.postMessage")));
    }

    public void clickAddNewPost() {
        actions.waitForElementClickable("posts.addNewPost");
        actions.clickElement("posts.addNewPost");
    }

    public void confirmDeletion() {
        actions.waitForElementClickable("posts.deleteDropDown");
        actions.clickElement("posts.deleteDropDown");
        actions.waitForElementClickable("posts.selectDeleteDropDown");
        actions.clickElement("posts.selectDeleteDropDown");
    }

    public void clickDeletePost() {
        actions.waitForElementClickable("posts.deletePost");
        actions.clickElement("posts.deletePost");
        validateHeader("Delete post");
    }

    public void validatePostCreated() {
        try {
            actions.assertElementAttribute("postPage.postContent.validation", "innerText",
                    getUIMappingByKey("postPage.postMessage"));
            LOGGER.info("New public post was created successfully.");
        } catch (AssertionFailedError e) {
            Assertions.fail("Post was not created.");
        }

    }

    public void validatePostEdited() {
        try {
            actions.assertElementAttribute("postPage.editPostContent.validation", "innerText",
                    getUIMappingByKey("postPage.postMessage.edit"));
            LOGGER.info("Public post was edited successfully.");
        } catch (AssertionFailedError e) {
            Assertions.fail("Post was not edited.");
        }

    }

    public void validatePostLiked() {
        try {
            actions.assertElementPresent("posts.dislikePostButton");
            LOGGER.info("Public post was liked successfully.");
        } catch (AssertionFailedError e) {
            Assertions.fail("Post was not liked.");
        }

    }

    public void clickBrowsePublicPost() {
        actions.waitForElementClickable("posts.browsePublicPosts");
        actions.clickElement("posts.browsePublicPosts");
    }

    public void validatePostDeleted() {
        try {
            actions.assertElementAttribute("post.deleteMessage", "innerHTML", "Post deleted successfully");
            LOGGER.info("Public post was deleted successfully.");
        } catch (AssertionFailedError e) {
            Assertions.fail("Public post was not deleted successfully.");
        }

    }

    public void assertPublicPostOrdered() {
        List<WebElement> rankList = driver.findElements(By.xpath("//span[@class='seen']"));
        List<String> ranks = rankList.stream().map(n -> n.getText()).collect(Collectors.toList());
        List<String> unsortedRanks = List.copyOf(ranks);
        Collections.sort(ranks);
        Assertions.assertTrue(ranks.equals(unsortedRanks), "Public posts were not ordered by date.");
        LOGGER.info("Public posts ordered by date.");
    }

    public void editPostMessage() {
        actions.clickElement("posts.commentField");
        actions.typeValueInField(getUIMappingByKey("postPage.postMessage.edit"), "posts.commentField");
        LOGGER.info(format("Comment was set to : \"%s\"", getUIMappingByKey("postPage.postMessage.edit")));
    }

    public void validatePostDisliked() {
        try {
            actions.assertElementPresent("posts.likePost");
            LOGGER.info("Public post was disliked successfully.");
        } catch (AssertionFailedError e) {
            Assertions.fail("Post was not disliked.");
        }

    }
}
