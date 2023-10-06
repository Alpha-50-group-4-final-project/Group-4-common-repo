package WEare.adminpart;

import WEare.BaseTest;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class AdminTests extends BaseTest {

    public static final String TEST_SET_UP_COMMENT = "Automated comment by selenium driver";
    public String adminUsername = "adminTeam";
    public String adminPassword = "12345678";


    @BeforeEach
    public void adminLogIn() {
        homePage.navigateToHomePage();
        //actions.navigateToPage("http://localhost:8081/");
        login(adminUsername, adminPassword);
        adminPage.validateAdminPageNavigated();
        createPost();
        commentsPage.addComment(adminUsername);
    }
    @AfterEach
    public  void clean(){
        //actions.navigateToPage("http://localhost:8081/");
        homePage.navigateToHomePage();
        loginPage.clickOnLogOutButton();
    }

    @Test
    public void editLatestPost_when_editPostClicked() {
        homePage.navigateToLatestPosts();
        postsPage.clickExplorePost();
        adminPage.clickEditButton();
        adminPage.editPostInformation();
        homePage.navigateToLatestPosts();
        adminPage.validatePostEdited();
    }

    @Test
    public void deleteLatestPost_when_deletePostClicked() {
        homePage.navigateToLatestPosts();
        postsPage.clickExplorePost();
        adminPage.clickDeleteButton();
        adminPage.deletePost();
        adminPage.validateDeletion();
    }

    @Test
    public void editLatestComment_when_editCommentClicked() {
        homePage.navigateToLatestPosts();
        postsPage.clickExplorePost();
        postsPage.clickShowComments();
        commentsPage.clickEditComment();
        commentsPage.editComment();
        // postsAndCommentsPage.validateCommentEdited();
    }


    @Test
    public void deleteLatestComment_when_deleteCommentClicked() {
        homePage.navigateToLatestPosts();
        postsPage.clickExplorePost();
        postsPage.clickShowComments();
        commentsPage.clickDeleteComment();
        commentsPage.deleteComment();
        //validation
    }

    @Test
    public void disableAnotherUserAccount_when_disableClicked() {
        adminPage.clickGOTOadminzoneButton();
        adminPage.clickOnViewUsersButton();
        searchingPage.seeCurrentUserProfileByName("Ann-Marie");
        searchingPage.assertElementPresent("adminPage.disableButton");
        adminPage.clickDisableButton();
        searchingPage.assertElementPresent("adminPage.enableButton");
        adminPage.clickOnEnableButton();
    }
    @Test
    public void enableDisabledUserAccount_when_enableClicked() {
        adminPage.clickGOTOadminzoneButton();
        adminPage.clickOnViewUsersButton();
        searchingPage.seeCurrentUserProfileByName("Ann-Marie");
        adminPage.clickOnEnableButton();
        searchingPage.assertElementPresent("adminPage.disableButton");
    }





}
