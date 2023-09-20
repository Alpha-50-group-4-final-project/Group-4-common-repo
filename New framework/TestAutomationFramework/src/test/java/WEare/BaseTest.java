package WEare;

import com.telerikacademy.testframework.UserActions;
import org.junit.jupiter.api.BeforeAll;
import pages.WEare.CreateNewPostPage;
import pages.WEare.LoginPage;
import pages.WEare.UserRegistrationPage;

import static com.telerikacademy.testframework.data.RandomUsernamePasswordGenerator.randomPassword;
import static com.telerikacademy.testframework.data.RandomUsernamePasswordGenerator.randomUsername;


public class BaseTest {

    public static   String username=randomUsername();
    public static String passsword =randomPassword();

    static UserActions actions = new UserActions();
    protected UserRegistrationPage registerNewUser = new UserRegistrationPage(actions.getDriver());
    protected LoginPage login =new LoginPage(actions.getDriver());
    protected CreateNewPostPage post=new CreateNewPostPage(actions.getDriver());

    @BeforeAll
    public static void setUp() {
        UserActions.loadBrowser("WEare.homePage");
    }

//    @AfterAll
//    public static void tearDown() {
//
//        UserActions.quitDriver();
//    }

    public static void login() {

    }

}
