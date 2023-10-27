package WEare.privatepart;

import WEare.BaseTest;
import jdk.jfr.Label;
import org.junit.jupiter.api.*;

import static com.telerikacademy.testframework.Utils.getUIMappingByKey;

@DisplayName("CommentsTests")
public class CommentsTests extends BaseTest {
    private static final String commentBody = getUIMappingByKey("commentPage.validCommentMessage");
    private static final String postBody = getUIMappingByKey("postPage.postMessage");

    @BeforeAll
    public static void testSetup() {
        api.registerUser(usernameRandom, passwordRandom);
        apiPost = api.createPost(usernameRandom, passwordRandom, postBody);
        login(usernameRandom, passwordRandom);
    }

    @AfterAll
    public static void testClean() {
        api.deletePost(usernameRandom, passwordRandom, apiPost.postId);
    }

    @Test
    @Label("Jira FPW-101")
    @Tag("HappyPath")
    @DisplayName("Validate User Can Comment on a Public Post")
    public void addValidComment_when_postCommentButtonClicked() {
        postsPage.navigateToPage();
        postsPage.explorePost(usernameRandom);
        commentsPage.writeComment();
        commentsPage.submitComment();
        commentsPage.validateCommentCreated();
    }

    @Test
    @Label("Jira FPW-121")
    @Tag("HappyPath")
    @DisplayName("Validate User Can Edit His Comment on a Public Post")
    public void editComment_when_validTextAdded() {
        apiComment = api.createComment(usernameRandom, passwordRandom, commentBody, apiPost.postId);apiComment = api.createComment(usernameRandom, passwordRandom, commentBody, apiPost.postId);
        postsPage.navigateToPage();
        postsPage.explorePost(usernameRandom);
        commentsPage.showComments();
        commentsPage.clickEditCommentButton();
        commentsPage.editComment();
        commentsPage.validateCommentEdited();
        api.deleteComment(usernameRandom, passwordRandom, apiComment.commentId);
    }

    @Test
    @Label("Jira FPW-133")
    @Tag("HappyPath")
    @DisplayName("Validate User Can Like a Comment on public post")
    public void likeComment_when_likedButtonClicked() {
        apiComment = api.createComment(usernameRandom, passwordRandom, commentBody, apiPost.postId);
        postsPage.navigateToPage();
        postsPage.explorePost(usernameRandom);
        commentsPage.showComments();
        commentsPage.likeComment();
        commentsPage.validateCommentLiked();
        api.deleteComment(usernameRandom, passwordRandom, apiComment.commentId);
    }

    @Test
    @Label("Jira FPW-135")
    @Tag("HappyPath")
    @DisplayName("Validate User Can Unlike a Comment on public post")
    public void unlikeComment_when_unlikedButtonClicked() {
        apiComment = api.createComment(usernameRandom, passwordRandom, commentBody, apiPost.postId);
        postsPage.navigateToPage();
        postsPage.explorePost(usernameRandom);
        commentsPage.showComments();
        commentsPage.unlikeComment();
        commentsPage.validateCommentUnliked();
        api.deleteComment(usernameRandom, passwordRandom, apiComment.commentId);
    }

    @Test
    @Label("Jira FPW-124")
    @Tag("HappyPath")
    @DisplayName("Validate User Can Delete His Comment on a Public Post")
    public void deleteComment_when_deleteButtonClicked() {
        api.createComment(usernameRandom, passwordRandom, commentBody, apiPost.postId);
        postsPage.navigateToPage();
        postsPage.explorePost(usernameRandom);
        commentsPage.showComments();
        commentsPage.deleteComment();
        commentsPage.deleteCommentConfirmation();
        commentsPage.validateCommentDeleted();

    }
}
