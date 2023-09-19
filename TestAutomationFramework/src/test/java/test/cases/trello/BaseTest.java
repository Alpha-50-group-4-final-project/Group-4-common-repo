package test.cases.trello;

import com.telerikacademy.testframework.UserActions;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;

import static com.telerikacademy.testframework.test.data.RandomUsernamePasswordGenerator.randomPassword;
import static com.telerikacademy.testframework.test.data.RandomUsernamePasswordGenerator.randomUsername;

public class BaseTest {

    public static   String username=randomUsername();
    public static String passsword =randomPassword();

    static UserActions actions = new UserActions();

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
