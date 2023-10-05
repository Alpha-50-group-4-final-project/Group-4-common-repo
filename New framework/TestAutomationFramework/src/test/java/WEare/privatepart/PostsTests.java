package WEare.privatepart;

import WEare.BaseTest;
import org.junit.jupiter.api.*;

import static com.telerikacademy.testframework.Utils.LOGGER;


@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class PostsTests extends BaseTest {
    public static final String EDITED_POST_MESSAGE = "This post was edited by Selenium WebDriver";

    private static final String POST_MESSAGE="This post was made by Selenium WebDriver";
    private static final String VALID_COMMENT_MESSAGE = "This is a valid comment made by Selenium WebDriver";

    @BeforeAll
    public static void testSetup(){
        registerUser(usernameRandom,passwordRandom);
        login(usernameRandom, passwordRandom);
    }

    @AfterAll
    public void returnHome() {
        homePage.navigateToHomePage();
        loginPage.clickOnLogOutButton();
    }

    @Test
   @Order(1)
   // @Description("Create a new public post")
    public void createPublicPost_when_addNewPostButtonClicked() {
        postsPage. clickOnAddNewPostButton();
        postsPage. clickOnPostVisibilityButton();
        postsPage.postPublicVisibilityChoice();
        postsPage.typeMessageInMessageField(POST_MESSAGE);
        postsPage. clickOnSavePostButton();
        postsPage.validatePostCreated();
    }

    @Test
    @Order(2)
    //@Description("Validate the user can edit his public post")
    public void editPublicPost_when_editPostButtonClicked() {
        homePage.navigateToLatestPosts();

        postsPage.clickOnExplorePostButton(usernameRandom);
        postsPage.clickOnEditPostButton();
        postsPage.clickOnPostVisibilityButton();
        postsPage.postPublicVisibilityChoice();
        postsPage.typeMessageInMessageField(EDITED_POST_MESSAGE);
        postsPage.clickOnSavePostButton();
        postsPage.validatePostEdited();
    }

    @Test
    @Order(3)
    //@Description("Validate user can like a post")
    public void likePublicPost_when_likeButtonClicked() {
       // commentsPage.goToLatestComment();
        homePage.navigateToLatestPosts();
        postsPage.clickOnLikePostButton();
        postsPage.validatePostLiked();
    }

    @Test
    @Order(4)
    public void deletePost_when_deleteButtonClicked(){
        //create post
           // postsPage.goToLatestPosts();
        postsPage.clickBrowsePublicPost();

            postsPage.clickOnExplorePostButton(usernameRandom);
            postsPage.clickOnDeletePostButton();
            postsPage.choosingDeletePostOption();
            postsPage.clickPostSubmitButton();
            postsPage.validatePostDeleted();

    }
}

//    @Test
//    @Order(4)
//    //@Description("Validate user can dislike a post")
//    public void dislikePublicPost_when_dislikeButtonClicked() {
//        postsPage.goToLatestPosts();
//        postsPage.clickOnDislikePostButton();
//        actions.assertElementPresent("posts.likePost");
//        LOGGER.info("Public post was disliked successfully.");
//    }

