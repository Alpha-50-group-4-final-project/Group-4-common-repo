package WEare;


import org.junit.jupiter.api.Test;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import pages.WEare.WEareCreateNewPostPage;
import pages.WEare.WEareLoginPage;
import pages.WEare.WEareNewUserRegistrationPage;

public class WEareTests extends BaseTest {


    @ParameterizedTest
    @CsvSource({ "Boris,123Password_","Dinko,123.,"})
    public void registerNewUserTest_When_validCredentials_arePassed(String username,String password,String expectedMessage) {
        WEareNewUserRegistrationPage registerNewUser = new WEareNewUserRegistrationPage(actions.getDriver());
        registerNewUser.registerNewUser(username, password);
        actions.assertElementPresent("WEare.RegisterLoginErrorMessage");
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

