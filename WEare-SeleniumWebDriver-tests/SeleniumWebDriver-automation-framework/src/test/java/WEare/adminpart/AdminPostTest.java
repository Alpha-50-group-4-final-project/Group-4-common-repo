package WEare.adminpart;

import WEare.BaseTest;
import jdk.jfr.Label;
import org.junit.jupiter.api.*;

import static com.telerikacademy.testframework.Utils.getUIMappingByKey;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
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

    @Test
    @Order(1)
    @Label("Jira FPW-148")
    public void editOtherUsersPost_when_editPostClicked() {
        homePage.navigateToLatestPosts();
        postsPage.clickExplorePost();
        adminPage.clickEditButton();
        adminPage.editPostInformation();
        homePage.navigateToLatestPosts();
        adminPage.validatePostEdited();
    }

    @Test
    @Order(2)
    @Label("Jira FPW-150")
    public void deleteOtherUsersPost_when_deletePostClicked() {
        homePage.navigateToLatestPosts();
        postsPage.clickExplorePost();
        adminPage.clickDeleteButton();
        adminPage.deletePost();
        adminPage.validateDeletion();
    }
}
