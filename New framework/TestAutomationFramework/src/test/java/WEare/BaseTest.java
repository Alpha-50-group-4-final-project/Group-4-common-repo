package WEare;

import com.telerikacademy.testframework.UserActions;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import pages.WEare.*;


import static com.telerikacademy.testframework.data.RandomUsernamePasswordGenerator.randomPassword;
import static com.telerikacademy.testframework.data.RandomUsernamePasswordGenerator.randomUsername;

public class BaseTest {

    public static   String usernameRandom;
    public static String passwordRandom;
    protected static HomePage homePage;
    static UserActions actions = new UserActions();

    protected static UserRegistrationPage registerUser;
    protected static LoginPage login;
    protected static CreateNewPostPage createNewPostPage;
    protected static PersonalProfilePage profileEdit;


    @BeforeAll
    public static void setUp() {
        UserActions.loadBrowser("WEare.homePage");

        usernameRandom = randomUsername();
        passwordRandom = randomPassword();

        homePage = new HomePage(actions.getDriver());
        registerUser = new UserRegistrationPage(actions.getDriver());
        login =new LoginPage(actions.getDriver());
        createNewPostPage = new CreateNewPostPage(actions.getDriver());
        profileEdit=new PersonalProfilePage(actions.getDriver());
    }

//    @BeforeEach
//    public void beforeTests(){
//
//    }

//    @AfterAll
//    public static void tearDown() {
//
//        UserActions.quitDriver();
//    }

    public static void login() {

    }


}
