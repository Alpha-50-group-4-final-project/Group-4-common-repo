package WEare;

import com.telerikacademy.testframework.UserActions;
import org.junit.jupiter.api.BeforeAll;
import pages.WEare.WEareCreateNewPostPage;
import pages.WEare.WEareLoginPage;
import pages.WEare.WEareNewUserRegistrationPage;

import static com.telerikacademy.testframework.data.RandomUsernamePasswordGenerator.randomPassword;
import static com.telerikacademy.testframework.data.RandomUsernamePasswordGenerator.randomUsername;


public class BaseTest {

    public static   String username=randomUsername();
    public static String passsword =randomPassword();

    static UserActions actions = new UserActions();
    protected WEareNewUserRegistrationPage registerNewUser = new WEareNewUserRegistrationPage(actions.getDriver());
    protected WEareLoginPage login =new WEareLoginPage(actions.getDriver());
    protected WEareCreateNewPostPage post=new WEareCreateNewPostPage(actions.getDriver());

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
