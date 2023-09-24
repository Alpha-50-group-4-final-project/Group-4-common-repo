package WEare.privatepart;

import WEare.BaseTest;
import jdk.jfr.Description;
import org.junit.jupiter.api.*;
import org.openqa.selenium.Keys;
import pages.WEare.LoginPage;
import pages.WEare.PostsAndCommentsPage;
import static pages.WEare.Constants.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.Random;


@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class PostsAndCommentsTests extends BaseTest {



    String username = "dinko";
    String password = "pass123";


    @BeforeAll
    public void init() throws IOException {
        login(username, password);
    }

    @AfterAll
    public void returnHome() {
        actions.waitForElementClickable(HOME_BUTTON);
        actions.clickElement(HOME_BUTTON);
        loginPage.clickOnLogOutButton();
    }

    @Test
    @Order(1)
    @Description("Create a new public post")
    public void createPublicPost() {
        postsAndCommentsPage.createPost();
        actions.assertElementPresent(POST_EXIST);
        actions.assertElementPresent(POST_IS_PUBLIC);
    }

    @Test
    @Order(2)
    @Description("Validate the user can edit his public post")
    public void editPublicPost() {
        postsAndCommentsPage.editPost();
        actions.assertElementPresent(POST_EDITED_EXIST);
    }

    @Test
    @Order(3)
    @Description("Validate user can like a post")
    public void likePublicPost() {
        postsAndCommentsPage.likePost();
        actions.assertElementPresent(DISLIKE_POST_BUTTON);
    }

    @Test
    @Order(4)
    @Description("Validate user can dislike a post")
    public void dislikePublicPost() {
        postsAndCommentsPage.dislikePost();
        actions.assertElementPresent(LIKE_POST_BUTTON);
    }

    @Test
    @Order(5)
    @Description("Validate registered user is able to add a valid comment to a post")
    public void addValidComment() {
        postsAndCommentsPage.addComment(VALID_COMMENT_MESSAGE);
        actions.assertElementPresent(DELETE_COMMENT_BUTTON);
    }

    @Test
    @Order(6)
    @Description("Validate user can delete his post")
    public void deletePublicPost() {
        postsAndCommentsPage.goToLatestPosts();
        actions.waitForElementClickable(EXPLORE_PUBLIC_POSTS);
        actions.clickElement(EXPLORE_PUBLIC_POSTS);
        postsAndCommentsPage.deletePost();
        actions.assertElementPresent(DELETE_MESSAGE);
    }
}
