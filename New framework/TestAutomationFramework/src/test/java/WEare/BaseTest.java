package WEare;

import com.telerikacademy.testframework.UserActions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import pages.WEare.CreateNewPostPage;
import pages.WEare.LoginPage;
import pages.WEare.UserRegistrationPage;


import static com.telerikacademy.testframework.data.RandomUsernamePasswordGenerator.randomPassword;
import static com.telerikacademy.testframework.data.RandomUsernamePasswordGenerator.randomUsername;

public class BaseTest {

    public static   String usernameRandom;
    public static String passwordRandom;

    static UserActions actions = new UserActions();
    protected UserRegistrationPage registerNewUser;
    protected LoginPage login;
    protected CreateNewPostPage createNewPostPage;

    @BeforeAll
    public static void setUp() {
        UserActions.loadBrowser("WEare.homePage");
    }

    @BeforeEach
    public void beforeTests(){
        usernameRandom = randomUsername();
        passwordRandom = randomPassword();


        registerNewUser = new UserRegistrationPage(actions.getDriver());
        login =new LoginPage(actions.getDriver());
        createNewPostPage = new CreateNewPostPage(actions.getDriver());
    }

//    @AfterAll
//    public static void tearDown() {
//
//        UserActions.quitDriver();
//    }

    public static void login() {

    }

}
