package WEare.privatepart;

import WEare.BaseTest;
import jdk.jfr.Description;
import org.junit.jupiter.api.*;
import org.openqa.selenium.Keys;

import static com.telerikacademy.testframework.Utils.LOGGER;


@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class PostsAndCommentsTests extends BaseTest {
    public static final String EDITED_POST_MESSAGE = "This post was edited by Selenium WebDriver";
    static String username = "dinko";
    static String password = "pass123";
    private static final String POST_MESSAGE="This post was made by Selenium WebDriver";
    private static final String VALID_COMMENT_MESSAGE = "This is a valid comment made by Selenium WebDriver";

    @BeforeAll
    public static void testSetup(){
        login(username, password);
    }

    @AfterAll
    public void returnHome() {
        actions.waitForElementClickable("homeButton");
        actions.clickElement("homeButton");
        loginPage.clickOnLogOutButton();
    }

    @Test
    @Order(1)
    @Description("Create a new public post")
    public void createPublicPost() {
        postsPage. clickOnAddNewPostButton();
        postsPage. clickOnPostVisibilityButton();
        postsPage.postPublicVisibilityChoice();
        postsPage.typeMessageInMessageField(POST_MESSAGE);
        postsPage. clickOnSavePostButton();

        actions.assertElementPresent("posts.postExist");
        actions.assertElementPresent("posts.postIsPublic");
        LOGGER.info("New public post was created successfully.");
    }

    @Test
    @Order(2)
    @Description("Validate the user can edit his public post")
    public void editPublicPost() {
        postsPage.goToLatestPosts();

        postsPage.clickOnExplorePostButton(username);
        postsPage.clickOnEditPostButton();
        postsPage.clickOnPostVisibilityButton();
        postsPage.postPublicVisibilityChoice();
        postsPage.typeMessageInMessageField(EDITED_POST_MESSAGE);
        postsPage.clickOnSavePostButton();
        actions.assertElementPresent("posts.postEditExist");
        LOGGER.info("Public post was edited successfully.");
    }

    @Test
    @Order(3)
    @Description("Validate user can like a post")
    public void likePublicPost() {
        comments.goToLatestComment();

        postsPage.clickOnLikePostButton();
        actions.assertElementPresent("posts.dislikePostButton");
        LOGGER.info("Public post was liked successfully.");
    }

    @Test
    @Order(4)
    @Description("Validate user can dislike a post")
    public void dislikePublicPost() {
        postsPage.goToLatestPosts();
        postsPage.clickOnDislikePostButton();
        actions.assertElementPresent("posts.likePost");
        LOGGER.info("Public post was disliked successfully.");
    }

    @Test
    @Order(5)
    @Description("Validate registered user is able to add a valid comment to a post")
    public void addValidComment() {
        comments.clickOnLastPublicPost();
        comments.writeComment(VALID_COMMENT_MESSAGE);
        comments.clickOnSubmitCommentButton();
        actions.pressKey(Keys.PAGE_UP);
        comments.clickOnShowCommentsButton();

        actions.assertElementPresent("posts.deleteCommentButton");
        LOGGER.info("Comment: "+VALID_COMMENT_MESSAGE +" was successfully added to existing post.");
    }

    @Test
    @Order(6)
    @Description("Validate user can delete his post")
    public void deletePublicPost() {
        postsPage.goToLatestPosts();
        actions.waitForElementClickable("posts.browsePublicPosts");
        actions.clickElement("posts.browsePublicPosts");
        postsPage.goToLatestPosts();
        postsPage.clickOnExplorePostButton(username);
        postsPage.clickOnDeletePostButton();
        postsPage.choosingDeletePostOption();
        postsPage.clickPostSubmitButton();
        actions.assertElementPresent("post.deleteMessage");
        LOGGER.info("Public post was deleted successfully.");
    }
    @AfterAll
    public static void gettingOuOfAcc(){
        if(actions.isElementVisible("homePage.LogoutButton")){
            actions.clickElement("homePage.LogoutButton");
        }
    }
}
