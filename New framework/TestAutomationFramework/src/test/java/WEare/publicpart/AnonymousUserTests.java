package WEare.publicpart;

import WEare.BaseTest;
import org.junit.jupiter.api.Test;

public class AnonymousUserTests extends BaseTest {
    @Test
    public void viewRegisterPage_when_registerButtonClicked() {
        homePage.navigateToRegisterPage();
        registrationPage.assertPageNavigated();
        registrationPage.validateHeader("Join our community");
    }
    @Test
    public void viewLoginPage_when_signInButtonClicked() {
        homePage.navigateToLoginPage();
        loginPage.assertPageNavigated();
        loginPage.validateHeader("Login Page");
    }
    @Test
    public void viewPublicPosts_when_latestPostsClicked() {
        homePage.navigateToLatestPosts();
        latestPostPage.assertPageNavigated();
        latestPostPage.validateHeader("Explore all posts");
    }
    @Test
    public void postOrderedChronologically_when_latestPostsClicked(){
        //api create profile and public posts?

        homePage.navigateToLatestPosts();
        latestPostPage.assertPageNavigated();
        latestPostPage.assertPublicPostOrdered();
    }

    @Test
    public void viewPublicProfiles_when_searchByNamePerformed() {
        homePage.navigateToPage();
        homePage.typeIntoNameSearchBox("Ann");
        homePage.clickOnSearchButton();
        homePage.validateSearchResult("Ann");
    }

//    @Test
//    public void errorMessage_when_searchPrivateProfilePerformed(){
//
//    }
//    @Test
//    public void errorMessage_when_searchUnexistingProfilePerformed(){
//
//    }
}
