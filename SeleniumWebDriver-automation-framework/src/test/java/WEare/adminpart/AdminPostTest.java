package WEare.adminpart;

import WEare.BaseTest;
import jdk.jfr.Label;
import org.junit.jupiter.api.*;

import static com.telerikacademy.testframework.Utils.getUIMappingByKey;
@DisplayName("AdminPostsTests")
public class AdminPostTest extends BaseTest {
    private static final String postMessage = getUIMappingByKey("postPage.postMessage");

    @BeforeAll
    public static void testSetUp() {
        api.registerUser(usernameRandom, passwordRandom);
        apiPost = api.createPost(usernameRandom, passwordRandom, postMessage);
        api.registerUser(adminUsername, adminPassword);
        login(adminUsername, adminPassword);
        adminPage.validateAdminPageNavigated();
    }
    @AfterAll
    public static void deletePost(){
        api.deletePost(adminUsername, adminPassword, apiPost.postId);
    }

    @Test
    @Label("Jira FPW-148")
    @Tag("HappyPath")
    @DisplayName("Edit Post with valid data provided")
    public void editOtherUsersPost_when_editPostClicked() {
        postsPage.navigateToPage();
        postsPage.explorePost(usernameRandom);
        adminPage.clickEditButton();
        adminPage.editPostInformation();
        adminPage.validatePostEdited();
    }

    @Test
    @Label("Jira FPW-150")
    @Tag("HappyPath")
    @DisplayName("Delete Post")
    public void deleteOtherUsersPost_when_deletePostClicked() {
        postsPage.navigateToPage();
        postsPage.explorePost(usernameRandom);
        adminPage.clickDeleteButton();
        adminPage.deletePost();
        adminPage.validateDeletion();
    }
}
