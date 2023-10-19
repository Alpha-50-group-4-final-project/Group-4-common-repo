package WEare.adminpart;

import WEare.BaseTest;
import jdk.jfr.Label;
import org.junit.jupiter.api.*;

import static com.telerikacademy.testframework.Utils.getUIMappingByKey;
@DisplayName("AdminCommentTests")
public class AdminCommentTest extends BaseTest {
    private static final String postMessage = getUIMappingByKey("postPage.postMessage");
    private static final String commentMessage = getUIMappingByKey("commentPage.postMessage");

    @BeforeAll
    public static void testSetUp() {
        api.registerUser(usernameRandom, passwordRandom);
        apiPost = api.createPost(usernameRandom, passwordRandom, postMessage);
        api.registerUser(adminUsername, adminPassword);
    }
    @BeforeEach
    public void createComment(){
        api.createComment(usernameRandom, passwordRandom, commentMessage, apiPost.postId);
        login(adminUsername, adminPassword);
    }
    @AfterEach
    public void deleteComment(){
        api.deleteComment(usernameRandom,passwordRandom, apiComment.commentId);
        logout();
    }

    @AfterAll
    public static void clearData() {
        api.deletePost(adminUsername, adminPassword, apiPost.postId);
    }

    @Test
    @Label("Jira FPW-151")
    @Tag("HappyPath")
    @DisplayName("Edit Comment with valid data provided")
    public void editOtherUsersComment_when_editCommentClicked() {
        homePage.navigateToLatestPosts();
        postsPage.explorePost(usernameRandom);
        postsPage.clickShowComments();
        commentsPage.clickEditCommentButton();
        commentsPage.editComment();
        commentsPage.validateCommentEdited();
    }

    @Test
    @Label("Jira FPW-153")
    @Tag("HappyPath")
    @DisplayName("Delete Comment")
    public void deleteOtherUsersComment_when_deleteCommentClicked() {
        homePage.navigateToLatestPosts();
        postsPage.explorePost(usernameRandom);
        postsPage.clickShowComments();
        commentsPage.deleteComment();
        commentsPage.deleteCommentConfirmation();
        commentsPage.validateCommentDeleted();
    }
}
