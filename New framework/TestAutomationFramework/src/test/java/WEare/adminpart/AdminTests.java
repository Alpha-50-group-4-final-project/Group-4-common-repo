package WEare.adminpart;

import WEare.BaseTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class AdminTests extends BaseTest {

    public String adminUsername = "Ann";
    public String adminPassword = "123456";
    @BeforeEach
    public void adminLogIn() {
        login(adminUsername, adminPassword);
        adminPage.validateAdminPageNavigated();
        //create a post for edition/deletion
    }

    @Test
    public void editLatestPost_when_editPostClicked(){
        homePage.navigateToLatestPosts();
        latestPostPage.clickExplorePost();
        adminPage.clickEditButton();
        adminPage.editPostInformation();
        homePage.navigateToLatestPosts();
        adminPage.validatePostEdited();
    }

    @Test
    public void deleteLatestPost_when_deletePostClicked(){
        homePage.navigateToLatestPosts();
        latestPostPage.clickExplorePost();
        adminPage.clickDeleteButton();
        adminPage.deletePost();
        //adminPage.validatePostDeleted();
    }
}
