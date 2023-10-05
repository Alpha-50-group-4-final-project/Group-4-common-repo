package WEare.privatepart;

import WEare.BaseTest;
import org.junit.jupiter.api.*;


@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class CommentsTests extends BaseTest {
    //private static final String VALID_COMMENT_MESSAGE = "This is a valid comment made by Selenium WebDriver";

    @BeforeAll
    public static void testSetup() {
        // registerUser(usernameRandom,passwordRandom);
        //login(usernameRandom,passwordRandom);
        login("Dumbo", "12345678");
        createPost();
    }

    @AfterAll
    public void returnHome() {
        homePage.navigateToHomePage();
        loginPage.clickOnLogOutButton();
    }


    @Test
    @Order(1)
    public void addValidComment_when_postCommentButtonClicked() {
        //postsPage.navigateToPage();
        //postsPage.clickOnExplorePostButton(usernameRandom);
        postsPage.clickOnExplorePostButton("Dumbo");
        commentsPage.writeComment();
        commentsPage.clickOnPostCommentButton();
        commentsPage.validateCommentCreated();
    }

    @Test
    @Order(2)
    public void editComment_when_validTextAdded() {
        homePage.navigateToLatestPosts();
        //postsPage.clickOnExplorePostButton(usernameRandom);
        postsPage.clickOnExplorePostButton("Dumbo");
        commentsPage.clickOnShowCommentsButton();
        commentsPage.clickEditComment();
        commentsPage.editComment();
        commentsPage.validateCommentEdited();
    }

    @Test
    @Order(3)
    public void likeComment_when_likedButtonClicked() {
        homePage.navigateToLatestPosts();
        //postsPage.clickOnExplorePostButton(usernameRandom);
        postsPage.clickOnExplorePostButton("Dumbo");
        commentsPage.clickOnShowCommentsButton();
        commentsPage.clickOnLikeButton();
        commentsPage.validateCommentLiked();
    }

    @Test
    @Order(4)
    public void deleteComment_when_deleteButtonClicked() {
        homePage.navigateToLatestPosts();
        //postsPage.clickOnExplorePostButton(usernameRandom);
        postsPage.clickOnExplorePostButton("Dumbo");
        commentsPage.clickOnShowCommentsButton();
        commentsPage.clickDeleteComment();
        commentsPage.deleteComment();
        commentsPage.validateCommentDeleted();
    }


}
