package WEare.adminpart;

import WEare.BaseTest;
import jdk.jfr.Label;
import org.junit.jupiter.api.*;

import static com.telerikacademy.testframework.Utils.getUIMappingByKey;

//@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class AdminCommentTest extends BaseTest {
    private static final String postMessage = getUIMappingByKey("postPage.postMessage");
    private static final String commentMessage = getUIMappingByKey("commentPage.postMessage");

    @BeforeAll
    public static void testSetUp() {
        api.registerUser(usernameRandom, passwordRandom);
        apiPost = api.createPost(usernameRandom, passwordRandom, postMessage);
        api.createComment(usernameRandom, passwordRandom, commentMessage, apiPost.postId);
        api.registerUser(adminUsername, adminPassword);
        login(adminUsername, adminPassword);
    }

    @AfterAll
    public static void testTearDown() {
        logout();
        api.deletePost(adminUsername, adminPassword, apiPost.postId);
    }

    @Test
    @Label("Jira FPW-151")
    public void editOtherUsersComment_when_editCommentClicked() {
        homePage.navigateToLatestPosts();
        postsPage.clickExplorePost();
        postsPage.clickShowComments();
        commentsPage.clickEditComment();
        commentsPage.editComment();
        commentsPage.validateCommentEdited();
    }

    @Test
    @Label("Jira FPW-153")
    public void deleteOtherUsersComment_when_deleteCommentClicked() {
        homePage.navigateToLatestPosts();
        postsPage.clickExplorePost();
        postsPage.clickShowComments();
        commentsPage.clickDeleteComment();
        commentsPage.deleteComment();
        commentsPage.validateCommentDeleted();
    }
}
