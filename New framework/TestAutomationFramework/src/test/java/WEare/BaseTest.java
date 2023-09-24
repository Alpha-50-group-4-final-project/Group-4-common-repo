package WEare;

import com.github.javafaker.Faker;
import com.telerikacademy.testframework.UserActions;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import pages.WEare.*;


import static com.telerikacademy.testframework.data.RandomUsernamePasswordGenerator.randomPassword;
import static com.telerikacademy.testframework.data.RandomUsernamePasswordGenerator.randomUsername;

public class BaseTest {

    public static   String usernameRandom;
    public static String passwordRandom;
    public static HomePage homePage;
    protected static UserActions actions = new UserActions();

    public static UserRegistrationPage registrationPage;
    public static LoginPage loginPage;
    public static CreateNewPostPage createNewPostPage;
    public static PersonalProfilePage editProfilePage;
    public static LatestPostPage latestPostPage;
    public static SearchingPage searchingPage;
    public static Faker faker;
    public static PostsAndCommentsPage postsAndCommentsPage;


    @BeforeAll
    public static void setUp() {
        UserActions.loadBrowser("WEare.homePage");

        usernameRandom = randomUsername();
        passwordRandom = randomPassword();

        homePage = new HomePage(actions.getDriver());
        registrationPage = new UserRegistrationPage(actions.getDriver());
        loginPage =new LoginPage(actions.getDriver());
        createNewPostPage = new CreateNewPostPage(actions.getDriver());
        editProfilePage =new PersonalProfilePage(actions.getDriver());
        latestPostPage =new LatestPostPage(actions.getDriver());
       searchingPage=new SearchingPage(actions.getDriver());
       faker=new Faker();
       postsAndCommentsPage = new PostsAndCommentsPage(actions.getDriver());
    }

//    @BeforeEach
//    public void beforeTests(){
//
//    }

    @AfterAll
    public static void tearDown() {

        UserActions.quitDriver();
    }

    public static void userSetUP(String firstName,String lastName,String birthdayDate) {
        editProfilePage.fillUpFirstNameField(firstName);
        editProfilePage.fillUpLastNameField(lastName);
        editProfilePage.fillBirthdayField(birthdayDate);
        editProfilePage.clickPersonalInformationUpdateButton();
    }
    public static void registerUser(String username,String password){
        homePage.navigateToRegisterPage();
        registrationPage.fillUsernameField(username);
        registrationPage.fillEmailField(password);
        registrationPage.fillPasswordFields(password);
        registrationPage.selectCategoryField();
        registrationPage.clickRegistryButton();
        registrationPage.assertUserCreatedWithWelcomeText();
    }
    public  static void login(String username,String password){
        loginPage.clickOnLoginButton();
        loginPage.fillUsernameField(username);
        loginPage.fillPasswordField(password);
        loginPage.clickOnSubmitButton();
        loginPage.assertElementPresent("WEare.homePage.LogoutButton");
    }

}
