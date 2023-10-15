package WEare.privatepart;

import WEare.BaseTest;
import jdk.jfr.Label;
import org.junit.jupiter.api.*;

import static com.telerikacademy.testframework.Utils.getUIMappingByKey;


public class CommentsTests extends BaseTest {
    private static String commentBody = getUIMappingByKey("commentPage.validCommentMessage");
    private static String postBody = getUIMappingByKey("postPage.postMessage");

    @BeforeAll
    public static void testSetup() {
        api.registerUser(usernameRandom, passwordRandom);
        apiPost = api.createPost(usernameRandom, passwordRandom, postBody);
        login(usernameRandom, passwordRandom);
    }

    @AfterAll
    public static void returnHome() {

        homePage.navigateToHomePage();
        logout();
        api.deletePost(usernameRandom, passwordRandom, apiPost.postId);
    }

    @Test
    @Label("Jira FPW-101")
    public void addValidComment_when_postCommentButtonClicked() {
        homePage.navigateToLatestPosts();
        postsPage.explorePost(usernameRandom);
        commentsPage.writeComment();
        commentsPage.submitComment();
        commentsPage.validateCommentCreated();
    }

    @Test
    @Label("Jira FPW-121")
    public void editComment_when_validTextAdded() {
        api.createComment(usernameRandom, passwordRandom, commentBody, apiPost.postId);
        homePage.navigateToLatestPosts();
        postsPage.explorePost(usernameRandom);
        commentsPage.showComments();
        commentsPage.clickEditCommentButton();
        commentsPage.editComment();
        commentsPage.validateCommentEdited();
    }

    @Test
    @Label("Jira FPW-133")
    public void likeComment_when_likedButtonClicked() {
        api.createComment(usernameRandom, passwordRandom, commentBody, apiPost.postId);
        homePage.navigateToLatestPosts();
        postsPage.explorePost(usernameRandom);
        commentsPage.showComments();
        commentsPage.likeComment();
        commentsPage.validateCommentLiked();
    }
    @Test
    @Label("Jira FPW-135")
    public void unlikeComment_when_unlikedButtonClicked() {
        api.createComment(usernameRandom, passwordRandom, commentBody, apiPost.postId);
        homePage.navigateToLatestPosts();
        postsPage.explorePost(usernameRandom);
        commentsPage.showComments();
        commentsPage.unlikeComment();
        commentsPage.validateCommentUnliked();
    }

    @Test
    @Label("Jira FPW-124")
    public void deleteComment_when_deleteButtonClicked() {
        api.createComment(usernameRandom, passwordRandom, commentBody, apiPost.postId);
        homePage.navigateToLatestPosts();
        postsPage.explorePost(usernameRandom);
        commentsPage.showComments();
        commentsPage.deleteComment();
        commentsPage.deleteCommentConfirmation();
        commentsPage.validateCommentDeleted();
    }

}
