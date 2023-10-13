package WEare.publicpart;

import WEare.BaseTest;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class AnonymousUserTests extends BaseTest {
    @BeforeAll
    public static void testSetUp(){
        api.registerUser(usernameRandom,passwordRandom);
        api.updateUserProfile(usernameRandom,passwordRandom,"Ann","Petrova");
    }


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
        postsPage.assertPageNavigated();
        postsPage.validateHeader("Explore all posts");
    }
    @Test
    public void viewPublicProfiles_when_searchByNamePerformed() {
        homePage.navigateToPage();
        //create & update user with API
        homePage.typeIntoNameSearchBox("Ann");
        homePage.clickOnSearchButton();
        homePage.validateSearchResult("Ann");
    }


}
