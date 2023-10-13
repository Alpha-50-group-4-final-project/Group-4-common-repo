package WEare.adminpart;

import WEare.BaseTest;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class AdminTests extends BaseTest {

    public static final String TEST_SET_UP_COMMENT = "Automated comment by selenium driver";
    public static final String PROFILE_FOR_ENABLE_DISSABLE = "Ann-Marie";
    public static String adminUsername = faker.name().firstName()+"admin";
    public static String adminPassword = "12345678";


    @BeforeAll
    public static void testSetUp(){
        homePage.navigateToHomePage();
        api.registerUser(adminUsername, adminPassword);
        api.registerUser(usernameRandom,passwordRandom);
        api.updateUserProfile(usernameRandom,passwordRandom,PROFILE_FOR_ENABLE_DISSABLE,"TADAM");
    }


    @BeforeEach
    public void adminLogIn() {
        homePage.navigateToHomePage();
        //actions.navigateToPage("http://localhost:8081/");
        login(adminUsername, adminPassword);
        adminPage.validateAdminPageNavigated();
        apiPost=api.createPost(adminUsername,adminPassword,POST_MESSAGE);
        api.createComment(adminUsername,adminPassword,TEST_SET_UP_COMMENT,apiPost.postId);
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
        searchingPage.seeCurrentUserProfileByName(PROFILE_FOR_ENABLE_DISSABLE);
        searchingPage.assertElementPresent("adminPage.disableButton");
        adminPage.clickDisableButton();
        searchingPage.assertElementPresent("adminPage.enableButton");
        adminPage.clickOnEnableButton();
    }
    @Test
    public void enableDisabledUserAccount_when_enableClicked() {
        adminPage.clickGOTOadminzoneButton();
        adminPage.clickOnViewUsersButton();
        searchingPage.seeCurrentUserProfileByName(PROFILE_FOR_ENABLE_DISSABLE);
        adminPage.clickOnEnableButton();
        searchingPage.assertElementPresent("adminPage.disableButton");
    }





}
