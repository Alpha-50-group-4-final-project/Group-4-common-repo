package WEare;

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
    }

//    @BeforeEach
//    public void beforeTests(){
//
//    }

    @AfterAll
    public static void tearDown() {

        UserActions.quitDriver();
    }

    public static void userSetUP(String firstName,String lastName) {
        editProfilePage.fillUpFirstNameField(firstName);
        editProfilePage.fillUpLastNameField(lastName);
        editProfilePage.fillBirthdayField("01", "15", "2023");
        editProfilePage.clickPersonalInformationUpdateButton();
    }

}
