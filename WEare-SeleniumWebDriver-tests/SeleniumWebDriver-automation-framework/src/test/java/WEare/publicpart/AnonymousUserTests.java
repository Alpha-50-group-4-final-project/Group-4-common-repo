package WEare.publicpart;

import WEare.BaseTest;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import jdk.jfr.Label;

import static com.telerikacademy.testframework.Utils.getUIMappingByKey;

public class AnonymousUserTests extends BaseTest {
    private static String firstNameUpdate = getUIMappingByKey("personalProfilePage.firstName");

    @BeforeAll
    public static void testSetUp() {
        api.registerUser(usernameRandom, passwordRandom);
        api.updateUserProfile(usernameRandom, passwordRandom, firstNameUpdate, lastNameRandom);
    }

    @Test
    @Label("Jira FPW-32")
    public void viewRegisterPage_when_registerButtonClicked() {
        homePage.navigateToRegisterPage();
        registrationPage.assertPageNavigated();
        registrationPage.validateHeader(getUIMappingByKey("registrationPage.header"));
    }

    @Test
    @Label("Jira FPW-33")
    public void viewLoginPage_when_signInButtonClicked() {
        homePage.navigateToLoginPage();
        loginPage.assertPageNavigated();
        loginPage.validateHeader(getUIMappingByKey("loginPage.header"));
    }

    @Test
    @Label("Jira FPW-34")
    public void viewPublicPosts_when_latestPostsClicked() {
        homePage.navigateToLatestPosts();
        postsPage.assertPageNavigated();
        postsPage.validateHeader(getUIMappingByKey("postPage.header"));
    }

    @Test
    @Label("Jira FPW-36")
    public void viewPublicProfiles_when_searchByNamePerformed() {
        homePage.navigateToPage();
        homePage.typeIntoNameSearchBox(firstNameUpdate);
        homePage.clickOnSearchButton();
        homePage.validateSearchResult(firstNameUpdate);
    }

}
