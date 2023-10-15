package WEare.adminpart;

import WEare.BaseTest;
import jdk.jfr.Label;
import org.junit.jupiter.api.*;

import static com.telerikacademy.testframework.Utils.getUIMappingByKey;


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
    public static void clearData(){
        homePage.navigateToHomePage();
        if (actions.isElementVisible("homePage.LogoutButton")) {
            actions.clickElement("homePage.LogoutButton");
        }
        api.deletePost(adminUsername, adminPassword, apiPost.postId);
    }

    @Test
    @Label("Jira FPW-148")
    public void editOtherUsersPost_when_editPostClicked() {
        homePage.navigateToLatestPosts();
        postsPage.clickOnExplorePostButton(usernameRandom);
        adminPage.clickEditButton();
        adminPage.editPostInformation();
        adminPage.validatePostEdited();
    }

    @Test
    @Label("Jira FPW-150")
    public void deleteOtherUsersPost_when_deletePostClicked() {
        homePage.navigateToLatestPosts();
        postsPage.clickOnExplorePostButton(usernameRandom);
        adminPage.clickDeleteButton();
        adminPage.deletePost();
        adminPage.validateDeletion();
    }
}
