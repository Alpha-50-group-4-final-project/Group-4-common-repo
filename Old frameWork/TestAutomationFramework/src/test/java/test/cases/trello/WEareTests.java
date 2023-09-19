package test.cases.trello;

import org.junit.jupiter.api.Test;
import pages.WEare.WEareCreateNewPostPage;
import pages.WEare.WEareLoginPage;
import pages.WEare.WEareNewUserRegistrationPage;

public class WEareTests extends BaseTest {

    @Test
    public void registerNewUserTest() {
        WEareNewUserRegistrationPage registerNewUser = new WEareNewUserRegistrationPage(actions.getDriver());
        registerNewUser.registerNewUser(username, passsword);
    }

    @Test
    public void loginS() {
        WEareNewUserRegistrationPage registerNewUser = new WEareNewUserRegistrationPage(actions.getDriver());
        registerNewUser.registerNewUser(username, passsword);

        WEareLoginPage login =new WEareLoginPage(actions.getDriver());
        login.login(username,passsword);
    }

    @Test
    public void createNewPost(){
        WEareNewUserRegistrationPage registerNewUser = new WEareNewUserRegistrationPage(actions.getDriver());
        registerNewUser.registerNewUser(username, passsword);

        WEareLoginPage login =new WEareLoginPage(actions.getDriver());
        login.login(username,passsword);

        WEareCreateNewPostPage post=new WEareCreateNewPostPage(actions.getDriver());
        post.createNewPost();
    }

//    comment
}

