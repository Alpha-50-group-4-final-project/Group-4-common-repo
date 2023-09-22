package WEare.publicpart;

import WEare.BaseTest;
import org.junit.jupiter.api.Test;

public class AnonymousUserTests extends BaseTest {
    @Test
    public void viewRegisterPage_when_registerButtonClicked() {
        homePage.navigateToRegisterPage();
        registrationPage.assertPageNavigated();
        //assert header?
    }
    @Test
    public void viewLoginPage_when_signInButtonClicked() {
        homePage.navigateToLoginPage();
        loginPage.assertPageNavigated();
        //assert header?
    }
    @Test
    public void viewPublicPosts_when_latestPostsClicked() {
        homePage.navigateToLatestPosts();
        latestPostPage.assertPageNavigated();
        latestPostPage.assertPublicPostShown();
        //assert header
        //assert latest post?
    }

    @Test
    public void viewPublicProfiles_when_searchPublicProfilesPerformed() {
    }

    @Test
    public void errorMessage_when_searchPrivateProfilePerformed(){

    }
    @Test
    public void errorMessage_when_searchUnexistingProfilePerformed(){

    }
}
