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
        // postsAndCommentsPage.createPost();
        // postsAndCommentsPage.addComment("Automated comment by selenium driver");
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
        postsAndCommentsPage.clickShowComments();
        postsAndCommentsPage.clickEditComment();
        postsAndCommentsPage.editComment();
        // postsAndCommentsPage.validateCommentEdited();
    }


    @Test
    public void deleteLatestComment_when_deleteCommentClicked() {
        homePage.navigateToLatestPosts();
        latestPostPage.clickExplorePost();
        postsAndCommentsPage.clickShowComments();
        postsAndCommentsPage.clickDeleteComment();
        postsAndCommentsPage.deleteComment();
        //validation
    }

    @Test
    public void disableAnotherUserAccount() {
        adminPage.clickGOTOadminzoneButton();
        adminPage.clickOnViewUsersButton();
        searchingPage.seeCurrentUserProfileByName("");
        searchingPage.assertElementPresent("adminPage.disableButton");
        adminPage.clickDisableButton();
        searchingPage.assertElementPresent("adminPage.enableButton");
    }
    @Test
    public void enableAnotherUserAccount() {
        adminPage.clickGOTOadminzoneButton();
        adminPage.clickOnViewUsersButton();
        searchingPage.seeCurrentUserProfileByName("");
        searchingPage.assertElementPresent("adminPage.enableButton");
        adminPage.clickOnEnableButton();
        searchingPage.assertElementPresent("adminPage.disableButton");
    }

}
