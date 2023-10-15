package WEare.privatepart;

import WEare.BaseTest;
import jdk.jfr.Label;
import org.junit.jupiter.api.*;

import static com.telerikacademy.testframework.Utils.getUIMappingByKey;


public class CommentsTests extends BaseTest {
    private static String commentBody = getUIMappingByKey("commentPage.validCommentMessage");

    @BeforeAll
    public static void testSetup() {
        api.registerUser(usernameRandom, passwordRandom);
        apiPost = api.createPost(usernameRandom, passwordRandom, postMessage);
        login(usernameRandom, passwordRandom);
    }

    @AfterAll
    public static void returnHome() {
        homePage.navigateToHomePage();
        if (actions.isElementVisible("homePage.LogoutButton")) {
            actions.clickElement("homePage.LogoutButton");
        }
        api.deletePost(usernameRandom, passwordRandom, apiPost.postId);
    }

    @Test
    @Label("Jira FPW-101")
    public void addValidComment_when_postCommentButtonClicked() {
        homePage.navigateToLatestPosts();
        postsPage.clickOnExplorePostButton(usernameRandom);
        commentsPage.writeComment();
        commentsPage.clickOnPostCommentButton();
        commentsPage.validateCommentCreated();
    }

    @Test
    @Label("Jira FPW-121")
    public void editComment_when_validTextAdded() {
        api.createComment(usernameRandom, passwordRandom, commentBody, apiPost.postId);
        homePage.navigateToLatestPosts();
        postsPage.clickOnExplorePostButton(usernameRandom);
        commentsPage.clickOnShowCommentsButton();
        commentsPage.clickEditComment();
        commentsPage.editComment();
        commentsPage.validateCommentEdited();
    }

    @Test
    @Label("Jira FPW-133")
    public void likeComment_when_likedButtonClicked() {
        api.createComment(usernameRandom, passwordRandom, commentBody, apiPost.postId);
        homePage.navigateToLatestPosts();
        postsPage.clickOnExplorePostButton(usernameRandom);
        commentsPage.clickOnShowCommentsButton();
        commentsPage.clickOnLikeButton();
        commentsPage.validateCommentLiked();
    }
    @Test
    @Label("Jira FPW-135")
    public void unlikeComment_when_unlikedButtonClicked() {
        api.createComment(usernameRandom, passwordRandom, commentBody, apiPost.postId);
        homePage.navigateToLatestPosts();
        postsPage.clickOnExplorePostButton(usernameRandom);
        commentsPage.clickOnShowCommentsButton();
        commentsPage.clickOnUnlikeButton();
        commentsPage.validateCommentUnliked();
    }

    @Test
    @Label("Jira FPW-124")
    public void deleteComment_when_deleteButtonClicked() {
        api.createComment(usernameRandom, passwordRandom, commentBody, apiPost.postId);
        homePage.navigateToLatestPosts();
        postsPage.clickOnExplorePostButton(usernameRandom);
        commentsPage.clickOnShowCommentsButton();
        commentsPage.clickDeleteComment();
        commentsPage.deleteComment();
        commentsPage.validateCommentDeleted();
    }

}
