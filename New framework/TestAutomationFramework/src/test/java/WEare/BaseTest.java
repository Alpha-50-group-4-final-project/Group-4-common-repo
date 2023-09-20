package WEare;

import com.telerikacademy.testframework.UserActions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import pages.WEare.WEareCreateNewPostPage;
import pages.WEare.WEareLoginPage;
import pages.WEare.WEareNewUserRegistrationPage;

import static com.telerikacademy.testframework.data.RandomUsernamePasswordGenerator.randomPassword;
import static com.telerikacademy.testframework.data.RandomUsernamePasswordGenerator.randomUsername;

public class BaseTest {

    public static   String usernameRandom;
    public static String passwordRandom;

    static UserActions actions = new UserActions();
    protected WEareNewUserRegistrationPage registerNewUser;
    protected WEareLoginPage login;
    protected WEareCreateNewPostPage post;

    @BeforeAll
    public static void setUp() {
        UserActions.loadBrowser("WEare.homePage");
    }

    @BeforeEach
    public void beforeTests(){
        usernameRandom = randomUsername();
        passwordRandom = randomPassword();


        registerNewUser = new WEareNewUserRegistrationPage(actions.getDriver());
        login =new WEareLoginPage(actions.getDriver());
        post = new WEareCreateNewPostPage(actions.getDriver());
    }

//    @AfterAll
//    public static void tearDown() {
//
//        UserActions.quitDriver();
//    }

    public static void login() {

    }

}
