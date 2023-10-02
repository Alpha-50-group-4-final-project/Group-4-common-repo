package WEare.adminpart;

import WEare.BaseTest;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class AdminTests extends BaseTest {

    public static final String TEST_SET_UP_COMMENT = "Automated comment by selenium driver";
    public String adminUsername = "teamadmin";
    public String adminPassword = "12345678";
    private static final String POST_MESSAGE="This post was made by Selenium WebDriver";

    @BeforeEach
    public void adminLogIn() {
        actions.navigateToPage("http://localhost:8081/");
        login(adminUsername, adminPassword);
        adminPage.validateAdminPageNavigated();
        createPost();
        comments.addComment(TEST_SET_UP_COMMENT);
    }
    @AfterEach
    public  void clean(){
        actions.navigateToPage("http://localhost:8081/");
      loginPage.clickOnLogOutButton();
    }

    @Test
    public void editLatestPost_when_editPostClicked() {
        homePage.navigateToLatestPosts();
        latestPostPage.clickExplorePost();
        adminPage.clickEditButton();
        adminPage.editPostInformation();
        homePage.navigateToLatestPosts();
        adminPage.validatePostEdited();
    }

    @Test
    public void deleteLatestPost_when_deletePostClicked() {
        homePage.navigateToLatestPosts();
        latestPostPage.clickExplorePost();
        adminPage.clickDeleteButton();
        adminPage.deletePost();
        adminPage.validateDeletion();
    }

    @Test
    public void editLatestComment_when_editCommentClicked() {
        homePage.navigateToLatestPosts();
        latestPostPage.clickExplorePost();
        comments.clickShowComments();
        comments.clickEditComment();
        comments.editComment();
        // postsAndCommentsPage.validateCommentEdited();
    }


    @Test
    public void deleteLatestComment_when_deleteCommentClicked() {
        homePage.navigateToLatestPosts();
        latestPostPage.clickExplorePost();
        comments.clickShowComments();
        comments.clickDeleteComment();
        comments.deleteComment();
        //validation
    }

    @Test
    public void disableAnotherUserAccount() {
        adminPage.clickGOTOadminzoneButton();
        adminPage.clickOnViewUsersButton();
        searchingPage.seeCurrentUserProfileByName("Ann");
        searchingPage.assertElementPresent("adminPage.disableButton");
        adminPage.clickDisableButton();
        searchingPage.assertElementPresent("adminPage.enableButton");
        adminPage.clickOnEnableButton();
    }
    @Test
    public void enableAnotherUserAccount() {
        adminPage.clickGOTOadminzoneButton();
        adminPage.clickOnViewUsersButton();
        searchingPage.seeCurrentUserProfileByName("Ann");
        adminPage.clickOnEnableButton();
        searchingPage.assertElementPresent("adminPage.disableButton");
    }

    private static void createPost(){
        postsPage. clickOnAddNewPostButton();
        postsPage. clickOnPostVisibilityButton();
        postsPage.postPublicVisibilityChoice();
        postsPage.typeMessageInMessageField(POST_MESSAGE);
        postsPage. clickOnSavePostButton();

        actions.assertElementPresent("posts.postExist");
        actions.assertElementPresent("posts.postIsPublic");
    }


}
