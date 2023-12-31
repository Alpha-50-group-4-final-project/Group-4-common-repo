package WEare.publicpart;

import WEare.BaseTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import jdk.jfr.Label;

import static com.telerikacademy.testframework.Utils.getUIMappingByKey;

@DisplayName("UnregisteredUserTests")
public class AnonymousUserTests extends BaseTest {
    private static final String firstNameUpdate = getUIMappingByKey("personalProfileUpdatePage.firstName");
    private static final String lastNameUpdate = getUIMappingByKey("personalProfileUpdatePage.lastName");
    private static final String name = firstNameUpdate+" "+lastNameUpdate;

    @Test
    @Label("Jira FPW-32")
    @Tag("HappyPath")
    @DisplayName("Validate Register button works")
    public void viewRegisterPage_when_registerButtonClicked() {
        homePage.navigateToRegisterPage();
        registrationPage.assertPageNavigated();
        registrationPage.validateHeader(getUIMappingByKey("registrationPage.header"));
    }

    @Test
    @Label("Jira FPW-33")
    @Tag("HappyPath")
    @DisplayName("Validate SignIn button works")
    public void viewLoginPage_when_signInButtonClicked() {
        homePage.navigateToLoginPage();
        loginPage.assertPageNavigated();
        loginPage.validateHeader(getUIMappingByKey("loginPage.header"));
    }

    @Test
    @Label("Jira FPW-34")
    @Tag("HappyPath")
    @DisplayName("Validate Latests posts button works")
    public void viewPublicPosts_when_latestPostsClicked() {
        postsPage.navigateToPage();
        postsPage.assertPageNavigated();
        postsPage.validateHeader(getUIMappingByKey("postPage.header"));
    }

    @Test
    @Label("Jira FPW-36")
    @Tag("HappyPath")
    @DisplayName("Search public user's profile as non-registered user")
    public void viewPublicProfiles_when_searchByNamePerformed() {
        api.registerUser(usernameRandom, passwordRandom);
        api.updateUserProfile(usernameRandom, passwordRandom, firstNameUpdate, lastNameUpdate);
        homePage.navigateToPage();
        homePage.typeIntoNameSearchBox(firstNameUpdate);
        homePage.clickOnSearchButton();
        homePage.validateSearchResult(name);
    }
}
