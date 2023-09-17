package test.cases.trello;

import com.telerikacademy.testframework.UserActions;
import org.junit.AfterClass;
import org.junit.BeforeClass;

public class BaseTest {

    static UserActions actions = new UserActions();

    @BeforeClass
    public static void setUp() {

        UserActions.loadBrowser("WEare.homePage");
    }

//    @AfterClass
//    public static void tearDown() {
//
//        UserActions.quitDriver();
//    }

    public static void login() {

    }

}
