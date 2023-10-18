package WEare;
import com.github.javafaker.Faker;
import com.telerikacademy.testframework.UserActions;
import com.telerikacademy.testframework.api.PostModel;
import com.telerikacademy.testframework.api.WEareApi;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.extension.ExtendWith;
import pages.WEare.*;

import java.text.SimpleDateFormat;
import static com.telerikacademy.testframework.Utils.LOGGER;


@ExtendWith(MyTestWatcher.class)
public class BaseTest {

    public static String usernameRandom;
    public static String lastNameRandom;
    public static String passwordRandom;
    public static HomePage homePage;
    protected static UserActions actions;

    protected static WEareApi api;
    protected static PostModel apiPost;
    protected static UserRegistrationPage registrationPage;
    protected static LoginPage loginPage;

    protected static PersonalProfilePage editProfilePage;
    protected static SearchingPage searchPage;
    protected static AdminPage adminPage;
    protected static Faker faker;
    protected static PostsPage postsPage;
    protected static CommentsPage commentsPage;
    protected static SimpleDateFormat dtf;
    protected static String adminUsername;
    protected static String adminPassword;

    @BeforeAll
    public static void setUp() {
        UserActions actions = new UserActions();
        UserActions.loadBrowser("homePage");
        faker = new Faker();
        homePage = new HomePage(actions.getDriver());
        registrationPage = new UserRegistrationPage(actions.getDriver());
        loginPage = new LoginPage(actions.getDriver());
        editProfilePage = new PersonalProfilePage(actions.getDriver());
        searchPage = new SearchingPage(actions.getDriver());
        adminPage = new AdminPage(actions.getDriver());
        postsPage = new PostsPage(actions.getDriver());
        commentsPage = new CommentsPage(actions.getDriver());
        api = new WEareApi();
        apiPost = new PostModel();
        usernameRandom = faker.name().firstName();
        lastNameRandom = faker.name().lastName();
        adminUsername = faker.name().firstName() + "admin";
        adminPassword = "12345678";
        LOGGER.info("The follow username was generated: " + usernameRandom);
        passwordRandom = faker.internet().password(8, 20, true, true);
        LOGGER.info("The follow password was generated: " + usernameRandom);
        dtf = new SimpleDateFormat("yyyy/MM/dd");

    }

    @AfterAll
    public static void logOutFromAccount() {
        logout();
        UserActions.quitDriver();
    }

    public static void login(String username, String password) {
        loginPage.navigateToSignIn();
        loginPage.fillUsername(username);
        loginPage.fillPassword(password);
        loginPage.submitForm();
    }

    public static void logout() {
        homePage.navigateToHomePage();
        loginPage.clickOnLogOutButton();
    }
}

