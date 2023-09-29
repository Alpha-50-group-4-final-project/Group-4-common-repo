package WEare;

import com.github.javafaker.Faker;
import com.telerikacademy.testframework.UserActions;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import pages.WEare.*;

import static com.telerikacademy.testframework.Utils.LOGGER;


public class BaseTest {

    public static String usernameRandom;
    public static String passwordRandom;
    public static HomePage homePage;
    protected static UserActions actions = new UserActions();

    public static UserRegistrationPage registrationPage;
    public static LoginPage loginPage;

    public static PersonalProfilePage editProfilePage;
    public static LatestPostPage latestPostPage;
    public static SearchingPage searchingPage;
    public static AdminPage adminPage;
    public static Faker faker;
    public static PostsPage postsPage;

    public static CommentsPage comments;




    @BeforeAll
    public static void setUp() {
        UserActions.loadBrowser("homePage");
        faker = new Faker();

        homePage = new HomePage(actions.getDriver());
        registrationPage = new UserRegistrationPage(actions.getDriver());
        loginPage = new LoginPage(actions.getDriver());
        editProfilePage = new PersonalProfilePage(actions.getDriver());
        latestPostPage = new LatestPostPage(actions.getDriver());
        searchingPage = new SearchingPage(actions.getDriver());
        adminPage = new AdminPage(actions.getDriver());
        postsPage = new PostsPage(actions.getDriver());
        comments = new CommentsPage(actions.getDriver());

        usernameRandom = faker.name().firstName();
        LOGGER.info("The follow username was generated: "+usernameRandom );
        passwordRandom = faker.internet().password(8,20,true,true);
        LOGGER.info("The follow password was generated: "+usernameRandom );
    }

    @AfterAll
    public static void tearDown() {

        UserActions.quitDriver();
    }

    public static void userSetUP(String firstName, String lastName, String birthdayDate) {
        editProfilePage.fillUpFirstNameField(firstName);
        editProfilePage.fillUpLastNameField(lastName);
        editProfilePage.fillBirthdayField(birthdayDate);
        editProfilePage.clickPersonalInformationUpdateButton();
    }

    public static void registerUser(String username, String password) {
        homePage.navigateToRegisterPage();
        registrationPage.fillUsernameField(username);
        registrationPage.fillEmailField(username);
        registrationPage.fillPasswordFields(password);
        registrationPage.selectCategoryField();
        registrationPage.clickRegistryButton();
        registrationPage.assertUserCreatedWithWelcomeText();
        LOGGER.info("User with the following user name: "  +username +"and password: "+password +" was registered." );
    }

    public static void login(String username, String password) {
        loginPage.clickOnLoginButton();
        loginPage.fillUsernameField(username);
        loginPage.fillPasswordField(password);
        loginPage.clickOnSubmitButton();
        loginPage.assertElementPresent("homePage.LogoutButton");
        LOGGER.info("User with the following user name: "  +username +"and password: "+password +" has logged in successfully." );
    }


}
