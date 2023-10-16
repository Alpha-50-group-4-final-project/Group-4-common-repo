package WEare.publicpart;

import WEare.BaseTest;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import jdk.jfr.Label;
import org.junit.platform.suite.api.Suite;

import static com.telerikacademy.testframework.Utils.getUIMappingByKey;

@Suite
public class AnonymousUserTests extends BaseTest {
    private static String firstNameUpdate = getUIMappingByKey("personalProfilePage.firstName");

    @Test
    @Label("Jira FPW-32")
    public void viewRegisterPage_when_registerButtonClicked() {
        homePage.navigateToRegisterPage();
        registrationPage.assertPageNavigated();
        registrationPage.validateHeader(getUIMappingByKey("registrationPage.header"));
    }

    @Test
    @Label("Jira FPW-33")
    @Tag("Happy path")
    public void viewLoginPage_when_signInButtonClicked() {
        homePage.navigateToLoginPage();
        loginPage.assertPageNavigated();
        loginPage.validateHeader(getUIMappingByKey("loginPage.header"));
    }

    @Test
    @Label("Jira FPW-34")
    @Tag("Happy path")
    public void viewPublicPosts_when_latestPostsClicked() {
        homePage.navigateToLatestPosts();
        postsPage.assertPageNavigated();
        postsPage.validateHeader(getUIMappingByKey("postPage.header"));
    }

    @Test
    @Label("Jira FPW-36")
    @Tag("Happy path")
    public void viewPublicProfiles_when_searchByNamePerformed() {
        api.registerUser(usernameRandom, passwordRandom);
        api.updateUserProfile(usernameRandom, passwordRandom, firstNameUpdate, lastNameRandom);
        homePage.navigateToPage();
        homePage.typeIntoNameSearchBox(firstNameUpdate);
        homePage.clickOnSearchButton();
        homePage.validateSearchResult(firstNameUpdate);
    }

}
