package WEare.privatepart;

import WEare.BaseTest;
import org.junit.jupiter.api.*;



public class CommentsTests extends BaseTest {
    //private static final String VALID_COMMENT_MESSAGE = "This is a valid comment made by Selenium WebDriver";

    @BeforeAll
    public static void testSetup() {
        api.registerUser(usernameRandom, passwordRandom);
        apiPost = api.createPost(usernameRandom, passwordRandom, POST_MESSAGE);
        System.out.println(apiPost.postId);
        var two = api.createComment(usernameRandom, passwordRandom, POST_MESSAGE, apiPost.postId);
        login(usernameRandom, passwordRandom);
        postsPage.navigateToPage();
    }

    @AfterAll
    public static void returnHome() {
        homePage.navigateToHomePage();
        loginPage.clickOnLogOutButton();
    }


    @Test
    @Order(1)
    public void addValidComment_when_postCommentButtonClicked() {
        postsPage.navigateToPage();
        //postsPage.clickOnExplorePostButton(usernameRandom);
        postsPage.clickOnExplorePostButton(usernameRandom);
        commentsPage.writeComment();
        commentsPage.clickOnPostCommentButton();
//        commentsPage.validateCommentCreated();
    }

    @Test

    public void editComment_when_validTextAdded() {

        homePage.navigateToLatestPosts();
        //postsPage.clickOnExplorePostButton(usernameRandom);
        postsPage.clickOnExplorePostButton(usernameRandom);
        commentsPage.clickOnShowCommentsButton();
        commentsPage.clickEditComment();
        commentsPage.editComment();
        commentsPage.validateCommentEdited();
    }

    @Test

    public void likeComment_when_likedButtonClicked() {
        homePage.navigateToLatestPosts();
        //postsPage.clickOnExplorePostButton(usernameRandom);
        postsPage.clickOnExplorePostButton(usernameRandom);
        commentsPage.clickOnShowCommentsButton();
        commentsPage.clickOnLikeButton();
        commentsPage.validateCommentLiked();
    }

    @Test

    public void deleteComment_when_deleteButtonClicked() {
        homePage.navigateToLatestPosts();
        //postsPage.clickOnExplorePostButton(usernameRandom);
        postsPage.clickOnExplorePostButton(usernameRandom);
        commentsPage.clickOnShowCommentsButton();
        commentsPage.clickDeleteComment();
        commentsPage.deleteComment();
        commentsPage.validateCommentDeleted();
    }


}
