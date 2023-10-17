package WEare.publicpart;

import WEare.BaseTest;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import jdk.jfr.Label;

import static com.telerikacademy.testframework.Utils.getUIMappingByKey;

public class AnonymousUserTests extends BaseTest {
    private static String firstNameUpdate = getUIMappingByKey("personalProfilePage.firstName");

    @Test
    @Label("Jira FPW-32")
    @Tag("HappyPath")
    public void viewRegisterPage_when_registerButtonClicked() {
        homePage.navigateToRegisterPage();
        registrationPage.assertPageNavigated();
        registrationPage.validateHeader(getUIMappingByKey("registrationPage.header"));
    }

    @Test
    @Label("Jira FPW-33")
    @Tag("HappyPath")
    public void viewLoginPage_when_signInButtonClicked() {
        homePage.navigateToLoginPage();
        loginPage.assertPageNavigated();
        loginPage.validateHeader(getUIMappingByKey("loginPage.header"));
    }

    @Test
    @Label("Jira FPW-34")
    @Tag("HappyPath")
    public void viewPublicPosts_when_latestPostsClicked() {
        homePage.navigateToLatestPosts();
        postsPage.assertPageNavigated();
        postsPage.validateHeader(getUIMappingByKey("postPage.header"));
    }

    @Test
    @Label("Jira FPW-36")
    @Tag("HappyPath")
    public void viewPublicProfiles_when_searchByNamePerformed() {
        api.registerUser(usernameRandom, passwordRandom);
        api.updateUserProfile(usernameRandom, passwordRandom, firstNameUpdate, lastNameRandom);
        homePage.navigateToPage();
        homePage.typeIntoNameSearchBox(firstNameUpdate);
        homePage.clickOnSearchButton();
        homePage.validateSearchResult(firstNameUpdate);
    }

}
