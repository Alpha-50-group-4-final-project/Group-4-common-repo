package test.cases.trello;

import com.telerikacademy.testframework.UserActions;
import org.junit.AfterClass;
import org.junit.BeforeClass;

import static com.telerikacademy.testframework.test.data.RandomUsernamePasswordGenerator.randomPassword;
import static com.telerikacademy.testframework.test.data.RandomUsernamePasswordGenerator.randomUsername;

public class BaseTest {

    public static   String username=randomUsername();
    public static String passsword =randomPassword();

    static UserActions actions = new UserActions();

    @BeforeClass
    public static void setUp() {
        UserActions.loadBrowser("WEare.homePage");
    }

    @AfterClass
    public static void tearDown() {

        UserActions.quitDriver();
    }

    public static void login() {

    }

}
