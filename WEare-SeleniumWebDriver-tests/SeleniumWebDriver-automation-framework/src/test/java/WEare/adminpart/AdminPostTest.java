package WEare.adminpart;

import WEare.BaseTest;
import org.junit.jupiter.api.*;
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class AdminPostTest extends BaseTest {

    @BeforeAll
    public static void testSetUp(){
        api.registerUser(usernameRandom,passwordRandom);
        apiPost=api.createPost(usernameRandom,passwordRandom,POST_MESSAGE);
        api.registerUser(adminUsername, adminPassword);
        login(adminUsername, adminPassword);
        adminPage.validateAdminPageNavigated();
    }

    @Test
    @Order(1)
    public void editLatestPost_when_editPostClicked() {
        homePage.navigateToLatestPosts();
        //search post by username?
        postsPage.clickExplorePost();
        adminPage.clickEditButton();
        adminPage.editPostInformation();
        homePage.navigateToLatestPosts();
        adminPage.validatePostEdited();
    }
    @Test
    @Order(2)
    public void deleteLatestPost_when_deletePostClicked() {
        homePage.navigateToLatestPosts();
        //search post by name?
        postsPage.clickExplorePost();
        adminPage.clickDeleteButton();
        adminPage.deletePost();
        adminPage.validateDeletion();
    }
}
