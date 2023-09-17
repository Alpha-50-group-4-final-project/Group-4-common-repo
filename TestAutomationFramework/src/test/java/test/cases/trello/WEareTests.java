package test.cases.trello;

import org.junit.Test;
import pages.WEare.WEareNewUserRegistrationPage;

public class WEareTests extends BaseTest {

    @Test
    public void registerNewUserTest() {
        WEareNewUserRegistrationPage registerNewUser = new WEareNewUserRegistrationPage(actions.getDriver());
        registerNewUser.registerNewUser(username, passsword);
    }
}

