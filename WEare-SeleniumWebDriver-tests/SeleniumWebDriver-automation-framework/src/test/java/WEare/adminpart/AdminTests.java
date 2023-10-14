package WEare.adminpart;

import WEare.BaseTest;
import org.junit.jupiter.api.*;

public class AdminTests extends BaseTest {

    @BeforeAll
    public static void testSetUp(){
        homePage.navigateToHomePage();
        api.registerUser(adminUsername, adminPassword);
        api.registerUser(usernameRandom,passwordRandom);
        api.updateUserProfile(usernameRandom,passwordRandom, PROFILE_FOR_ENABLE_DISABLE,lastNameRandom);
    }

    @BeforeEach
    public void adminLogIn() {
        homePage.navigateToHomePage();
        login(adminUsername, adminPassword);
        adminPage.validateAdminPageNavigated();
        //apiPost=api.createPost(usernameRandom,passwordRandom,POST_MESSAGE);
        //api.createComment(usernameRandom,passwordRandom,TEST_SET_UP_COMMENT,apiPost.postId);
    }
    @AfterEach
    public void adminLogout(){
        homePage.navigateToHomePage();
        loginPage.clickOnLogOutButton();
    }

    @Test
    public void editLatestPost_when_editPostClicked() {
        apiPost=api.createPost(usernameRandom,passwordRandom,POST_MESSAGE);
        homePage.navigateToLatestPosts();
        postsPage.clickExplorePost();
        adminPage.clickEditButton();
        adminPage.editPostInformation();
        homePage.navigateToLatestPosts();
        adminPage.validatePostEdited();
    }

    @Test
    public void deleteLatestPost_when_deletePostClicked() {
        apiPost=api.createPost(usernameRandom,passwordRandom,POST_MESSAGE);
        homePage.navigateToLatestPosts();
        postsPage.clickExplorePost();
        adminPage.clickDeleteButton();
        adminPage.deletePost();
        adminPage.validateDeletion();
    }

    @Test
    public void editLatestComment_when_editCommentClicked() {
        apiPost=api.createPost(usernameRandom,passwordRandom,POST_MESSAGE);
        api.createComment(usernameRandom,passwordRandom,TEST_SET_UP_COMMENT,apiPost.postId);
        homePage.navigateToLatestPosts();
        postsPage.clickExplorePost();
        postsPage.clickShowComments();
        commentsPage.clickEditComment();
        commentsPage.editComment();
        // postsAndCommentsPage.validateCommentEdited();
    }


    @Test
    public void deleteLatestComment_when_deleteCommentClicked() {
        apiPost=api.createPost(usernameRandom,passwordRandom,POST_MESSAGE);
        api.createComment(usernameRandom,passwordRandom,TEST_SET_UP_COMMENT,apiPost.postId);
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
        searchingPage.seeCurrentUserProfileByName(PROFILE_FOR_ENABLE_DISABLE);
        searchingPage.assertElementPresent("adminPage.disableButton");
        adminPage.clickDisableButton();
        searchingPage.assertElementPresent("adminPage.enableButton");
        adminPage.clickOnEnableButton();
    }
    @Test
    public void enableDisabledUserAccount_when_enableClicked() {
        adminPage.clickGOTOadminzoneButton();
        adminPage.clickOnViewUsersButton();
        searchingPage.seeCurrentUserProfileByName(PROFILE_FOR_ENABLE_DISABLE);
        adminPage.clickOnEnableButton();
        searchingPage.assertElementPresent("adminPage.disableButton");
    }





}
